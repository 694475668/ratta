package com.ratta.spnote.schedule;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.FirmwareUpdateUtil;
import com.ratta.spnote.util.UFile;
import com.ratta.suponote.equipment.model.EquipmentLog;
import com.ratta.suponote.equipment.service.EquipmentLogService;
import com.ratta.suponote.firmware.service.FirmwareTaskService;
import com.ratta.suponote.model.system.ScheduleLog;
import com.ratta.suponote.model.system.ScheduleTask;
import com.ratta.suponote.param.model.Reference;
import com.ratta.suponote.param.service.ReferenceService;
import com.ratta.suponote.stock.service.StockService;
import com.ratta.suponote.system.service.ScheduleTaskService;

/**
 * @author page 调度任务工厂 2018-10-31
 */
public class QuartzJobFactory implements Job {
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ScheduleTaskService scheduleTaskService;
	@Autowired
	private StockService stockService;
	@Autowired
	private FirmwareTaskService firmwareTaskService;
	@Autowired
	private EquipmentLogService equipmentLogService;
	@Autowired
	private ReferenceService referenceService;
	@Autowired
	private JobTrigger jobTrigger;

	/**
	 * 
	 * 所有的调度任务执行入口
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		ScheduleTask scheduleTask = (ScheduleTask) context.getMergedJobDataMap().get("ScheduleTask");
		String serviceName = scheduleTask.getBzcode();
		logger.info(scheduleTask.toString());

		// edit by ming 2016-12-01
		Date startD = new Date();
		int execResult = 0;

		if ("1".equals(scheduleTask.getStatus())) {
			logger.error("调度任务:{}已禁用", scheduleTask);
			return;
		}

		logger.info("调度任务[{}] is processing", scheduleTask.getName());
		if ("database_clean".equals(serviceName)) {

			execResult = scheduleTaskService.dataclean();

		} else if ("token_clean".equals(serviceName)) {

			execResult = scheduleTaskService.tokenclean();

		} else if ("file_clean".equals(serviceName)) {

			execResult = fileClean();

		} else if ("equipmentLog_clean".equals(serviceName)) {

			execResult = equipmentLogClean();

		} else if ("firmware_update".equals(serviceName)) {

			List<String> equipmentNoList = stockService
					.queryEquipmentNoByTaskId(Long.parseLong(scheduleTask.getName()));
			try {
				int result = FirmwareUpdateUtil.push(equipmentNoList, "03");
				if (result == 1) {
					firmwareTaskService.updateFinshFlag(Long.parseLong(scheduleTask.getName()));
					ScheduleTask job = new ScheduleTask();
					job.setName(scheduleTask.getName());
					try {
						jobTrigger.delete(job);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		// 执行结束
		Date endD = new Date();
		String result = execResult > 0 ? "0" : "1";
		logger.info("调度任务:{}执行结束,开始时间:{},结束时间:{},结果:{}", scheduleTask.getName(), startD, endD, result);
		addLog(scheduleTask.getId(), startD, endD, result);
	}

	/**
	 * 
	 * <p>
	 * equipmentLogClean
	 * </p>
	 * <p>
	 * 清理设备日志文件
	 * </p>
	 */
	private int equipmentLogClean() {
		int result = 0;
		int count = 0;
		final String equipment_Log_BucketName = ConfigUtil.get("Equipment_Log_BucketName");
		try {
			logger.info("清理设备日志文件：");
			List<Reference> ref = referenceService.queryParam("01", "EQUIPMENTLOG_CLEAN_DAY");
			long days_before = Integer.parseInt(ref.get(0).getValue());
			// 查询小于时间的所有记录
			List<EquipmentLog> equipmentLogs = equipmentLogService.selectByDays(days_before);
			if (equipmentLogs != null && equipmentLogs.size() > 0) {
				// 从ufile上删除小于时间的文件
				for (EquipmentLog equipmentLog : equipmentLogs) {
					equipmentLogService.delete(equipmentLog.getId());
					boolean deleteFileResult = UFile.deleteFile(equipment_Log_BucketName,
							equipmentLog.getLogName().trim());
					if (deleteFileResult) {
						count += 1;
					}
				}
				if (count == equipmentLogs.size()) {
					logger.info("清理设备日志文件成功！");
					result = 1;
				}
			} else {
				logger.info("无需清理！");
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("清理设备日志文件,错误原因:{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * <p>
	 * fileClean
	 * </p>
	 * <p>
	 * 清理项目中的垃圾文件t
	 * </p>
	 */
	private int fileClean() {
		try {
			logger.info("开始清理垃圾文件：");
			// 1、获取垃圾文件夹路径
			String path = ConfigUtil.get("file_clean_url");
			boolean result = deleteDir(path);
			if (result) {
				logger.info("清理垃圾文件成功！");
				return 1;
			} else {
				logger.info("清理垃圾文件失败！");
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("清理垃圾文件失败,错误原因:{}", e.getMessage());
			return 0;
		}
	}

	public boolean deleteDir(String path) {
		File file = new File(path);
		// 判断是否待删除目录是否存在
		if (!file.exists()) {
			logger.info("The dir are not exists!");
			return true;
		}
		// 取得当前目录下所有文件和文件夹
		String[] content = file.list();
		for (String name : content) {
			File temp = new File(path, name);
			// 判断是否是目录
			if (temp.isDirectory()) {
				// 递归调用，删除目录里的内容
				deleteDir(temp.getAbsolutePath());
				// 删除空目录
				temp.delete();
			} else {
				if (!temp.delete()) {
					// 直接删除文件
					logger.info("Failed to delete {}" + name);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 添加 task 日志
	 * 
	 * @param taskid 任务 id
	 * @param startD 开始时间
	 * @param endD   结束时间
	 * @param result 执行结果
	 */
	public void addLog(Long taskid, Date startD, Date endD, String result) {
		ScheduleLog log = new ScheduleLog();
		log.setTask_id(taskid);
		log.setKsrq(startD);
		log.setJsrq(endD);
		log.setResult(result);
		scheduleTaskService.addLog(log);
	}
}
