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
							url : '${pageContext.request.contextPath}/stockController/return',
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

		$("#healthyState")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIPMENT_HEALTHY_STATE',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 30,
							valueField : 'value',
							textField : 'value_cn'
						});

	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false;"
	style="margin-top: 20px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="equipment_no" />:</td>
					<td><input name="equipment_no" style="width: 200px;"
						value="${equipment_no}" type="text" readonly></td>
				</tr>
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="healthy_state" />:</td>
					<td><input id="healthyState" name="healthyState"
						style="width: 214px;" type="text" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="serial_number" />:</td>
					<td><input id="serrialNumber" name="serrialNumber" type="text"
						class="easyui-validatebox span2"
						style="width: 200px; margin-top: 10px;"
						data-options="required:true,validType:'maxLength[20]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="return_time" />:</td>
					<td><input name="returnTime" type="text"
						class="easyui-datebox" style="width: 214px; height: 30px;"
						data-options="required:true,editable:false"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="business_handler" />:</td>
					<td><input name="businessHandler" type="text"
						class="easyui-validatebox span2"
						style="width: 200px; margin-top: 10px;"
						data-options="required:true,validType:'maxLength[10]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="remark" />:</td>
					<td><input name="remark" type="text"
						class="easyui-validatebox span2" style="width: 200px;"
						data-options="validType:'maxLength[255]'"></td>
				</tr>
			</table>
		</form>
	</div>

</div>