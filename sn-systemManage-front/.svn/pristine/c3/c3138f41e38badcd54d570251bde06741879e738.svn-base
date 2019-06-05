package com.ratta.spnote.util;

import java.text.MessageFormat;
import java.util.Locale;

import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page 消息类 2018-10-31
 */
public class Message {

	private String str_cn;
	private String str_en;

	private Message(String str_cn, String str_en) {
		super();
		this.str_cn = str_cn;
		this.str_en = str_en;
	}

	/**
	 * 创建一个消息实例
	 * 
	 * @param str_cn
	 * @param str_en
	 * @return
	 */
	public static synchronized Message createInstance(String str_cn, String str_en) {
		return new Message(str_cn, str_en);
	}

	/**
	 * 根据lang 参数 获取message 消息
	 * 
	 * @param message 消息实体
	 * @param lang    语言
	 * @param objects 参数
	 * @return 消息
	 */
	public String getMessage(String lang, Object... objects) {
		String str = null;
		if ("CN".equals(lang)) {
			str = this.str_cn;
		} else if ("US".equals(lang)) {
			str = this.str_en;
		} else {
			str = this.str_cn;
		}
		try {
			return MessageFormat.format(str, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据默认本地语言参数 获取message 消息
	 * 
	 * @param message 消息实体
	 * @param objects 参数
	 * @return 消息
	 */
	public String getMessage(Object... objects) {
		SessionInfo sessionInfo = HttpReqRspUtil.getSessionInfo();
		String lang = sessionInfo == null ? Locale.SIMPLIFIED_CHINESE.getCountry()
				: sessionInfo.getLocale().getCountry();
		return getMessage(lang, objects);
	}

	public String getMessage(String lang) {
		Object[] objects = new Object[0];
		return getMessage(lang, objects);
	}

	public String getMessage() {
		SessionInfo sessionInfo = HttpReqRspUtil.getSessionInfo();
		String lang = sessionInfo == null ? Locale.SIMPLIFIED_CHINESE.getCountry()
				: sessionInfo.getLocale().getCountry();
		return getMessage(lang);
	}

	@Override
	public String toString() {
		return "Message [str_cn=" + str_cn + ", str_en=" + str_en + "]";
	}

}
