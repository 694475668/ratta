package com.ratta.suponote.firmware.service;

import java.util.List;

import com.ratta.suponote.firmware.model.FirmwareZip;
import com.ratta.suponote.firmware.result.FirmwareInfoResult;

/**
 * @author page
 * 固件重组压缩包服务
 * 2018-10-31
 */
public interface FirmwareZipService {

	/**
	 * 
	 * <p>addFirmwareZip</p>
	 * <p>添加版本号相同的组合大固件</p>
	 * @param FirmwareZip 固件压缩包
	 * @return 返回枚举
	 */
	FirmwareInfoResult addFirmwareZip(FirmwareZip FirmwareZip);
	
	/**
	 * delete<br>
	 * 根据版本号删除固件信息<br>
	 * @param version 版本号
	 * @return 返回枚举
	 */
	FirmwareInfoResult delete(String version);
	/**
	 * firmwareZipList<br>
	 * 根据版本号查找固件信息<br>
	 * @param version 版本号
	 * @return 固件压缩包集合
	 */
	List<FirmwareZip> firmwareZipList(String version);
}
