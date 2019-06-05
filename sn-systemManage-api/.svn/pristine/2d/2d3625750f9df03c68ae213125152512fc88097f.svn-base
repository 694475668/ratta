package com.ratta.suponote.param.result;

/**
 * @author page
 * 参数详细返回枚举
 * 2018-10-31
 */
public enum ReferenceResult {

	SUCCESS(0,"操作成功", "executed"), 
	REFERENCE_NULL(1,"参数信息为空", "input parameter details"),
	DATABASE_ERROR(2,"数据库操作异常", "database error"),
	SESSIONINFO_NULL(3,"session超时,请重新登录", "session timeout, please relogin"),
	REFERENCE_ID_NULL(4,"参数明细id为空", "the  ID is null"),
	REFERENCE_EXISTS(5,"参数已存在！", "Parameter already exists")
	;
	
	private int value;
	private String desc;
	private String desc_en;

	private ReferenceResult(int value, String desc,String desc_en) {
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


	