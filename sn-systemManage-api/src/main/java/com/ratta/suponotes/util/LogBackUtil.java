package com.ratta.suponotes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author page
 * 日志配置文件工具类
 * 2018-10-31
 */
public class LogBackUtil {

	private final static Properties ps = new Properties();
	/**
	 * 日志根目录配置名称
	 */
	private final static String LOG_ROOT ="logRoot";
	/**
	 * sn-systemManage-front的tomcat启动日志目录
	 */
	private final static String lOG_MANAGE_TOMCAT ="logManageTomcat";
	/**
	 * sn-systemManage-front的文件服务器日志备份
	 */
	public final static String lOG_FILE_SERVER_TOMCAT ="logFileServerTomcat";
	/**
	 * sn-systemManage-front的tomcat的catalina.out文件路径
	 */
	public final static String CATALINA_OUT ="catalinaOut"; 
	private LogBackUtil(){
		
	}
	static{
		try {
			if(System.getProperty("os.name").startsWith("Windows")){
				InputStream is =  LogBackUtil.class.getClassLoader().getResourceAsStream("logback.properties");
				ps.load(is); 
			}else{
				String path = System.getProperty("user.dir") + File.separator + "etc" + File.separator + "logback.properties";
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
	 * 通过键获取值
	 * 
	 * @param key 键值
	 * @return key
	 */
	public static final String get(String key) {
		if(ps == null){
			return null;
		}
		return ps.getProperty(key).trim();
	}
	
	/**
	 * 
			* <p>backupLog</p>
			* <p>备份日志文件</p>
			* @param logName 日志文件名 (比如sn-systemManage-front)
			* @return file
	 */
	public static File backupLog(String logName){
		//1,获取日志文件路径
		//2,将不是今天的日志文件打成zip包
		//3,将zip包发送到指定目录下
		//4,删除被打包的文件
		//5,返回打包好的压缩文件
	String logRoot =  LogBackUtil.get(LOG_ROOT);
	
	List<File> files = FileUtils.listsAllFiles(logRoot);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(new Date());

	List<File> backFiles = new ArrayList<File>();
	//查询出今天之前的所有的日志
	for (File file : files) {
		String modifyTime = FileUtils.readModifyTime(file);
		if(today.compareTo(modifyTime) > 0){   
			backFiles.add(file);
		}
	}
	String zipName =logRoot+File.separator+"logback_"+logName+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".zip";
	try {
		FileUtils.zipFile(zipName, backFiles);
	} catch (IOException e) {
		e.printStackTrace();
	}
	//删除已经备份过的日志文件
	for (File file : backFiles) {
		file.delete();
	}
	return new File(zipName);
}
	/**
	 * 
			* <p>backupLogTomcat</p>
			* <p>备份sn-systemManage-front的tomcat启动日志文件</p>
			* @param logName 日志文件名 
			* @return file
	 */
	public static File backupLogTomcat(String logName){
		//1,获取日志文件路径
		//2,将不是今天的日志文件打成zip包
		//3,将zip包发送到指定目录下
		//4,删除被打包的文件
		//5,返回打包好的压缩文件
		String logRoot =  LogBackUtil.get(lOG_MANAGE_TOMCAT);
		
		List<File> files = FileUtils.listsAllFiles(logRoot);
		String catalinaOut =  LogBackUtil.get(CATALINA_OUT);
		File cafile =new File(catalinaOut);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
	
		List<File> backFiles = new ArrayList<File>();
		//查询出今天之前的所有的日志
		for (File file : files) {
			String modifyTime = FileUtils.readModifyTime(file);
			if(today.compareTo(modifyTime) > 0){   
				backFiles.add(file);
			}
			
		}
		boolean boo=false;
		String zipName =logRoot+File.separator+"logback_"+logName+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".zip";
		try {
			for (File file : backFiles) {
				if("catalina.out".equals(file.getName().toLowerCase())){
					boo=true;
				}
			}
			if(!boo){
				backFiles.add(cafile);
			}
			FileUtils.zipFile(zipName, backFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//删除已经备份过的日志文件
		for (File file : backFiles) {
			if("catalina.out".equals(file.getName().toLowerCase())){
				continue;
			}
			file.delete();
		}
		return new File(zipName);
   }
	/**
	 * 
			* <p>backupLogTomcat</p>
			* <p>备份sn-systemManage-front的tomcat启动日志文件</p>
			* @param logName 日志文件名
			* @param home 路径
			* @return file
	 */
	public static File backupLogLandi(String logName,String home){
		//1,获取日志文件路径
		//2,将不是今天的日志文件打成zip包
		//3,将zip包发送到指定目录下
		//4,删除被打包的文件
		//5,返回打包好的压缩文件
		
		List<File> files = FileUtils.listsAllFiles(home);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
	
		List<File> backFiles = new ArrayList<File>();
		//查询出今天之前的所有的日志
		for (File file : files) {
			String modifyTime = FileUtils.readModifyTime(file);
			if(today.compareTo(modifyTime) > 0){   
				backFiles.add(file);
			}
			
		}
		String zipName =home+File.separator+"logback_"+logName+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".zip";
		try {
			FileUtils.zipFile(zipName, backFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//删除已经备份过的日志文件
		for (File file : backFiles) {
			file.delete();
		}
		return new File(zipName);
}
	/**
	 * 
			* <p>backupLogTomcat</p>
			* <p>备份sn-systemManage-front的文件服务器日志</p>
			* @param logName 日志文件名 
			* @return file
	 */
	public static File backupLogFileTomcat(String logName){
		//1,获取日志文件路径
		//2,将不是今天的日志文件打成zip包
		//3,将zip包发送到指定目录下
		//4,删除被打包的文件
		//5,返回打包好的压缩文件
		String logRoot =  LogBackUtil.get(lOG_FILE_SERVER_TOMCAT);
		
		List<File> files = FileUtils.listsAllFiles(logRoot);
		File cafile =new File(logRoot+File.separator+"catalina.out");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
	
		List<File> backFiles = new ArrayList<File>();
		//查询出今天之前的所有的日志
		for (File file : files) {
			String modifyTime = FileUtils.readModifyTime(file);
			if(today.compareTo(modifyTime) > 0){   
				backFiles.add(file);
			}
		}
		boolean boo=false;
		String zipName =logRoot+File.separator+"logback_"+logName+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".zip";
		try {
			for (File file : backFiles) {
				if("catalina.out".equals(file.getName().toLowerCase())){
					boo=true;
				}
			}
			if(!boo){
				backFiles.add(cafile);
			}
			FileUtils.zipFile(zipName, backFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//删除已经备份过的日志文件
		for (File file : backFiles) {
			if("catalina.out".equals(file.getName().toLowerCase())){
				continue;
			}
			file.delete();
		}
		return new File(zipName);
}

}

	