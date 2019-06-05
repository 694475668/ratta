package com.ratta.suponotes.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author page
 * 基础工具类
 * 2018-10-31
 */
public  class BaseUtil {
	/**
	 * 
			* <p>map2String</p>
			* <p>将map转换成字符串形式</p>
			* @param map 要转换的map集合
			* @return 转换后的字符串
	 */
	public static String map2String(Map<String, Object> map){
		if(map!=null){
			Set<String> set= map.keySet();
			StringBuffer sb = new StringBuffer();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String name = it.next();
				sb.append(name+":"+map.get(name)).append(",");
			}
			if(sb.length()>0){
				return sb.substring(0, sb.length()-1);
			}
		}
		return "";
	}
	/**
	 * 
			* <p>orclFuzzyQueryParam</p>
			* <p>组装oracle模糊查询参数</p>
			* @param obj 要转换的参数对象
			* @return 组装好了的模糊查询对象
	 */
	public static Object orclFuzzyQueryParam(Object obj){
		if(obj==null || "".equals(String.valueOf(obj).trim())){
			return null;
		}
		return "%%"+ String.valueOf(obj).trim()+"%%";
	}
	/**
	 * 
			* <p>fillString</p>
			* <p>填充字符串</p>
			* @param str 原始字符串
			* @param fill 以fill字符补充
			* @param len 固定长度
			* @param isEnd 是否在str末尾追加
			* @return 返回补充好的字符串
	 */
	public static String fillString(String str, char fill, int len,boolean isEnd) {
		if (str == null){
			str = "";
		}
		int fillLen = len - str.getBytes().length;
		if (len <= 0) {
			return str;
		}
		for (int i = 0; i < fillLen; i++) {
			if (isEnd) {
				str += fill;
			} else {
				str = fill + str;
			}
		}
		return str;
	}
	/**
	 * 
			* <p>getCurrentDateTime</p>
			* <p>获取当前时间</p>
			* @param string 格式化样式
			* @return 当前时间
	 */
	public static String getCurrentDateTime(String string){
		SimpleDateFormat sdf = new SimpleDateFormat(string);
		return sdf.format(new Date());
	}
	
	/**
	 * 
			* <p>map2string</p>
			* <p>map里面的键值对按照append 拼接</p>
			* @param map 集合
			* @param append 连接字符串
			* @return 返回字符串
	 */
	public static String map2string(Map<String, Object> map,String append){
		if(map==null){
			return null;
		}
		StringBuffer sb  = new StringBuffer();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object obj=map.get(key);
			if(obj!=null){
				sb.append(key+"="+obj).append(append);
			}
		}
		if(sb.length()>0){
			sb.delete(sb.length()-append.length(), sb.length());
		}
		return sb.toString();
	}
	/**
	 * 
			* <p>map2string</p>
			* <p>map里面的键值对按照默认字符串拼接</p>
			* @param map 集合
			* @return 返回字符串
	 */
	public static String map2string(Map<String, Object> map) {
		return map2string(map, "&");
	}
	
	/**
	 * 
			* <p>calcDistance</p>
			* <p>计算地球上两点距离</p>
			* @param lat1  位置1纬度
			* @param lng1  位置1经度
			* @param lat2  位置2纬度
			* @param lng2  位置2经度
			* @return 距离
	 */
	public static Double calcDistance(Double lat1, Double lng1, Double lat2, Double lng2){
		Double radLat1 = rad(lat1);
		Double radLat2 = rad(lat2);
		Double a = radLat1 - radLat2;
		Double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(
					Math.sqrt(
							Math.pow(Math.sin(a / 2), 2)
							+ Math.cos(radLat1) * Math.cos(radLat2)
							* Math.pow(Math.sin(b / 2), 2)
					)
				);
		s = s * 6378.137;
		s = Math.round(s * 100000) / 100000;
		return s;
	}
	
	private static Double rad(Double num){
		return num*Math.PI/180.0;
	}
	
}

	