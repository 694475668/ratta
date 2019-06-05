package com.ratta.spnote.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 
		* ApkUtil
		* APK工具类
 */
public class ApkUtil {
	private static  String aapt;
	static{
		if(System.getProperty("os.name").startsWith("Windows")){
			aapt = new File("aapt/aapt.exe").getAbsolutePath();
		}else{
			aapt = System.getProperty("user.dir") + File.separator + "aapt" + File.separator + "aapt";
		}
	}
	/**
	 * 读取apk文件包信息
	 * @param file apk文件
	 * @return apk文件全部信息
	 */
	public static List<String> getApkAllInfo(File file){
		Process process =null;
		List<String> params = new ArrayList<String>();
		String command = aapt+ " d badging " + file.getAbsolutePath();
		System.out.println("command : "+command);
		try {
			process = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),"utf8"));
			String line =null;
			while((line=br.readLine())!=null){
				params.add(line);
			}
			process.waitFor();   
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(process!=null){
				process.destroy();
			}
		}
		return params;
	}
	/**
	 * 获取apk文件全部的配置信息
	 * @param path apk文件全路径
	 * @return apk文件全部信息
	 */
	public static List<String> getApkAllInfo(String path){
		File file  = new File(path);
		if(!file.exists()){
			return null;
		}
		return getApkAllInfo(file);
	}
	
	/**
	 * 列出apk文件里面所有的文件名
	 * @param zipPathName
	 * @throws Exception
	 */
	public static List<String> listJarFile(String zipPathName)throws Exception{
		ZipFile zipFile = new ZipFile(zipPathName);
		Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
		List<String> fileNames = new ArrayList<String>();
		while(zipEntrys.hasMoreElements()){
			ZipEntry zipEntry = zipEntrys.nextElement();
			fileNames.add(zipEntry.getName());
		}
		if(zipFile!=null){
			zipFile.close();
		}
		return fileNames;
	}
	/**
	 * 获取文件的版本信息
	 * @param file apk文件
	 * @return 版本信息
	 */
	public static String getVersion(File file){
		Map<String, Object> maps= getAPKPackage(file);
		return String.valueOf(maps.get("versionName"));
	}
	/**
	 * 
	 * @param filePath apk文件全路径
	 * @return apk文件版本信息
	 */
	public static String getVersion(String filePath){
		return getVersion(new File(filePath));
	}
	/**
	 * 
			* <p>getPackage</p>
			* <p>获取apk的包名</p>
			* @param filePath 文件路径
			* @return 包名字符串
	 */
	public static String getPackage(String filePath){
		return getPackage(new File(filePath));
	}
	/**
	 * 
			* <p>getPackage</p>
			* <p>获取apk的包名</p>
			* @param file apk文件
			* @return 包名字符串
	 */
	public static String getPackage(File file){
		Map<String, Object> maps= getAPKPackage(file);
		return String.valueOf(maps.get("name"));
	}
	
	/**
	 * 
			* <p>getVersionCode</p>
			* <p>获取apk的内部版本号</p>
			* @param url 路径
			* @return 包名字符串
	 */
	public static String getVersionCode(String url){
		File file = new File(url);
		Map<String, Object> maps= getAPKPackage(file);
		return String.valueOf(maps.get("versionCode"));
	}

	/**
	 * 
			* <p>getAPKPackage</p>
			* <p>获取包头部信息</p>
			* //values [0] package  values[1] versionCode value[2] versionName
			* @param file 文件
			* @return name  -- 包名 versionCode 版本  versionName 版本号
			* 
	 */
	private static Map<String, Object> getAPKPackage(File file){
		List<String> params = getApkAllInfo(file);
		Map<String, Object> packs = new HashMap<String, Object>();
		for (String string : params) {
			if(string.startsWith("package")){
				String[] values=string.split(":")[1].trim().split(" ");

				for (String str : values) {
					String[] strs = str.split("=");
					packs.put(strs[0].replace("'", ""), strs[1].replace("'", ""));
				}
			}
		}
		return packs;
	}
	/**
	 * 
	 * @param apkPath apk 文件的路径
	 * @return 默认logo文件
	 */
	public static File getIcon(String apkPath){
		String fileName = "res/drawable-mdpisss/ic_launcher.png";
		return getIcon(apkPath, fileName);
	}
	/**
	 * 
	 * @param apkFile apk 文件
	 * @return 默认logo文件
	 */
	public static File getIcon(File apkFile){
		return getIcon(apkFile.getAbsolutePath());
	}
	/**
	 * 获取apk文件的logo
	 * @param apkpath apk文件路径
	 * @param fileName 要获取的文件的名称
	 * @return logo文件
	 */
	public static File getIcon(String apkPath,String fileName){
		File file = new File(apkPath);
		String outputPath = file.getParent()+File.separator+fileName;
		return getIcon(apkPath, fileName, outputPath);
	}
	/**
	 * 
	 * @param apkFile apk文件
	 * @param fileName 要获取的文件名
	 * @return fileName 文件
	 */
	public static File getIcon(File apkFile,String fileName){
		return getIcon(apkFile.getAbsolutePath(), fileName);
	}
	/**
	 * 获取apk文件的logo
	 * @param apkpath apk文件路径
	 * @param fileName 要获取的文件的名称
	 * @param outputPath 输出的文件路径
	 * @return logo文件
	 */
	public static File getIcon(String apkPath,String fileName,String outputPath){
		return extractFileFromApk(apkPath, fileName,outputPath);
	}
	/**
	 * 
	 * @param apkFile apk文件
	 * @param fileName 要获取的文件名
	 * @param outputPath 输出的文件路径
	 * @return fileName 文件
	 */
	public static File getIcon(File apkFile,String fileName,String outputPath){
		return getIcon(apkFile.getAbsolutePath(), fileName, outputPath);
	}
	
    /**
     * 从 apkPath文件里面 读取 fileName文件 写入到 outpuPath目录
     * @param apkpath apk文件路径
     * @param fileName 要获取的文件名
     * @param outputPath 输出到的目录
     * @throws Exception 抛出的异常 
     * @return file 返回的文件
     */
    public static File extractFileFromApk(String apkPath, String fileName, String outputPath){
    	File file = new File(outputPath);
		if(!file.exists()){
			File parent= new File(file.getParent());
			parent.mkdirs();
		}
    	BufferedOutputStream bos=null;
    	BufferedInputStream bis =null;
    	InputStream is =null;
    	ZipFile zFile =null;
		try {
			zFile= new ZipFile(new File(apkPath));
			ZipEntry entry = zFile.getEntry(fileName);
			if(entry!=null){
				is = zFile.getInputStream(entry);
			}else{
				Enumeration<? extends ZipEntry>  entries =zFile.entries();
				while(entries.hasMoreElements()){
					ZipEntry entity= entries.nextElement();
					if(entity.getName().endsWith(fileName.substring(fileName.lastIndexOf("/")))){
						is=zFile.getInputStream(entity);
						break;
					}
				}
			}

			
			
			bos= new BufferedOutputStream(new FileOutputStream(file), 1024);
			byte[] b = new byte[1024];
			bis = new BufferedInputStream(is, 1024);
			while(bis.read(b) != -1){
			    bos.write(b);
			}
			bos.flush();
			
		} catch (Exception e) {
			e.printStackTrace();				
		}finally{
			if(zFile!=null){
				try {
					zFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();	
				}
			}
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();	
				}
			}
			if(is !=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
        return file;
    }
    /**
     * 
     * @param res 将从apk文件获取的参数信息转换成对象,方便处理(不可用)
     * <p>暂时未实现</p>
     * @return apk文件参数对象
     */
    public static<T> T string2obj(String res){
    	
    	return null;
    }
    /**
     * 根据属性名获取属性值(不太好用)
     * @param apkPath apk文件路径
     * @param name 属性名称
     * @return 属性值集合
     */
    public static List<String> getProperty(String apkPath, String name){
    	 List<String> params = getApkAllInfo(apkPath);
    	 List<String> values = new ArrayList<String>();
    	 for (String string : params) {
			if(string.startsWith(name)){
				String[] vs= string.split(":");
				if(vs.length>1){
					values.add(vs[1].replace("'", "").trim());
				}
			}
		}
    	return values;
    }
    /**
     * 
    		* <p>getApkLabel</p>
    		* <p>获取apk文件的application-label</p>
    		* @param apk 文件路径
    		* @return application-label 文件描述
     */
    public static String getApkLabel(String filePath,String app_name){
    	List<String> lines = getApkAllInfo(filePath);
    
    	for (String string : lines) {
			if(string.startsWith("application-label")){
				String[] str = string.split(":");
				if(str.length>1){
					return str[1].replace("'", "");
				}
			}else if(string.startsWith("application")) {
				String[] str = string.split("'");
				if(str.length>1){
					return str[1];
				}
			}
		}
    	return null;
    }
    public static void main(String[] args) {
		String url="E:\\assemble-debug-1.0.0.1-2019-03-08.apk";
		List<String> string = getApkAllInfo(url);
		for (String string2 : string) {
			System.out.println(string2);
		}
	}
}
