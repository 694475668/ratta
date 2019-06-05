package com.ratta.suponote.model.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author page
 * 角色类
 * 2018-10-31
 */
public class Trole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 角色ID
	 */
	private String id;
	/**
	 * 上级角色
	 */
	private Trole trole;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 下级角色集合
	 */
	private Set<Trole> troles = new HashSet<Trole>(0);
	/**
	 * 拥有资源集合
	 */
	private List<Tresource> tresources = new ArrayList<Tresource>(0);
//	private Set<Tuser> tusers = new HashSet<Tuser>(0);

	/**
	 * 角色创建时间
	 */
	private Date create_date;
	/**
	 * 角色最后修改时间
	 */
	private Date modify_date;
	/**
	 * 角色创建人
	 */
	private String create_user;
	/**
	 * 角色最后修改人
	 */
	private String modify_user;
	public Trole() {
	}

	public Trole(String id, String name) {
		this.id = id;
		this.name = name;
	}

//	public Trole(String id, Trole trole, String name, String remark, Integer seq, Set<Trole> troles, Set<Tresource> tresources, Set<Tuser> tusers) {
//		this.id = id;
//		this.trole = trole;
//		this.name = name;
//		this.remark = remark;
//		this.seq = seq;
//		this.troles = troles;
//		this.tresources = tresources;
//		this.tusers = tusers;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Trole getTrole() {
		return trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
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

	public Set<Trole> getTroles() {
		return troles;
	}

	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}



	public List<Tresource> getTresources() {
		return tresources;
	}

	public void setTresources(List<Tresource> tresources) {
		this.tresources = tresources;
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
		return "Trole [id=" + id + ", trole=" + trole + ", name=" + name + ", remark=" + remark + ", troles=" + troles
				+ ", tresources=" + tresources + ", create_date=" + create_date + ", modify_date=" + modify_date
				+ ", create_user=" + create_user + ", modify_user=" + modify_user + "]";
	}
	

}
