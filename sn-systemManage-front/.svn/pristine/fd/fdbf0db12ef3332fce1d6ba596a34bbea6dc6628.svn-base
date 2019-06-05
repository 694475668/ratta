<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$("#equipment_model")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/equipTypeController/queryNoPage',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 300,
							height : 30,
							width : 394,
							valueField : 'id',
							textField : 'type'
						});
	});

	// 导入
	function importExcel() {
		var equipment_model = $("#equipment_model").combogrid('getValue');
		var note = $("#note").val();
		if (equipment_model == null || equipment_model == "") {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_the_equipment_type"/>！',
					'info');
			$('#file').val('');
			return false;
		}
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});

		$
				.ajaxFileUpload({
					url : '${pageContext.request.contextPath}/inOutStockController/import',
					type : 'post',
					secureuri : false,
					fileElementId : 'file',
					dataType : 'JSON',
					data : {
						equipment_model : equipment_model,
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
						if (data.success) {
						}
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
				<tr style="height: 54px;">
					<td style="padding-right: 10px"><spring:message
							code="equipment_model" />:</td>
					<td><input id="equipment_model" name="equipment_model"
						type="text"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="remark" />:</td>
					<td><input id="note" name="note" type="text"
						style="width: 380px;"></td>
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