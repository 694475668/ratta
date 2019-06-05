package com.ratta.suponote.param.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.param.model.Dictionary;
import com.ratta.suponote.param.result.DictionaryResult;

/**
 * @author page
 * 数据字典服务
 * 2018-10-31
 */
public interface DictionaryService {
	/**
	 * 
			* <p>query</p>
			* <p>查询数据字典信息</p>
			* @param ph 分页帮助类
			* @param params 查询参数
			* @return easyui datagrid数据模型
	 */
	
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 根据key值获取数据字典列表
	 * @param name 名称
	 * @return 字典集合
	 */
	List<Dictionary> queryDictionary(String name);
	/**
	 * 
			* <p>save</p>
			* <p>添加数据字典信息</p>
			* @param dictionary 数据字典信息
			* @param sessionInfo 操作员信息模型
			* @return 返回枚举
	 */
	DictionaryResult save(Dictionary dictionary,SessionInfo sessionInfo);
	/**
	 * 
			* <p>query</p>
			* <p>根据id查询数据字典信息</p>
			* @param id id
			* @return 数据字典信息
	 */
	Dictionary query(Long id);
	/**
	 * 
			* <p>update</p>
			* <p>更新数据字典信息</p>
			* @param dictionary 数据字典信息
			* @param sessionInfo 操作员信息模型
			* @return 返回枚举
	 */
	DictionaryResult update(Dictionary dictionary ,SessionInfo sessionInfo);
	/**
	 * 
			* <p>delete</p>
			* <p>删除数据字典信息</p>
			* @param id id
			* @param sessionInfo 操作员信息模型
			* @return 返回枚举
	 */
	 DictionaryResult delete(Long id,SessionInfo sessionInfo);
}

	