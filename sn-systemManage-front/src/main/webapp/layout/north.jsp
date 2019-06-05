<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	/**
	 * 
	 * @requires jQuery,EasyUI,jQuery cookie plugin
	 * 
	 * 更换EasyUI主题的方法
	 * 
	 * @param themeName
	 *            主题名称
	 */
	function changeThemeFun(themeName) {
		if ($.cookie('easyuiThemeName')) {
			$('#layout_north_pfMenu').menu('setIcon', {
				target : $('#layout_north_pfMenu div[title=' + $.cookie('easyuiThemeName') + ']')[0],
				iconCls : 'emptyIcon'
			});
		}
		$('#layout_north_pfMenu').menu('setIcon', {
			target : $('#layout_north_pfMenu div[title=' + themeName + ']')[0],
			iconCls : 'tick'
		});

		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);

		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				try {
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				} catch (e) {
					try {
						ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
					} catch (e) {
					}
				}
			}
		}

		$.cookie('easyuiThemeName', themeName, {
			expires : 7
		});

	};

	function logoutFun(b) {
		$.getJSON('${pageContext.request.contextPath}/userController/logout', {
			t : new Date()
		}, function(result) {
			if (b) {
				location.replace('${pageContext.request.contextPath}/');
			} else {
				$('#sessionInfoDiv').html('');
				$('#loginDialog').dialog('open');
				$('#loginDialog input').val('');
			}
		});
	}

	function editCurrentUserPwd() {
		if("${sessionInfo.locale.country}"=='CN'){
			parent.$.modalDialog({
				title : '修改密码',
				width : 400,
				height : 250,
				href : '${pageContext.request.contextPath}/userController/editCurrentUserPwdPage',
				buttons : [ {
					text : '修改',
					handler : function() {
						var f = parent.$.modalDialog.handler.find('#editCurrentUserPwdForm');
						f.submit();
					}
				} ]
			});
		}
		if("${sessionInfo.locale.country}"=='US'){
			parent.$.modalDialog({
				title : 'Change Password',
				width : 400,
				height : 250,
				href : '${pageContext.request.contextPath}/userController/editCurrentUserPwdPage',
				buttons : [ {
					text : 'Modify',
					handler : function() {
						var f = parent.$.modalDialog.handler.find('#editCurrentUserPwdForm');
						f.submit();
					}
				} ]
			});
		}
	}
	function currentUserRole() {
		if("${sessionInfo.locale.country}"=='CN'){
			parent.$.modalDialog({
				title : '我的角色',
				width : 300,
				height : 250,
				href : '${pageContext.request.contextPath}/userController/currentUserRolePage'
			});
		}
		if("${sessionInfo.locale.country}"=='US'){
			parent.$.modalDialog({
				title : 'My Role',
				width : 300,
				height : 250,
				href : '${pageContext.request.contextPath}/userController/currentUserRolePage'
			});
		}
	}
	function currentUserResource() {
		if("${sessionInfo.locale.country}"=='CN'){
			parent.$.modalDialog({
				title : '我可以访问的资源',
				width : 300,
				height : 250,
				href : '${pageContext.request.contextPath}/userController/currentUserResourcePage'
			});
		}
		if("${sessionInfo.locale.country}"=='US'){
			parent.$.modalDialog({
				title : 'I can access the resources',
				width : 300,
				height : 250,
				href : '${pageContext.request.contextPath}/userController/currentUserResourcePage'
			});
		}
	}
	
	//语言切换
	function CNLanguage(language){
	    $.post('${pageContext.request.contextPath}/global/change', 
	    {
			langType : language
		},
		function() {
			window.location.href = "${pageContext.request.contextPath}/index.jsp" ;
		});

	} 
</script>
<c:if test="${sessionInfo.locale.country=='CN' }">
	<%-- <div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;border-style:none;color:white;background-color:#2b2b2b;" class="alert alert-info">
		<c:if test="${sessionInfo.id != null}">[<strong>${sessionInfo.name}</strong>]，欢迎你！您使用 [<strong>${sessionInfo.ip}</strong>] IP登录</c:if>
	</div> --%>
	<div style="position: absolute; right: 0px; bottom: 0px;color:red;">
		<a style="color:white;"
		href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a> 
		<a style="color:white;"  
		href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">注销</a>
	</div>
</c:if>
	<div id="layout_north_langue" style="width: 100px; display: none;">
		<div onclick="CNLanguage('en');">English</div>
		<div class="menu-sep"></div>
		<div onclick="CNLanguage('zh_CN');">简体中文</div>
		<div class="menu-sep"></div>
		<div onclick="CNLanguage('zh_HK');">繁體中文</div>
	</div>

<c:if test="${sessionInfo.locale.country=='US' }">
	<div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;" class="alert alert-info">
		<c:if test="${sessionInfo.id != null}">[<strong>${sessionInfo.name}</strong>]， Welcome！You are using [<strong>${sessionInfo.ip}</strong>] to login</c:if>
	</div>
	<div style="position: absolute; right: 0px; bottom: 0px;">
		<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">Control Panel</a> 
		<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">Log out</a>
	</div>
</c:if>
<c:if test="${sessionInfo.locale.country=='CN' }">
	<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
		<div onclick="editCurrentUserPwd();">修改密码</div>
		<div class="menu-sep"></div>
		<div onclick="currentUserRole();">我的角色</div>
		<div class="menu-sep"></div>
		<div onclick="currentUserResource();">我的权限</div>
		<div class="menu-sep"></div>
		<div title="1.1.1.20190314_release" id="tplink">版本号</div>
	</div>
	<div id="layout_north_zxMenu" style="width: 100px; display: none;">
<!-- 		<div onclick="logoutFun();">锁定窗口</div> -->
		<div class="menu-sep"></div>
		<div onclick="logoutFun(true);">重新登录</div>
		<div onclick="logoutFun(true);">退出系统</div>
	</div>
</c:if>
<c:if test="${sessionInfo.locale.country=='US' }">
	<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
		<div onclick="editCurrentUserPwd();">Change Password</div>
		<div class="menu-sep"></div>
		<div onclick="currentUserRole();">My Role</div>
		<div class="menu-sep"></div>
		<div onclick="currentUserResource();">My Permission</div>
		<div class="menu-sep"></div>
		<div title="1.0.3.20180903_release">Version</div>
	</div>
	<div id="layout_north_zxMenu" style="width: 100px; display: none;">
<!-- 		<div onclick="logoutFun();">Window Lock</div> -->
		<div class="menu-sep"></div>
		<div onclick="logoutFun(true);">Relogin</div>
		<div onclick="logoutFun(true);">Logout</div>
	</div>
</c:if>
