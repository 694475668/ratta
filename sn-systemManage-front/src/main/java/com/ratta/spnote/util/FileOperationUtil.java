package com.ratta.spnote.util;

import java.io.File;

/**
 * @author page 文件操作帮助类 2018-10-31
 */
public class FileOperationUtil {

	public static void main(String[] args) {
		String sPath = "E:/SN100-Manage/sn-systemManage-front/src/main/webapp/file/firmware/1517996765059";
		File file = new File(sPath);
		deleteAll(file);

	}

	public static void deleteAll(File file) {

		if (file.isFile() || file.list().length == 0) {
			file.delete();
		} else {
			for (File f : file.listFiles()) {
				deleteAll(f); // 递归删除每一个文件

			}
			file.delete(); // 删除文件夹
		}
	}

}
