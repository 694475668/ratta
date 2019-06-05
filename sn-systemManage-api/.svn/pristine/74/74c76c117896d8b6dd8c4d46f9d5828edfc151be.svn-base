package com.ratta.suponote.system.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.system.ScheduleLog;
import com.ratta.suponote.model.system.ScheduleTask;

/**
 * @author page
 * 调度任务服务
 * 2018-10-31
 */
public interface ScheduleTaskService {
	/**
	 * 
			* <p>query</p>
			* <p>查询调度任务</p>
			* @param ph 分页帮助类
			* @param params 查询参数
			* @return easyui datagrid 数据模型
	 */
	DataGrid  query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>queryAll</p>
			* <p>查询所有的调度任务信息</p>
			* @return 调度任务集合
	 */
	List<ScheduleTask> queryAll();
	/**
	 * 
			* <p>save</p>
			* <p>添加调度任务</p>
			* @param scheduleTask 调度任务
			* @param sessionInfo 信息模型
			* @return 1添加成功  0 添加失败
	 */
	int save(ScheduleTask scheduleTask,SessionInfo sessionInfo);
	/**
	 * 
			* <p>update</p>
			* <p>修改调度任务信息</p>
			* @param scheduleTask 调度任务信息
			* @param sessionInfo 信息模型
			* @return 1 修改成功 0 修改失败
	 */
	int update(ScheduleTask scheduleTask,SessionInfo sessionInfo);

	/**
	 * 
			* <p>queryById</p>
			* <p>根据id查询调度任务信息</p>
			* @param id 调度任务id
			* @return 调度任务
	 */
	ScheduleTask queryById(Long id);
	
	/**
	 * 
			* <p>queryById</p>
			* <p>根据多个id查询调度任务信息</p>
			* @param ids 多个调度任务id
			* @return 调度任务
	 */	
	List<ScheduleTask> queryByIds(String ids);

	/**
	 * 
			* <p>queryLog</p>
			* <p>查询调度任务操作日志</p>
			* @param ph 分页帮助类
			* @param params 查询参数
			* @return easyui datagrid 数据模型
	 */
	DataGrid queryLog(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>addLog</p>
			* <p>添加记录</p>
			* @param scheduleLog 调度任务记录
			* @return 1添加成功  0 添加失败
	 */
	int addLog(ScheduleLog scheduleLog);
	
	/**
	 * 
			* <p>dataclean</p>
			* <p>数据库定期清理数据</p>
			* @return 1 执行成功  ，0 执行失败
	 */
	int dataclean();
	/**
	 * 
			* <p>tokenclean</p>
			* <p>数据库定期清理token表数据</p>
			* @return 1 执行成功  ，0 执行失败
	 */
	int tokenclean();
	
	/**
	 * 
			* <p>logback</p>
			* <p>manage-service 日志备份</p>
			* @return 1执行成功,0 执行失败
	 */
	//int logback();
	/**
	 * 
			* <p>dbback</p>
			* <p>数据库备份</p>
			* @return 1执行成功,0 执行失败
	 */
	//int dbback();
}

	