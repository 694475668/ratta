<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>固件版本审核</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jslib/jquery-print/css/ratta-print.css"
	type="text/css">
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
	test="${fn:contains(sessionInfo.resourceList, '/firmwareController/deleteAuditTest')}">
	<script type="text/javascript">
		$.canDelete = true;
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
							url : '${pageContext.request.contextPath}/firmwareController/dataGridAudit',
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
										field : 'audit_info',
										title : '<spring:message code="audit_content"/>',
										width : 200,
										halign : 'center',
										align : 'center'
									},
									{
										field : 'audit_user',
										title : '<spring:message code="audit_user"/>',
										width : 100,
										halign : 'center',
										align : 'center'
									},

									{
										field : 'audit_time',
										title : '<spring:message code="audit_time"/>',
										width : 140,
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
														width : 200,
														hidden : true
													},
													{
														field : 'type',
														title : '<spring:message code="file_name"/>',
														width : 100,
														align : 'center'
													},
													{
														field : 'version',
														title : '<spring:message code="version"/>',
														width : 100,
														align : 'center'
													},
													{
														field : 'action',
														title : '<spring:message code="operate"/>',
														width : 40,
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
		$("#cron")
				.click(
						function() {
							console
							parent.$
									.modalDialog({
										title : '<spring:message code="schedele_time"/>',
										width : 850,
										closable : false,
										height : 340,
										href : '${pageContext.request.contextPath}/firmwareController/quartz',
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
		$("#sp_time").hide();
		$("#onTime").click(function() {
			$("#sp_time").show();
		});
		$("#nowPerform").click(function() {
			$("#sp_time").hide();
		});

		//参数类别根据数据字典表获取
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
	//固件审核
	function firmwareAuditFun() {
		var row = dataGrid.datagrid('getSelected');

		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="audit_msg"/>', 'info');
			return;
		}
		if (row.audit_flag == 'N' || row.audit_flag == 'Y') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="audit_status_error"/>', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="audit"/>',
					width : 600,
					height : 250,
					href : '${pageContext.request.contextPath}/firmwareController/firmwareAudit?id='
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
	//固件审核测试
	function auditTestFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="firmware_audit_test"/>', 'info');
			return;
		}
		selectByModel();
		selectAuditTestList(row.version);
		if (row.audit_flag == 'N' || row.audit_flag == 'Y') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="audit_status_error"/>', 'info');
			return;
		}
		$("#cronDiv").show().dialog({
			modal : false,
			width : 950,
			closable : false,
			height : 480,
			buttons : [ {
				text : '<spring:message code="finish"/>',
				iconCls : 'database_save',
				plain : true,
				handler : function() {
					$("#cronDiv").window('close');
					dataGrid.datagrid('load', {});
					clearData();
				}
			} ]
		});
	}

	//初始化审核测试页面
	$(function() {
		$("#tid").datagrid({
			width : 800,
			url : '',
			method : 'post',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : '',
			sortOrder : 'desc',
			idField : 'equipment_no',
			//fit : true,
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			toolbar : '#serch',
			rownumbers : true,
			singleSelect : true,
			pagination : true,
			columns : [ [ {
				field : 'id',
				title : '<spring:message code="id"/>',
				width : 30,
				checkbox : true
			}, {
				field : 'equipment_no',
				title : '<spring:message code="equipment_no"/>',
				width : 150,
				align : 'center',
				halign : 'center'
			}, {
				field : 'firmware_version',
				title : '<spring:message code="now_version"/>',
				width : 229,
				align : 'center',
				halign : 'center'
			},
			{
				field : 'equipment_model',
				title : '<spring:message code="equipment_model"/>',
				width : 130,
				align : 'center',
				halign : 'center'
			},
			{
				field : 'batch_no',
				title : '<spring:message code="batch_no"/>',
				width : 130,
				align : 'center',
				halign : 'center'
			},
			{
				field : 'equipment_purpose',
				title : '<spring:message code="equipment_purpose"/>',
				width : 99,
				align : 'center',
				halign : 'center'
			}] ],
		});
		//设置分页控制
		var p = $('#tid').datagrid('getPager');
		$(p).pagination({
			showRefresh : false
		});

		//初始化批次号
		$("#batch_no").combogrid({
			width : 147,
			height : 24,
			panelWidth : 420,
			panelHeight : 350,
			fitColumns : true,
			idField : 'batch_no',
			textField : 'batch_no',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			editable : false,
			url : '',
			toolbar : '#toolbar5',
			columns : [ [ {
				field : 'batch_no',
				title : '<spring:message code="batch_no"/>',
				width : 200
			} ] ]
		});
	});

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

	$(function() {
		$("#equipment_purpose")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
									+ 'EQUIPMENT_PURPOSE',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							width : 147,
							valueField : 'value',
							textField : 'value_cn'
						});
	});

	//设备型号根据固件版本号筛选
	function selectByModel() {
		var row = dataGrid.datagrid('getSelected');
		var version = row.version;
		var type = $("#type").val();
		$("#equipment_model")
				.combogrid(
						{
							width : 147,
							height : 24,
							panelWidth : 420,
							panelHeight : 350,
							fitColumns : true,
							idField : 'equipment_model',
							textField : 'type',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/firmwareEquipController/dataGrid?version='
									+ version + '&type=' + type,
							toolbar : '#toolbar3',
							columns : [ [ {
								field : 'type',
								title : '<spring:message code="equipment_model"/>',
								width : 200
							} ] ],
							onSelect : selectByEquipModel
						});
	}
	//固件审核测试列表
	function selectAuditTestList(version) {
		$("#dataGrid1")
				.datagrid(
						{
							width : 800,
							url : '${pageContext.request.contextPath}/firmwareController/dataGridAuditTest?firmware_version='
									+ version,
							method : 'post',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : '',
							sortOrder : 'desc',
							idField : 'equipmentno',
							//fit : true,
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
										align : 'center',
										halign : 'center',
										hidden : true
									},
									{
										field : 'equipmentno',
										title : '<spring:message code="equipment_no"/>',
										width : 160,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'firmware_version',
										title : '<spring:message code="now_version"/>',
										width : 280,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'performTime',
										title : '<spring:message code="performTime"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'updateStatus',
										title : '<spring:message code="update_status"/>',
										width : 100,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.updateStatus == 1) {
												return '<spring:message code="no_update"/>';
											}
											if (rec.updateStatus == 2) {
												return '<spring:message code="yes_update"/>';
											}
										}
									},
									{
										field : 'action',
										title : '<spring:message code="operate"/>',
										width : 60,
										align : 'center',
										formatter : function(value, rec, index) {
											var str = '';
											if ($.canDelete) {
												if (rec.updateStatus == 1) {
													str += $
															.formatString(
																	'<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="<spring:message code="delete"/>"/>',
																	rec.id,
																	index,
																	'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
												}

											}
											return str;
										}
									} ] ],
						});
	}
	//批次号根据设备型号筛选
	function selectByEquipModel() {
		var batch_no = $('#batch').val();
		var row = dataGrid.datagrid('getSelected');
		var g = $('#equipment_model').combogrid('grid'); // 获取表格控件对象
		var r = g.datagrid('getSelected'); //获取表格当前选中行
		$("#batch_no")
				.combogrid(
						{
							width : 147,
							height : 24,
							panelWidth : 420,
							panelHeight : 350,
							fitColumns : true,
							idField : 'batch_no',
							textField : 'batch_no',
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							editable : false,
							url : '${pageContext.request.contextPath}/inOutStockController/queryBatchNo?equipment_model='
									+ r.equipment_model
									+ '&id='
									+ row.id
									+ '&batch_no=' + batch_no,
							toolbar : '#toolbar5',
							columns : [ [ {
								field : 'batch_no',
								title : '<spring:message code="batch_no"/>',
								width : 200
							} ] ]
						});
	}
	//设备列表根据批次号筛选
	function selectByBatchNo() {
		var equipment_purpose = $("#equipment_purpose").combobox('getValue');
		var equipment_number = $("#equipment_number").val();
		var row = dataGrid.datagrid('getSelected');
		var batch_no = $('#batch_no').combogrid('getValue');
		var equipment_model= $('#equipment_model').combogrid('getValue');
		$("#tid").datagrid(
								{
									width : 800,
									url : '${pageContext.request.contextPath}/stockController/dataGridNotRelease?batch_no='
											+ batch_no
											+ '&firmware_version='
											+ row.version
											+ '&equipment_model='
											+ equipment_model
											+ '&equipment_number='
											+ equipment_number
											+ '&equipment_purpose='
											+ equipment_purpose,
									method : 'post',
									pageSize : 10,
									pageList : [ 10, 20, 30, 40, 50 ],
									sortName : '',
									sortOrder : 'desc',
									idField : 'equipment_no',
									//fit : true,
									checkOnSelect : false,
									selectOnCheck : false,
									nowrap : false,
									cache : false,
									striped : true,
									rownumbers : true,
									singleSelect : true,
									pagination : true,
									columns : [ [
											{
												field : 'id',
												title : '<spring:message code="id"/>',
												width : 30,
												checkbox : true
											},
											{
												field : 'equipment_no',
												title : '<spring:message code="equipment_no"/>',
												width : 150,
												align : 'center',
												halign : 'center'
											},
											{
												field : 'firmware_version',
												title : '<spring:message code="now_version"/>',
												width : 229,
												align : 'center',
												halign : 'center'
											},
											{
												field : 'equipment_model',
												title : '<spring:message code="equipment_model"/>',
												width : 130,
												align : 'center',
												halign : 'center'
											},
											{
												field : 'batch_no',
												title : '<spring:message code="batch_no"/>',
												width : 130,
												align : 'center',
												halign : 'center'
											},
											{
												field : 'equipment_purpose',
												title : '<spring:message code="equipment_purpose"/>',
												width : 99,
												align : 'center',
												halign : 'center'
											} ] ],
								});
	}
	//点击添加按钮，显示在datagrid
	function auditTestAddFun() {
		var row = dataGrid.datagrid('getSelected');
		var porformType = $('input[name="porformType"]:checked').val();
		var cron = $("#cron").val();
		var rows = $("#tid").datagrid("getChecked");

		if (porformType == '1') {
			if (cron == "" || cron == undefined || cron == null) {
				$.messager.alert('<spring:message code="hint"/>',
						'<spring:message code="select_cron"/>', 'info');
				return;
			}
		}

		if (rows.length == 0) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_equipNo"/>', 'info');
			return;
		}

		var copyRows = [];
		for (var j = 0; j < rows.length; j++) {
			copyRows.push(rows[j].equipment_no); //把rows复制给另一个数组，因为deleteRow删除后会刷新页面的index，所以每次只能删除一个
		}

		params = "id=" + row.id + "&porformType=" + porformType + "&equuipmentNo="
				+ copyRows + "&performTime=" + cron + "&firmware_version="
				+ row.version;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$
				.post(
						'${pageContext.request.contextPath}/firmwareController/firmwareAuditTest',
						params, function(result) {
							if (result.success) {
								parent.$.messager.show({
									title : '<spring:message code="hint"/>',
									timeout : 3000,
									msg : result.msg
								});
								$("#dataGrid1").datagrid('reload');
								clearData();
							} else {
								parent.$.messager.show({
									title : '<spring:message code="hint"/>',
									timeout : 3000,
									msg : result.msg
								});
							}
							parent.$.messager.progress('close');
						}, 'JSON');
	}
	//删除
	function deleteFun(id) {
		var row = dataGrid.datagrid('getSelected');
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
												'${pageContext.request.contextPath}/firmwareController/deleteAuditTest',
												{
													id : id,
													version : row.version
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														$("#dataGrid1")
																.datagrid(
																		'reload');
													} else {
														parent.$.messager
																.show({
																	title : '<spring:message code="hint"/>',
																	timeout : 3000,
																	msg : result.msg
																});
														$("#dataGrid1")
																.datagrid(
																		'reload');
													}
													parent.$.messager
															.progress('close');
												}, 'JSON');

							}
						});
	}

	function clearModelValue() {
		$('#type').val('');
		selectByModel();
	}

	function clearBatchValue() {
		$('#batch').val('');
		selectByEquipModel();
	}
	// 点击完成，清除页面数据
	function clearData() {
		clearSerchData();
		$("#nowPerform").prop('checked', true);
		$("#sp_time").hide();
		$("#cron").val('');
	}
	
	// 点击完成，清除页面数据
	function clearSerchData() {
		$('#type').val('');
		$('#batch').val('');
		$('#equipment_number').val('');
		$('#equipment_model').combogrid('clear', '');
		$('#batch_no').combogrid('clear', '');
		$("#batch_no").combogrid("grid").datagrid("loadData", {
			total : 0,
			rows : []
		});
		removeComboxItem('equipment_purpose', $('#equipment_purpose').combobox(
				'getValue'));
		var rows = $("#tid").datagrid("getRows");
		var length = rows.length;
		onUncheckAllMR(length);
		$('#tid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	}
	//清理序列号列表选中
	function onUncheckAllMR(length) {
		for (var i = 0; i < length; i++) {
			$("#tid").datagrid('uncheckRow', i);
		}

	}
	//清理下拉框选择的记录
	function removeComboxItem(id, v) {
		var rows = $('#' + id).combobox('getData');
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].name == v) {
				rows.splice(i, 1);
				break;
			}
			//;
		}
		$('#' + id).combobox('loadData', rows).combobox('setValue', '');
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
			test="${fn:contains(sessionInfo.resourceList, '/firmwareController/firmwareAuditTest')}">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'brick_add',plain:true"
				onclick="auditTestFun();"><spring:message code="audit_test" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/firmwareController/firmwareAudit')}">
			<a onclick="firmwareAuditFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'status_away'"><spring:message
					code="audit" /></a>
		</c:if>
	</div>
	
	
	<div id="cronDiv" title='<spring:message code="audit_test"/>' style="overflow-y: scroll; display: none;">
		<form id="cronForm">
			<table class="sp_table">
				<tr class="sp_radio">
				   <td>
							<spring:message code="please_perform" />：
				   </td>
					<td>
						&nbsp;&nbsp;<!-- 单选按钮 立即  --> <input class="sp_radio_space" id="nowPerform"
						name="porformType" type="radio" value="0" checked /> <spring:message
							code="now_perform" />&nbsp;&nbsp;
						<!-- 单选按钮 按时生效  --> <input class="sp_radio_space" id="onTime"
						name="porformType" type="radio" value="1" /> <spring:message
							code="Take_effect_on_time" />
					</td>
				</tr>
				<tr id="sp_time">
					<td><p style="margin-left: 17px;">
							<spring:message code="performTime" />
							：
						</p></td>
					<td><input id="cron" name="performTime"
						class="easyui-validatebox" style="width: 150px;" data-options=""
						readonly /></td>
				</tr>

			</table>

			<div style="margin-left: 50px;">
                <div id="serch">
                  <table id="searchTable" style="border: solid 0px red; margin: 5px;">
					<tr>
						<td><spring:message code="equipment_model" /></td>
						<td><spring:message code="batch_no" /></td>
						<td><spring:message code="equipment_purpose" /></td>
						<td><spring:message code="equipment_number" /></td>
					</tr>
					<tr>
						<td><input id="equipment_model" name="equipment_model"
							type="text" style="margin-top: 10px;"></td>
						<td><input id="batch_no" name="batch_no" type="text"
							style="height: 15px;margin-top: 10px;"></td>
						<td><input id="equipment_purpose" name="equipment_purpose" type="text"
							style="margin-top: 10px;"></td>
						<td><input id="equipment_number" name="equipment_number" type="text"
							style="height: 15px; width: 120px; margin-top: 10px;">&nbsp;&nbsp;
							<a id="serchEquipment" href="javascript:void(0)" class="easyui-linkbutton"  title="搜索"
							data-options="iconCls:'icon-search'" onclick="selectByBatchNo()"></a>&nbsp;
							<a id="serchEquipment" href="javascript:void(0)" class="easyui-linkbutton"  title="清空"
							data-options="iconCls:'cancel'"  onclick="clearSerchData()"></a>
						</td>
					</tr>
				 </table>
				</div>
				<table id="tid"></table>  
				
				<br><input id="button" type="button"
					value="<spring:message code="add"/>" onclick="auditTestAddFun()" />
			</div>
		</form>
		<div style="margin-left: 50px;">
			<spring:message code="audit_task_list" />
			：<br /> <input id="dataGrid1" name="dataGrid1" type="text"
				style="height: 340px;" />
		</div>
	</div>
	<div id="toolbar3" style="display: none;">
		<form id="terminalModelForm">
			<table>
				<tr>
					<td><spring:message code="equipment_model" />：</td>
					<td><input id="type" name="type"
						class="easyui-validatebox span2" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true"
						onclick="selectByModel();"><spring:message code="query" /></a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'pencil_delete',plain:true"
						onclick="clearModelValue();"><spring:message code="clear" /></a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar5" style="display: none;">
		<form id="batchNoForm">
			<table>
				<tr>
					<td><spring:message code="batch_no" />：</td>
					<td><input id="batch" name="batch_no"
						class="easyui-validatebox span2" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'search',plain:true"
						onclick="selectByEquipModel();"><spring:message code="query" /></a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'pencil_delete',plain:true"
						onclick="clearBatchValue();"><spring:message code="clear" /></a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>