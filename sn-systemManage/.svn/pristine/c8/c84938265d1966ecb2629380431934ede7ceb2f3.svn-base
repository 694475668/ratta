package com.ratta.suponote.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author page
 * Config配置文件工具类
 * 2018-10-31
 */
public class ConfigUtil {
	
	private final static Properties  PROPERTIES = new Properties();
	private final static String OS_NAME = "os.name";
	private final static String OS_NAME_START = "Windows";
	/**
	 * 日志备份目录
	 */
	public static final String NFS_DIR_LOGBACK ="nfs_dir_logback";
	private ConfigUtil(){
		
	}
	static{
		try {
			if(System.getProperty(OS_NAME).startsWith(OS_NAME_START)){
				InputStream is =  ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
				PROPERTIES.load(is); 
			}else{
				String path = System.getProperty("user.dir") + File.separator + "etc" + File.separator + "config.properties";
				InputStream is = new FileInputStream(new File(path));
				PROPERTIES.load(is);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	
	public static final String getSessionInfoName() {
		return "sessionInfo";
	}

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		if(PROPERTIES == null){
			return null;
		}
		return PROPERTIES.getProperty(key).trim();
	}
}

	