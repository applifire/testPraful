Ext.define('Eproject.eproject.web.com.view.organization.locationmanagement.AddressTypeMain', {
     "xtype": "addressTypeMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "AddressTypeMainController",
     "restURL": "/AddressType",
     "defaults": {
          "split": true
     },
     "requires": ["Eproject.eproject.shared.com.model.organization.locationmanagement.AddressTypeModel", "Eproject.eproject.web.com.controller.organization.locationmanagement.AddressTypeMainController", "Eproject.eproject.shared.com.viewmodel.organization.locationmanagement.AddressTypeViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Address Type",
               "name": "AddressTypeTreeContainer",
               "itemId": "AddressTypeTreeContainer",
               "restURL": "/AddressType",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "AddressTypeTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": [{
                         "name": "addressType",
                         "itemId": "addressType",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type",
                         "fieldId": "DC6E9A67-1252-44A8-B6DA-65B7ED73CF4A",
                         "minLength": "0",
                         "maxLength": "128",
                         "errorMessage": "Please enter address type",
                         "bindable": "addressType"
                    }]
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Address Type",
                    "title": "Address Type",
                    "name": "AddressType",
                    "itemId": "AddressTypeForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "addressTypeId",
                         "itemId": "addressTypeId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type Id<font color='red'> *<\/font>",
                         "fieldId": "6378E17B-30C8-446D-8F31-4FE859E1C756",
                         "minLength": "0",
                         "maxLength": "64",
                         "hidden": true,
                         "value": "",
                         "bindable": "addressTypeId"
                    }, {
                         "name": "addressType",
                         "itemId": "addressType",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "DC6E9A67-1252-44A8-B6DA-65B7ED73CF4A",
                         "minLength": "0",
                         "maxLength": "128",
                         "errorMessage": "Please enter address type",
                         "bindable": "addressType",
                         "columnWidth": 0.5
                    }, {
                         "name": "addressTypeDesc",
                         "itemId": "addressTypeDesc",
                         "xtype": "textareafield",
                         "customWidgetType": "vdTextareafield",
                         "displayName": "Address Type Desc",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type Desc",
                         "fieldId": "639ADAE5-6EA8-402D-972D-0807FFC2E823",
                         "bindable": "addressTypeDesc",
                         "columnWidth": 0.5
                    }, {
                         "name": "addressTypeIcon",
                         "itemId": "addressTypeIcon",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type Icon",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type Icon",
                         "fieldId": "49511D9D-2C40-4193-BF0F-2C910F891CC6",
                         "minLength": "0",
                         "maxLength": "128",
                         "bindable": "addressTypeIcon",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "D796B1B9-F6D3-4780-81B3-F3F2DA2BD8C0",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 845,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 845,
                              "customId": 528
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 845,
                              "customId": 529,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 845,
                              "customId": 530,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Address Type",
                    "title": "Details Grid",
                    "name": "AddressTypeGrid",
                    "itemId": "AddressTypeGrid",
                    "restURL": "/AddressType",
                    "columns": [{
                         "header": "Address Type Id",
                         "dataIndex": "addressTypeId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Address Type",
                         "dataIndex": "addressType",
                         "flex": 1
                    }, {
                         "header": "Address Type Desc",
                         "dataIndex": "addressTypeDesc",
                         "flex": 1
                    }, {
                         "header": "Address Type Icon",
                         "dataIndex": "addressTypeIcon",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Address Type",
               "title": "Address Type",
               "name": "AddressType",
               "itemId": "AddressTypeForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "addressTypeId",
                    "itemId": "addressTypeId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Address Type Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type Id<font color='red'> *<\/font>",
                    "fieldId": "6378E17B-30C8-446D-8F31-4FE859E1C756",
                    "minLength": "0",
                    "maxLength": "64",
                    "hidden": true,
                    "value": "",
                    "bindable": "addressTypeId"
               }, {
                    "name": "addressType",
                    "itemId": "addressType",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Address Type",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "DC6E9A67-1252-44A8-B6DA-65B7ED73CF4A",
                    "minLength": "0",
                    "maxLength": "128",
                    "errorMessage": "Please enter address type",
                    "bindable": "addressType",
                    "columnWidth": 0.5
               }, {
                    "name": "addressTypeDesc",
                    "itemId": "addressTypeDesc",
                    "xtype": "textareafield",
                    "customWidgetType": "vdTextareafield",
                    "displayName": "Address Type Desc",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type Desc",
                    "fieldId": "639ADAE5-6EA8-402D-972D-0807FFC2E823",
                    "bindable": "addressTypeDesc",
                    "columnWidth": 0.5
               }, {
                    "name": "addressTypeIcon",
                    "itemId": "addressTypeIcon",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Address Type Icon",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type Icon",
                    "fieldId": "49511D9D-2C40-4193-BF0F-2C910F891CC6",
                    "minLength": "0",
                    "maxLength": "128",
                    "bindable": "addressTypeIcon",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "D796B1B9-F6D3-4780-81B3-F3F2DA2BD8C0",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 845,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 845,
                         "customId": 528
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 845,
                         "customId": 529,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 845,
                         "customId": 530,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});