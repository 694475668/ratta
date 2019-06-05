package com.ratta.suponote.model.system;

import java.util.Date;
import java.util.Set;

/**
 * @author page
 * 用户类
 * 2018-10-31
 */
public class Tuser implements java.io.Serializable {
	/**
	 * 序列化标志
	 */
	private static final long serialVersionUID = 1L;
	/** pk */
	private Long id;
	/** 用户名 */
	private String username;
	/** 姓名 */
	private String name;
	/** 手机号码 */
	private String phone;
	/** 联系地址 */
	private String address;
	/** 电子邮箱 */
	private String email;
	/** 登录密码 */
	private String pwd;
	/** 登录密码错误次数 */
	private Long counts;
	/** 状态1：正常，0：锁定 */
	private String status;
	/** 有效标识（1：有效，0：无效，2：停用） */
	private String isactive;
	/** 最后一次登录时间 */
	private Date last_logindate;
	/** 创建时间 */
	private Date created;
	/** 创建人员 */
	private String createdby;
	/** 修改时间 */
	private Date updated;
	/** 修改人员 */
	private String updatedby;
	/** 是否已经修改密码:0未修改,1已修改 */
	private String modify_pwd;
	/**
	 * 用户拥有的角色
	 */
	private Set<Trole> troles;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public Date getLast_logindate() {
		return last_logindate;
	}
	public void setLast_logindate(Date last_logindate) {
		this.last_logindate = last_logindate;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	
	public String getModify_pwd() {
		return modify_pwd;
	}
	public void setModify_pwd(String modify_pwd) {
		this.modify_pwd = modify_pwd;
	}
	public Set<Trole> getTroles() {
		return troles;
	}
	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}
	@Override
	public String toString() {
		return "Tuser [id=" + id + ", username=" + username + ", name=" + name
				+ ", phone=" + phone + ", address=" + address + ", email="
				+ email + ", pwd=" + pwd + ", counts=" + counts + ", status="
				+ status + ", isactive=" + isactive + ", last_logindate="
				+ last_logindate + ", created=" + created + ", createdby="
				+ createdby + ", updated=" + updated + ", updatedby="
				+ updatedby + ", modify_pwd=" + modify_pwd + ", troles="
				+ troles + "]";
	}

}
