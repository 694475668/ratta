<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/firmDeployTaskController/flash',
							onSubmit : function() {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								var isValid = $(this).form('validate');
								if (!isValid) {
									parent.$.messager.progress('close');
								}
								return isValid;
							},
							success : function(result) {
								parent.$.messager.progress('close');
								try {//Json解析出错时，异常处理。
									result = $.parseJSON(result);
								} catch (e) {
									parent.$.messager
											.alert(
													'<spring:message code="error"/>',
													'<spring:message code="req_error"/>',
													'error');
									return false;
								}
								if (result.success) {
									$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : result.msg,
												timeout : 5000,
												showType : 'slide'
											});
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');
									parent.$.modalDialog.handler
											.dialog('close');
								} else {
									parent.$.messager.alert(
											'<spring:message code="error"/>',
											result.msg, 'error');
								}
							}
						});

		//切换单刷和批刷
		$(".batch").hide();
		$("#singleBrush").click(
				function() {
					$(".batch").hide();
					$(".single").show();
					removeComboxItem('equipment_purpose', $(
							'#equipment_purpose').combobox('getValue'));
					$('#equipment_model').combogrid('clear', '');
					$('#batch_no').combogrid('clear', '');
				});
		$("#batchBrush").click(function() {
			$(".batch").show();
			$(".single").hide();
			$("#equipment_no").val("");
		});

	});
	//初始化终端型号
	$(function() {
		$("#equipment_model").combogrid({
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
		$("#batch_no")
				.combogrid(
						{
							panelWidth : 420,
							panelHeight : 350,
							multiple: true,
							fitColumns : true,
							idField : 'batch_no',
							textField : 'batch_no',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/inOutStockController/queryBatchNo?id=0&equipment_model='
									+ r.equipment_model
									+ '&batch_no='
									+ batch_no,
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

	//清理下来框选择的记录
	function removeComboxItem(id, v) {
		var rows = $('#' + id).combobox('getData');
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].name == v) {
				rows.splice(i, 1);
				break;
			}
			//;
		}
		$('#' + id).combobox('loadData', rows).combobox('setValue', '');
	}
</script>
<div style="margin-top: 20px;">
	<div style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto;">
				<tr style="height: 36px;">
					<td><p>
							<spring:message code="root_type" />
							：
						</p></td>
					<td><input id="singleBrush" style="margin-right: 10px;"
						type="radio" name="rootType" value="1" checked /> <spring:message
							code="single_brush" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="batchBrush" style="margin-right: 10px;" type="radio"
						name="rootType" value="2" /> <spring:message code="batch_brush" />
					</td>
				</tr>
				<tr>
					<td><p>
							<spring:message code="area" />
							：
						</p></td>
					<td><p>
							<input id="area" name="area">
						</p></td>
				</tr>
				<tr>
					<td><p>
							<spring:message code="custom" />
							：
						</p></td>
					<td><p>
							<input id="custom" name="custom">
						</p></td>
				</tr>
				<tr>
					<td><p>
							<spring:message code="configuration" />
							：
						</p></td>
					<td><p>
							<input id="config" name="configuration">
						</p></td>
				</tr>
				<tr>
					<td><p>
							<spring:message code="versionPurpose" />
							：
						</p></td>
					<td><p>
							<input id="versionType" name="versionType">
						</p></td>
				</tr>
				<tr>
					<td><p>
							<spring:message code="deployVersion" />
							：
						</p></td>
					<td><p>
							<input id="firmware_version" name="firmware_version" />
						</p></td>
				</tr>
				<tr class="batch">
					<td><p>
							<spring:message code="equipment_model" />
							：
						</p></td>
					<td><p>
							<input id="equipment_model" name="equipment_type" />
						</p></td>
				</tr>
				<tr class="batch">
					<td><p>
							<spring:message code="batch_no" />
							：
						</p></td>
					<td><p>
							<input id="batch_no" name="batch_no">
						</p></td>
				</tr>
				<tr class="batch">
					<td><p>
							<spring:message code="equipment_purpose" />
							：
						</p></td>
					<td><p>
							<input id="equipment_purpose" name="equipment_purpose">
						</p></td>
				</tr>
				<tr class="single">
					<td><p>
							<spring:message code="equipment_no" />
							：
						</p></td>
					<td><p>
							<input id="equipment_no" name="equipmentno">
						</p></td>
				</tr>
			</table>

		</form>
	</div>
</div>


<div id="toolbar2" style="display: none;">
	<form id="firmwareForm">
		<table>
			<tr>
				<td><spring:message code="deployVersion" />：</td>
				<td><input id="version" name="firmware_version"
					style="width: 220px;" /></td>
				<td><a href="javascript:void(0);" onclick="selectByData();"><spring:message
							code="query" /></a></td>
				<td><a href="javascript:void(0);" onclick="clearValue();"><spring:message
							code="clear" /></a></td>
			</tr>
		</table>
	</form>
</div>
<div id="toolbar3" style="display: none;">
	<form id="terminalModelForm">
		<table>
			<tr>
				<td><spring:message code="equipment_model" />：</td>
				<td><input id="type" name="type" /></td>
				<td><a href="javascript:void(0);" onclick="selectByModel();"><spring:message
							code="query" /></a></td>
				<td><a href="javascript:void(0);" onclick="clearModelValue();"><spring:message
							code="clear" /></a></td>
			</tr>
		</table>
	</form>
</div>
<div id="toolbar5" style="display: none;">
	<form id="batchNoForm">
		<table>
			<tr>
				<td><spring:message code="batch_no" />：</td>
				<td><input id="batch" name="batch_no" /></td>
				<td><a href="javascript:void(0);"
					onclick="selectByEquipModel();"><spring:message code="query" /></a></td>
				<td><a href="javascript:void(0);" onclick="clearBatchValue();"><spring:message
							code="clear" /></a></td>
			</tr>
		</table>
	</form>
</div>

