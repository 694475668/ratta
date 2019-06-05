package com.ratta.suponote.stock.result;

/**
 * @author page
 * 经销商管理返回枚举
 * 2018-10-31
 */
public enum InOutStockRseult {

	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败！", "Request fail !"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
	SN_EXISTS(3,"存在已经入库的设备序列号", "serial number already used"), 
	STOCK_NULL(4,"设备序列号不存在", "input serial number not found"),
	SN_HAS_OUTSOTCK_ERROR(5,"存在已经出库的设备,不可以出库", "unbale to activate, device is already activated"),
	INOUT_IS_OUTSTOCK_ERROR(6,"存在已经出库的设备,不能撤销", "unable to revoke, device is already activated"),
	NO_MATCH(7,"批次号和设备型号或固件版本号不匹配！", "Batch number and device model or firmware version number do not match!"),
	NO_IMPORT_DATA(8,"没有需要导入的入库记录！", "There is no entry record to import!"),
	EQUIPMENT_NOT_NORMAL(9,"出库设备中存在维修中或已报废的设备！", "Out of storage equipment in the presence of maintenance or scrapped equipment!"),
	ACTIVATED_DEVICE_NOT_REVOKE(10,"出库单存在已经激活的设备，不可以撤销！", "There is an activated device in the outbound order. It cannot be revoked!"),
	RETURN_DEVICE_NOT_REVOKE(11,"出库单存在有退换货记录的设备，不可以撤销！", "The equipment with the record of return and exchange of goods. It cannot be revoked!"),
	IS_LEGAL_EQUIPMENT(12,"存在不符合设备型号的设备号", "There is an equipment number that does not conform to the equipment model!");

	private int value;
	private String desc;
	private String desc_en;

	private InOutStockRseult(int value, String desc,String desc_en) {
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
