package com.ratta.suponote.feedback.result;

/**
 * @author page
 * 经销商管理返回枚举
 * 2018-10-31
 */
public enum FeedbackProblemTypeRseult {

	SUCCESS(0, "操作成功","executed"), 
	FEEDBACKPROBLEMTYPE_NULL(1,"反馈问题类型信息为空", "The feedback problem type information is empty."), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"), 
	DATABASE_ERROR(3, "数据库操作异常", "database error"), 
	TYPEID_NULL(4,"id为空", "input error id"), 
	TYPE_ID_EXISTS(5, "该类型编号已经存在", "The type number already exists");

	private int value;
	private String desc;
	private String desc_en;

	private FeedbackProblemTypeRseult(int value, String desc,String desc_en) {
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
