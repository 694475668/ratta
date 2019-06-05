<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/snuserController/unBund')}">
	<script type="text/javascript">
		$.canUnbund = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/mina/euipment_push')}">
	<script type="text/javascript">
		$.canLock = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/snuserController/detail')}">
	<script type="text/javascript">
		$.canDetail = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/snuserController/dataGrid',
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
										field : 'userid',
										title : '<spring:message code="id"/>',
										width : 30,
										hidden : true
									},
									{
										field : 'username',
										title : '<spring:message code="nickname"/>',
										width : 150,
										sortable : true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'countryCode',
										title : '<spring:message code="country_code"/>',
										width : 150,
										halign : 'center',
										align : 'center'

									},
									{
										field : 'telephone',
										title : '<spring:message code="tel"/>',
										width : 150,
										halign : 'center',
										align : 'center'

									},
									{
										field : 'email',
										title : '<spring:message code="email"/>',
										width : 200,
										halign : 'center',
										align : 'center'
									},
									/* {
										field : 'wechatno',
										title : '<spring:message code="wechatno"/>',
										width : 150,
										halign : 'center',
										align : 'center'
									}, */

									{
										field : 'createtime',
										title : '<spring:message code="regist_date"/>',
										width : 170,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'isNormal',
										title : '<spring:message code="status"/>',
										width : 100,
										align : 'center',
										halign : 'center',
										formatter : function(value, row) {
											if (row.isNormal == 'Y') {
												return '<spring:message code="normal"/>';
											}
											if (row.isNormal == 'N') {
												return '<spring:message code="Freeze"/>';
											}
										}
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
																'<img onclick="detail(\'{0}\');" src="{1}" title="<spring:message code="detail"/>"/>',
																row.userid,
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


							detailFormatter : function(index, row) {
								return '<div style="padding:2px"><table class="ddv"></table></div>';
							},


						});
	});

	//锁定设备
	/* function lockFun(equipment_number) {
		$.post('${pageContext.request.contextPath}/mina/euipment_push', {
			equipment_number : equipment_number,
			msg : '01'
		}, function(result) {
			if (result.success) {
				parent.$.messager.show({
					title : '<spring:message code="hint"/>',
					timeout : 3000,
					msg : result.msg
				});
				dataGrid.datagrid('reload');
			} else {
				parent.$.messager.show({
					title : '<spring:message code="hint"/>',
					timeout : 3000,
					msg : result.msg
				});
			}
		}, 'JSON');
	}

	//解锁设备
	function UnlockFun(equipment_number) {
		$.post('${pageContext.request.contextPath}/mina/euipment_push', {
			equipment_number : equipment_number,
			msg : '02'
		}, function(result) {
			if (result.success) {
				parent.$.messager.show({
					title : '<spring:message code="hint"/>',
					timeout : 3000,
					msg : result.msg
				});
				dataGrid.datagrid('reload');
			} else {
				parent.$.messager.show({
					title : '<spring:message code="hint"/>',
					timeout : 3000,
					msg : result.msg
				});
				dataGrid.datagrid('reload');
			}
		}, 'JSON');
	}
 */
	//冻结
	function freezeFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="check_freeze_msg"/>！', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="freeze_message"/>？',
						function(r) {
							if (r) {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.getJSON(
												'${pageContext.request.contextPath}/snuserController/Freeze',
												{
													id : row.userid
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
													}
													parent.$.messager
															.progress('close');
												});
							}
						});
	}

	//解冻
	function thawFun() {
		var row = dataGrid.datagrid('getSelected');
		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="check_thaw_msg"/>！', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="thaw_message"/>？',
						function(r) {
							if (r) {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.getJSON(
												'${pageContext.request.contextPath}/snuserController/Thaw',
												{
													id : row.userid
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
													}
													parent.$.messager
															.progress('close');
												});
							}
						});
	}

	//修改账号
	function updateTelephone() {
		var row = dataGrid.datagrid('getSelected');

		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="update_message"/>！', 'info');
			return;
		}
		if(row.telephone ==  undefined || row.telephone == ''){
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="telephone_is_null"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="updateTelephone"/>',
					width : 600,
					height : 250,
					href : '${pageContext.request.contextPath}/snuserController/updateAccount?id='
							+ row.userid,
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
	//修改邮箱
	function updateEmail() {
		var row = dataGrid.datagrid('getSelected');

		if (row == null) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="update_message"/>！', 'info');
			return;
		}
		if(row.email == undefined || row.email == ''){
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="email_is_null"/>！', 'info');
			return;
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="updateEmail"/>',
					width : 600,
					height : 250,
					href : '${pageContext.request.contextPath}/snuserController/updateEmail?id='
							+ row.userid,
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
	//解绑
	function UnbundFun(id) {
		ddv.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="unbund_msg"/>？',
						function(b) {
							if (b) {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>'
										});
								$
										.post(
												'${pageContext.request.contextPath}/snuserController/unBund',
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

	//详情
	function detail(userid) {
		parent.$
				.modalDialog({
					title : '<spring:message code="detail"/>',
					width : 800,
					height : 500,
					href : '${pageContext.request.contextPath}/snuserController/detail?id='
							+ userid,
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

	function searchFun() {
		dataGrid.datagrid("uncheckAll");
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		dataGrid.datagrid("uncheckAll");
		$('#searchForm input').val('');
		var isNormal = $("#isNormal").combobox('getData');
		$("#isNormal").combobox('select', isNormal[0].id);
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
						<td><spring:message code="username" /></td>
						<td><spring:message code="tel" /></td>
						<td><spring:message code="status" /></td>
						<td><spring:message code="regist_begin_date" /></td>
						<td><spring:message code="regist_end_date" /></td>
					</tr>
					<tr>
						<td><input id="username" name="username" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><input id="telephone" name="telephone" type="text"
							style="height: 15px; width: 180px; margin-top: 10px;"></td>
						<td><select id="isNormal" name="isNormal"
							class="easyui-combobox" style="width: 180px; height: 25px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="Y"><spring:message code="normal" /></option>
								<option value="N"><spring:message code="Freeze" /></option>
						</select></td>
						<td><input id="modifydatetimeStart"
							name="modifydatetimeStart" type="text" class="easyui-datebox"
							style="width: 200px; height: 25px;" data-options="editable:false">
						</td>
						<td><input id="modifydatetimeEnd" name="modifydatetimeEnd"
							type="text" class="easyui-datebox"
							style="width: 200px; height: 25px;" data-options="editable:false">
						</td>
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
			test="${fn:contains(sessionInfo.resourceList, '/snuserController/updateAccount')}">
			<a onclick="updateTelephone();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'color_swatch'"><spring:message
					code="updateTelephone" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/snuserController/updateEmail')}">
			<a onclick="updateEmail();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'server_edit'"><spring:message
					code="updateEmail" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/snuserController/Freeze')}">
			<a onclick="freezeFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'status_away'"><spring:message
					code="Freeze" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/snuserController/Thaw')}">
			<a onclick="thawFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'status_online'"><spring:message
					code="Thaw" /></a>
		</c:if>
	</div>

</body>
</html>