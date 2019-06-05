package com.ratta.suponote.firmware.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author page
 * 固件重组压缩包类
 * 2018-10-31
 */
public class FirmwareZip implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id */
	private Integer id;
	/** 固件版本 */
    private String version;
    /** 是否有App标识 */
    private String appFlag;
    /** 是否有系统标识 */
    private String systemFlag;
    /** 是否有App库标识 */
    private String libAppFlag;
    /** 是否有系统库标识 */
    private String libSystemFlag;
    /** 地址 */
    private String url;
    /** MD5 */
    private String md5;
    /** 操作者 */
    private String opUser;
    /** 压缩密码 */
    private String secret;
    /** 操作时间 */
    private Date opTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag == null ? null : appFlag.trim();
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag == null ? null : systemFlag.trim();
    }

    public String getLibAppFlag() {
        return libAppFlag;
    }

    public void setLibAppFlag(String libAppFlag) {
        this.libAppFlag = libAppFlag == null ? null : libAppFlag.trim();
    }

    public String getLibSystemFlag() {
        return libSystemFlag;
    }

    public void setLibSystemFlag(String libSystemFlag) {
        this.libSystemFlag = libSystemFlag == null ? null : libSystemFlag.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser == null ? null : opUser.trim();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

	@Override
	public String toString() {
		return "FirmwareZip [id=" + id + ", version=" + version + ", appFlag=" + appFlag + ", systemFlag=" + systemFlag
				+ ", libAppFlag=" + libAppFlag + ", libSystemFlag=" + libSystemFlag + ", url=" + url + ", md5=" + md5
				+ ", opUser=" + opUser + ", secret=" + secret + ", opTime=" + opTime + "]";
	}

}