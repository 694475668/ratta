<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>设备日志管理</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/equipmentLogController/detail')}">
	<script type="text/javascript">
		$.canDetail = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/equipmentLogController/dataGrid',
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
										field : 'firmware_version',
										title : '<spring:message code="firmware_version"/>',
										width : 220,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'logName',
										title : '<spring:message code="logName"/>',
										width : 270,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'type',
										title : '<spring:message code="log_type"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.type == 1) {
												return '<spring:message code="normal_log"/>';
											}
											if (rec.type == 2) {
												return '<spring:message code="error_log"/>';
											}
										}
									},
									{
										field : 'create_time',
										title : '<spring:message code="create_date"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'isDownload',
										title : '<spring:message code="isDownload"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.isDownload == 0) {
												return '<spring:message code="no_download"/>';
											}
											if (rec.isDownload == 1) {
												return '<spring:message code="downloaded"/>';
											}
										}
									},
									{
										field : 'flag',
										title : '<spring:message code="flag"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.flag == 0) {
												return '<spring:message code="no_check"/>';
											}
											if (rec.flag == 1) {
												return '<spring:message code="checked"/>';
											}
											if (rec.flag == 2) {
												return '<spring:message code="passed"/>';
											}
										}
									},
									{
										field : 'action',
										title : '<spring:message code="remark"/>',
										width : 60,
										align : 'center',
										formatter : function(value, row, index) {
											if (row.flag != '0') {
												var str = '';
												if ($.canDetail) {
													str += $
															.formatString(
																	'<img onclick="detail(\'{0}\');" src="{1}" title="<spring:message code="remark"/>"/>',
																	row.id,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/feed/feed.png');
												}
												return str;
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

	});

	//详情
	function detail(id) {
		parent.$
				.modalDialog({
					title : '<spring:message code="remark"/>',
					width : 850,
					height : 600,
					href : '${pageContext.request.contextPath}/equipmentLogController/detail?id='
							+ id,
					buttons : [ {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//备注
	function remark() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="remark_msg"/>', 'info');
			return;
		}
		if (row.isDownload == 0) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="first_download_msg"/>', 'info');
			return;
		}
		if (row.flag == 2) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="no_remark"/>', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="remark"/>',
					width : 850,
					height : 600,
					href : '${pageContext.request.contextPath}/equipmentLogController/toRemark?&id='
							+ row.id,
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
	//审阅
	function passed() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="passed_msg"/>！', 'info');
			return;
		}
		if (row.flag != 1) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="checked_msg"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="passed"/>',
					width : 850,
					height : 600,
					href : '${pageContext.request.contextPath}/equipmentLogController/toPassed?id='
							+ row.id,
					buttons : [ {
						text : '<spring:message code="passed"/>',
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
	//删除
	function deleteFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="delete_msg"/>！', 'info');
			return;
		}
		if (row.type == 2) {
			if (row.flag != 2) {
				$.messager.alert('<spring:message code="hint"/>',
						'<spring:message code="only_delete"/>！', 'info');
				return;
			}
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
												'${pageContext.request.contextPath}/equipmentLogController/delete',
												{
													id : row.id,
													logName : row.logName
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

	//下载
	function downloadFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_download"/>！', 'info');
			return;
		}
		$
				.fileDownload(
						'${pageContext.request.contextPath}/equipmentLogController/download',
						{
							httpMethod : 'POST',
							data : {
								logName : row.logName,
								id : row.id
							},
							successCallback : function(url) {
								$
										.post(
												'${pageContext.request.contextPath}/equipmentLogController/updateIsDownload',
												{
													id : row.id
												},
												function(result) {
													if (result.success) {
														dataGrid
																.datagrid('reload');
													}
												}, 'JSON');

							},
							failCallback : function(html, url) {
								var json = JSON.parse(html);
								console.log(json);
							}
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
						<td><spring:message code="equipment_no" /></td>
						<td><spring:message code="firmware_version" /></td>
						<td><spring:message code="log_type" /></td>
						<td><spring:message code="isDownload" /></td>
						<td><spring:message code="flag" /></td>
						<td><spring:message code="beginTime" /></td>
						<td><spring:message code="endTime" /></td>
					</tr>
					<tr>
						<td><input name="equipment_no" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input name="firmware_version" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
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
								<option value="1"><spring:message code="normal_log" /></option>
								<option value="2"><spring:message code="error_log" /></option>
						</select></td>
						<td><select name="isDownload" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto',
				                height:25
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="0"><spring:message code="no_download" /></option>
								<option value="1"><spring:message code="downloaded" /></option>
						</select></td>
						<td><select name="flag" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto',
				                height:25
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="0"><spring:message code="no_check" /></option>
								<option value="1"><spring:message code="checked" /></option>
								<option value="2"><spring:message code="passed" /></option>
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
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/equipmentLogController/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'"><spring:message
					code="delete" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/equipmentLogController/download')}">
			<a onclick="downloadFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'arrow_down'"><spring:message
					code="log_download" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/equipmentLogController/toRemark')}">
			<a onclick="remark();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'cup_edit'"><spring:message
					code="remark" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/equipmentLogController/toPassed')}">
			<a onclick="passed();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'thumb_up'"><spring:message
					code="passed" /></a>
		</c:if>
	</div>
</body>
</html>
