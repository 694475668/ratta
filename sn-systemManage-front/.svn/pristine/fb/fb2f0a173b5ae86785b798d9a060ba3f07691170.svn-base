package com.ratta.spnote.controller.system;

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
import com.ratta.suponote.service.system.maint.AuditService;

/**
 * @author page 操作审计管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/operAuditController")
public class OperAuditController extends BaseController {

	@Autowired
	private AuditService auditService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String op_user, String op_time_begin, String op_time_end, String client_ip,
			String op_type, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("op_user", "%" + (op_user == null ? "" : op_user) + "%");
		params.put("op_time_begin", op_time_begin);
		params.put("op_time_end", op_time_end);
		params.put("client_ip", client_ip);
		params.put("op_type", op_type);
		params.put("sessionInfo", sessionInfo);
		return auditService.query(ph, params);
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/system/operation/operAudit";
	}

}