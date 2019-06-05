package com.ratta.spnote.controller.firmware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.ratta.suponote.firmware.model.FirmwareEquipment;
import com.ratta.suponote.firmware.result.FirmwareEquipResult;
import com.ratta.suponote.firmware.service.FirmwareEquipService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 设备型号管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/firmwareEquipController")
public class FirmwareEquipController extends BaseController {

	@Autowired
	private FirmwareEquipService firmwareEquipService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String version, String type, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("version", version);
		params.put("type", type);
		params.put("sessionInfo", sessionInfo);
		return firmwareEquipService.firmwareEquip(ph, params);
	}

	/**
	 * 查询固件相对应的设备型号
	 */
	@RequestMapping("/queryNoPage")
	@ResponseBody
	public List<FirmwareEquipment> queryNoPage(String version) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("version", version);
		return firmwareEquipService.queryEquipByVersion(params);
	}

	/**
	 * 跳转修改固件相对应的设备型号页面
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdateFirmEquip")
	public String toUpdateFirmEquip(HttpServletRequest request, String version) {
		request.setAttribute("version", version);
		return "/admin/firmware/updateFirmEquip";
	}

	/**
	 * 修改固件绑定的设备型号 updateFirmEquip
	 * 
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@RequestMapping(value = "/updateFirmEquip", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "FirmEquip_update")
	public Json updateFirmEquip(String version, String checkbo, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		List<String> equipmentids = new ArrayList<String>();
		try {
			String[] equipmentIds = checkbo.split(",");
			for (int i = 0; i < equipmentIds.length; i++) {
				equipmentids.add(equipmentIds[i]);
			}
			FirmwareEquipResult result = firmwareEquipService.addFirmwareEquipment(version, equipmentids);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
