Ext.onReady(function(){
	// Viadagem do jSinit
	'use strict';

	var viewport = new Ext.Viewport({
		layout: 'border',
		items : [{
			xtype: 'panel',
			title: 'Região Norte',
			region: 'north',
			height: 80
		}, {
			xtype: 'team-list',
			title: 'Região Oeste',
			region: 'west',
			collapsible: true,
			width: 250,
			listeners : {
				teamchange : function(record) {
					var ct = viewport.ctMain;
					
					ct.removeAll(true);
					ct.add({
						xtype : 'panel',
						title : record.get('name')
					});
					ct.doLayout();
				}
			}
		}, {
			xtype: 'container',
			region: 'center',
			layout : 'fit',
			ref: 'ctMain'
		}]
	});
	
	
	
	
	
}); //end onReady