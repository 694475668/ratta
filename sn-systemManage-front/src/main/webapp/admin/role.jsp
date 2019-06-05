<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

</head>
<body>
	<c:if
		test="${fn:contains(sessionInfo.resourceList, '/roleController/editPage')}">
		<script type="text/javascript">
			$.canEdit = true;
		</script>
	</c:if>
	<c:if
		test="${fn:contains(sessionInfo.resourceList, '/roleController/delete')}">
		<script type="text/javascript">
			$.canDelete = true;
		</script>
	</c:if>
	<c:if
		test="${fn:contains(sessionInfo.resourceList, '/roleController/grantPage')}">
		<script type="text/javascript">
			$.canGrant = true;
		</script>
	</c:if>
	<script type="text/javascript">
		var dataGrid;
		$(function() {
			dataGrid = $('#dataGrid')
					.datagrid(
							{
								url : '${pageContext.request.contextPath}/roleController/dataGrid',
								fit : true,
								fitColumns : true,
								border : false,
								pagination : true,
								idField : 'id',
								pageSize : 10,
								pageList : [ 10, 20, 30, 40, 50 ],
								sortName : 'created',
								sortOrder : 'desc',
								checkOnSelect : true,
								selectOnCheck : true,
								rownumbers : true,
								nowrap : false,
								striped : true,
								singleSelect : true,
								frozenColumns : [ [
										{
											title : '<spring:message code="id"/>',
											field : 'id',
											width : 150,
											hidden : true
										},
										{
											field : 'name',
											title : '<spring:message code="role_name"/>',
											width : 200
										} ] ],
								columns : [ [
										{
											field : 'resourceIds',
											title : '<spring:message code="resourceIds"/>',
											width : 400,
											formatter : function(value, row,
													index) {
												if (value) {
													var names = row.resourceNames;
													//						alert(names.length);
													if (names.length > 60) {
														names = names
																.substring(0,
																		60)
																+ '...';
													}
													return names;
												}
												return '';
											},
											align : 'left',
										},
										{
											field : 'create_date',
											title : '<spring:message code="create_date"/>',
											width : 80,
											hidden : true,
											formatter : function(value, row,
													index) {
												return formatter_datettime(value);
											}
										},
										{
											field : 'create_user',
											title : '<spring:message code="create_user"/>',
											width : 80,
											hidden : true
										},
										{
											field : 'modify_date',
											title : '<spring:message code="modify_date"/>',
											width : 120,
											hidden : true,
											formatter : function(value, row,
													index) {
												return formatter_datettime(value);
											}
										},
										{
											field : 'modify_user',
											title : '<spring:message code="modify_user"/>',
											width : 120,
											hidden : true
										},
										{
											field : 'remark',
											title : '<spring:message code="remark"/>',
											width : 80,
											align : 'left',
											halign : 'center'
										},
										{
											field : 'action',
											title : '<spring:message code="operate"/>',
											width : 40,
											align : 'center',
											halign : 'center',
											formatter : function(value, row,
													index) {
												var str = '';
												if ($.canEdit) {
													str += $
															.formatString(
																	'<img onclick="editFun(\'{0}\');" src="{1}" title="<spring:message code="update"/>"/>',
																	row.id,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
												}
												str += '&nbsp;';
												if ($.canGrant) {
													str += $
															.formatString(
																	'<img onclick="grantFun(\'{0}\');" src="{1}" title="<spring:message code="impower"/>"/>',
																	row.id,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/key.png');
												}
												str += '&nbsp;';
												if ($.canDelete) {
													str += $
															.formatString(
																	'<img onclick="deleteFun(\'{0}\');" src="{1}" title="<spring:message code="delete"/>"/>',
																	row.id,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
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
								onRowContextMenu : function(e, rowIndex,
										rowData) {
									e.preventDefault();
									$(this).datagrid('unselectAll').datagrid(
											'uncheckAll');
									$(this).datagrid('selectRow', rowIndex);
									$('#menu').menu('show', {
										left : e.pageX,
										top : e.pageY
									});
								}
							});

		});

		//删除
		function deleteFun(id) {
			if (id != undefined) {
				dataGrid.datagrid('selectRecord', id);
			}
			parent.$.messager
					.confirm(
							'<spring:message code="enquire"/>',
							'<spring:message code="delete_message"/>？',
							function(b) {
								if (b) {
									parent.$.messager
											.progress({
												title : '<spring:message code="hint"/>',
												text : '<spring:message code="please_latter"/>'
											});
									$
											.post(
													'${pageContext.request.contextPath}/roleController/delete',
													{
														id : id
													},
													function(result) {
														if (result.success) {
															parent.$.messager
																	.show({
																		title : '<spring:message code="hint"/>',
																		msg : result.msg
																	});
															dataGrid
																	.datagrid('reload');
														} else {
															$.messager
																	.alert(
																			'<spring:message code="hint"/>',
																			result.msg,
																			'info');
														}
														parent.$.messager
																.progress('close');
													}, 'JSON');
								}
							});
		}

		//修改
		function editFun(id) {
			if (id != undefined) {
				dataGrid.datagrid('selectRecord', id);
			}
			var node = dataGrid.datagrid('getSelected');

			var roleId = '${sessionInfo.roleId}'; //当前角色ID
			roleId = roleId.split(",");
			for (var i = 0; i < roleId.length; i++) {
				if (roleId[i] == node.id) {
					$.messager.alert('<spring:message code="hint"/>',
							'<spring:message code="uprole_message"/>!', 'info');
					return;
				}
			}

			if (node) {
				parent.$
						.modalDialog({
							title : '<spring:message code="update_role"/>',
							width : 500,
							height : 300,
							href : '${pageContext.request.contextPath}/roleController/editPage?id='
									+ node.id,
							buttons : [
									{
										text : '<spring:message code="save"/>',
										iconCls : 'database_save',
										plain : true,
										handler : function() {
											parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
											var f = parent.$.modalDialog.handler
													.find('#form');
											f.submit();
										}
									},
									{
										text : '<spring:message code="cancel"/>',
										iconCls : 'cancel',
										plain : true,
										handler : function() {
											parent.$.modalDialog.handler
													.dialog('close');
										}
									} ]
						});
			}
		}

		//添加
		function addFun() {
			parent.$
					.modalDialog({
						title : '<spring:message code="add_role"/>',
						width : 500,
						height : 300,
						href : '${pageContext.request.contextPath}/roleController/addPage',
						buttons : [
								{
									text : '<spring:message code="save"/>',
									iconCls : 'pencil_add',
									plain : true,
									handler : function() {
										parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
										var f = parent.$.modalDialog.handler
												.find('#form');
										f.submit();
									}
								},
								{
									text : '<spring:message code="cancel"/>',
									iconCls : 'cancel',
									plain : true,
									handler : function() {
										parent.$.modalDialog.handler
												.dialog('close');
									}
								} ]
					});
		}

		//展开、折叠
		var exp = true;
		var exp1 = true;
		function redo() {
			$("#dataGrid").datagrid(exp ? 'collapseAll' : 'expandAll');
			exp = !exp;
			$("#redo").html(
					exp1 ? '<spring:message code="spread"/>'
							: '<spring:message code="fold"/>');
			exp1 = !exp1;
		}

		/* function undo() {
			var node = dataGrid.datagrid('getSelected');
			if (node) {
				dataGrid.datagrid('collapseAll', node.id);
			} else {
				dataGrid.datagrid('collapseAll');
			}
		} */

		//授权
		function grantFun(id) {
			if (id != undefined) {
				dataGrid.datagrid('selectRecord', id);
			}
			var node = dataGrid.datagrid('getSelected');

			if ('${sessionInfo.roleId}'.indexOf(node.id) >= 0) {
				parent.$.messager.alert('<spring:message code="error"/>',
						'<spring:message code="grrole_message"/>', 'error');
				return false;
			}
			if (node) {
				parent.$
						.modalDialog({
							title : '<spring:message code="role_impower"/>',
							width : 500,
							height : 500,
							href : '${pageContext.request.contextPath}/roleController/grantPage?id='
									+ node.id,
							buttons : [
									{
										text : '<spring:message code="impower"/>',
										handler : function() {
											parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
											var f = parent.$.modalDialog.handler
													.find('#form');
											f.submit();
										}
									},
									{
										text : '<spring:message code="cancel"/>',
										handler : function() {
											parent.$.modalDialog.handler
													.dialog('close');
										}
									} ]
						});
			}
		}

		function searchFun() {
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		}

		function cleanFun() {
			$('#searchForm input').val('');
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		}
	</script>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 102px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="role_name" /></td>
					</tr>
					<tr>
						<td><input id="name" name="name" type="text"
							style="height: 15px; width: 200px;"></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="searchFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true"><spring:message
				code="query" /></a> <a onclick="cleanFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"><spring:message
				code="clear" /></a>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/roleController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
	</div>

	<%-- <div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/roleController/addPage')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'"><spring:message code="add"/></div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/roleController/delete')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'"><spring:message code="delete"/></div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/roleController/editPage')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'"><spring:message code="update"/></div>
		</c:if>
	</div> --%>
</body>
</html>