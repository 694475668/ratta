package com.ratta.suponote.param.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.NotCompare;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 系统参数明细类
 * 2018-10-31
 */
public class Reference implements Serializable {
	private static final long serialVersionUID = 207468343852470329L;
	@PK
	private Long id;
	/**序号*/
	private String serial;
	/**参数名*/
	private String name;
	/**参数值*/
	private String value;
	/**中文简体参数名称	*/
	private String value_cn;
	/**修改人员*/
	@NotCompare
	private String op_user;
	/**修改时间*/
	@NotCompare
	private Date op_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue_cn() {
		return value_cn;
	}
	public void setValue_cn(String value_cn) {
		this.value_cn = value_cn;
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
	
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Reference [id=" + id + ", serial=" + serial + ", name=" + name + ", value=" + value + ", value_cn="
				+ value_cn + ", op_user=" + op_user + ", op_time=" + op_time + "]";
	}
	
	

	
}
