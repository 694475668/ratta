package com.ratta.spnote.controller.system;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 调度任务信息
 * 2018-10-31
 */
public class ScheduleTaskMessage {
	/**
	 * 添加成功!
	 */
	public static final Message ADD_SUCCESS=Message.createInstance("添加成功!", "schedule task added");
	
	/**
	 * 添加失败,错误:{0}
	 */
	public static final Message ADD_ERROR=Message.createInstance("添加失败,错误:{0}!", "schedule task insert failure, error {0}");
	/**
	 * 编辑成功
	 */
	public static final Message EDIT_SUCCESS=Message.createInstance("编辑成功", "schedule task modified");
	/**
	 * 编辑失败
	 */
	public static final Message EDIT_ERROR=Message.createInstance("编辑失败！",  "schedule task modification failiure！");
	/**
	 * 启动定时任务成功
	 */
	public static final Message START_SUCCESS=Message.createInstance("启用定时任务成功!",  "schedule task enabled");
	/**
	 * 启动定时任务失败,错误:{0}
	 */
	public static final Message START_ERROR= Message.createInstance("定时任务启用失败 错误:{0}",  "enable task failure, error {0}");
	/**
	 * 停用定时任务成功
	 */
	public static final Message STOP_SUCCESS=Message.createInstance("停用定时任务成功!",  "schedule task disabled");
	/**
	 * 定时任务停用失败,错误:{0}
	 */
	public static final Message STOP_ERROR=Message.createInstance("停用定时任务失败 错误:{0}" , "disable task failure, error {0}");
	/**
	 * 已执行定时任务
	 */
	public static final Message EXEC_SUCCESS=Message.createInstance("已执行定时任务","task executed");
	/**
	 * 定时任务执行异常,错误:{0}
	 */
	public static final Message EXEC_ERROR=Message.createInstance("定时任务执行失败 错误:{0}",  "run task failure, error {0}");
	/**
	 * 未选择调度任务
	 */
	public static final Message SCHEDULE_ID_NULL=Message.createInstance("未选择调度任务", "task not selected");
	/**
	 * 定时任务:{0}已停用
	 */
	public static final Message STATUS_STOP=Message.createInstance("定时任务[{0}]已停用",  "schedule task {0} disabled");
	/**
	 * 任务[{0}]正在运行
	 */
	public static final Message TASK_RUNNING=Message.createInstance("任务[{0}]正在运行",  "task [{0}] is running");
}
