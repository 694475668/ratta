package com.ratta.spnote.controller.stock;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 库存管理信息
 * 2018-10-31
 */
public class StockMessage {
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
	 * 第(j)行数据出现错误,批次号不能为空
	 */
	public static final Message BATCH_NO_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,批次号不能为空","Error ({0}) in the row data, the batch no. cannot be empty.");
	/**
	 * 第(j)行数据出现错误,设备号不能为空
	 */
	public static final Message EQUIOMENT_NO_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,设备号不能为空","The ({0}) row data is wrong and the equipment no. cannot be empty.");
	/**
	 * 第(j)行数据出现错误,固件版本号不能为空
	 */
	public static final Message FIRMWARE_VERSION_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,固件版本不能为空","The ({0}) line data is wrong and the firmware version cannot be empty.");
	/**
	 * 设备型号为空
	 */
	public static final Message EQUIPMENT_TYPE_NULL=Message.createInstance("设备型号为空！","Equipment type is null !");  
	/**
	 * 上传文件应该是excel格式
	 */
	public static final Message FILE_FORMAT_EXCEL_ERROR=Message.createInstance("上传文件应该是excel格式","The upload file should be in excel format.");  
	/**
	 * 导入失败,数据有误!
	 */
	public static final Message DEALERS_IMPORT_ERROR=Message.createInstance("导入失败,数据有误!","Import failed, incorrect data!");  
}
