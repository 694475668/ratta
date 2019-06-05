package com.ratta.spnote.controller;

import com.ratta.spnote.util.Message;

/**
 * @author page 验证码消息 2018-10-31
 */
public class CaptchaMessage {

	/**
	 * 验证码获取失败
	 */
	public static final Message CAPTCHA_GENERATE_VERIFICATION_CODE_SUCCESS = Message.createInstance("验证码获取失败",
			"failed to retrieve correct verification code ");
}
