<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#editCurrentUserPwdForm').form({
			url : '${pageContext.request.contextPath}/userController/editCurrentUserPwd',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '<spring:message code="hint"/>',
					text : '<spring:message code="please_latter"/>'
				});
				
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				try{//Json解析出错时，异常处理。
					result = $.parseJSON(result);
				}
				catch(e){
					parent.$.messager.alert('<spring:message code="error"/>', '<spring:message code="req_error"/>', 'error');
					return false;
				}
				if (result.success) {
					if("${sessionInfo.locale.country}"=='CN'){
						$.messager.confirm("操作提示", "更改密码成功，请重新登陆", function (data) {
				            if (data) {
								window.location.href = "${pageContext.request.contextPath}/login.jsp" ;
				            }else{
				            	parent.$.modalDialog.handler.dialog('close');
				            }
				        });
					}
					if("${sessionInfo.locale.country}"=='HK'){
						$.messager.confirm("操作提示", "更改密碼成功，請重新登錄", function (data) {
				            if (data) {
								window.location.href = "${pageContext.request.contextPath}/login.jsp" ;
				            }else{
				            	parent.$.modalDialog.handler.dialog('close');
				            }
				        });
					}
					if("${sessionInfo.locale.country}"=='US'){
						$.messager.confirm("Operation Tips", "Change password is successful, please login again", function (data) {
				            if (data) {
								window.location.href = "${pageContext.request.contextPath}/login.jsp" ;
				            }else{
				            	parent.$.modalDialog.handler.dialog('close');
				            }
				        });
					}
				} else {
					if("${sessionInfo.locale.country}"=='CN'){
					   parent.$.messager.alert('错误', result.msg, 'error');
					}
					if("${sessionInfo.locale.country}"=='HK'){
					   parent.$.messager.alert('錯誤', result.msg, 'error');
					}
					if("${sessionInfo.locale.country}"=='US'){
					   parent.$.messager.alert('error', result.msg, 'error');
					}
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<c:if test="${sessionInfo.id == null}">
			<img src="${pageContext.request.contextPath}/style/images/blue_face/bluefaces_35.png" alt="" />
			<c:if test="${sessionInfo.locale.country=='CN' }">
			   <div>登录已超时，请重新登录，然后再刷新本功能！</div>
			</c:if>
			<c:if test="${sessionInfo.locale.country=='US' }">
			   <div>Login timeout, please log in again, and then refresh this function！</div>
			</c:if>
			<c:if test="${sessionInfo.locale.country=='HK' }">
			   <div>登錄已超時，請重新登錄，然後再刷新本功能！</div>
			</c:if>
			<script type="text/javascript" charset="utf-8">
				try {
					parent.$.messager.progress('close');
				} catch (e) {
				}
			</script>
		</c:if>
		<c:if test="${sessionInfo.id != null}">
			<form id="editCurrentUserPwdForm" method="post">
				<input name="id" type="hidden" value="${sessionInfo.id }"/>
				<table class="table table-hover table-condensed">
					<tr>
					    <c:if test="${sessionInfo.locale.country=='CN' }">
						   <th>登录名</th>
						</c:if>
						<c:if test="${sessionInfo.locale.country=='US' }">
						   <th>username</th>
						</c:if>
						<c:if test="${sessionInfo.locale.country=='HK' }">
						   <th>登錄名</th>
						</c:if>
						   <td>${sessionInfo.username}</td>
					</tr>
					
					<c:if test="${sessionInfo.locale.country=='CN' }">
						<tr>
							<th>原密码</th>
							<td><input name="oldPwd" type="password" placeholder="请输入原密码" class="easyui-validatebox" data-options="required:true" style="width:300px;"></td>
						</tr>
						<tr>
							<th>新密码</th>
							<td><input name="pwd" type="password" placeholder="请输入新密码" class="easyui-validatebox" data-options="required:true,validType:'validPWD[6, 32]'" style="width:300px;"></td>
						</tr>
						<tr>
							<th>重复</th>
							<td><input name="rePwd" type="password" placeholder="请再次输入新密码" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#editCurrentUserPwdForm input[name=pwd]\']'" style="width:300px;"></td>
						</tr>
					</c:if>
					<c:if test="${sessionInfo.locale.country=='US' }">
						<tr>
							<th>oldPwd</th>
							<td><input name="oldPwd" type="password" placeholder="Please enter the original password" class="easyui-validatebox" data-options="required:true" style="width:250px;"></td>
						</tr>
						<tr>
							<th>newPwd</th>
							<td><input name="pwd" type="password" placeholder="Please enter new password" class="easyui-validatebox" data-options="required:true,validType:'validPWD[6, 32]'" style="width:250px;"></td>
						</tr>
						<tr>
							<th>repeatPwd</th>
							<td><input name="rePwd" type="password" placeholder="Please enter a new password again" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#editCurrentUserPwdForm input[name=pwd]\']'" style="width:250px;"></td>
						</tr>
					</c:if>
					<c:if test="${sessionInfo.locale.country=='HK' }">
						<tr>
							<th>原密碼</th>
							<td><input name="oldPwd" type="password" placeholder="請輸入原密碼" class="easyui-validatebox" data-options="required:true" style="width:300px;"></td>
						</tr>
						<tr>
							<th>新密碼</th>
							<td><input name="pwd" type="password" placeholder="請輸入新密碼" class="easyui-validatebox" data-options="required:true,validType:'validPWD[6, 32]'" style="width:300px;"></td>
						</tr>
						<tr>
							<th>重複</th>
							<td><input name="rePwd" type="password" placeholder="請再次輸入新密碼" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#editCurrentUserPwdForm input[name=pwd]\']'" style="width:300px;"></td>
						</tr>
					</c:if>
				</table>
			</form>
		</c:if>
	</div>
</div>