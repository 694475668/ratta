package com.ratta.suponote.usersn.result;

/**
 * @author page
 * 错误码服务返回码
 * 2018-10-31
 */
public enum SnUserResult {
	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败！", "request fail!"),
	STATUS_SAME(2, "状态相同！","The same status!"),
	SESSIONINFO_NULL(3, "session超时,请重新登录","session timeout, please relogin"),
	TELPHONE_EXITS(4, "该手机号已被注册","The phone number has been registered"),
	EMAIL_EXITS(5, "该邮箱已被注册","The email has been registered"),
	TELPHONE_SAME(6, "该手机号和原手机号相同","The phone number is same"),
	EMAIL_SAME(7, "该邮箱和原邮箱相同","The email is same");
	private int value;
	private String desc;
	private String desc_en;

	private SnUserResult(int value, String desc,String desc_en) {
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
