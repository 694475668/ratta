package com.ratta.suponotes.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
/**
 * 
		* <p>Title: MD5Util</p>
		* <p>Description: MD5加密工具类</p>
 */
public class MD5Util {
	/**
	 * 
			* <p>md5</p>
			* <p>对输入的字符串进行md5加密</p>
			* @param str 明文字符串
			* @return md5加密后的字符串
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
			* <p>sign</p>
			* <p>签名</p>
			* @param maps 集合
			* @return 签名
	 */
	public static String sign(Map<String, Object> maps) {
		Iterator<String> it=  maps.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while(it.hasNext()){
			String key = it.next();
			Object value = maps.get(key);
			sb.append(key).append(value);
		}
		return md5(sb.toString());
	}
	

}
