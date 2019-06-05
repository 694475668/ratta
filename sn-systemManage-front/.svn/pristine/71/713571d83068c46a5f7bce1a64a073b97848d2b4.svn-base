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
import com.ratta.suponote.usersn.model.UserEquipment;
import com.ratta.suponote.usersn.service.UserEquipmentService;

/**
 * @author page 
 * 用户设备管理控制层 
 * 2019-02-27
 */
@Controller
@RequestMapping("/userEquipmentController")
public class UserEquipmentController extends BaseController {
	/**
	 * 用户管理service
	 */
	@Autowired
	private UserEquipmentService userEquipmentService;

	/**
	 * dataGrid
	 * 获取用户设备数据表格
	 * @return easyui grid数据模型
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(String equipment_number, String telephone, String email,PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("equipment_number", equipment_number);
		params.put("telephone", telephone);
		params.put("email", email);
		params.put("sessionInfo", sessionInfo);
		return userEquipmentService.dataGrid(ph, params);
	}

	/**
	 * manager
	 * 跳转到用户设备管理页面
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/snuser/userEquipment";
	}
	
	/**
	 * 用于操作审计记录
	 * @param id
	 * @return
	 */
	public UserEquipment getBean(int id) {
		return userEquipmentService.get(id);
	}
}
