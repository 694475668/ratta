<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>经销商管理</title>
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
							url : '${pageContext.request.contextPath}/dealersController/dataGrid',
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
										field : 'dealersName',
										title : '<spring:message code="dealers_name"/>',
										width : 200,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'dealersType',
										title : '<spring:message code="dealers_type"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'contact',
										title : '<spring:message code="contact"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},

									{
										field : 'phone',
										title : '<spring:message code="phone"/>',
										width : 110,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'address',
										title : '<spring:message code="warehouse_address"/>',
										width : 250,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'applicationTime',
										title : '<spring:message code="apply_time"/>',
										width : 100,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'salesman',
										title : '<spring:message code="salesman"/>',
										width : 80,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'remark',
										title : '<spring:message code="remark"/>',
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
		$("#dealersType").combobox(
				{
					url : '${pageContext.request.contextPath}/dictionaryController/queryDictionary?name='
							+ 'DEALERS_TYPE',
					editable : false, //不可编辑状态
					cache : false,
					panelHeight : 'auto',
					height : 24,
					width : 150,
					valueField : 'value',
					textField : 'value_cn'
		});	
	});
	//添加
	function addFun() {
		parent.$
				.modalDialog({
					title : '<spring:message code="add"/>',
					width : 600,
					height : 480,
					//method: 'get',
					href : '${pageContext.request.contextPath}/dealersController/addDealers',
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
					height : 480,
					href : '${pageContext.request.contextPath}/dealersController/editDealers?id='
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
												'${pageContext.request.contextPath}/dealersController/delete',
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

	//下载
	function downloadFun() {
		$
				.download(
						'${pageContext.request.contextPath}/dealersController/downloadDealersXls',
						'tyvtgvbygvyv', 'post');
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
	//弹出选择文件框
	function selectFile() {
		//触发 文件选择的click事件  
		$("#file").trigger("click");
	}
	// 导入
	function importFun() {
		$
				.ajaxFileUpload({
					url : '${pageContext.request.contextPath}/dealersController/upload',
					type : 'post',
					secureuri : false,
					fileElementId : 'file',
					dataType : 'JSON',
					success : function(data, status) {
						//去掉<pre></pre>标签
						var start = data.indexOf(">");
						if (start != -1) {
							var end = data.indexOf("<", start + 1);
							if (end != -1) {
								data = data.substring(start + 1, end);
							}
						}
						//转化为JSON对象
						data = eval('(' + data + ')');
						//alert(data);
						$.messager.alert('<spring:message code="hint"/>',
								data.msg, 'info');
						if (data.success) {
							searchFun();
						}
					},
					complete : function(xmlHttpRequest) {
						//这里将input替换掉重新添加change事件  
						$("#file")
								.replaceWith(
										'<input type="file" id="file" name="file" onchange="importFun();" accept=".xls,.xlsx" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/>');
					},
					error : function(data, status, e) {
						alert(e);
					}
				});
		return false;
	}
	//导出
	function responseExcel() {
		var dealersName = $('#dealersName').val();
		var contact = $('#contact').val();
		var phone = $('#phone').val();
		var dealersType = $('#dealersType').combobox("getValue");
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var params = "dealersName=" + dealersName + "&contact=" + contact
				+ "&phone=" + phone + "&dealersType=" + dealersType + "&beginTime="
				+ beginTime + "&endTime=" + endTime;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$
				.post(
						'${pageContext.request.contextPath}/dealersController/export',
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

	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
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
						<td><spring:message code="dealers_name" /></td>
						<td><spring:message code="contact" /></td>
						<td><spring:message code="phone" /></td>
						<td><spring:message code="dealers_type" /></td>
						<td><spring:message code="beginTime" /></td>
						<td><spring:message code="endTime" /></td>
					</tr>
					<tr>
						<td><input id="dealersName" name="dealersName" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="contact" name="contact" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="phone" name="phone" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="dealersType" name="dealersType" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="beginTime" name="beginTime" type="text"
							class="easyui-datebox" style="width: 150px; height: 25px;"
							data-options="editable:false"></td>
						<td><input id="endTime" name="endTime" type="text"
							class="easyui-datebox" style="width: 150px; height: 25px;"
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
			test="${fn:contains(sessionInfo.resourceList, '/dealersController/addDealers')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/dealersController/editDealers')}">
			<a onclick="editFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil', plain:true"
				href="javascript:void(0);"><spring:message code="update" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/dealersController/delete')}">
			<a onclick="deleteFun()" id="" class="easyui-linkbutton"
				data-options="iconCls:'pencil_delete', plain:true"
				href="javascript:void(0);"><spring:message code="delete" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/dealersController/downloadDealersXls')}">
			<a onclick="downloadFun()" class="easyui-linkbutton"
				data-options="iconCls:'pencil', plain:true"
				href="javascript:void(0);"><spring:message code="download" /></a>
		</c:if>
		<input type="file" id="file" name="file" onchange="importFun();"
			accept=".xls,.xlsx"
			style="filter: alpha(opacity = 0); opacity: 0; width: 0; height: 0;" />
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/dealersController/upload')}">
			<a onclick="selectFile();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_go'"><spring:message
					code="import" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/dealersController/export')}">
			<a onclick="responseExcel();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_go'"><spring:message
					code="export" /></a>
		</c:if>
	</div>
</body>
</html>
