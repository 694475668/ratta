package com.ratta.spnote.controller.app;

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
import com.ratta.suponote.app.model.AppFixPoint;
import com.ratta.suponote.app.result.AppFixPointResult;
import com.ratta.suponote.app.service.AppFixPointService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 
 * app修复点管理控制层 
 * 2019-02-07
 */
@Controller
@RequestMapping("/appFixPointController")
public class AppFixPointController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppFixPointService appFixPointService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String appName, String appVersion, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("appName", appName);
		params.put("appVersion", appVersion);
		params.put("sessionInfo", sessionInfo);
		return appFixPointService.query(ph, params);
	}

	/**
	 * 跳转到app修复点管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/app/appFixPoint/appFixPoint";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addAppFixPoint")
	public String addAppFixPoint() {
		return "/admin/app/appFixPoint/addAppFixPoint";
	}

	/**
	 * 添加
	 * 
	 * @param appFixPoint
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "app_fixPoint_add", operationType = OperateLogger.OperationType.C, table = "t_app_fixpoint")
	public Json add(AppFixPoint appFixPoint, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			AppFixPointResult result = appFixPointService.save(appFixPoint, sessionInfo);
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
	@RequestMapping(value = "/updateAppFixPoint")
	public String updateAppFixPoint(HttpServletRequest request, Long id) {
		AppFixPoint appFixPoint = appFixPointService.query(id);
		request.setAttribute("appFixPoint", appFixPoint);
		return "/admin/app/appFixPoint/updateAppFixPoint";
	}

	/**
	 * 修改
	 * 
	 * @param appFixPoint
	 * @param session
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "app_fixPoint_update", operationType = OperateLogger.OperationType.U, table = "t_app_fixpoint")
	public Json edit(AppFixPoint appFixPoint, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			AppFixPointResult result = appFixPointService.update(appFixPoint, sessionInfo);
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
	public AppFixPoint getBean(Long id) {
		return appFixPointService.query(id);
	}
}
