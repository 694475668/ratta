<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<div>
		<h4>
			${msg} ${msg1}
			&nbsp;&nbsp;
				<label id="timeout" style="display: inline; font-size: x-large;">3</label>
			&nbsp;&nbsp;
		${msg2}</h4>
	</div>
<script type="text/javascript" charset="utf-8">
	try {
		parent.$.messager.progress('close');
	} catch (e) {
		alert("加载超时！")
	}
	
	setInterval(go, 1000);
	
	var x = 3; //利用了全局变量来执行 
	function go() {
		x--;
		if (x >= 0) {
			document.getElementById("timeout").innerHTML = x; //每次设置的x的值都不一样了。 
		} else {
			//top.location.href = "${pageContext.request.contextPath}";
			top.location.reload();
		}
	}
</script>
