'use strict';

const category = require('../controllers/category');

/**
 * category service
 */

const { createCoreService } = require('@strapi/strapi').factories;

function logDebugStrapi(message) {
    strapi.log.debug(message)
}

module.exports = createCoreService('api::category.category', ({ strapi }) =>  ({
    
    // custom
    async sync(ctx) {


        // get user and request body
        const user = ctx.state.user
        const { body } = ctx.request
        const type = 'api::category.category'

        // get from database all user's categories
        const categoriesFromDb = await strapi.db.query(type)
            .findMany({
                filters: {
                    userId: user.id
                }
            })

        
        // transformation category from DB to dictionary
        const dictCategoriesFromDb = Object.fromEntries(categoriesFromDb.map(
            (c) => [c.idLocal, c]
        ))

        // filter categories from request from those that are already on server with a later date
        const categoriesFromClient = body.filter( (category) => 
            dictCategoriesFromDb[category.idLocal] ?
            dictCategoriesFromDb[category.idLocal].updatedAtLocal < category.updatedAtLocal : true
        ).map(category => {
            category.userId = user.id
            return category
        })

        // split categories from request into those that need to be added and those that need to be updated
        const categoriesToCreate = categoriesFromClient.filter ( category =>
            !dictCategoriesFromDb[category.idLocal]
        )

        const categoriesToUpdate = categoriesFromClient.filter ( category =>
            dictCategoriesFromDb[category.idLocal]
        )

        // // filter categories which need to be added from already existed categories

        // save and update data at server
        if (categoriesToCreate.length > 0) {
            await strapi.db.query(type).createMany({ data: categoriesToCreate })
        } else {

            logDebugStrapi("categoriesToCreate.length < 0")
        }

        if (categoriesToUpdate.length > 0) {
            logDebugStrapi("categoriesToUpdate: " + JSON.stringify(categoriesToUpdate, null, 2));
            // delete all categories with idLocal
            await strapi.db.query(type).deleteMany({
                where: {
                    idLocal: {
                        $in: categoriesToUpdate.map(c => c.idLocal)
                    }
                }
            })

            // add new categories (data)
            await strapi.db.query(type).createMany({
                data: categoriesToUpdate
            })
        } else {

            logDebugStrapi("categoriesToUpdate.length < 0")
        }

        // send client categories which client doesn't has or which were updated latest
        const dictCategoriesFromClient = Object.fromEntries(body.map(c => [c.idLocal, c]))

        return categoriesFromDb
            .filter(category => dictCategoriesFromClient[category.idLocal] ?
                dictCategoriesFromClient[category.idLocal].updatedAtLocal < category.updatedAtLocal : true
        )
    },
  
  
    async create(ctx) {
        const user = ctx.state.user

        const { body } = ctx.request
        const category = body.data
        category.userId = user.id.toString()
        await strapi.entityService.create('api::category.category', {
            data: category
        })

        strapi.log.debug("user id = " + user.id.toString())
        logDebugStrapi("user: " + user.username)
        logDebugStrapi("user: " + user.email)

        return category;
    }
  }));
