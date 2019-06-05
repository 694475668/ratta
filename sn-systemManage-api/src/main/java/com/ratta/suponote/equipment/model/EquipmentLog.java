package com.ratta.suponote.equipment.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 设备日志记录表
 * 2018-10-31
 */
@Model(table="t_equipment_log")
public class EquipmentLog implements Serializable{

	/**
	 * 序列标志
	 */
	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	
	private Long id;
	/** 设备号*/
	private String equipment_no;
	/** 类型 1：普通日志   2：错误日志 */
	private String type;
	/** 日志名*/
	private String logName;
	/**固件版本号*/
	private String firmware_version;
	/**时间*/
	private Date create_time;
	/**备注*/
	private String remark;
	/**是否已下载*/
	private String isDownload;
	/**标识*/
	private String flag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEquipment_no() {
		return equipment_no;
	}
	public void setEquipment_no(String equipment_no) {
		this.equipment_no = equipment_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getFirmware_version() {
		return firmware_version;
	}
	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsDownload() {
		return isDownload;
	}
	public void setIsDownload(String isDownload) {
		this.isDownload = isDownload;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "EquipmentLog [id=" + id + ", equipment_no=" + equipment_no + ", type=" + type + ", logName=" + logName
				+ ", firmware_version=" + firmware_version + ", create_time=" + create_time + ", remark=" + remark
				+ ", isDownload=" + isDownload + ", flag=" + flag + "]";
	}
	
}
