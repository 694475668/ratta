package com.ratta.suponote.param.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.param.model.Reference;
/**
 * @author page
 * 系统参数管理持久层
 * 2018-10-31
 */
@Repository("referenceDao")
public class ReferenceDao extends SqlSessionDaoSupport{
	/**
	 * 
			* <p>save</p>
			* <p>添加系统参数明细信息</p>
			* @param reference 系统参数明细信息
			* @return 数据库影响行数
	 */
	public int save(Reference reference){
		return  getSqlSession().insert("reference.save",reference);
	}
	
	/**
	 * 
			* <p>queryById</p>
			* <p>根据id查询系统参数详细信息</p>
			* @param id 系统参数详细信息id
			* @return 系统参数详细信息
	 */
	public Reference queryById(Long id){
		return getSqlSession().selectOne("reference.queryById",id);
	}
	/**
	 * 
			* <p>update</p>
			* <p>更新系统参数详细信息</p>
			* @param reference 系统参数详细信息
			* @return 数据库影响行数
	 */
	public  int update(Reference reference){
		return getSqlSession().update("reference.update",reference);
	}
	/**
	 * 
			* <p>query</p>
			* <p>根据查询条件查询系统参数详细信息</p>
			* @param params 查询条件
			* refid 系统参数id
			* @return 系统参数详细信息列表
	 */
	public  List<Reference> query(Map<String, Object> params){
		return getSqlSession().selectList("reference.query",params);
	}
	/**
	 * 
			* <p>count</p>
			* <p>统计系统参数详细信息</p>
			* @param params 查询条件
			* refid 系统参数id
			* @return 系统参数详细信息统计数
	 */
	public  long count(Map<String, Object> params){
		return getSqlSession().selectOne("reference.count", params);
	}
	
	/**
	 * 
			* <p>queryByParamCode</p>
			* <p>根据业务码查询参数信息</p>
			* @param serial 参数编码
			* @param name  业务码
			* @return 參數信息
	 */
	public List<Reference> queryByParamCode(String serial,String name){
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("serial", serial);
		params.put("name", name);
		return getSqlSession().selectList("reference.queryByParamCode", params);
	}
	/**
	 * 
			* <p>delete</p>
			* <p>删除参数详细信息</p>
			* @param params 参数params
			* @return 数据库影响行数
	 */
	public int delete(Long id){
		return getSqlSession().delete("reference.deleteById",id);
	}
}

	