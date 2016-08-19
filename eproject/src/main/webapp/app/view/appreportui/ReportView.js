Ext.define('Eproject.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Eproject.view.appreportui.ReportViewController',
	            'Eproject.view.appreportui.datagrid.DataGridPanel',
	            'Eproject.view.appreportui.datagrid.DataGridView',
	            'Eproject.view.appreportui.querycriteria.QueryCriteriaView',
	            'Eproject.view.appreportui.chart.ChartView',
	            'Eproject.view.appreportui.datapoint.DataPointView',
	            'Eproject.view.googlemaps.map.MapPanel',
	            'Eproject.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
