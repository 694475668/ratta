<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>错误码管理</title>
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
							url : '${pageContext.request.contextPath}/errorCodeController/dataGrid',
							method : 'post',
							//idField : 'code',
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
										field : 'code',
										title : '<spring:message code="error_code"/>',
										width : 110,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'remark',
										title : '<spring:message code="systemError"/>',
										width : 110,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'desc',
										title : '<spring:message code="error_desc"/>',
										width : 300,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'desc_en',
										title : '<spring:message code="english_error_desc"/>',
										width : 400,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="op_user"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="op_time"/>',
										width : 200,
										align : 'center',
										halign : 'center'
									//formatter: formatterdate
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
					title : '<spring:message code="add_code"/>',
					width : 600,
					height : 350,
					//method: 'get',
					href : '${pageContext.request.contextPath}/errorCodeController/addErrorCode',
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
					title : '<spring:message code="update_code"/>',
					width : 600,
					height : 350,
					href : '${pageContext.request.contextPath}/errorCodeController/editErrorCode?code='
							+ row.code,
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
												'${pageContext.request.contextPath}/errorCodeController/delete',
												{
													code : row.code
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
		$('#code').val('');
		$('#desc').val('');

		dataGrid.datagrid('load', {});
	}

	//更新时间
	function formatterdate(val, row) {
		if (val != null) {
			var date = new Date(val);
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var h = date.getHours();
			var mi = date.getMinutes();
			var s = date.getSeconds();
			return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
					+ (d < 10 ? ('0' + d) : d) + ' ' + (h < 10 ? ('0' + h) : h)
					+ ':' + (mi < 10 ? ('0' + mi) : mi) + ':'
					+ (s < 10 ? ('0' + s) : s);
		}
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
						<td><spring:message code="error_code" /></td>
						<td><spring:message code="errordesc" /></td>
					</tr>
					<tr>
						<td><input id="code" name="code" type="text"
							style="height: 15px; width: 200px;"></td>
						<td><input id="desc" name="desc" type="text"
							style="height: 15px; width: 200px;"></td>
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
			test="${fn:contains(sessionInfo.resourceList, '/errorCodeController/addErrorCode')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/errorCodeController/editErrorCode')}">
			<a onclick="editFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil', plain:true"
				href="javascript:void(0);"><spring:message code="update" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/errorCodeController/delete')}">
			<a onclick="deleteFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil_delete', plain:true"
				href="javascript:void(0);"><spring:message code="delete" /></a>
		</c:if>
	</div>
</body>
</html>
