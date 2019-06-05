<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>app版本管理</title>
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
							url : '${pageContext.request.contextPath}/appVersionController/dataGrid',
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
										title : '<spring:message code="url"/>',
										field : 'url',
										width : 150,
										hidden : true
									},
									{
										field : 'appName',
										title : '<spring:message code="app_name"/>',
										width : 180,
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
										field : 'versionNo',
										title : '<spring:message code="version_no"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'packageName',
										title : '<spring:message code="package_name"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'updateFlag',
										title : '<spring:message code="is_install"/>',
										width : 100,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.updateFlag == 0) {
												return '<spring:message code="no_force"/>';
											}
											if (rec.updateFlag == 1) {
												return '<spring:message code="force"/>';
											}
										}
									},
									{
										field : 'auditingFlag',
										title : '<spring:message code="auditingFlag"/>',
										width : 100,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.auditingFlag == 1) {
												return '<spring:message code="not_auditing"/>';
											}
											if (rec.auditingFlag == 2) {
												return '<spring:message code="pass"/>';
											}
											if (rec.auditingFlag == 3) {
												return '<spring:message code="no_pass"/>';
											}
										}
									},
									{
										field : 'deployFlag',
										title : '<spring:message code="deployFlag"/>',
										width : 100,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.deployFlag == 1) {
												return '<spring:message code="not_deploy"/>';
											}
											if (rec.deployFlag == 2) {
												return '<spring:message code="gray"/>';
											}
											if (rec.deployFlag == 3) {
												return '<spring:message code="deploy"/>';
											}
											if (rec.deployFlag == 4) {
												return '<spring:message code="no_deploy"/>';
											}
										}
									},
									{
										title : '<spring:message code="file_name"/>',
										field : 'fileName',
										width : 300,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'appEnvironment',
										title : '<spring:message code="app_environment"/>',
										width : 130,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'opUser',
										title : '<spring:message code="op_user"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'opTime',
										title : '<spring:message code="upload_file_time"/>',
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
												'${pageContext.request.contextPath}/appVersionController/delete',
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
	//版本上传
	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="app_upload"/>',
					width : 500,
					closable : false,
					height : 350,
					href : '${pageContext.request.contextPath}/appVersionController/addAppUpload',
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
							deleteFile();
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//删除文件
	function deleteFile() {
		$
				.post(
						'${pageContext.request.contextPath}/appVersionController/deleteFile',
						'JSON');
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
						'${pageContext.request.contextPath}/appVersionController/download',
						{
							httpMethod : 'POST',
							data : {
								url : row.url,
								fileName : row.fileName
							},
							successCallback : function(url) {

							},
							failCallback : function(html, url) {
								var json = JSON.parse(html);
								console.log(json);
							}
						});
	}

	//撤销
	function undoFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="undo_msg"/>！', 'info');
			return;
		}
		if (row.deployFlag != '3') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="undo_error_info"/>', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="undo_message"/>',
						function(b) {
							if (b) {

								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.post(
												'${pageContext.request.contextPath}/appVersionController/undo',
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
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="deploy_msg"/>！', 'info');
			return;
		}
		if (row.auditingFlag != '2') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="deploy_error_info"/>', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="undo_message"/>',
						function(b) {
							if (b) {

								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.post(
												'${pageContext.request.contextPath}/appVersionController/deploy',
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

	
	//审核
	function auditingFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="audit_msg"/>', 'info');
			return;
		}
		if (row.auditingFlag != '1') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="app_auditing_error_msg"/>', 'info');
			return;
		}
		if (row.deployFlag != '2') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="app_auditing_error_msg"/>', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="audit"/>',
					width : 390,
					height : 200,
					href : '${pageContext.request.contextPath}/appVersionController/toAuditing?id='
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
	
	//灰度
	function grayFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="gray_msg"/>', 'info');
			return;
		}
		if (row.auditingFlag == '3') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="app_gray_error_msg"/>', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="gray"/>',
					width : 652,
					height : 453,
					closable : false,
					href : '${pageContext.request.contextPath}/appVersionController/toGray?id='
							+ row.id,
					buttons : [ {
						text : '<spring:message code="close"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							searchFun();
							parent.$.modalDialog.handler.dialog('close');
						}
					}]
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
						<td><spring:message code="app_environment" /></td>
						<td><spring:message code="is_install" /></td>
						<td><spring:message code="auditingFlag" /></td>
						<td><spring:message code="deployFlag" /></td>
					</tr>
					<tr>
						<td><input name="appName" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input name="appVersion" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input name="appEnvironment" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><select name="updateFlag" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto'
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="0"><spring:message code="no_force" /></option>
								<option value="1"><spring:message code="force" /></option>
						</select></td>
						<td><select name="auditingFlag" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto'
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="1"><spring:message code="not_auditing" /></option>
								<option value="2"><spring:message code="pass" /></option>
								<option value="3"><spring:message code="no_pass" /></option>
						</select></td>
						<td><select name="deployFlag" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto'
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="1"><spring:message code="not_deploy" /></option>
								<option value="2"><spring:message code="gray" /></option>
								<option value="3"><spring:message code="deploy" /></option>
								<option value="4"><spring:message code="no_deploy" /></option>
						</select></td>
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
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/addAppUpload')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'arrow_up'"><spring:message
					code="ver_upload" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/download')}">
			<a onclick="downloadFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'arrow_down'"><spring:message
					code="app_download" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/undo')}">
			<a onclick="undoFun()" class="easyui-linkbutton"
				data-options="iconCls:'arrow_in', plain:true"
				href="javascript:void(0);"><spring:message code="undo" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/toAuditing')}">
			<a onclick="auditingFun()" class="easyui-linkbutton"
				data-options="iconCls:'status_away', plain:true"
				href="javascript:void(0);"><spring:message code="audit" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/toGray')}">
			<a onclick="grayFun()" class="easyui-linkbutton"
				data-options="iconCls:'arrow_join', plain:true"
				href="javascript:void(0);"><spring:message code="gray" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/deploy')}">
			<a onclick="deployFun()" class="easyui-linkbutton"
				data-options="iconCls:'arrow_out', plain:true"
				href="javascript:void(0);"><spring:message code="deploy" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/appVersionController/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'"><spring:message
					code="delete" /></a>
		</c:if>
	</div>
</body>
</html>
