<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>设备型号管理</title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/equipTypeController/dataGrid',
							method : 'post',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : '',
							sortOrder : 'desc',
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
										title : '<spring:message code="id"/>',
										field : 'id',
										width : 150,
										hidden : true
									},
									{
										field : 'type',
										title : '<spring:message code="type"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'sn_front',
										title : '<spring:message code="sn_front"/>',
										width : 160,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'sn_length',
										title : '<spring:message code="sn_length"/>',
										width : 80,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'sn_offset',
										title : '<spring:message code="sn_offset"/>',
										width : 80,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'sn_offset_length',
										title : '<spring:message code="sn_offset_length"/>',
										width : 80,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'remark',
										title : '<spring:message code="specification_briefly"/>',
										width : 240,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="op_user"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="op_time"/>',
										width : 200,
										align : 'center',
										halign : 'center'
									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');

								var rows = $("#dataGrid").datagrid('getRows');
								if (rows.length == 0) {
									$.messager
											.show({
												title : 'Tip',
												msg : '<spring:message code="no_data"/>',
												timeout : 3000,
												showType : 'slide'
											});
								}
							}

						});
	});

	//添加
	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add"/>',
					width : 600,
					height : 400,
					//method: 'get',
					href : '${pageContext.request.contextPath}/equipTypeController/addEquipType',
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
	function editFun() {
		var row = dataGrid.datagrid('getSelected');

		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="update_message"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="update"/>',
					width : 600,
					height : 400,
					href : '${pageContext.request.contextPath}/equipTypeController/editEquipType?id='
							+ row.id,
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
	function deleteFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="delete_msg"/>！', 'info');
			return;
		}
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
												'${pageContext.request.contextPath}/equipTypeController/delete',
												{
													id : row.id
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
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}

	//清空
	function cleanFun() {
		$('#type').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 102px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="type" /></td>
					</tr>
					<tr>
						<td><input id="type" name="type" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
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
			test="${fn:contains(sessionInfo.resourceList, '/equipTypeController/addEquipType')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/equipTypeController/editEquipType')}">
			<a onclick="editFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil', plain:true"
				href="javascript:void(0);"><spring:message code="update" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/equipTypeController/delete')}">
			<a onclick="deleteFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil_delete', plain:true"
				href="javascript:void(0);"><spring:message code="delete" /></a>
		</c:if>
	</div>
</body>
</html>
