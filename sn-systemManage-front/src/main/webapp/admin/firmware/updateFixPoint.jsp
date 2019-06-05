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
							url : '${pageContext.request.contextPath}/firmwareFixPointController/edit',
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
				style="margin: 10px; border-collapse: separate; border-spacing: 0px 5px;">
				<tr>
					<td><spring:message code="firmware_version" />：</td>
					<td><input name="id" type="hidden"
						value="${firmwareFixPoint.id}"> <input
						name="firmware_version" type="text"
						value="${firmwareFixPoint.firmware_version}"
						style="width: 400px; height: 14px;" readonly="readonly"></td>
				</tr>
				<tr>
					<td><spring:message code="lan" />：</td>
					<td><input name="lan" type="text"
						value="${firmwareFixPoint.lan}"
						style="width: 400px; height: 14px;" readonly="readonly"></td>
				</tr>
			</table>
			<td><textarea id="fixPoint" name="fixPoint" warp="virtual"
					required>${firmwareFixPoint.fixPoint}</textarea></td>
		</form>
	</div>
</div>