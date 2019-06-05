<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>退换货管理</title>
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
							url : '${pageContext.request.contextPath}/equipmentReturnController/dataGrid',
							method : 'post',
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
										title : '<spring:message code="id"/>',
										field : 'id',
										width : 150,
										hidden : true
									},
									{
										field : 'equipment_no',
										title : '<spring:message code="equipment_no"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'serrialNumber',
										title : '<spring:message code="serial_number"/>',
										width : 140,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'returnTime',
										title : '<spring:message code="return_time"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'businessHandler',
										title : '<spring:message code="business_handler"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'exchangeEquipmentNo',
										title : '<spring:message code="exchange_EquipmentNo"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'type',
										title : '<spring:message code="type"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.type == 1) {
												return '<spring:message code="return"/>';
											}
											if (rec.type == 2) {
												return '<spring:message code="exchange"/>';
											}
										}
									},
									{
										field : 'healthyState',
										title : '<spring:message code="healthy_state"/>',
										width : 60,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'remark',
										title : '<spring:message code="remark"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="op_user"/>',
										width : 60,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="op_time"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									} ] ],
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

		$("#healthyState")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIPMENT_HEALTHY_STATE',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 25,
							valueField : 'value',
							textField : 'value_cn'
						});
	});

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
						<td><spring:message code="equipment_no" /></td>
						<td><spring:message code="healthy_state" /></td>
						<td><spring:message code="type" /></td>
						<td><spring:message code="beginTime" /></td>
						<td><spring:message code="endTime" /></td>
					</tr>
					<tr>
						<td><input name="equipment_no" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input id="healthyState" name="healthyState" type="text"
							style="width: 120px; margin-top: 10px;"></td>
						<td><select name="type" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto',
				                height:25
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="1"><spring:message code="return" /></option>
								<option value="2"><spring:message code="exchange" /></option>
						</select></td>
						<td><input id="beginTime" name="beginTime" type="text"
							class="easyui-datebox" style="width: 120px; height: 25px;"
							data-options="editable:false"></td>
						<td><input id="endTime" name="endTime" type="text"
							class="easyui-datebox" style="width: 120px; height: 25px;"
							data-options="editable:false"></td>
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
	</div>
</body>
</html>
