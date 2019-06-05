package com.ratta.suponotes.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author page
 * 返回枚举工具类
 * 2018-10-31
 */
public class ResultEnumUtil {

	/**
	 * 
			* <p>getDesc</p>
			* <p>使用服务器默认语言获取枚举里面的描述信息</p>
			* @param t 枚举实体
			* @param <T> 枚举实体
			* @return 枚举描述信息
	 */
	public static<T> String getDesc(T t){
		return getDesc(t, Locale.SIMPLIFIED_CHINESE);
	}
	/**
	 * 
			* <p>getDesc</p>
			* <p>根据指定的语言获取枚举类型的描述信息</p>
			* @param t 枚举实体
			* @param <T> 枚举实体
			* @param locale 语言
			* @return 枚举描述信息
	 */
	public static<T> String getDesc(T t,Locale locale){
		String country = null ;
			if(locale !=null){
				if("US".equals(locale.getCountry())){
					country="_en";
				}else if("CN".equals(locale.getCountry())){
					country="";
				}
			}else{
				country="";
			}
			
			try {
				Class clazz=  t.getClass();
				Field[] fields= clazz.getDeclaredFields();
				for (Field field : fields) {
					if(("desc"+country).equals(field.getName())){
							Method method=clazz.getMethod("get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1));
							return String.valueOf(method.invoke(t));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	
}

	