package com.ratta.spnote.controller.equipment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.UFile;
import com.ratta.suponote.equipment.model.EquipmentLog;
import com.ratta.suponote.equipment.result.EquipmentLogResult;
import com.ratta.suponote.equipment.service.EquipmentLogService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page 设备日志管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/equipmentLogController")
public class EquipmentLogController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	static final int LENGTH_LIMIT = 1000;
	@Autowired
	private EquipmentLogService equipmentLogService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String type, String flag, String isDownload, String equipment_no,
			String firmware_version, String beginTime, String endTime, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("type", type);
		params.put("flag", flag);
		params.put("isDownload", isDownload);
		params.put("firmware_version", firmware_version);
		params.put("equipment_no", equipment_no);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return equipmentLogService.query(ph, params);
	}

	/**
	 * 跳转到设备日志管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/equipment/equipmentLog";
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
	/** 设备日志管理[删除] */
	@OperateLogger(content = "equipmentLog_delete", operationType = OperateLogger.OperationType.D, table = "t_equipment_log")
	public Json delete(Long id, String logName, HttpSession session) {
		Json j = new Json();
		final String equipment_Log_BucketName = ConfigUtil.get("Equipment_Log_BucketName");
		try {
			EquipmentLogResult result = equipmentLogService.delete(id);
			if (result.getValue() == 0) {
				UFile.deleteFile(equipment_Log_BucketName, logName.trim());
			}
			j.setSuccess(result.getValue() == 0);
			j.setMsg(result.getDesc());
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到添加日志备注页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRemark")
	public String toRemark(HttpServletRequest request, Long id) {
		request.setAttribute("equipmentLog", equipmentLogService.query(id));
		return "/admin/equipment/remark";
	}

	/**
	 * 添加备注
	 * 
	 * @param id
	 * @param remark
	 * @return
	 */
	@RequestMapping("/remark")
	@ResponseBody
	@OperateLogger(content = "equipmentLog_remark", operationType = OperateLogger.OperationType.U, table = "t_equipment_log")
	public Json remark(Long id, String remark) {
		Json j = new Json();
		try {
			if (remark == null || remark.isEmpty()) {
				j.setSuccess(false);
				j.setMsg(EquipmentLogMessage.REMARK_IS_NULL.getMessage());
				return j;
			}
			if (remark.length() > LENGTH_LIMIT) {
				j.setSuccess(false);
				j.setMsg(EquipmentLogMessage.LENGTH_LIMIT.getMessage());
				return j;
			}
			int result = equipmentLogService.updateByParam(id, "1", "", remark);
			if (result > 0) {
				j.setSuccess(true);
				j.setMsg(EquipmentLogMessage.SUCCESS.getMessage());
			} else {
				j.setSuccess(false);
				j.setMsg(EquipmentLogMessage.FAIL.getMessage());
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到添加日志审阅页面
	 * 
	 * @return
	 */
	@RequestMapping("/toPassed")
	public String toPassed(HttpServletRequest request, Long id) {
		request.setAttribute("equipmentLog", equipmentLogService.query(id));
		return "/admin/equipment/passed";
	}

	/**
	 * 审阅
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/passed")
	@ResponseBody
	@OperateLogger(content = "equipmentLog_passed", operationType = OperateLogger.OperationType.U, table = "t_equipment_log")
	public Json passed(Long id) {
		Json j = new Json();
		try {
			int result = equipmentLogService.updateByParam(id, "2", "", "");
			if (result > 0) {
				j.setSuccess(true);
				j.setMsg(EquipmentLogMessage.SUCCESS.getMessage());
			} else {
				j.setSuccess(false);
				j.setMsg(EquipmentLogMessage.FAIL.getMessage());
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到添加日志备注详情页面
	 * 
	 * @return
	 */
	@RequestMapping("/detail")
	public String toDetail(HttpServletRequest request, Long id) {
		request.setAttribute("equipmentLog", equipmentLogService.query(id));
		return "/admin/equipment/detail";
	}

	/**
	 * 下载完成后更新掉下载状态
	 * 
	 * @return
	 */
	@RequestMapping("/updateIsDownload")
	@ResponseBody
	public Json updateIsDownload(Long id) {
		Json j = new Json();
		try {
			int result = equipmentLogService.updateByParam(id, "", "1", "");
			if (result > 0) {
				j.setSuccess(true);
			} else {
				j.setSuccess(false);
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param logName  文件名
	 * @throws IOException
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void download(Long id, HttpServletRequest request, HttpServletResponse response, String logName)
			throws IOException {
		byte[] buffer = new byte[1024];
		final String equipment_Log_BucketName = ConfigUtil.get("Equipment_Log_BucketName");
		try {
			// 读取文件
			InputStream fis = UFile.downloadFile(equipment_Log_BucketName, logName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + logName);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			OutputStream fos = response.getOutputStream();
			int length = fis.read(buffer);
			while (length > 0) {
				fos.write(buffer, 0, length);
				length = fis.read(buffer);
			}
			fis.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public EquipmentLog getBean(Long id) {
		return equipmentLogService.query(id);
	}
}
