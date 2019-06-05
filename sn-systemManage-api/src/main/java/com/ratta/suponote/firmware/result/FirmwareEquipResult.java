package com.ratta.suponote.firmware.result;

/**
 * @author page
 * 固件修复点返回码
 * 2018-10-31
 */
public enum FirmwareEquipResult {
	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败", "request fail!"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
	FIXPOINT_IS_EXISTS(3, "该固件修复点已经存在！","The firmware fix point already exists!"),
	FIXPOINT_IS_NULL(4, "修复点不允许为空！","The firmware fix point is null!"),
	FIRMWAREINFO_NULL(5, "大固件信息为空！","The big firmware information is empty."),
	EQUIPMENTINFO_NULL(6, "设备型号为空！","The equipment model is empty!");

	private int value;
	private String desc;
	private String desc_en;

	private FirmwareEquipResult(int value, String desc,String desc_en) {
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
