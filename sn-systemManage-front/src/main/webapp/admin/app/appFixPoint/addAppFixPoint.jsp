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
							url : '${pageContext.request.contextPath}/appFixPointController/add',
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
		$("#lan").combobox({
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'LAN',
							editable : false, //不可编辑状态
							required : true,
							cache : false,
							panelHeight : 'auto',
							width : 200,
							height : 24,
							valueField : 'value_cn',
							textField : 'value_cn'
		});
		
		$("#appVersion").combobox({
			url : '${pageContext.request.contextPath}/appVersionController/queryVersionByAppName?appName='+appName,
			required : true,
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			width : 200,
			height : 24,
			valueField : 'appVersion',
			textField : 'appVersion'
       });

		$("#appName").combobox({
			url : '${pageContext.request.contextPath}/appVersionController/queryAllAppVersion',
			required : true,
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			width : 200,
			height : 24,
			valueField : 'appName',
			textField : 'appName',
			onSelect : selectByData
       });
	});

	function selectByData() {
		var appName = $('#appName').combobox('getValue');
		$("#appVersion").combobox({
			url : '${pageContext.request.contextPath}/appVersionController/queryVersionByAppName?appName='+appName,
			required : true,
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			width : 200,
			height : 24,
			valueField : 'appVersion',
			textField : 'appVersion'
       });
	}

	$(function() {
		//添加 CK编辑器
		var editor = CKEDITOR.instances['fixPoint'];
		if (editor) {
			delete CKEDITOR.instances['fixPoint'];
		}
		CKEDITOR.replace('fixPoint', {
			height : '340px',
			width : '900px',
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
					<td><spring:message code="app_name" />：</td>
					<td><input id="appName" name="appName" type="text"></td>
					<td style="padding-left:20px;"><spring:message code="version" />：</td>
					<td><input id="appVersion" name="appVersion" type="text"></td>
					<td style="padding-left:20px;"><spring:message code="lan" />：</td>
					<td><input id="lan" name="lan" type="text"></td>
			</table>
			<textarea id="fixPoint" name="fixPoint" warp="virtual" data-options="required:true"></textarea>
		</form>
	</div>
</div>