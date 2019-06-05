<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>登录记录管理</title>
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
							url : '${pageContext.request.contextPath}/longinRecordController/dataGrid',
							method : 'post',
							//idField : '',
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
							columns : [ [
									{
										field : 'id',
										title : '<spring:message code="id"/>',
										width : 30,
										hidden : true
									},
									{
										field : 'username',
										title : '<spring:message code="username"/>',
										width : 220,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'login_method',
										title : '<spring:message code="login_method"/>',
										width : 120,
										sortable : true,
										halign : 'center',
										align : 'center'

									},
									{
										field : 'ip',
										title : 'ip',
										width : 200,
										halign : 'center',
										align : 'center'
									},
									{
										field : 'equipment',
										title : '<spring:message code="equipment"/>',
										width : 150,
										halign : 'center',
										align : 'center'
									},

									{
										field : 'browser',
										title : '<spring:message code="browser"/>',
										width : 250,
										halign : 'center',
										align : 'center'
									},

									{
										field : 'modifydatetimeStart',
										title : '<spring:message code="login_time"/>',
										width : 180,
										halign : 'center',
										align : 'center'
									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
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

	//登录方式根据数据字典表获取
	$(function() {
		$("#login_method")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'LOGIN_METHOD',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});
	});
	//设备根据数据字典表获取
	$(function() {
		$("#equipment")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIPMENT',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
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
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 102px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="username" /></td>
						<td><spring:message code="login_method" /></td>
						<td><spring:message code="equipment" /></td>
						<td><spring:message code="begin_date" /></td>
						<td><spring:message code="end_date" /></td>

					</tr>
					<tr>
						<td><input id="username" name="username" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input id="login_method" name="login_method"
							style="width: 214px;"></td>
						<td><input id="equipment" name="equipment"
							style="width: 214px;"></td>
						<td><input id="modifydatetimeStart"
							name="modifydatetimeStart" type="text" class="easyui-datebox"
							style="width: 200px; height: 25px;" data-options="editable:false">
						</td>
						<td><input id="modifydatetimeEnd" name="modifydatetimeEnd"
							type="text" class="easyui-datebox"
							style="width: 200px; height: 25px;" data-options="editable:false">
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
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true" onclick="searchFun();"><spring:message
				code="query" /></a> <a href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"
			onclick="cleanFun();"><spring:message code="clear" /></a>
	</div>

</body>
</html>