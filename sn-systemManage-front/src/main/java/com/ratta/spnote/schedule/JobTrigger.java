package com.ratta.spnote.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ratta.suponote.model.system.ScheduleTask;
import com.ratta.suponote.system.service.ScheduleTaskService;

/**
 * @author page 调度任务trigger 2018-10-31
 */
public class JobTrigger {
	@Autowired
	private Scheduler scheduler;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 调度任务dao
	 */
	@Autowired
	private ScheduleTaskService scheduleTaskService;

	/**
	 * 
	 * <p>
	 * run
	 * </p>
	 * <p>
	 * 调度任务初始化方法(在spring-context.xml配置执行)
	 * </p>
	 * 
	 * @throws SchedulerException
	 */
	public void run() throws SchedulerException {
		List<ScheduleTask> jobList = scheduleTaskService.queryAll();
		for (ScheduleTask job : jobList) {
			// update by ming 2017-03-01
			if (job.getStatus().equals("0")) {
				logger.info("启动调度任务:{}", job);
				add(job);
			}
		}
	}

	public void add(ScheduleTask job) throws SchedulerException {
		// 老李写的玩意 这里有问题 comment by ming 2017-03-01
//		if(job.getStatus().equals("1")){//1停用
//			logger.info("添加调度任务失败,该任务已停用:" + job.toString());
//			return;
//		}

		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName());
		// 获取trigger，相当于在spring配置文件中定义的bean
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 如果trigger不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(job.getName()).build();
			jobDetail.getJobDataMap().put("ScheduleTask", job);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getName()).withSchedule(scheduleBuilder).build();
//			scheduler.start();// !!!!!!! add by jjustt for test 20160826
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/** 暂停任务 */
	public void paused(ScheduleTask job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getName());
		scheduler.pauseJob(jobKey);
	}

	/** 恢复任务 */
	public void resume(ScheduleTask job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getName());
		scheduler.resumeJob(jobKey);
	}

	/** 删除任务 */
	public void delete(ScheduleTask job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getName());
		scheduler.deleteJob(jobKey);
	}

	/** 立即执行 */
	public void immediateExecute(ScheduleTask job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getName());
		JobDataMap data = new JobDataMap();
		data.put("ScheduleTask", job);
		scheduler.triggerJob(jobKey, data);
	}

	/** 计划中的任务 */
	public List<ScheduleTask> getPlaningJob() throws SchedulerException {
		List<ScheduleTask> list = new ArrayList<ScheduleTask>();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleTask job = new ScheduleTask();
				job.setName(jobKey.getName());
				job.setRemark("Trigger：" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCron(cronExpression);
				}
				list.add(job);
			}
		}
		return list;
	}

	/** 正在运行的任务 */
	public List<ScheduleTask> getRunningJob() throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleTask> list = new ArrayList<ScheduleTask>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleTask job = new ScheduleTask();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setName(jobKey.getName());
			job.setRemark("Trigger:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCron(cronExpression);
			}
			list.add(job);
		}
		return list;
	}

	/** 更新CronExpression */
	public void update(ScheduleTask job) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		if (trigger == null) {
			// 计划任务没有启用，不需要更新 CronExpression
			return;
		}

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

}
