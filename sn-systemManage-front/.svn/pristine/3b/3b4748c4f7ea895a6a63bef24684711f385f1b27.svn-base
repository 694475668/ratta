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
							url : '${pageContext.request.contextPath}/firmwareFixPointController/add',
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
							width : 414,
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});

		//初始化固件版本
		$("#firmware_version")
				.combogrid(
						{
							required : true,
							panelWidth : 420,
							panelHeight : 350,
							width : 414,
							height : 24,
							fitColumns : true,
							idField : 'version',
							textField : 'version',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/firmwareController/dataGrid',
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
										title : '<spring:message code="firmware_version"/>',
										width : 200
									} ] ]
						});
	});

	function selectByData() {
		var version = $("#version").val();
		$("#firmware_version")
				.combogrid(
						{
							required : true,
							panelWidth : 420,
							panelHeight : 350,
							width : 414,
							height : 24,
							fitColumns : true,
							idField : 'version',
							textField : 'version',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/firmwareController/dataGrid?version='
									+ version,
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
										title : '<spring:message code="firmware_version"/>',
										width : 200
									} ] ]
						});
	}

	function clearValue() {
		$('#version').val('');
		selectByData();
	}

	$(function() {
		//添加 CK编辑器
		var editor = CKEDITOR.instances['fixPoint'];
		if (editor) {
			delete CKEDITOR.instances['fixPoint'];
		}
		CKEDITOR.replace('fixPoint', {
			height : '340px',
			width : '800px',
			language : 'zh-cn'
		});
	});
</script>

<div>
	<div style="overflow: hidden;">
		<form id="form" method="post">
			<table
				style="margin: 10px; border-collapse: separate; border-spacing: 0px 10px;">
				<tr>
					<td><spring:message code="firmware_version" />：</td>
					<td><input id="firmware_version" name="firmware_version"
						type="text"></td>
				</tr>
				<tr>
					<td><spring:message code="lan" />：</td>
					<td><input id="lan" name="lan" type="text"
						data-options="required:true"></td>
				</tr>
			</table>
			<textarea id="fixPoint" name="fixPoint" warp="virtual"
				data-options="required:true"></textarea>
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
</div>