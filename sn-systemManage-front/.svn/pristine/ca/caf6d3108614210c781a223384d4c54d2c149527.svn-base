<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$("#userInfo").datagrid({
			width :650,
			url : '',
			method : 'post',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : '',
			sortOrder : 'desc',
			idField : 'userid',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			toolbar : '#serch',
			rownumbers : true,
			singleSelect : true,
			pagination : true,
			columns : [ [ {
				field : 'userid',
				title : '<spring:message code="id"/>',
				width : 30,
				checkbox : true
			}, {
				field : 'telephone',
				title : '<spring:message code="phone"/>',
				width : 285,
				align : 'center',
				halign : 'center'
			}, {
				field : 'email',
				title : '<spring:message code="email"/>',
				width : 285,
				align : 'center',
				halign : 'center'
			}] ],
		});
		
	});
	
	function selectData() {
		var telephone = $("#telephone").val();
		var email = $("#email").val();
		$("#userInfo").datagrid(
								{
									width : 650,
									url : '${pageContext.request.contextPath}/snuserController/dataGrid?telephone='
											+ telephone
											+ '&email='
											+ email,
									method : 'post',
									pageSize : 10,
									pageList : [ 10, 20, 30, 40, 50 ],
									sortName : '',
									sortOrder : 'desc',
									idField : 'userid',
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
											checkbox : true
										}, {
											field : 'telephone',
											title : '<spring:message code="phone"/>',
											width : 285,
											align : 'center',
											halign : 'center'
										}, {
											field : 'email',
											title : '<spring:message code="email"/>',
											width : 285,
											align : 'center',
											halign : 'center'
										}] ],
								});
	}
	
	function clearSerchData() {
		$('#telephone').val('');
		$('#email').val('');
		var rows = $("#userInfo").datagrid("getRows");
		var length = rows.length;
		onUncheckAllMR(length);
		$('#userInfo').datagrid('loadData', {
			total : 0,
			rows : []
		});
	}
	
	//清理列表选中
	function onUncheckAllMR(length) {
		for (var i = 0; i < length; i++) {
			$("#tid").datagrid('uncheckRow', i);
		}

	}
	
	function saveData() {
		var rows = $("#userInfo").datagrid("getChecked");
		var id = $("#id").val();
		if (rows.length == 0) {
			$.messager.alert('<spring:message code="hint"/>',
					'<spring:message code="select_userid"/>', 'info');
			return;
		}
		var copyRows = [];
		for (var j = 0; j < rows.length; j++) {
			copyRows.push(rows[j].userid);
		}

		params = "id=" + id + "&userid=" + copyRows;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$.post(
						'${pageContext.request.contextPath}/appVersionController/gray',
						params, function(result) {
							if (result.success) {
								parent.$.messager.show({
									title : '<spring:message code="hint"/>',
									timeout : 3000,
									msg : result.msg
								});
								parent.$.messager.progress('close');
							} else {
								parent.$.messager.show({
									title : '<spring:message code="hint"/>',
									timeout : 3000,
									msg : result.msg
								});
							}
						}, 'JSON');
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow-x:hidden;">
		<form id="form" method="post">
			<input id="id" name="id" type="hidden" value="${id }">
			<div>
                <div id="serch">
                  <table id="searchTable" style="border: solid 0px red; margin: 5px;">
					<tr>
						<td><input id="telephone" name="phone" placeholder="手机号..."
							type="text" style="margin-top: 10px;"></td>
						<td><input id="email" name="email" type="text" placeholder="电子邮箱..."
							style="margin-top: 10px;">
							<a href="javascript:void(0)" class="easyui-linkbutton"  title="搜索"
							data-options="iconCls:'icon-search'" onclick="selectData()"></a>&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"  title="清空"
							data-options="iconCls:'cancel'"  onclick="clearSerchData()"></a>
							<a href="javascript:void(0)" class="easyui-linkbutton"  title="添加"
							data-options="iconCls:'add'"  onclick="saveData()"></a></td>
					</tr>
				 </table>
				</div>
				<table id="userInfo"></table>  
			</div>
		</form>
	</div>

</div>