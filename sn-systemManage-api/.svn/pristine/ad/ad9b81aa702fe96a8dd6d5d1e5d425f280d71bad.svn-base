package com.ratta.suponote.stock.result;

/**
 * @author page
 * 库存信息返回枚举
 * 2018-10-31
 */
public enum StockRseult {

	SUCCESS(0, "操作成功","executed"), 
	REQUEST_FAIL(1,"请求失败！", "Request fail !"), 
	SESSIONINFO_NULL(2, "session超时,请重新登录","session timeout, please relogin"),
	EXCHANGE_STOCK_NULL(3, "换货设备不存在！","Replacement equipment does not exist"),
	EXCHANGE_STOCK_ISNOT_NORMAL(4, "换货设备正处于维修中或已报废！","Replacement equipment is under repair or scrapped!"),
	EXCHANGE_STOCK_IS_USE(5, "换货设备已处于使用中，请仔细核对！","The replacement equipment is in use, please check it carefully!"),
    EXCHANGE_IS_CONSISTENT(6, "换货设备号和被换货设备号不能一致！","The replacement equipment number and the replacement equipment number cannot be consistent!"),
    EQUIPMENT_MODEL_INCONSISTENT(7, "设备型号不一致！","The equipment models are inconsistent!"),
	RETURN_LIMIT(8, "仅出库设备可以退换货！","Only out of stock equipment can be returned and replaced!");

	private int value;
	private String desc;
	private String desc_en;

	private StockRseult(int value, String desc,String desc_en) {
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
