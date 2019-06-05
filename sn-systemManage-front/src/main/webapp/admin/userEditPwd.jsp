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
							url : '${pageContext.request.contextPath}/userController/editPwd',
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
								try {
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
									parent.$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : result.msg,
												timeout : 5000,
												showType : 'slide'
											});
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
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
	style="margin-top: 20px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td colspan="2"><input name="id" type="hidden" class="span4"
						value="${user.id}" readonly="readonly">&nbsp;</td>
				</tr>
				<tr>
					<th style="padding-right: 10px"><spring:message
							code="username" /></th>
					<td><input readonly="readonly" type="text" class="span4"
						value="${user.username}" readonly="readonly"></td>
				</tr>
				<tr>
					<th><spring:message code="pwd" /></th>
					<td><input name="pwd" type="password"
						class="easyui-validatebox span4"
						data-options="required:true,validType:'validPWD[6, 32]'"></td>
				</tr>
			</table>
		</form>
	</div>
</div>