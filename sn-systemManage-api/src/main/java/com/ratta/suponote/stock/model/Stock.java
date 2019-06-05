package com.ratta.suponote.stock.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponote.model.system.ExcelResources;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 设备库存类
 * 2018-10-31
 */
@Model(table="t_m_stock")
public class Stock implements Serializable{

	/**
	 * 序列标志
	 */
	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	private Long id;
	/** 设备型号 */
	@ExcelResources(order=1,title="设备型号")
	private String equipment_model;
	/** 设备号 */
	@ExcelResources(order=3,title="设备号")
	private String equipment_no;
	/** 当前固件版本 */
	@ExcelResources(order=4,title="固件版本")
	private String firmware_version;
	/** 批次号*/
	@ExcelResources(order=2,title="批次号")
	private String batch_no;
	/** 出入库ID*/
	private Long inout_id;
	/** 入库时间 */
	@ExcelResources(order=5,title="入库时间")
	private Date op_time;
	/** 任务ID*/
	private int task_id;
	/** 发布固件版本*/
	private String releaseFirmwareVersion;
	/** 固件更新状态*/
	private String update_status;
	/** 出入库标识 */
	private String flag;
	/** 设备用途 */
	private String equipment_purpose;
	/** 激活状态 */
	private String state;
	/** 设备状态 */
	private String status;
	/**健康状态*/
	private String healthyState;
	/**备注*/
	private String remark;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInout_id() {
		return inout_id;
	}

	public void setInout_id(Long inout_id) {
		this.inout_id = inout_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getReleaseFirmwareVersion() {
		return releaseFirmwareVersion;
	}

	public void setReleaseFirmwareVersion(String releaseFirmwareVersion) {
		this.releaseFirmwareVersion = releaseFirmwareVersion;
	}

	public String getUpdate_status() {
		return update_status;
	}

	public void setUpdate_status(String update_status) {
		this.update_status = update_status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEquipment_model() {
		return equipment_model;
	}

	public void setEquipment_model(String equipment_model) {
		this.equipment_model = equipment_model;
	}

	public String getEquipment_no() {
		return equipment_no;
	}

	public void setEquipment_no(String equipment_no) {
		this.equipment_no = equipment_no;
	}

	public String getFirmware_version() {
		return firmware_version;
	}

	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public String getEquipment_purpose() {
		return equipment_purpose;
	}

	public void setEquipment_purpose(String equipment_purpose) {
		this.equipment_purpose = equipment_purpose;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public String getHealthyState() {
		return healthyState;
	}

	public void setHealthyState(String healthyState) {
		this.healthyState = healthyState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", equipment_model=" + equipment_model + ", equipment_no=" + equipment_no
				+ ", firmware_version=" + firmware_version + ", batch_no=" + batch_no + ", inout_id=" + inout_id
				+ ", op_time=" + op_time + ", task_id=" + task_id + ", releaseFirmwareVersion=" + releaseFirmwareVersion
				+ ", update_status=" + update_status + ", flag=" + flag + ", equipment_purpose=" + equipment_purpose
				+ ", state=" + state + ", status=" + status + ", healthyState=" + healthyState + ", remark=" + remark
				+ "]";
	}


}
