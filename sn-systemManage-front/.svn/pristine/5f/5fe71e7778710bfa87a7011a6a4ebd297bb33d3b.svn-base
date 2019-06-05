<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>操作审计</title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/logController/manager')}">
	<script type="text/javascript">
		$.canViewLog = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/operAuditController/dataGrid',
							method : 'post',
							idField : 'id',
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
										field : 'username',
										title : '<spring:message code="username"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="op_user"/>',
										width : 160,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="op_time"/>',
										width : 170,
										align : 'center',
										halign : 'center',
									//formatter : formatterdate
									},
									{
										field : 'client_ip',
										title : '<spring:message code="client_ip"/>',
										width : 280,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_type',
										title : '<spring:message code="op_type"/>',
										width : 100,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.op_type == 1) {
												return '<spring:message code="query"/>';
											}
											if (rec.op_type == 2) {
												return '<spring:message code="add"/>';
											}
											if (rec.op_type == 3) {
												return '<spring:message code="update"/>';
											}
											if (rec.op_type == 4) {
												return '<spring:message code="delete"/>';
											}
											if (rec.op_type == 5) {
												return '<spring:message code="user_login"/>';
											}
											if (rec.op_type == 6) {
												return '<spring:message code="user_logout"/>';
											}
											if (rec.op_type == 7) {
												return '<spring:message code="import"/>';
											}
											if (rec.op_type == 8) {
												return '<spring:message code="batch_operation"/>';
											}
										}
									},
									{
										field : 'op_item',
										title : '<spring:message code="op_item"/>',
										width : 320,
										align : 'center',
										halign : 'center'
									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function(data) {
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

	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}

	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}

	function detailPanel(trace_no) {
		parent.$
				.modalDialog({
					title : '<spring:message code="detail"/>',
					width : 800,
					height : 500,
					href : '${pageContext.request.contextPath}/logController/manager?trace_no='
							+ trace_no,
					buttons : [ {
						text : '<spring:message code="confirm"/>',
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//操作时间转换
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
			style="height: 108px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="op_user" /></td>
						<td><spring:message code="client_ip" /></td>
						<td><spring:message code="op_type" /></td>
						<td><spring:message code="begin_date" /></td>
						<td><spring:message code="end_date" /></td>
					</tr>
					<tr>
						<td><input id="op_user" name="op_user" type="text"
							style="height: 15px; width: 180px; margin-top: 6px;"></td>
						<td><input id="client_ip" name="client_ip" type="text"
							style="height: 15px; width: 180px; margin-top: 6px;"></td>
						<td><select id="op_type" name="op_type" style="width: 192px"
							class="easyui-combobox" data-options="editable:false">
								<option value=""><spring:message code="all" /></option>
								<!-- <option value="1">查询</option> -->
								<option value="2"><spring:message code="add" /></option>
								<option value="3"><spring:message code="update" /></option>
								<option value="4"><spring:message code="delete" /></option>
								<option value="5"><spring:message code="user_login" /></option>
								<option value="6"><spring:message code="user_logout" /></option>
								<option value="7"><spring:message code="import" /></option>
								<option value="8"><spring:message
										code="batch_operation" /></option>
						</select></td>
						<td><input id="op_time_begin" name="op_time_begin"
							type="text" class="easyui-datebox"
							style="width: 194px; height: 23px;" data-options="editable:false">
						</td>
						<td><input id="op_time_end" name="op_time_end" type="text"
							class="easyui-datebox" style="width: 194px; height: 23px;"
							data-options="editable:false"></td>
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
	</div>

</body>
</html>
