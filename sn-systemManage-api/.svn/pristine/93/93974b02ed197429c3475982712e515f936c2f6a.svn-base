package com.ratta.suponote.business.result;

/**
 * @author page
 * 经销商管理返回枚举
 * 2018-10-31
 */
public enum DealersRseult {

	SUCCESS(0, "操作成功","executed"), 
	DICTIONARY_NULL(1,"经销商信息为空", "The dealer information is empty."), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"), 
	DATABASE_ERROR(3, "数据库操作异常", "database error"), 
	DICTIONARY_ID_NULL(4,"id为空", "input error id"), 
	SERVE_CODE_EXISTS(5, "业务码已经存在", "serve code already exists"),
	DEALERS_EQUIPMENT_EXISTS(6, "该经销商下有设备出库！", "The dealer has a binding device"),
	DEALERS_IS_EXISTS(7, "该经销商已经存在！", "The dealer already exists");

	private int value;
	private String desc;
	private String desc_en;

	private DealersRseult(int value, String desc,String desc_en) {
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
