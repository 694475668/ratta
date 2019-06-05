package com.ratta.spnote.controller.business;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 经销商消息
 * 2018-10-31
 */
public class DealersMessage {
	/**
	 * 数据查询失败
	 */
	public static final Message DEVICE_INOUT_QUERY_DATA_FAIL_ERROR=Message.createInstance("数据查询失败", "query failure");  
	/**
	 * 上传文件应该是excel格式
	 */
	public static final Message DEALERS_FILE_FORMAT_EXCEL_ERROR=Message.createInstance("上传文件应该是excel格式","The upload file should be in excel format.");  
	
	/**
	 * 第(j)行数据出现错误,经销商名称不能为空
	 */
	public static final Message DEALERS_DEALERSNAME_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,经销商名称不能为空","Error ({0}) in the row data, the dealer name cannot be empty.");
	/**
	 * 第(j)行数据出现错误,经销商类别不能为空
	 */
	public static final Message DEALERS_DEALERSTYPE_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,经销商类别不能为空","Error ({0}) in the row data, the dealer type cannot be empty.");
	/**
	 * 第(j)行数据出现错误,联系人不能为空
	 */
	public static final Message DEALERS_CONTACT_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,联系人不能为空","The ({0}) row data is wrong and the contact cannot be empty.");
	/**
	 * 第(j)行数据出现错误,手机号不能为空
	 */
	public static final Message DEALERS_PHONE_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,手机号不能为空","The ({0}) line data is wrong and the phone number cannot be empty.");
	/**
	 * 第(j)行数据出现错误,手机号不合法
	 */
	public static final Message DEALERS_PHONE_FORMAT_ERROR=Message.createInstance("第({0})行数据出现错误,手机号不合法","The ({0}) line data is wrong and the phone number are illegal");
	/**
	 * 第(j)行数据出现错误,地址不能为空
	 */
	public static final Message DEALERS_ADDRESS_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,地址不能为空","The ({0}) line data is wrong and the address cannot be empty.");
	/**
	 * 第(j)行数据出现错误,申请时间不能为空
	 */
	public static final Message DEALERS_APPLICATIONTIME_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,申请时间不能为空","The ({0}) line data is wrong and the application time cannot be empty.");
	/**
	 * 第(j)行数据出现错误,业务员不能为空
	 */
	public static final Message DEALERS_SALESMAN_NULL_ERROR=Message.createInstance("第({0})行数据出现错误,业务员不能为空","The ({0}) line data is wrong and the salesman cannot be empty.");
	
	/**
	 * 导入失败,数据有误!
	 */
	public static final Message DEALERS_IMPORT_ERROR=Message.createInstance("导入失败,数据有误!","Import failed, incorrect data!");  
	/**
	 * 没有要导出的数据
	 */
	public static final Message DEALERS_INOUT_NO_DATA_TO_EXPORT_ERROR=Message.createInstance("没有要导出的数据","There is no data to export.");  
	/**
	 * 导出失败
	 */
	public static final Message DEALERS_INOUT_EXPORT_ERROR=Message.createInstance("导出失败","Export failure");  
	/**
	 * 导出成功
	 */
	public static final Message DEALERS_INOUT_EXPORT_SUCCESS=Message.createInstance("导出成功","Export success"); 
}
