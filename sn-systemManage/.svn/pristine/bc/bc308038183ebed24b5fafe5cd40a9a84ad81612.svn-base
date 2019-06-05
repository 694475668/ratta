package com.ratta.suponote.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.model.system.Tresource;
import com.ratta.suponote.model.system.Trole;
/**
 * @author page
 * 资源操作持久层
 * 2018-10-31
 */
@Repository("resourceDao")
public class ResourceDao extends SqlSessionDaoSupport{
   static final String SPLIT_SIGN = ",";
	/**
	 * 
			* <p>find</p>
			* <p>根据参数查询资源集合</p>
			* @param params 参数map集合
			* params参数名称:
			* 	resourceTypeId  资源类型ID
			* 	userId  用户ID
			* @return 资源集合
	 */
	public List<Tresource> find(Map<String, Object> params) {
		List<Tresource> list= getSqlSession().selectList("resource.queryAllResource",params);
		return list;
	}
	/**
	 * 
			* <p>getResourceByRole</p>
			* <p>根据角色id查询资源对象集合</p>
			* @param role_id 角色id
	 * @param locale 
			* @return 资源集合
	 */
	public List<Tresource> getResourceByRole(String roleId, Locale locale) {
		Map<String, Object> params  = new HashMap<String, Object>(8);
		params.put("role_id", roleId);
		params.put("locale", locale);
		return getSqlSession().selectList("resource.getResourceByRole", params);
	}
	/**
	 * 
			* <p>update</p>
			* <p>更新资源对象</p>
			* @param tresource  资源对象
			* @return 受影响的记录数
	 */
	public int update(Tresource tresource){
		return getSqlSession().update("resource.update", tresource);
	}
	/**
	 * 
			* <p>save</p>
			* <p>保存资源对象</p>
			* @param tresource 资源对象
			* @return 受影响的记录数
	 */
	public int save(Tresource tresource){
		 return  getSqlSession().insert("resource.insert", tresource);
	}
	/**
	 * 
			* <p>delete</p>
			* <p>删除资源对象</p>
			* @param tresource 资源对象
			* @return 删除的记录数
	 */
	public int delete(Tresource tresource) {
		int count=0;
		//删除资源数据
		 count+=getSqlSession().delete("resource.delete",tresource);
		 //删除资源相对应的角色记录
		 // 该角色下如果有资源，则不允许删除当前资源
//		 count+=getSqlSession().delete("resource.deleteResRole",tresource);
		return count;
	}
	
	/**
	 * 
			* <p>hashRoleOwnResources</p>
			* <p>判断资源是否被某个角色所拥有</p>
			* @param resourceId 资源ID
			* @return 是否有角色拥有当前资源
	 */
	public boolean hasRoleOwnResources(String resourceId){
		Map<String, Object> params = new HashMap<String, Object>(8) ;
		params.put("id", resourceId) ;
		Long count = getSqlSession().selectOne("resource.hasRoleOwnResource", params);
		return count != 0 ;
	}
	
	/**
	 * 
			* <p>get</p>
			* <p>根据ID 查询指定的资源对象</p>
			* @param 资源id
			* @return 资源对象
	 */
	public Tresource get(String id,Locale locale){
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("id", id);
		params.put("locale", locale);
		return  getSqlSession().selectOne("resource.getTresourceById", params);
	}
	/**
	 * 
			* <p>saveRoleResource</p>
			* <p>保存角色与资源的对应关系</p>
			* @param tresource 资源
			* @param troles 角色集合
			* @return 返回插入的记录数
	 */
	public int saveRoleResource(Tresource tresource,Set<Trole> troles){
		int count=0;
		for(Trole trole :troles){
			Map<String, Object> map = new HashMap<String, Object>(8);
			map.put("s_id", tresource.getId());
			map.put("r_id", trole.getId());
			count+=getSqlSession().insert("resource.saveRoleResource",map);
		}
		return count;
	}
	/**
	 * 
			* <p>findAllSeelctResource</p>
			* <p>根据资源id 查询指定的资源</p>
			* @param ids 资源id字符串,以逗号分隔开,每个id要用单引号括起来
	 * @param locale 
			* @return 资源集合
	 */
	public List<Tresource> querySelectResource(String ids, Locale locale) {
		List<String> idss=new ArrayList<String>();
		for (String string : ids.split(SPLIT_SIGN)) {
			idss.add(string);
		}
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("idss", idss);
		params.put("locale", locale);
		List<Tresource> list =getSqlSession().selectList("resource.selectResourceByIds", params);
		return list;
	}
	
	public Tresource getByURL(String resourceURL,Locale locale){
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("resourceURL", resourceURL);
		params.put("locale", locale);
		return  getSqlSession().selectOne("resource.getTresourceByURL", params);
	}
}