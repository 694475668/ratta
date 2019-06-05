package com.ratta.suponote.app.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * App版本类
 * 2019-03-07
 */
@Model(table="t_app_version")
public class AppVersion implements Serializable{

	private static final long serialVersionUID = 1L;
	@PK
	private int id;
	/** 应用名称 */
	private String appName;
	/** 应用版本 */
	private String appVersion;
	/** md5 */
	private String md5;
	/** 应用包名 */
	private String packageName;
	/** 更新标志。0:不强制；1：强制 */
	private String updateFlag;
	/** 地址 */
	private String url;
	/** 内部版本号 */
	private String versionNo;
	/** 发布标识 */
	private String deployFlag;
	/** 审核标识 */
	private String auditingFlag;
	/** app版本连接环境 */
	private String appEnvironment;
	/** 文件名称 */
	private String fileName;
	/** 操作人 */
	private String opUser;
	/** 操作时间 */
	private Date opTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getDeployFlag() {
		return deployFlag;
	}
	public void setDeployFlag(String deployFlag) {
		this.deployFlag = deployFlag;
	}
	
	public String getAuditingFlag() {
		return auditingFlag;
	}
	public void setAuditingFlag(String auditingFlag) {
		this.auditingFlag = auditingFlag;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getAppEnvironment() {
		return appEnvironment;
	}
	public void setAppEnvironment(String appEnvironment) {
		this.appEnvironment = appEnvironment;
	}
	@Override
	public String toString() {
		return "AppVersion [id=" + id + ", appName=" + appName + ", appVersion=" + appVersion + ", md5=" + md5
				+ ", packageName=" + packageName + ", updateFlag=" + updateFlag + ", url=" + url + ", versionNo="
				+ versionNo + ", deployFlag=" + deployFlag + ", auditingFlag=" + auditingFlag + ", appEnvironment="
				+ appEnvironment + ", fileName=" + fileName + ", opUser=" + opUser + ", opTime=" + opTime + "]";
	}
	
}
