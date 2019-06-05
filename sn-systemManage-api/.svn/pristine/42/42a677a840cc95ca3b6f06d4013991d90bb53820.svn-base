package com.ratta.suponote.business.model;

import java.io.Serializable;
import java.util.Date;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 设备型号维护类
 * 2018-10-31
 */
@Model(table="t_m_equiptype")
public class EquipType implements Serializable{

	/**
	 * 序列标志
	 */
	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	private Long id;
	/** 设备型号 */
	private String type;
	/** SN总长度  */
	private int sn_length;
	/** SN头   */
	private String sn_front;
	/** SN偏移   */
	private int sn_offset;
	/** SN偏移长度  */
	private int sn_offset_length;
	/** 规格简述 */
	private String remark;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSn_length() {
		return sn_length;
	}
	public void setSn_length(int sn_length) {
		this.sn_length = sn_length;
	}
	public String getSn_front() {
		return sn_front;
	}
	public void setSn_front(String sn_front) {
		this.sn_front = sn_front;
	}
	public int getSn_offset() {
		return sn_offset;
	}
	public void setSn_offset(int sn_offset) {
		this.sn_offset = sn_offset;
	}
	public int getSn_offset_length() {
		return sn_offset_length;
	}
	public void setSn_offset_length(int sn_offset_length) {
		this.sn_offset_length = sn_offset_length;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		return "EquipType [id=" + id + ", type=" + type + ", sn_length="
				+ sn_length + ", sn_front=" + sn_front + ", sn_offset="
				+ sn_offset + ", sn_offset_length=" + sn_offset_length
				+ ", remark=" + remark + ", op_user=" + op_user + ", op_time="
				+ op_time + "]";
	}
	
}
