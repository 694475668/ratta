package com.ratta.spnote.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * @author page 压缩文件帮助类 2018-10-31
 */
public class DecryptionZipUtil {
	private static Logger logger = LoggerFactory.getLogger(DecryptionZipUtil.class);

	/**
	 * 
	 * @Title: zipFilesAndEncrypt
	 * @Description: 将指定路径下的文件压缩至指定zip文件，并以指定密码加密 若密码为空，则不进行加密保护
	 * @param srcFileName 待压缩文件路径
	 * @param zipFileName zip文件名
	 * @param password    加密密码
	 * @return
	 * @throws Exception
	 */
	public static void zipFilesAndEncrypt(String srcFileName, String zipFileName, String password) throws Exception {
		ZipOutputStream outputStream = null;
		InputStream inputStream = null;
		if (StringUtils.isEmpty(srcFileName) || StringUtils.isEmpty(zipFileName)) {
			throw new NullArgumentException("待压缩文件或者压缩文件名");
		}
		try {
			File srcFile = new File(srcFileName);
			File[] files = new File[0];
			if (srcFile.isDirectory()) {
				files = srcFile.listFiles();
			} else {
				files = new File[1];
				files[0] = srcFile;
			}
			outputStream = new ZipOutputStream(new FileOutputStream(new File(zipFileName)));
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if (!StringUtils.isEmpty(password)) {
				parameters.setEncryptFiles(true);
				// Zip4j supports AES or Standard Zip Encryption (also called
				// ZipCrypto)
				// If you choose to use Standard Zip Encryption, please have a
				// look at example
				// as some additional steps need to be done while using
				// ZipOutputStreams with
				// Standard Zip Encryption
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
				parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
				parameters.setPassword(password);
			}

			// Now we loop through each file and read this file with an
			// inputstream
			// and write it to the ZipOutputStream.
			int fileNums = files.length;
			for (int i = 0; i < fileNums; i++) {
				File file = (File) files[i];

				// This will initiate ZipOutputStream to include the file
				// with the input parameters
				outputStream.putNextEntry(file, parameters);

				// If this file is a directory, then no further processing is
				// required
				// and we close the entry (Please note that we do not close the
				// outputstream yet)
				if (file.isDirectory()) {
					outputStream.closeEntry();
					continue;
				}

				inputStream = new FileInputStream(file);
				byte[] readBuff = new byte[4096];
				int readLen = -1;
				// Read the file content and write it to the OutputStream
				while ((readLen = inputStream.read(readBuff)) != -1) {
					outputStream.write(readBuff, 0, readLen);
				}
				// Once the content of the file is copied, this entry to the zip
				// file
				// needs to be closed. ZipOutputStream updates necessary header
				// information
				// for this file in this step
				outputStream.closeEntry();
				inputStream.close();
			}
			// ZipOutputStream now writes zip header information to the zip file
			outputStream.finish();
		} catch (Exception e) {
			logger.error("文件压缩出错", e);
			throw e;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("压缩文件输出错误", e);
					throw e;
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("压缩文件错误", e);
					throw e;
				}
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param fromFile
	 * @param toFile   <br/>
	 *                 2016年12月19日 下午3:31:50
	 * @throws IOException
	 */
	public static void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream ins = new FileInputStream(fromFile);
		FileOutputStream out = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = ins.read(b)) != -1) {
			out.write(b, 0, n);
		}

		ins.close();
		out.close();
	}

	public static void main(String[] args) {
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("D:/1.txt"));
		files.add(new File("D:/2.txt"));
		File toFile = new File("D://yuanlang");
		if (!toFile.exists()) {
			toFile.mkdirs(); // 创建目录
		}
		for (int i = 0; i < files.size(); i++) {
			try {
				copyFile(files.get(i), new File("D://yuanlang\\" + i + ".txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			zipFilesAndEncrypt("D://yuanlang", "D://test.zip", "123");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}