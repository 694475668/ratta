package com.ratta.spnote.controller.stock;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 出入库管理信息
 * 2018-10-31
 */
public class InOutStockMessage {
	/**
	 * 没有要导出的数据
	 */
	public static final Message Stock_DATA_NO_EXPORT_ERROR=Message.createInstance("没有要导出的数据","There is no data to export.");  
	/**
	 * 导出失败
	 */
	public static final Message Stock_DATA_EXPORT_ERROR=Message.createInstance("导出失败","Export failure");  
	/**
	 * 导出成功
	 */
	public static final Message Stock_DATA_EXPORT_SUCCESS=Message.createInstance("导出成功","Export success"); 
	/**
	 * 请求失败
	 */
	public static final Message REQUEST_FAIL=Message.createInstance("请求失败！","Request fail!"); 
	/**
	 * 请求失败
	 */
	public static final Message STOCK_NULL=Message.createInstance("库存数量为0！","None stock!"); 
}
