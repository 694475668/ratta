package com.ratta.suponote.business.result;

/**
 * @author page
 * 设备型号管理返回枚举
 * 2018-10-31
 */
public enum EquipTypeRseult {

	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败！", "request fail!"),
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
	EQUIPTYPE_EQUIPMENT_BIND(3, "该设备型号下有绑定设备！","The device model has a binding device！"),
	TYPE_IS_EXISTS(4, "该设备型号已经存在！", "The device model already exists");

	private int value;
	private String desc;
	private String desc_en;

	private EquipTypeRseult(int value, String desc,String desc_en) {
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
