package com.ratta.suponote.firmware.result;

/**
 * @author page
 * 固件版本返回码
 * 2018-10-31
 */
public enum FirmwareInfoResult {
	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败!", "request fail!"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
    ONLY_DELETE(3, "仅能删除未审核的固件版本！","You can only delete unexamined firmware versions！"),
    FIRMWAREINFO_NULL(4, "大固件信息为空！","The big firmware information is empty."),
    EQUIPMENTINFO_NULL(5, "设备型号为空！","The equipment model is empty!");

	private int value;
	private String desc;
	private String desc_en;

	private FirmwareInfoResult(int value, String desc,String desc_en) {
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
