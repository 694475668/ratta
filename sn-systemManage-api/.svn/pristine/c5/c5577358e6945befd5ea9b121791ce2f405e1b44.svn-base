package com.ratta.suponote.model.system;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.NotCompare;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 调度任务类
 * 2018-10-31
 */
public class ScheduleTask implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -5932563521726797224L;
	/** 主键(seq_schedule_task) */
	
	@PK
	private Long id;
	/** 任务名称 */
	private String name;
	/** 任务描述 */
	private String remark;
	/** 克隆表达式 */
	private String cron;
	/** 任务状态(1停用,0启用) */
	private String status;
	
	/** 创建时间 */
	@NotCompare
	private Date created;
	/** 创建人员 */
	@NotCompare
	private String createdby;
	/** 修改时间 */
	@NotCompare
	private Date updated;
	/** 修改人员 */
	@NotCompare
	private String updatedby;
	/** 业务码 */
	@NotCompare
	private String bzcode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	public String getBzcode() {
		return bzcode;
	}
	public void setBzcode(String bzcode) {
		this.bzcode = bzcode;
	}
	@Override
	public String toString() {
		return "ScheduleTask [id=" + id + ", name=" + name + ", remark="
				+ remark + ", cron=" + cron + ", status=" + status
				+ ", created=" + created + ", createdby=" + createdby
				+ ", updated=" + updated + ", updatedby=" + updatedby
				+ ", bzcode=" + bzcode + "]";
	}


}
