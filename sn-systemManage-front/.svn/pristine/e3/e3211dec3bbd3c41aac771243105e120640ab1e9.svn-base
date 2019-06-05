<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	parent.$.messager.progress('close');
	$(function() {
		//添加 CK编辑器
		var editor = CKEDITOR.instances['remark'];
		if (editor) {
			delete CKEDITOR.instances['remark'];
		}
		CKEDITOR.replace('remark', {
			height : '410px',
			width : '850px',
			language : 'zh-cn'
		});
	});
</script>
<div style="overflow: hidden;">
	<table
		style="margin: 10px; border-collapse: separate; border-spacing: 0px 10px;">
		<tr>
			<td><spring:message code="logName" />： <input name="id"
				type="hidden" value="${equipmentLog.id}">${equipmentLog.logName}</td>
		</tr>
	</table>
	<textarea id="remark" name="remark" readonly>${equipmentLog.remark}</textarea>
</div>
