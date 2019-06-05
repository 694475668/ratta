package com.ratta.suponote.app.service;

import java.util.Map;
import com.ratta.suponote.app.model.AppFixPoint;
import com.ratta.suponote.app.result.AppFixPointResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * app修复点服务
 * 2018-10-31
 */
public interface AppFixPointService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询app修复点信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>save</p>
	 * <p>添加app修复点信息</p>
	 * @param appFixPoint 实体
	 * @param sessionInfo sessionInfo
	 * @return 结果
	 */
	AppFixPointResult save(AppFixPoint appFixPoint,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>update</p>
	 * <p>修改app修复点信息</p>
	 * @param appFixPoint 实体
	 * @param sessionInfo sessionInfo
	 * @return 结果
	 */
	AppFixPointResult update(AppFixPoint appFixPoint,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询app修复点信息</p>
	 * @param id id
	 * @return app修复点信息
	 */
	AppFixPoint query(Long id);
	
}
