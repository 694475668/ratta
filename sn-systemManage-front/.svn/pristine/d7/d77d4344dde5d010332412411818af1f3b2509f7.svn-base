<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	var sum_counts = "${inOutStock.counts}";
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form(
						{
							url : '${pageContext.request.contextPath}/inOutStockController/out',
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
	//查询所有的经销商
	$(function() {
		$("#dealers_name").combobox(
						{
							url : '${pageContext.request.contextPath}/dealersController/queryNoPage',
							editable : false, //不可编辑状态
							cache : false,
							required : true,
							panelHeight : 'auto',
							height : 30,
							width : 394,
							valueField : 'id',
							textField : 'dealersName',
							onSelect : selectDealerInfoByDealerName
						});
		
		$("#equipment_purpose").combobox(
				{
					url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
							+ 'EQUIPMENT_PURPOSE',
					editable : false, //不可编辑状态
					cache : false,
					required : true,
					panelHeight : 'auto',
					height : 30,
					width : 394,
					valueField : 'value',
					textField : 'value_cn'
		});
	});
	
	function selectDealerInfoByDealerName(){
		var id = $("#dealers_name").combobox('getValue');
		$.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/dealersController/queryDearlerById",
            data: {id:id},
            dataType: "json",
            success: function(data){
            	$("#contact").val(data.contact);
            	$("#phone").val(data.phone);
            	$("#address").val(data.address);
            }
        });
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false;" style="margin-top: 20px;">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
			    <tr  style="height: 34px;">
					<td style="padding-right: 10px"><spring:message code="equipment_purpose" />:</td>
					<td><input id="equipment_purpose" name="equipment_purpose"/>
					</td>
				</tr>
				<tr  style="height: 54px;">
					<td style="padding-right: 10px;"><spring:message code="dealers_name" />:</td>
					<td><input id="dealers_name" name="dealers_name" ></td>
				</tr>
				<tr style="padding-top:10px;">
					<td style="padding-right: 10px;"><spring:message code="contact" />:</td>
					<td><input id="contact" name="contact" type="text"
					style="width: 380px;" readOnly="readOnly"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px;"><spring:message code="phone" />:</td>
					<td><input id="phone" name="phone" type="text"
					style="width: 380px;" readOnly="readOnly"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px;"><spring:message code="warehouse_address" />:</td>
					<td><input id="address" name="address" type="text"
					style="width: 380px;" readOnly="readOnly"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="equipment_no" />:</td>
					<td><input id="equipment_no" name="equipment_no" type="text" style="width: 380px;"
						class="easyui-validatebox span2"  data-options="required:true,validType:'maxLength[30]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="firmware_version" />:</td>
					<td><input id="firmware_version" name="firmware_version" type="text" style="width: 380px;"
						class="easyui-validatebox span2"  data-options="required:true,validType:'maxLength[50]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="remark" />:</td>
					<td><input name="note" type="text" style="width: 380px;"
						data-options="validType:'maxLength[100]'"></td>
				</tr>
			</table>
		</form>
	</div>

</div>