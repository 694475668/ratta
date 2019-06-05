package com.ratta.suponote.firmware.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 固件修复点表
 * 2018-10-31
 */
@Model(table="t_firmware_fixpoint")
public class FirmwareFixPoint implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	private Long id;
	/** 语言 */
	private String lan;
	/** 修复点 */
	private String fixPoint;
	/** 固件版本 */
	private String firmware_version;
	/** 操作人 */
	private String op_user;
	/** 操作时间 */
	private Date op_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getFixPoint() {
		return fixPoint;
	}
	public void setFixPoint(String fixPoint) {
		this.fixPoint = fixPoint;
	}
	public String getFirmware_version() {
		return firmware_version;
	}
	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}
	
	public String getOp_user() {
		return op_user;
	}
	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	@Override
	public String toString() {
		return "FirmwareFixPoint [id=" + id + ", lan=" + lan + ", fixPoint="
				+ fixPoint + ", firmware_version=" + firmware_version
				+ ", op_user=" + op_user + ", op_time=" + op_time + "]";
	}
	
}
