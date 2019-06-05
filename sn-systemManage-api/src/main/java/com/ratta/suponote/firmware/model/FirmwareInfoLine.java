package com.ratta.suponote.firmware.model;

import java.io.Serializable;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 固件信息子表
 * 2018-10-31
 */
@Model(table="t_fireware_info_line")
public class FirmwareInfoLine implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** PK */
	@PK
	private Long id;
	/**文件名称 */
	private String file_name;
	/** 固件版本 */
	private String version;
	/** 修改点 */
	private String modify_point;
	/** 影响范围 */
	private String impact_scope;
	/** 版本目的 */
	private String version_purpose;
	/** 升级范围*/ 
	private String updrage_scope;
	/** 文件地址*/ 
	private String fileUrl;
	/** 合入标志(Y：合入 ,N：不是合入) */
	private String merge_flag;
	/** 固件大版本ID*/
	private String firm_id;
	/** 打包时间 */
	private String packaging_time;
	/** 文件MD5 */
	private String md5;
	/** 文件类型*/
	private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getModify_point() {
		return modify_point;
	}
	public void setModify_point(String modify_point) {
		this.modify_point = modify_point;
	}
	public String getImpact_scope() {
		return impact_scope;
	}
	public void setImpact_scope(String impact_scope) {
		this.impact_scope = impact_scope;
	}
	public String getVersion_purpose() {
		return version_purpose;
	}
	public void setVersion_purpose(String version_purpose) {
		this.version_purpose = version_purpose;
	}
	public String getUpdrage_scope() {
		return updrage_scope;
	}
	public void setUpdrage_scope(String updrage_scope) {
		this.updrage_scope = updrage_scope;
	}
	public String getMerge_flag() {
		return merge_flag;
	}
	public void setMerge_flag(String merge_flag) {
		this.merge_flag = merge_flag;
	}
	public String getFirm_id() {
		return firm_id;
	}
	public void setFirm_id(String firm_id) {
		this.firm_id = firm_id;
	}
	public String getPackaging_time() {
		return packaging_time;
	}
	public void setPackaging_time(String packaging_time) {
		this.packaging_time = packaging_time;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "{id=" + id + ", file_name=" + file_name
				+ ", version=" + version + ", modify_point=" + modify_point + ", impact_scope=" + impact_scope
				+ ", version_purpose=" + version_purpose + ", updrage_scope=" + updrage_scope + ", fileUrl=" + fileUrl
				+ ", merge_flag=" + merge_flag + ", firm_id=" + firm_id + ", packaging_time=" + packaging_time
				+ ", md5=" + md5 + ", type=" + type + "}";
	}
	
}
