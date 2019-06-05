package com.ratta.suponote.firmware.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.firmware.model.FirmwareFixPoint;

/**
 * @author page
 * 固件修复点管理持久层
 * 2018-10-31
 */
@Repository("firmwareFixPointDao")
public class FirmwareFixPointDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件修复点</p>
	 * @param params 查询固件修复点
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 固件修复点列表
	 */
	public List<FirmwareFixPoint> query(Map<String, Object> params){
		return getSqlSession().selectList("firmwareFixPoint.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计固件修复点</p>
	 * @param params 查询固件修复点本
	 * @return 固固件修复点统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("firmwareFixPoint.count", params);
	}
	/**
	 * 
	 * <p>save</p>
	 * <p>添加固件修复点</p>
	 * @param params 查询固件修复点
	 * @return 结果
	 */
	public int save(FirmwareFixPoint firmwareFixPoint){
		return getSqlSession().insert("firmwareFixPoint.save", firmwareFixPoint);
	}
	/**
	 * 
	 * <p>update</p>
	 * <p>修改固件修复点</p>
	 * @param params 查询固件修复点
	 * @return 结果
	 */
	public int update(FirmwareFixPoint firmwareFixPoint){
		return getSqlSession().update("firmwareFixPoint.update", firmwareFixPoint);
	}
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据固件版本号删除固件修复点</p>
	 * @param firmware_version 固件版本号
	 * @return 结果
	 */
	public int delete(String firmwareVersion){
		return getSqlSession().delete("firmwareFixPoint.delete", firmwareVersion);
	}
	
}
