package com.ratta.suponote.firmware.model;

import java.io.Serializable;
import java.util.Date;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 固件设备表
 * 2018-10-31
 */
@Model(table="t_m_firmware_euip")
public class FirmwareEquipment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PK
	private Long id;
	/** 设备型号 */
	private String equipment_model;
	/** 设备型号名称 */
	private String type;
	/** 上传时间 */
	private Date upload_time;
	/** 固件版本 */
	private String firmware_version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEquipment_model() {
		return equipment_model;
	}
	public void setEquipment_model(String equipment_model) {
		this.equipment_model = equipment_model;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public String getFirmware_version() {
		return firmware_version;
	}
	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "FirmwareEquipment [id=" + id + ", equipment_model="
				+ equipment_model + ", type=" + type + ", upload_time="
				+ upload_time + ", firmware_version=" + firmware_version + "]";
	}
	

}
