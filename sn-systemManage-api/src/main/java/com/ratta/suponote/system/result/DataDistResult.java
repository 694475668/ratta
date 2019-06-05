package com.ratta.suponote.system.result;

/**
 * @author page
 * 数据字典操作枚举
 * 2018-10-31
 */
public enum DataDistResult {
	SUCCESS(0,"操作成功", "executed"), 
	SESSIONINFO_NULL(2,"session超时,请重新登录", "session timeout, please relogin"), 
	TABLE_NULL(3,"表信息为空","input table details"),
	TABLE_ID_NULL(4,"表信息id为空","input table ID"), 
	COLUMN_NULL(5,"字段信息为空","input data"), 
	COLUMN_ID_NULL(6,"表字段id为空","input data ID")
	;
	private int value;
	private String desc;
	private String desc_en;
	private DataDistResult(int value,String desc,String desc_en){
		this.value = value;
		this.desc = desc;
		this.desc_en=desc_en;
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

	