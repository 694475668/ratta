package com.ratta.suponote.app.model;

import java.io.Serializable;
import java.util.Date;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * App灰度发布类
 * 2019-03-07
 */
@Model(table="t_app_gray_deploy")
public class AppGrayDeploy implements Serializable{

	private static final long serialVersionUID = 1L;
	@PK
	private int id;
	/** appID */
	private long appId;
	/** 用户ID */
	private long userId;
	/** 是否是历史 */
	private String isHistory;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "AppGrayDeploy [id=" + id + ", appId=" + appId + ", userId=" + userId + ", isHistory=" + isHistory
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
