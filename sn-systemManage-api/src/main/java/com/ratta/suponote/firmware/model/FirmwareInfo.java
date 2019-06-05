package com.ratta.suponote.firmware.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 固件信息表
 * 2018-10-31
 */
@Model(table="t_fireware_info")
public class FirmwareInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	private Long id;
	/** 区域 */
	private String area;
	/** 定制 */
	private String custom;
	/** 配置 */
	private String configuration;
	/** 固件版本 */
	private String version;
	/** 状态 */
	private String status;
	/** 修改点 */
	private String modifyPoint;
	/** 影响范围 */
	private String impactScope;
	/** 版本目的 */
	private String versionPurpose;
	/** 升级范围*/
	private String updrageScope;
	/** 打包时间 */
	private String packagingTime; 
	/** 审核人员 */
	private String audit_user;
	/** 审核时间*/
	private Date audit_time;
	/** 审核标志 */
	private String audit_flag;
	/** 审核信息*/
	private String audit_info;
	/** 操作员 */
	private String op_user;
	/** 上传时间*/
	private Date op_time;
	private List <FirmwareInfoLine> packageList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModifyPoint() {
		return modifyPoint;
	}
	public void setModifyPoint(String modifyPoint) {
		this.modifyPoint = modifyPoint;
	}
	public String getImpactScope() {
		return impactScope;
	}
	public void setImpactScope(String impactScope) {
		this.impactScope = impactScope;
	}
	
	public String getAudit_user() {
		return audit_user;
	}
	public void setAudit_user(String audit_user) {
		this.audit_user = audit_user;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public String getAudit_flag() {
		return audit_flag;
	}
	public void setAudit_flag(String audit_flag) {
		this.audit_flag = audit_flag;
	}
	public String getAudit_info() {
		return audit_info;
	}
	public void setAudit_info(String audit_info) {
		this.audit_info = audit_info;
	}
	public String getVersionPurpose() {
		return versionPurpose;
	}
	public void setVersionPurpose(String versionPurpose) {
		this.versionPurpose = versionPurpose;
	}
	public String getUpdrageScope() {
		return updrageScope;
	}
	public void setUpdrageScope(String updrageScope) {
		this.updrageScope = updrageScope;
	}
	public String getPackagingTime() {
		return packagingTime;
	}
	public void setPackagingTime(String packagingTime) {
		this.packagingTime = packagingTime;
	}
	public String getOp_user() {
		return op_user;
	}
	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	public List<FirmwareInfoLine> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<FirmwareInfoLine> packageList) {
		this.packageList = packageList;
	}
	@Override
	public String toString() {
		return "FirmwareInfo [id=" + id + ", area=" + area + ", custom="
				+ custom + ", configuration=" + configuration
				+ ", version=" + version + ", status=" + status
				+ ", modifyPoint=" + modifyPoint + ", impactScope="
				+ impactScope + ", versionPurpose=" + versionPurpose
				+ ", updrageScope=" + updrageScope + ", packagingTime="
				+ packagingTime + ", audit_user=" + audit_user
				+ ", audit_time=" + audit_time + ", audit_flag=" + audit_flag
				+ ", audit_info=" + audit_info + ", op_user=" + op_user
				+ ", op_time=" + op_time + ", packageList=" + packageList + "]";
	}
}
