Ext.define('Eproject.view.usermanagement.admin.AddUserDetails', {
	extend : 'Ext.form.Panel',
	xtype : 'addUserDetails',
	itemId : 'addUserDetails',
	requires : [ 'Eproject.view.usermanagement.admin.AddUserDetailsController', 'Eproject.view.usermanagement.admin.AddUserDetailsViewModel', 'Eproject.model.AddUserDataModel',
	// 'Eproject.p2.com.p2.model.Contacts.CoreContactsModel',
	// 'Eproject.p2.com.p2.model.Authentication.UserModel',
	],
	controller : 'addUserController',
	viewModel : 'addUserModel',
	margin : '3 0 0 0',
	layout : 'anchor',
	autoScroll : true,
	bodyPadding : '8',
	items : [ {
		xtype : 'fieldset',
		title : 'General Info',
		width : '100%',
		layout : 'column',
		items : [ {
			columnWidth : .50,
			defaults : {
				width : '95%',
				margin : '8'
			},
			items : [ {
				xtype : 'combo',
				name : 'titleId',
				fieldLabel : 'Title<font color="red">*</font>',
				store : {
					fields : [ 'titleId', 'titles' ],
					data : [],
					sorters : [ 'titles', 'ASC' ]
				},
				allowBlank : false,
				emptyText : 'Select Title',
				queryMode : 'local',
				forceSelection : true,
				displayField : 'titles',
				valueField : 'titleId',
				listeners : {
					afterrender : 'afterTitleComboRender'
				},
				bind : "{coreContacts.titleId}"
			}, {
				xtype : 'textfield',
				name : 'firstName',
				fieldLabel : 'First Name<font color="red">*</font>',
				allowBlank : false,
				regex : /^[a-zA-Z]+$/,
				invalidText : 'First Name should be character only!',
				bind : "{coreContacts.firstName}"
			}, {
				xtype : 'textfield',
				name : 'middleName',
				fieldLabel : 'Middle Name',
				regex : /^[a-zA-Z ]+$/,
				invalidText : 'Middle Name should be character only!',
				bind : "{coreContacts.middleName}"
			}, {
				xtype : 'textfield',
				name : 'lastName',
				fieldLabel : 'Last Name<font color="red">*</font>',
				allowBlank : false,
				regex : /^[a-zA-Z]+$/,
				invalidText : 'Last Name should be character only!',
				bind : "{coreContacts.lastName}"
			}, {
				xtype : 'datefield',
				name : 'dateofbirth',
				itemId : 'dateofbirth',
				fieldLabel : 'DOB',
				maxValue : new Date(),
				format: 'd-m-Y',
                submitFormat: 'd-m-Y',
				listeners : {
					select : 'calculateAge'
				},
				bind : "{coreContacts.dateofbirth}"
			}, {
				xtype : 'numberfield',
				name : 'age',
				itemId : 'age',
				fieldLabel : 'Age',
				minValue : '18',
				readOnly : true, 
				bind : "{coreContacts.age}"
			}, {
				xtype : 'radiogroup',
				name : 'genderId',
				itemId : 'genderId',
				fieldLabel : 'Gender<font color="red">*</font>',
				// bind: "{coreContacts.genderId}",
				/*
				 * bind: { value: "{coreContacts.genderId}" },
				 */
				items : [ {
					boxLabel : 'Male',
					name : 'genderId',
					inputValue : '2B81C578-0A97-4B76-A799-4523B5CC5B66',
					checked : true
				}, {
					boxLabel : 'Female',
					name : 'genderId',
					inputValue : '00CF63C8-E1A3-465B-981D-029A9367D92B'
				}, ]
			}, {
				xtype : 'textfield',
				name : 'emailId',
				fieldLabel : 'Email ID<font color="red">*</font>',
				allowBlank : false,
				regex : /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
				bind : "{coreContacts.emailId}"
			}, {
				xtype : 'textfield',
				name : 'phoneNumber',
				fieldLabel : 'Phone Number<font color="red">*</font>',
				allowBlank : false,
				regex : /^((\+\d{1,3}([-\(]| )?\(?\d\)?([-\(]| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})$/,
				bind : "{coreContacts.phoneNumber}"
			}, {
				xtype : 'combo',
				name : 'timezone',
				itemId : 'timezoneCombo',
				fieldLabel : 'Timezone<font color="red">*</font>',
				allowBlank : false,
				emptyText : 'Select User Timezone',
				queryMode : 'local',
				forceSelection : true,
				displayField : 'timeZoneLabel',
				valueField : 'timeZoneId',
				bind : "{coreContacts.timezone.timeZoneId}",
				store : {
					fields : [ 'timeZoneId', 'timeZoneLabel' ],
					data : [],
					sorters : [ 'timeZoneLabel', 'ASC' ]
				},
				listeners : {
					afterrender : 'afterrenderTimezone'
				},
			} ]
		}, {
			columnWidth : .50,
			defaults : {
				width : '95%',
				margin : '8'
			},
			items : [ {
				xtype : 'combo',
				name : 'nativeTitle',
				fieldLabel : 'Native Title',
				store : {
					fields : [ 'titleId', 'titles' ],
					data : [],
					sorters : [ 'titles', 'ASC' ]
				},
				emptyText : 'Select Native Title',
				queryMode : 'local',
				forceSelection : true,
				displayField : 'titles',
				valueField : 'titleId',
				listeners : {
					afterrender : 'afterTitleComboRender'
				},
				bind : "{coreContacts.nativeTitle}"
			}, {
				xtype : 'textfield',
				name : 'nativeFirstName',
				fieldLabel : 'Native First Name',
				regex : /^[a-zA-Z]+$/,
				invalidText : 'Native First Name should be character only!',
				bind : "{coreContacts.nativeFirstName}"
			}, {
				xtype : 'textfield',
				name : 'nativeMiddleName',
				fieldLabel : 'Native Middle Name',
				regex : /^[a-zA-Z ]+$/,
				invalidText : 'Native Middle Name should be character only!',
				bind : "{coreContacts.nativeMiddleName}"
			}, {
				xtype : 'textfield',
				name : 'nativeLastName',
				fieldLabel : 'Native Last Name',
				regex : /^[a-zA-Z]+$/,
				invalidText : 'Native Last Name should be character only!',
				bind : "{coreContacts.nativeLastName}"
			}, {
				xtype : 'combo',
				name : 'nativeLanguageCode',
				fieldLabel : 'Native Language Code',
				store : {
					fields : [ 'languageId', 'language' ],
					data : [],
					sorters : [ 'language', 'ASC' ]
				},
				emptyText : 'Select Language',
				queryMode : 'local',
				forceSelection : true,
				displayField : 'language',
				valueField : 'languageId',
				listeners : {
					afterrender : 'afterLanguageComboRender'
				},
				bind : "{coreContacts.nativeLanguageCode}"
			}/*, {
				xtype : 'datefield',
				name : 'approximateDOB',
				fieldLabel : 'Approx DOB',
				bind : "{coreContacts.approximateDOB}"
			}*/ ]
		} ]
	}, {
		xtype : 'fieldset',
		title : 'Contact Info',
		collapsible : true,
		collapsed : true,

		items : [ {
			xtype : 'form',
			layout : 'vbox',
			items : [ {
				width : '100%',
				layout : 'column',
				items : [ {
					columnWidth : .50,
					defaults : {
						width : '95%',
						margin : '8'
					},
					items : [ {
						xtype : 'combo',
						name : 'addressTypeId',
						itemId : 'addressTypeId',
						fieldLabel : 'Address Type<font color="red">*</font>',
						store : {
							fields : [ 'addressTypeId', 'addressType' ],
							data : [],
							sorters : [ 'addressType', 'ASC' ]
						},
						emptyText : 'Select Address Type',
						queryMode : 'local',
						forceSelection : true,
						displayField : 'addressType',
						valueField : 'addressTypeId',
						listeners : {
							afterrender : 'aftrAddrTypeComboRender'
						},
					// bind:'{coreContacts.address.addressTypeId}'
					}, {
						xtype : 'textfield',
						fieldLabel : 'Address 1<font color="red">*</font>',
						name : 'address1',
						itemId : 'address1',
					// bind:'{coreContacts.address.address1}'
					}, {
						xtype : 'combo',
						name : 'countryId',
						itemId : 'countryId',
						fieldLabel : 'Country<font color="red">*</font>',
						store : {
							fields : [ 'countryName', 'countryId' ],
							data : [],
							sorters : [ 'countryName', 'ASC' ]
						},
						emptyText : 'Select Country',
						queryMode : 'local',
						forceSelection : false,
						displayField : 'countryName',
						valueField : 'countryId',
						listeners : {
							afterrender : 'aftrCountryComboRender',
							change : 'onCountryChange'
						},
					// bind:'{coreContacts.address.countryId}'
					}, {
						xtype : 'combo',
						fieldLabel : 'State<font color="red">*</font>',
						store : {
							fields : [ 'stateName', 'stateId' ],
							data : [],
							sorters : [ 'stateName', 'ASC' ]
						},
						name : 'stateId',
						itemId : 'stateId',
						emptyText : 'Select State',
						queryMode : 'local',
						forceSelection : true,
						displayField : 'stateName',
						valueField : 'stateId',
						listeners : {
							change : 'onStateChange'
						},
					// bind:'{coreContacts.address.stateId}'
					}, {
						xtype : 'combo',
						fieldLabel : 'City<font color="red">*</font></b>',
						store : {
							fields : [ 'cityName', 'cityId' ],
							data : [],
							sorters : [ 'cityName', 'ASC' ]
						},
						name : 'cityId',
						itemId : 'cityId',
						emptyText : 'Select City',
						queryMode : 'local',
						forceSelection : true,
						displayField : 'cityName',
						valueField : 'cityId',
					// bind:'{coreContacts.address.cityId}'
					}, {
						xtype : 'textfield',
						name : 'zipcode',
						itemId : 'zipcode',
						fieldLabel : 'Postal Code<font color="red">*</font>',
						regex : /^[a-zA-Z0-9]{6}$/,
					// bind:'{coreContacts.address.zipcode}'
					} ]
				}, {
					columnWidth : .50,
					defaults : {
						width : '95%',
						margin : '8'
					},
					items : [ {
						xtype : 'textfield',
						fieldLabel : 'Address Label',
						name : 'addressLabel',
					// bind:'{coreContacts.address.addressLabel}'
					}, {
						xtype : 'textfield',
						name : 'address2',
						fieldLabel : 'Address 2',
					// bind:'{coreContacts.address.address2}'
					}, {
						xtype : 'textfield',
						name : 'address3',
						fieldLabel : 'Address 3',
					// bind:'{coreContacts.address.address3}'
					}, {
						xtype : 'numberfield',
						name : 'latitude',
						fieldLabel : 'Latitude',
						allowDecimals: true, 
						decimalPrecision: 10
					// bind:'{coreContacts.address.latitude}'
					}, {
						xtype : 'numberfield',
						name : 'longitude',
						fieldLabel : 'Longitude',
				        allowDecimals: true,
				        decimalPrecision: 10
					// bind:'{coreContacts.address.longitude}'
					}, {
						width : '14%',
						xtype : 'button',
						text : 'Add Address',
						tooltip : 'Add Address to grid',
						handler : 'addAddressData'
					} ]
				} ]
			}, {
				xtype : 'grid',
				itemId : 'addrGrid',
				title : 'Address Details',
				width : '100%',
				margin : '0 0 6 0',
				border : true,
				bindLevel : "coreContacts.address",
				store : {
					fields : [],
					data : []
				},
				columns : [ {
					text : 'Address Type',
					flex : 0.1,
					dataIndex : 'addressTypeId',
					renderer : 'addrTypeColRender'
				}, {
					text : 'Address 1',
					flex : 0.2,
					dataIndex : 'address1'
				}, {
					text : 'Postal Code',
					flex : 0.1,
					dataIndex : 'zipcode'
				}, {
					text : 'City',
					flex : 0.2,
					dataIndex : 'cityId',
					renderer : 'cityColRender'
				}, {
					text : 'State',
					flex : 0.2,
					dataIndex : 'stateId',
					renderer : 'stateColRender'
				}, {
					text : 'Country',
					flex : 0.1,
					dataIndex : 'countryId',
					renderer : 'countryColRender'
				}, {
					text : 'Address Label',
					flex : 0.1,
					dataIndex : 'addressLabel',
					hidden : true,
				}, {
					text : 'Address 2',
					flex : 0.1,
					dataIndex : 'address2',
					hidden : true,
				}, {
					text : 'Address 3',
					flex : 0.1,
					dataIndex : 'address3',
					hidden : true,
				}, {
					text : 'Latitude',
					flex : 0.1,
					dataIndex : 'latitude',
					hidden : true,
				}, {
					text : 'Longitude',
					flex : 0.1,
					dataIndex : 'longitude',
					hidden : true,
				}, {
					xtype : 'actioncolumn',
					menuDisabled : true,
					text : 'Action',
					align : 'center',
					flex : 0.1,
					items : [ {
						icon : 'images/delete.gif',
						tooltip : 'Delete Row',
						handler : "onDeleteRowClick"
					} ]
				} ]

			} ]
		} ]
	}, {
		xtype : 'fieldset',
		title : 'Communication Info',
		collapsible : true,
		collapsed : true,
		items : [ {
			xtype : 'form',
			layout : 'vbox',
			items : [ {
				width : '100%',
				layout : 'column',
				items : [ {
					columnWidth : .50,
					defaults : {
						width : '95%',
						margin : '8'
					},
					items : [ {
						xtype : 'combo',
						name : 'commGroupId',
						itemId : 'commGroupId',
						fieldLabel : 'Communication Group<font color="red">*</font>',
						store : {
							fields : [ 'commGroupName', 'commGroupId' ],
							data : [],
							sorters : [ 'commGroupName', 'ASC' ]
						},
						emptyText : 'Select Communication Group',
						queryMode : 'local',
						forceSelection : false,
						displayField : 'commGroupName',
						valueField : 'commGroupId',
						listeners : {
							afterrender : 'aftrCommGroupComboRender',
							change : 'onCommGroupChange'
						}
					}, {
						xtype : 'textfield',
						name : 'commData',
						itemId : 'commData',
						fieldLabel : 'Communication Data<font color="red">*</font>',
					} ]
				}, {
					columnWidth : .50,
					defaults : {
						width : '95%',
						margin : '8'
					},
					items : [ {
						xtype : 'combo',
						name : 'commType',
						itemId : 'commType',
						fieldLabel : 'Communication Type<font color="red">*</font>',
						store : {
							fields : [ 'commTypeName', 'commType' ],
							data : [],
							sorters : [ 'commTypeName', 'ASC' ]
						},
						emptyText : 'Select Communication Type',
						queryMode : 'local',
						forceSelection : false,
						displayField : 'commTypeName',
						valueField : 'commType',
					}, {
						width : '10%',
						xtype : 'button',
						text : 'Add Data',
						tooltip : 'Add Communication Data to grid',
						handler : 'addCommData'
					} ]
				} ]
			}, {
				xtype : 'grid',
				itemId : 'commGrid',
				title : 'Communication Details',
				width : '100%',
				margin : '0 0 6 0',
				border : true,
				bindLevel : "coreContacts.communicationData",
				store : {
					fields : [],
					data : []
				},
				columns : [ {
					text : 'Communication Group',
					flex : 0.3,
					dataIndex : 'commGroupId',
					renderer : 'commGroupColRender'
				}, {
					text : 'Communication Type',
					flex : 0.3,
					dataIndex : 'commType',
					renderer : 'commTypeColRender'
				}, {
					text : 'Communication Data',
					flex : 0.3,
					dataIndex : 'commData'
				}, {
					xtype : 'actioncolumn',
					menuDisabled : true,
					text : 'Action',
					align : 'center',
					flex : 0.1,
					items : [ {
						icon : 'images/delete.gif',
						tooltip : 'Delete Row',
						handler : "onDeleteRowClick"
					} ]
				} ]
			} ]

		} ]
	},/*
		 * { xtype : 'fieldset', title : 'Login Credentials', width : '100%',
		 * layout : 'column', items : [ { columnWidth : .50, defaults : { width :
		 * '95%', margin : '8' }, items : [ { xtype : 'textfield', name :
		 * 'loginId', fieldLabel : 'Login Id<font color="red">*</font>',
		 * allowBlank : false, bind : '{loginId}' } ] }, { columnWidth : .50,
		 * defaults : { width : '95%', margin : '8' }, items : [ {
		 * xtype:'combo', emptyText:'Select Questions', fieldLabel:'Security
		 * Question<font color="red">*</font>' }, { xtype:'textfield',
		 * emptyText:'Enter Your Answer', fieldLabel:'Security Answer<font
		 * color="red">*</font>' }] } ] },
		 */{
		xtype : 'fieldset',
		title : 'Other Settings',
		width : '100%',
		layout : 'column',
		items : [ {
			columnWidth : .50,
			defaults : {
				width : '95%',
				margin : '8'
			},
			items : [ {
				xtype : 'numberfield',
				name : 'sessionTimeout',
				fieldLabel : 'Session Time Out<font color="red">*</font>',
				emptyText:'Set Session Time Out In Seconds',
				minValue : 120,
				maxValue : 3600,
				allowBlank : false,
				bind : "{user.sessionTimeout}"
			} ]
		}, {
			columnWidth : .50,
			defaults : {/* width:'95%', */
				margin : '8'
			},
			items : [ {
				xtype : 'checkbox',
				name : 'allowMultipleLogin',
				fieldLabel : 'Allow Multiple Login',
				labelWidth : 120,
				checked : 0,
				bind : "{user.allowMultipleLogin}",
				hidden : true
			} ]
		} ]
	} ],
	buttons : [ {
		text : 'Save',
		icon : 'images/greenFlopy_save.png',
		listeners : {
			click : 'onSaveUserForm'
		}
	}, {
		text : 'Reset',
		icon : 'images/reset1.png',
		listeners : {
			click : 'onResetUserForm'
		}
	} ]
});