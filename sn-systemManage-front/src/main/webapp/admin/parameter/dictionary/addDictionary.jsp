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
							url : '${pageContext.request.contextPath}/dictionaryController/add',
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
	$
			.extend(
					$.fn.validatebox.defaults.rules,
					{
						//错误编码
						code : {
							validator : function(value, param) {
								var rules = $.fn.validatebox.defaults.rules;
								if (value.length > 2) {
									rules.code.message = '<spring:message code="max_length"/>';
									return false;
								}
								if (!/^[a-zA-Z0-9]{1,2}$/.test(value)) {
									rules.code.message = '<spring:message code="integer_english"/>';
									return false;
								}
								rules.code.message = '';
								return true;
							},
						}
					});
</script>

<div class="easyui-layout" data-options="fit:true,border:false;"
	style="margin-top: 30px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<th><h5></h5></th>
				<tr>
					<td><spring:message code="serial" /></td>
					<td><input name="value" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[20]'"></td>
				</tr>
				<tr>
					<td><spring:message code="bz_code" /></td>
					<td><input name="name" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[100]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="bz_code_name" /></td>
					<td><input id="remark" name="remark" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[100]'"></td>
				</tr>
				<tr>
					<td><spring:message code="code_name_cn" /></td>
					<td><input id="value_cn" name="value_cn" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[100]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="code_name_en" /></td>
					<td><input id="value_en" name="value_en" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[100]'"></td>
				</tr>

			</table>
		</form>
	</div>

</div>