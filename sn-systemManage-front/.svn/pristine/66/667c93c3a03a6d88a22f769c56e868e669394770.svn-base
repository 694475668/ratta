<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>定时任务</title>
<jsp:include page="../../inc.jsp"></jsp:include>

</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north', border:false"
			style="height: 230px;; overflow: hidden;">
			<div id="tt" class="easyui-tabs" style="height: 300px;">


				<!-- 秒设置  -->
				<div title="<spring:message code="quartz_set_second"/>" class="sec"
					data-options="iconCls:'clock'" style="height: 300px;">
					<div class="easyui-panel" style="height: 70px; padding-left: 10px;">
						<table>
							<tr>
								<td><span style="width: 80px;"><input type="radio"
										name="sec" value="every" checked />&nbsp;<spring:message
											code="quartz_every_sec" /></span></td>
								<td><span style="margin-left: 20px;"><input
										type="radio" name="sec" value="quick" />&nbsp;<spring:message
											code="quartz_quick_set" /></span></td>
								<td></td>
							</tr>
							<tr class="secquick">
								<td></td>
								<td></td>
								<td><spring:message code="quartz_from1" /></td>
								<td><input id="secStart" style="width: 80px;" value="0" />
								</td>
								<td><spring:message code="quartz_from2_second" /></td>
								<td><input id="secInterval" style="width: 80px;" value="0" /></td>
								<td><spring:message code="quartz_from3_second" /></td>
								<td><input id="secValue" name="value" type="text" value="*" /></td>
							</tr>
						</table>
					</div>

					<div class="easyui-panel"
						style="height: 120px; padding-left: 10px;">

						<table>
							<tr>
								<td><span><input type="radio" name="sec"
										value="manual" />&nbsp;<spring:message code="quartz_manul_set" /></span></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="secmanual" style="border: 0px solid #000">
										<input class="sec" type="checkbox" style="margin-left: 15px;"
											value="0" />00 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="1" />01 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="2" />02 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="3" />03 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="4" />04 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="5" />05 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="6" />06 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="7" />07 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="8" />08 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="9" />09 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="10" />10 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="11" />11 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="12" />12 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="13" />13 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="14" />14
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="secmanual" style="border: 0px solid #000">
										<input class="sec" type="checkbox" style="margin-left: 15px;"
											value="15" />15 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="16" />16 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="17" />17 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="18" />18 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="19" />19 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="20" />20 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="21" />21 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="22" />22 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="23" />23 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="24" />24 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="25" />25 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="26" />26 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="27" />27 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="28" />28 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="29" />29

									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="secmanual" style="border: 0px solid #000">
										<input class="sec" type="checkbox" style="margin-left: 15px;"
											value="30" />30 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="31" />31 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="32" />32 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="33" />33 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="34" />34 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="35" />35 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="36" />36 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="37" />37 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="38" />38 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="39" />39 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="40" />40 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="41" />41 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="42" />42 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="43" />43 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="44" />44
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="secmanual" style="border: 0px solid #000">
										<input class="sec" type="checkbox" style="margin-left: 15px;"
											value="45" />45 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="46" />46 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="47" />47 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="48" />48 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="49" />49 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="50" />50 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="51" />51 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="52" />52 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="53" />53 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="54" />54 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="55" />55 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="56" />56 <input
											class="sec" type="checkbox" style="margin-left: 15px;"
											value="57" />57 <input class="sec" type="checkbox"
											style="margin-left: 15px;" value="58" />58 <input class="sec"
											type="checkbox" style="margin-left: 15px;" value="59" />59
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 分设置 -->
				<div title="<spring:message code="quartz_set_minute"/>" class="min"
					data-options="iconCls:'clock'" style="height: 300px;">
					<div class="easyui-panel" style="height: 70px; padding-left: 10px;">
						<table>
							<tr>
								<td><span style="width: 80px;"><input type="radio"
										name="min" value="every" checked />&nbsp;<spring:message
											code="quartz_every_minute" /></span></td>
								<td><span style="margin-left: 20px;"><input
										type="radio" name="min" value="quick" />&nbsp;<spring:message
											code="quartz_quick_set" /></span></td>
								<td></td>
							</tr>
							<tr class="minquick">
								<td></td>
								<td></td>
								<td><spring:message code="quartz_from1" /></td>
								<td><input id="minStart" style="width: 80px;" value="0" />
								</td>
								<td><spring:message code="quartz_from2" /></td>
								<td><input id="minInterval" style="width: 80px;" value="0" /></td>
								<td><spring:message code="quartz_from3" /></td>
								<td><input id="minValue" name="value" type="text" value="*" /></td>
							</tr>
						</table>
					</div>
					<div class="easyui-panel"
						style="height: 120px; padding-left: 10px;">

						<table>
							<tr>
								<td><span><input type="radio" name="min"
										value="manual" />&nbsp;<spring:message code="quartz_manul_set" /></span></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="minmanual" style="border: 0px solid #000">
										<input class="min" type="checkbox" style="margin-left: 15px;"
											value="0" />00 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="1" />01 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="2" />02 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="3" />03 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="4" />04 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="5" />05 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="6" />06 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="7" />07 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="8" />08 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="9" />09 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="10" />10 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="11" />11 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="12" />12 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="13" />13 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="14" />14
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="minmanual" style="border: 0px solid #000">
										<input class="min" type="checkbox" style="margin-left: 15px;"
											value="15" />15 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="16" />16 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="17" />17 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="18" />18 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="19" />19 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="20" />20 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="21" />21 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="22" />22 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="23" />23 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="24" />24 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="25" />25 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="26" />26 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="27" />27 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="28" />28 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="29" />29

									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="minmanual" style="border: 0px solid #000">
										<input class="min" type="checkbox" style="margin-left: 15px;"
											value="30" />30 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="31" />31 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="32" />32 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="33" />33 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="34" />34 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="35" />35 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="36" />36 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="37" />37 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="38" />38 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="39" />39 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="40" />40 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="41" />41 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="42" />42 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="43" />43 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="44" />44
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="minmanual" style="border: 0px solid #000">
										<input class="min" type="checkbox" style="margin-left: 15px;"
											value="45" />45 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="46" />46 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="47" />47 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="48" />48 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="49" />49 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="50" />50 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="51" />51 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="52" />52 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="53" />53 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="54" />54 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="55" />55 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="56" />56 <input
											class="min" type="checkbox" style="margin-left: 15px;"
											value="57" />57 <input class="min" type="checkbox"
											style="margin-left: 15px;" value="58" />58 <input class="min"
											type="checkbox" style="margin-left: 15px;" value="59" />59
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 时设置 -->
				<div title="<spring:message code="quartz_set_hour"/>" class="hour"
					data-options="iconCls:'clock'" style="">
					<div class="easyui-panel" style="height: 65px; padding-left: 10px;">
						<table>
							<tr>
								<td><span style="width: 80px;"><input type="radio"
										name="hour" value="every" checked />&nbsp;<spring:message
											code="quartz_every_hour" /></span></td>
								<td><span style="margin-left: 20px;"><input
										type="radio" name="hour" value="quick" />&nbsp;<spring:message
											code="quartz_quick_set" /></span></td>
								<td></td>
							</tr>
							<tr class="hourquick">
								<td></td>
								<td></td>
								<td></td>
								<td><spring:message code="quartz_from1" /></td>
								<td><input id="hourStart" style="width: 80px;" value="0" />
								</td>
								<td><spring:message code="quartz_from2_hour" /></td>
								<td><input id="hourInterval" style="width: 80px;" value="0" /></td>
								<td><spring:message code="quartz_from3_hour" /></td>
								<td><input id="hourValue" name="value" type="text"
									value="*" /></td>
							</tr>
						</table>
					</div>
					<div class="easyui-panel"
						style="height: 140px; padding-left: 10px;">

						<table>
							<tr>
								<td><span><input type="radio" name="hour"
										value="manual" />&nbsp;<spring:message code="quartz_manul_set" /></span></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="hourmanual" style="border: 0px solid #000">
										<span>AM</span> <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="0" />00 <input class="hour"
											type="checkbox" style="margin-left: 15px;" value="1" />01 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="2" />02 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="3" />03 <input class="hour"
											type="checkbox" style="margin-left: 15px;" value="4" />04 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="5" />05 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="6" />06 <input class="hour"
											type="checkbox" style="margin-left: 15px;" value="7" />07 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="8" />08 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="9" />09 <input class="hour"
											type="checkbox" style="margin-left: 15px;" value="10" />10 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="11" />11
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="hourmanual" style="border: 0px solid #000">
										<span>PM</span> <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="12" />12 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="13" />13 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="14" />14 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="15" />15 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="16" />16 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="17" />17 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="18" />18 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="19" />19 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="20" />20 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="21" />21 <input class="hour" type="checkbox"
											style="margin-left: 15px;" value="22" />22 <input
											class="hour" type="checkbox" style="margin-left: 15px;"
											value="23" />23

									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 日设置 -->
				<div title="<spring:message code="quartz_set_day"/>" class="day"
					data-options="iconCls:'clock'" style="">
					<div class="easyui-panel" style="height: 65px; padding-left: 10px;">
						<table>
							<tr>
								<td><span style="width: 80px; margin-right: 20px;"><input
										type="radio" name="day" value="disable" checked />&nbsp;<spring:message
											code="quartz_not_set" /></span></td>
								<td><span style="width: 80px;"><input type="radio"
										name="day" value="every" />&nbsp;<spring:message
											code="quartz_every_day" /></span></td>
								<td><span style="margin-left: 20px;"><input
										type="radio" name="day" value="quick" />&nbsp;<spring:message
											code="quartz_quick_set" /></span></td>
								<td></td>
							</tr>
							<tr class="dayquick">
								<td></td>
								<td></td>
								<td></td>
								<td><spring:message code="quartz_from1" /></td>
								<td><input id="dayStart" style="width: 80px;" value="0" />
								</td>
								<td><spring:message code="quartz_from2_day" /></td>
								<td><input id="dayInterval" style="width: 80px;" value="0" /></td>
								<td><spring:message code="quartz_from3_day" /></td>
								<td><input id="dayValue" name="value" type="text" value="*" /></td>
							</tr>
						</table>
					</div>
					<div class="easyui-panel"
						style="height: 140px; padding-left: 10px;">

						<table>
							<tr>
								<td><span><input type="radio" name="day"
										value="manual" />&nbsp;<spring:message code="quartz_manul_set" /></span></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="daymanual" style="border: 0px solid #000">
										<input class="day" type="checkbox" style="margin-left: 15px;"
											value="1" />01 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="2" />02 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="3" />03 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="4" />04 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="5" />05 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="6" />06 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="7" />07 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="8" />08 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="9" />09 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="10" />10 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="11" />11 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="12" />12 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="13" />13 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="14" />14 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="15" />15
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="daymanual" style="border: 0px solid #000">
										<input class="day" type="checkbox" style="margin-left: 15px;"
											value="16" />16 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="17" />17 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="18" />18 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="19" />19 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="20" />20 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="21" />21 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="22" />22 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="23" />23 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="24" />24 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="25" />25 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="26" />26 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="27" />27 <input
											class="day" type="checkbox" style="margin-left: 15px;"
											value="28" />28 <input class="day" type="checkbox"
											style="margin-left: 15px;" value="29" />29 <input class="day"
											type="checkbox" style="margin-left: 15px;" value="30" />30
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="daymanual" style="border: 0px solid #000">
										<input type="checkbox" style="margin-left: 15px;" value="31" />31
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 月设置 -->
				<div title="<spring:message code="quartz_set_month"/>" class="month"
					data-options="iconCls:'clock'" style="">
					<div class="easyui-panel" style="height: 65px; padding-left: 10px;">
						<table>
							<tr>
								<td><span style="width: 80px;"><input type="radio"
										name="month" value="every" checked />&nbsp;<spring:message
											code="quartz_every_month" /></span></td>
								<td><span style="margin-left: 20px;"><input
										type="radio" name="month" value="quick" />&nbsp;<spring:message
											code="quartz_quick_set" /></span></td>
								<td></td>
							</tr>
							<tr class="monthquick">
								<td></td>
								<td></td>
								<td><spring:message code="quartz_from1" /></td>
								<td><input id="monthStart" style="width: 80px;" value="0" />
								</td>
								<td><spring:message code="quartz_from2_month" /></td>
								<td><input id="monthInterval" style="width: 80px;"
									value="0" /></td>
								<td><spring:message code="quartz_from3_month" /></td>
								<td><input id="monthValue" name="value" type="text"
									value="*" /></td>
							</tr>
						</table>
					</div>
					<div class="easyui-panel"
						style="height: 140px; padding-left: 10px;">

						<table>
							<tr>
								<td><span><input type="radio" name="month"
										value="manual" />&nbsp;<spring:message code="quartz_manul_set" /></span></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="monthmanual" style="border: 0px solid #000">
										<input class="month" type="checkbox"
											style="margin-left: 15px;" value="1" />01 <input
											class="month" type="checkbox" style="margin-left: 15px;"
											value="2" />02 <input class="month" type="checkbox"
											style="margin-left: 15px;" value="3" />03 <input
											class="month" type="checkbox" style="margin-left: 15px;"
											value="4" />04 <input class="month" type="checkbox"
											style="margin-left: 15px;" value="5" />05 <input
											class="month" type="checkbox" style="margin-left: 15px;"
											value="6" />06 <input class="month" type="checkbox"
											style="margin-left: 15px;" value="7" />07 <input
											class="month" type="checkbox" style="margin-left: 15px;"
											value="8" />08 <input class="month" type="checkbox"
											style="margin-left: 15px;" value="9" />09 <input
											class="month" type="checkbox" style="margin-left: 15px;"
											value="10" />10 <input class="month" type="checkbox"
											style="margin-left: 15px;" value="11" />11 <input
											class="month" type="checkbox" style="margin-left: 15px;"
											value="12" />12
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 周设置 -->
				<div title="<spring:message code="quartz_set_week"/>" class="week"
					data-options="iconCls:'clock'" style="">
					<div class="easyui-panel" style="height: 65px; padding-left: 10px;">

						<table>
							<tr>
								<td><span style="width: 80px; margin-right: 20px;"><input
										type="radio" name="week" value="disable" checked />&nbsp;<spring:message
											code="quartz_not_set" /></span></td>
								<td><span style="width: 80px;"><input type="radio"
										name="week" value="every" />&nbsp;<spring:message
											code="quartz_every_week" /></span></td>
								<td><span style="margin-left: 20px;"><input
										type="radio" name="week" value="quick" />&nbsp;<spring:message
											code="quartz_quick_set" /></span></td>
								<td></td>
							</tr>
							<tr class="weekquick">
								<td></td>
								<td></td>
								<td></td>
								<td><spring:message code="quartz_from1" /></td>
								<td><select id="weekStart" class="easyui-combobox"
									name="dept" style="width: 80px;" data-options="editable:false">
										<option value="1"><spring:message code="quartz_zhou1" /></option>
										<option value="2"><spring:message code="quartz_zhou2" /></option>
										<option value="3"><spring:message code="quartz_zhou3" /></option>
										<option value="4"><spring:message code="quartz_zhou4" /></option>
										<option value="5"><spring:message code="quartz_zhou5" /></option>
										<option value="6"><spring:message code="quartz_zhou6" /></option>
										<option value="0"><spring:message code="quartz_zhou7" /></option>
								</select></td>
								<td><spring:message code="quartz_from2_week" /></td>
								<td><input id="weekInterval" style="width: 80px;" value="0" /></td>
								<td><spring:message code="quartz_from3_week" /></td>
								<td><input id="weekValue" name="value" type="text"
									value="?" /></td>
							</tr>
						</table>
					</div>
					<div class="easyui-panel"
						style="height: 140px; padding-left: 10px;">

						<table>
							<tr>
								<td><span><input type="radio" name="week"
										value="manual" />&nbsp;<spring:message code="quartz_manul_set" /></span></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="weekmanual" style="border: 0px solid #000">
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="2" />
										<spring:message code="quartz_zhou1" />
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="3" />
										<spring:message code="quartz_zhou2" />
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="4" />
										<spring:message code="quartz_zhou3" />
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="5" />
										<spring:message code="quartz_zhou4" />
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="6" />
										<spring:message code="quartz_zhou5" />
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="7" />
										<spring:message code="quartz_zhou6" />
										<input class="week" type="checkbox" style="margin-left: 15px;"
											value="1" />
										<spring:message code="quartz_zhou7" />
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false"
			style="height: 10;; overflow: hidden;">
			<div>
				<a id="btn" href="#" class="easyui-linkbutton"
					data-options="iconCls:'search',plain:true"
					onclick="testExpression();"><spring:message
						code="quartz_output_exp" /></a> <input id="cronExpression"
					class="easyui-validatebox" data-options="plain:true" readonly />
			</div>
		</div>
	</div>

	<script type="text/javascript">
		parent.$.messager.progress('close');

		(function($) {

			$.fn.disable = function() {
				/// <summary>
				/// 屏蔽所有 input 元素
				/// </summary>
				/// <returns type="jQuery" />
				return $(this).find("input").each(function() {
					$(this).attr("disabled", "disabled");
				});
			};

			$.fn.enable = function() {
				/// <summary>
				/// 使得所有 input 元素都有效
				/// </summary>
				/// <returns type="jQuery" />
				return $(this).find("input").each(function() {
					$(this).removeAttr("disabled");
				});
			};
			// 初始化 numberspinner
			initNumberspinner();
			disableEverything();

			$(":radio").change(function() {
				var prefix = $(this).val();
				var name = $(this).attr("name");

				if ("every" == prefix) {
					$("." + name + "quick").disable();
					$("." + name + "manual").disable();
					closeSpinner(name, true);
					responseEveryRadio(name);
				} else if ("quick" == prefix) {
					$("." + name + "quick").enable();
					$("." + name + "manual").disable();
					closeSpinner(name, false);
					responseQuickRadio(name);
				} else if ("manual" == prefix) {
					$("." + name + "quick").disable();
					$("." + name + "manual").enable();
					closeSpinner(name, true);
					responseManualRadio(name);
				} else if ("disable" == prefix) {
					$("." + name + "quick").disable();
					$("." + name + "manual").disable();
					closeSpinner(name, true);
					responseEveryRadio(name);

					$("#" + name + "Value").val("?");
				}
			});

			$(":checkbox").change(function() {
				var prefix = $(this).attr("class");
				responseManualRadio(prefix);
			});

			// 2017.5.23 add
			// cron表达式
			/*  var cron = "${scheduleTask.cron}";
			 // 秒设置
			 var second = cron.split(" ")[0];
			 Set(second, "sec");
			 // 分设置
			 var minute = cron.split(" ")[1];
			 Set(minute, "min");
			 // 时设置
			 var hour = cron.split(" ")[2];
			 Set(hour, "hour");
			 // 日设置
			 var day = cron.split(" ")[3];
			 Set(day, "day");
			 // 月设置
			 var month = cron.split(" ")[4];
			 Set(month, "month");
			 // 周设置
			 var week = cron.split(" ")[5];
			 Set(week, "week"); */
		})(jQuery);

		// 通用方法(timeSet:秒、分、时、日、月、周设置；name:名称/class)
		function Set(timeSet, name) {
			// 每秒/分钟/小时/天/月/周
			if (timeSet == "*") {
				$("input:radio[name='" + name + "'][value='every']").prop(
						'checked', 'checked');
			} else if (timeSet.indexOf("/") != -1) {
				// 快捷设置
				var quick = timeSet.split("/");
				// 周设置开始为下拉框，不是numberspinner
				if (name == "week") {
					$("#" + name + "Start").combobox('setValue', quick[0]);
				} else {
					$("#" + name + "Start").numberspinner('setValue', quick[0]);
				}
				$("#" + name + "Interval").numberspinner('setValue', quick[1]);
				$("input:radio[name='" + name + "'][value='quick']").prop(
						'checked', 'checked');
			} else if (timeSet.indexOf("?") != -1) {
				// 日、周设置（不设置）
				$("input:radio[name='" + name + "'][value='disable']").prop(
						'checked', 'checked');
			} else {
				// 自定义设置
				if (timeSet.indexOf(",") != -1) {
					var manual = timeSet.split(",");
					for (var i = 0; i < manual.length; i++) {
						$(
								"." + name + " input:checkbox[value='"
										+ manual[i] + "']").attr('checked',
								'true');
					}
				} else {
					$("." + name + " input:checkbox[value='" + timeSet + "']")
							.attr('checked', 'true');
				}
				$("." + name).enable();
				// 自定义设置radio选中
				$("input:radio[name='" + name + "'][value='manual']").prop(
						'checked', 'checked');
				// 快捷设置置灰
				closeSpinner(name, true);
			}
			// 重新设置秒/分钟/小时/天/月/周的值
			$("#" + name + "Value").val(timeSet);
		}

		// 初始化 numberspinner
		function initNumberspinner() {

			// 秒
			$('#secStart').numberspinner({
				required : true,
				min : 0,
				max : 59
			});

			$("#secInterval").numberspinner({
				required : true,
				min : 1,
				max : 59
			});

			// 分钟
			$('#minStart').numberspinner({
				required : true,
				min : 0,
				max : 59
			});

			$("#minInterval").numberspinner({
				required : true,
				min : 1,
				max : 59
			});

			// 小时
			$('#hourStart').numberspinner({
				required : true,
				min : 0,
				max : 23
			});

			$("#hourInterval").numberspinner({
				required : true,
				min : 1,
				max : 23
			});

			// 天
			$('#dayStart').numberspinner({
				required : true,
				min : 1,
				max : 31
			});

			$("#dayInterval").numberspinner({
				required : true,
				min : 1,
				max : 31
			});

			// 月 
			$('#monthStart').numberspinner({
				required : true,
				min : 1,
				max : 31
			});

			$("#monthInterval").numberspinner({
				required : true,
				min : 1,
				max : 31
			});

			// 周
			$("#weekInterval").numberspinner({
				required : true,
				min : 1,
				max : 7
			});

			$("input[id$='Start']").numberspinner({
				onChange : function(value) {
					var name = $(this).attr("id").replace(/Start/, "").trim();
					responseQuickRadio(name);
				}
			});

			$("input[id$='Interval']").numberspinner(
					{
						onChange : function(value) {
							var name = $(this).attr("id").replace(/Interval/,
									"").trim();
							responseQuickRadio(name);
						}
					});

			$("#weekStart").combobox({
				onSelect : function() {
					var start = $(this).combobox('getValue');
					var interval = $("#weekInterval").val();
					$("#weekValue").val(start + "/" + interval);
				}
			});
		}

		function disableEverything() {
			var cf = [ 'sec', 'min', 'hour', 'day', 'month', 'week', 'year' ];
			for (var i = 0, len = cf.length; i < len; i++) {
				$("." + cf[i] + "quick").disable();
				$("." + cf[i] + "manual").disable();
				closeSpinner(cf[i], true);
				$("#" + cf[i] + "Value").hide();
			}
		}

		// 关闭 spinner
		function closeSpinner(name, flag) {
			if (flag) {
				if ("week" == name) {
					$("#" + name + "Start").combobox("disable");
				} else {
					$("#" + name + "Start").numberspinner({
						disabled : true
					});
				}

				$("#" + name + "Interval").numberspinner({
					disabled : true
				});
			} else {
				if ("week" == name) {
					$("#" + name + "Start").combobox("enable");
				} else {
					$("#" + name + "Start").numberspinner({
						disabled : false
					});
				}

				$("#" + name + "Interval").numberspinner({
					disabled : false
				});
			}
		}

		// 响应 每天，每月，每小时，每分钟  。。。 radio
		function responseEveryRadio(name) {
			setValue(name, "*");
		}

		// 响应快捷设置
		function responseQuickRadio(name) {
			// 从 start 开始 
			var start = $("#" + name + "Start").val();
			// 每隔 interval 重复执行 
			var interval = $("#" + name + "Interval").val();

			if (name == "week") {
				start = $('#weekStart').combobox('getValue');
			}

			setValue(name, start + "/" + interval);
		}

		// 响应自定义设置
		function responseManualRadio(name) {
			var p = "";
			$("." + name + ":checked").each(function(i) {
				var value = $(this).val();
				p += value + ",";
			});

			var len = p.length;
			p = p.substring(0, len - 1);

			if (p.trim() != "") {
				$("#" + name + "Value").val(p);
			} else {
				$("#" + name + "Value").val("*");
			}

		}

		// 分别生成 sec/min/hour/day/month/week/year 对应的 cronexpression 表达式
		function setValue(name, value) {
			$("#" + name + "Value").val(value);
		}

		// 生成 corn表达式
		function createCronExpression() {
			var cf = [ 'sec', 'min', 'hour', 'day', 'month', 'week' ]; //, 'year'
			var v = "";
			//var second = "* " ;
			for (var i = 0, len = cf.length; i < len; i++) {
				var val = $("#" + cf[i] + "Value").val();
				/* if(val != "*" && val != "?"){
				 second = "0 " ;
				} */
				var day = $("#" + cf[3] + "Value").val();
				var week = $("#" + cf[5] + "Value").val();
				if (day == "?" && week == "?") {
					$.messager.alert('<spring:message code="error"/>',
							"<spring:message code='corn_error'/>", 'error');
					break;
				}
				if (day != "?" && week != "?") { //日、周都设置
					$.messager.alert('<spring:message code="error"/>',
							"<spring:message code='corn_error'/>", 'error');
					break;
				}
				v = v + (typeof (val) == "undefined" ? "?" : val) + " ";
			}
			//v = second + v ;
			var len = v.length;
			v = v.substring(0, len - 1);

			return v;

		}

		// 生成 corn表达式
		function testExpression() {
			var cf = [ 'sec', 'min', 'hour', 'day', 'month', 'week' ]; // ,'year'
			var v = "";
			// var second = "* " ;
			for (var i = 0, len = cf.length; i < len; i++) {
				var val = $("#" + cf[i] + "Value").val();
				var day = $("#" + cf[3] + "Value").val();
				var week = $("#" + cf[5] + "Value").val();
				if (day == "?" && week == "?") {
					$.messager.alert('<spring:message code="error"/>',
							"<spring:message code='corn_error'/>", 'error');
					break;
				}
				if (day != "?" && week != "?") { //日、周都设置
					$.messager.alert('<spring:message code="error"/>',
							"<spring:message code='corn_error'/>", 'error');
					break;
				}

				//parent.$.messager.alert("#" + cf[i] + "Value", val);
				//if(val != "*" && val != "?"){
				// second = "0 " ;
				//}
				v = v + (typeof (val) == "undefined" ? "?" : val) + " ";
			}
			//v = second + v ;
			var len = v.length;
			v = v.substring(0, len - 1);
			$("#cronExpression").val(v);

		}
	</script>
</body>

</html>
