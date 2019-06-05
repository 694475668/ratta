package com.ratta.spnote.controller.stock;

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
import com.ratta.suponote.stock.model.EquipmentReturnRecord;
import com.ratta.suponote.stock.service.EquipmentReturnRecordService;

/**
 * @author page 设备退换货管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/equipmentReturnController")
public class EquipmentReturnController extends BaseController {

	@Autowired
	private EquipmentReturnRecordService equipmentReturnRecordService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String type, String equipment_no, String healthyState, String beginTime,
			String endTime, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("type", type);
		params.put("healthyState", healthyState);
		params.put("equipment_no", equipment_no);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return equipmentReturnRecordService.query(ph, params);
	}

	/**
	 * 跳转到设备库存管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/stock/return";
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public EquipmentReturnRecord getBean(Long id) {
		return equipmentReturnRecordService.query(id);
	}
}
