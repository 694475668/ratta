<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
		<style type="text/css">
			td{
			width:90px;
			}
			
		</style>
	</head>
</html>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');

		$('#form').form({
			url : '${pageContext.request.contextPath}/dataDictController/updateColumn',
			onSubmit : function() {
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
				try{
					result = $.parseJSON(result);
				}
				catch(e){
					parent.$.messager.alert('<spring:message code="error"/>', '<spring:message code="req_error"/>', 'error');
					return false;
				}
				if (result.success) {
					parent.$.messager.show({
						title : '<spring:message code="hint"/>',
						msg : result.msg,
						timeout : 5000,
						showType : 'slide'
					});
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('<spring:message code="error"/>', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<input name="table_id" type="hidden" value="${column.table_id }"/>
			<input name="id" type="hidden" value="${column.id }"/>
			<table style="margin:auto">
					<tr>
						<td>字段名</td>
						<td>
							<input name="name" style="margin-top:15px;width:200px;height:14px;" type="text" class="easyui-validatebox span2" data-options="required:true,validType:'maxLength[20]'"
								value="${column.name }"
							 />
						</td>
						<td>类型</td>
					<td>
						<select id="type" class="easyui-combobox"
						name="type" style="width: 215px; "
						data-options="
							    	    valueField:'id',
									    textField:'text',
									    editable:false,
							          ">
							<option value="1" <c:if test="${column.type eq 1 }">selected</c:if>>varchar</option>
							<option value="2" <c:if test="${column.type eq 2 }">selected</c:if>>int</option>
							<option value="3" <c:if test="${column.type eq 3 }">selected</c:if>>blob</option>
							<option value="4" <c:if test="${column.type eq 4 }">selected</c:if>>timestamp</option>
						</select>
					</td>
					
					</tr>
					<tr>
						<td>是否主键</td>
						<td>
								<select id="is_primary" class="easyui-combobox"
									name="is_primary"  style="width: 215px; "
									data-options="valueField:'id',textField:'text',editable:false,">
								<option value="1"  <c:if test="${column.is_primary eq 1 }">selected</c:if>>是</option>
								<option value="2"  <c:if test="${column.is_primary eq 2 }">selected</c:if>>否</option>
							</select>
						</td>
						<td>
					是否自增长
					</td>
					<td>
						<select id="is_auto_increment" class="easyui-combobox"
								name="is_auto_increment" style="width: 215px; "
								data-options="valueField:'id',textField:'text',editable:false,">
							<option value="1"  <c:if test="${column.is_auto_increment eq 1 }">selected</c:if>>是</option>
							<option value="2"  <c:if test="${column.is_auto_increment eq 2 }">selected</c:if>>否</option>
						</select>
					</td>
				
				</tr>
				<tr>
					<td>
						长度
					</td>
					<td>
						<input name="length" style="margin-top:15px;width:200px;height:14px;" type="text" class="easyui-validatebox span2" data-options="validType:'maxLength[20]'"
						value="${column.length }" 
						/>
					</td>
					<td>
						是否可空
					</td>
					<td>
						<select id="is_null" class="easyui-combobox"
								name="is_null" style="width: 215px; "
								data-options="valueField:'id',textField:'text',editable:false,">
							<option value="1"  <c:if test="${column.is_null eq 1 }">selected</c:if>>是</option>
							<option value="2"  <c:if test="${column.is_null eq 2 }">selected</c:if>>否</option>
						</select>
					</td>
					
				</tr>
				<tr><td style="height:8px"></td></tr>
				<tr>
						<td>描述</td>
					<td>
						<textarea name="description" style="width:320px;"  class="span5"  maxlength="100">${column.description }</textarea>
					</td>
					<td>默认值</td>
					<td>
						<input name="default_val" style="margin-top:15px;width:200px;height:14px;" type="text" class="easyui-validatebox span2" data-options="validType:'maxLength[50]'"
						value="${column.default_val }"
						/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>