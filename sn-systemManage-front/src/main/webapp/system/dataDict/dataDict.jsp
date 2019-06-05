<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>


<title>用户管理</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/dataDictController/addTable')}">
	<script type="text/javascript">
		$.canAddTable = true;
	</script>
</c:if>

<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/dataDictController/listTable',
							method : 'post',
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : '',
							sortOrder : 'desc',
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
							nowrap : false,
							striped : true,
							rownumbers : true,
							singleSelect : true,
							pagination : true,
							view: detailview,
							detailFormatter:function(index,row){    
						        return '<div id="ddv-' + index + '" ></div>';    
						    },
							columns : [ [ {
								field : 'id',
								title : '<spring:message code="id"/>',
								width : 150,
								hidden:true
							}, {
								field : 'name',
								title : '表名',
								width : 200,
								sortable : true,
								align : 'left',
								halign : 'center'
							},
									{
										field : 'type',
										title : '类型',
										width : 100,
										formatter:function(val,row,index){
											if(val=='1'){
												return "表";
											}
											else if(val=='2'){
												return "视图";
											}
											else if(val=='3'){
												return "函数";
											}
											else if(val=='4'){
												return "触发器";
											}
										}
										
									},
									{
										field : 'description',
										title : '描述',
										width:400
									},
									
							
								
									{
										field : 'updatedby',
										title : '<spring:message code="modify_user"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										hidden : false
									},
									{
										field : 'updated',
										title : '<spring:message code="modify_date"/>',
										width : 200,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'action',
										title : '操作',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center',
										formatter:function(val,row,index){
											var str="";
											//修改表名
											str += $.formatString('<img onclick="updateFun(\'{0}\');" src="{1}" title="修改"/>',row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/plugin_edit.png'); 
											//删除表名
											str += $.formatString('<img onclick="deleteTable(\'{0}\');" src="{1}" title="删除"/>',row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
											//添加字段
											str += $.formatString('<img onclick="addColumn(\'{0}\');" src="{1}" title="添加字段"/>',row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/pencil_add.png');
											return str;
										}
									}
									
								
									 ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$(this).datagrid('unselectAll').datagrid('uncheckAll');
								$(this).datagrid('selectRow', rowIndex);
								$('#menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},
			                //详情
			                onExpandRow: function(index,row){
			                
			                	//点击+号，选中父类
			                    $('#dataGrid').datagrid('selectRow',index);
			                    var row = $('#dataGrid').datagrid('getSelected');
								
			                	//ddv = $(this).datagrid('getRowDetail',index).find('table.div.div');  //3.
			                	ddv =$('#ddv-'+index);
			                	
			                    ddv.datagrid({  //4.ddv[index]换成ddv
			                        url:'${pageContext.request.contextPath}/dataDictController/listColumn?table_id='+row.id,
			                        fitColumns:true,
			                        singleSelect:true,
			                        rownumbers:true,
									pagination : true,
									idField : 'id',
									pageSize : 10,
									pageList : [ 10, 20, 30, 40, 50 ],
									checkOnSelect : false,
									selectOnCheck : false,
									striped : true,
									nowrap : false,
			                        columns:[[
			                            {field:'id',title:'主键',width:200,hidden: true},
			                            {field:'name',title:'字段名',width:100,align:'center'},
			                            {field:'type',title:'字段类型',width:100,align:'center',
				                            formatter:function(val,row,index){
				                            	return val=='1'?'varchar':(val=='2'?'int':(val=='3'?'blob':'timestamp'));
				                            }
			                            },
			                            {field:'length',title:'字段长度',width:100,align:'center'},
			                            {field:'is_primary',title:'主键',width:100,align:'center',
				                            formatter:function(val,row,index){
				                            	return val==1?'是':'否';
				                            }
			                            },

			                            {field:'is_auto_increment',title:'自增',width:100,align:'center',
			                            	formatter:function(val,row,index){
				                            	return val==1?'是':'否';
				                            }
			                            },
			                          	{field:'is_null',title:'可空',width:100,align:'center',
				                            formatter:function(val,row,index){
				                            	return val==1?'是':'否';
				                            }
			                            },
			                            {field:'default_val',title:'默认值',width:60,align:'center'},
			                            {field:'description',title:'描述',width:150,align:'center'},
			                            {field:'updatedby',title:'修改人',width:100,align:'center'},
			                            {field:'updated',title:'修改时间',width:140,align:'center'},
			                            {field:'action',title:'<spring:message code="operate"/>',width:80,align:'center',
			            					formatter : function(value, row, index) {
			            						var str = ''; 
			            						
			            						str += $.formatString('<img onclick="updateColumn(\'{0}\');" src="{1}" title="<spring:message code="edit_detail"/>"/>',row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
			            						
			            						str += '&nbsp;';
			            						
			            						str += $.formatString('<img onclick="deleteColumn(\'{0}\');" src="{1}" title="<spring:message code="delete_detail"/>"/>',row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
			            						
			            						
			            						return str;
			            					}
			            				}
			            				]],
			                        onResize:function(){
			                            $('#dataGrid').datagrid('fixDetailRowHeight',index);
			                        },
			                        onLoadSuccess:function(){
			                        	
			                            setTimeout(function(){
			                                $('#dataGrid').datagrid('fixDetailRowHeight',index);
			                            },0);
			                        }
			                        
			                    });
			                    $('#dataGrid').datagrid('fixDetailRowHeight',index);
							}
						});
	});



	
	function searchFun() {
		dataGrid.datagrid("uncheckAll");
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		dataGrid.datagrid("uncheckAll");
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 68px; border: solid 0px red; overflow: hidden;">
			<form id="searchForm">
				<table 
				    style="border: solid 0px red; margin: 10px 20px 0px 20px; margin-bottom: 0px; width:">
					<tr>
						<td>
							<div
								style="float: left; border: solid 0px blue; height: 23px; margin-top: 2px;">
								<div style="float: left; border: solid 0px red; width: 60px;">表名:</div>
								<div style="float: left;">
									<input id="name" name="name" type="text"
										style="height: 12px; width: 150px">
								</div>

								<div style="float:left;border:solid 0px red;">类型:</div>
								<div style="float:left;">
									 <select id="type" name="type" class="easyui-combobox" style="width:60px;" data-options="editable:false">
										<option value="">全部</option>
										<option value='1'>表</option>
										<option value='2'>视图</option>
										<option value='3'>函数</option>
										<option value='4'>触发器</option>
									</select> 
								</div>
								<div style="float: left; border: solid 0px red; width: 60px;">描述:</div>
								<div style="float: left;">
									<input id="description" name="description" type="text" style="height: 12px; width: 150px">
								</div>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
	    
	    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();"><spring:message code="query"/></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();"><spring:message code="clear"/></a>
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'"><spring:message code="add"/></a>
		
		
	</div>
</body>
<script type="text/javascript">
//添加
	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add_parameter"/>',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/dataDictController/addTablePage',
					type:'get',
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//修改
	function updateFun(id) {
		
		parent.$
				.modalDialog({
					title : '<spring:message code="add_parameter"/>',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/dataDictController/updateTablePage?id='+id,
					type:'get',
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//删除
	function deleteTable(id) {
		if (id != undefined) {
			dataGrid.datagrid('selectRecord', id);
		}	
		parent.$.messager.confirm('<spring:message code="enquire"/>', '<spring:message code="delete_message"/>？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '<spring:message code="hint"/>',
					text : '<spring:message code="please_latter"/>'
				});
				$.post('${pageContext.request.contextPath}/dataDictController/deleteTable', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.show({
							title:'<spring:message code="hint"/>',
							msg : result.msg								
						});
						dataGrid.datagrid('reload');
					}else{
						$.messager.alert('<spring:message code="hint"/>', result.msg, 'info');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}
	
	//添加
	function addColumn(id) {
		parent.$
				.modalDialog({
					title : '添加字段',
					width : 800,
					height : 500,
					href : '${pageContext.request.contextPath}/dataDictController/addColumnPage?table_id='+id,
					type:'get',
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//修改
	function updateColumn(id) {
		
		parent.$
				.modalDialog({
					title : '修改字段',
					width : 800,
					height : 500,
					href : '${pageContext.request.contextPath}/dataDictController/updateColumnPage?id='+id,
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//删除字段
	function deleteColumn(id) {
		parent.$.messager.confirm('<spring:message code="enquire"/>', '<spring:message code="delete_message"/>？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '<spring:message code="hint"/>',
					text : '<spring:message code="please_latter"/>'
				});
				$.post('${pageContext.request.contextPath}/dataDictController/deleteColumn', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.show({
							title:'<spring:message code="hint"/>',
							msg : result.msg								
						});
						dataGrid.datagrid('reload');
					}else{
						$.messager.alert('<spring:message code="hint"/>', result.msg, 'info');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}
</script>
</html>