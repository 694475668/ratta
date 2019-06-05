package com.ratta.suponote.firmware.result;

/**
 * @author page
 * 固件修复点返回码
 * 2018-10-31
 */
public enum FirmwareFixPointResult {
	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败!", "request fail!"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
	FIXPOINT_IS_EXISTS(3, "该固件修复点已经存在！","The firmware fix point already exists!"),
	FIXPOINT_IS_NULL(4, "固件修复点不能为空！","The firmware fix point is not null!"),
	FIXPOINT_LENGTH_LIMIT(5, "超过最大字符数(8000)！","MaxLength(8000)!");

	private int value;
	private String desc;
	private String desc_en;

	private FirmwareFixPointResult(int value, String desc,String desc_en) {
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
