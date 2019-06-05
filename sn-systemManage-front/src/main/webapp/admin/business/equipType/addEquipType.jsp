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
							url : '${pageContext.request.contextPath}/equipTypeController/add',
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
		//验证输入必须是最大两位的数字
		number_max_length : {
			validator : function(value) {
				return /^\d{1,2}$/i.test(value);
			},
			message : '必须输入数字，且最大两位'
		}
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false;"
	style="margin-top: 30px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td><spring:message code="type" />：</td>
					<td><input name="type" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[50]'"></td>
				</tr>

				<tr>
					<td style="padding-right: 10px"><spring:message
							code="sn_length" />：</td>
					<td><input id="sn_length" name="sn_length" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'number_max_length'">
					</td>
				</tr>
				<tr>
					<td><spring:message code="sn_front" />：</td>
					<td><input name="sn_front" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[30]'"></td>
				</tr>
				<tr>
					<td><spring:message code="sn_offset" />：</td>
					<td><input id="sn_offset" name="sn_offset" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'number_max_length'">
					</td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="sn_offset_length" />：</td>
					<td><input id="sn_offset_length" name="sn_offset_length"
						type="text" class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'number_max_length'">
					</td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="specification_briefly" />：</td>
					<td><input name="remark" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="validType:'maxLength[100]'"></td>
				</tr>

			</table>
		</form>
	</div>

</div>