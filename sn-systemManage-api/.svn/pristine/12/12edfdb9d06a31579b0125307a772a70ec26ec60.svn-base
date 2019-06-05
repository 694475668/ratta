package com.ratta.suponote.model.system.maint;

import java.io.Serializable;
import java.util.Date;

/**
 * @author page
 * 操作审计类
 * 2018-10-31
 */
public class Audit implements Serializable {
	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -4800755324508555763L;
	/** PK */
	private Long id;
	/** 用户名*/
	private String username;
	/** 资源id */
	private String resource_id;
	/** 操作类型（1:查询2:添加3:修改4:删除） */
	private String op_type;
	/** 操作事项 */
	private String op_item;
	/** 客户端IP */
	private String client_ip;
	/** 操作人员 */
	private String op_user;
	/** 操作时间 */
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
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getOp_type() {
		return op_type;
	}
	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}
	public String getOp_item() {
		return op_item;
	}
	public void setOp_item(String op_item) {
		this.op_item = op_item;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
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
		return "Audit [id=" + id + ", username=" + username + ", resource_id="
				+ resource_id + ", op_type=" + op_type + ", op_item=" + op_item
				+ ", client_ip=" + client_ip + ",  op_user=" + op_user + ", op_time=" + op_time + "]";
	}
	
	
}
