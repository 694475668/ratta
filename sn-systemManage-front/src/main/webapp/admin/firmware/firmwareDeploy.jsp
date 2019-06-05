<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>固件版本发布</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jslib/jquery-print/css/ratta-print.css"
	type="text/css">
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
							url : '${pageContext.request.contextPath}/firmDeployTaskController/dataGrid',
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
										width : 10,
										hidden : true
									},
									{
										field : 'equipment_type',
										title : '<spring:message code="equipment_model"/>',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'area',
										title : '<spring:message code="area"/>',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'custom',
										title : '<spring:message code="custom"/>',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'configuration',
										title : '<spring:message code="configuration"/>',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'batch_no',
										title : '<spring:message code="batch_no"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'equipmentno',
										title : '<spring:message code="equipment_no"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'deployVersion',
										title : '<spring:message code="deployVersion"/>',
										width : 250,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'performTime',
										title : '<spring:message code="performTime"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'finishFlag',
										title : '<spring:message code="finishFlag"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, row) {
											if (row.finishFlag == '1') {
												return '<spring:message code="finish"/>';
											}
											if (row.finishFlag == '0') {
												return '<spring:message code="no_finish"/>';
											}
										}
									},
									{
										field : 'type',
										title : '<spring:message code="task_type"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, row) {
											if (row.type == '2') {
												return '<spring:message code="release"/>';
											}
											if (row.type == '3') {
												return '<spring:message code="flash"/>';
											}
										}
									},
									{
										field : 'status',
										title : '<spring:message code="status"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, row) {
											if (row.status == '0') {
												return '<spring:message code="normal"/>';
											}
											if (row.status == '1') {
												return '<spring:message code="undo"/>';
											}
										}
									},
									{
										field : 'equipment_purpose',
										title : '<spring:message code="equipment_purpose"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'deployUser',
										title : '<spring:message code="deployUser"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'deployTime',
										title : '<spring:message code="deployTime"/>',
										width : 130,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'revokeUser',
										title : '<spring:message code="revokeUser"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'revokeTime',
										title : '<spring:message code="revokeTime"/>',
										width : 130,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'isHistory',
										title : '<spring:message code="isHistory"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, row) {
											if (row.isHistory == '0') {
												return '<spring:message code="no"/>';
											}
											if (row.isHistory == '1') {
												return '<spring:message code="yes"/>';
											}
										}
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
		$("#cron")
				.click(
						function() {
							console
							parent.$
									.modalDialog({
										title : '<spring:message code="schedele_time"/>',
										width : 850,
										height : 340,
										href : '${pageContext.request.contextPath}/firmDeployTaskController/quartz',
										buttons : [
												{
													text : '<spring:message code="save"/>',
													plain : true,
													handler : function() {
														// 调用 quartz.jsp 中的 createCronExpression() 方法,得到返回的 cron 表达式
														var b = window.parent
																.createCronExpression();
														$("#cron").val(b);
														parent.$.modalDialog.handler
																.dialog('close');
													}
												},
												{
													text : '<spring:message code="cancel"/>',
													plain : true,
													handler : function() {
														parent.$.modalDialog.handler
																.dialog('close');
													}
												} ]
									});
						});
		$("#sp_time").hide();
		$("#onTime").click(function() {
			$("#sp_time").show();
		});
		$("#nowPerform").click(function() {
			$("#sp_time").hide();
		});
	});
	//设备类型根据数据字典表获取
	$(function() {
		$("#equipment_type")
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
	});
	//设备配置根据数据字典表获取
	$(function() {
		$("#configuration")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIPMENT_CONFIG',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});
	});
	//固件任务完成标识根据数据字典表获取
	$(function() {
		$("#finishFlag")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'FIRMWARETASK_FINISH_FLAG',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});
	});

	//删除
	function deleteFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="delete_msg"/>！', 'info');
			return;
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
												'${pageContext.request.contextPath}/firmDeployTaskController/delete',
												{
													id : row.id,
													batch_no : row.batch_no,
													equipment_purpose : row.equipment_purpose
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														dataGrid
																.datagrid('reload');
													} else {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														dataGrid
																.datagrid('reload');
													}
													parent.$.messager
															.progress('close');
												}, 'JSON');

							}
						});
	}

	//撤销
	function revokeFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="please_choice_data"/>！', 'info');
			return;
		}
		if (row.isHistory == '1') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="revokeNowTask"/>！', 'info');
			return;
		}
		if (row.status == '1') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="revokeNomalTask"/>！', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="undo_message"/>？',
						function(b) {
							if (b) {

								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.post(
												'${pageContext.request.contextPath}/firmDeployTaskController/revoke',
												{
													id : row.id
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														dataGrid
																.datagrid('reload');
													} else {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														dataGrid
																.datagrid('reload');
													}
													parent.$.messager
															.progress('close');
												}, 'JSON');

							}
						});
	}

	//发布
	function deployFun() {
		$("#cronDiv").show().dialog({
			modal : false,
			width : 550,
			closable : false,
			height : 490,
			buttons : [ {
				text : '<spring:message code="release"/>',
				plain : true,
				handler : function() {
					edit();
				}
			}, {
				text : '<spring:message code="cancel"/>',
				plain : true,
				handler : function() {
					$("#cronDiv").window('close');
					dataGrid.datagrid('load', {});
					clearData();
				}
			} ]
		});
	}
	//刷机
	function flashFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="flash"/>',
					width : 500,
					height : 440,
					href : '${pageContext.request.contextPath}/firmDeployTaskController/toFlash',
					buttons : [ {
						text : '<spring:message code="flash"/>',
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
	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}

	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		$('#isHistory').val('');
		dataGrid.datagrid('load', {});
	}

	//发布页面JS开始

	//初始化终端型号
	$(function() {
		$("#equipment_model").combogrid({
			required : true,
			width : 214,
			panelWidth : 420,
			panelHeight : 350,
			fitColumns : true,
			idField : 'equipment_model',
			textField : 'type',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			editable : false,
			url : '',
			toolbar : '#toolbar3',
			columns : [ [ {
				field : 'type',
				title : '<spring:message code="equipment_model"/>',
				width : 200
			} ] ]
		});
	});
	//初始化批次号
	$(function() {
		$("#batch_no").combogrid({
			required : true,
			width : 214,
			panelWidth : 420,
			panelHeight : 350,
			fitColumns : true,
			multiple: true,
			idField : 'batch_no',
			textField : 'batch_no',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			editable : false,
			url : '',
			toolbar : '#toolbar5',
			columns : [ [ 
				{
					checkbox:true
				},
				{
				field : 'batch_no',
				title : '<spring:message code="batch_no"/>',
				width : 200
			} ] ]
		});
	});
	//初始化固件版本
	$(function() {
		$("#firmware_version").combogrid({
			required : true,
			width : 214,
			panelWidth : 420,
			panelHeight : 350,
			fitColumns : true,
			idField : 'version',
			textField : 'version',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			editable : false,
			url : '',
			toolbar : '#toolbar2',
			columns : [ [ {
				field : 'id',
				title : '<spring:message code="id"/>',
				width : 10,
				hidden : true
			}, {
				field : 'version',
				title : '<spring:message code="deployVersion"/>',
				width : 200
			} ] ]
		});
	});
	//初始化设备用途
	$(function() {
		$("#equipment_purpose").combobox(
				{
					url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
							+ 'EQUIPMENT_PURPOSE',
					editable : false, //不可编辑状态
					cache : false,
					panelHeight : 'auto',
					height : 24,
					width : 214,
					valueField : 'value',
					textField : 'value_cn'
		});
	});
	//固件版本根据区域、定制、设备配置筛选
	function selectByData() {
		var version = $("#version").val();
		var area = $('#area').combobox('getValue');
		var versionType = $('#versionType').combobox('getText');
		var custom = $('#custom').combobox('getValue');
		var configuration = $('#config').combobox('getValue');
		if (area != "" && custom != "" && configuration != ""
				&& versionType != "") {
			$("#firmware_version")
					.combogrid(
							{
								required : true,
								panelWidth : 420,
								panelHeight : 350,
								fitColumns : true,
								idField : 'version',
								textField : 'version',
								pagination : true,
								pageSize : 10,
								pageList : [ 10, 20, 30, 40, 50 ],
								editable : false,
								url : '${pageContext.request.contextPath}/firmwareController/dataGrid?version='
										+ version
										+ '&area='
										+ area
										+ '&versionType='
										+ versionType
										+ '&custom='
										+ custom
										+ '&configuration='
										+ configuration
										+ '&status=2&audit_flag=Y',
								toolbar : '#toolbar2',
								columns : [ [
										{
											field : 'id',
											title : '<spring:message code="id"/>',
											width : 10,
											hidden : true
										},
										{
											field : 'version',
											title : '<spring:message code="deployVersion"/>',
											width : 200
										} ] ],
								onSelect : selectByModel
							});
		}
	}
	//设备型号根据固件版本号筛选
	function selectByModel() {
		var type = $("#type").val();
		var g = $('#firmware_version').combogrid('grid'); // 获取表格控件对象
		var r = g.datagrid('getSelected'); //获取表格当前选中行
		$("#equipment_model")
				.combogrid(
						{
							required : true,
							panelWidth : 420,
							panelHeight : 350,
							fitColumns : true,
							idField : 'equipment_model',
							textField : 'type',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/firmwareEquipController/dataGrid?version='
									+ r.version + '&type=' + type,
							toolbar : '#toolbar3',
							columns : [ [ {
								field : 'type',
								title : '<spring:message code="equipment_model"/>',
								width : 200
							} ] ],
							onSelect : selectByEquipModel
						});
	}
	//批次号根据设备型号筛选
	function selectByEquipModel() {
		var batch_no = $('#batch').val();
		// 获取设备型号
		var g = $('#equipment_model').combogrid('grid'); // 获取表格控件对象
		var r = g.datagrid('getSelected'); //获取表格当前选中行
		// 获取固件ID
		var f = $('#firmware_version').combogrid('grid');
		var v = f.datagrid('getSelected'); //获取表格当前选中行
		$("#batch_no")
				.combogrid(
						{
							required : true,
							panelWidth : 420,
							panelHeight : 350,
							fitColumns : true,
							multiple: true,
							idField : 'batch_no',
							textField : 'batch_no',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/inOutStockController/queryBatchNo?equipment_model='
									+ r.equipment_model
									+ '&id='
									+ v.id
									+ '&batch_no=' + batch_no,
							toolbar : '#toolbar5',
							columns : [ [ 
								{
									checkbox:true
								},
								{
								field : 'batch_no',
								title : '<spring:message code="batch_no"/>',
								width : 200
							} ] ]
						});
	}
	//设备配置根据数据字典表获取
	$(function() {
		$("#config")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIPMENT_CONFIG',
							editable : false, //不可编辑状态
							required : true,
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn',
							onSelect : selectByData
						});
	});

	//区域根据数据字典表获取
	$(function() {
		$("#custom")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'CUSTOM',
							editable : false, //不可编辑状态
							cache : false,
							required : true,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn',
							onSelect : selectByData
						});

		$("#area")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'AREA',
							editable : false, //不可编辑状态
							cache : false,
							required : true,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn',
							onSelect : selectByData
						});

		$("#versionType")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'VERSION_PURPOSE',
							editable : false, //不可编辑状态
							cache : false,
							required : true,
							panelHeight : 'auto',
							height : 24,
							width : 214,
							valueField : 'value',
							textField : 'value_cn',
							onSelect : selectByData
						});
	});
	function clearValue() {
		$('#version').val('');
		selectByData();
	}

	function clearModelValue() {
		$('#type').val('');
		selectByModel();
	}

	function clearBatchValue() {
		$('#batch').val('');
		selectByEquipModel();
	}

	// 编辑发布
	function edit() {
		var equipment_model = $('#equipment_model').combogrid('grid').datagrid('getSelected').equipment_model;
		var batch_no = $('#batch_no').combogrid('getValues'); // 获取表格控件对象
		var firmware_version= $('#firmware_version').combogrid('grid').datagrid('getSelected').version;
		var area = $('#area').combobox('getValue');
		var custom = $('#custom').combobox('getValue');
		var equipment_purpose = $('#equipment_purpose').combobox('getValue');
		var configuration = $('#config').combobox('getValue');
		var porformType = $('input[name="porformType"]:checked').val();
		var cron = $("#cron").val();
		if (porformType == '1') {
			if (cron == "" || cron == undefined || cron == null) {
				$.messager.alert('<spring:message code="hint"/>',
						'<spring:message code="select_cron"/>', 'info');
				return;
			}
		}
		if (equipment_purpose == "" || equipment_purpose == undefined
				|| equipment_purpose == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_equiPurpose"/>', 'info');
			return;
		}
		params = "equipment_purpose=" + equipment_purpose + "&area=" + area
				+ "&custom=" + custom + "&configuration=" + configuration
				+ "&equipment_type=" + equipment_model + "&batch_no="
				+ batch_no + "&porformType=" + porformType + "&performTime="
				+ cron + "&firmware_version=" + firmware_version;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$
				.post(
						'${pageContext.request.contextPath}/firmDeployTaskController/deploy',
						params, function(result) {
							if (result.success) {
								parent.$.messager.show({
									title : '<spring:message code="hint"/>',
									timeout : 3000,
									msg : result.msg
								});
								$("#cronDiv").window('close');
								dataGrid.datagrid('load', {});
								clearData();
							} else {
								parent.$.messager.show({
									title : '<spring:message code="hint"/>',
									timeout : 3000,
									msg : result.msg
								});
							}
							parent.$.messager.progress('close');
						}, 'JSON');
	}
	// 点击取消，清除页面数据
	function clearData() {
		$("#version").val('');
		$('#type').val('');
		$('#batch').val('');
		removeComboxItem('area', $('#area').combobox('getValue'));
		removeComboxItem('custom', $('#custom').combobox('getValue'));
		removeComboxItem('config', $('#config').combobox('getValue'));
		removeComboxItem('versionType', $('#versionType').combobox('getValue'));
		removeComboxItem('equipment_purpose', $('#equipment_purpose').combobox(
				'getValue'));

		$('#equipment_model').combogrid('clear', '');
		$('#firmware_version').combogrid('clear', '');
		$('#batch_no').combogrid('clear', '');
		$("#batch_no").combogrid("grid").datagrid("loadData", {
			total : 0,
			rows : []
		});
		$("#equipment_model").combogrid("grid").datagrid("loadData", {
			total : 0,
			rows : []
		});
		$("#firmware_version").combogrid("grid").datagrid("loadData", {
			total : 0,
			rows : []
		});

		$("#nowPerform").prop('checked', true);
		$("#sp_time").hide();
		$("#cron").val('');
	}
	//清理下来框选择的记录
	function removeComboxItem(id, v) {
		var rows = $('#' + id).combobox('getData');
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].name == v) {
				rows.splice(i, 1);
				break;
			}
		}
		$('#' + id).combobox('loadData', rows).combobox('setValue', '');
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
						<td><spring:message code="equipment_model" /></td>
						<td><spring:message code="status" /></td>
						<td><spring:message code="isHistory" /></td>
						<td><spring:message code="deployVersion" /></td>
						<td><spring:message code="batch_no" /></td>
						<td><spring:message code="begin_date" /></td>
						<td><spring:message code="end_date" /></td>
					</tr>
					<tr>
						<td><input id="equipment_type" name="equipment_type"
							type="text" style="height: 15px; width: 135px; margin-top: 10px;"></td>
						<td><input id="finishFlag" name="finishFlag" type="text"
							style="height: 15px; width: 130px; margin-top: 10px;"></td>
						<td><select id="isHistory" name="isHistory"
							class="easyui-combobox" style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto'
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="0"><spring:message code="no" /></option>
								<option value="1"><spring:message code="yes" /></option>
						</select></td>
						<td><input id="firmware_ver" name="deployVersion" type="text"
							style="height: 15px; width: 135px; margin-top: 10px;"></td>
						<td><input name="batch_no" type="text"
							style="height: 15px; width: 135px; margin-top: 10px;"></td>
						<td><input id="beginTime" name="beginTime" type="text"
							class="easyui-datebox" style="width: 150px; height: 25px;"
							data-options="editable:false"></td>
						<td><input id="endTime" name="endTime" type="text"
							class="easyui-datebox" style="width: 150px; height: 25px;"
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
			test="${fn:contains(sessionInfo.resourceList, '/firmDeployTaskController/deploy')}">
			<a onclick="deployFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'rss_go', plain:true"
				href="javascript:void(0);"><spring:message code="release" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmDeployTaskController/toFlash')}">
			<a onclick="flashFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'disconnect', plain:true"
				href="javascript:void(0);"><spring:message code="flash" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmDeployTaskController/delete')}">
			<a onclick="deleteFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil_delete', plain:true"
				href="javascript:void(0);"><spring:message code="delete" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmDeployTaskController/revoke')}">
			<a onclick="revokeFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'arrow_green', plain:true"
				href="javascript:void(0);"><spring:message code="undo" /></a>
		</c:if>
	</div>

	<div id="cronDiv" title='<spring:message code="release"/>'
		style="overflow-y: scroll; display: none;">
		<form id="cronForm">
			<table class="sp_table">
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="area" />
							：
						</p></td>
					<td><p>
							<input id="area" name="area">
						</p></td>
				</tr>
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="custom" />
							：
						</p></td>
					<td><p>
							<input id="custom" name="custom">
						</p></td>
				</tr>
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="configuration" />
							：
						</p></td>
					<td><p>
							<input id="config" name="configuration">
						</p></td>
				</tr>
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="versionPurpose" />
							：
						</p></td>
					<td><p>
							<input id="versionType" name="versionType">
						</p></td>
				</tr>
				<tr class="sp_table_tr">
					<td><p class="sp_table_td_ef">
							<spring:message code="deployVersion" />
							：
						</p></td>
					<td><p>
							<input id="firmware_version" name="firmware_version" type="text" />
						</p></td>
				</tr>
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="equipment_model" />
							：
						</p></td>
					<td><p>
							<input id="equipment_model" name="equipment_model" type="text" />
						</p></td>
				</tr>
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="batch_no" />
							：
						</p></td>
					<td><p>
							<input id="batch_no" name="batch_no">
						</p></td>
				</tr>
				<tr>
					<td><p class="sp_table_td_ef">
							<spring:message code="equipment_purpose" />
							：
						</p></td>
					<td><p>
							<input id="equipment_purpose" name="equipment_purpose">
						</p></td>
				</tr>
				<tr class="sp_radio">
					<td><p>
							<spring:message code="please_perform" />
							：
						</p></td>
				</tr>
				<tr class="sp_radio">
					<td>
						<!-- 单选按钮 立即  --> <input class="sp_radio_space" id="nowPerform"
						name="porformType" type="radio" value="0" checked /> <spring:message
							code="now_perform" />
					</td>
					<td>
						<!-- 单选按钮 按时生效  --> <input class="sp_radio_space" id="onTime"
						name="porformType" type="radio" value="1" /> <spring:message
							code="Take_effect_on_time" />
					</td>
				</tr>
				<tr id="sp_time">
					<td><p style="margin-left: 17px;">
							<spring:message code="performTime" />
							：
						</p></td>
					<td><input id="cron" name="performTime"
						class="easyui-validatebox" style="width: 150px;" data-options=""
						readonly /></td>
				</tr>
			</table>

		</form>
	</div>
	<div id="toolbar2" style="display: none;">
		<form id="firmwareForm">
			<table>
				<tr>
					<td><spring:message code="deployVersion" />：</td>
					<td><input id="version" name="firmware_version"
						class="easyui-validatebox span2" style="width: 220px;" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true"
						onclick="selectByData();"><spring:message code="query" /></a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'pencil_delete',plain:true"
						onclick="clearValue();"><spring:message code="clear" /></a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar3" style="display: none;">
		<form id="terminalModelForm">
			<table>
				<tr>
					<td><spring:message code="equipment_model" />：</td>
					<td><input id="type" name="type"
						class="easyui-validatebox span2" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true"
						onclick="selectByModel();"><spring:message code="query" /></a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'pencil_delete',plain:true"
						onclick="clearModelValue();"><spring:message code="clear" /></a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar5" style="display: none;">
		<form id="batchNoForm">
			<table>
				<tr>
					<td><spring:message code="batch_no" />：</td>
					<td><input id="batch" name="batch_no"
						class="easyui-validatebox span2" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true"
						onclick="selectByEquipModel();"><spring:message code="query" /></a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'pencil_delete',plain:true"
						onclick="clearBatchValue();"><spring:message code="clear" /></a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
