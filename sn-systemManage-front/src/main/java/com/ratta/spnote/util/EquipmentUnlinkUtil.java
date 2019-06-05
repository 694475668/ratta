package com.ratta.spnote.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.alibaba.fastjson.JSONObject;
import com.ratta.suponote.model.pagemodel.EquipmentUnllinkPush;

/**
 * @author page 用来存储Excel标题的对象，通过该对象可以获取标题和方法的对应关系 2018-10-31
 */
public class EquipmentUnlinkUtil {
	public static final int push(String equipment_no, String account) throws UnsupportedEncodingException {
		String url = ConfigUtil.get("terminalUnlinkUrl");
		Date date = new Date();
		EquipmentUnllinkPush mp = new EquipmentUnllinkPush();
		mp.setBusCode("DTEU");
		mp.setLanguage("CN");
		mp.setNonce(RandomUtil.getRandomStringByLength(10) + date.getTime());
		mp.setTimestamp(date.getTime());
		mp.setAccount(account);
		mp.setEquipmentNo(equipment_no);
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
