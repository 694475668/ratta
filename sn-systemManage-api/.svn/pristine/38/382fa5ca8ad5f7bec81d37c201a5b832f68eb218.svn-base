package com.ratta.suponote.app.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * App修复点类
 * 2019-03-07
 */
@Model(table="t_app_fixpoint")
public class AppFixPoint implements Serializable{

	private static final long serialVersionUID = 1L;
	@PK
	private Long id;
	/** 应用ID */
	private int appId;
	/** 应用名称 */
	private String appName;
	/** 应用版本 */
	private String appVersion;
	/** 语言 */
	private String lan;
	/** 修复点 */
	private String fixPoint;
	/** 操作人 */
	private String opUser;
	/** 操作时间 */
	private Date opTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getFixPoint() {
		return fixPoint;
	}
	public void setFixPoint(String fixPoint) {
		this.fixPoint = fixPoint;
	}
	public String getOpUser() {
		return opUser;
	}
	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	@Override
	public String toString() {
		return "AppFixPoint [id=" + id + ", appId=" + appId + ", appName=" + appName + ", appVersion=" + appVersion
				+ ", lan=" + lan + ", fixPoint=" + fixPoint + ", opUser=" + opUser + ", opTime=" + opTime + "]";
	}
	
}
