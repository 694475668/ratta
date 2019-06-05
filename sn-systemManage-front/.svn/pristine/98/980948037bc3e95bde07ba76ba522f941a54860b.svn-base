<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>固件版本上传</title>
<jsp:include page="../../inc.jsp"></jsp:include>
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
							url : '${pageContext.request.contextPath}/firmwareFixPointController/dataGrid',
							method : 'post',
							//idField : '',
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
										field : 'id',
										title : '<spring:message code="id"/>',
										width : 30,
										hidden : true
									},
									{
										field : 'firmware_version',
										title : '<spring:message code="version"/>',
										width : 250,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'fixPoint',
										title : '<spring:message code="fixPoint"/>',
										width : 600,
										sortable : true,
										halign : 'center',
										align : 'center'
									},
									{
										field : 'lan',
										title : '<spring:message code="lan"/>',
										width : 50,
										halign : 'center',
										align : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="upload_user"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="upload_file_time"/>',
										width : 150,
										halign : 'center',
										align : 'center'
									} ] ],
							toolbar : '#toolbar',
							onHeaderContextMenu : function(e, field) {
								e.preventDefault();
								if (!cmenu) {
									createColumnMenu();
								}
								cmenu.menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},

							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
							},

							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$(this).datagrid('unselectAll').datagrid(
										'uncheckAll');
								$(this).datagrid('selectRow', rowIndex);
							},

							detailFormatter : function(index, row) {
								return '<div style="padding:2px"><table class="ddv"></table></div>';
							},
						});
	});

	$(function() {
		$("#lan")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'LAN',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});
	});
	//添加
	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add"/>',
					width : 800,
					height : 600,
					href : '${pageContext.request.contextPath}/firmwareFixPointController/addFixPoint',
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
					width : 800,
					height : 600,
					href : '${pageContext.request.contextPath}/firmwareFixPointController/updateFixPoint?id='
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

	function searchFun() {
		dataGrid.datagrid("uncheckAll");
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		dataGrid.datagrid("uncheckAll");
		$('#searchForm input').val('');
		var lan = $("#lan").combobox('getData');
		$("#lan").combobox('select', lan[0].id);
		dataGrid.datagrid('load', {});
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
						<td><spring:message code="version" /></td>
						<td><spring:message code="lan" /></td>
					</tr>
					<tr>
						<td><input id="firmware_version" name="firmware_version"
							type="text" style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input id="lan" name="lan" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true" onclick="searchFun();"><spring:message
				code="query" /></a> <a href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"
			onclick="cleanFun();"><spring:message code="clear" /></a>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmwareFixPointController/addFixPoint')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmwareFixPointController/updateFixPoint')}">
			<a onclick="editFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil', plain:true"
				href="javascript:void(0);"><spring:message code="update" /></a>
		</c:if>
	</div>

</body>
</html>