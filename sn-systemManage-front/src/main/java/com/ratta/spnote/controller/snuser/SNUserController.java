package com.ratta.spnote.controller.snuser;

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
import com.ratta.suponote.usersn.model.SNUser;
import com.ratta.suponote.usersn.result.SnUserResult;
import com.ratta.suponote.usersn.service.SNUserService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 用户管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/snuserController")
public class SNUserController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 用户管理service
	 */
	@Autowired
	private SNUserService userService;

	/**
	 * 
	 * <p>
	 * manager
	 * </p>
	 * <p>
	 * 跳转到用户管理页面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/snuser/snuser";
	}

	/**
	 * 
	 * <p>
	 * dataGrid
	 * </p>
	 * <p>
	 * 获取用户数据表格
	 * </p>
	 * 
	 * @param user 查询用户
	 * @param ph   分页帮助类
	 * @return easyui grid数据模型
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(String username, String telephone, String email,String isNormal, String modifydatetimeStart,
			String modifydatetimeEnd, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("username", username);
		params.put("telephone", telephone);
		params.put("email", email);
		params.put("isNormal", isNormal);
		params.put("modifydatetimeStart", modifydatetimeStart);
		params.put("modifydatetimeEnd", modifydatetimeEnd);
		params.put("sessionInfo", sessionInfo);
		return userService.dataGrid(ph, params);
	}


	/**
	 * 跳转到修改账号页面
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updateAccount")
	public String updateAccount(HttpServletRequest request, String id) {
		SNUser snUser = userService.get(id);
		request.setAttribute("snUser", snUser);
		return "/admin/snuser/updateTelphone";
	}

	/**
	 * 修改账号
	 * 
	 * @param errorCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/editAccount", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "telephone_update", operationType = OperateLogger.OperationType.U, table = "t_user")
	public Json editAccount(SNUser snUser, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			SnUserResult snuserResult = userService.updateAccount(snUser, sessionInfo);
			j.setSuccess(snuserResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(snuserResult, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 跳转到修改邮箱页面
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updateEmail")
	public String updateEmail(HttpServletRequest request, String id) {
		SNUser snUser = userService.get(id);
		request.setAttribute("snUser", snUser);
		return "/admin/snuser/updateEmail";
	}

	/**
	 * 修改账号
	 * 
	 * @param errorCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/editEmail", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "email_update", operationType = OperateLogger.OperationType.U, table = "t_user")
	public Json editEmail(SNUser snUser, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			SnUserResult snuserResult = userService.updateAccount(snUser, sessionInfo);
			j.setSuccess(snuserResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(snuserResult, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 冻结
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/Freeze")
	@ResponseBody
	@OperateLogger(content = "sn_user_freeze", operationType = OperateLogger.OperationType.U, table = "t_user")
	public Json Freeze(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			SnUserResult snuserResult = userService.updateIsNormal(id, "N");
			j.setSuccess(snuserResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(snuserResult, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 查看用户详情
	 * 
	 * @param ids
	 * @param session
	 * @return
	 */
	@RequestMapping("/detail")
	@OperateLogger(content = "sn_user_detail", operationType = OperateLogger.OperationType.R, table = "t_user")
	public String detail(HttpServletRequest request, String id) {
		SNUser snUser = userService.get(id);
		request.setAttribute("snUser", snUser);
		return "/admin/snuser/detail";
	}

	/**
	 * 解绑
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/unBund")
	@ResponseBody
	@OperateLogger(content = "user_equipment_delete", operationType = OperateLogger.OperationType.D, table = "t_user_equipment")
	public Json unBund(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			SnUserResult snuserResult = userService.unBund(id, sessionInfo);
			j.setSuccess(snuserResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(snuserResult, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg("系统有误");
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 解冻
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/Thaw")
	@ResponseBody
	@OperateLogger(content = "sn_user_thaw", operationType = OperateLogger.OperationType.U, table = "t_user")
	public Json Thaw(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			SnUserResult snuserResult = userService.updateIsNormal(id, "Y");
			j.setSuccess(snuserResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(snuserResult, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 批量操作 在选择一条数据的情况
	 * 
	 * @param id
	 * @return
	 */
	public SNUser getBean(String id) {
		return userService.get(id);
	}
}
