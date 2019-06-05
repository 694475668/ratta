package com.ratta.suponote.param.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.param.model.Dictionary;

/**
 * @author page
 * 数据字典管理持久层
 * 2018-10-31
 */
@Repository("dictionaryDao")
public class DictionaryDao extends SqlSessionDaoSupport{

	
	/**
	 * 
			* <p>query</p>
			* <p>查询数据字典</p>
			* @param params 查询参数
			* p_begin 分页开始页(必填)
			* p_end 分页结束页(必填)
			* @return 数据字典列表
	 */
	public List<Dictionary> query(Map<String, Object> params){
		return getSqlSession().selectList("dictionary.query", params);
	}
	/**
	 * 
			* <p>queryNoPage</p>
			* <p>不分页查询数据字典</p>
			* @param params 查询参数
			* @return 数据字典列表
	 */
	public List<Dictionary> queryNoPage(String name){
		return getSqlSession().selectList("dictionary.queryNoPage", name);
	}
	/**
	 * 
			* <p>count</p>
			* <p>统计数据字典信息</p>
			* @param params 查询参数
			* @return 数据字典统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("dictionary.count", params);
	}
	/**
	 * 
			* <p>save</p>
			* <p>添加数据字典信息</p>
			* @param  数据字典信息
			* @return 数据库影响行数
	 */
	public int save(Dictionary dictionary){
		return getSqlSession().insert("dictionary.save", dictionary);
	}
	/**
	 * 
			* <p>query</p>
			* <p>根据id查询信息</p>
			* @param id
			* @return 数据字典信息
	 */
	public Dictionary query(Long id){
		return getSqlSession().selectOne("dictionary.queryById", id);
	}
	/**
	 * 
			* <p>query</p>
			* <p>根据id查询信息</p>
			* @param id
			* @return 数据字典信息
	 */
	public Dictionary queryByPamram(Map<String, Object> params){
		return getSqlSession().selectOne("dictionary.queryByPamram", params);
	}
	/**
	 * 
			* <p>update</p>
			* <p>更新数据字典信息</p>
			* @param 数据字典信息
			* @return 数据库影响行数
	 */
	public int update(Dictionary dictionary){
		return getSqlSession().update("dictionary.update", dictionary);
	}
	/**
	 * 
			* <p>delete</p>
			* <p>删除数据字典信息</p>
			* @param code 数据字典信息
			* @return 数据库影响行数
	 */
	public  int delete(Long id){
		return getSqlSession().delete("dictionary.delete", id);
	}
}

	