package com.ratta.suponote.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.model.system.Tresourcetype;

/**
 * @author page
 * 资源类型持久层
 * 2018-10-31
 */
@Repository("resourceTypeDaoImpl")
public class ResourceTypeDao extends SqlSessionDaoSupport{
	/**
	 * 
			* <p>getById</p>
			* <p>根据ID获取资源类型</p>
			* @param id 资源类型ID
			* @return 资源类型
	 */
	public Tresourcetype getById(String id) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("id", id);
		return getSqlSession().selectOne("resourcetype.getById",params);
	}
	/**
	 * 
			* <p>findAll</p>
			* <p>查询所有的资源类型</p>
			* @return 资源类型列表
	 */
	public List<Tresourcetype> findAll(){
		return getSqlSession().selectList("resourcetype.findAllResourceType");
	}
	
}