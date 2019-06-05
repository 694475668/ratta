package com.ratta.spnote.controller.firmware;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.firmware.model.FirmwareFixPoint;
import com.ratta.suponote.firmware.result.FirmwareFixPointResult;
import com.ratta.suponote.firmware.service.FirmwareFixPointService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 固件修复点管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/firmwareFixPointController")
public class FirmwareFixPointController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FirmwareFixPointService firmwareFixPointService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String firmware_version, String lan, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("firmware_version", firmware_version);
		params.put("lan", lan);
		params.put("sessionInfo", sessionInfo);
		return firmwareFixPointService.query(ph, params);
	}

	/**
	 * 跳转到固件修复点管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/firmware/firmwareFixPoint";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addFixPoint")
	public String addFixPoint() {
		return "/admin/firmware/addFixPoint";
	}

	/**
	 * 添加
	 * 
	 * @param firmwareFixPoint
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "fixPoint_add", operationType = OperateLogger.OperationType.C, table = "t_firmware_fixpoint")
	public Json add(FirmwareFixPoint firmwareFixPoint, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FirmwareFixPointResult result = firmwareFixPointService.save(firmwareFixPoint, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateFixPoint")
	public String updateFixPoint(HttpServletRequest request, Long id) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("id", id);
		FirmwareFixPoint firmwareFixPoint = firmwareFixPointService.queryByParam(params).get(0);
		request.setAttribute("firmwareFixPoint", firmwareFixPoint);
		return "/admin/firmware/updateFixPoint";
	}

	/**
	 * 修改
	 * 
	 * @param firmwareFixPoint
	 * @param session
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "fixPoint_update", operationType = OperateLogger.OperationType.U, table = "t_firmware_fixpoint")
	public Json edit(FirmwareFixPoint firmwareFixPoint, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FirmwareFixPointResult result = firmwareFixPointService.update(firmwareFixPoint, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public FirmwareFixPoint getBean(Long id) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("id", id);
		return firmwareFixPointService.queryByParam(params).get(0);
	}
}
