<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/appVersionController/add',
			onSubmit : function() {	
				 if($("#fileName").val()==null||$("#fileName").val()==""){
					 $.messager.alert('<spring:message code="hint"/>', '<spring:message code="click_upload"/>！', 'info');
			         return false;
				 }

				parent.$.messager.progress({
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
				try{//Json解析出错时，异常处理。
					result = $.parseJSON(result);
				}
				catch(e){
					parent.$.messager.alert('<spring:message code="error"/>', '<spring:message code="req_error"/>', 'error');
					return false;
				}
				if (result.success) {
					$.messager.show({
						title:'<spring:message code="hint"/>',
						msg:result.msg,
						timeout:5000,
						showType:'slide'
					});
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('<spring:message code="error"/>', result.msg, 'error');
				}
			}
		});
	});
	
	//版本文件上传
	function ajaxFileUpload() {
    	parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});        	
		$.ajaxFileUpload({
			url : '${pageContext.request.contextPath}/appVersionController/upload',
			type : 'post',
			secureuri:false,
			fileElementId : 'file',
			dataType : 'JSON',
			data:{},
			success : function(data, status) {
				parent.$.messager.progress('close');
		         //转化为JSON对象
		        data=eval('('+data+')');
		        $("#appName").val(data.errorinfo); //文件路径
		        if(data.verinfo!="null"){
		        	$("#appVersion").val(data.verinfo);  //版本号
		        }
		        if(data.obj!="null"){
		        	$("#packageName").val(data.obj);  //包名
		        }
		        $.messager.alert('<spring:message code="hint"/>', data.msg, 'info');
				if (data.success) {
					$("#fileName").val(data.iconinfo); 
				}
				$('#file').val('');
			},
		});
		return false;
	
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<th><h5></h5></th>
				<tr>
					<td><spring:message code="is_install"/>：</td>
					<td><select name="updateFlag" data-options="required:true" style="width:297px;">
                            <option value ="0"><spring:message code="no_force"/></option>
                            <option value ="1"><spring:message code="force"/></option>
                        </select>
					</td>
				</tr>
				<tr>
					<td><spring:message code="ver_upload"/>：</td>
					<td>
					<a href="javascript:;" class="file">
						<spring:message code="file_select"/>
    					<input type="file" name="file" id="file"  onchange="ajaxFileUpload();"/>
					</a>
					</td>
				</tr>
				<tr>
					<td><spring:message code="file_name"/>：</td>
					<td><input name="fileName" id ="fileName" type="text" style="width:280px;" readOnly="readOnly"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code="app_name"/>：</td>
					<td><input id="appName" name="appName" type="text" style="width:280px;"  readOnly="readOnly"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code="version"/>：</td>
					<td><input id="appVersion" name="appVersion" type="text" style="width:280px;"  readOnly="readOnly"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code="package_name"/>：</td>
					<td><input id="packageName" name="packageName" type="text" style="width:280px;"  readOnly="readOnly"/></td>
				</tr>
			</table>
		</form>
	</div>
</div>