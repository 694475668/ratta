package com.ratta.suponote.model.pagemodel;

/**
 * @author page
 * 资源类型模型类
 * 2018-10-31
 */
public class ResourceType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 资源类型ID
	 */
	private String id;
	/**
	 * 资源类型名称
	 */
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ResourceType [id=" + id + ", name=" + name + "]";
	}

}
