package com.ratta.suponote.model.system;

import java.io.Serializable;
import java.util.Date;

/**
 * @author page
 * 调度任务日志类
 * 2018-10-31
 */
public class ScheduleLog implements Serializable{
	/**
	 * 序列化标志
	 */
	private static final long serialVersionUID = -7252958968984194194L;
	/** PK seq_schedule_log */
	private Long id;
	/** 开始执行时间 */
	private Date ksrq;
	/** 结束执行时间 */
	private Date jsrq;
	/** 任务ID */
	private Long task_id;
	/** 执行结果（成功|失败） */
	private String result;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getKsrq() {
		return ksrq;
	}
	public void setKsrq(Date ksrq) {
		this.ksrq = ksrq;
	}
	public Date getJsrq() {
		return jsrq;
	}
	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}
	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ScheduleLog [id=" + id + ", ksrq=" + ksrq + ", jsrq=" + jsrq
				+ ", task_id=" + task_id + ", result=" + result + "]";
	}
	
}
