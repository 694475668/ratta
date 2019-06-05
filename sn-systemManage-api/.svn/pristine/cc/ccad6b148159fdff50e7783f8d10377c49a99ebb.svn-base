package com.ratta.suponote.model.pagemodel;

import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.NotCompare;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 资源模型类
 * 2018-10-31
 */
@Model(table="t_m_resource")
public class Resource implements java.io.Serializable {
	@NotCompare
	private static final long serialVersionUID = 1L;
	/**
	 * 资源ID
	 */
	@PK
	@NotCompare
	private String id;
	/**
	 * 资源类型ID
	 */
	private String typeId;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 上级资源ID
	 */
	private String pid;
	/**
	 * 上级资源名称
	 */
	private String pname;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 资源序号
	 */
	private Integer seq;
	/**
	 * 资源链接
	 */
	private String url;
	/**
	 * 资源图标名称
	 */
	private String iconCls;
	
	private String roleId;
	private String roleName;
	/**
	 * 资源创建时间
	 */
	@NotCompare
	private Date create_time;
	/**
	 * 资源创建人
	 */
	@NotCompare
	private String create_user;
	/**
	 * 资源最后更新时间
	 */
	@NotCompare
	private Date update_time;
	/**
	 * 资源最后更新人
	 */
	@NotCompare
	private String update_user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", typeId=" + typeId + ", typeName="
				+ typeName + ", pid=" + pid + ", pname=" + pname + ", name="
				+ name + ", remark=" + remark + ", seq=" + seq + ", url=" + url
				+ ", iconCls=" + iconCls + ", roleId=" + roleId + ", roleName="
				+ roleName + ", create_time=" + create_time + ", create_user="
				+ create_user + ", update_time=" + update_time
				+ ", update_user=" + update_user + "]";
	}

}
