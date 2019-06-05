package com.ratta.suponote.stock.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.stock.model.Stock;

/**
 * @author page
 * 库存管理持久层
 * 2018-10-31
 */
@Repository("stockDao")
public class StockDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备库存</p>
	 * @param params 查询设备库存
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 设备库存列表
	 */
	public List<Stock> query(Map<String, Object> params){
		return getSqlSession().selectList("stock.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计设备库存信息</p>
	 * @param params 查询设备库存
	 * @return 设备库存统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("stock.count", params);
	}
	/**
	 * 
	 * <p>querySumAll</p>
	 * <p>查询所有的设备库存</p>
	 * @param params 查询参数
	 */
	public List<Stock> querySumAll(Map<String, Object> params){
		return getSqlSession().selectList("stock.querySumAll",params);
	}
	/**
	 * 
	 * <p>queryStockNotRelease</p>
	 * <p>查询未发布固件任务的设备</p>
	 * @param batch_no 批次号
	 */
	public List<Stock> queryStockNotRelease(Map<String, Object> params){
		return getSqlSession().selectList("stock.queryStockNotRelease",params);
	}
	/**
	 * 
	 * <p>countStockNotRelease</p>
	 * <p>统计未发布固件任务的设备</p>
	 * @param params 查询设备库存
	 * @return 统计数
	 */
	public Long countStockNotRelease(Map<String, Object> params){
		return getSqlSession().selectOne("stock.countStockNotRelease", params);
	}
	/**
	 * 
	 * <p>queryTaskIdByInOutId</p>
	 * <p>通过出入库ID查询任务ID</p>
	 * @param inoutId 出入库ID 
	 */
	public List<Long> queryTaskIdByInOutId(long inoutId){
		return getSqlSession().selectList("stock.queryTaskIdByInOutId",inoutId);
	}
	/**
	 * 
	 * <p>queryEquipmentByInOutId</p>
	 * <p>根据出入库ID查询所有的设备库存</p>
	 * @param inoutId 出入库ID 
	 */
	public List<Stock> queryEquipmentByInOutId(long inoutId){
		return getSqlSession().selectList("stock.queryEquipmentByInOutId",inoutId);
	}
	/**
	 * 
	 * <p>queryEquipmentNoByTaskId</p>
	 * <p>根据任务ID查询设备号信息</p>
	 * @param taskId 固件更新任务ID
	 * @return 设备号
	 */
	public List<String> queryEquipmentNoByTaskId(long taskId){
		return getSqlSession().selectList("stock.queryEquipmentNoByTaskId",taskId);
	}
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询设备库存信息</p>
	 * @param id
	 * @return 设备库存信息
	 */
	public Stock query(Long id){
		return getSqlSession().selectOne("stock.queryById", id);
	}
	/**
	 * 
	 * <p>queryTaskIdByBatchno</p>
	 * <p>根据批次号查询任务ID</p>
	 * @param batchNo 批次号
	 * @return 
	 */
	public List<String>  queryTaskIdByBatchno(String batchNo){
		return getSqlSession().selectList("stock.queryTaskIdByBatchno", batchNo);
	}
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据inoutId删除设备库存信息</p>
	 * @param inoutId
	 * @return 
	 */
	public int delete(Long inoutId){
		return getSqlSession().delete("stock.delete", inoutId);
	}
	/**
	 * 
	 * <p>save</p>
	 * <p>添加设备库存信息</p>
	 * @param stock
	 * @return 成功或失败
	 */
	public int save(Stock stock){
		return getSqlSession().insert("stock.save", stock);
	}
	/**
	 * 
	 * <p>updateTaskId</p>
	 * <p>更新固件更新任务ID</p>
	 * @param inoutId 出入库ID
	 * @param taskID 固件任务ID
	 * @return 
	 */
	public int updateTaskId(long inoutId,long taskId){
		Map<String, Object> params=new HashMap<String, Object>(8);
		params.put("task_id", taskId);
		params.put("inout_id", inoutId);
		return getSqlSession().update("stock.updateTaskId", params);
	}
	/**
	 * 
	 * <p>updateStatusByInoutId</p>
	 * <p>更新设备固件更新状态</p>
	 * @param inoutId 出入库ID
	 * @param updateStatus 更新状态
	 * @return 
	 */
	public int updateStatusByInoutId(long inoutId,String updateStatus){
		Map<String, Object> params=new HashMap<String, Object>(8);
		params.put("update_status", updateStatus);
		params.put("inout_id", inoutId);
		return getSqlSession().update("stock.updateStatusByInoutId", params);
	}
	/**
	 * 
	 * <p>updateTaskIdByEquiNo</p>
	 * <p> 根据设备号更新固件更新任务ID</p>
	 * @param equipmentNo 设备号
	 * @param taskId 固件任务ID
	 * @param updateStatus 更新状态
	 * @return 
	 */
	public int updateTaskIdByEquiNo(String equipmentNo,int taskId,String updateStatus){
		Map<String, Object> params=new HashMap<String, Object>(16);
		params.put("task_id", taskId);
		params.put("equipment_no", equipmentNo);
		params.put("update_status", updateStatus);
		return getSqlSession().update("stock.updateTaskIdByEquiNo", params);
	}
	/**
	 * 
	 * <p>updateByEquiNo</p>
	 * <p> 根据设备号更新设备的健康状态、出入库标识、备注、激活状态</p>
	 * @param stock
	 * @return 
	 */
	public int updateByEquiNo(Stock stock){
		return getSqlSession().update("stock.updateByEquiNo", stock);
	}
	/**
	 * 
	 * <p>update</p>
	 * <p>更新设备库存信息</p>
	 * @param stock
	 * @return 成功或失败
	 */
	public int update(Stock stock){
		return getSqlSession().update("stock.update", stock);
	}
	/**
	 * 
	 * <p>updateHealthyState</p>
	 * <p>更新设备健康状态信息</p>
	 * @param equipmentNo 设备号
	 * @param healthyState 健康状态
	 * @return 成功或失败
	 */
	public int updateHealthyState(String equipmentNo,String healthyState) {
		Map<String, Object> params=new HashMap<String, Object>(8);
		params.put("equipment_no", equipmentNo);
		params.put("healthyState", healthyState);
		return getSqlSession().update("stock.updateHealthyState", params);
	}
	/**
	 * 
	 * <p>queryByEquipNo</p>
	 * <p>根据设备号查询设备库存信息</p>
	 * @param equipmentNo 设备号
	 * @return 设备库存信息
	 */
	public Stock queryByEquipNo(String equipmentNo){
		return getSqlSession().selectOne("stock.queryByEquipNo", equipmentNo);
	}
	/**
	 * 
	 * <p>isLagal</p>
	 * <p>判断设备是否合法</p>
	 * @param equipmentNo 设备号
	 * @return 
	 */
	public int isLagal(String equipmentNo){
		return getSqlSession().selectOne("stock.isLagal", equipmentNo);
	}
}
