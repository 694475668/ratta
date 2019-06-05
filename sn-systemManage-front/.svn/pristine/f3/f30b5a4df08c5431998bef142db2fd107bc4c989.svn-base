<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="logsUl">

</ul>
<ul>
<!-- <li><a href="javascript:void(0)" onclick="jump('E:/apache-tomcat-7.0.54/wst_log_2014-11-10.html')">这是什么</a></li> -->
<!-- <li> -->
<!-- 	<a href="E:/apache-tomcat-7.0.54/wst_log_2014-11-10.html">这是直接的测试</a> -->
<!-- </li> -->
<!-- <li><a href="file:///::{20D04FE0-3AEA-1069-A2D8-08002B30309D}" >这是测试吗</a></li> -->
<!-- <li><a href="javascript:void(0);" onclick="addTab('test')">这是测试吗</a></li> -->
</ul>

<script type="text/javascript">

	$(function(){
		$.post("${pageContext.request.contextPath}/commonController/listLogs", function(result){
			if(result.success){
				$("#logsUl").html(result.obj) ;
			}
		}, 'json') ;
	}) ;
	
	// 下载文件
	function responseDownload(filename, path){
		window.location.href="${pageContext.request.contextPath}/commonController/download?path=" + path + "&filename=" + filename ;
	}
	
	
	
	function addTab(title, path) {
		
// 		$.post('${pageContext.request.contextPath}/commonController/showLog?title=' + title + "&path=" + path, function(data){
// 			alert(data) ;
// 		}) ;
		
// 		return ;
		var iframe = '<iframe src="${pageContext.request.contextPath}/commonController/showLog?'
				+ '?title=' + title + '&path=' + path 
				+ '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
// 		var opts = {
// 			title : params.title,
// 			closable : true,
// 			iconCls : params.iconCls,
// 			content : iframe,
// 			border : false,
// 			fit : true
// 		};
		var opts = {
			title : title,
			closable : true,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
// 			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}

	function jump(file){
// 		alert(file) ;
// 		window.location.href = file ;
// 		$.get(file).success(function(content){
// 			alert(content) ;
// 		});
	}


</script>