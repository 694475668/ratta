<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>系统参数</title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/systemParaDetailController/editSystemParaDetail')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/systemParaDetailController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/systemParaDetailController/dataGridDetail',
							method : 'post',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : '',
							sortOrder : 'name',
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
							nowrap : false,
							striped : true,
							rownumbers : true,
							singleSelect : true,
							pagination : true,
							columns : [ [
									{
										field : 'id',
										title : '<spring:message code="id"/>',
										width : 200,
										hidden : true
									},
									{
										field : 'serial',
										title : '<spring:message code="serial"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'name',
										title : '<spring:message code="bz_code"/>',
										width : 200,
										align : 'center'
									},
									{
										field : 'value',
										title : '<spring:message code="value"/>',
										width : 200,
										align : 'center'
									},
									{
										field : 'value_cn',
										title : '<spring:message code="param_name"/>',
										width : 300,
										align : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="op_user"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="op_time"/>',
										width : 130,
										align : 'center'
									},
									{
										field : 'action',
										title : '<spring:message code="operate"/>',
										width : 90,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if ($.canEdit) {
												str += $
														.formatString(
																'<img onclick="editDetailFun(\'{0}\');" src="{1}" title="<spring:message code="update"/>"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											}
											str += '&nbsp;';
											if ($.canDelete) {
												str += $
														.formatString(
																'<img onclick="deleteDetailFun(\'{0}\');" src="{1}" title="<spring:message code="delete"/>"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
											}

											return str;
										}
									} ] ],
							toolbar : '#toolbar',

							onLoadSuccess : function() {
								$('#searchTable table').show();
								parent.$.messager.progress('close');
							},
						});
	});

	//添加
	function addDetailFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add"/>',
					scroll : false,
					width : 500,
					height : 350,
					href : '${pageContext.request.contextPath}/systemParaDetailController/addSystemParaDetail',
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//修改
	function editDetailFun(id) {
		parent.$
				.modalDialog({
					title : '<spring:message code="update"/>',
					width : 500,
					height : 350,
					href : '${pageContext.request.contextPath}/systemParaDetailController/editSystemParaDetail?id='
							+ id,
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'database_save',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '<spring:message code="cancel"/>',
						iconCls : 'cancel',
						plain : true,
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//删除
	function deleteDetailFun(id) {
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="delete_message"/>？',
						function(b) {
							if (b) {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.post(
												'${pageContext.request.contextPath}/systemParaDetailController/delete',
												{
													id : id
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														dataGrid
																.datagrid('reload');
													} else {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														dataGrid
																.datagrid('reload');
													}
													parent.$.messager
															.progress('close');
												}, 'JSON');

							}

						});
	}

	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchTable')));
	}

	//清空
	function cleanFun() {
		$('#searchTable input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 102px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchTable">
				<table style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="bz_code" /></td>
					</tr>
					<tr>
						<td><input id="name" name="name" type="text"
							style="height: 15px; width: 200px; margin-top: 10px;"></td>
					</tr>
				</table>
			</form>
		</div>

		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="searchFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true"><spring:message
				code="query" /></a> <a onclick="cleanFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"><spring:message
				code="clear" /></a>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/systemParaDetailController/addSystemParaDetail')}">
			<a onclick="addDetailFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
	</div>
</body>
</html>
