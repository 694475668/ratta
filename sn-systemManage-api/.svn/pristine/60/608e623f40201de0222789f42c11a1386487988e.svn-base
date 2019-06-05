package com.ratta.suponote.model.system;

import java.util.HashSet;
import java.util.Set;

/**
 * @author page
 * 资源类型类
 * 2018-10-31
 */
public class Tresourcetype implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 资源类型ID
	 */
	private String id;
	/**
	 * 资源类型名称
	 */
	private String name;
	private Set<Tresource> tresources = new HashSet<Tresource>(0);

	public Tresourcetype() {
	}

	public Tresourcetype(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Tresourcetype(String id, String name, Set<Tresource> tresources) {
		this.id = id;
		this.name = name;
		this.tresources = tresources;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Tresource> getTresources() {
		return this.tresources;
	}

	public void setTresources(Set<Tresource> tresources) {
		this.tresources = tresources;
	}

	@Override
	public String toString() {
		return "Tresourcetype [id=" + id + ", name=" + name + ", tresources=" + tresources + "]";
	}

}
