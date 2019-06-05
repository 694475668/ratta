package com.ratta.suponote.firmware.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.firmware.model.FirmwareTask;

/**
 * @author page
 * 固件任务持久层
 * 2018-10-31
 */
@Repository("firmwareTaskDao")
public class FirmwareTaskDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件版本任务</p>
	 * @param params 查询固件版本
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 固件版本列表
	 */
	public List<FirmwareTask> query(Map<String, Object> params){
		return getSqlSession().selectList("firmwareTask.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计固件版本任务信息</p>
	 * @param params 查询固件版本
	 * @return 固件版本统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("firmwareTask.count", params);
	}
	/**
	 * 
	 * <p>queryAuditTest</p>
	 * <p>查询固件审核测试任务</p>
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 固件审核测试列表
	 */
	public List<FirmwareTask> queryAuditTest(Map<String, Object> params){
		return getSqlSession().selectList("firmwareTask.queryAuditTest", params);
	}
	
	/**
	 * 
	 * <p>countAuditTest</p>
	 * <p>统计固件审核测试任务</p>
	 * @return 固件审核测试任务统计数
	 */
	public Long countAuditTest(Map<String, Object> params){
		return getSqlSession().selectOne("firmwareTask.countAuditTest", params);
	}
	/**
	 * 
	 * <p>countByVersion</p>
	 * <p>根据版本号统计审核测试的记录数</p>
	 * @return 固件审核测试任务统计数
	 */
	public int countByVersion(String version){
		return getSqlSession().selectOne("firmwareTask.countByVersion", version);
	}
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询固件版本任务信息</p>
	 * @param id
	 * @return 固件版本信息
	 */
	public FirmwareTask query(Long id){
		return getSqlSession().selectOne("firmwareTask.queryById", id);
	}
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据id查询删除固件任务信息</p>
	 * @param id
	 * @return 
	 */
	public int delete(Long id){
		return getSqlSession().delete("firmwareTask.delete", id);
	}
	/**
	 * 
	 * <p>update</p>
	 * <p>修改固件发布任务</p>
	 * @param params 
	 * @return
	 */
	public int update(Map<String, Object> params){
		return getSqlSession().update("firmwareTask.update", params);
	}
	/**
	 * 
	 * <p>insertFirmwareTask</p>
	 * <p>修改固件发布任务完成标识</p>
	 * @param params id
	 * @return
	 */
	public int insertFirmwareTask(FirmwareTask firmwareTask){
		return getSqlSession().insert("firmwareTask.insertFirmwareTask", firmwareTask);
	}
	/**
	 * 
	 * <p>deleteTask</p>
	 * <p>根据id查询删除终端固件任务信息</p>
	 * @param equipmentNo 设备号
	 * @param taskCode 任务码
	 * @return 
	 */
	public int deleteTask(String equipmentNo,String taskCode){
		Map<String, Object> params=new HashMap<String, Object>(8);
		params.put("task_code", taskCode);
		params.put("equipment_no", equipmentNo);
		return getSqlSession().delete("firmwareTask.deleteTask", params);
	}
	/**
	 * 
			* <p>generateId</p>
			* <p>生成出入库记录id</p>
			* @return 出入库记录id
	 */
	public Long generateId() {
		return getSqlSession().selectOne("firmwareTask.generateId");
	}
	/**
	 * 
	 * <p>selectByVersion</p>
	 * <p>根据版本号查询固件任务</p>
	 * @param firmwareVersion 固件版本号 
	 * @return 固件任务ID列表
	 */
	public List<Long> selectByVersion(String firmwareVersion){
		return getSqlSession().selectList("firmwareTask.selectByVersion", firmwareVersion);
	}
}
