package com.ratta.suponote.stock.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;

/**
 * @author page
 * 设备退换货管理持久层
 * 2018-10-31
 */
@Repository("equipmentReturnRecordDao")
public class EquipmentReturnRecordDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备记退换货记录</p>
	 * @param params params
	 * @return 退换货记录列表
	 */
	public List<EquipmentReturnRecord> query(Map<String, Object> params){
		return getSqlSession().selectList("equipmentReturnRecord.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计退换货记录</p>
	 * @param params params
	 * @return 退换货记录统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("equipmentReturnRecord.count", params);
	}
	/**
	 * 
			* <p>save</p>
			* <p>生成退换货记录</p>
			* @return 
	 */
	public int save(EquipmentReturnRecord equipmentReturnRecord) {
		return getSqlSession().insert("equipmentReturnRecord.save",equipmentReturnRecord);
	}
	/**
	 * 
	 * <p>queryById</p>
	 * <p>根据ID查询退换货记录</p>
	 * @param id id
	 * @return 退换货记录
	 */
	public EquipmentReturnRecord queryById(Long id){
		return getSqlSession().selectOne("equipmentReturnRecord.queryById", id);
	}
	/**
	 * 
	 * <p>queryByEquipmentNo</p>
	 * <p>根据设备号查询退换货记录</p>
	 * @param equipmentNo 设备号
	 * @return 退换货记录
	 */
	public List<EquipmentReturnRecord> queryByEquipmentNo(String equipmentNo){
		return getSqlSession().selectList("equipmentReturnRecord.queryByEquipmentNo", equipmentNo);
	}
}
