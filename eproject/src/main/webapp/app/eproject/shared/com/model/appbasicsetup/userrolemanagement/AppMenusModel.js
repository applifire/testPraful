Ext.define('Eproject.eproject.shared.com.model.appbasicsetup.userrolemanagement.AppMenusModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "menuId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "menuTreeId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "menuIcon",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "menuAction",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "menuCommands",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "menuDisplay",
          "type": "boolean",
          "defaultValue": ""
     }, {
          "name": "menuHead",
          "type": "boolean",
          "defaultValue": ""
     }, {
          "name": "menuLabel",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "uiType",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "refObjectId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "menuAccessRights",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "appType",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "appId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "autoSave",
          "type": "boolean",
          "defaultValue": ""
     }, {
          "name": "startDate",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "expiryDate",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "goLiveDate",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});