package com.ratta.suponote.stock.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.stock.model.InOutStock;

/**
 * @author page
 * 出入库管理持久层
 * 2018-10-31
 */
@Repository("inOutStockDao")
public class InOutStockDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询出入库记录</p>
	 * @param params 查询出入库记录
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 出入库记录列表
	 */
	public List<InOutStock> query(Map<String, Object> params){
		return getSqlSession().selectList("inOutStock.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计出入库记录</p>
	 * @param params 查询出入库记录
	 * @return 出入库记录统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("inOutStock.count", params);
	}
	/**
	 * 
	 * <p>queryBatchNo</p>
	 * <p>查询批次号</p>
	 * @param params 
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 批次号列表
	 */
	public List<InOutStock> queryBatchNo(Map<String, Object> params){
		return getSqlSession().selectList("inOutStock.queryBatchNo", params);
	}
	
	/**
	 * 
	 * <p>countBatchNo</p>
	 * <p>统计批次号</p>
	 * @param params 
	 * @return 批次号统计数
	 */
	public Long countBatchNo(Map<String, Object> params){
		return getSqlSession().selectOne("inOutStock.countBatchNo", params);
	}
	
	/**
	 * 
	 * <p>querySumAll</p>
	 * <p>查询所有的设备库存</p>
	 * @param params 查询参数
	 */
	public List<InOutStock> querySumAll(Map<String, Object> params){
		return getSqlSession().selectList("inOutStock.querySumAll",params);
	}
	/**
	 * 
	 * <p>queryEquipmentPurpose</p>
	 * <p>根据批次号查询设备用途</p>
	 * @param batchNo 批次号
	 */
	public List<InOutStock> queryEquipmentPurpose(String batchNo){
		return getSqlSession().selectList("inOutStock.queryEquipmentPurpose",batchNo);
	}
	/**
	 * 
	 * <p>queryAllById</p>
	 * <p>根据id查询未转化的出入库记录</p>
	 * @param id
	 * @return 出入库记录信息
	 */
	public InOutStock queryAllById(Long id){
		return getSqlSession().selectOne("inOutStock.queryAllById", id);
	}
	/**
	 * 
	 * <p>queryByBatchNo</p>
	 * <p>根据批次号查询设备型号和初始固件版本号</p>
	 * @param batchNo 批次号
	 * @return 出入库记录信息
	 */
	public InOutStock queryByBatchNo(String batchNo){
		return getSqlSession().selectOne("inOutStock.queryByBatchNo", batchNo);
	}
	/**
	 * 
			* <p>generateId</p>
			* <p>生成出入库记录id</p>
			* @return 出入库记录id
	 */
	public Long generateId() {
		return getSqlSession().selectOne("inOutStock.generateId");
	}
	/**
	 * 
			* <p>save</p>
			* <p>生成出入库记录</p>
			* @return 
	 */
	public int save(InOutStock inoutStock) {
		return getSqlSession().insert("inOutStock.save",inoutStock);
	}
	/**
	 * 
			* <p>update</p>
			* <p>修改出入库记录</p>
			* @return 
	 */
	public int update(InOutStock inoutStock) {
		return getSqlSession().update("inOutStock.update",inoutStock);
	}
	/**
	 * 
			* <p>queryNewInOut</p>
			* <p>根据设备号查询最新的入库信息</p>
			* @param equipmentNo 设备序列号
			* @return 入库记录信息
	 */
	public InOutStock queryNewInOut(String equipmentNo) {
		return getSqlSession().selectOne("inOutStock.queryNewInOut", equipmentNo);
	}
	/**
	 * 
	 * <p>queryByDealersId</p>
	 * <p>根据经销商ID判断是否在出入库</p>
	 * @param params 
	 * @return 
	 */
	public Long queryByParams(Map<String, Object> params){
		return getSqlSession().selectOne("inOutStock.queryByParams",params);
	}
	/**
	 * 
			* <p>queryByInoutId</p>
			* <p>根据出入库ID查询剩余库存记录和第一个设备号</p>
			* @param inout_id 出入库ID
			* @return 记录信息
	 */
	public InOutStock queryByInoutId(Long inoutId,String flag) {
		Map<String, Object> params=new HashMap<String, Object>(8);
		params.put("inout_id", inoutId);
		params.put("flag", flag);
		return getSqlSession().selectOne("inOutStock.queryByInoutId", params);
	}
}
