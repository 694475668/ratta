package com.ratta.suponote.model.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author page
 * 资源类
 * 2018-10-31
 */
public class Tresource implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 资源ID
	 */
	private String id;
	/**
	 * 资源类型
	 */
	private Tresourcetype tresourcetype;
	/**
	 * 上级资源
	 */
	private Tresource tresource;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源备注
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
	 * 资源图标
	 */
	private String icon;
	/**
	 * 下级资源
	 */
	private Set<Tresource> tresources = new HashSet<Tresource>(0);
	/**
	 * 资源创建时间
	 */
	private Date create_time;
	/**
	 * 资源创建人
	 */
	private String create_user;
	/**
	 * 资源最后更新时间
	 */
	private Date update_time;
	/**
	 * 资源最后更新人
	 */
	private String update_user;
	
	public Tresource() {
	}

	public Tresource(String id, Tresourcetype tresourcetype, String name) {
		this.id = id;
		this.tresourcetype = tresourcetype;
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tresourcetype getTresourcetype() {
		return tresourcetype;
	}

	public void setTresourcetype(Tresourcetype tresourcetype) {
		this.tresourcetype = tresourcetype;
	}

	public Tresource getTresource() {
		return tresource;
	}

	public void setTresource(Tresource tresource) {
		this.tresource = tresource;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Set<Tresource> getTresources() {
		return tresources;
	}

	public void setTresources(Set<Tresource> tresources) {
		this.tresources = tresources;
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
		return "Tresource [id=" + id + ", tresourcetype=" + tresourcetype + ", tresource=" + tresource + ", name="
				+ name + ", remark=" + remark + ", seq=" + seq + ", url=" + url + ", icon=" + icon + ", tresources="
				+ tresources + ", create_time=" + create_time + ", create_user=" + create_user + ", update_time="
				+ update_time + ", update_user=" + update_user + "]";
	}


}
