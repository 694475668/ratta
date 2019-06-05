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
							url : '${pageContext.request.contextPath}/firmwareController/commitAudit',
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
</script>



<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 40px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input id="id" name="id" type="hidden" value="${id }">
			<table style="margin-left: 30px;">
				<tr>
					<td style="padding-right: 30px"><spring:message
							code="audit_flag" /> :</td>
					<td><select name="audit_flag" id="audit_flag">
							<option value="Y"><spring:message code="agree" /></option>
							<option value="N"><spring:message code="disagree" /></option>
					</select></td>

				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="remark" />
						:</td>
					<td><input id="audit_info" name="audit_info"
						class="easyui-validatebox span2" style="width: 420px;"
						data-options="validType:'maxLength[200]'" type="text">
					</td>
				</tr>
			</table>
		</form>
	</div>

</div>