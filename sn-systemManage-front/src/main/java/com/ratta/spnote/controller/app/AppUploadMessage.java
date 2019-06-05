package com.ratta.spnote.controller.app;

import com.ratta.spnote.util.Message;

public  class AppUploadMessage {
	/**
	 * 上传成功
	 */
	public static final Message APP_VER_UPLOAD_SUCCESS=Message.createInstance("上传成功","upload success");
	/**
	 * 上传失败
	 */
	public static final Message APP_VER_UPLOAD_FAIL=Message.createInstance("上传失败","upload failure");
	/**
	 * 版本已存在
	 */
	public static final Message APP_VERSION_EXIST=Message.createInstance("版本已存在","the app version is exist");
}
