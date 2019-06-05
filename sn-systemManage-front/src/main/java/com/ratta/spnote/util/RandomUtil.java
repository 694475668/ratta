package com.ratta.spnote.util;

import java.util.Random;

/**
 * @author page 随机数生成工具类 2018-10-31
 */
public class RandomUtil {

	/**
	 * 生成指定长度的随机数(仅数字)
	 * 
	 * @param length 随机数长度
	 * @return
	 */
	public static String getRandomStringByLength(int length) {
		String base = "012345678998765432101234567890";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成指定长度的随机密码（含字母大小写）
	 * 
	 * @param length 密码位数
	 * @return
	 */
	public static String getRandomPwdByLength(int length) {
		char tmp;
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int randomTmp = random.nextInt(3);// 0,1,2
			if (randomTmp == 0) {
				tmp = (char) (random.nextInt(10) + '0');// 生成0~9的数字
			} else if (randomTmp == 1) {
				tmp = (char) (random.nextInt(26) + 'A');// 生成A~Z的字母
			} else {
				tmp = (char) (random.nextInt(26) + 'a');// 生成a~z的字母
			}
			sb.append(tmp);
		}
		return sb.toString();
	}

}
