var TeamList = Ext.extend(Ext.Panel, {

	initComponent : function() {
		
		this.dsTeams = new Ext.data.JsonStore({
			autoDestroy : true,
			proxy : new Ext.data.HttpProxy({method : 'GET', url : 'teams'}),
			root : 'list',
			fields : [
				{name : 'id', type : 'int'},
				{name : 'name', type : 'string'},
				{name : 'badge', type : 'string'}
			]
		});
		
		this.dsTeams.load();
		
		var tpl = new Ext.XTemplate(
			'<tpl for=".">',
				'<div class="item-team">',
					'<img src="{badge}" style="width: 40px; height: 40px; vertical-align: middle;margin-right: 10px;" />', 
					'<span style="vertical-align: middle; font-size:16px;">{name}</span>' ,
				'</div>',
			'</tpl>'
		);	
	
		Ext.apply(this, {
			title : 'Times',
			tbar : [{
				xtype: 'button',
				text: '+ Time',
				scope : this,
				handler: this.onBtnNewClick	
			}],
			items : [{
				xtype : 'dataview',
				store: this.dsTeams,
		        tpl: tpl,
		        itemSelector:'div.item-team',
		        emptyText: 'Nenhum time encontrado',
		        listeners : {
					scope : this,
					click : this.onDtvClick
		        }
			}]
		});
		
		TeamList.superclass.initComponent.apply(this, arguments);
	},
	
	onDtvClick : function(dtv, index, node, e) {
		var rec = this.dsTeams.getAt(index);

		if (this.selectedTeamIdx === index) {
			return;
		}
		
		this.selectedTeamIdx = index;
		this.fireEvent('teamchange', rec);	
	},
	
	onBtnNewClick : function() {
	
		var win = new Ext.Window({
			title : 'Novo time',
			width : 400,
			height : 200,
			layout : 'fit',
			buttons : [{
				text : 'Salvar',
				scope : this,
				handler : function() {
					var form = win.frmNewTeam.getForm(),
						values = form.getFieldValues(),
						record = new this.dsTeams.recordType(values, Ext.id());
					
					if(!form.isValid()) {
						return;
					}
					
					//TODO salvar no banco
					this.dsTeams.add(record);
					win.close();
				}
			},{
				text : 'Cancelar',
				handler : function() {
					win.close();
				}
			}],
			items : {
				xtype : 'form',
				ref : 'frmNewTeam',
				border : false,
				defaultType : 'textfield',
				defaults : {
					anchor : '-50',
					allowBlank : false
				},
				items : [{
					fieldLabel : 'Nome',
					name : 'name'
				},{
					fieldLabel : 'Imagem (url)',
					name : 'badge'
				}]
			}
		});

		win.show();
	},
	
	onBtnSaveTeamClick : function() {}
});

Ext.reg('team-list', TeamList);
