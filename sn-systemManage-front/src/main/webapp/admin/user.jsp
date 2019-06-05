<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<style>
#searchTable td {
	padding-right: 20px;
}
</style>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/grantPage')}">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/editPwdPage')}">
	<script type="text/javascript">
		$.canEditPwd = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/unlock')}">
	<script type="text/javascript">
		$.canUnLock = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/userController/dataGrid',
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
										width : 150,
										checkbox : true
									},
									{
										field : 'username',
										title : '<spring:message code="username"/>',
										width : 120,
										sortable : true,
										align : 'left',
										halign : 'center'
									},
									{
										field : 'name',
										title : '<spring:message code="name"/>',
										width : 100,
										sortable : true,
										halign : 'center',
										align : 'center'

									},
									{
										field : 'pwd',
										title : '<spring:message code="pwd"/>',
										width : 70,
										formatter : function(value, row, index) {
											return '******';
										},
										hidden : true
									},
									{
										field : 'created',
										title : '<spring:message code="create_date"/>',
										width : 120,
										sortable : true,
										formatter : function(value, row, index) {
											return formatter_datettime(value);
										},
										hidden : true
									},

									{
										field : 'roleIds',
										title : '<spring:message code="roleIds"/>',
										width : 150,
										hidden : true
									},
									{
										field : 'roleNames',
										title : '<spring:message code="roleNames"/>',
										width : 200,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'createdby',
										title : '<spring:message code="create_user"/>',
										width : 50,
										hidden : true
									},
									{
										field : 'phone',
										title : '<spring:message code="tel"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'email',
										title : '<spring:message code="email"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'address',
										title : '<spring:message code="address"/>',
										width : 120,
										align : 'center',
										halign : 'center',
										hidden : true
									},
									{
										field : 'updatedby',
										title : '<spring:message code="modify_user"/>',
										width : 80,
										align : 'center',
										halign : 'center',
										hidden : false
									},
									{
										field : 'updated',
										title : '<spring:message code="modify_date"/>',
										width : 140,
										sortable : true,
										align : 'center',
										halign : 'center',
									/*formatter:function(value,row,index){
										return formatter_datettime(value);
									}
									 */
									},
									{
										field : 'status',
										title : '<spring:message code="status"/>',
										width : 60,
										align : 'center',
										halign : 'center',
										formatter : function(value, rec) {
											if (rec.status == 0) {
												return '<spring:message code="lock"/>';
											}
											if (rec.status == 1) {
												return '<spring:message code="normal"/>';
											}
											if (rec.status == 2) {
												return '<spring:message code="stop"/>';
											}
										}
									},
									{
										field : 'action',
										title : '<spring:message code="operate"/>',
										width : 140,
										align : 'center',
										formatter : function(value, row, index) {
											var str = '';
											if ($.canEdit) {
												str += $
														.formatString(
																'<img onclick="editFun(\'{0}\');" src="{1}" title="<spring:message code="update"/>"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											}
											str += '&nbsp;';
											if ($.canUnLock) {
												str += $
														.formatString(
																'<img onclick="unlockFun(\'{0}\',\'{1}\');" src="{2}" title="<spring:message code="unlock"/>"/>',
																row.id,
																row.status,
																'${pageContext.request.contextPath}/style/images/extjs_icons/lock/lock_open.png');
											}
											str += '&nbsp;';
											if ($.canGrant) {
												str += $
														.formatString(
																'<img onclick="grantFun(\'{0}\',\'{1}\');" src="{2}" title="<spring:message code="impower"/>"/>',
																row.id,
																row.username,
																'${pageContext.request.contextPath}/style/images/extjs_icons/key.png');
											}
											str += '&nbsp;';
											if ($.canDelete) {
												str += $
														.formatString(
																'<img onclick="deleteFun(\'{0}\');" src="{1}" title="<spring:message code="delete"/>"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
											}
											str += '&nbsp;';
											if ($.canEditPwd) {
												str += $
														.formatString(
																'<img onclick="editPwdFun(\'{0}\');" src="{1}" title="<spring:message code="editPwd"/>"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/lock/lock_edit.png');
											}
											return str;
										}
									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$(this).datagrid('unselectAll').datagrid(
										'uncheckAll');
								$(this).datagrid('selectRow', rowIndex);
								$('#menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							}
						});

		$('#roleIds').combotree({
			url : '${pageContext.request.contextPath}/roleController/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			multiple : false,
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			},
			cascadeCheck : false,
			value : $.stringToList('${user.roleIds}')
		});

	});

	//修改密码
	function editPwdFun(id) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$
				.modalDialog({
					title : '<spring:message code="editPwd"/>',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/userController/editPwdPage?id='
							+ id,
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'database_save',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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
	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="delete_message"/>？',
						function(b) {
							if (b) {
								var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
								if (currentUserId != id) {
									parent.$.messager
											.progress({
												title : '<spring:message code="hint"/>',
												text : '<spring:message code="please_latter"/>'
											});
									$
											.post(
													'${pageContext.request.contextPath}/userController/delete',
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
															$.messager
																	.alert(
																			'<spring:message code="hint"/>',
																			result.msg,
																			'info');
														}
														parent.$.messager
																.progress('close');
													}, 'JSON');
								} else {
									parent.$.messager
											.show({
												title : '<spring:message code="hint"/>',
												msg : '<spring:message code="userdelete_msg"/>！'
											});
								}
							}
						});
	}

	//批量删除
	function batchDeleteFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager
					.confirm(
							'<spring:message code="enquire"/>',
							'<spring:message code="delete_message"/>？',
							function(r) {
								if (r) {
									parent.$.messager
											.progress({
												title : '<spring:message code="hint"/>',
												text : '<spring:message code="please_latter"/>'
											});
									var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
									var flag = false;
									for (var i = 0; i < rows.length; i++) {
										if (currentUserId != rows[i].id) {
											ids.push(rows[i].id);
										} else {
											flag = true;
										}
									}
									$
											.getJSON(
													'${pageContext.request.contextPath}/userController/batchDelete',
													{
														ids : ids.join(',')
													},
													function(result) {
														if (result.success) {
															dataGrid
																	.datagrid('load');
															dataGrid
																	.datagrid(
																			'uncheckAll')
																	.datagrid(
																			'unselectAll')
																	.datagrid(
																			'clearSelections');
														}
														if (flag) {
															parent.$.messager
																	.show({
																		title : '<spring:message code="hint"/>',
																		msg : '<spring:message code="userdelete_msg"/>！'
																	});
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
		} else {
			parent.$.messager.show({
				title : '<spring:message code="hint"/>',
				msg : '<spring:message code="checkdelete_msg"/>！'
			});
		}
	}

	//修改
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$
				.modalDialog({
					title : '<spring:message code="update_user"/>',
					width : 500,
					height : 350,
					href : '${pageContext.request.contextPath}/userController/editPage?id='
							+ id,
					buttons : [ {
						text : '<spring:message code="save"/>',
						iconCls : 'database_save',
						plain : true,
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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

	//添加
	function addFun() {
		parent.$.modalDialog({
			title : '<spring:message code="add_user"/>',
			width : 500,
			height : 400,
			href : '${pageContext.request.contextPath}/userController/addPage',
			buttons : [ {
				text : '<spring:message code="save"/>',
				iconCls : 'pencil_add',
				plain : true,
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
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

	//批量授权
	function batchGrantFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		var names = [];
		var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
		if (rows.length > 0) {
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].id != currentUserId) {
					ids.push(rows[i].id);
					names.push(rows[i].username);
				} else {
					$.messager.alert('<spring:message code="hint"/>',
							'<spring:message code="user_impower_msg"/>！',
							'info');
					return;
				}
			}
			//if(currentUserId != id){
			parent.$
					.modalDialog({
						title : '<spring:message code="user_impower"/>',
						width : 500,
						height : 300,
						href : '${pageContext.request.contextPath}/userController/grantPage?ids='
								+ ids.join(',')
								+ '&usernames='
								+ names.join(','),
						buttons : [
								{
									text : '<spring:message code="impower"/>',
									handler : function() {
										parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
										var f = parent.$.modalDialog.handler
												.find('#form');
										f.submit();
									}
								},
								{
									text : '<spring:message code="cancel"/>',
									handler : function() {
										parent.$.modalDialog.handler
												.dialog('close');
									}
								} ]
					});
		} else {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="checkuser_msg"/>！', 'info');
		}
	}

	//授权
	function grantFun(id, username) {
		// 		var rows = dataGrid.datagrid('getSelections');
		// 		id = rows[0].id;

		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
		if (currentUserId != id) {
			parent.$
					.modalDialog({
						title : '<spring:message code="user_impower"/>',
						width : 500,
						height : 300,
						href : '${pageContext.request.contextPath}/userController/grantPage?ids='
								+ id + '&usernames=' + username,
						buttons : [
								{
									text : '<spring:message code="impower"/>',
									handler : function() {
										parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
										var f = parent.$.modalDialog.handler
												.find('#form');
										f.submit();
									}
								},
								{
									text : '<spring:message code="cancel"/>',
									handler : function() {
										parent.$.modalDialog.handler
												.dialog('close');
									}
								} ]
					});
		} else {
			parent.$.messager.show({
				title : '<spring:message code="hint"/>',
				msg : '<spring:message code="user_impower_msg"/>！'
			});
		}
	}

	//启用
	function useFun() {
		var rows = dataGrid.datagrid('getChecked');

		//var ids = [];
		if (rows.length > 0) {

			if (rows[0].status == 1) { //0:锁定，1:正常，2:停用
				$.messager.alert('<spring:message code="hint"/>',
						'<spring:message code="normaluser_msg"/>！', 'info');
				return;
			}
			var ids = [];
			var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
			if (rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].id != currentUserId) {
						ids.push(rows[i].id);
					} else {
						$.messager.alert('<spring:message code="hint"/>',
								'<spring:message code="userenable_msg"/>！',
								'info');
						return;
					}
				}
			}

			parent.$.messager
					.confirm(
							'<spring:message code="enquire"/>',
							'<spring:message code="enable_message"/>？',
							function(r) {
								if (r) {
									parent.$.messager
											.progress({
												title : '<spring:message code="hint"/>',
												text : '<spring:message code="please_latter"/>'
											});
									//var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
									//var flag = false;
									//for (var i = 0; i < rows.length; i++) {
									//if (currentUserId != rows[i].id) {
									//ids.push(rows[i].id);
									//}else{
									//flag = true;
									//}
									//}
									$
											.getJSON(
													'${pageContext.request.contextPath}/userController/batchOpen',
													{
														ids : ids.join(',')
													},
													/* function(result) {
														if (result.success) {
															dataGrid.datagrid('load');
															dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
														}
														if (flag) {
															parent.$.messager.show({title : '提示',msg : '不可以启用自己！'});
														} else {
															parent.$.messager.show({
																title:'提示',
																timeout:3000,
																msg : '启用用户成功'
															});
														}
														parent.$.messager.progress('close');
													}); */

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
														}
														parent.$.messager
																.progress('close');
													});
								}
							});
		} else {
			parent.$.messager.show({
				title : '<spring:message code="hint"/>',
				msg : '<spring:message code="enable_msg"/>！'
			});
		}
	}

	//停用
	function closeFun() {
		var rows = dataGrid.datagrid('getChecked');

		//var ids = [];
		if (rows.length > 0) {

			if (rows[0].status == 2) { //0:锁定，1:正常，2:停用
				$.messager.alert('<spring:message code="hint"/>',
						'<spring:message code="disable_msg"/>！', 'info');
				return;
			}
			var ids = [];
			var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
			if (rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].id != currentUserId) {
						ids.push(rows[i].id);
					} else {
						$.messager.alert('<spring:message code="hint"/>',
								'<spring:message code="userdisable_msg"/>！',
								'info');
						return;
					}
				}
			}

			parent.$.messager
					.confirm(
							'<spring:message code="enquire"/>',
							'<spring:message code="disable_message"/>？',
							function(r) {
								if (r) {
									parent.$.messager
											.progress({
												title : '<spring:message code="hint"/>',
												text : '<spring:message code="please_latter"/>'
											});
									//var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
									//var flag = false;
									//for (var i = 0; i < rows.length; i++) {
									//if (currentUserId != rows[i].id) {
									//ids.push(rows[i].id);
									//} else {
									//flag = true;
									//}
									//}
									$
											.getJSON(
													'${pageContext.request.contextPath}/userController/batchClose',
													{
														ids : ids.join(',')
													},
													/* function(result) {
														if (result.success) {
															dataGrid.datagrid('load');
															dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
														}
														if (flag) {
															parent.$.messager.show({title : '提示',msg : '不可以停用自己！'});
														} else {
															parent.$.messager.show({
																title:'提示',
																timeout:3000,
																msg : '停用用户成功'
															});
														}
														parent.$.messager.progress('close');
													}); */

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
														}
														parent.$.messager
																.progress('close');
													});
								}
							});
		} else {
			parent.$.messager.show({
				title : '<spring:message code="hint"/>',
				msg : '<spring:message code="check_disable_msg"/>！'
			});
		}
	}

	//解锁
	function unlockFun(id, status) {

		// 		var row = dataGrid.datagrid('getSelected');
		// 		if (row == null) {
		// 			$.messager.alert('<spring:message code="hint"/>', '<spring:message code="unlock_message"/>！', 'info');
		// 			return;
		// 		}
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
			status = rows[0].status;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}

		if (status != 0) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="unlock_msg"/>！', 'info');
			return;
		}
		parent.$.messager
				.confirm(
						'<spring:message code="enquire"/>',
						'<spring:message code="unlockuser_msg"/>？',
						function(b) {
							if (b) {
								parent.$.messager
										.progress({
											title : '<spring:message code="hint"/>',
											text : '<spring:message code="please_latter"/>.'
										});
								$
										.post(
												'${pageContext.request.contextPath}/userController/unlock',
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
													}
													parent.$.messager
															.progress('close');
												}, 'JSON');

							}
						});
	}

	function searchFun() {
		dataGrid.datagrid("uncheckAll");
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		dataGrid.datagrid("uncheckAll");
		$('#searchForm input').val('');
		$('#roleIds').combotree('clear');
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
			style="height: 108px; border: solid 0px red; overflow-Y: hidden;">
			<form id="searchForm">
				<table id="searchTable" style="border: solid 0px red; margin: 10px;">
					<tr>
						<td><spring:message code="username" /></td>
						<td><spring:message code="roleIds" /></td>
						<td><spring:message code="status" /></td>
						<td><spring:message code="modify_begin_date" /></td>
						<td><spring:message code="modify_end_date" /></td>
					</tr>
					<tr>
						<td><input id="username" name="username" type="text"
							style="height: 15px; width: 180px; margin-top: 6px;"></td>
						<td><select id="roleIds" name="roleIds" style="width: 180px;"></select>
						<%-- <img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#roleIds').combotree('clear');"/>--%></td>
						<td><select id="status" name="status" class="easyui-combobox"
							style="width: 180px;"
							data-options="
				                valueField:'id',
				                textField:'text',
				                editable:false
				              ">
								<option value=""><spring:message code="all" /></option>
								<option value="0"><spring:message code="lock" /></option>
								<option value="1"><spring:message code="normal" /></option>
								<option value="2"><spring:message code="stop" /></option>
						</select></td>
						<td><input id="modifydatetimeStart"
							name="modifydatetimeStart" type="text" class="easyui-datebox"
							style="width: 200px; height: 23px;" data-options="editable:false">
						</td>
						<td><input id="modifydatetimeEnd" name="modifydatetimeEnd"
							type="text" class="easyui-datebox"
							style="width: 200px; height: 23px;" data-options="editable:false">
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
			test="${fn:contains(sessionInfo.resourceList, '/userController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'"><spring:message
					code="add" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/grantPage')}">
			<a onclick="batchGrantFun();" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="plain:true,iconCls:'tux'"><spring:message
					code="batch_impower" /></a>
		</c:if>
		<%-- 		<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/unlock')}"> --%>
		<%-- 		   <a onclick="unlockFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'tux'"><spring:message code="unlock"/></a> --%>
		<%-- 		</c:if> --%>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/batchOpen')}">
			<a onclick="useFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'brick_go'"><spring:message
					code="start" /></a>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/batchClose')}">
			<a onclick="closeFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'brick_go'"><spring:message
					code="stop" /></a>
		</c:if>
	</div>

	<%-- <div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/addPage')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/delete')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/userController/editPage')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">修改</div>
		</c:if>
	</div> --%>
</body>
</html>