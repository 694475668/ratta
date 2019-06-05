<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看图片</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
    $(function(){
    	var picture = sessionStorage.getItem("picture"); 
    	$("#picture").attr("src","data:image/png;base64,"+picture);
    });    
</script>
</head>
<body>
   <div style="text-align: center; margin: 100px auto;">
      <img id="picture" src="">
   </div>
</body>
</html>