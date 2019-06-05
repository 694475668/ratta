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
						<p class="sp_table_td_rt">${firmwareInfoLine.version}</p>
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
						<p class="sp_table_td_rt">${firmwareInfoLine.modify_point}</p>
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
						<p class="sp_table_td_rt">${firmwareInfoLine.impact_scope}</p>
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
						<p class="sp_table_td_rt">${firmwareInfoLine.version_purpose}</p>
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
						<p class="sp_table_td_rt">${firmwareInfoLine.updrage_scope}</p>
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
						<p class="sp_table_td_rt">${firmwareInfoLine.packaging_time}</p>

					</td>
				</tr>
			</table>
		</form>
	</div>
</div>