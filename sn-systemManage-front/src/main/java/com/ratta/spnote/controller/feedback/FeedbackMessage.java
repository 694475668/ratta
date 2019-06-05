package com.ratta.spnote.controller.feedback;

import com.ratta.spnote.util.Message;

/**
 * @author page
 * 反馈信息
 * 2018-10-31
 */
public class FeedbackMessage {
	/**
	 * 导出失败
	 */
	public static final Message EXPORT_ERROR=Message.createInstance("导出失败","Export failure");  
	/**
	 * 导出成功
	 */
	public static final Message EXPORT_SUCCESS=Message.createInstance("导出成功","Export success"); 
}
