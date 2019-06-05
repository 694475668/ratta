package com.ratta.suponote.app.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.app.model.AppFixPoint;

/**
 * @author page
 * app修复点数据交互层
 * 2019-02-07
 */
@Repository("appFixPointDao")
public class AppFixPointDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询app修复点</p>
	 * @param params 查询参数
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return app修复点列表
	 */
	public List<AppFixPoint> query(Map<String, Object> params){
		return getSqlSession().selectList("appFixPoint.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计app修复点信息</p>
	 * @param params 查询参数
	 * @return app修复点统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("appFixPoint.count", params);
	}
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加app修复点</p>
	 * @param params app修复点
	 * @return 结果
	 */
	public int save(AppFixPoint appFixPoint){
		return getSqlSession().insert("appFixPoint.save", appFixPoint);
	}
	/**
	 * 
	 * <p>update</p>
	 * <p>修改app修复点</p>
	 * @param params app修复点
	 * @return 结果
	 */
	public int update(AppFixPoint appFixPoint){
		return getSqlSession().update("appFixPoint.update", appFixPoint);
	}
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询app修复点信息</p>
	 * @param id
	 * @return app修复点信息
	 */
	public AppFixPoint query(Long id){
		return getSqlSession().selectOne("appFixPoint.queryById", id);
	}
	
	/**
	 * 
	 * <p>deleteFixPointByAppId</p>
	 * <p>根据appId删除修复点</p>
	 * @return 结果
	 */
	public int deleteFixPointByAppId(Long appId){
		return getSqlSession().delete("appFixPoint.deleteFixPointByAppId",appId);
	}
}
