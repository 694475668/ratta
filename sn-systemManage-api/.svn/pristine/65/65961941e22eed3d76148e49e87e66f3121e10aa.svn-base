package com.ratta.suponote.stock.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponote.stock.result.StockRseult;

/**
 * @author page
 * 设备库存服务
 * 2018-10-31
 */
public interface StockService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备库存信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>querySum</p>
	 * <p>不分页查询设备库存信息</p>
	 * @param params 参数
	 * @return 库存信息
	 */
	List<Stock> querySum(Map<String, Object> params);
	/**
	 * 
	 * <p>queryEquipmentByInOutId</p>
	 * <p>根据出入库ID查询设备库存信息</p>
	 * @param inout_id 出入库ID
	 * @return 库存信息
	 */
	List<Stock> queryEquipmentByInOutId(long inout_id);
	/**
	 * 
	 * <p>queryStockNotRelease</p>
	 * <p>查询未发布固件任务的设备</p>
	 * @param params 参数
	 * @param ph 分页帮助类
	 * @return 库存信息
	 */
	DataGrid queryStockNotRelease(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>queryEquipmentNoByTaskId</p>
	 * <p>根据任务ID查询设备号信息</p>
	 * @param task_id 任务ID
	 * @return 设备号
	 */
	List<String> queryEquipmentNoByTaskId(long task_id);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询设备库存信息</p>
	 * @param id id
	 * @return 库存信息
	 */
	Stock query(Long id);
	/**
	 * 
	 * <p>isLagal</p>
	 * <p>根据设备号查询设备库存信息</p>
	 * @param equipment_no 设备号
	 * @return 库存信息
	 */
	int isLagal(String equipment_no);
	/**
	 * 
	 * <p>countByTaskID</p>
	 * <p>根据任务ID查询所有的设备</p>
	 * @param task_id 任务ID
	 * @return 数量
	 */
	int countByTaskID(int task_id);
	/**
	 * 
	 * <p>queryByEquipNo</p>
	 * <p>根据设备号查询设备库存信息</p>
	 * @param equipment_no 设备号
	 * @return 设备库存信息
	 */
	Stock queryByEquipNo(String equipment_no);
	/**
	 * 
	 * <p>queryTaskIdByInOutId</p>
	 * <p>通过出入库ID查询任务ID</p>
	 * @param inout_id 出入库ID
	 * @return task_id
	 */
	List<Long> queryTaskIdByInOutId(long inout_id);
	/**
	 * 
	 * <p>returnStock</p>
	 * <p>退货</p>
	 * @param equipmentReturnRecord 退换货记录实体
	 * @param sessionInfo session
	 * @return 处理结果
	 */
	StockRseult returnStock(EquipmentReturnRecord equipmentReturnRecord, SessionInfo sessionInfo);
	/**
	 * 
	 * <p>exchangeStock</p>
	 * <p>换货</p>
	 * @param equipmentReturnRecord 退换货记录实体
	 * @param sessionInfo session
	 * @return 处理结果
	 */
	StockRseult exchangeStock(EquipmentReturnRecord equipmentReturnRecord, SessionInfo sessionInfo);
	/**
	 * 
	 * <p>healthyState</p>
	 * <p>修改健康状态</p>
	 * @param equipmentReturnRecord 退换货记录实体
	 * @param sessionInfo session
	 * @return 处理结果
	 */
	StockRseult healthyState(EquipmentReturnRecord equipmentReturnRecord, SessionInfo sessionInfo);
}
