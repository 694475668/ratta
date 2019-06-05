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
							url : '${pageContext.request.contextPath}/systemParaDetailController/editDetail',
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
		//排序号
		pxh : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				if (!/^[0-9]+$/.test(value)) {
					rules.pxh.message = '<spring:message code="integer"/>';
					return false;
				}
				rules.pxh.message = '';
				return true;
			}
		}
	});
</script>



<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 20px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input id="id" name="id" type="hidden" value="${reference.id }">
			<table style="margin: auto">
				<th><h5></h5></th>
				<tr>
					<td><spring:message code="bz_code" /></td>
					<td><input id="name" name="name" type="text"
						class="easyui-validatebox span2" style="width: 200px;"
						value="${reference.name }" readonly="readonly"></td>

				</tr>
				<tr>
					<td><spring:message code="serial" /></td>
					<td><input id="serial" name="serial" type="text"
						class="easyui-validatebox span2" style="width: 200px;"
						value="${reference.serial }" readonly="readonly"></td>

				</tr>
				<tr id="value2">
					<td><spring:message code="value" /></td>
					<td><input id="value" name="value"
						class="easyui-validatebox span2" style="width: 200px;"
						data-options="required:true,validType:'maxLength[100]'"
						value="${reference.value }" type="text"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="param_name" /></td>
					<td><input name="value_cn" type="text"
						class="easyui-validatebox span2" style="width: 200px;"
						data-options="required:true,validType:'maxLength[100]'"
						value="${reference.value_cn }"></td>
				</tr>
			</table>
		</form>
	</div>

</div>