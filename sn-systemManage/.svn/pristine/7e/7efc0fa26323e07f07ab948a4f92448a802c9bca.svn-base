package com.ratta.suponote.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.model.pagemodel.User;
import com.ratta.suponote.model.system.Trole;
import com.ratta.suponote.model.system.Tuser;

/**
 * @author page
 * 用户管理持久层
 * 2018-10-31
 */
@Repository("userDao")
public class UserDao extends SqlSessionDaoSupport {
	/**
	 * find
	 * 根据参数查询用户
	 * @param params
	 *            参数map集合 params: p_begin 分页开始页 p_end 分页截止页 createdatetimeStart
	 *            查询(创建时间开始) createdatetimeEnd 查询(创建时间结束) modifydatetimeStart
	 *            查询(修改时间开始) modifydatetimeEnd 查询(修改时间结束) username 用户名(模糊查询)
	 * @return 用户列表
	 */
	public List<Tuser> find(Map<String, Object> params) {
		List<Tuser> users = getSqlSession().selectList("user.getAllUser",
				params);
		return users;
	}

	/**
	 * count
	 * 根据参数查询用户数
	 * @param params
	 *            参数map集合 params: username 用户名(模糊查询)
	 * @return 用户数
	 */
	public Long count(Map<String, Object> params) {
		return getSqlSession().selectOne("user.countAllUser", params);
	}

	/**
	 * get
	 * 根据用户名和密码查询用户
	 * @param params
	 *            参数map结合 params: user_name 用户名 pwd 密码
	 * @return 用户
	 */
	public Tuser get(Map<String, Object> params) {
		Tuser user = getSqlSession().selectOne("user.getUserByNameAndPWD",
				params);
		return user;
	}

	/**
	 * get
	 * 根据用户id查询用户
	 * @param id
	 *            用户ID
	 * @return 用户
	 */
	public Tuser get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>(8) ;
		params.put("id", id) ;
		Tuser user = getSqlSession().selectOne("user.getTUserById", params);
		return user;
	}

	/**
	 * getUserByName
	 * 根据用户名查询用户(不区分大小写)
	 * @param username
	 *            用户名
	 * @return
	 */
	public Tuser getUserByName(String username) {
		return getSqlSession().selectOne("user.getUserByUserName", username);
	}
	/**
	 * getUserByName
	 * 根据用户名查询用户(区分大小写)
	 * @param username
	 *            用户名
	 * @return
	 */
	public Tuser getUserByName1(String username) {
		return getSqlSession().selectOne("user.getUserByUserName1", username);
	}

	/**
	 * save
	 * 保存用户
	 * @param tuser
	 *            用户
	 * @return 受影响的记录数
	 */
	public int save(Tuser tuser) {
		return getSqlSession().insert("user.save", tuser);
	}
	
	/**
	 * getMaxSEQ
	 * 获取序列的最大值
	 * @return 最大的序列值
	 */
	public long getMaxSEQ(){
		return getSqlSession().selectOne("user.getMaxSEQ") ;
	}

	/**
	 * update
	 * 更新用户
	 * @param tuser
	 *            用户
	 * @return 受影响的记录数
	 */
	public int update(Tuser tuser) {
		System.out.println("updated tuser = " + tuser) ;
		return getSqlSession().update("user.update", tuser);
	}

	/**
	 * updateUsersRoles
	 * 更新用户角色的对应关系
	 * @param users
	 *            用户列表
	 * @return
	 */
	public int updateUsersRoles(List<Tuser> users) {
		// 1,先删除用户的角色关系
		// 2,保存新的角色对应关系
		int count = 0;
		count += getSqlSession().delete("user.deleteUserRoles", users);
		Map<String, Object> params = new HashMap<String, Object>(128);
		for (Tuser tuser : users) {
			params.put("u_id", tuser.getId());
			for (Trole trole : tuser.getTroles()) {
				params.put("r_id", trole.getId());
				count += getSqlSession().insert("user.insertUserRoles", params);
			}
			count += getSqlSession().update("user.update", tuser);
		}
		return count;
	}

	/**
	 * getLocked
	 * 根据用户id判断该用户是否锁定
	 * @param id
	 *            用户id
	 * @param 密码最大输错次数
	 * @return 0表示没有锁定,1表示锁定
	 */
	public long getLocked(Long id) {
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("id", id);
		params.put("serial", "01");
		params.put("name", "PASSWORD_COUNT");
		return getSqlSession().selectOne("user.selectLocked", params);
	}

	/**
	 * freshErrorLogin
	 * 清楚登陆密码输错次数
	 * @param id用户id
	 * @return 受影响的记录数
	 */
	public int freshErrorLogin(Long id) {
		return getSqlSession().update("user.freshErrorLogin", id);
	}
	
	/**
	 * delete
	 *  物理删除用户
	 * @param id
	 *            用户id
	 * @return 删除用户数量
	 */
	public int delete(Long id){
		return getSqlSession().delete("user.delete", id) ;
	}
	/**
	 * deleteUserRoles
	 * 物理删除用户
	 * @param id 用户id
	 * @return 删除用户角色数量
	 */
	public int deleteUserRoles(Long id){
		Tuser user=new Tuser();
		user.setId(id);
		List<Tuser> users=new ArrayList<Tuser>();
		users.add(user);
		return getSqlSession().delete("user.deleteUserRoles", users) ;
	}
	
	public List<User> getByIds(String[] ids){
		return getSqlSession().selectList("user.getByIds", ids) ;
	}

	/**
	 * 
			* <p>errorLogin</p>
			* <p>更新用户密码输错次数</p>
			* @param id 用户表id
			* @return 数据库影响行数
	 */
	public int errorLogin(Long id) {
		return getSqlSession().update("user.errorLogin", id);
	}
	/**
	 * 
			* <p>lockUser</p>
			* <p>锁定用户</p>
			* @param id
	 */
	public int lockUser(Long id) {
		return getSqlSession().update("user.lockUser", id);
	}
	
	/**
	 * 查询剩余密码登录次数
	 * @param id
	 * @return
	 */
	public long selectErrorCounts(Long id){
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("id", id);
		params.put("serial", "01");
		params.put("name", "PASSWORD_COUNT");
		return getSqlSession().selectOne("user.selectErrorCounts", params);
	}
}