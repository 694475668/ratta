<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- 引入jQuery -->
<script src="${ctx}/jslib/jquery-print/jquery-1.10.2.js" type="text/javascript" charset="utf-8"></script>
<!-- 引入jQuery文件下载 -->
<script src="${ctx}/jslib/jquery.fileDownload.js" type="text/javascript" charset="utf-8"></script>
<!-- JQuery print 打印 插件 @Troy 2014-09-01 15:45:28 -->
<script type="text/javascript" src="${ctx}/jslib/jquery-print/jquery-ui-1.10.4.custom.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/jquery-print/jquery.PrintArea.js" charset="utf-8"></script>
<link rel="stylesheet" href="${ctx}/jslib/jquery-print/css/ratta-print.css" type="text/css">
<!-- 引入上传按钮的样式 2016-11-10 add by ming  -->
<link rel="stylesheet" href="${ctx}/jslib/css/upload_button.css" type="text/css">
<!-- 引入md5 js -->
<script type="text/javascript" src="${ctx}/jslib/md5.js" charset="utf-8"></script>

<!-- 引入ckeditor 2016-11-17 add by jjustt-->
<script src="${ctx}/jslib/ckeditor-4.8.0/ckeditor.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/extBrowser.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${ctx}/jslib/My97DatePicker4.8b3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入kindEditor插件 -->
<link rel="stylesheet" href="${ctx}/jslib/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript" src="${ctx}/jslib/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>


<!-- 引入Highcharts -->
<script src="${ctx}/jslib/Highcharts-3.0.1/js/highcharts.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入bootstrap样式 -->
<link href="${ctx}/jslib/bootstrap-2.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- <script charset="utf-8" src="${ctx}/jslib/bootstrap-2.3.1/js/bootstrap.min.js" charset="utf-8"></script> -->

<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/jslib/jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">
<!-- <link rel="stylesheet" href="${ctx}/jslib/jquery-easyui-1.4.2/themes/icon.css" type="text/css"> -->
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>

<!-- 多语言 -->
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<c:if test="${sessionInfo.locale.country=='CN' }">
  <script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
</c:if>
<c:if test="${sessionInfo.locale.country=='HK' }">
  <script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_TW.js" charset="utf-8"></script>
</c:if>
<c:if test="${sessionInfo.locale.country=='US' }">
  <script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.3/locale/easyui-lang-en.js" charset="utf-8"></script>
</c:if>


<!-- 修复EasyUI1.3.3中layout组件的BUG -->
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>

<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-edatagrid/jquery.edatagrid.js" charset="utf-8"></script>
<!-- 引入EasyUI Portal插件 -->
<link rel="stylesheet" href="${ctx}/jslib/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<c:if test="${sessionInfo.locale.country=='CN' }">
<script type="text/javascript" src="${ctx}/jslib/extEasyUI.js?v=201305241047" charset="utf-8"></script>
</c:if>
<c:if test="${sessionInfo.locale.country=='HK' }">
<script type="text/javascript" src="${ctx}/jslib/extEasyUI_HK.js?v=201305241047" charset="utf-8"></script>
</c:if>
<c:if test="${sessionInfo.locale.country=='US' }">
<script type="text/javascript" src="${ctx}/jslib/extEasyUI_US.js?v=201305241047" charset="utf-8"></script>
</c:if>

<!-- 扩展EasyUI Icon -->
<link rel="stylesheet" href="${ctx}/style/extEasyUIIcon.css?v=201305301906" type="text/css">

<!-- 扩展jQuery -->
<script type="text/javascript" src="${ctx}/jslib/extJquery.js?v=201305301341" charset="utf-8"></script>

<!-- 默认提前一个月  js add by @Troy 2014-08-25 09:46:49 -->
<script type="text/javascript" src="${ctx}/jslib/time.js" charset="utf-8"></script>
<!-- 打印 JS 引入,调用 print() 方法，默认会打印 id="report_content" 下的内容 -->
<script type="text/javascript" src="${ctx}/jslib/print.js" charset="utf-8"></script>


<!-- 表单验证 @wangshuang 2014-08-23 -->

<c:if test="${sessionInfo.locale.country=='CN' }">
  <script type="text/javascript" src="${ctx}/jslib/commons.js" charset="utf-8"></script>
</c:if> 
<c:if test="${sessionInfo.locale.country=='US' }">
  <script type="text/javascript" src="${ctx}/jslib/commons_US.js?date=123" charset="utf-8"></script>
</c:if>
<c:if test="${sessionInfo.locale.country=='HK' }">
  <script type="text/javascript" src="${ctx}/jslib/commons_HK.js" charset="utf-8"></script>
</c:if>
<script type="text/javascript" src="${ctx}/jslib/upload/ajaxfileupload.js"></script>

<!-- add by ming 2014-10-29 -->
<script type="text/javascript" src="${ctx}/jslib/datagrid-detailview.js"></script> 

<script type="text/javascript" src="${ctx}/jslib/lens/lens.js"></script> 
<link rel="stylesheet" href="${ctx}/jslib/lens/lens.css" type="text/css">

<!-- 2016.5.13 Add-->
<script type="text/javascript" src="${ctx}/jslib/jquery.jqzoom.js"></script>
<link rel="stylesheet" href="${ctx}/jslib/jqzoom.css" type="text/css">
<!-- 禁止键盘回车事件  -->
<script>
	function keydown(e) { 
       var currKey=0,e=e||event;
       currKey=e.keyCode||e.which||e.charCode;
       var keyName = String.fromCharCode(currKey);
       if(currKey==13){
			return false;
	}  
    }
      document.onkeydown = keydown;
</script>

<script type="text/javascript"> 

//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外 
function banBackSpace(e){ 
	var ev = e || window.event;//获取event对象 
	var obj = ev.target || ev.srcElement;//获取事件源 
	var t = obj.type || obj.getAttribute('type');//获取事件源类型 
	//获取作为判断条件的事件类型 
	var vReadOnly = obj.getAttribute('readonly'); 
	var vEnabled = obj.getAttribute('enabled'); 
	//处理null值情况 
	vReadOnly = (vReadOnly == null) ? false : vReadOnly; 
	vEnabled = (vEnabled == null) ? true : vEnabled; 
	
	//当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
	//并且readonly属性为true或enabled属性为false的，则退格键失效 
	var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
	&& (vReadOnly==true || vEnabled!=true))?true:false; 
	//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
	var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") 
	?true:false; 
	
	//判断 
	if(flag2){ 
		return false; 
	} 
	if(flag1){ 
		return false; 
	} 
} 

//禁止后退键 作用于Firefox、Opera 
document.onkeypress=banBackSpace; 
//禁止后退键 作用于IE、Chrome 
document.onkeydown=banBackSpace; 

</script> 
