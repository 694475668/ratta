package com.ratta.suponote.utils;

import java.text.MessageFormat;

/**
 * 消息类
 * @author page
 *
 */
public class Message {
    private static final String LANGUAGE_CN = "CN";
    private static final String LANGUAGE_EN = "US";
	private String strCn;
	private String strEn;
	
	
	private Message(String strCn,String strEn) {
		super();
		this.strCn = strCn;
		this.strEn = strEn;
	}

	/**
	 * 创建一个消息实例
	 * @param str_cn
	 * @param str_en
	 * @return
	 */
	public static synchronized Message createInstance(String strCn,String strEn){
		return new Message(strCn, strEn);
	}

	
	/**
	 * 根据lang 参数 获取message 消息
	 * @param message 消息实体
	 * @param lang 语言
	 * @param objects 参数
	 * @return 消息
	 */
	public  String getMessage(String lang,Object... objects){
		String  str=null;
		if(LANGUAGE_CN.equals(lang)){
			str=this.strCn;
		}else if(LANGUAGE_EN.equals(lang)){
			str=this.strEn;
		}else {
			str=this.strCn;
		}
		try {
			return MessageFormat.format(str, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public  String getMessage(String lang){
		Object[] objects =new Object[0];
		return getMessage( lang, objects);
	}

	@Override
	public String toString() {
		return "Message [strCn=" + strCn + ", strEn="
				+ strEn + "]";
	}
	
	
}
