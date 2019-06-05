package com.ratta.spnote.controller.business;

import java.util.HashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSONObject;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.business.model.EquipType;
import com.ratta.suponote.business.result.EquipTypeRseult;
import com.ratta.suponote.business.service.EquipTypeService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 设备型号管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/equipTypeController")
public class EquipTypeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EquipTypeService equipTypeService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String type, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("type", type);
		params.put("sessionInfo", sessionInfo);
		return equipTypeService.query(ph, params);
	}

	/**
	 * 跳转到数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/business/equipType/equipType";
	}

	/**
	 * 不分页查询设备型号记录
	 */
	@RequestMapping("/queryNoPage")
	@ResponseBody
	public List<EquipType> queryNoPage() {
		return equipTypeService.queryNoPage();
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addEquipType")
	public String addEquipType() {
		return "/admin/business/equipType/addEquipType";
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
	/** 设备型号管理[添加] */
	@OperateLogger(content = "EquipType_add", operationType = OperateLogger.OperationType.C, table = "t_m_equiptype")
	public Json add(EquipType equipType, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			EquipTypeRseult result = equipTypeService.save(equipType, sessionInfo);
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
	@RequestMapping(value = "/editEquipType")
	public String editEquipType(HttpServletRequest request, Long id) {
		EquipType equipType = equipTypeService.query(id);
		request.setAttribute("equipType", equipType);
		return "/admin/business/equipType/updateEquipType";
	}

	/**
	 * 根据ID查询设备型号记录
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryById")
	@ResponseBody
	public Json querySNOffsetLength(Long equipment_model, HttpServletRequest request) {
		EquipType equipType = equipTypeService.query(equipment_model);
		Json j = new Json();
		JSONObject jObj = new JSONObject();
		if (equipType == null) {
			j.setMsg(DealersMessage.DEVICE_INOUT_QUERY_DATA_FAIL_ERROR.getMessage());
			j.setSuccess(false);
			return j;
		}
		jObj.put("sn_front", equipType.getSn_front());
		jObj.put("sn_length", equipType.getSn_length());
		jObj.put("sn_offset", equipType.getSn_offset());
		jObj.put("sn_offset_length", equipType.getSn_offset_length());
		j.setObj(jObj.toJSONString());
		j.setSuccess(true);
		return j;
	}

	/**
	 * 修改
	 * 
	 * @param dealers
	 * @param session
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	/** 设备型号管理[修改] */
	@OperateLogger(content = "EquipType_update", operationType = OperateLogger.OperationType.U, table = "t_m_equiptype")
	public Json edit(EquipType equipType, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			EquipTypeRseult result = equipTypeService.update(equipType, sessionInfo);
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
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	/** 设备型号管理[删除] */
	@OperateLogger(content = "EquipType_delete", operationType = OperateLogger.OperationType.D, table = "t_m_equiptype")
	public Json delete(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			EquipTypeRseult result = equipTypeService.delete(id, sessionInfo);
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
	public EquipType getBean(Long id) {
		return equipTypeService.query(id);
	}
}
