package com.ratta.suponote.param.result;

/**
 * @author page
 * 错误码服务返回码
 * 2018-10-31
 */
public enum DictionaryResult {
	SUCCESS(0, "操作成功","executed"), 
	DICTIONARY_NULL(1,"数据字典信息为空", "input dictionary info"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"), 
	DATABASE_ERROR(3, "数据库操作异常", "database error"), 
	DICTIONARY_ID_NULL(4,"id为空", "input error id"), 
	SERVE_CODE_EXISTS(5, "业务码已经存在", "serve code already exists"),
	SERIAL_IS_SAME(6, "同一个业务码下不允许存在相同编码！","The business same code does not allow the same code!");

	private int value;
	private String desc;
	private String desc_en;

	private DictionaryResult(int value, String desc,String desc_en) {
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
