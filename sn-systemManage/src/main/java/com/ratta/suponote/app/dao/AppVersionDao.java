package com.ratta.suponote.app.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.app.model.AppVersion;

/**
 * @author page
 * app版本数据交互层
 * 2019-02-07
 */
@Repository("appVersionDao")
public class AppVersionDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询app版本</p>
	 * @param params 查询参数
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return app版本列表
	 */
	public List<AppVersion> query(Map<String, Object> params){
		return getSqlSession().selectList("appVersion.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计app版本信息</p>
	 * @param params 查询参数
	 * @return app版本统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("appVersion.count", params);
	}
	
	
	/**
	 * 
	 * <p>queryAllAppVersion</p>
	 * <p>查询app名称列表</p>
	 * @return app名称列表
	 */
	public List<String> queryAllAppVersion(){
		return getSqlSession().selectList("appVersion.queryAllAppVersion");
	}
	/**
	 * 
	 * <p>queryVersionByAppName</p>
	 * <p>查询app版本列表</p>
	 * @return app版本列表
	 */
	public List<String> queryVersionByAppName(String appName){
		return getSqlSession().selectList("appVersion.queryVersionByAppName",appName);
	}
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询app版本信息</p>
	 * @param id
	 * @return app版本信息
	 */
	public AppVersion query(Long id){
		return getSqlSession().selectOne("appVersion.queryById", id);
	}
	/**
	 * 
	 * <p>save</p>
	 * <p>新增app版本信息</p>
	 * @param appVersion 实体
	 * @return 结果
	 */
	public int save(AppVersion appVersion){
		return getSqlSession().insert("appVersion.save", appVersion);
	}
	
	/**
	 * 
	 * <p>updateDeployFlagById</p>
	 * <p>修改app版本发布状态</p>
	 * @param params 参数
	 * @return 结果
	 */
	public int updateDeployFlagById(Map<String, Object> params){
		return getSqlSession().update("appVersion.updateDeployFlagById", params);
	}
	
	/**
	 * 
	 * <p>updateAuditingFlag</p>
	 * <p>修改app版本审核状态</p>
	 * @param appVersion appVersion
	 * @return 结果
	 */
	public int updateAuditingFlag(AppVersion appVersion){
		return getSqlSession().update("appVersion.updateAuditingFlag", appVersion);
	}
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>根据ID删除app版本</p>
	 * @return 结果
	 */
	public int delete(Long id){
		return getSqlSession().delete("appVersion.delete",id);
	}
	
}
