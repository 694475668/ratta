package com.ratta.suponote.business.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.business.model.Dealers;
import com.ratta.suponote.business.result.DealersRseult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * 经销商管理服务
 * 2018-10-31
 */
public interface DealersService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询经销商信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加经销商信息</p>
	 * @param dealers 经销商信息
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	DealersRseult save(Dealers dealers,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>update</p>
	 * <p>更新经销商信息</p>
	 * @param dealers 经销商信息
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	DealersRseult update(Dealers dealers ,SessionInfo sessionInfo);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除经销商信息</p>
	 * @param id 经销商id
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	DealersRseult delete(Long id,SessionInfo sessionInfo);

	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询经销商信息</p>
	 * @param id id
	 * @return 经销商信息
	 */
	Dealers query(Long id);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>导入经销商信息</p>
	 * @param dealers 经销商
	 * @param sessionInfo 操作员信息模型
	 * @return 结果
	 */
	String save(List<Dealers> dealers, SessionInfo sessionInfo);
	
	/**
	 * 
	 * <p>querySum</p>
	 * <p>不分页查询经销商信息</p>
	 * @param params 参数
	 * @return 经销商信息
	 */
	List<Dealers> querySum(Map<String, Object> params);
}
