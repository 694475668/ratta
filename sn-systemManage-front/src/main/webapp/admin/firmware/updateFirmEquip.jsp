<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 30px;">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="version" />：</td>
					<td><input id="version" name="version" type="text"
						style="height: 15px; width: 250px; margin-top: 10px;" readonly />
					</td>
				</tr>
				<tr>
					<td style="padding-right: 10px; padding-bottom: 30px;"><spring:message
							code="equipment_model" />：</td>
					<td>
						<div style="display: flex; flex-wrap: wrap; width: 500px;">
							<div id="combox"></div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>

</div>

<script type="text/javascript">
	var typeIds = new Array();
	var types = new Array();
	var version = '${version}';
	$('#version').val(version);
	$(function() {
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/firmwareEquipController/updateFirmEquip',
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
								alert(typeIds.length);
								if (typeIds.length == 1) {
									alert(typeIds.length);
									if (!isValid) {
										parent.$.messager.progress('close');
									} else {
										alert(typeIds.length);
										if (typeIds.length == 1) {
											parent.$.messager.progress('close');
											$.messager.alert('提示', '请选择设备型号');
											return false;
										}
									}
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

		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/firmwareEquipController/queryNoPage",
					data : {
						version : version
					},
					dataType : "json",
					async : false,
					success : function(data) {
						if (data != null && data != "") {
							for (var i = 0; i < data.length; i++) {
								var data_data = data[i];
								types[i] = data_data.equipment_model;
							}

						}
					}
				});

		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/equipTypeController/queryNoPage",
					data : {},
					dataType : "json",
					success : function(data) {
						if (data != null && data != "") {
							for (var i = 0; i < data.length; i++) {
								var data_data = data[i];
								$("#combox")
										.append(
												'<div style="flex-grow: 1;width: 250px;float:left;">'
														+ '<input id="combox'
														+ i
														+ '" name="checkbo" onclick ="comboxclick('
														+ i
														+ ')" value="'
														+ data_data.id
														+ '" type="checkbox" class="combobox-checkbox" style="vertical-align:middle;margin:0">'
														+ '&nbsp;&nbsp;&nbsp;'
														+ data_data.type)
										+ '</div>';
								for (var j = 0; j < types.length; j++) {
									if (types[j] == data_data.id) {
										$('#combox' + i).attr("checked", 'true');
										$('#combox' + i).attr("disabled", 'disabled');
										comboxclick(i);
									}
								}

							}

						}
					}
				});

	});

	//复选框单选事件
	function comboxclick(i) {
		if ($('#combox' + i).prop("checked")) {
			typeIds[typeIds.length] = $('#combox' + i).attr('value');
		} else {
			for (var j = 0; j < typeIds.length; j++) {
				if (typeIds[j] == $('#combox' + i).attr('value')) {
					typeIds.splice(j, 1);
					break;
				}
			}
		}
	}
</script>
