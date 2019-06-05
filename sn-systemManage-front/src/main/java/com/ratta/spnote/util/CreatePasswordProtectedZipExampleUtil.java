package com.ratta.spnote.util;

import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * @author page 压缩文件 2018-10-31
 */
public class CreatePasswordProtectedZipExampleUtil {

	public static void main(String[] args) {
		// 创建压缩文件
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile("D:/SN100.A000.1803281000.001_rd.zip");
		} catch (ZipException e) {
			e.printStackTrace();
		}
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("C:/Users/TroyLiu/Desktop/supernotes-web/新建文件夹/app.zip"));
		files.add(new File("C:/Users/TroyLiu/Desktop/supernotes-web/新建文件夹/app_lib.zip"));
		files.add(new File("C:/Users/TroyLiu/Desktop/supernotes-web/新建文件夹/config.json"));
		files.add(new File("C:/Users/TroyLiu/Desktop/supernotes-web/新建文件夹/system.zip"));
		files.add(new File("C:/Users/TroyLiu/Desktop/supernotes-web/新建文件夹/system_lib.zip"));
		// 压缩
		CompressionFiles(zipFile, files, "123456");
	}

	/**
	 * CompressionFiles<br>
	 * 加密压缩文件<br>
	 * 
	 * @param zipFile  压缩后的文件目录
	 * @param files    等待压缩的文件
	 * @param password 压缩密码
	 */
	public static void CompressionFiles(ZipFile zipFile, ArrayList<File> files, String password) {
		try {
			// 设置压缩文件参数
			ZipParameters parameters = new ZipParameters();
			// 设置压缩方法
			parameters.setCompressionMethod(Zip4jConstants.COMP_STORE);

			// 设置压缩级别
			// DEFLATE_LEVEL_FASTEST - Lowest compression level but higher speed
			// of compression
			// DEFLATE_LEVEL_FAST - Low compression level but higher speed of
			// compression
			// DEFLATE_LEVEL_NORMAL - Optimal balance between compression
			// level/speed
			// DEFLATE_LEVEL_MAXIMUM - High compression level with a compromise
			// of speed
			// DEFLATE_LEVEL_ULTRA - Highest compression level but low speed
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

			// 设置压缩文件加密
			parameters.setEncryptFiles(true);

			// 设置加密方法
			parameters.setEncryptionMethod(Zip4jConstants.COMP_STORE);

			// 设置aes加密强度
			parameters.setAesKeyStrength(Zip4jConstants.COMP_STORE);

			// 设置密码
			parameters.setPassword(password);
			// 添加文件到压缩文件
			zipFile.addFiles(files, parameters);
			// 将文件夹目录添加到压缩文件里面
			/*
			 * zipFile.addFolder("D:/Download\\20160414\\merchant\\settle", parameters);
			 */
		} catch (ZipException e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * UnpackZip<br>
	 * 解压缩加密的压缩包<br>
	 * 
	 * @param zipPath    压缩包路径
	 * @param password   压缩密码
	 * @param unpackPath 解压后的路径
	 */
	public static int UnpackZip(String zipPath, String password, String unpackPath) {
		int a = 0;
		try {
			ZipFile zipFile = new ZipFile(zipPath);
			// 判断压缩包是否加密
			if (zipFile.isEncrypted()) {
				// 如果是，则设置zip文件的密码。
				zipFile.setPassword(password);
			}
			zipFile.extractAll(unpackPath);
			a = 1;
		} catch (ZipException e) {
			e.printStackTrace();
			a = 0;
		}
		return a;
	}

}
