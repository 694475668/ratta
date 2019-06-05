package com.ratta.suponote.firmware.result;

/**
 * @author page
 * 固件版本发布任务返回码
 * 2018-10-31
 */
public enum FirmwareTaskResult {
	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败!", "request fail!"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
    ONLY_DELETE(3, "仅能删除未到执行时间的的固件发布任务！","Can only delete firmware release tasks that are not in execution time！"),
    NULL_FIX_POINT(4, "请为固件添加修复点！","Add a fix point to the firmware！");

	private int value;
	private String desc;
	private String desc_en;

	private FirmwareTaskResult(int value, String desc,String desc_en) {
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
