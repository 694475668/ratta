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
							url : '${pageContext.request.contextPath}/equipmentLogController/remark',
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
		//添加 CK编辑器
		var editor = CKEDITOR.instances['remark'];
		if (editor) {
			delete CKEDITOR.instances['remark'];
		}
		CKEDITOR.replace('remark', {
			height : '380px',
			width : '850px',
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
					<td><spring:message code="logName" />： <input name="id"
						type="hidden" value="${equipmentLog.id}">${equipmentLog.logName}</td>
				</tr>
			</table>
			<textarea id="remark" name="remark">${equipmentLog.remark}</textarea>
		</form>
	</div>
</div>