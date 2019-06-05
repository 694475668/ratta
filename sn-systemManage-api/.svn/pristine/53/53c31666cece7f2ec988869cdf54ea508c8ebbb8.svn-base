package com.ratta.suponote.stock.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponote.model.system.ExcelResources;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 设备库存出入库记录表
 * 2018-10-31
 */
@Model(table="t_m_inout")
public class InOutStock implements Serializable{
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
	private String equipment_no;
	/** 固件版本 */
	@ExcelResources(order=7,title="固件版本")
	private String firmware_version;
	/** 经销商 */
	@ExcelResources(order=6,title="经销商")
	private String dealers_name;
	/** 数量 */
	@ExcelResources(order=3,title="数量")
	private int counts;
	/** 起始序号 */
	@ExcelResources(order=4,title="起始序号")
	private String begin_sn;
	/** 截止序号*/
	@ExcelResources(order=5,title="截止序号")
	private String end_sn;
	/** 批次号*/
	@ExcelResources(order=2,title="批次号")
	private String batch_no;
	/** 出入库标识*/
	@ExcelResources(order=8,title="出入库标识")
	private String flag;
	/** 配置中文*/
	private String value_cn;
	/** 状态*/
	@ExcelResources(order=9,title="状态")
	private String status;
	/** 备注*/
	private String note;
	/**设备用途*/
	@ExcelResources(order=10,title="设备用途")
	private String equipment_purpose;
	/**配置*/
	private String configuration;
	/** 区域 */
	private String area;
	/** 定制 */
	private String custom;
	/** 操作员*/
	private String op_user;
	/** 操作时间 */
	private Date op_time;
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
	public String getDealers_name() {
		return dealers_name;
	}
	public void setDealers_name(String dealers_name) {
		this.dealers_name = dealers_name;
	}
	
	public String getEquipment_purpose() {
		return equipment_purpose;
	}
	public void setEquipment_purpose(String equipment_purpose) {
		this.equipment_purpose = equipment_purpose;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	public String getValue_cn() {
		return value_cn;
	}
	public void setValue_cn(String value_cn) {
		this.value_cn = value_cn;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getBegin_sn() {
		return begin_sn;
	}
	public void setBegin_sn(String begin_sn) {
		this.begin_sn = begin_sn;
	}
	public String getEnd_sn() {
		return end_sn;
	}
	public void setEnd_sn(String end_sn) {
		this.end_sn = end_sn;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	@Override
	public String toString() {
		return "InOutStock [id=" + id + ", equipment_model=" + equipment_model
				+ ", equipment_no=" + equipment_no + ", firmware_version="
				+ firmware_version + ", dealers_name=" + dealers_name
				+ ", counts=" + counts + ", begin_sn=" + begin_sn + ", end_sn="
				+ end_sn + ", batch_no=" + batch_no + ", flag=" + flag
				+ ", value_cn=" + value_cn + ", status=" + status + ", note="
				+ note + ", equipment_purpose=" + equipment_purpose
				+ ", configuration=" + configuration + ", area=" + area
				+ ", custom=" + custom + ", op_user=" + op_user + ", op_time="
				+ op_time + "]";
	}

}
