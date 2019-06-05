<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/country-code.js"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/snuserController/editAccount',
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
		getCode();
	});
	//输入框验证
	$.extend($.fn.validatebox.defaults.rules, {
		//手机号码
		mobile : {// 验证手机号码   
			validator : function(value) {
				return /^\d{6,15}$/i.test(value);
			},
			message : '手机号码格式不正确'
		}
	});
	function getCode() {
		var lens = cCodeList.length;
		for (var i = 0; i < lens; i++) {
			var code = cCodeList[i].code;
			var name = cCodeList[i].name;
			$("#countryCode").append(
					"<option value=" + code.replace('+', '') + ">" + code
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + name
							+ "</option>");
		}
	}
</script>



<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 40px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input id="id" name="userid" type="hidden" value="${snUser.userid }">
			<table style="margin: auto">
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="old_telephone" />:</td>
					<td><input name="old_countryCode" type="text"
						class="easyui-validatebox span2" style="width: 60px;" value="${snUser.countryCode }"
						readonly="readonly"><input id="old_telephone" name="old_telephone" type="text"
						class="easyui-validatebox span2" style="width: 180px;" value="${snUser.telephone }"
						readonly="readonly"></td>

				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="new_telephone" />:</td>
					<td><select id="countryCode" name="countryCode"
						style="width: 75px;"></select><input id="telephone"
						name="telephone" class="easyui-validatebox span2"
						style="width: 180px;"
						data-options="required:true,validType:'mobile'" type="text">
					</td>
				</tr>
			</table>
		</form>
	</div>

</div>