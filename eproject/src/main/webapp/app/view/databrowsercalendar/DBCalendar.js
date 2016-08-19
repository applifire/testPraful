Ext.define('Eproject.view.databrowsercalendar.DBCalendar', {
	extend : 'Eproject.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Eproject.view.databrowsercalendar.DBCalendarController',
	             'Eproject.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
