<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree')
				.tree(
						{
							url : '${pageContext.request.contextPath}/resourceController/allTree',
							parentField : 'pid',
							//lines : true,
							checkbox : true,
							onClick : function(node) {
							},
							onLoadSuccess : function(node, data) {
								var ids = $.stringToList('${role.resourceIds}');
								if (ids.length > 0) {
									for (var i = 0; i < ids.length; i++) {
										if (resourceTree.tree('find', ids[i])) {
											var node = resourceTree.tree(
													'find', ids[i]).target;
											if (resourceTree.tree('isLeaf',
													node)) {
												resourceTree
														.tree('check', node);
											}
										}
									}
								}
								$('#roleGrantLayout').layout('panel', 'west')
										.panel(
												'setTitle',
												$.formatString(
														'[{0}]角色可以访问的资源',
														'${role.name}'));
								parent.$.messager.progress('close');
							},
							cascadeCheck : true
						});

		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/roleController/grant',
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
								var checknodes = resourceTree
										.tree('getChecked');
								var checknodes2 = resourceTree.tree(
										'getChecked', 'indeterminate'); // get indeterminate nodes

								var ids = [];
								if (checknodes && checknodes.length > 0) {
									for (var i = 0; i < checknodes.length; i++) {
										ids.push(checknodes[i].id);
									}
								}
								if (checknodes2 && checknodes2.length > 0) {
									for (var i = 0; i < checknodes2.length; i++) {
										ids.push(checknodes2[i].id);
									}
								}

								$('#resourceIds').val(ids);
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
												msg : result.msg,
												timeout : 5000,
												showType : 'slide'
											});
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为role.jsp页面预定义好了
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

	function checkAll() {
		var nodes = resourceTree.tree('getChecked', 'unchecked');
		if (nodes && nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				resourceTree.tree('check', nodes[i].target);
			}
		}
	}
	function uncheckAll() {
		var nodes = resourceTree.tree('getChecked');
		if (nodes && nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				resourceTree.tree('uncheck', nodes[i].target);
			}
		}
	}
	function checkInverse() {
		var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
		var checknodes = resourceTree.tree('getChecked');
		if (unchecknodes && unchecknodes.length > 0) {
			for (var i = 0; i < unchecknodes.length; i++) {
				resourceTree.tree('check', unchecknodes[i].target);
			}
		}
		if (checknodes && checknodes.length > 0) {
			for (var i = 0; i < checknodes.length; i++) {
				resourceTree.tree('uncheck', checknodes[i].target);
			}
		}
	}
</script>
<div id="roleGrantLayout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'west'"
		title="<spring:message code="system_resources"/>"
		style="width: 300px; padding: 1px;">
		<div class="well well-small">
			<form id="form" method="post">
				<input name="id" type="hidden" class="span2" value="${role.id}"
					readonly="readonly">
				<ul id="resourceTree"></ul>
				<input id="resourceIds" name="resourceIds" type="hidden" />
			</form>
		</div>
	</div>
	<div data-options="region:'center'" title=""
		style="overflow: hidden; padding: 10px;">
		<div class="well well-small">
			<span class="label label-success">${role.name}</span>
			<div>${role.remark}</div>
		</div>
		<div class="well well-large">
			<button class="btn btn-success" onclick="checkAll();">
				<spring:message code="select_all" />
			</button>
			<br /> <br />
			<button class="btn btn-warning" onclick="checkInverse();">
				<spring:message code="invert" />
			</button>
			<br /> <br />
			<button class="btn btn-inverse" onclick="uncheckAll();">
				<spring:message code="cancel" />
			</button>
		</div>
	</div>
</div>