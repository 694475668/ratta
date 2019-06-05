<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="java.util.Locale"%>  
<%@ page  import="javax.servlet.http.HttpServletRequest"%>  
<%  
	  request.setAttribute("msg", "用户名");
	  request.setAttribute("msg1", "密码");
	  request.setAttribute("msg2", "验证码");
	  request.setAttribute("msg3", "登录");
	  request.setAttribute("msg4", "验证码错误,请重新输入!");
	  request.setAttribute("msg5", "登录中......");
%> 
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/favicon.ico" rel="shortcut icon">
<title>SuperNote业务管理系统</title>
<script src="${pageContext.request.contextPath}/jslib/jquery-print/jquery-1.10.2.js" type="text/javascript" charset="utf-8"></script>
<link href="${pageContext.request.contextPath}/jslib/bootstrap3/css/bootstrap.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/jslib/bootstrap-2.3.1/css/templatemo_style.css" rel="stylesheet">

<script charset="utf-8" src="${pageContext.request.contextPath}/jslib/bootstrap3/js/bootstrap.min.js" charset="utf-8"></script>

</head>
<body style="overflow-y: hidden;background: url(${pageContext.request.contextPath}/style/images/bg.jpg) fixed center center no-repeat;background-size: cover;width: 100%;">
<div class="container-fluid">
	<div class="row" align="center">
		<div class="col-xs-4 col-sm-4 col-md-4">
			<div style="padding-top: 80px;">
			   <div style="margin-bottom:15px;">
			     <p style="color:white;font-weight:bold;font-size:50px;">登录</p>
		       </div>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" method="post" id="form">				
		        <div class="form-group">
		          <div class="col-xs-12 col-md-12 col-sm-12 ">		            
		            <div class="control-wrapper">
		            	<input type="text" class="form-control" style="height:40px;" id="name" name="username" placeholder="${msg}" value=""/>
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12 col-xs-12 col-sm-12 ">
		          	<div class="control-wrapper">
		            	<input type="password" class="form-control" style="height:40px;" id="pwd" name="pwd" placeholder="${msg1}" value =""/>
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="control-wrapper">
		          	<div class="row">
                    	<div class="col-xs-3 col-sm-3 col-md-3" style="margin-left:15px;">
                          <input type="text" class="form-control" style="width:210%;height:40px;" id="inputValidCode" name="vadCode" placeholder="${msg2}">
						</div>
						<div class="col-xs-3 col-sm-3 col-md-3" style="margin-left:22%;">
                          <img alt="验证码" id="vadCode" style="width:210%;height:40px;"
                                                 src="${pageContext.request.contextPath}/captchaController/createImage" onclick="changeImg(this)">
						</div>
		          	</div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12 col-xs-12 col-sm-12 ">
		          	<div class="control-wrapper">
  				        <div id="valid" style="color:red;display:none;">验证码输入错误</div>
						<div style="color:red;">${message}</div>
						
						<div id="loading" align="left" style="display: none;">
							<img alt="登陆中....." style="width: 30px;height: 30px;" src="${pageContext.request.contextPath}/style/images/login.gif" />
							<label id="login">${msg5}</label>
						</div>
						
						<div  align="left" style="float: left;display: inline-block;">
							<label id="error2" style="color: red"></label>
							<input type="hidden" value="${msg4}" id="codeError"/>
						</div>
						
					</div>
		          </div>
		        </div>		        
		        <div class="form-group">
		          	<div class="control-wrapper">
		          	    <div class="col-md-12 col-xs-12 col-sm-12">
		          			<input id="b_login" type="button"  value="${msg3}" class="btn btn-info col-xs-9 col-sm-9 col-md-9" style="margin-left:30px;border-radius:8px;font-weight:bold;background-color:#2b2b2b;font-size:20px;border-color:#2b2b2b;" onclick="validCode();">
		          		</div>
		          </div>
		        </div>
		        <hr>
		      </form>
		      </div>
		</div>
		</div>
</div>
	<script type="text/javascript" charset="utf-8">
	
	$(function(){
		// 回车事件
		// @Troy 2014-09-10 17:52:19
		$('#inputValidCode').keyup(function(event) {
			if (event.keyCode == '13') {
				validCode();
			}
		});
		
		
	}) ;
	
	 function loginFun() {
			var form = $("#form") ;
				$("#b_login").attr("disabled",true);
				$("#loading").show();
				$("#error2").html("");
				$.post('${pageContext.request.contextPath}/userController/login', form.serialize(), function(result) {
					 if (result.success) {
						 	window.location.href = "${pageContext.request.contextPath}/index.jsp";
					} else {
						$("#loading").hide();
						$("#error2").html(result.msg);
						changeImg($("#vadCode").get(0)) ;
						$("#b_login").attr("disabled",false);
					}
				}, 'JSON');
	} 
		
		// 点击图片时，更换验证码
		function changeImg(img){
			img.src = img.src + "?" + new Date().getTime() ;
//  			validCode();
// 			clean();
		}
		
	       function validCode() {
			$.post("${pageContext.request.contextPath}/captchaController/code", function(code){
				if(code.success){
					var a = document.getElementById('codeError').value;
					var preCode = $("#inputValidCode").val() ;
					if(code.msg.toLowerCase() != preCode.toLowerCase()){
						$("#error2").html(a);
						$("#inputValidCode").val("") ;
// 						alert("验证码错误,请重新输入!");
						changeImg($("#vadCode").get(0)) ;
				    	$("#pwd").val("") ;
						return ;
					}else{
						loginFun();
					}
				}
			}, 'JSON') ;
		}
	       
	    function clean(){
	    	$("#name").val("") ;
	    	$("#pwd").val("") ;
	    	$("#inputValidCode").val("") ;
	    }  
	   
	  
	   
	</script>
</body>

</html>
