package com.ratta.suponote.firmware.service;

import java.util.List;
import java.util.Map;
import com.ratta.suponote.firmware.model.FirmwareEquipment;
import com.ratta.suponote.firmware.result.FirmwareEquipResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;

/**
 * 
 *
 */
/**
 * @author page
 * 固件设备绑定管理服务
 * 2018-10-31
 */
public interface  FirmwareEquipService{

	/**
	 * 
			* <p>firmwareEquip</p>
			* <p>加载固件设备绑定信息</p>
			* @param ph 分页类 
			* @param params 参数
			* @return datagrid模型
	 */
	DataGrid firmwareEquip(PageHelper ph, Map<String, Object> params);
	/**
	 * queryCount<br>
	 * <br>
	 * @param version 版本号
	 * @return count 数量
	 */
	Long queryCount(String version);
	/**
	 * queryEquipByVersion
	 * @param params 参数
	 * @return 设备型号列表
	 */
	List<FirmwareEquipment> queryEquipByVersion(Map<String, Object> params);
	/**
	 * 
	 * <p>addFirmwareEquipment</p>
	 * <p>添加大固件跟设备型号对应关系信息</p>
	 * @param version 固件版本
	 * @param equipmentId 设备ID
	 * @return 返回枚举
	 */
	FirmwareEquipResult addFirmwareEquipment(String version,List<String> equipmentId);
}
