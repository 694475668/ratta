package com.ratta.spnote.controller;

import com.ratta.spnote.util.Message;

/**
 * @author page 公共消息 2018-10-31
 */
public class CommonMessage {

	/**
	 * 请上传31M以下（含31M）的文件
	 */
	public static final Message COMMON_UPLOAD_FILE_SIZE_ERROR = Message.createInstance("请上传31M以下（含31M）的文件",
			"uploading file size limited to 31MB");
	/**
	 * 上传文件应该是jpg或png格式
	 */
	public static final Message COMMON_UPLOAD_FILE_FORMAT_JPG_OR_PNG_ERROR = Message.createInstance("上传文件应该是jpg或png格式",
			"supporting jpg/png only");
	/**
	 * 上传失败
	 */
	public static final Message COMMON_UPLOAD_ERROR = Message.createInstance("上传失败!", "upload failure");
	/**
	 * 上传成功
	 */
	public static final Message COMMON_UPLOAD_SUCCESS = Message.createInstance("上传成功!", "upload success");
}
