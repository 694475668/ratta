package com.ratta.suponote.firmware.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.firmware.model.FirmwareFixPoint;
import com.ratta.suponote.firmware.result.FirmwareFixPointResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * 固件修复点管理服务
 * 2018-10-31
 */
public interface  FirmwareFixPointService{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件修复点信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>queryByParam</p>
	 * <p>根据参数查询固件修复点信息</p>
	 * @param params 参数
	 * @return 固件修复点
	 */
	List<FirmwareFixPoint> queryByParam(Map<String, Object> params);
	/**
	 * 
	 * <p>save</p>
	 * <p>添加固件修复点信息</p>
	 * @param firmwareFixPoint 实体
	 * @param sessionInfo sessionInfo
	 * @return 结果
	 */
	FirmwareFixPointResult save(FirmwareFixPoint firmwareFixPoint,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>update</p>
	 * <p>修改固件修复点信息</p>
	 * @param firmwareFixPoint 实体
	 * @param sessionInfo sessionInfo
	 * @return 结果
	 */
	FirmwareFixPointResult update(FirmwareFixPoint firmwareFixPoint,SessionInfo sessionInfo);
}
