package com.ratta.suponote.param.result;

/**
 * @author page
 * 错误码服务返回码
 * 2018-10-31
 */
public enum ErrorCodeResult {
	SUCCESS(0, "操作成功","executed"), 
	ERRORCODE_NULL(1,"错误码信息为空", "input errror info"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"), 
	DATABASE_ERROR(3, "数据库操作异常", "database error"), 
	ERRORCODE_CODE_NULL(4,"错误码code为空", "input error code"), 
	ERRORCODE_CODE_EXISTS(5, "错误码已经存在", "error code already exists");

	private int value;
	private String desc;
	private String desc_en;

	private ErrorCodeResult(int value, String desc,String desc_en) {
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
