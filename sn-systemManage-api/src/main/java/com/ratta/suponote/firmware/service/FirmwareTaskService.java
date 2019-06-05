package com.ratta.suponote.firmware.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.firmware.model.FirmwareTask;
import com.ratta.suponote.firmware.result.FirmwareTaskResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.model.InOutStock;

/**
 * @author page
 * 固件信息发布任务管理服务
 * 2018-10-31
 */
public interface  FirmwareTaskService{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件版本发布任务信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据ID查询固件版本发布任务信息</p>
	 * @param id id
	 * @return 固件任务实体
	 */
	FirmwareTask query(Long id);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据ID查询删除固件版本发布任务信息</p>
	 * @param id id
	 * @param inouts 出入库集合
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	FirmwareTaskResult delete(Long id,List<InOutStock> inouts,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>revoke</p>
	 * <p>撤销固件版本发布任务信息</p>
	 * @param firmwareTask 任务
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	FirmwareTaskResult revoke(FirmwareTask firmwareTask,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>deleteTaskById</p>
	 * <p>根据ID删除固件任务</p>
	 * @param id id
	 */
	void deleteTaskById(long id);
	/**
	 * 
	 * <p>deploy</p>
	 * <p>发布固件版本</p>
	 * @param firmwareTask 固件任务
	 * @param inouts 出入库集合
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	FirmwareTaskResult deploy(FirmwareTask firmwareTask,List<InOutStock> inouts,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>firmwareAuditTest</p>
	 * <p>审核测试固件版本</p>
	 * @param firmwareTask 固件任务实体
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	FirmwareTaskResult firmwareAuditTest(FirmwareTask firmwareTask,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>queryAuditTest</p>
	 * <p>查询固件审核测试任务信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid queryAuditTest(PageHelper ph, Map<String, Object> params);

	/**
	 * 
	 * <p>updateFinshFlag</p>
	 * <p>修改固件发布任务完成标识</p>
	 * @param id id 
	 * @return 更新结果
	 */
	long updateFinshFlag(long id);
	/**
	 * 
	 * <p>countByVersion</p>
	 * <p>根据版本号统计审核测试的记录数</p>
	 * @param version 版本号 
	 * @return 更新结果
	 */
	int countByVersion(String version);
	/**
	 * 
	 * <p>generateId</p>
	 * <p>获取ID</p>
	 * @return id
	 */
	Long generateId();
	/**
	 * 
	 * <p>updateIsHistory</p>
	 * <p>修改固件发布任务为历史版本</p>
	 * @param id id 
	 * @return 更新结果
	 */
	long updateIsHistory(long id);
}
