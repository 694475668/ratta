package com.ratta.spnote.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.schedule.JobTrigger;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.HttpReqRspUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.system.ScheduleTask;
import com.ratta.suponote.system.service.ScheduleTaskService;

/**
 * @author page 调度管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/scheduleTaskController")
public class ScheduleTaskController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ScheduleTaskService scheduleTaskService;
	@Autowired
	private JobTrigger jobTrigger;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String name, String status) {
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("name", name);
		params.put("status", status);
		return scheduleTaskService.query(ph, params);
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/system/schedule/main";
	}

	@RequestMapping("/quartz")
	public String quartz(HttpServletRequest request, Long id) {
		ScheduleTask scheduleTask = scheduleTaskService.queryById(id);
		request.setAttribute("scheduleTask", scheduleTask);
		return "/admin/system/schedule/quartz";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addSchedule")
	public String addSchedule() {
		return "/admin/system/schedule/addSchedule";
	}

	/**
	 * 添加
	 * 
	 * @param scheduleTask
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Json add(ScheduleTask scheduleTask, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			scheduleTaskService.save(scheduleTask, sessionInfo);
			j.setSuccess(true);
			j.setMsg(ScheduleTaskMessage.ADD_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(ScheduleTaskMessage.ADD_ERROR.getMessage(e.getMessage()));
			logger.error(e.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping("/getQuartz")
	@ResponseBody
	public ScheduleTask getQuartz(Long id) {
		return scheduleTaskService.queryById(id);
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editSchedule")
	public String editSchedule(HttpServletRequest request, Long id) {
		ScheduleTask scheduleTask = scheduleTaskService.queryById(id);
		request.setAttribute("scheduleTask", scheduleTask);
		return "/admin/system/schedule/updateSchedule";
	}

	/**
	 * 修改
	 * 
	 * @param scheduleTask
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "scheduleTask_update", operationType = OperateLogger.OperationType.U, table = "t_m_schedule_task")
	public Json edit(ScheduleTask scheduleTask, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			scheduleTaskService.update(scheduleTask, sessionInfo);
			jobTrigger.update(scheduleTask);
			j.setSuccess(true);
			j.setMsg(ScheduleTaskMessage.EDIT_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(ScheduleTaskMessage.EDIT_ERROR.getMessage(e.getMessage()));
			logger.error(e.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 
	 * 启用定时任务
	 * 
	 * @author jjustt
	 */
	@RequestMapping("/addTask")
	@ResponseBody
	@OperateLogger(content = "scheduleTask_open", operationType = OperateLogger.OperationType.U, table = "t_m_schedule_task")
	public Json addTask(String ids) {
		SessionInfo sessionInfo = HttpReqRspUtil.getSessionInfo();
		Json j = new Json();
		try {
			if (StringUtils.isEmpty(ids)) {
				j.setMsg(ScheduleTaskMessage.SCHEDULE_ID_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}
			for (String id : ids.split(",")) {
				ScheduleTask task = scheduleTaskService.queryById(Long.parseLong(id));
				if (task != null) {
					jobTrigger.add(task);
					task.setStatus("0");
					scheduleTaskService.update(task, sessionInfo);
				}

			}
			j.setSuccess(true);
			j.setMsg(ScheduleTaskMessage.START_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(ScheduleTaskMessage.START_ERROR.getMessage(e.getMessage()));
			logger.error(e.getMessage());
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;

	}

	/**
	 * 停用定时任务
	 * 
	 * @return
	 */
	@RequestMapping("/removeTask")
	@ResponseBody
	@OperateLogger(content = "scheduleTask_stop", operationType = OperateLogger.OperationType.U, table = "t_m_schedule_task")
	public Json removeTask(String ids) {
		SessionInfo sessionInfo = HttpReqRspUtil.getSessionInfo();
		Json j = new Json();
		try {
			if (StringUtils.isEmpty(ids)) {
				j.setMsg(ScheduleTaskMessage.SCHEDULE_ID_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}
			for (String id : ids.split(",")) {
				ScheduleTask task = scheduleTaskService.queryById(Long.parseLong(id));
				if (task != null) {
					jobTrigger.delete(task);
					task.setStatus("1");
					scheduleTaskService.update(task, sessionInfo);
				}

			}
			j.setSuccess(true);
			j.setMsg(ScheduleTaskMessage.STOP_SUCCESS.getMessage());

		} catch (Exception e) {
			j.setMsg(ScheduleTaskMessage.STOP_ERROR.getMessage(e.getMessage()));
			logger.error(e.getMessage());
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 执行定时任务
	 * 
	 * @return
	 */
	@RequestMapping("/executeTask")
	@ResponseBody
	@OperateLogger(content = "scheduleTask_exec", operationType = OperateLogger.OperationType.U, table = "t_m_schedule_task")
	public synchronized Json executeTask(Long id, HttpSession session) {
		Json j = new Json();
		try {
			if (id == null) {
				j.setMsg(ScheduleTaskMessage.SCHEDULE_ID_NULL.getMessage());
				j.setSuccess(false);
				return j;
			}

			ScheduleTask scheduleTask = scheduleTaskService.queryById(id);
			boolean running = false;
			for (ScheduleTask runningTask : jobTrigger.getRunningJob()) {
				logger.info("runningTask:" + runningTask);
				if (runningTask.getName().equals(scheduleTask.getName())
						&& Trigger.TriggerState.NORMAL.name().equals(runningTask.getStatus())) {
					running = true;
					j.setMsg(ScheduleTaskMessage.TASK_RUNNING.getMessage(
							HttpReqRspUtil.getSessionInfo().getLocale().getCountry(), scheduleTask.getName()));
					return j;
				}
			}
			if (!running) {
				jobTrigger.immediateExecute(scheduleTask);
			}
			j.setSuccess(true);
			j.setMsg(ScheduleTaskMessage.EXEC_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(ScheduleTaskMessage.EXEC_ERROR.getMessage(e.getMessage()));
			logger.error(e.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 查看日志页面
	 * 
	 * @return
	 */
	@RequestMapping("/logPage")
	public String logPage(String id, HttpSession request) {
		request.setAttribute("task_id", Integer.parseInt(id));
		return "/admin/system/schedule/showlog";
	}

	/**
	 * 查看日志
	 * 
	 * @param ph
	 * @param date_begin
	 * @param date_end
	 * @return
	 */
	@RequestMapping("/dataGridDetail")
	@ResponseBody
	public DataGrid dataGridDetail(PageHelper ph, String id, String date_begin, String date_end, HttpSession request) {
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("task_id", request.getAttribute("task_id"));
		params.put("date_begin", date_begin);
		params.put("date_end", date_end);
		return scheduleTaskService.queryLog(ph, params);
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleTask getBean(Long id) {
		return scheduleTaskService.queryById(id);
	}

	public ScheduleTask getBean(String id) {
		return getBean(Long.parseLong(id));
	}

	/**
	 * 多个id获取实体
	 * 
	 * @param ids
	 * @return
	 */
	public ScheduleTask[] getBean(String[] ids) {
		ScheduleTask[] tasks = new ScheduleTask[ids.length];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = getBean(ids[i]);
		}
		return tasks;
	}
}