<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="schedule_title" /></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/scheduleTaskController/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/scheduleTaskController/executeTask')}">
	<script type="text/javascript">
		$.canExecute = true;
	</script>
</c:if>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div
			data-options="region:'north',title:'<spring:message code="query_criteria"/>',border:false"
			style="height: 108px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="schedule_name" /></td>
						<td><spring:message code="schedule_status" /></td>
					</tr>
					<tr>
						<td><input id="name" name="name" type="text"
							style="height: 15px; width: 200px; margin-top: 6px;" /></td>
						<td><select id="status" class="easyui-combobox" name="status"
							style="width: 200px;" data-options="onSelect:responseSearch">
								<option value=""><spring:message code="all" /></option>
								<option value="0"><spring:message code="start" /></option>
								<option value="1"><spring:message code="stop" /></option>
						</select></td>
					</tr>
				</table>
			</form>
		</div>

		<div data-options="region:'center',border:false">
			<table id="taskGrid"></table>
		</div>
	</div>

	<div id="toolbar" style="display: none;">
		<a id="search" href="#" class="easyui-linkbutton"
			data-options="iconCls:'search',plain:true"
			onclick="responseSearch();"><spring:message code="query" /></a> <a
			id="clear" href="#" class="easyui-linkbutton"
			data-options="iconCls:'pencil_delete',plain:true"
			onclick="clearValue();"><spring:message code="clear" /></a>
		<!-- 启用 -->
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/scheduleTaskController/addTask')}">
			<a id="open" href="#" class="easyui-linkbutton"
				data-options="iconCls:'link_add',plain:true" onclick="addTask();"><spring:message
					code="start" /></a>
		</c:if>
		<!-- 停用 -->
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/scheduleTaskController/removeTask')}">
			<a id="close" href="#" class="easyui-linkbutton"
				data-options="iconCls:'link_delete',plain:true"
				onclick="removeTask();"><spring:message code="stop" /></a>
		</c:if>
	</div>

	<div id="cronDiv" title='<spring:message code="new_task"/>'
		style="width: 600px; height: 230px; overflow: hidden; display: none;">
		<form id="cronForm">
			<input id="id" name="id" class="easyui-validatebox"
				style="display: none;" /> <input id="statusName" name="status"
				class="easyui-validatebox" style="display: none;" />
			<table style="margin-top: 10px; margin-left: 10px;">
				<tr>
					<td><spring:message code="schedule_name" />：</td>
					<td><input id="quartzName" name="name"
						class="easyui-validatebox" data-options="" readonly /></td>
					<td><span style="margin-left: 30px;"><spring:message
								code="schedele_time" />：</span></td>
					<td><input id="cron" name="cron" class="easyui-validatebox"
						style="width: 150px;" data-options="" readonly /></td>
				</tr>
				<tr rowspan="3">
					<td><spring:message code="task_desc" />：</td>
					<td colspan="5"><textarea id="remark" name="remark"
							maxlength="100"
							style="width: 98%; height: 80px; margin-top: 10px;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		var dataGrid;
		$(function() {
			parent.$.messager.progress('close');
			dataGrid = $('#taskGrid')
					.datagrid(
							{
								url : '${pageContext.request.contextPath}/scheduleTaskController/dataGrid',
								fit : true,
								fitColumns : true,
								border : false,
								pagination : true,
								pageSize : 10,
								pageList : [ 10, 20, 30, 40, 50 ],
								checkOnSelect : false,
								selectOnCheck : false,
								nowrap : true,
								striped : true,
								rownumbers : true,
								singleSelect : true,
								columns : [ [
										{
											field : 'id',
											title : '<spring:message code="id"/>',
											width : 80,
											align : 'center',
											halign : 'center',
											checkbox : true
										},
										{
											field : 'log',
											title : '<spring:message code="exec_log"/>',
											width : 60,
											align : 'center',
											halign : 'center',
											formatter : function(value, row,
													index) {
												return $
														.formatString(
																'<a onclick="viewLog(\'{0}\');" ><spring:message code="show_log"/></a>',
																row.id);
											}
										},/* {
													field : 'id',
													title : '<spring:message code="id"/>',
													width : 200,
													align: 'center',
													halign: 'center',
													hidden:true
												}, {
											
													field : 'task_id',
													title : '任务编号',
													width : 100,
													align: 'center',
													halign: 'center',
													hidden:true
												},  */
										{

											field : 'name',
											title : '<spring:message code="schedule_name"/>',
											width : 100,
											align : 'center',
											halign : 'center'
										},
										{
											field : 'status',
											title : '<spring:message code="schedule_status"/>',
											width : 80,
											align : 'center',
											halign : 'center',
											formatter : function(value, row,
													index) {
												if (value == 0) {
													return "<spring:message code="start"/>";
												} else {
													return "<spring:message code="stop"/>";
												}
											}
										},
										{
											field : 'cron',
											title : '<spring:message code="schedele_time"/>',
											width : 80,
											align : 'center',
											halign : 'center'
										},
										{
											field : 'remark',
											title : '<spring:message code="task_desc"/>',
											width : 200,
											sortable : true,
											align : 'center',
											halign : 'center'
										},
										{
											field : 'action',
											title : '<spring:message code="operate"/>',
											width : 60,
											align : 'center',
											formatter : function(value, row,
													index) {
												var str = '';
												if ($.canEdit) {
													str += $
															.formatString(
																	'<img onclick="responseEdit(\'{0}\');" src="{1}" title="<spring:message code="update"/>"/>',
																	row.id,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
												}
												str += '&nbsp;&nbsp;';
												if ($.canExecute) {
													str += $
															.formatString(
																	'<img onclick="executeTask(\'{0}\',\'{1}\');" src="{2}" title="<spring:message code="exec"/>"/>',
																	row.id,
																	row.status,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/resultset_last.png');
												}
												return str;
											}
										} ] ],
								toolbar : '#toolbar',
								onLoadSuccess : function() {
									$('#searchForm1 table').show();
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

			//点击调度时间,跳转到调度时间页面
			$("#cron")
					.click(
							function() {
								var id = $("#id").val();
								parent.$
										.modalDialog({
											title : '<spring:message code="schedele_time"/>',
											width : 850,
											height : 340,
											cache : true,
											href : '${pageContext.request.contextPath}/scheduleTaskController/quartz?id='
													+ id,
											buttons : [
													{
														text : '<spring:message code="save"/>',
														plain : true,
														handler : function() {
															// 调用 quartz.jsp 中的 createCronExpression() 方法,得到返回的 cron 表达式
															var b = window.parent
																	.createCronExpression();
															$("#cron").val(b);
															parent.$.modalDialog.handler
																	.dialog('close');
														}
													},
													{
														text : '<spring:message code="cancel"/>',
														plain : true,
														handler : function() {
															parent.$.modalDialog.handler
																	.dialog('close');
														}
													} ]
										});
							});
		});

		var dialog = $("#cronDiv");
		// 修改任务，跳转到新建任务页面
		function responseEdit(id) {
			$
					.post(
							'${pageContext.request.contextPath}/scheduleTaskController/getQuartz',
							'id=' + id,
							function(result) {
								$("#id").val(result.id);
								$("#statusName").val(result.status);
								$("#quartzName").val(result.name);
								//$("#bzCode").val(result.bzcode) ;
								$("#cron").val(result.cron);
								$("#remark").val(result.remark);
								dialog
										.show()
										.dialog(
												{
													modal : false,
													buttons : [
															{
																text : '<spring:message code="edit"/>',
																plain : true,
																handler : function() {
																	edit();
																}
															},
															{
																text : '<spring:message code="cancel"/>',
																plain : true,
																handler : function() {
																	dialog
																			.window('close');
																}
															} ]
												});
							}, 'json');
		}

		//响应“清除”按钮
		function clearValue() {
			$('#name').val('');
			$("#taskGrid").datagrid("uncheckAll");

			var status = $('#status').combobox('getData');
			$("#status").combobox('select', '');
			$("#taskGrid")
					.datagrid('load', $.serializeObject($("#searchForm")));
		}

		// 编辑 定时任务
		function edit() {
			if (nullValid()) {
				$
						.post(
								'${pageContext.request.contextPath}/scheduleTaskController/edit',
								$("#cronForm").serialize(),
								function(result) {
									if (result.success) {
										// 刷新表格
										$("#taskGrid").datagrid('reload');
										//关闭弹出窗口
										dialog.window('close');
										// 弹出提示信息，--修改成功
										$.messager
												.show({
													title : '<spring:message code="hint"/>',
													msg : result.msg,
													timeout : 3000,
													showType : 'slide'
												});
									} else {
										$.messager
												.alert(
														'<spring:message code="error"/>',
														result.msg, 'error');
									}
								}, 'json');
			}
		}

		// 启用任务
		function addTask() {
			if (!isSelected()) {
				return;
			}
			var idJson = getSelectedIds();
			if (idJson.disabledNames == "") {
				$.messager.alert('<spring:message code="warn"/>',
						'<spring:message code="warn_task_is_open"/>', 'warn');
				return;
			} else {
				if (idJson.enabledIds.trim() != "") {
					/*测试人员认为不需要提示
					  $.messager.alert('<spring:message code="warn"/>', " [ " + idJson.enabledNames + " ] '<spring:message code="task_is_opened"/>'", 'warn'); 
					 */
				}
			}

			$
					.post(
							'${pageContext.request.contextPath}/scheduleTaskController/addTask',
							"ids=" + idJson.disabledIds,
							function(result) {
								if (result.success) {
									// 刷新 grid
									freshGrid();
									// 弹出提示信息，--修改成功
									$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : result.msg,
												timeout : 3000,
												showType : 'slide'
											});
								} else {
									$.messager.alert(
											'<spring:message code="error"/>',
											result.msg, 'error');
								}
							}, 'json');
		}

		// 停用任务
		function removeTask() {
			if (!isSelected()) {
				return;
			}
			var idJson = getSelectedIds();

			if (idJson.enabledIds.trim() == "") {
				$.messager.alert('<spring:message code="warn"/>',
						'<spring:message code="warn_task_is_stop"/>', 'warn');
				return;
			} else {
				if (idJson.disabledIds.trim() != "") {
					/* 测试人员认为不需要提示
					   $.messager.alert('<spring:message code="warn"/>', " [ " + idJson.disabledNames + " ] <spring:message code="task_is_stoped"/>", 'warn'); 
					 */
				}
			}
			$
					.post(
							'${pageContext.request.contextPath}/scheduleTaskController/removeTask',
							"ids=" + idJson.enabledIds,
							function(result) {
								if (result.success) {
									// 刷新 grid
									freshGrid();
									// 弹出提示信息，--修改成功
									$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : result.msg,
												timeout : 3000,
												showType : 'slide'
											});
								} else {
									$.messager.alert(
											'<spring:message code="error"/>',
											result.msg, 'error');
								}
							}, 'json');
		}

		// 执行任务
		function executeTask(id, status) {
			if (status != 0) {
				$.messager.alert('<spring:message code="warn"/>',
						'<spring:message code="warn_start_task_first"/>',
						'warn');
				return;
			}
			$
					.post(
							'${pageContext.request.contextPath}/scheduleTaskController/executeTask',
							"id=" + id,
							function(result) {
								if (result.success) {
									// 弹出提示信息，--修改成功
									$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : result.msg,
												timeout : 3000,
												showType : 'slide'
											});
									//setTimeout(openButton,60000);
								} else {
									$.messager.alert(
											'<spring:message code="error"/>',
											result.msg, 'error');
									// openButton();
								}
							}, 'json');

		}

		// 文本框 交验
		function nullValid() {
			var flag = true;
			if ($("#quartzName").val().trim() == "") {
				flag = false;
				alert("<spring:message code="warn_task_name_empty"/>");
				return;
			}
			if ($("#cron").val().trim() == "") {
				flag = false;
				alert("<spring:message code="warn_task_time_empty"/>");
				return;
			}
			return flag;
		}

		// 校验是否选中
		function isSelected() {
			var flag = true;
			var rows = getSelectedRow();
			if (rows == null || rows.length == 0) {
				$.messager.alert('<spring:message code="hint"/>',
						'<spring:message code="warn_select_line_first"/>',
						'info');
				flag = false;
				return flag;
			}
			return flag;
		}

		//刷新 grid
		function freshGrid() {
			$("#taskGrid").datagrid("uncheckAll");
			$("#taskGrid").datagrid('reload');
		}

		function getSelectedRow() {
			var rows = $("#taskGrid").datagrid('getChecked');
			return rows;
		}

		// 获得所有行的id 连接成的字符串
		// 获得选中的所有启用的行
		// @Troy 
		function getSelectedIds() {

			var j = {
				"enabledIds" : "",
				"enabledNames" : "",
				"disabledIds" : "",
				"disabledNames" : ""
			};

			var rows = getSelectedRow();

			var enabledIds = "";
			var enabledNames = "";
			var disabledIds = "";
			var disabledNames = "";
			for (var i = 0, sumi = rows.length; i < sumi; i++) {
				if (rows[i].status == 0) {
					enabledIds = (rows[i].id + "," + enabledIds);
					enabledNames = (rows[i].name + "," + enabledNames);
				} else {
					disabledIds = (rows[i].id + "," + disabledIds);
					disabledNames = (rows[i].name + "," + disabledNames);
				}
			}
			enabledIds = enabledIds.trim();
			enabledNames = enabledNames.trim();
			disabledIds = disabledIds.trim();
			disabledNames = disabledNames.trim();

			var len1 = enabledIds.length;
			var len2 = disabledIds.length;
			var nameLen = enabledNames.length;
			var nameLen2 = disabledNames.length;

			enabledIds = enabledIds.substr(0, len1 - 1);
			enabledNames = enabledNames.substr(0, nameLen - 1);

			disabledIds = disabledIds.substr(0, len2 - 1);
			disabledNames = disabledNames.substr(0, nameLen2 - 1);

			j.enabledIds = enabledIds;
			j.enabledNames = enabledNames;
			j.disabledIds = disabledIds;
			j.disabledNames = disabledNames;

			return j;

		}

		// 下拉框查询
		function responseSearch() {
			$("#taskGrid")
					.datagrid('load', $.serializeObject($("#searchForm")));
		}

		// 查看日志
		function viewLog(id) {
			parent.$
					.modalDialog({
						title : '<spring:message code="show_log"/>',
						width : 900,
						height : 410,
						href : '${pageContext.request.contextPath}/scheduleTaskController/logPage?id='
								+ id,
						buttons : [ {
							text : '<spring:message code="cancel"/>',
							iconCls : 'cancel',
							plain : true,
							handler : function() {
								parent.$.modalDialog.handler.dialog('close');
							}
						} ]
					});
		}
	</script>
</body>

</html>
