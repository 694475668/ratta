package com.ratta.suponote.equipment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.equipment.model.EquipmentLog;

/**
 * @author page
 * 设备日志管理持久层
 * 2018-10-31
 */
@Repository("equipmentLogDao")
public class EquipmentLogDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备记日志记录</p>
	 * @param params params
	 * @return 日志记录列表
	 */
	public List<EquipmentLog> query(Map<String, Object> params){
		return getSqlSession().selectList("equipmentLog.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计日志记录</p>
	 * @param params params
	 * @return 日志记录统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("equipmentLog.count", params);
	}
	/**
	 * 
	 * <p>queryById</p>
	 * <p>根据ID查询日志记录</p>
	 * @param id id
	 * @return 退换货记录
	 */
	public EquipmentLog queryById(Long id){
		return getSqlSession().selectOne("equipmentLog.queryById", id);
	}
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据id查询删除设备日志信息</p>
	 * @param id
	 * @return 
	 */
	public int delete(Long id){
		return getSqlSession().delete("equipmentLog.delete", id);
	}
	/**
	 * 
			* <p>selectByDays</p>
			* <p>查询某个时间点之前的数据</p>
			* @param daysBefore 几天前的数据
			* @return 结果集
	 */
	public List<EquipmentLog>  selectByDays(Long daysBefore){
		return getSqlSession().selectList("equipmentLog.selectByDays", daysBefore);
	}
	/**
	 * <p>updateByParam</p>
	 * <p>根据ID更新下载标识、查看标识、备注</p>
	 * @param id id
	 * @param flag 查看标识
	 * @param isDownload 是否下载标识
	 * @param remark 备注
	 * @return 返回枚举
	 */
	public int updateByParam(Long id,String flag,String isDownload,String remark) {
		Map<String,Object> params = new HashMap<String,Object>(16);
		params.put("id", id);
		params.put("flag", flag);
		params.put("isDownload", isDownload);
		params.put("remark", remark);
		return getSqlSession().update("equipmentLog.updateByParam", params);
	}
}
