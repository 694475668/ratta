<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="show_log" /></title>
<jsp:include page="../../../inc.jsp"></jsp:include>

</head>

<body>


	<script type="text/javascript">
		var dataGrid;
		var task_id = '${id}';

		$(function() {
			parent.$.messager.progress('close');

			dataGrid = $('#logGrid')
					.datagrid(
							{
								url : '${pageContext.request.contextPath}/scheduleTaskController/dataGridDetail',
								fit : true,
								fitColumns : true,
								border : false,
								pagination : true,
								pageSize : 10,
								pageList : [ 10, 20, 30, 40, 50 ],
								checkOnSelect : true,
								selectOnCheck : true,
								nowrap : false,
								striped : true,
								rownumbers : true,
								singleSelect : false,
								columns : [ [
										{
											title : 'ID',/* add by jjustt */
											field : 'id',
											width : 200,
											align : 'center',
											halign : 'center',
										/* 	hidden:true */
										},/*  {
											
													field : 'name',
													title : '任务名称',
													width : 110,
													align: 'center',
													halign: 'center'
												},  */
										{
											field : 'ksrq',
											title : '<spring:message code="schedule_start_time"/>',
											width : 140,
											align : 'center',
											halign : 'center'
										},
										{
											field : 'jsrq',
											title : '<spring:message code="schedule_end_time"/>',
											width : 140,
											align : 'center',
											halign : 'center'
										},
										{
											field : 'result',
											title : '<spring:message code="task_exec_result"/>',
											width : 200,
											sortable : true,
											align : 'center',
											halign : 'center',
											formatter : function(value, row,
													index) {
												if (value == 0) {
													return "<spring:message code="schedule_task_exec_success"/>";
												} else {
													return "<spring:message code="schedule_task_exec_fail"/>";
												}
											}
										} ] ],
								queryParams : {
									task_id : task_id
								},
								toolbar : '#toolbar',
								onLoadSuccess : function() {
									//$('#searchForm1 table').show();
									parent.$.messager.progress('close');
									$(this).datagrid('tooltip');
								},
								onRowContextMenu : function(e, rowIndex,
										rowData) {
									e.preventDefault();
									$(this).datagrid('unselectAll');
									$(this).datagrid('selectRow', rowIndex);
									$('#menu').menu('show', {
										left : e.pageX,
										top : e.pageY
									});
								}

							});
		});

		function responseSearch() {
			dataGrid.datagrid('load', $.serializeObject($("#logForm")));
		}
		function clean() {
			$('#logForm input').val('');
			dataGrid.datagrid('load', {});
		}
	</script>

	<div class="easyui-layout" data-options="fit : true,border : false">

		<div data-options="region:'center',border:false"
			style="height: 200px;; overflow: hidden;">
			<table id="logGrid"></table>

			<div id="toolbar" style="display: none;">
				<form id="logForm">
					<span><spring:message code="begin_date" />:</span> <input
						id="date_begin" name="date_begin" class="easyui-datebox"
						data-options="editable:false" /> <span><spring:message
							code="end_date" /></span> <input id="date_end" name="date_end"
						class="easyui-datebox" data-options="editable:false" /> <input
						name="task_id" type="hidden" value="${id}"></input> <a id="search"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true"
						onclick="responseSearch();"><spring:message code="query" /></a> <a
						id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true" onclick="clean();"><spring:message
							code="clear" /></a>
				</form>
			</div>

		</div>
	</div>



</body>

</html>
