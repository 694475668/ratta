package com.ratta.suponote.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.app.model.AppGrayDeploy;

/**
 * @author page
 * app版本灰度交互层
 * 2019-02-07
 */
@Repository("appGrayDeployDao")
public class AppGrayDeployDao extends SqlSessionDaoSupport{
	/**
	 * 
	 * <p>save</p>
	 * <p>新增app版本灰度信息</p>
	 * @param AppGrayDeploy 实体
	 * @return 结果
	 */
	public int save(AppGrayDeploy AppGrayDeploy){
		return getSqlSession().insert("appGrayDeploy.save", AppGrayDeploy);
	}
	
	/**
	 * 
	 * <p>deleteByParam</p>
	 * <p>根据参数删除app灰度记录</p>
	 * @param appId appId
	 * @param userId userId
	 * @return 结果
	 */
	public int deleteByParam(long appId,String userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("userId", userId);
		return getSqlSession().delete("appGrayDeploy.deleteByParam", params);
	}
	
	/**
	 * 
	 * <p>updateByAppId</p>
	 * <p>修改app版本灰度数据状态</p>
	 * @param appId appId
	 * @return 结果
	 */
	public int updateByAppId(long appId){
		return getSqlSession().update("appGrayDeploy.updateByAppId", appId);
	}

	
}
