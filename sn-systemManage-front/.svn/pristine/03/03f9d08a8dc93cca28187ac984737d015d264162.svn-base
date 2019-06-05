<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {
		$('#iconCls')
				.combobox(
						{
							data : $.iconData,
							formatter : function(v) {
								return $
										.formatString(
												'<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}',
												v.value, v.value);
							},
							value : '${resource.iconCls}'
						});

		$('#pid').combotree({
			url : '${pageContext.request.contextPath}/resourceController/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value : '${resource.pid}',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});

		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/resourceController/edit',
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
								try {
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
									parent.$.messager
											.show({
												title : '<spring:message code="hint"/>',
												timeout : 3000,
												msg : result.msg
											});
									parent.$.modalDialog.openner_treeGrid
											.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
									parent.layout_west_tree.tree('reload');
									parent.$.modalDialog.handler
											.dialog('close');
								}
							}
						});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 20px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="resource_name" /><input name="id" type="hidden"
						value="${resource.id}" readonly="readonly"></td>
					<td colspan="3"><input name="name" style="width: 400px;"
						type="text" placeholder="请输入资源名称" class="easyui-validatebox span2"
						data-options="required:true,validType:'maxLength[30]'"
						value="${resource.name}"></td>
				</tr>
				<tr>
					<td><spring:message code="resource_url" /></td>
					<td colspan="3"><input name="url" style="width: 400px;"
						type="text" placeholder="请输入资源路径" class="easyui-validatebox span2"
						value="${resource.url}" data-options="validType:'maxLength[100]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="typeName" /></td>
					<td><select name="typeId" class="easyui-combobox"
						data-options="width:178,height:24,editable:false,panelHeight:'auto'">
							<c:forEach items="${resourceTypeList}" var="resourceType">
								<option value="${resourceType.id}"
									<c:if test="${resourceType.id == resource.typeId}">selected="selected"</c:if>>${resourceType.name}</option>
							</c:forEach>
					</select></td>
					<td style="padding-left: 16px;"><spring:message code="seq" /></td>
					<td><input name="seq" value="${resource.seq}"
						class="easyui-numberspinner" style="width: 159px; height: 24px;"
						required="required" data-options="editable:false,min:100"></td>
				</tr>
				<tr style="height: 10px;">
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><spring:message code="pname" /></td>
					<td><select id="pid" name="pid" style="width: 178px;"></select>
					</td>
					<td style="padding-right: 10px; padding-left: 16px;"><spring:message
							code="icon" /></td>
					<td colspan="3"><input id="iconCls" name="iconCls"
						style="width: 159px; height: 24px;" data-options="editable:false" /></td>
				</tr>
				<tr style="height: 10px;">
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><spring:message code="remark" /></td>
					<td colspan="3"><textarea name="remark" style="width: 400px;"
							maxLength="70" class="span5">${resource.remark}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>
