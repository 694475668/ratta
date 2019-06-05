<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jslib/jquery-print/css/ratta-print.css"
	type="text/css">
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		//转换性别
		var sex = '${snUser.sex}';
		if (sex == 1) {
			$("#sex").html("男");
		} else {
			$("#sex").html("女");
		}
		//字节大小转换，参数为b
		var totalcapacity = '${snUser.totalcapacity}';
		var sizes = [ 'B', 'KB', 'MB', 'GB' ];
		if (totalcapacity == 0) {
			return '0 KB';
		}
		var i = parseInt(Math.floor(Math.log(totalcapacity) / Math.log(1024)));
		$("#totalcapacity")
				.html(
						(totalcapacity / Math.pow(1024, i)).toFixed(1) + ' '
								+ sizes[i]);
	});
</script>
<%-- <div class="easyui-layout" data-options="fit:true,border:false" style="margin-top:30px;">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table style="margin-left: 50px;font-size:13px;">
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="user_sex"/>:</td>
					<td id="sex"></td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="birthday"/>:</td>  
					<td>${snUser.birthday}</td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="gernalSign"/>:</td>
					<td>${snUser.personalsign}</td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="hobby"/>:</td>
					<td>${snUser.hobby}</td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="education"/>:</td>
					<td>${snUser.education}</td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="job"/>:</td>
					<td>${snUser.job}</td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="address"/>:</td>
					<td>${snUser.address}</td>
				</tr>
				<tr style="height:30px;">
					<td  style="padding-right:10px;font-weight:bold"><spring:message code="cloud_capacity"/>:</td>
					<td id="totalcapacity"></td>
				</tr>
			</table>
		</form>
	</div>

</div>
 --%>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="sp_table">
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="user_sex" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt" id="sex"></p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="birthday" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${snUser.birthday}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="gernalSign" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${snUser.personalsign}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="hobby" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${snUser.hobby}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="education" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${snUser.education}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="job" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${snUser.job}</p>

					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="address" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${snUser.address}</p>

					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="cloud_capacity" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt" id="totalcapacity"></p>

					</td>
				</tr>
			</table>
		</form>
	</div>
</div>