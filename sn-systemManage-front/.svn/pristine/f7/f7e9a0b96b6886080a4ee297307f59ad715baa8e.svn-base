package com.ratta.spnote.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.ratta.suponote.firmware.model.FirmwareInfo;

import net.sf.json.JSONObject;

/**
 * @author page 读取文本文件内容 2018-10-31
 */
public class ReadTxtUtil {

	public static String readTxtFile(String filePath) {
		String txt = "";
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null)

				{
					txt = txt + lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return txt;

	}

	@SuppressWarnings("unused")
	public static void main(String argv[]) {
		String filePath = "D:\\12.json";
		String txt = readTxtFile(filePath);
		// 将json数据转成对象
		FirmwareInfo firmwareInfo = (FirmwareInfo) JSONObject.toBean(JSONObject.fromObject(txt), FirmwareInfo.class);
		txt = txt.trim();
		System.out.println(txt);
	}
}
