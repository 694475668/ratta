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
							url : '${pageContext.request.contextPath}/userController/edit',
							onSubmit : function() {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								var isValid = $(this).form('validate');
								if (!isValid) {
									parent.$.messager.progress('close');
								} else {
									if ($('#pwd').val() != $('#old_pwd').val()) {
										$('#pwd').val(hex_md5($('#pwd').val()));
									}
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
													'_$tag_________________________',
													'_$tag_____________________________',
													'error');
									return false;
								}
								if (result.success) {
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
									parent.$.modalDialog.handler
											.dialog('close');
									parent.$.messager
											.show({
												title : '_$tag________________________',
												msg : result.msg,
												timeout : 5000,
												showType : 'slide'
											});
								} else {
									parent.$.messager.alert(
											'_$tag_________________________',
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
			<input name="isactive" type="hidden" class="span4"
				value="${user.isactive}"> <input name="status"
				type="hidden" class="span4" value="${user.status}"> <input
				name="last_logindate" type="hidden" class="span4"
				value="${user.last_logindate}"> <input name="counts"
				type="hidden" class="span4" value="${user.counts}"> <input
				name="id" type="hidden" class="span4" value="${user.id}"> <input
				id='old_pwd' name="" type="hidden" class="span4" value="${user.pwd}" />
			<table style="margin: auto">
				<th><h5></h5></th>

				<%-- <tr>
			      <td><input name="counts" type="hidden" class="span4" value="${user.counts}" >&nbsp;</td>
			    </tr>
			    <tr>
			      <td><input name="id" type="hidden" class="span4" value="${user.id}" >&nbsp;</td>
			    </tr>
			    <tr>
			    <td><input name="pwd"  type="hidden" class="span4" value="${user.pwd}" /></td>
			    </tr> --%>
				<tr>
					<td><spring:message code="username" /></td>
					<td><input name="username" type="text"
						class="easyui-validatebox span4" data-options="required:true"
						value="${user.username}" readonly="readonly"></td>
				</tr>
				<tr>
					<td><spring:message code="pwd" /></td>
					<td><input id="pwd" name="pwd" type="password"
						class="easyui-validatebox span4"
						data-options="required:true,validType:'validPWD[6, 32]'"
						value="${user.pwd }"></td>
				</tr>
				<tr>
					<td><spring:message code="name" /></td>
					<td><input name="name" type="text"
						class="easyui-validatebox span4"
						data-options="required:true,validType:'maxLength[20]'"
						value="${user.name}"></td>
				</tr>
				<tr>
					<td><spring:message code="tel" /></td>
					<td><input name="phone" type="text"
						class="easyui-validatebox span4"
						data-options="validType:'tel[20]'" value="${user.phone}"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="email" /></td>
					<td><input name="email" type="text"
						class="easyui-validatebox span4"
						data-options="validType:'email[50]'" value="${user.email }"></td>
				</tr>

				<!-- 				<tr> -->
				<!-- 					<td>联系地址</td> -->
				<%-- 					<td><input name="address" type="text" placeholder="请输入联系地址" class="easyui-validatebox span4" value="${user.address }"></td> --%>
				<!-- 				</tr> -->
			</table>
		</form>
	</div>
</div>