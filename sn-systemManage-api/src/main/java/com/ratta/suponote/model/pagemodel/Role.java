package com.ratta.suponote.model.pagemodel;

import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.NotCompare;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 角色模型类
 * 2018-10-31
 */
@Model(table="t_m_role")
public class Role implements java.io.Serializable {
	@NotCompare
	private static final long serialVersionUID = 1L;
	/**
	 * 角色ID
	 */
	@PK
	@NotCompare
	private String id;
	/**
	 * 上级角色ID
	 */
	private String pid;
	/**
	 * 上级角色名称
	 */
	private String pname;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 角色序列
	 */
	private Integer seq;
	/**
	 * 角色图标名称
	 */
	private String iconCls;
	/**
	 * 角色拥有的资源ID列表
	 */
	private String resourceIds;
	/**
	 * 角色拥有的资源名称列表
	 */
	private String resourceNames;

	/**
	 * 角色创建时间
	 */
	@NotCompare
	private Date create_date;
	/**
	 * 角色最后修改时间
	 */
	@NotCompare
	private Date modify_date;
	/**
	 * 角色创建人
	 */
	@NotCompare
	private String create_user;
	/**
	 * 角色最后修改人
	 */
	@NotCompare
	private String modify_user;
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getResourceNames() {
		return resourceNames;
	}

	public void setResourceNames(String resourceNames) {
		this.resourceNames = resourceNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", pid=" + pid + ", pname=" + pname
				+ ", name=" + name + ", remark=" + remark + ", seq=" + seq
				+ ", iconCls=" + iconCls + ", resourceIds=" + resourceIds
				+ ", resourceNames=" + resourceNames + ", create_date="
				+ create_date + ", modify_date=" + modify_date
				+ ", create_user=" + create_user + ", modify_user="
				+ modify_user + "]";
	}
	
	

}
