package com.ratta.spnote.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ratta.suponote.model.pagemodel.MessagePush;

/**
 * @author page 用来存储Excel标题的对象，通过该对象可以获取标题和方法的对应关系 2018-10-31
 */
public class FirmwareUpdateUtil {
	public static final int push(List<String> equipmentNoList, String msg) throws UnsupportedEncodingException {
		String url = ConfigUtil.get("terminalUrl");
		Date date = new Date();
		MessagePush mp = new MessagePush();
		mp.setBusCode("DTNC");
		mp.setEquipmentNoList(equipmentNoList);
		mp.setLanguage("CN");
		mp.setNoticeCode(msg);
		mp.setNonce(RandomUtil.getRandomStringByLength(10) + date.getTime());
		mp.setTimestamp(date.getTime());
		String json = JSONObject.toJSONString(mp);
		try {
			String result = HttpUtil.sendPost(url, json, "utf-8");
			JSONObject jsonNew = JSONObject.parseObject(result);
			if ("true".equals(jsonNew.getString("success"))) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
}
