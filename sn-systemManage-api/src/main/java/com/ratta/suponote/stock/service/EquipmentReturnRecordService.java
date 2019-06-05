package com.ratta.suponote.stock.service;

import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;

/**
 * @author page
 * 设备退换货服务
 * 2018-10-31
 */
public interface EquipmentReturnRecordService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备退换货信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询设备退换货信息</p>
	 * @param id id
	 * @return 退换货信息
	 */
	EquipmentReturnRecord query(Long id);
}
