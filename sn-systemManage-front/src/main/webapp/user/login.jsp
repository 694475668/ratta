<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" charset="utf-8">
	var loginDialog;
	var defaultUserInfoDialog;
	var lang ="${sessionInfo.locale.country}";
	$(function() {
		loginDialog = $('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			buttons : [ 
			{
				id:"login_btn",
				text : lang=='CN'?"登录":(lang=="HK"?"登錄":"login"),
				handler : function() {
					loginFun();
				}
			} ]
		});

		defaultUserInfoDialog = $('#defaultUserInfoDialog').show().dialog({
			top : 0,
			left : 200
		});
		var sessionInfo_userId = '${sessionInfo.id}';
		var sessionInfo_userName = '${sessionInfo.name}' ;
		var session_changePwd = '${changePwd}' ;
		
		if (sessionInfo_userId) {/*目的是，如果已经登陆过了，那么刷新页面后也不需要弹出登录窗体*/
			loginDialog.dialog('close');
			defaultUserInfoDialog.dialog('close');
			if(session_changePwd == "true" && sessionInfo_userName != "admin"){
					changePWD() ;		// 强制修改密码
			}
		}

		$('#loginDialog input').keyup(function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		});
	});
	function loginFun() {
		if (layout_west_tree) {//当west功能菜单树加载成功后再执行登录
// 			loginTabs = $('#loginTabs').tabs('getSelected');//当前选中的tab
// 			var form = loginTabs.find('form');//选中的tab里面的form
			var form = $("#loginForm") ;
			if (form.form('validate')) {
				parent.$.messager.progress({
					title : '<spring:message code="hint"/>',
					text : '<spring:message code="please_latter"/>'
				});
				$.post('${pageContext.request.contextPath}/userController/login', form.serialize(), function(result) {
					if (result.success) {
						if (!layout_west_tree_url) {
							layout_west_tree.tree({
								url : '${pageContext.request.contextPath}/resourceController/tree',
								onBeforeLoad : function(node, param) {
									parent.$.messager.progress({
										title : '<spring:message code="hint"/>',
										text : '<spring:message code="please_latter"/>'
									});
								}
							});
						} 
						
						var j;
						try{
							j = $.parseJSON(result.obj);
						}
						catch(e){
							parent.$.messager.progress('close');
							parent.$.messager.alert('<spring:message code="error"/>', '<spring:message code="req_error"/>', 'error');
							return false;
						}
						
						$('#loginDialog').dialog('close');
						$('#sessionInfoDiv').html($.formatString('[<strong>{0}</strong>]，欢迎你！您使用[<strong>{1}</strong>]IP登录！', j.name, j.ip));
	
						// 是否需要修改密码
						// add by @Troy 2014-09-22 15:51:12
						if(j.changePwd == "true"){
							// 如果不是 admin 用户，强制修改密码
							if(j.name != "admin"){
								// 强制修改密码
								changePWD() ;
							}
						}
						
					} 
					parent.$.messager.progress('close');
				}, "JSON");
			}
		}
	}
	
	// 强制修改密码
	function changePWD(){
		parent.$
		.modalDialog({
			title : lang=='CN'?"修改密码":(lang=="HK"?"修改密碼":"modify password"),
			width : 400,
			height : 250,
			href : '${pageContext.request.contextPath}/userController/editCurrentUserPwdPage',
			closed: false,   
			closable : false,
			closeOnEscape: false,
			buttons : [ {
				text : lang=='CN'?"修改":(lang=='HK'?'修改':'modify'),
				handler : function() {
// 					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#editCurrentUserPwdForm');
					f.submit();
				}
			}
			/* 
			,{
				text:lang=='CN'?'取消':(lang=='HK'?'取消':'cancel'),
				handler:function(){
					parent.$.modalDialog.handler.dialog('close');
				}
			}
			 */
			]
		});
	}
</script>

