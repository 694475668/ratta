package com.ratta.spnote.controller.parameter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.param.model.ErrorCode;
import com.ratta.suponote.param.result.ErrorCodeResult;
import com.ratta.suponote.param.service.ErrorCodeService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 错误码管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/errorCodeController")
public class ErrorCodeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ErrorCodeService errorCodeService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String code, String desc, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("code", code);
		params.put("desc", desc);
		params.put("sessionInfo", sessionInfo);
		return errorCodeService.query(ph, params);
	}

	/**
	 * 跳转到错误码管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/parameter/errorCode/errorCode";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addErrorCode")
	public String addErrorCode() {
		return "/admin/parameter/errorCode/addErrorCode";
	}

	/**
	 * 添加
	 * 
	 * @param errorCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "ErrorCode_add", operationType = OperateLogger.OperationType.C, table = "t_errorcode")
	public Json add(ErrorCode errorCode, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			ErrorCodeResult result = errorCodeService.save(errorCode, sessionInfo);
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
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/editErrorCode")
	public String editErrorCode(HttpServletRequest request, String code) {
		ErrorCode errorCode = errorCodeService.query(code);
		request.setAttribute("errorCode", errorCode);
		return "/admin/parameter/errorCode/updateErrorCode";
	}

	/**
	 * 修改
	 * 
	 * @param errorCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "ErrorCode_update", operationType = OperateLogger.OperationType.U, table = "t_errorcode")
	public Json edit(ErrorCode errorCode, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			ErrorCodeResult result = errorCodeService.update(errorCode, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除
	 * 
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "ErrorCode_delete", operationType = OperateLogger.OperationType.D, table = "t_errorcode")
	public Json delete(String code, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			ErrorCodeResult result = errorCodeService.delete(code, sessionInfo);
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
	public ErrorCode getBean(String id) {
		return errorCodeService.query(id);
	}

}
