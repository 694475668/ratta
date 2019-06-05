<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {

		var session_roleId = '${role.id}';
		parent.$.messager.progress('close');

		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/roleController/edit',
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
											.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为role.jsp页面预定义好了
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
	style="margin-top: 30px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td><spring:message code="role_name" /><input name="id"
						type="hidden" class="span2" value="${role.id}" readonly="readonly"></td>
					<td><input name="name" style="width: 320px;" type="text"
						class="easyui-validatebox span2"
						data-options="required:true,validType:'maxLength[25]'"
						value="${role.name}" /></td>
					<!-- 					<td>上级角色</td> -->
					<%-- 					<td><select id="pid" name="pid" style="width: 140px; height: 29px;"></select><img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#pid').combotree('clear');" /></td> --%>
				</tr>
				<tr>
					<!-- <td>排序</td>
					<td><input name="seq" value="100" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:true"></td> -->

				</tr>
				<tr>
					<td style="height: 8px"></td>
				</tr>
				<tr>
					<td><spring:message code="remark" /></td>
					<td><textarea name="remark" style="width: 320px;"
							class="span5" maxlength="100">${role.remark}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>