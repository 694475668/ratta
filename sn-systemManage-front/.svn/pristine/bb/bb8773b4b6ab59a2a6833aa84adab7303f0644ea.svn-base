<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>反馈问题记录管理</title>
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
							url : '${pageContext.request.contextPath}/feedbackRecordController/dataGrid',
							method : 'post',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : '',
							sortOrder : 'desc',
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
							nowrap : true,
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
										title : '<spring:message code="typeId"/>',
										field : 'typeId',
										width : 70,
										hidden :true,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'valueOne',
										title : '<spring:message code="valueOne"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'valueTwo',
										title : '<spring:message code="valueTwo"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'valueThree',
										title : '<spring:message code="valueThree"/>',
										width : 150,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'equipment_no',
										title : '<spring:message code="equipment_number"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'contact',
										title : '<spring:message code="contact"/>',
										width : 120,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'createTime',
										title : '<spring:message code="create_date"/>',
										width : 145,
										align : 'center',
										halign : 'center'
									},
									{
										field : 'description',
										title : '<spring:message code="description"/>',
										width : 290,
										align : 'left',
										halign : 'center'
									}] ],
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
		//初始化数据
		$("#valueOne").combobox({
					url : '${pageContext.request.contextPath}/feedbackProblemTypeController/queryValueOne',
					editable : false, //不可编辑状态
					cache : false,
					panelHeight : 'auto',
					height : 24,
					valueField : 'typeId',
					textField : 'valueCn',
					onSelect : selectTwoByOne
		});
		$("#valueTwo").combobox({
			url : '',
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			height : 24,
			valueField : 'typeId',
			textField : 'valueCn'
        });
		$("#valueThree").combobox({
			url : '',
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			height : 24,
			valueField : 'typeId',
			textField : 'valueCn'
        });
	});
	
	//第二级问题类型通过第一级筛选
	function selectTwoByOne() {
		var typeId = $('#valueOne').combobox('getValue');
		$("#valueTwo").combobox({
			url : '${pageContext.request.contextPath}/feedbackProblemTypeController/queryValueTwo?typeId='+typeId,
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			height : 24,
			valueField : 'typeId',
			textField : 'valueCn',
			onSelect : selectThreeByTwo
        });
	}
	//第三级问题类型通过第二级筛选
	function selectThreeByTwo() {
		var typeId = $('#valueTwo').combobox('getValue');
		$("#valueThree").combobox({
			url : '${pageContext.request.contextPath}/feedbackProblemTypeController/queryValueThree?typeId='+typeId,
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : 'auto',
			height : 24,
			valueField : 'typeId',
			textField : 'valueCn'
        });
	}
	//导出
	function responseExcel() {
		var typeId = $('#typeId').val();
		var params = "typeId=" + typeId;
		parent.$.messager.progress({
			title : '<spring:message code="hint"/>',
			text : '<spring:message code="please_latter"/>'
		});
		$.post(
						'${pageContext.request.contextPath}/feedbackRecordController/export',params,
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

	//查询
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}


	//清空
	function cleanFun() {
		$('#searchForm input').val('');
		$('#valueOne').combobox('clear');
		$('#valueTwo').combobox('loadData', {});
		$('#valueThree').combobox('loadData', {});
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
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
						<td><spring:message code="equipment_number" /></td>
						<td><spring:message code="contact" /></td>
						<td><spring:message code="valueOne" /></td>
						<td><spring:message code="valueTwo" /></td>
						<td><spring:message code="valueThree" /></td>
					</tr>
					<tr>
						<td><input name="equipment_no" type="text"
							style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input name="contact" type="text"
						style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="valueOne" name="valueOne" type="text"
						style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="valueTwo" name="valueTwo" type="text"
						style="height: 15px; width: 150px; margin-top: 10px;"></td>
						<td><input id="valueThree" name="valueThree" type="text"
						style="height: 15px; width: 150px; margin-top: 10px;"></td>
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
		<c:if test="${fn:contains(sessionInfo.resourceList, '/feedbackRecordController/export')}">
			<a onclick="responseExcel();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_go'"><spring:message
					code="export" /></a>
		</c:if>
	</div>
</body>
</html>
