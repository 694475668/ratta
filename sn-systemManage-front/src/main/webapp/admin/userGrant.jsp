<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {

		$('#roleIds').combotree({
			url : '${pageContext.request.contextPath}/roleController/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			multiple : true,
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			},
			cascadeCheck : false,
			value : $.stringToList('${user.roleIds}')
		});

		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/userController/grant',
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
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
									parent.$.modalDialog.handler
											.dialog('close');
									parent.$.modalDialog.openner_dataGrid
											.datagrid('uncheckAll').datagrid(
													'unselectAll').datagrid(
													'clearSelections');
									parent.$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : result.msg,
												timeout : 5000,
												showType : 'slide'
											});
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
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><spring:message code="username" /></td>
					<td><input name="ids" type="hidden" value="${ids}"
						readonly="readonly"> <input type="text" class="span4"
						value="${usernames}" readonly="readonly"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="roleIds" /></td>
					<td><select id="roleIds" name="roleIds"
						style="width: 300px; height: 30px;"></select><img
						src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png"
						onclick="$('#roleIds').combotree('clear');" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>