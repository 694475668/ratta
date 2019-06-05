package com.ratta.suponote.business.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.business.model.EquipType;
import com.ratta.suponote.business.result.EquipTypeRseult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * 设备型号管理服务
 * 2018-10-31
 */
public interface EquipTypeService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询设备型号信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加设备型号信息</p>
	 * @param equipType 设备型号信息
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	EquipTypeRseult save(EquipType equipType,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>update</p>
	 * <p>更新设备型号信息</p>
	 * @param equipType 设备型号信息
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	EquipTypeRseult update(EquipType equipType ,SessionInfo sessionInfo);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除设备型号信息</p>
	 * @param id 设备型号id
	 * @param sessionInfo 用户sessionInfo
	 * @return 返回枚举
	 */
	EquipTypeRseult delete(Long id,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询设备型号信息</p>
	 * @param id id
	 * @return 设备型号信息
	 */
	EquipType query(Long id);
	/**
	 * 
	 * <p>queryNoPage</p>
	 * <p>不分页查询设备型号信息</p>
	 * @return 设备型号信息
	 */
	List<EquipType> queryNoPage();
	
}
