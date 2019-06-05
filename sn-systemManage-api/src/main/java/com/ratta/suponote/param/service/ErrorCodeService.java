package com.ratta.suponote.param.service;

import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.param.model.ErrorCode;
import com.ratta.suponote.param.result.ErrorCodeResult;

/**
 * @author page
 * 错误码服务
 * 2018-10-31
 */
public interface ErrorCodeService {
	/**
	 * 
			* <p>query</p>
			* <p>查询错误码信息</p>
			* @param ph 分页帮助类
			* @param params 查询参数
			* @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>save</p>
			* <p>添加错误码信息</p>
			* @param errorCode 错误码信息
			* @param sessionInfo 操作员信息模型
			* @return 错误码返回枚举
	 */
	ErrorCodeResult save(ErrorCode errorCode,SessionInfo sessionInfo);
	/**
	 * 
			* <p>query</p>
			* <p>根据code查询错误码信息</p>
			* @param code 错误码code
			* @return 错误码信息
	 */
	ErrorCode query(String code);
	/**
	 * 
			* <p>update</p>
			* <p>更新错误码信息</p>
			* @param errorCode 错误码信息
			* @param sessionInfo 操作员信息模型
			* @return 错误码返回枚举
	 */
	ErrorCodeResult update(ErrorCode errorCode ,SessionInfo sessionInfo);
	/**
	 * 
			* <p>delete</p>
			* <p>删除错误码信息</p>
			* @param code 错误码code
			* @param sessionInfo 操作员信息模型
			* @return 错误码返回枚举
	 */
	 ErrorCodeResult delete(String code,SessionInfo sessionInfo);
}

	