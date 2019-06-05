package com.ratta.suponote.model.pagemodel;

import java.util.List;
import java.util.Locale;

/**
 * @author page
 * 信息模型
 * 2018-10-31
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 用户登录名
	 */
	private String name;
	/**
	 * 登陆用户类型(1租户,2代理商,3商户)
	 */
	private String user_type;
	/**
	 * 用户登录IP
	 */
	private String ip;
	/**
	 * 用户可访问的资源地址列表
	 */
	private List<String> resourceList;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 语言
	 */
	private  Locale locale;
	/**
	 * 文件
	 */
	private String url;
	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SessionInfo [id=" + id + ", roleId=" + roleId + ", name="
				+ name + ", user_type=" + user_type + ", ip=" + ip
				+ ", resourceList=" + resourceList + ", username=" + username
				+ ", locale=" + locale + ", url=" + url + "]";
	}



	
	






	
}
