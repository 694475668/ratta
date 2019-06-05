<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	var typeIds = new Array();
	var name = null;
	$(function() {
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/firmwareController/firmwareUpload',
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
								if (typeIds.length == 0 || name == null
										|| name == '' || name == "null") {
									console.log(name);
									if (!isValid) {
										parent.$.messager.progress('close');
									} else {
										if (typeIds.length == 0) {
											parent.$.messager.progress('close');
											$.messager.alert('提示', '请选择设备型号');
											return false;
										} else if (name == null || name == ''
												|| name == "null") {
											parent.$.messager.progress('close');
											$.messager.alert('提示', '请选择文件');
											return false;
										}

									}
								}
								return isValid;
							},
							success : function(result) {
								parent.$.messager.progress('close');
								try {//Json解析出错时，异常处理。
									console.log(result);
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

		$
				.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/equipTypeController/queryNoPage",
					data : {},
					dataType : "json",
					success : function(data) {
						if (data != null && data != "") {
							for (var i = 0; i < data.length; i++) {
								var data_data = data[i];
								console.log(data_data.type)
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
							}

						}
					}
				});
	});
	function ajaxFileUpload() {
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		var file = document.getElementById('file');
		var fileName = file.files[0].name;
		var password = document.getElementById("password").value;
		$
				.ajaxFileUpload({
					url : '${pageContext.request.contextPath}/firmwareController/uploadFirmware',
					type : 'post',
					secureuri : false,
					fileElementId : 'file',
					dataType : 'JSON',
					data : {
						'password' : password
					},
					success : function(data, status) {
						//去掉<pre></pre>标签
						var start = data.indexOf(">");
						if (start != -1) {
							var end = data.indexOf("<", start + 1);
							if (end != -1) {
								data = data.substring(start + 1, end);
							}
						}
						//转化为JSON对象
						data = eval('(' + data + ')');

						parent.$.messager.progress('close');
						if (data.success) {
							$("#file_Path").val(data.errorinfo);
							name = data.errorinfo;
							document.getElementById("filezip").setAttribute(
									"disabled", true);
							document.getElementById("filezip").value = fileName;
							$.messager.alert('<spring:message code="hint"/>',
									data.msg, 'info');
						} else {
							$.messager.alert('<spring:message code="hint"/>',
									data.msg, 'info');
						}
					},
				});
		$('#file').val('');
		return false;
	}

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
	function upload() {
		var password = document.getElementById("password").value;
		if (password == null || password == '' || password == "null") {
			parent.$.messager.progress('close');
			$.messager.alert('提示', '请输入密码');
			return false;
		}
		$('#file').click();
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 30px;">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td><spring:message code="psw" />：</td>
					<td><input id="password" name="password" type="password"
						class="easyui-validatebox span2" style="width: 400px;"
						data-options="required:true,validType:'maxLength[20]'"></td>
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
				<tr>
					<td><spring:message code="FireWareZip" />：</td>
					<td><input type=button onclick='upload()' value="选择文件"
						id="filezip"> <input type="file" name="file" id="file"
						style='display: none' onchange="ajaxFileUpload()" accept=".zip" />
						<input type="hidden" name="file_Path" id="file_Path" /></td>
				</tr>

			</table>
		</form>
	</div>

</div>