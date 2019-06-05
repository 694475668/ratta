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
							url : '${pageContext.request.contextPath}/snuserController/editEmail',
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
	//输入框验证
	$.extend($.fn.validatebox.defaults.rules, {
		email : {
			validator : function(value) {
				return /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/i.test(value);
			},
			message : '邮箱格式不正确'
		}
	});
	
</script>



<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 40px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input id="id" name="userid" type="hidden" value="${snUser.userid }">
			<table style="margin: auto">
				<tr>
					<td style="padding-right: 10px"><spring:message code="old_email" />:</td>
					<td><input name="oldEmail" type="text" class="easyui-validatebox span2" style="width: 255px;"
						data-options="required:true" value="${snUser.email }"
						readonly="readonly"></td>

				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="new_email" />:</td>
					<td><input name="email" class="easyui-validatebox span2"
						style="width: 255px;"
						data-options="required:true,validType:'email'" type="text">
					</td>
				</tr>
			</table>
		</form>
	</div>

</div>