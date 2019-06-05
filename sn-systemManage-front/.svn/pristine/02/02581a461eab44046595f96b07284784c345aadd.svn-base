<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style type="text/css">
table tr td {
	height: 14px;
}
</style>
<script type="text/javascript">
	var g;
	$(function() {
		parent.$.messager.progress('close');
		$('#iconCls')
				.combobox(
						{
							data : $.iconData,
							formatter : function(v) {
								return $
										.formatString(
												'<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}',
												v.value, v.value);
							}
						});

		$('#pid').combogrid({
			url : '',
			valueField : 'id',
			textField : 'name',
			panelWidth : 500,
			panelHeight : 300
		});

		loadResourceGrid();

		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/resourceController/add',
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

	// 加载资源管理 Grid
	// @Troy 2014-08-11 10:07:57 
	function loadResourceGrid() {
		g = $("#pid").combogrid('grid');
		g
				.treegrid({
					url : '${pageContext.request.contextPath}/resourceController/treeGrid',
					idField : 'id',
					textField : 'id',
					treeField : 'name',
					parentField : 'pid',
					fit : true,
					fitColumns : false,
					border : false,
					frozenColumns : [ [ {
						title : '<spring:message code="id"/>',
						field : 'id',
						width : 150,
						hidden : true
					} ] ],
					columns : [ [ {
						field : 'name',
						title : '<spring:message code="resource_name"/>',
						width : 200
					}, {
						field : 'pname',
						title : '<spring:message code="pname"/>',
						width : 80
					}, {
						field : 'remark',
						title : '<spring:message code="remark"/>',
						width : 180
					} ] ],
					toolbar : '#toolbar',
					onContextMenu : function(e, row) {
						e.preventDefault();
						$(this).treegrid('unselectAll');
						$(this).treegrid('select', row.id);
						$('#menu').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
					},
					onLoadSuccess : function() {
						parent.$.messager.progress('close');

						$(this).treegrid('tooltip');
					},
					onDblClickCell : function(field, row) {
						//grid 双击事件
						// 动态绑定 grid 数值到 combo 中
						$("#pid").combogrid('setValue', row.id);
						$("#pid").combogrid('setText', row.name);
					}
				});
	}

	//折叠所有 treeGrid
	//@Troy 2014-08-11 12:42:44
	function redo() {
		var node = g.treegrid('getSelected');
		if (node) {
			g.treegrid('expandAll', node.id);
		} else {
			g.treegrid('expandAll');
		}
	}

	//展开所有 treeGrid
	//@Troy 2014-08-11 12:42:56
	function undo() {
		var node = g.treegrid('getSelected');
		if (node) {
			g.treegrid('collapseAll', node.id);
		} else {
			g.treegrid('collapseAll');
		}
	}

	//刷新 grid
	// @Troy 2014-08-11 12:54:41
	function fresh() {
		g.treegrid('reload');
		$("#source_name").val("");
	}

	//响应查询按钮
	// @Troy 2014-08-11 12:56:07
	function responseSearch() {

		var source_name = $("#source_name").val();
		g
				.treegrid({
					url : '${pageContext.request.contextPath}/resourceController/treeGrid',
					queryParams : {
						source_name : source_name
					},
					method : "post"
				});

	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin-top: 20px;">
	<div id="toolbar" style="display: none;">
		<span><spring:message code="resource_name" /></span> <input
			id="source_name" class="easyui-validatebox" type="text" value="" /> <a
			onclick="responseSearch()" id="btn" href="#"
			class="easyui-linkbutton" data-options="iconCls:'icon-search'"><spring:message
				code="query" /></a> <a onclick="redo();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'resultset_next'"><spring:message
				code="spread" /></a> <a onclick="undo();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'resultset_previous'"><spring:message
				code="fold" /></a>
	</div>


	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin: auto">
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="resource_name" /><input name="id" type="hidden"
						value="${resource.id}" readonly="readonly"></td>
					<td colspan="3"><input name="name" style="width: 400px;"
						type="text" class="easyui-validatebox span2"
						data-options="required:true,validType:'maxLength[30]'" value=""></td>
				</tr>
				<tr>
					<td><spring:message code="resource_url" /></td>
					<td colspan="3"><input name="url" style="width: 400px;"
						type="text" class="easyui-validatebox span2"
						data-options="validType:'maxLength[100]'"></td>
				</tr>
				<tr>
					<td style="padding-right: 10px;"><spring:message
							code="typeName" /></td>
					<td><select name="typeId" class="easyui-combobox"
						data-options="width:178,height:24,editable:false,panelHeight:'auto'">
							<c:forEach items="${resourceTypeList}" var="resourceType">
								<option value="${resourceType.id}">${resourceType.name}</option>
							</c:forEach>
					</select></td>
					<td style="padding-left: 16px;"><spring:message code="seq" /></td>
					<td><input name="seq" value="100" class="easyui-numberspinner"
						style="width: 159px; height: 24px;" required="required"
						data-options="editable:false,min:100"></td>

				</tr>
				<tr>
					<td colspan="4" style="height: 10px;"></td>
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
				<tr>
					<td colspan="4" style="height: 10px;"></td>
				</tr>
				<tr>
					<td><spring:message code="remark" /></td>
					<td colspan="3"><textarea name="remark" style="width: 400px;"
							rows="" cols="" class="span5" maxLength="70"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>