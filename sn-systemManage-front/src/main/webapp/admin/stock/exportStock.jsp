<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
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
	
	// 批出
	function importExcel() {
		var dealers_name = $("#dealers_name").combobox('getValue');
		var equipment_purpose = $("#equipment_purpose").combobox('getValue');
		var note = $("#note").val();
		if (equipment_purpose == null || equipment_purpose == "") {
			$.messager.alert('<spring:message code="hint"/>','<spring:message code="select_the_equipment_purpose"/>！','info');
			$('#file').val('');
			return false;
		}
		if (dealers_name == null || dealers_name == "") {
			$.messager.alert('<spring:message code="hint"/>','<spring:message code="select_the_dealer"/>！','info');
			$('#file').val('');
			return false;
		}
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});

		$.ajaxFileUpload({
					url : '${pageContext.request.contextPath}/inOutStockController/batchOut',
					type : 'post',
					secureuri : false,
					fileElementId : 'file',
					dataType : 'JSON',
					data : {
						dealers_name : dealers_name,
						equipment_purpose : equipment_purpose,
						note : note
					},
					success : function(data, status) {
						parent.$.messager.progress('close');
						try {//Json解析出错时，异常处理。
							//转化为JSON对象
							data = JSON.parse(data);
						} catch (e) {
							parent.$.messager.alert(
									'<spring:message code="error"/>',
									'<spring:message code="req_error"/>',
									'error');
							return false;
						}
						$.messager.alert('<spring:message code="hint"/>',
								data.msg, 'info');
						$("#file").val("");
					},
				});
		return false;
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 20px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<th><h5></h5></th>
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
					<td style="padding-right: 10px"><spring:message code="remark" />:</td>
					<td><input id="note" name="note" type="text" style="width: 380px;"
						data-options="validType:'maxLength[100]'"></td>
				</tr>
				<tr>
					<td><spring:message code="stock_import" />:</td>
					<td><a href="javascript:;" class="file"> <spring:message
								code="file_select" /> <input type="file" name="file" id="file"
							accept=".xlsx" onchange="importExcel()" />
					</a></td>
				</tr>
			</table>
		</form>
	</div>

</div>