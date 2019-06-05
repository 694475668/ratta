package com.ratta.suponote.param.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.NotCompare;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 错误码类
 * 2018-10-31
 */
@Model(table="t_errorcode")
public class ErrorCode implements Serializable{
	@NotCompare
	private static final long serialVersionUID = -5033829544965131912L;
	/** 错误编码(pk,固定长度2位) */
	@PK
	private String code;
	/** 系统错误 */
	private String remark;
	/** 错误描述 */
	private String desc;
	/** 操作员 */
	@NotCompare
	private String op_user;
	/** 操作时间 */
	@NotCompare
	private Date op_time;
	/** 错误描述英文*/
	private String desc_en;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getDesc_en() {
		return desc_en;
	}
	public void setDesc_en(String desc_en) {
		this.desc_en = desc_en;
	}
	@Override
	public String toString() {
		return "ErrorCode [code=" + code + ", remark=" + remark + ", desc="
				+ desc + ", op_user=" + op_user + ", op_time=" + op_time
				+ ", desc_en=" + desc_en + "]";
	}

	
}
