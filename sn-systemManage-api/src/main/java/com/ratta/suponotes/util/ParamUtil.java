package com.ratta.suponotes.util;
/**
 * @author page
 * 传参数工具类
 * 2018-10-31
 */
public  class ParamUtil {

	/**
	 * 
			* <p>parseStr2Int</p>
			* <p>将字符串转换成int值</p>
			* @param paramter 参数
			* @param defaultValue 默认值
			* @return 如果为空默认返回值为defaultValue
	 */
	public static int parseStr2Int(Object paramter,int defaultValue){
	
			try {
				if(paramter!=null  && !"".equals(String.valueOf(paramter).trim())){
					return Integer.parseInt(String.valueOf(paramter).trim());
				}
				return defaultValue;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		return 0;
	}
	
	/**
	 * 
			* <p>parseStr2Int</p>
			* <p>将字符串转换成integer</p>
			* @param paramter 参数
			* @return  如果为空 默认返回值为-1 
	 */
	public static int parseStr2Int(Object paramter){
		return parseStr2Int(paramter, -1);
	}
}

	