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
							url : '${pageContext.request.contextPath}/dealersController/add',
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
		
		$("#dealersType").combobox(
				{
					url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
							+ 'DEALERS_TYPE',
					required : true,
					editable : false, //不可编辑状态
					cache : false,
					panelHeight : 'auto',
					height : 30,
					width : 394,
					valueField : 'value',
					textField : 'value_cn'
		});
	});

	//输入框验证
	$.extend($.fn.validatebox.defaults.rules, {
		//手机号码
		mobile : {// 验证手机号码
			validator : function(value) {
				return /^(13|15|16|17|18|19)\d{9}$/i.test(value);
			},
			message : '手机号码格式不正确'
		}
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false;"
	style="margin-top: 10px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<th><h5></h5></th>
				<tr style="height: 54px;">
					<td><spring:message code="dealers_type" />：</td>
					<td><input id="dealersType" name="dealersType" type="text"
						data-options="required:true" ></td>
				</tr>
				<tr>
					<td><spring:message code="dealers_name" />：</td>
					<td><input name="dealersName" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[64]'"></td>
				</tr>
				<tr>
					<td><spring:message code="contact" />：</td>
					<td><input name="contact" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[24]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="phone" />：</td>
					<td><input id="phone" name="phone" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'mobile'"></td>
				</tr>
				<tr>
					<td><spring:message code="warehouse_address" />：</td>
					<td><input id="address" name="address" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[225]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="salesman" />：</td>
					<td><input id="salesman" name="salesman" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[32]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="apply_time" />：</td>
					<td><input id="applicationTime" name="applicationTime"
						type="text" class="easyui-datebox"
						style="width: 394px; height: 30px;"
						data-options="required:true,editable:false"></td>
				</tr>
                
				<tr style="height: 60px;">
					<td style="padding-right: 10px"><spring:message
							code="remark" />：</td>
					<td><input id="remark" name="remark" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="validType:'maxLength[100]'"></td>
				</tr>
			</table>
		</form>
	</div>

</div>