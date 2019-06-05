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
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="sp_table">
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="version" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${firmwareInfo.version}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="modify_point" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${firmwareInfo.modifyPoint}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="impact_scope" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${firmwareInfo.impactScope}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="version_purpose" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${firmwareInfo.versionPurpose}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="updrage_scope" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${firmwareInfo.updrageScope}</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="total_version" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">
						<table width="300" border="1" cellspacing="0" cellpadding="0"
							class="sp_child_table">
							<thead class="sp_child_th">
								<td>模块名字</td>
								<td>版本号</td>
							</thead>
							<c:forEach var="firmwareInfoLine" items="${firmwareInfoLine}"
								varStatus="status">
								<tr class="sp_child_tr">
									<td>${firmwareInfoLine.type}</td>
									<td>${firmwareInfoLine.version}</td>
								</tr>
							</c:forEach>
						</table>
						</p>
					</td>
				</tr>
				<tr class="sp_table_tr">
					<td>
						<p class="sp_table_td_lf">
							<spring:message code="packaging_time" />
							:
						</p>
					</td>
					<td>
						<p class="sp_table_td_rt">${firmwareInfo.packagingTime}</p>

					</td>
				</tr>
			</table>
		</form>
	</div>
</div>