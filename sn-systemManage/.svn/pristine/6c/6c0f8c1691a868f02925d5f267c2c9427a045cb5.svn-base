package com.ratta.suponote.business.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.business.model.Dealers;

/**
 * @author page
 *
 * 2018-10-31
 */
@Repository("dealersDao")
public class DealersDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询经销商</p>
	 * @param params 查询参数
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 经销商列表
	 */
	public List<Dealers> query(Map<String, Object> params){
		return getSqlSession().selectList("dealers.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计经销商信息</p>
	 * @param params 查询参数
	 * @return 经销商统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("dealers.count", params);
	}
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加经销商信息</p>
	 * @param  经销商信息
	 * @return 数据库影响行数
	 */
	public int save(Dealers dealers){
		return getSqlSession().insert("dealers.insert", dealers);
	}
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新经销商信息</p>
	 * @param 经销商
	 * @return 数据库影响行数
	 */
	public int update(Dealers dealers){
		return getSqlSession().update("dealers.update", dealers);
	}
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除经销商信息</p>
	 * @param code 经销商信息
	 * @return 数据库影响行数
	 */
	public int delete(Long id){
		return getSqlSession().delete("dealers.delete", id);
	}
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询经销商信息</p>
	 * @param id
	 * @return 经销商信息
	 */
	public Dealers query(Long id){
		return getSqlSession().selectOne("dealers.queryById", id);
	}
	
	/**
	 * 
	 * <p>queryByDealersName</p>
	 * <p>根据名称查询经销商信息</p>
	 * @param dealersName
	 * @return 经销商信息
	 */
	public Dealers queryByDealersName(String dealersName) {
		return getSqlSession().selectOne("dealers.queryByDealersName", dealersName);
	}
	
	/**
	 * 
	 * <p>querySumAll</p>
	 * <p>查询所有的经销商</p>
	 * @param params 查询参数
	 * @return 经销商信息集合
	 */
	public List<Dealers> querySumAll(Map<String, Object> params){
		return getSqlSession().selectList("dealers.querySumAll",params);
	}
}
