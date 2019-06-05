package com.ratta.suponote.usersn.service;

import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.usersn.model.SNUser;
import com.ratta.suponote.usersn.result.SnUserResult;
/**
 * 
		* <p>Title: SNUserService</p>
		* <p>Description: SN用户服务类</p>
 */
public interface SNUserService {
	/**
	 * 
			* <p>dataGrid</p>
			* <p>加载用户主页面datagrid</p>
			* @param ph 分页用的模型
			* @param params 参数
			* @return 页面easyui  datagrid模型
	 */
	DataGrid dataGrid(PageHelper ph,Map<String, Object> params);
	
	/**
	 * 
			* <p>updateIsNormal</p>
			* <p>冻结/解冻</p>
			* @param userid 用户ID
			* @param isNormal 是否冻结
			* @return 1 成功 0 失败
	 */
	SnUserResult updateIsNormal(String userid,String isNormal);
	/**
	 * 
			* <p>get</p>
			* <p>根据ID获取用户</p>
			* @param userid 用户ID
			* @return 用户
	 */
	SNUser get(String userid);
	/**
	 * 
			* <p>updateAccount</p>
			* <p>修改账户</p>
			* @param snuser 用户实体
			* @param sessionInfo 操作员信息模型
			* @return 返回枚举
	 */
	SnUserResult updateAccount(SNUser snuser ,SessionInfo sessionInfo);
	/**
	 * 
			* <p>unBund</p>
			* <p>解绑</p>
			* @param id id
			* @param sessionInfo 操作员信息模型
			* @return 返回枚举
	 */
	SnUserResult unBund(Long id,SessionInfo sessionInfo);
	/**
	 * getUsernameByEquipmentNo
	 *   根据设备号查询用户账号
	 * @param equipment_number 设备号
	 * @return 用户账号
	 */
	String getUsernameByEquipmentNo(String equipment_number);
}

