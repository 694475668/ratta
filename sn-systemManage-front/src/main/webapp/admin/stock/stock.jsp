<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>设备库存管理</title>
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
							url : '${pageContext.request.contextPath}/stockController/dataGrid',
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
										width : 30,
										hidden : true
									},
									{
										field : 'equipment_model',
										title : '<spring:message code="equipment_model"/>',
										width : 130,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'batch_no',
										title : '<spring:message code="batch_no"/>',
										width : 160,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'equipment_no',
										title : '<spring:message code="equipment_no"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'flag',
										title : '<spring:message code="in_out_flag"/>',
										width : 90,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.flag == 1) {
												return '<spring:message code="in"/>';
											}
											if (rec.flag == 2) {
												return '<spring:message code="out"/>';
											}
										}
									},
									{
										field : 'state',
										title : '<spring:message code="is_activate"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.state == 'Y') {
												return '<spring:message code="activate"/>';
											}
											if (rec.state == 'N') {
												return '<spring:message code="no_activate"/>';
											}
										}
									},
									{
										field : 'status',
										title : '<spring:message code="device_status"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.status == 'Y') {
												return '<spring:message code="normal"/>';
											}
											if (rec.status == 'N') {
												return '<spring:message code="lock"/>';
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
										field : 'firmware_version',
										title : '<spring:message code="firmware_version"/>',
										width : 250,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'releaseFirmwareVersion',
										title : '<spring:message code="releaseFirmwareVersion"/>',
										width : 250,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'update_status',
										title : '<spring:message code="update_status"/>',
										width : 90,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="in_time"/>',
										width : 130,
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

		$("#equipment_model")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/equipTypeController/queryNoPage',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'id',
							textField : 'type'
						});

		//设备固件更新状态根据数据字典表获取
		$("#update_status")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIP_FIRM_UPDATE_STATUS',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});

	});
	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	//退货
	function returnStockFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_return_record"/>！', 'info');
			return;
		}
		if (row.flag == 1) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_is_out"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="return_stock"/>',
					width : 500,
					height : 400,
					//method: 'get',
					href : '${pageContext.request.contextPath}/stockController/returnStock?equipment_no='
							+ row.equipment_no,
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
	//换货
	function exchangeStockFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_exchange_record"/>！', 'info');
			return;
		}
		if (row.flag == 1) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_is_out"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="exchange_stock"/>',
					width : 500,
					height : 450,
					//method: 'get',
					href : '${pageContext.request.contextPath}/stockController/exchangeStock?equipment_no='
							+ row.equipment_no,
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
	//修改健康状态
	function healthyStateFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_healthyState_record"/>！',
					'info');
			return;
		}
		if (row.flag == 2) {
			$.messager
					.alert(
							'<spring:message code="hint"/>',
							'<spring:message code="equipment_out_not_uodate"/>',
							'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="healthy_state"/>',
					width : 500,
					height : 250,
					//method: 'get',
					href : '${pageContext.request.contextPath}/stockController/toHealthyState?equipment_no='
							+ row.equipment_no,
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
	//导出
	function responseExcel() {
		var equipment_model = $('#equipment_model').combobox('getValue');
		var update_status = $('#update_status').combobox('getValue');
		var equipment_no = $('#equipment_no').val();
		var batch_no = $('#batch_no').val();
		var firmware_version = $('#firmware_version').val();
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var params = "firmware_version=" + firmware_version + "&update_status="
				+ update_status + "&equipment_model=" + equipment_model
				+ "&equipment_no=" + equipment_no + "&batch_no=" + batch_no
				+ "&beginTime=" + beginTime + "&endTime=" + endTime;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$
				.post(
						'${pageContext.request.contextPath}/stockController/export',
						params,
						function(result) {
							if (result.success) {
								var j;
								try {//Json解析出错时，异常处理。
									j = $.parseJSON(result.obj);
								} catch (e) {
									parent.$.messager
											.alert(
													'<spring:message code="error"/>',
													'<spring:message code="req_error"/>',
													'error');
									return false;
								}
								var download = "${pageContext.request.contextPath}/excel/export/"
										+ j.name;
								window.top.location.href = download;

							} else {
								$.messager.show({
									title : '<spring:message code="hint"/>',
									msg : result.msg,
									timeout : 5000,
									showType : 'slide'
								});
							}
							parent.$.messager.progress('close');
						}, 'json');
	}

	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	//参数类别根据数据字典表获取
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 120px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="equipment_model" /></td>
						<td><spring:message code="equipment_no" /></td>
						<td><spring:message code="batch_no" /></td>
						<td><spring:message code="firmware_version" /></td>
						<td><spring:message code="deployVersion" /></td>
						<td><spring:message code="update_status" /></td>
						<td><spring:message code="healthy_state" /></td>
						<td><spring:message code="inStockbeginTime" /></td>
						<td><spring:message code="inStockEndTime" /></td>
					</tr>
					<tr>
						<td><input id="equipment_model" name="equipment_model"
							type="text" style="height: 15px; width: 135px; margin-top: 10px;"></td>
						<td><input id="equipment_no" name="equipment_no" type="text"
							style="height: 15px; width: 140px; margin-top: 10px;"></td>
						<td><input id="batch_no" name="batch_no" type="text"
							style="height: 15px; width: 140px; margin-top: 10px;"></td>
						<td><input id="firmware_version" name="firmware_version"
							type="text" style="height: 15px; width: 140px; margin-top: 10px;"></td>
						<td><input id="firmware_ver" name="deployVersion" type="text"
							style="height: 15px; width: 140px; margin-top: 10px;"></td>
						<td><input id="update_status" name="update_status"
							type="text" style="height: 15px; width: 80px; margin-top: 10px;"></td>
						<td><input id="healthyState" name="healthyState" type="text"
							style="width: 80px; margin-top: 10px;"></td>
						<td><input id="beginTime" name="beginTime" type="text"
							class="easyui-datebox" style="width: 90px; height: 25px;"
							data-options="editable:false"></td>
						<td><input id="endTime" name="endTime" type="text"
							class="easyui-datebox" style="width: 90px; height: 25px;"
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
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/stockController/returnStock')}">
			<a onclick="returnStockFun()" class="easyui-linkbutton"
				data-options="iconCls:'package_go', plain:true"
				href="javascript:void(0);"><spring:message code="return_stock" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/stockController/exchangeStock')}">
			<a onclick="exchangeStockFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'shape_group'"><spring:message
					code="exchange_stock" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/stockController/toHealthyState')}">
			<a onclick="healthyStateFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'thumb_up'"><spring:message
					code="healthy_state" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/stockController/export')}">
			<a onclick="responseExcel();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_go'"><spring:message
					code="export" /></a>
		</c:if>
	</div>
</body>
</html>
