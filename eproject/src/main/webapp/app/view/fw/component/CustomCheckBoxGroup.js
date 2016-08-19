Ext.define('Eproject.view.fw.component.CustomCheckBoxGroup', {
	extend:'Ext.form.CheckboxGroup',
	xtype : 'customcheckboxgroup',

//	getCmpValue : function(){
//	return this.getValue()[this.name];
//	},

	getCmpValue : function() {
		var selectedValue = this.getValue()[this.name];
		if(selectedValue instanceof Array) {
			return selectedValue;
		} else {
			var valuesArray = [];
			valuesArray.push(selectedValue);
			return valuesArray;
		}
	},

	setCmpValue : function(data) {
		var cbGroupItems = this.items.items;
		for(var index = 0; index < cbGroupItems.length; index++) {
			var checkBox = cbGroupItems[index];

			for(var index1 = 0; index1 < data.length; index1++) {
				var cBoxName = checkBox.name;
				if(checkBox.inputValue[cBoxName] == data[index1][cBoxName]) {
					checkBox.setValue(true);
					break;
				}
			}
		}
	}

});