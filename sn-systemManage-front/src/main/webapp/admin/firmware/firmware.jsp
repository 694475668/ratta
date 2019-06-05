<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>固件版本上传</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/firmwareController/detail')}">
	<script type="text/javascript">
		$.canDetail = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/firmwareController/detailLine')}">
	<script type="text/javascript">
		$.canDetailLine = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/firmwareController/dataGrid',
							method : 'post',
							//idField : '',
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
										field : 'id',
										title : '<spring:message code="id"/>',
										width : 30,
										hidden : true
									},
									{
										field : 'area',
										title : '<spring:message code="area"/>',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'custom',
										title : '<spring:message code="custom"/>',
										width : 100,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'configuration',
										title : '<spring:message code="configuration"/>',
										width : 150,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'version',
										title : '<spring:message code="version"/>',
										width : 250,
										sortable : true,
										halign : 'center',
										align : 'center'

									},
									{
										field : 'status',
										title : '<spring:message code="status"/>',
										width : 80,
										halign : 'center',
										align : 'center'
									},
									{
										field : 'audit_flag',
										title : '<spring:message code="audit_flag"/>',
										width : 80,
										halign : 'center',
										align : 'center',
										formatter : function(value, row) {
											if (row.audit_flag == 'Y') {
												return '<spring:message code="agree"/>';
											}
											if (row.audit_flag == 'N') {
												return '<spring:message code="disagree"/>';
											}
										}
									},
									{
										field : 'op_time',
										title : '<spring:message code="upload_file_time"/>',
										width : 150,
										halign : 'center',
										align : 'center'
									},

									{
										field : 'op_user',
										title : '<spring:message code="upload_user"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'action',
										title : '<spring:message code="operate"/>',
										width : 60,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if ($.canDetail) {
												str += $
														.formatString(
																'<img onclick="detail(\'{0}\');" src="{1}" title="<spring:message code="verison_detail"/>"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/feed/feed.png');
											}
											return str;
										}
									} ] ],
							toolbar : '#toolbar',
							onHeaderContextMenu : function(e, field) {
								e.preventDefault();
								if (!cmenu) {
									createColumnMenu();
								}
								cmenu.menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},

							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
							},

							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$(this).datagrid('unselectAll').datagrid(
										'uncheckAll');
								$(this).datagrid('selectRow', rowIndex);
							},

							view : detailview,

							detailFormatter : function(index, row) {
								return '<div style="padding:2px"><table class="ddv"></table></div>';
							},

							//详情
							onExpandRow : function(index, row) {
								//点击+号，选中父类
								$('#dataGrid').datagrid('selectRow', index);
								var row = $('#dataGrid')
										.datagrid('getSelected');
								ddv = $(this).datagrid('getRowDetail', index)
										.find('table.ddv'); //3.
								ddv
										.datagrid({ //4.ddv[index]换成ddv
											url : '${pageContext.request.contextPath}/firmwareController/firmwareInfoLine?id='
													+ row.id,
											fitColumns : true,
											singleSelect : true,
											rownumbers : true,
											pagination : true,
											idField : 'id',
											pageSize : 10,
											pageList : [ 10, 20, 30, 40, 50 ],
											checkOnSelect : false,
											selectOnCheck : false,
											striped : true,
											nowrap : false,
											columns : [ [
													{
														field : 'id',
														title : '<spring:message code="id"/>',
														width : 10,
														hidden : true
													},
													{
														field : 'type',
														title : '<spring:message code="file_name"/>',
														width : 20,
														align : 'center'
													},
													{
														field : 'version',
														title : '<spring:message code="version"/>',
														width : 40,
														align : 'center'
													},
													{
														field : 'action',
														title : '<spring:message code="operate"/>',
														width : 10,
														align : 'center',
														formatter : function(
																value, row,
																index) {
															var str = '';
															if ($.canDetailLine) {
																str += $
																		.formatString(
																				'<img onclick="detailLine(\'{0}\');" src="{1}" title="<spring:message code="verison_detail"/>"/>',
																				row.id,
																				'${pageContext.request.contextPath}/style/images/extjs_icons/feed/feed.png');
															}
															return str;
														}
													} ] ],
											onResize : function() {
												$('#dataGrid').datagrid(
														'fixDetailRowHeight',
														index);
											},
											onLoadSuccess : function() {
												setTimeout(
														function() {
															$('#dataGrid')
																	.datagrid(
																			'fixDetailRowHeight',
																			index);
														}, 0);
											},

										});
								$('#dataGrid').datagrid('fixDetailRowHeight',
										index);
							},

						});
	});
	//参数类别根据数据字典表获取
	$(function() {
		$("#status")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'FIRMWARE_STATUS',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'value',
							textField : 'value_cn'
						});
	});

	//详情
	function detail(id) {
		parent.$
				.modalDialog({
					title : '<spring:message code="verison_detail"/>',
					width : 800,
					height : 500,
					href : '${pageContext.request.contextPath}/firmwareController/detail?id='
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
	//固件版本信息子类详情
	function detailLine(id) {
		parent.$
				.modalDialog({
					title : '<spring:message code="verison_detail"/>',
					width : 800,
					height : 500,
					href : '${pageContext.request.contextPath}/firmwareController/detailLine?id='
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
												'${pageContext.request.contextPath}/firmwareController/delete',
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
	//固件上传
	function addFireWareUpload() {
		parent.$
				.modalDialog({
					title : '<spring:message code="FireWareUpload"/>',
					width : 650,
					height : 400,
					href : '${pageContext.request.contextPath}/firmwareController/addfirmware',
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
							//dataGrid.datagrid('reload');
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

	//修改固件相对应的设备型号
	function updateFirmwareEquipFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="please_choice_data"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="updateFirmwareEquip"/>',
					width : 650,
					height : 400,
					href : '${pageContext.request.contextPath}/firmwareEquipController/toUpdateFirmEquip?version='
							+ row.version,
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
							dataGrid.datagrid('reload');
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

	function searchFun() {
		dataGrid.datagrid("uncheckAll");
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		dataGrid.datagrid("uncheckAll");
		$('#searchForm input').val('');
		var status = $("#status").combobox('getData');
		$("#status").combobox('select', status[0].id);
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
						<td><spring:message code="version" /></td>
						<td><spring:message code="status" /></td>
						<td><spring:message code="begin_date" /></td>
						<td><spring:message code="end_date" /></td>
					</tr>
					<tr>
						<td><input id="version" name="version" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input id="status" name="status" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input id="beginTime" name="beginTime" type="text"
							class="easyui-datebox" style="width: 200px; height: 25px;"
							data-options="editable:false"></td>
						<td><input id="endTime" name="endTime" type="text"
							class="easyui-datebox" style="width: 200px; height: 25px;"
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
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'brick_add',plain:true" onclick="searchFun();"><spring:message
				code="query" /></a> <a href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="iconCls:'brick_delete',plain:true"
			onclick="cleanFun();"><spring:message code="clear" /></a>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmwareController/addfirmware')}">
			<a onclick="addFireWareUpload();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'status_offline'"><spring:message
					code="upload" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmwareController/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'"><spring:message
					code="delete" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmwareEquipController/toUpdateFirmEquip')}">
			<a onclick="updateFirmwareEquipFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'new'"><spring:message
					code="updateFirmwareEquip" /></a>
		</c:if>
	</div>

</body>
</html>