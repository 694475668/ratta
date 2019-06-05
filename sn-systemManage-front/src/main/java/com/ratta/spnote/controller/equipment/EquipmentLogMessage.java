package com.ratta.spnote.controller.equipment;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 设备日志信息
 * 2018-10-31
 */
public class EquipmentLogMessage {
	/**
	 * 请求成功
	 */
	public static final Message SUCCESS=Message.createInstance("请求成功！", "Success!");
	/**
	 *请求失败
	 */
	public static final Message FAIL=Message.createInstance("请求失败！", "Fail!");
	/**
	 *备注不可为空！
	 */
	public static final Message REMARK_IS_NULL=Message.createInstance("备注不可为空！", "The remark is not null!");
	/**
	 *备注超过最大字符数！
	 */
	public static final Message LENGTH_LIMIT=Message.createInstance("超过最大字符数(1000)!", "MaxLength(1000)!");

}
