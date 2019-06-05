package com.ratta.suponote.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ratta.suponote.model.system.Tresource;
import com.ratta.suponote.model.system.Trole;
import com.ratta.suponotes.util.BaseUtil;
/**
 * @author page
 * 角色管理持久层
 * 2018-10-31
 */
@Repository("roleDao")
public class RoleDao extends SqlSessionDaoSupport {
	/**
	 * 
			* <p>find</p>
			* <p>根据用户id获取角色列表</p>
			* @param userId 用户ID
			* @param child 是否查询出子角色
			* @param locale 语言 
			* @return 角色列表
	 */
	public List<Trole> find(String userId,String child, String name, Locale locale) {
		logger.info("userid:"+userId);
		Map<String, Object> params = new HashMap<String, Object>(32);
		if(!StringUtils.isEmpty(userId)){
			params.put("user_id", userId);
		}
		params.put("child", child);
		params.put("name", BaseUtil.orclFuzzyQueryParam(name));
		params.put("locale", locale);
		List<Trole> list= getSqlSession().selectList("role.getAllRole", params);
		return list;
	}
	
	/**
	 * 
			* <p>get</p>
			* <p>根据角色ID获取角色</p>
			* @param id 角色ID
	 * @param locale 
			* @return 角色
	 */
	public Trole get(String id, Locale locale){
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("id", id);
		params.put("locale", locale);
		 return getSqlSession().selectOne("role.getRoleById",params);
	}
	/**
	 * 
			* <p>save</p>
			* <p>保存角色对象</p>
			* @param trole 角色
			* @return 受影响的数据库记录数
	 */
	public int save(Trole trole) {
		return getSqlSession().insert("role.insert", trole);
	}
	/**
	 * 
			* <p>saveUserRoles</p>
			* <p>保存用户与角色对应关系</p>
			* @param user_id 用户ID
			* @param role_id 角色ID
			* @return 数据库受影响的记录数
	 */
	public int saveUserRoles(String userId, String roleId) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("u_id", userId);
		params.put("r_id",roleId);
		return getSqlSession().insert("role.saveUserRole", params);
	}
	/**
	 * 
			* <p>delete</p>
			* <p>删除角色</p>
			* @param trole 要删除的角色
			* @return 数据库受影响的记录数
	 */
	public int delete(Trole trole) {
		int count=0;
		//删除角色与资源对应关系
		count+= getSqlSession().delete("role.deleteRoleResource",trole);
		//删除角色与人员的对应关系
		// 如果该角色下有人员信息，则不允许删除
//		count+= getSqlSession().delete("role.deleteRoleUser", trole);
		//删除角色信息
		 count += getSqlSession().delete("role.delete", trole);
		return count;
	}
	
	/**
	 * 
			* <p>hasUser</p>
			* <p>此角色下是否有用户</p>
			* @param roleId 角色 ID
			* @return 当前角色下是否有用户存在
	 */
	public boolean hasUser(String roleId){
		Map<String, Object> params = new HashMap<String, Object>(8) ;
		params.put("id", roleId) ;
		Long count = getSqlSession().selectOne("role.hasUser", params) ;
		System.out.println("hasUser.count = " + count );
		return count != 0 ;
	}
	
	/**
	 * 
			* <p>update</p>
			* <p>修改角色信息</p>
			* @param trole 要保存的角色
			* @return  数据库受影响的记录数
	 */
	public int update(Trole trole) {
		return getSqlSession().update("role.update", trole);
	}
	/**
	 * 
			* <p>updateRoleResource</p>
			* <p>更新角色与资源的对应关系</p>
			* @param trole 角色
			* @return 数据库受影响的记录数
	 */
	public int updateRoleResource(Trole trole) {
		//1,先删除该角色下对应的所有资源对应关系
		//2,保存新的角色资源对应关系
		int count =getSqlSession().delete("role.deleteRoleResource",trole);
		if( trole.getTresources()!=null){
			for (Tresource tresource : trole.getTresources()) {
				Map<String, Object> params = new HashMap<String, Object>(8);
				params.put("trole", trole);
				params.put("tresource", tresource);
				count+= getSqlSession().insert("role.insertRoleResourcce",params);
			}
		}
		return count;
	}
}
