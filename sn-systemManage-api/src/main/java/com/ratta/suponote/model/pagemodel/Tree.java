package com.ratta.suponote.model.pagemodel;

import java.util.List;

/**
 * @author page
 * 页面资源树模型
 * 2018-10-31
 */
public class Tree implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 资源ID
	 */
	private String id;
	/**
	 * 资源名称
	 */
	private String text;
	/**
	 * 资源状态
	 */
	private String state = "open";
	private boolean checked = false;
	private Object attributes;
	/**
	 * 下级资源
	 */
	private List<Tree> children;
	/**
	 * 资源图标
	 */
	private String iconCls;
	/**
	 * 上级资源ID
	 */
	private String pid;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", text=" + text + ", state=" + state
				+ ", checked=" + checked + ", attributes=" + attributes
				+ ", children=" + children + ", iconCls=" + iconCls + ", pid="
				+ pid + "]";
	}
}
