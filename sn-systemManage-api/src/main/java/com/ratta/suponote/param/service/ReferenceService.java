package com.ratta.suponote.param.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.param.model.Reference;
import com.ratta.suponote.param.result.ReferenceResult;

/**
 * @author page
 * 系统参数明细服务
 * 2018-10-31
 */
public interface ReferenceService {
	/**
	 * 
			* <p>query</p>
			* <p>根据查询条件查询系统参数详细信息</p>
			* @param ph 分页帮助类
			* @param params 查询条件
			* @return easyui datagrid 数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>save</p>
			* <p>添加系统参数详细信息</p>
			* @param reference 系统参数详细信息
			* @param sessionInfo 操作员信息模型
			* @return 参数详细返回枚举
	 */
	ReferenceResult save(Reference reference,SessionInfo sessionInfo);
	/**
	 * 
			* <p>queryById</p>
			* <p>根据id查询系统参数详细信息</p>
			* @param id 系统参数详细信息id
			* @return 系统参数详细信息
	 */
	Reference queryById(Long id);
	/**
	 * 
			* <p>update</p>
			* <p>更新系统参数详细信息</p>
			* @param reference 系统参数详细信息
			* @param sessionInfo 操作员信息模型
			* @return 参数详细返回枚举
	 */
	ReferenceResult update(Reference reference,SessionInfo sessionInfo);
	
	/**
	 * 
			* <p>delete</p>
			* <p>根据id删除系统参数详细信息</p>
			* @param id 参数明细id
			* @param sessionInfo 操作员信息模型
			* @return 参数明细操作结果枚举
	 */
	ReferenceResult delete(Long id,SessionInfo sessionInfo);
	
	/**
	 * queryParam<br>
	 * 根据条件查询系统参数<br>
	 * @param serial 参数编码
	 * @param name 参数名称
	 * @return 参数子类
	 */
	List<Reference> queryParam(String serial, String name);
}

	