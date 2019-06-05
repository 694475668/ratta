package com.ratta.suponote.dao.system;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.model.system.ScheduleLog;
import com.ratta.suponote.model.system.ScheduleTask;

/**
 * @author page
 * 调度任务管理持久层
 * 2018-10-31
 */
@Repository("scheduleTaskDao")
public class ScheduleTaskDao extends SqlSessionDaoSupport{
	/**
	 * 
			* <p>save</p>
			* <p>添加调度任务信息</p>
			* @param scheduleTask 调度任务信息
			* @return 影响数据库行数
	 */
	public int save(ScheduleTask scheduleTask){
		return getSqlSession().insert("schedule.save", scheduleTask);
	}
	/**
	 * 
			* <p>update</p>
			* <p>修改调度任务信息</p>
			* @param scheduleTask 调度任务信息
			* @return 影响数据库行数
	 */
	public int update(ScheduleTask scheduleTask){
		return getSqlSession().update("schedule.update",scheduleTask);
	}
	
	
	/**
	 * 
			* <p>updateStatusByIds</p>
			* <p>根据ids更新启动状态</p>
			* @param params : {ids, status}
			* @return 影响数据库行数
	 */
	public int updateStatusByIds(Map<String, Object> params){
		return getSqlSession().update("schedule.updateStatusByIds",params);
	}
	
	
	
	
	/**
	 * 
			* <p>query</p>
			* <p>查询调度任务信息</p>
			* @param params 查询参数
			* p_begin 分页开始页
			* p_end 分页结束页
			* name 调度任务名称(模糊查询)
			* @return 调度任务列表
	 */
	public List<ScheduleTask> query(Map<String, Object> params){
		return getSqlSession().selectList("schedule.query", params);
	}
	/**
	 * 
			* <p>count</p>
			* <p>统计调度任务信息</p>
			* @param params  查询参数
			* @return 调度任务数
	 */
	public long count(Map<String, Object> params){
		return getSqlSession().selectOne("schedule.count", params);
	}
	/**
	 * 
			* <p>queryById</p>
			* <p>根据id查询调度任务信息</p>
			* @param id 调度任务id
			* @return 调度任务信息
	 */
	public  ScheduleTask queryById(Long id){
		return getSqlSession().selectOne("schedule.queryById", id);
	}
	
	/**
	 * 
			* <p>queryByIds</p>
			* <p>根据多个id查询调度任务信息</p>
			* @param ids： 多个调度任务id
			* @return 多个调度任务信息
	 */
	public  List<ScheduleTask> queryByIds(Map<String, Object> params){
		return getSqlSession().selectList("schedule.queryByIds", params);
	}
	
	/**
	 * 
			* <p>queryAll</p>
			* <p>查询所有可以运行的任务信息</p>
			* @return 调度任务信息
	 */
	public List<ScheduleTask> queryAll() {
		return getSqlSession().selectList("schedule.queryAll");
	}
	/**
	 * 
			* <p>saveLog</p>
			* <p>添加日志</p>
			* @param scheduleLog 调度任务日志
			* @return 数据库影响行数
	 */
	public int saveLog(ScheduleLog scheduleLog){
		return getSqlSession().insert("schedule.saveLog", scheduleLog);
	}
	/**
	 * 
			* <p>queryLog</p>
			* <p>查询日志</p>
			* @param params 查询参数
			* p_begin 分页起始页(必填)
			* p_end 分页结束页(必填)
			* task_id 调度任务id
			* schedule_date_begin 开始日期(格式:yyyy-MM-DD)
			* schedule_date_end 结束日期(格式:yyyy-MM-DD)
			* @return 调度任务日志list
	 */
	public  List<ScheduleLog> queryLog(Map<String, Object> params){
		return getSqlSession().selectList("schedule.queryLog", params);
	}
	/**
	 * 
			* <p>countLog</p>
			* <p>统计调度日志</p>
			* @param params 
			* task_id 调度任务id
			* schedule_date_begin 开始日期(格式:yyyy-MM-DD)
			* schedule_date_end 结束日期(格式:yyyy-MM-DD)
			* @return 记录数
	 */
	public Long countLog(Map<String, Object> params){
		return getSqlSession().selectOne("schedule.countLog", params);
	}
	
	/**
	 * 
			* <p>deleteByDays</p>
			* <p>删除记录</p>
			* @param Long daysBefore 几天前的数据
			* @return 数据库影响行数
	 */
	public int deleteByDays(Long daysBefore){
		return getSqlSession().delete("schedule.deleteByDays", daysBefore);
	}
	
}

	