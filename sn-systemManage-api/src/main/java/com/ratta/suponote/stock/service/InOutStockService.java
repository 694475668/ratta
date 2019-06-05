package com.ratta.suponote.stock.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.model.InOutStock;
import com.ratta.suponote.stock.result.InOutStockRseult;

/**
 * @author page
 * 出入库管理服务
 * 2018-10-31
 */
public interface InOutStockService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询出入库记录信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>queryBatchNo</p>
	 * <p>查询批次号记录信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid queryBatchNo(PageHelper ph,Map<String, Object> params);
	
	/**
	 * 
	 * <p>in</p>
	 * <p>添加入库记录</p>
	 * @param inOutStock 入库记录
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	InOutStockRseult in(InOutStock inOutStock,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>out</p>
	 * <p>添加出库记录</p>
	 * @param inOutStock 出库记录
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	InOutStockRseult out(List<InOutStock> inOutStock ,SessionInfo sessionInfo);
	
	/**
	 * 
	 * <p>undoInOutStock</p>
	 * <p>撤销出入库</p>
	 * @param id 出入库记录id
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	InOutStockRseult undoInOutStock(Long id,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>queryByInoutId</p>
	 * <p>根据出入库ID查询剩余库存记录和第一个设备号</p>
	 * @param inout_id inout_id
	 * @param flag 出入库标识
	 * @return 出入库记录
	 */
	InOutStock queryByInoutId(Long inout_id,String flag);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询出入库记录</p>
	 * @param id id
	 * @return 出入库记录
	 */
	InOutStock query(Long id);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>导入记录</p>
	 * @param inOutStocks 出入库记录
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	InOutStockRseult save(List<InOutStock> inOutStocks, SessionInfo sessionInfo);
	
	/**
	 * 
	 * <p>querySum</p>
	 * <p>不分页查询出入库记录</p>
	 * @param params 参数
	 * @return 出入库记录
	 */
	List<InOutStock> querySum(Map<String, Object> params);
	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件版本列表版本</p>
	 * @param params 参数
	 * @return 出入库记录
	 */
	List<InOutStock> query(Map<String, Object> params);
	/**
	 * 
	 * <p>queryEquipmentPurpose</p>
	 * <p>根据批次号查询设备用途</p>
	 * @param batch_no 批次号
	 * @return 记录
	 */
	List<InOutStock> queryEquipmentPurpose(String batch_no);

}
