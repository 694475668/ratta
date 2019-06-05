<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>出入库管理</title>
<jsp:include page="../../inc.jsp"></jsp:include>
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
							url : '${pageContext.request.contextPath}/inOutStockController/dataGrid',
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
										field : 'equipment_model',
										title : '<spring:message code="equipment_model"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'batch_no',
										title : '<spring:message code="batch_no"/>',
										width : 160,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'counts',
										title : '<spring:message code="counts"/>',
										width : 60,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'begin_sn',
										title : '<spring:message code="begin_sn"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'end_sn',
										title : '<spring:message code="end_sn"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'flag',
										title : '<spring:message code="in_out_flag"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.flag == 1) {
												return '<spring:message code="in"/>';
											}
											if (rec.flag == 2) {
												return '<spring:message code="out"/>';
											}
										}
									},
									{
										field : 'status',
										title : '<spring:message code="status"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.status == 1) {
												return '<spring:message code="normal"/>';
											}
											if (rec.status == 2) {
												return '<spring:message code="undo"/>';
											}
										}
									},
									{
										field : 'firmware_version',
										title : '<spring:message code="firmware_version"/>',
										width : 240,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'dealers_name',
										title : '<spring:message code="dealers_name"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'configuration',
										title : '<spring:message code="configuration"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'area',
										title : '<spring:message code="area"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'custom',
										title : '<spring:message code="custom"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'equipment_purpose',
										title : '<spring:message code="equipment_purpose"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'note',
										title : '<spring:message code="remark"/>',
										width : 180,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_user',
										title : '<spring:message code="op_user"/>',
										width : 60,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'op_time',
										title : '<spring:message code="op_time"/>',
										width : 150,
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
	$(function() {
		$("#equipment_model")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/equipTypeController/queryNoPage',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 'auto',
							height : 24,
							valueField : 'id',
							textField : 'type'
						});
	});
	$(function() {
		$("#dealers_name")
				.combobox(
						{
							url : '${pageContext.request.contextPath}/dealersController/queryNoPage',
							editable : false, //不可编辑状态
							cache : false,
							panelHeight : 300,
							height : 24,
							valueField : 'id',
							textField : 'dealersName'
						});
	});
	//下载
	function downloadFun(type) {
		$.download('${pageContext.request.contextPath}/inOutStockController/downloadDealersXls?type='+type,'tyvtgvbygvyv', 'post');
	}

	// Ajax 文件下载
	jQuery.download = function(url, data, method) {
		// 获取url和data
		if (url && data) {
			// data 是 string 或者 array/object
			data = typeof data == 'string' ? data : jQuery.param(data);
			// 把参数组装成 form的  input
			var inputs = '';
			jQuery
					.each(
							data.split('&'),
							function() {
								pair = this.split('=');
								inputs += '<input type="hidden" name="' + pair[0] + '" value="' + pair[1] + '" />';
							});
			// request发送请求
			jQuery(
					'<form action="' + url + '" method="' + (method || 'post')
							+ '">' + inputs + '</form>').appendTo('body')
					.submit().remove();
		}

	};
	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}

	//导出
	function responseExcel() {
		var equipment_model = $('#equipment_model').combobox('getValue');
		var dealers_name = $('#dealers_name').combobox('getValue');
		var flag = $('#flag').combobox('getValue');
		var status = $('#status').combobox('getValue');
		var equipment_no = $('#equipment_no').val();
		var batch_no = $('#batch_no').val();
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var params = "dealers_name=" + dealers_name + "&flag=" + flag
				+ "&status=" + status + "&equipment_model=" + equipment_model
				+ "&equipment_no=" + equipment_no + "&batch_no=" + batch_no
				+ "&beginTime=" + beginTime + "&endTime=" + endTime;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$
				.post(
						'${pageContext.request.contextPath}/inOutStockController/export',
						params,
						function(result) {
							if (result.success) {
								var j;
								try {//Json解析出错时，异常处理。
									j = $.parseJSON(result.obj);
								} catch (e) {
									parent.$.messager
											.alert(
													'<spring:message code="error"/>',
													'<spring:message code="req_error"/>',
													'error');
									return false;
								}
								var download = "${pageContext.request.contextPath}/excel/export/"
										+ j.name;
								window.top.location.href = download;

							} else {
								$.messager.show({
									title : '<spring:message code="hint"/>',
									msg : result.msg,
									timeout : 5000,
									showType : 'slide'
								});
							}
							parent.$.messager.progress('close');
						}, 'json');
	}
	//入库
	function inStockFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="in"/>',
					width : 600,
					height : 450,
					//method: 'get',
					href : '${pageContext.request.contextPath}/inOutStockController/inStock',
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
	//出库
	function outStockFun() {
			parent.$.modalDialog({
											title : '<spring:message code="out"/>',
											width : 600,
											height : 470,
											href : '${pageContext.request.contextPath}/inOutStockController/outStock',
											buttons : [
													{
														text : '<spring:message code="save"/>',
														iconCls : 'pencil_add',
														plain : true,
														handler : function() {
															parent.$.modalDialog.openner_dataGrid = dataGrid;
															var f = parent.$.modalDialog.handler
																	.find('#form');
															f.submit();
														}
													},
													{
														text : '<spring:message code="cancel"/>',
														iconCls : 'cancel',
														plain : true,
														handler : function() {
															parent.$.modalDialog.handler
																	.dialog('close');
														}
													} ]
										});

	}
	//批入
	function importFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="batch_in"/>',
					closable : false,
					width : 550,
					height : 350,
					href : '${pageContext.request.contextPath}/inOutStockController/importStock',
					buttons : [ {
						text : '<spring:message code="close"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							searchFun();
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//批出
	function exportStock() {
		parent.$
				.modalDialog({
					title : '<spring:message code="batch_out"/>',
					closable : false,
					width : 550,
					height : 440,
					href : '${pageContext.request.contextPath}/inOutStockController/exportStock',
					buttons : [ {
						text : '<spring:message code="close"/>',
						iconCls : 'pencil_add',
						plain : true,
						handler : function() {
							searchFun();
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}
	//撤销
	function undoFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="undo_msg"/>！', 'info');
			return;
		}
		if (row.status == '2') {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="undo_error_msg"/>！', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="undo_message"/>',
						function(b) {
							if (b) {

								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.post(
												'${pageContext.request.contextPath}/inOutStockController/inOutRevoke',
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

	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	//参数类别根据数据字典表获取
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
						<td><spring:message code="equipment_model" /></td>
						<td><spring:message code="batch_no" /></td>
						<td><spring:message code="dealers_name" /></td>
						<td><spring:message code="in_out_flag" /></td>
						<td><spring:message code="status" /></td>
						<td><spring:message code="beginTime" /></td>
						<td><spring:message code="endTime" /></td>
					</tr>
					<tr>
						<td><input id="equipment_model" name="equipment_model"
							type="text" style="height: 15px; width: 120px; margin-top: 10px;"></td>
						<td><input id="batch_no" name="batch_no" type="text"
							style="height: 15px; width: 120px; margin-top: 10px;"></td>
						<td><input id="dealers_name" name="dealers_name" type="text"
							style="height: 15px; width: 120px; margin-top: 10px;"></td>
						<td><select id="flag" name="flag" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto'
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="1"><spring:message code="in" /></option>
								<option value="2"><spring:message code="out" /></option>
						</select></td>
						<td><select id="status" name="status" class="easyui-combobox"
							style="width: 120px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false,
				                panelHeight : 'auto'
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="1"><spring:message code="normal" /></option>
								<option value="2"><spring:message code="undo" /></option>
						</select></td>
						<td><input id="beginTime" name="beginTime" type="text"
							class="easyui-datebox" style="width: 120px; height: 25px;"
							data-options="editable:false"></td>
						<td><input id="endTime" name="endTime" type="text"
							class="easyui-datebox" style="width: 120px; height: 25px;"
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
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/inStock')}">
			<a onclick="inStockFun()" class="easyui-linkbutton"
				data-options="iconCls:'medal_silver_add', plain:true"
				href="javascript:void(0);"><spring:message code="in" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/outStock')}">
			<a onclick="outStockFun()" id="outStock" class="easyui-linkbutton"
				data-options="iconCls:'medal_silver_delete', plain:true"
				href="javascript:void(0);"><spring:message code="out" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/inOutRevoke')}">
			<a onclick="undoFun()" class="easyui-linkbutton"
				data-options="iconCls:'arrow_green', plain:true"
				href="javascript:void(0);"><spring:message code="undo" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/downloadDealersXls')}">
			<a onclick="downloadFun(1)" class="easyui-linkbutton"
				data-options="iconCls:'text_dropcaps', plain:true"
				href="javascript:void(0);"><spring:message code="batch_in_templet" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/importStock')}">
			<a onclick="importFun()" class="easyui-linkbutton"
				data-options="iconCls:'ruby_get', plain:true"
				href="javascript:void(0);"><spring:message code="batch_in" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/downloadDealersXls')}">
			<a onclick="downloadFun(2)" class="easyui-linkbutton"
				data-options="iconCls:'text_dropcaps', plain:true"
				href="javascript:void(0);"><spring:message code="batch_out_templet" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/exportStock')}">
			<a onclick="exportStock()" class="easyui-linkbutton"
				data-options="iconCls:'ruby_put', plain:true"
				href="javascript:void(0);"><spring:message code="batch_out" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/inOutStockController/export')}">
			<a onclick="responseExcel();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_go'"><spring:message
					code="export" /></a>
		</c:if>
	</div>
</body>
</html>
