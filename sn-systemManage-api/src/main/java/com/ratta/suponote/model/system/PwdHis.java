package com.ratta.suponote.model.system;

import java.io.Serializable;
import java.util.Date;

/**
 * @author page
 * 密码历史类
 * 2018-10-31
 */
public class PwdHis implements Serializable{
	private static final long serialVersionUID = -8217703621429876178L;
	/**
	 * 主键 seq_pwd_his
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 操作时间
	 */
	private Date op_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	@Override
	public String toString() {
		return "PwdHis [id=" + id + ", username=" + username + ", pwd=" + pwd
				+ ", op_time=" + op_time + "]";
	}
	
	
}

	