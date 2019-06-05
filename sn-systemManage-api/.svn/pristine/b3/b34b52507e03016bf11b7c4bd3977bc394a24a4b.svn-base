package com.ratta.suponote.app.result;

/**
 * @author page
 * APP版本返回码
 * 2019-03-08
 */
public enum AppVersionResult {
	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败!", "request fail!"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin");

	private int value;
	private String desc;
	private String desc_en;

	private AppVersionResult(int value, String desc,String desc_en) {
		this.value = value;
		this.desc = desc;
		this.desc_en = desc_en;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}


	public String getDesc_en() {
		return desc_en;
	}

}
