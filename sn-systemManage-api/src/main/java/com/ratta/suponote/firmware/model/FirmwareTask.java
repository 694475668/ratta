package com.ratta.suponote.firmware.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 固件任务表
 * 2018-10-31
 */
@Model(table="t_m_firmware_task")
public class FirmwareTask implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	private Long id;
	/** 原始版本 */
	private String firmware_version;
	/** 发布版本 */
	private String deployVersion;
	/** 执行时间 */
	private String performTime;
	/** 发布人员 */
	private String deployUser;
	/** 发布时间 */
	private Date deployTime;
	/** 撤销人员 */
	private String revokeUser;
	/** 撤销时间 */
	private Date revokeTime;
	/** 完成标识 */
	private String finishFlag;
	/** 类型*/
	private String type;
	/** 执行类型*/
	private String porformType;
	/** 设备型号*/
	private String equipment_type;
	/** 配置*/
	private String configuration;
	/** 区域 */
	private String area;
	/** 定制 */
	private String custom;
	/** 设备用途 */
	private String equipment_purpose;
	/** 更新状态 */
	private String updateStatus;
	/** 设备号*/
	private String equipmentno;
	/** 设备批次*/
	private String batch_no;
	/** 业务码*/
	private String bz_code;
	/** 设备号集合*/
	private String equuipmentNo;
	/** 是否强行刷机*/
	private String isFlash;
	/** 是否是历史版本*/
	private String isHistory;
	/** 状态*/
	private String status;
	/** 刷机类型*/
	private int rootType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getEquipmentno() {
		return equipmentno;
	}
	public void setEquipmentno(String equipmentno) {
		this.equipmentno = equipmentno;
	}
	public String getFirmware_version() {
		return firmware_version;
	}
	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}
	
	public String getEquipment_purpose() {
		return equipment_purpose;
	}
	public void setEquipment_purpose(String equipment_purpose) {
		this.equipment_purpose = equipment_purpose;
	}
	public String getDeployVersion() {
		return deployVersion;
	}
	public void setDeployVersion(String deployVersion) {
		this.deployVersion = deployVersion;
	}
	
	public String getIsFlash() {
		return isFlash;
	}
	public void setIsFlash(String isFlash) {
		this.isFlash = isFlash;
	}
	public String getPerformTime() {
		return performTime;
	}
	public void setPerformTime(String performTime) {
		this.performTime = performTime;
	}
	public String getDeployUser() {
		return deployUser;
	}
	public void setDeployUser(String deployUser) {
		this.deployUser = deployUser;
	}
	
	public String getPorformType() {
		return porformType;
	}
	public void setPorformType(String porformType) {
		this.porformType = porformType;
	}
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
	public String getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getEquipment_type() {
		return equipment_type;
	}
	public void setEquipment_type(String equipment_type) {
		this.equipment_type = equipment_type;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	
	public String getBz_code() {
		return bz_code;
	}
	public void setBz_code(String bz_code) {
		this.bz_code = bz_code;
	}
	
	
	public String getEquuipmentNo() {
		return equuipmentNo;
	}
	public void setEquuipmentNo(String equuipmentNo) {
		this.equuipmentNo = equuipmentNo;
	}
	
	public String getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRevokeUser() {
		return revokeUser;
	}
	public void setRevokeUser(String revokeUser) {
		this.revokeUser = revokeUser;
	}
	public Date getRevokeTime() {
		return revokeTime;
	}
	public void setRevokeTime(Date revokeTime) {
		this.revokeTime = revokeTime;
	}
	
	public int getRootType() {
		return rootType;
	}
	public void setRootType(int rootType) {
		this.rootType = rootType;
	}
	@Override
	public String toString() {
		return "FirmwareTask [id=" + id + ", firmware_version=" + firmware_version + ", deployVersion=" + deployVersion
				+ ", performTime=" + performTime + ", deployUser=" + deployUser + ", deployTime=" + deployTime
				+ ", revokeUser=" + revokeUser + ", revokeTime=" + revokeTime + ", finishFlag=" + finishFlag + ", type="
				+ type + ", porformType=" + porformType + ", equipment_type=" + equipment_type + ", configuration="
				+ configuration + ", area=" + area + ", custom=" + custom + ", equipment_purpose=" + equipment_purpose
				+ ", updateStatus=" + updateStatus + ", equipmentno=" + equipmentno + ", batch_no=" + batch_no
				+ ", bz_code=" + bz_code + ", equuipmentNo=" + equuipmentNo + ", isFlash=" + isFlash + ", isHistory="
				+ isHistory + ", status=" + status + ", rootType=" + rootType + "]";
	}

}
