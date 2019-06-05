<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var layout_west_tree;
	var layout_west_tree_url = '';
	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {
		layout_west_tree_url = '${pageContext.request.contextPath}/resourceController/tree';
	}
	function getUrl(){
	return "${pageContext.request.contextPath}/index.jsp";
	}
	$(function() {
		layout_west_tree = $('#layout_west_tree').tree(
				{
					url : layout_west_tree_url,
					parentField : 'pid',
					//lines : true,
					onClick : function(node) {
						if (node.attributes && node.attributes.url) {
							var url;
							if (node.attributes.url.indexOf('/') == 0) {/*如果url第一位字符是"/"，那么代表打开的是本地的资源*/
								url = '${pageContext.request.contextPath}'
										+ node.attributes.url;
								if (url.indexOf('/druidController') == -1) {/*如果不是druid相关的控制器连接，那么进行遮罩层屏蔽*/
									if("${sessionInfo.locale.country}"=='CN'){
										parent.$.messager.progress({
											title : '提示',
											text : '数据处理中，请稍后。。。'
										});
									}
									if("${sessionInfo.locale.country}"=='HK'){
										parent.$.messager.progress({
											title : '提示',
											text : '數據處理中，請稍後。。。'
										});
									}
									if("${sessionInfo.locale.country}"=='US'){
										parent.$.messager.progress({
											title : 'hint',
											text : 'data processing,please later...'
										});
									}
								}
							} else {/*打开跨域资源*/
								url = node.attributes.url;
							}
							addTab({
								url : url,
								title : node.text,
								iconCls : node.iconCls
							});
						}
					},
					onBeforeLoad : function(node, param) {
						if (layout_west_tree_url) {//只有刷新页面才会执行这个方法
							if("${sessionInfo.locale.country}"=='CN'){
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后。。。'
								});
							}
							if("${sessionInfo.locale.country}"=='HK'){
								parent.$.messager.progress({
									title : '提示',
									text : '數據處理中，請稍後。。。'
								});
							}
							if("${sessionInfo.locale.country}"=='US'){
								parent.$.messager.progress({
									title : 'hint',
									text : 'data processing,please later...'
								});
							}
						}
					},
					onLoadSuccess : function(node, data) {
						parent.$.messager.progress('close');
					},
					onLoadError:function(arguments){
						parent.$.messager.progress('close');
						window.location.href = getUrl();
					}
				});
	});

	function addTab(params) {
		var iframe = '<iframe src="'
				+ params.url
				+ '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div class="easyui-accordion" data-options="fit:true,border:false" >

 <c:if test="${sessionInfo.locale.country=='CN' }">
	<div title="系统菜单" style="padding: 5px;background-color:#2b2b2b;color:white"
		data-options="border:false,isonCls:'anchor',tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'resultset_next',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'resultset_previous',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<div  style="padding: 2px">
			<ul id="layout_west_tree" style="color:white;background-color:#2b2b2b;">
			</ul>
		</div>
	</div>
</c:if>
 <c:if test="${sessionInfo.locale.country=='US' }">
	<div title="System Menu" style="padding: 5px;"
		data-options="border:false,isonCls:'anchor',tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'resultset_next',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'resultset_previous',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<div class="well well-small" style="background-color:#2b2b2b;">
			<ul id="layout_west_tree" style="color:white;background-color:#2b2b2b;">
			</ul>
		</div>
	</div>
</c:if>
<c:if test="${sessionInfo.locale.country=='HK' }">
	<div title="系統菜單" style="padding: 5px;"
		data-options="border:false,isonCls:'anchor',tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'resultset_next',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'resultset_previous',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<div class="well well-small">
			<ul id="layout_west_tree">
			</ul>
		</div>
	</div>
</c:if>

</div>