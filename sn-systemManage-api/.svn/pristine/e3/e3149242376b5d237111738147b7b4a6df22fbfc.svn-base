package com.ratta.suponote.app.service;

import java.util.List;
import java.util.Map;
import com.ratta.suponote.app.model.AppVersion;
import com.ratta.suponote.app.result.AppVersionResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * app版本服务
 * 2018-10-31
 */
public interface AppVersionService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询app版本信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>queryAllAppVersion</p>
	 * <p>查询app名称列表</p>
	 * @return app名称列表
	 */
	List<String> queryAllAppVersion();
	/**
	 * 
	 * <p>queryVersionByAppName</p>
	 * <p>查询app版本列表</p>
	 * @return app版本列表
	 */
	List<String> queryVersionByAppName(String appName);
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除app版本</p>
	 * @param id id
	 * @param sessionInfo sessionInfo
	 * @return 结果
	 */
	AppVersionResult delete(Long id,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>deploy</p>
	 * <p>发布app版本</p>
	 * @param id id
	 * @return 结果
	 */
	AppVersionResult deploy(Long id);
	/**
	 * 
	 * <p>updateDeployFlagById</p>
	 * <p>修改app版本发布状态</p>
	 * @param id id
	 * @param deployFlag 发布状态
	 * @return 结果
	 */
	AppVersionResult updateDeployFlagById(Long id,String deployFlag);
	/**
	 * 
	 * <p>appGrayDeploy</p>
	 * <p>灰度</p>
	 * @param id id
	 * @param userid userid
	 * @return 结果
	 */
	AppVersionResult appGrayDeploy(long id,String userid);
	/**
	 * 
	 * <p>updateAuditingFlag</p>
	 * <p>修改app版本审核状态</p>
	 * @param appVersion appVersion
	 * @return 结果
	 */
	AppVersionResult updateAuditingFlag(AppVersion appVersion);
	/**
	 * 
	 * <p>save</p>
	 * <p>新增app版本</p>
	 * @param appVersion 实体
	 * @return 结果
	 */
	AppVersionResult save(AppVersion appVersion);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询app版本信息</p>
	 * @param id id
	 * @return app版本信息
	 */
	AppVersion query(Long id);
	/**
	 * 
	 * <p>query</p>
	 * <p>查询app版本</p>
	 * @param params 查询参数
	 * @return app版本列表
	 */
	List<AppVersion> query(Map<String, Object> params);
	
}
