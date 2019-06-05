package com.ratta.suponote.business.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.business.model.EquipType;

/**
 * @author page
 *
 * 2018-10-31
 */
@Repository("equipTypeDao")
public class EquipTypeDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备型号</p>
	 * @param params 查询设备型号
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 设备型号列表
	 */
	public List<EquipType> query(Map<String, Object> params){
		return getSqlSession().selectList("equipType.query", params);
	}
	/**
	 * 
	 * <p>count</p>
	 * <p>统计设备型号信息</p>
	 * @param params 查询设备型号
	 * @return 设备型号统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("equipType.count", params);
	}
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加设备型号信息</p>
	 * @param  设备型号信息
	 * @return 数据库影响行数
	 */
	public int save(EquipType equipType){
		return getSqlSession().insert("equipType.insert", equipType);
	}
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新设备型号信息</p>
	 * @param 设备型号信息
	 * @return 数据库影响行数
	 */
	public int update(EquipType equipType){
		return getSqlSession().update("equipType.update", equipType);
	}
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除设备型号信息</p>
	 * @param code 设备型号信息
	 * @return 数据库影响行数
	 */
	public int delete(Long id){
		return getSqlSession().delete("equipType.delete", id);
	}
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询设备型号信息</p>
	 * @param id
	 * @return 设备型号信息
	 */
	public EquipType query(Long id){
		return getSqlSession().selectOne("equipType.queryById", id);
	}
	/**
	 * 
	 * <p>queryByType</p>
	 * <p>根据名称查询设备信息</p>
	 * @param type
	 * @return 设备型号信息
	 */
	public EquipType queryByType(String type) {
		return getSqlSession().selectOne("equipType.queryByType", type);
	}
}
