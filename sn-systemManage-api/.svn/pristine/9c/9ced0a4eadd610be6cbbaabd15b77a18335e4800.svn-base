package com.ratta.suponote.equipment.service;

import java.util.List;
import java.util.Map;
import com.ratta.suponote.equipment.model.EquipmentLog;
import com.ratta.suponote.equipment.result.EquipmentLogResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;

/**
 * @author page
 * 设备日志服务
 * 2018-10-31
 */
public interface EquipmentLogService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备日志信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询设备日志信息</p>
	 * @param id id
	 * @return 设备日志信息
	 */
	EquipmentLog query(Long id);
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据ID查询删除设备日志信息</p>
	 * @param id id
	 * @return 返回枚举
	 */
	EquipmentLogResult delete(Long id);
	/**
	 * <p>updateByParam</p>
	 * <p>根据ID更新下载标识、查看标识、备注</p>
	 * @param id id
	 * @param flag 查看标识
	 * @param isDownload 是否下载标识
	 * @param remark 备注
	 * @return 结果
	 */
	int updateByParam(Long id,String flag,String isDownload,String remark);
	/**
	 * 
			* <p>selectByDays</p>
			* <p>查询某个时间点之前的数据</p>
			* @param days_before 几天前的数据
			* @return 结果集
	 */
	List<EquipmentLog> selectByDays(Long days_before);
}
