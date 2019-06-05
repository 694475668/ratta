<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>app修复点管理</title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/appFixPointController/dataGrid',
							method : 'post',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : '',
							sortOrder : 'desc',
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
							nowrap : true,
							striped : true,
							rownumbers : true,
							singleSelect : true,
							pagination : true,
							columns : [ [
									{
										title : '<spring:message code="id"/>',
										field : 'id',
										width : 150,
										hidden : true
									},
									{
										field : 'appName',
										title : '<spring:message code="app_name"/>',
										width : 200,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'appVersion',
										title : '<spring:message code="version"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'lan',
										title : '<spring:message code="lan"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'fixPoint',
										title : '<spring:message code="fixPoint"/>',
										width : 320,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'opUser',
										title : '<spring:message code="op_user"/>',
										width : 250,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'opTime',
										title : '<spring:message code="op_time"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									}] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');

								var rows = $("#dataGrid").datagrid('getRows');
								if (rows.length == 0) {
									$.messager
											.show({
												title : 'Tip',
												msg : '<spring:message code="no_data"/>',
												timeout : 3000,
												showType : 'slide'
											});
								}
							}

						});
	});

	//添加
	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add"/>',
					width : 900,
					height : 600,
					href : '${pageContext.request.contextPath}/appFixPointController/addAppFixPoint',
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
	function editFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="update_message"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="update"/>',
					width : 900,
					height : 600,
					href : '${pageContext.request.contextPath}/appFixPointController/updateAppFixPoint?id='
							+ row.id,
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'database_save',
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
	
	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}


	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}

</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 102px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="app_name" /></td>
						<td><spring:message code="version" /></td>
					</tr>
					<tr>
						<td><input id="appName" name="appName" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="appVersion" name="appVersion" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
					</tr>
				</table>
			</form>
		</div>

		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="searchFun();" href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true"><spring:message code="query" /></a> 
		<a onclick="cleanFun();" href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"><spring:message code="clear" /></a>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appFixPointController/addAppFixPoint')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appFixPointController/updateAppFixPoint')}">
			<a onclick="editFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil', plain:true"
				href="javascript:void(0);"><spring:message code="update" /></a>
		</c:if>
	</div>
</body>
</html>
