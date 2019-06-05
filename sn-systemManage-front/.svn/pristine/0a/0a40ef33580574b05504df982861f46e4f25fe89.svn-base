package com.ratta.spnote.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author page system.properties 配置文件帮助类 2018-10-31
 */
public class SystemUtil {

	/**
	 * 配置文件
	 */
	private final static Properties ps = new Properties();;

	private SystemUtil() {
	}

	/**
	 * 初始化配置文件
	 */
	static {

		try {
			if (System.getProperty("os.name").startsWith("Windows")) {
				ps.load(ConfigUtil.class.getClassLoader().getResourceAsStream("system.properties"));
			} else {
				String path = System.getProperty("user.dir") + File.separator + "etc" + File.separator
						+ "system.properties";
				InputStream is = new FileInputStream(new File(path));
				ps.load(is);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * getPropertity
	 * </p>
	 * <p>
	 * 根据属性名获取属性值
	 * </p>
	 * 
	 * @param name 属性的名称
	 * @return 属性值
	 */
	public static String getPropertity(String name) {
		if (ps == null) {
			return null;
		}
		return ps.getProperty(name).trim();
	}

}
