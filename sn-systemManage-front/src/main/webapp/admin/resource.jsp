<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>资源管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/resourceController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/resourceController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid')
				.treegrid(
						{
							url : '${pageContext.request.contextPath}/resourceController/treeGrid',
							idField : 'id',
							treeField : 'name',
							parentField : 'pid',
							fit : true,
							fitColumns : false,
							border : false,
							frozenColumns : [ [ {
								title : '<spring:message code="id"/>',
								field : 'id',
								width : 150,
								hidden : true
							} ] ],
							columns : [ [
									{
										field : 'name',
										title : '<spring:message code="resource_name"/>',
										width : 240
									},
									{
										field : 'url',
										title : '<spring:message code="resource_url"/>',
										width : 240
									},
									{
										field : 'typeId',
										title : '<spring:message code="typeId"/>',
										width : 150,
										hidden : true
									},
									{
										field : 'typeName',
										title : '<spring:message code="typeName"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'seq',
										title : '<spring:message code="seq"/>',
										width : 60,
										align : 'center',
										hidden : true
									},
									{
										field : 'pid',
										title : '<spring:message code="pid"/>',
										width : 150,
										hidden : true
									},
									{
										field : 'pname',
										title : '<spring:message code="pname"/>',
										width : 160
									},
									{
										field : 'create_time',
										title : '<spring:message code="create_date"/>',
										width : 150,
										formatter : function(value, row, index) {
											return formatter_datettime(value);
										},
										hidden : true
									},
									{
										field : 'create_user',
										title : '<spring:message code="create_user"/>',
										width : 50,
										hidden : true
									},
									{
										field : 'update_time',
										title : '<spring:message code="modify_date"/>',
										width : 150,
										formatter : function(value, row, index) {
											return formatter_datettime(value);
										},
										hidden : true
									},
									{
										field : 'update_user',
										title : '<spring:message code="modify_user"/>',
										width : 50,
										hidden : true
									},
									{
										field : 'action',
										title : '<spring:message code="operate"/>',
										align : 'center',
										width : 80,
										formatter : function(value, row, index) {
											var str = '';
											if ($.canEdit) {
												str += $
														.formatString(
																'<img onclick="editFun(\'{0}\');" src="{1}" title="修改"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											}
											str += '&nbsp;';
											if ($.canDelete) {
												str += $
														.formatString(
																'<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
											}
											return str;
										}
									},
									{
										field : 'remark',
										title : '<spring:message code="remark"/>',
										width : 260
									} ] ],
							toolbar : '#toolbar',
							onContextMenu : function(e, row) {
								e.preventDefault();
								$(this).treegrid('unselectAll');
								$(this).treegrid('select', row.id);
								$('#menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$(this).treegrid('tooltip');
							}
						});
	});

	function deleteFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
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
													'${pageContext.request.contextPath}/resourceController/delete',
													{
														id : node.id
													},
													function(result) {
														if (result.success) {
															parent.$.messager
																	.show({
																		title : '<spring:message code="hint"/>',
																		msg : result.msg
																	});
															treeGrid
																	.treegrid('reload');
															parent.layout_west_tree
																	.tree('reload');
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
	}

	function editFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$
					.modalDialog({
						title : '<spring:message code="update_resource"/>',
						width : 600,
						height : 400,
						href : '${pageContext.request.contextPath}/resourceController/editPage?id='
								+ node.id,
						buttons : [
								{
									text : '<spring:message code="save"/>',
									iconCls : 'database_save',
									plain : true,
									handler : function() {
										parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
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

	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add_resource"/>',
					width : 600,
					height : 400,
					href : '${pageContext.request.contextPath}/resourceController/addPage',
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
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

	//展开、折叠
	var exp = true;
	var exp1 = true;
	function redo() {
		$('#treeGrid').treegrid(exp ? 'collapseAll' : 'expandAll');
		exp = !exp;
		$('#redo').html(
				exp1 ? '<spring:message code="spread"/>'
						: '<spring:message code="stop"/>');
		exp1 = !exp1;
	}

	/* function undo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('collapseAll', node.id);
		} else {
			treeGrid.treegrid('collapseAll');
		}
	} */
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title=""
			style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/resourceController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<a onclick="redo();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'resultset_next'"><span
			id="redo"><spring:message code="fold" /></span></a> <a
			onclick="treeGrid.treegrid('reload');" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'transmit'"><spring:message
				code="refresh" /></a>
	</div>

	<%-- <div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/resourceController/addPage')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/resourceController/delete')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/resourceController/editPage')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">修改</div>
		</c:if>
	</div> --%>
</body>
</html>