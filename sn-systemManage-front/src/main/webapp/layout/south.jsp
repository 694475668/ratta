<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<div  style="text-align: center;color:white;background-color:#2b2b2b;height: 50px;padding: 5px;font-weight:bold;">
	<c:if test="${sessionInfo.locale.country=='CN' }">
				版本号
	</c:if>
	<c:if test="${sessionInfo.locale.country=='US' }">
				version
	</c:if>
					:1.0.1.20180323_release
	</div>



