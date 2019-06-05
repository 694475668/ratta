package com.ratta.spnote.controller.snuser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.usersn.service.LoginRecordService;

/**
 * @author page 云盘用户登录记录控制层 2018-10-31
 */
@Controller
@RequestMapping("/longinRecordController")
public class LonginRecordController extends BaseController {
	/**
	 * 用户管理service
	 */
	@Autowired
	private LoginRecordService loginRecordService;

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
		return "/admin/snuser/loginRecord";
	}

	/**
	 * dataGrid 获取用户登录记录
	 * 
	 * @param ph 分页帮助类
	 * @return easyui grid数据模型
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(String username, String login_method, String equipment, String modifydatetimeStart,
			String modifydatetimeEnd, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("username", username);
		params.put("login_method", login_method);
		params.put("equipment", equipment);
		params.put("modifydatetimeStart", modifydatetimeStart);
		params.put("modifydatetimeEnd", modifydatetimeEnd);
		params.put("sessionInfo", sessionInfo);
		return loginRecordService.dataGrid(ph, params);
	}

}
