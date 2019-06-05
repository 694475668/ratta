package com.ratta.suponote.model.pagemodel;

/**
 * @author page
 * 设备解绑实体类
 * 2018-10-31
 */
public class EquipmentUnllinkPush{
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 业务码
	 */
	private String busCode;
	/**
	 * 时间戳
	 */
	private long timestamp;
	/**
	 * 现时标志
	 */
	private String nonce;
	/**
	 * 设备号
	 */
	private String equipmentNo;
	/**
	 * 云盘账号
	 */
	private String account;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEquipmentNo() {
		return equipmentNo;
	}
	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "EquipmentUnlbundPush [language=" + language + ", busCode=" + busCode + ", timestamp=" + timestamp
				+ ", nonce=" + nonce + ", equipmentNo=" + equipmentNo + ", account=" + account + "]";
	}

}
