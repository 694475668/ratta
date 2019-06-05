package com.ratta.suponote.stock.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 设备退换货记录类
 * 2018-10-31
 */
@Model(table="t_m_return_record")
public class EquipmentReturnRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PK
	private Long id;
	/**设备号*/
	private String equipment_no;
	/**类型  1：退货   2：换货*/
	private String type;
	/** 被换货的设备号*/
	private String exchangeEquipmentNo;
	/**退换货编号*/
	private String serrialNumber;
	/**退货时间*/
	private String returnTime;
	/**业务受理人*/
	private String businessHandler;
	/**备注*/
	private String remark;
	/**健康状态*/
	private String healthyState;
	/** 操作人*/
	private String op_user;
    /**操作时间*/
	private Date op_time;
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
	public String getExchangeEquipmentNo() {
		return exchangeEquipmentNo;
	}
	public void setExchangeEquipmentNo(String exchangeEquipmentNo) {
		this.exchangeEquipmentNo = exchangeEquipmentNo;
	}
	public String getSerrialNumber() {
		return serrialNumber;
	}
	public void setSerrialNumber(String serrialNumber) {
		this.serrialNumber = serrialNumber;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getBusinessHandler() {
		return businessHandler;
	}
	public void setBusinessHandler(String businessHandler) {
		this.businessHandler = businessHandler;
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
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getHealthyState() {
		return healthyState;
	}
	public void setHealthyState(String healthyState) {
		this.healthyState = healthyState;
	}
	@Override
	public String toString() {
		return "EquipmentReturnRecord [id=" + id + ", equipment_no=" + equipment_no + ", type=" + type
				+ ", exchangeEquipmentNo=" + exchangeEquipmentNo + ", serrialNumber=" + serrialNumber + ", returnTime="
				+ returnTime + ", businessHandler=" + businessHandler + ", remark=" + remark + ", healthyState="
				+ healthyState + ", op_user=" + op_user + ", op_time=" + op_time + "]";
	}
	

}
