<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"   isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/favicon.ico" rel="shortcut icon">
<title>SuperNote业务管理系统</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
    //判断session是否有值，如果没值跳转到login.jsp
	var session = "${sessionInfo}";
	var lang = "${sessionInfo.locale.country}";
	if(session==null||session==""){
		window.location.href="login.jsp";
	}
	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		/*index_layout.layout('collapse', 'east');*/

		index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				index_tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			},
			tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					var href = index_tabs.tabs('getSelected').panel('options').href;
					if (href) {/*说明tab是以href方式引入的目标页面*/
						var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
						index_tabs.tabs('getTab', index).panel('refresh');
					} else {/*说明tab是以content方式引入的目标页面*/
						var panel = index_tabs.tabs('getSelected').panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for ( var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
									try {
										CollectGarbage();
									} catch (e) {
									}
								}
							}
						} catch (e) {
						}
					}
				}
			}, {
				iconCls : 'delete',
				handler : function() {
					var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
					var tab = index_tabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						index_tabs.tabs('close', index);
					} else {
						if("${sessionInfo.locale.country}"=='CN'){
						    $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
						}
					    if("${sessionInfo.locale.country}"=='HK'){
						    $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被關閉！', 'error');
						} 
					    if("${sessionInfo.locale.country}"=='US'){
						    $.messager.alert('hint', '[' + tab.panel('options').title + ']Can not be closed！', 'error');
						} 
					}
				}
			} ]
		});

		index_tabsMenu = $('#index_tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('title');

				if (type === 'refresh') {
					index_tabs.tabs('getTab', curTabTitle).panel('refresh');
					return;
				}

				if (type === 'close') {
					var t = index_tabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
	});

	$(function () {
        var tt,delay=500;
        $('#index_tabs').find('div.tabs-scroller-right,div.tabs-scroller-left').mousedown(function () {
            tt = setInterval(function () { this.trigger('click') }.bind($(this)), delay);
            //tt = setTimeout(function () { this.trigger('click') }.bind($(this)), delay)
        }).mouseup(function () { clearInterval(tt) });
    });
</script>
</head>
<body>
	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>

	<div id="index_layout" style="background-color:#2b2b2b;">
		<div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north.jsp'" style="height: 50px; overflow: hidden;background-color:#2b2b2b;" class="logo"></div>

			
		<c:if test="${sessionInfo.locale.country=='CN' }">
		   <div data-options="region:'west',href:'${pageContext.request.contextPath}/layout/west.jsp',split:true" title="模块导航" style="width: 200px; color:white;overflow: hidden;"></div>
	    </c:if>
	    <c:if test="${sessionInfo.locale.country=='US' }">
		   <div data-options="region:'west',href:'${pageContext.request.contextPath}/layout/west.jsp',split:true" title="Module Navigation" style="width: 200px; overflow: hidden;"></div>
	    </c:if>
	    <c:if test="${sessionInfo.locale.country=='HK' }">
		   <div data-options="region:'west',href:'${pageContext.request.contextPath}/layout/west.jsp',split:true" title="模塊導航" style="width: 200px; overflow: hidden;"></div>
	    </c:if>
	    <c:if test="${sessionInfo.locale.country=='CN' }">
			<div data-options="region:'center'" title="欢迎使用SuperNote业务管理系统" style="overflow: hidden;background-color:#2b2b2b;">
				<div id="index_tabs" style="overflow: hidden;background-color:#2b2b2b;">
					<div title="首页" data-options="border:false" style="overflow: hidden;">
						<iframe src="${pageContext.request.contextPath}/portal/index.jsp" frameborder="0" style="border: 0; width: 100%; height: 100%;"></iframe>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${sessionInfo.locale.country=='US' }">
			<div data-options="region:'center'" title="Welcome to UTMS System" style="overflow: hidden;">
				<div id="index_tabs" style="overflow: hidden;">
					<div title="Home Page" data-options="border:false" style="overflow: hidden;">
						<iframe src="${pageContext.request.contextPath}/portal/index.jsp" frameborder="0" style="border: 0; width: 100%; height: 100%;"></iframe>
					</div>
				</div>
			</div>
		</c:if>
		<!-- 去掉左侧日历和通讯录 -->
<%-- 		<div data-options="region:'east',href:'${pageContext.request.contextPath}/layout/east.jsp'" title="日历" style="width: 230px; overflow: hidden;"></div> --%>
		<%-- <div data-options="region:'south',href:'${pageContext.request.contextPath}/layout/south.jsp',border:false" style="height: 30px; overflow: hidden;"></div> --%>
	</div>
	
<c:if test="${sessionInfo.locale.country=='CN' }">
	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>
</c:if>
<c:if test="${sessionInfo.locale.country=='US' }"> 
	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">refresh</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">close</div>
		<div title="closeOther" data-options="iconCls:'delete'">close other</div>
		<div title="closeAll" data-options="iconCls:'delete'">close all</div>
	</div>
</c:if>
<c:if test="${sessionInfo.locale.country=='HK' }">
	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">關閉</div>
		<div title="closeOther" data-options="iconCls:'delete'">關閉其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">關閉所有</div>
	</div>
</c:if>
</body>
</html>