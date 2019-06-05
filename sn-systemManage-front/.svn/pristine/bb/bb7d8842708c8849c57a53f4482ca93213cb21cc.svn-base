<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/grantPage')}">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/editPwdPage')}">
	<script type="text/javascript">
		$.canEditPwd = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/userController/dataGrid',
							fit : true,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : 'createdatetime',
							sortOrder : 'desc',
							checkOnSelect : true,
							selectOnCheck : true,
							nowrap : false,
							frozenColumns : [ [ {
								field : 'id',
								title : '编号',
								width : 150,
								checkbox : true
							},{
								field : 'username',
								title : '用户名',
								width : 100,
								sortable : true
							}, {
								field : 'name',
								title : '姓名',
								width : 100,
								sortable : true
							} ] ],
							columns : [ [
									{
										field : 'pwd',
										title : '密码',
										width : 70,
										formatter : function(value, row, index) {
											return '******';
										},
										hidden : true
									},
									{
										field : 'createdatetime',
										title : '创建时间',
										width : 120,
										sortable : true
									},
									{
										field : 'modifydatetime',
										title : '最后修改时间',
										width : 120,
										sortable : true
									},
									{
										field : 'roleIds',
										title : '所属角色ID',
										width : 150,
										hidden : true
									},
									{
										field : 'roleNames',
										title : '所属角色名称',
										width : 200
									},
									{
										field : 'updatedby',
										title : '更新操作员',
										width : 100
									},
									{
										field : 'updated',
										title : '更新时间',
										width : 120,
										sortable : true
									},
									{
										field : 'action',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if ($.canEdit) {
												str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="修改"/>',
																row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											}
											str += '&nbsp;';
											if ($.canGrant) {
												str += $.formatString('<img onclick="grantFun(\'{0}\',\'{1}\');" src="{2}" title="授权"/>',
																row.id,row.name,'${pageContext.request.contextPath}/style/images/extjs_icons/key.png');
											}
											str += '&nbsp;';
											if ($.canDelete) {
												str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
																row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
											}
											str += '&nbsp;';
											if ($.canEditPwd) {
												str += $.formatString('<img onclick="editPwdFun(\'{0}\');" src="{1}" title="修改密码"/>',
																row.id,'${pageContext.request.contextPath}/style/images/extjs_icons/lock/lock_edit.png');
											}
											return str;
										}
									} ] ],
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
							}
						});
	});

	function editPwdFun(id) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$
				.modalDialog({
					title : '修改用户密码',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/userController/editPwdPage?id='+ id,
					buttons : [ {
						text : '修改',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					},{
						text:'取消',
						handler:function(){
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//删除
	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm(
						'询问',
						'您是否要删除当前用户？',
						function(b) {
							if (b) {
								var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
								if (currentUserId != id) {
									parent.$.messager.progress({
										title : '提示',
										text : '数据处理中，请稍后....'
									});
									$.post('${pageContext.request.contextPath}/userController/delete',
													{
														id : id
													},
													function(result) {
														if (result.success) {
															parent.$.messager.show({
																title : '提示',
																timeout : 3000,
																msg : '删除用户成功'
															});
															dataGrid.datagrid('reload');
														}
														parent.$.messager.progress('close');
													}, 'JSON');
								} else {
									parent.$.messager.show({
										title : '提示',
										msg : '不可以删除自己！'
									});
								}
							}
						});
	}

	//批量删除
	function batchDeleteFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认',	'您是否要删除当前选中的用户？',
							function(r) {
								if (r) {
									parent.$.messager.progress({
										title : '提示',
										text : '数据处理中，请稍后....'
									});
									var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
									var flag = false;
									for (var i = 0; i < rows.length; i++) {
										if (currentUserId != rows[i].id) {
											ids.push(rows[i].id);
										} else {
											flag = true;
										}
									}
									$.getJSON('${pageContext.request.contextPath}/userController/batchDelete',
													{
														ids : ids.join(',')
													},
													function(result) {
														if (result.success) {
															dataGrid.datagrid('load');
															dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
														}
														if (flag) {
															parent.$.messager.show({title : '提示',msg : '不可以删除自己！'});
														} else {
															parent.$.messager.show({
																title:'提示',
																timeout:3000,
																msg : '批量删除用户成功'
															});
														}
														parent.$.messager.progress('close');
													});
								}
							});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}

	//修改
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
					title : '修改用户',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/userController/editPage?id='	+ id,
					buttons : [ {
						text : '修改',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					},{
						text : '取消',
						handler: function(){
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//添加
	function addFun() {
		parent.$.modalDialog({
			title : '添加用户',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/userController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			},{
				text:'取消',
				handler:function(){
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
	}

	function batchGrantFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		var names=[];
		if (rows.length > 0) {
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
				names.push(rows[i].name);
			}
			parent.$.modalDialog({
						title : '用户授权',
						width : 500,
						height : 300,
						href : '${pageContext.request.contextPath}/userController/grantPage?ids='
								+ ids.join(',')+'&names='+names.join(','),
						buttons : [ {
							text : '授权',
							handler : function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
								var f = parent.$.modalDialog.handler.find('#form');
								f.submit();
							}
						},{
							text:'取消',
							handler:function(){
								parent.$.modalDialog.handler.dialog('close');
							}
						} ]
					});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要授权的记录！'
			});
		}
	}

	//授权
	function grantFun(id,name) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.modalDialog({
					title : '用户授权',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/userController/grantPage?ids='
							+ id+'&names='+name,
					buttons : [ {
						text : '授权',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					},{
						text:'取消',
						handler:function(){
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false"
			style="height: 140px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="display: none;">
					<tr>
						<th>登录名</th>
						<td><input name="name" placeholder="请输入登录名" class="span2" /></td>
					</tr>
					<tr>
						<th>创建时间</th>
						
						<td> <input class="span2" name="createdatetimeStart"
							placeholder="点击选择时间"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly="readonly" />至<input class="span2"
							name="createdatetimeEnd" placeholder="点击选择时间"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly="readonly" />
							</td>
					</tr>
					<tr>
						<th>最后修改时间</th>
						<td><input class="span2" name="modifydatetimeStart"
							placeholder="点击选择时间"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly="readonly" />至<input class="span2"
							name="modifydatetimeEnd" placeholder="点击选择时间"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly="readonly" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/grantPage')}">
			<a onclick="batchGrantFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'tux'">批量授权</a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/batchDelete')}">
			<a onclick="batchDeleteFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a><a
			href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"
			onclick="cleanFun();">清空</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/addPage')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/delete')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/editPage')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">修改</div>
		</c:if>
	</div>
</body>
</html>