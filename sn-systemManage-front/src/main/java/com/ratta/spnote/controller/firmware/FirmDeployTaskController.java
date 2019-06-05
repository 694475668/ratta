package com.ratta.spnote.controller.firmware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.schedule.JobTrigger;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.FirmwareUpdateUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.firmware.model.FirmwareFixPoint;
import com.ratta.suponote.firmware.model.FirmwareTask;
import com.ratta.suponote.firmware.result.FirmwareTaskResult;
import com.ratta.suponote.firmware.service.FirmwareFixPointService;
import com.ratta.suponote.firmware.service.FirmwareTaskService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.system.ScheduleTask;
import com.ratta.suponote.param.model.Dictionary;
import com.ratta.suponote.param.model.Reference;
import com.ratta.suponote.param.service.DictionaryService;
import com.ratta.suponote.param.service.ReferenceService;
import com.ratta.suponote.stock.model.InOutStock;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponote.stock.service.InOutStockService;
import com.ratta.suponote.stock.service.StockService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 固件版本发布任务管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/firmDeployTaskController")
public class FirmDeployTaskController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FirmwareTaskService firmwareTaskService;
	@Autowired
	private StockService stockService;
	@Autowired
	private InOutStockService inOutStockService;
	@Autowired
	private ReferenceService referenceService;
	@Autowired
	private FirmwareFixPointService firmwareFixPointService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private JobTrigger jobTrigger;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String equipment_type, String configuration, String finishFlag,
			String batch_no, String deployVersion, String isHistory, String beginTime, String endTime,
			HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("equipment_type", equipment_type);
		params.put("finishFlag", finishFlag);
		params.put("batch_no", batch_no);
		params.put("deployVersion", deployVersion);
		params.put("isHistory", isHistory);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return firmwareTaskService.query(ph, params);
	}

	/**
	 * 发布
	 * 
	 * @param FirmwareTask
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deploy", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "firmwareTask_update", operationType = OperateLogger.OperationType.U, table = "t_m_firmware_task")
	public Json deploy(FirmwareTask firmwareTask, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<String> equipmentNoList = new ArrayList<String>();
		Json j = new Json();
		firmwareTask.setIsFlash("N");
		firmwareTask.setType("2");
		try {
			boolean boo = fixPointIsExist(firmwareTask);
			if (!boo) {
				j.setMsg(FirmwareMessage.FIXPOINT_IS_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}
			Map<String, Object> params = new HashMap<String, Object>(16);
			params.put("batchNos", firmwareTask.getBatch_no().split(","));
			params.put("status", "1");
			params.put("equipment_purpose", firmwareTask.getEquipment_purpose());
			List<InOutStock> inouts = inOutStockService.query(params);

			// 判断是立即发布还是定时发布
			if ("0".equals(firmwareTask.getPorformType())) {
				firmwareTask.setFinishFlag("1");
				firmwareTask.setPerformTime("立即执行");
				for (InOutStock inOutStock : inouts) {
					List<Stock> stocks = stockService.queryEquipmentByInOutId(inOutStock.getId());
					for (Stock stock : stocks) {
						equipmentNoList.add(stock.getEquipment_no());
					}
				}
				// 直接推送给终端接口，未终端传建任务
				int result = FirmwareUpdateUtil.push(equipmentNoList, "03");
				if (result == 0) {
					j.setMsg(FirmwareMessage.FIRMWARE_UPDATE_ERROR.getMessage());
					j.setSuccess(false);
					return j;
				}
			} else {
				// 添加调度任务
				ScheduleTask job = new ScheduleTask();
				Long id = firmwareTaskService.generateId();
				job.setName(id + "");
				job.setCron(firmwareTask.getPerformTime());
				job.setStatus("0");
				job.setBzcode("firmware_update");
				jobTrigger.add(job);
				// 添加固件更新任务数据
				firmwareTask.setFinishFlag("0");
				firmwareTask.setId(id);
			}
			deleteTask(inouts);
			FirmwareTaskResult firmwareTaskResult = firmwareTaskService.deploy(firmwareTask, inouts, sessionInfo);
			j.setSuccess(firmwareTaskResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(firmwareTaskResult, sessionInfo != null ? sessionInfo.getLocale() : null));

		} catch (Exception e) {
			j.setMsg(FirmwareMessage.FIRMWARE_UPDATE_ERROR.getMessage());
			logger.error(e.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 跳转到刷机页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toFlash")
	public String toFlash() {
		return "/admin/firmware/flash";
	}

	/**
	 * 刷机
	 * 
	 * @param FirmwareTask
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/flash", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "firmwareTask_flash", operationType = OperateLogger.OperationType.U, table = "t_m_firmware_task")
	public Json flash(FirmwareTask firmwareTask, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		firmwareTask.setIsFlash("Y");
		firmwareTask.setType("3");
		int result = 0;
		List<InOutStock> inouts = null;
		List<String> equipmentNoList = new ArrayList<String>();
		if (firmwareTask.getRootType() == 1) {
			if (firmwareTask.getEquipmentno().isEmpty()) {
				j.setMsg(FirmwareMessage.EQUIPMENTNO_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}
		} else {
			if (firmwareTask.getBatch_no().isEmpty() || firmwareTask.getEquipment_purpose().isEmpty()
					|| firmwareTask.getEquipment_type().isEmpty()) {
				j.setMsg(FirmwareMessage.BATCH_EQUIPTYPE_PURPOSE_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}
		}

		try {
			boolean boo = fixPointIsExist(firmwareTask);
			if (!boo) {
				j.setMsg(FirmwareMessage.FIXPOINT_IS_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}
			Map<String, Object> params = new HashMap<String, Object>(16);
			params.put("batchNos", firmwareTask.getBatch_no().split(","));
			params.put("status", "1");
			params.put("equipment_purpose", firmwareTask.getEquipment_purpose());
            
			// 查询设备列表，推送终端接口
			if (!"".equals(firmwareTask.getEquipmentno())) {
				Stock stock = stockService.queryByEquipNo(firmwareTask.getEquipmentno());
				if (stock == null) {
					j.setMsg(FirmwareMessage.EQUIPMENTNO_NULL.getMessage());
					j.setSuccess(false);
					return j;
				}
				InOutStock inOutStock = inOutStockService.queryByInoutId(stock.getInout_id(), "");
				if (stock.getTask_id() != 0) {
					List<String> string = stockService.queryEquipmentNoByTaskId(stock.getTask_id());
					if (string.size() == 1) {
						FirmwareTask task = firmwareTaskService.query(new Long((long) stock.getTask_id()));
						if ("1".equals(task.getType())) {
							firmwareTaskService.deleteTaskById(stock.getTask_id());
						} else {
							firmwareTaskService.updateIsHistory(stock.getTask_id());
							if (!"立即执行".equals(task.getPerformTime())) {
								ScheduleTask job = new ScheduleTask();
								job.setName(stock.getTask_id() + "");
								jobTrigger.delete(job);
							}
						}
					}
				}
				firmwareTask.setBatch_no(inOutStock.getBatch_no());
				firmwareTask.setEquipment_purpose(inOutStock.getEquipment_purpose());
				firmwareTask.setEquipment_type(inOutStock.getEquipment_model());
				equipmentNoList.add(firmwareTask.getEquipmentno());
				result = FirmwareUpdateUtil.push(equipmentNoList, "03");
				inouts = null;
			} else {
				inouts = inOutStockService.query(params);
				for (InOutStock inOutStock : inouts) {
					List<Stock> stocks = stockService.queryEquipmentByInOutId(inOutStock.getId());
					for (Stock stock : stocks) {
						equipmentNoList.add(stock.getEquipment_no());
					}
				}
				result = FirmwareUpdateUtil.push(equipmentNoList, "03");
				if (result == 1) {
					deleteTask(inouts);
				}
			}
			if (result == 1) {
				firmwareTask.setFinishFlag("1");
				firmwareTask.setPerformTime("立即执行");
				FirmwareTaskResult firmwareTaskResult = firmwareTaskService.deploy(firmwareTask, inouts, sessionInfo);
				j.setSuccess(firmwareTaskResult.getValue() == 0);
				j.setMsg(ResultEnumUtil.getDesc(firmwareTaskResult,
						sessionInfo != null ? sessionInfo.getLocale() : null));
			} else {
				j.setMsg(FirmwareMessage.FIRMWARE_UPDATE_ERROR.getMessage());
				j.setSuccess(false);
			}

		} catch (Exception e) {
			j.setMsg(FirmwareMessage.FIRMWARE_UPDATE_ERROR.getMessage());
			logger.error(e.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 跳转到调度时间设置页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/quartz")
	public String quartz(HttpServletRequest request) {
		return "/admin/firmware/quartz";
	}

	/**
	 * 跳转到设备库存管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/firmware/firmwareDeploy";
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
	@OperateLogger(content = "FirmwareTask_delete", operationType = OperateLogger.OperationType.D, table = "t_m_firmware_task")
	public Json delete(Long id, String batch_no, String equipment_purpose, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			Map<String, Object> params = new HashMap<String, Object>(16);
			params.put("batch_no", batch_no);
			params.put("status", "1");
			params.put("equipment_purpose", equipment_purpose);
			List<InOutStock> inouts = inOutStockService.query(params);

			FirmwareTask firmwareTask = firmwareTaskService.query(id);

			if (!"1".equals(firmwareTask.getFinishFlag())) {
				if ("0".equals(firmwareTask.getIsHistory())) {
					if (!"立即执行".equals(firmwareTask.getPerformTime())) {
						ScheduleTask job = new ScheduleTask();
						job.setName(id + "");
						jobTrigger.delete(job);
					}
				}

			}
			FirmwareTaskResult result = firmwareTaskService.delete(id, inouts, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 撤销
	 * 
	 * @param id                id
	 * @param batch_no          批次号
	 * @param equipment_purpose 设备用途
	 * @param session
	 * @return
	 */
	@RequestMapping("/revoke")
	@ResponseBody
	@OperateLogger(content = "FirmwareTask_revoke", operationType = OperateLogger.OperationType.D, table = "t_m_firmware_task")
	public Json revoke(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FirmwareTask firmwareTask = firmwareTaskService.query(id);

			if (!"1".equals(firmwareTask.getFinishFlag())) {
				if ("0".equals(firmwareTask.getIsHistory())) {
					if (!"立即执行".equals(firmwareTask.getPerformTime())) {
						ScheduleTask job = new ScheduleTask();
						job.setName(id + "");
						jobTrigger.delete(job);
					}
				}

			}
			FirmwareTaskResult result = firmwareTaskService.revoke(firmwareTask, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/** 判断固件版本是否有匹配的修复点 */
	public boolean fixPointIsExist(FirmwareTask firmwareTask) {
		List<Reference> ref = referenceService.queryParam("", "CHECK_FIXPOINT_VERTYPE");
		Map<String, Object> params = new HashMap<String, Object>(8);
		for (Reference reference : ref) {
			String type = firmwareTask.getFirmware_version()
					.substring(firmwareTask.getFirmware_version().lastIndexOf("_") + 1);
			if (type.equals(reference.getValue())) {
				params.put("firmware_version", firmwareTask.getFirmware_version());
				List<FirmwareFixPoint> fixpoint = firmwareFixPointService.queryByParam(params);
				if (fixpoint != null && fixpoint.size() > 0) {
					List<Dictionary> dictionary = dictionaryService.queryDictionary("LAN");
					if (fixpoint.size() != dictionary.size()) {
						logger.error("请为固件添加修复点！");
						return false;
					}
				} else {
					logger.error("请为固件添加修复点！");
					return false;
				}
			}
		}
		return true;
	}

	/** 将该批次号和设备用途的设备之前的定时任务全部从任务列表删除 */
	public void deleteTask(List<InOutStock> inouts) {
		try {
			for (InOutStock inOutStock : inouts) {
				List<Long> list = stockService.queryTaskIdByInOutId(inOutStock.getId());
				if (list != null && list.size() > 0) {
					for (Long long1 : list) {
						if (long1 != 0) {
							FirmwareTask task = firmwareTaskService.query(long1);
							if (task != null) {
								if ("1".equals(task.getType())) {
									firmwareTaskService.deleteTaskById(long1);
								} else {
									firmwareTaskService.updateIsHistory(long1);
									if (!"立即执行".equals(task.getPerformTime())) {
										ScheduleTask job = new ScheduleTask();
										job.setName(long1 + "");
										jobTrigger.delete(job);
									}
								}
							}
						}
					}
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public FirmwareTask getBean(Long id) {
		return firmwareTaskService.query(id);
	}
}
