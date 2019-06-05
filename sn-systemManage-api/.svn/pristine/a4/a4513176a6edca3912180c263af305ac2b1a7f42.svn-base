package com.ratta.suponote.param.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.NotCompare;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 数据字典类
 * 2018-10-31
 */
@Model(table="t_dictionary")
public class Dictionary implements Serializable{
	
	@NotCompare
	private static final long serialVersionUID = -5033829544965131912L;
	/** 错误编码(pk) */
	@PK
	private Long id;
	/** 业务码*/
	private String name;
	/** 数据值 */
	private String value;
	/** 数据简体描述 */
	private String value_cn;
	/** 数据英文描述 */
	private String value_en;
	/** 操作员 */
	@NotCompare
	private String op_user;
	/** 操作时间 */
	@NotCompare
	private Date op_time;
	/** 备注*/
	private String remark;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue_cn() {
		return value_cn;
	}
	public void setValue_cn(String value_cn) {
		this.value_cn = value_cn;
	}
	public String getValue_en() {
		return value_en;
	}
	public void setValue_en(String value_en) {
		this.value_en = value_en;
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
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", name=" + name + ", value=" + value
				+ ", value_cn=" + value_cn + ", value_en=" + value_en
				+ ", op_user=" + op_user + ", op_time=" + op_time + ", remark="
				+ remark + "]";
	}

	
}
