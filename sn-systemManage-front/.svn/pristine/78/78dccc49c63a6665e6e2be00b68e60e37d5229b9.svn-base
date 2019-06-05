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
							url : '${pageContext.request.contextPath}/inOutStockController/in',
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
	$(function() {
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

	//计算入库截止序号
	//判断sn长度是否合法
	//判断sn头是否一致
	//检验偏移--偏移长度之间的值是否是数字
	function showQuantity() {
		var sn_front;
		var sn_length;
		var sn_offset;
		var sn_offset_length;

		var counts = $("#counts").val(); //入库数量
		var begin_sn = $.trim($("#begin_sn").val()); //开始序号 
		var equipment_model = $('#equipment_model').combogrid('getValue');
		if (counts != '' && begin_sn != '') {
			parent.$.messager.progress({
				title : '<spring:message code="hint"/>',
				text : '<spring:message code="please_latter"/>'
			});

			$
					.post(
							'${pageContext.request.contextPath}/equipTypeController/queryById',
							{
								equipment_model : equipment_model
							},
							function(result) {
								if (result.success) {
									var g_info;
									try {//Json解析出错时，异常处理。
										g_info = $.parseJSON(result.obj);
									} catch (e) {
										parent.$.messager
												.alert(
														'<spring:message code="error"/>',
														'<spring:message code="req_error"/>',
														'error');
										return false;
									}
									sn_front = g_info.sn_front;
									sn_length = g_info.sn_length;
									sn_offset = g_info.sn_offset;
									sn_offset_length = g_info.sn_offset_length;

									if (sn_length == undefined
											|| isNaN(sn_length)
											|| sn_offset == undefined
											|| isNaN(sn_offset)
											|| sn_offset_length == undefined
											|| isNaN(sn_offset_length)) {
										parent.$.messager.progress('close');
										$.messager
												.alert(
														'<spring:message code="hint"/>',
														'<spring:message code="off_len_error"/>',
														'info');
										return false;
									}
									var head = begin_sn.substring(0,
											sn_front.length);
									var middle = begin_sn.substring(
											head.length, sn_offset - 1);
									var body = begin_sn.substring(
											sn_offset - 1, sn_offset - 1
													+ sn_offset_length);
									var end = begin_sn.substring(sn_offset - 1
											+ sn_offset_length);
									var temp_sn = parseInt(body)
											+ parseInt(counts) - 1;
									if (temp_sn.toString().length > body.length) {
										$.messager
												.alert(
														'<spring:message code="hint"/>',
														'<spring:message code="count_max"/>');
										parent.$.messager.progress('close');
										return false;
									}
									if (sn_length != begin_sn.length) {
										$.messager
												.alert(
														'<spring:message code="hint"/>',
														'<spring:message code="error_generate_sn"/>');
										parent.$.messager.progress('close');
										return false;
									}

									if (sn_front != head) {
										$.messager
												.alert(
														'<spring:message code="hint"/>',
														'<spring:message code="error_generate_sn"/>');
										parent.$.messager.progress('close');
										return false;
									}
									if (temp_sn.toString().length > sn_offset_length) {
										parent.$.messager.progress('close');
										return false;
									}
									var padding = '';
									for (var i = 0; i < sn_offset_length
											- temp_sn.toString().length; i++) {
										padding += '0';
									}

									body_str = padding + temp_sn;
									$("#end_sn").val(
											head + middle + body_str + end);
									$("#begin_sn").val(begin_sn);
								} else {
									parent.$.messager
											.show({
												title : '<spring:message code="hint"/>',
												timeout : 3000,
												msg : result.msg
											});
								}
								parent.$.messager.progress('close');
							}, 'JSON');
		}
		return false;
	}

	//输入框验证
	$.extend($.fn.validatebox.defaults.rules, {
		number : {
			validator : function(value) {
				return /^(\+?[1-9]\d{0,2}|\+?1000)$/i.test(value);
			},
			message : '必须输入[1-1000]的正整数'
		}
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false;"
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
						type="text" data-options="required:true"></td>
				</tr>

				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="batch_no" />:</td>
					<td><input id="batch_no" name="batch_no" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[30]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="firmware_version" />:</td>
					<td><input name="firmware_version" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[50]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="counts" />:</td>
					<td><input id="counts" name="counts" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'number'"
						onblur="showQuantity()"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message
							code="begin_sn" />:</td>
					<td><input id="begin_sn" name="begin_sn" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true,validType:'maxLength[30]'"
						onblur="showQuantity()"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="end_sn" />:</td>
					<td><input id="end_sn" name="end_sn" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="required:true" readonly="readonly"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px"><spring:message code="remark" />:</td>
					<td><input name="note" type="text"
						class="easyui-validatebox span2" style="width: 380px;"
						data-options="validType:'maxLength[100]'"></td>
				</tr>
			</table>
		</form>
	</div>

</div>