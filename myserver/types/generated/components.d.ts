import type { Schema, Attribute } from '@strapi/strapi';

export interface TimestapmsDatetime extends Schema.Component {
  collectionName: 'components_timestapms_datetimes';
  info: {
    displayName: 'Datetime';
    description: '';
  };
  attributes: {
    createdAtLocal: Attribute.DateTime;
    updatedAtLocal: Attribute.DateTime;
  };
}

declare module '@strapi/types' {
  export module Shared {
    export interface Components {
      'timestapms.datetime': TimestapmsDatetime;
    }
  }
}
