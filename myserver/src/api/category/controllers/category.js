'use strict';

/**
 * category controller
 */

const { createCoreController } = require('@strapi/strapi').factories;

module.exports = createCoreController('api::category.category',
  ({ strapi }) => ({

    // default
    async find(ctx) {
      
      const result = await strapi.entityService.findMany('api::category.category', {
        filters: {
          userId: ctx.state.user.id
        }
      })
      return result;
        // return null;
    },


    async create(ctx) {
      const newCategory = await strapi.service('api::category.category').create(ctx)
      const sanCategory = await this.sanitizeOutput(newCategory, ctx);
      ctx.body = sanCategory
    },

    // custom
    async sync(ctx) {
      return await strapi.service('api::category.category').sync(ctx)
      // ctx.body = "data was synced!";
    },
  })
);