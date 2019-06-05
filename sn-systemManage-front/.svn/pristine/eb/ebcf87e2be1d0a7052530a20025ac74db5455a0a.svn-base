<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');

		$('#form').form({
			url : '${pageContext.request.contextPath}/dataDictController/updateTable',
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
			<table style="margin:auto">
				<tr>
					<input id="id" name="id" type="hidden" value="${table.id }"/>
					<td>表名</td>
					<td><input name="name" style="margin-top:15px;width:320px;height:14px;" type="text" class="easyui-validatebox span2" data-options="required:true,validType:'maxLength[50]'"
						value="${table.name }"
					 /></td>
					</tr>
					<tr>
					<td>类型</td>
					<td>
						<select id="type" class="easyui-combobox"
						name="type" style="width: 215px; "
						data-options="
							    	    valueField:'id',
									    textField:'text',
									    editable:false,
							          ">
							<option value="1" <c:if test="${table.type eq 1 }">selected</c:if>>表</option>
							<option value="2" <c:if test="${table.type eq 2 }">selected</c:if>>视图</option>
							<option value="3" <c:if test="${table.type eq 3 }">selected</c:if>>函数</option>
							<option value="4" <c:if test="${table.type eq 4 }">selected</c:if>>触发器</option>
						</select>
					</td>
				</tr>
				<tr>
				</tr>
				<tr><td style="height:8px"></td></tr>
				<tr>
					<td>描述</td>
					<td>
					<textarea name="description" style="width:320px;"  class="span5"  maxlength="100">${table.description }</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>