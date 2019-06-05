package com.ratta.suponote.system.service;

import java.util.List;

import com.ratta.suponote.model.pagemodel.Role;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;

/**
 * @author page
 * 角色管理服务
 * 2018-10-31
 */
public interface RoleService {
	/**
	 * 
			* <p>add</p>
			* <p>添加角色</p>
			* @param role 要添加的角色
			* @param sessionInfo 用户sessionInfo
			* @return 返回受影响的记录数
	 */
	int  add(Role role, SessionInfo sessionInfo);
	/**
	 * 
			* <p>get</p>
			* <p>根据角色ID获取角色</p>
			* @param id 角色ID
			* @param sessionInfo 操作员信息
			* @return 角色
	 */
	Role get(String id,SessionInfo sessionInfo);

	/**
	 * 
			* <p>edit</p>
			* <p>编辑角色</p>
			* @param role 要保存修改过的角色
			* @param sessionInfo 用户sess
			* @return 返回受影响的记录数
	 */
	int edit(Role role,SessionInfo sessionInfo);
	/**
	 * 
			* <p>treeGrid</p>
			* <p>获取当前用户所拥有的角色</p>
			* @param sessionInfo 用户sessionInfo
			* @param role 角色
			* @return 角色列表
	 */
	
	List<Role> treeGrid(SessionInfo sessionInfo, Role role);

	/**
	 * 
			* <p>delete</p>
			* <p>根据ID删除角色</p>
			* @param id 角色ID
			* @param sessionInfo 操作员信息
			* @return 返回受影响的记录数
			* @throws Exception 异常
	 */
	int delete(String id,SessionInfo sessionInfo) throws Exception;
	/**
	 * 
			* <p>tree</p>
			* <p>获取角色树</p>
			* @param sessionInfo 用户sessionInfo
			* @return 角色树列表
	 */
	List<Tree> tree(SessionInfo sessionInfo);
	/**
	 * 
			* <p>allTree</p>
			* <p>获取所有的角色列表</p>
			* @return 角色树列表
	 */
	List<Tree> allTree();

	/**
	 * 
			* <p>grant</p>
			* <p>为角色授权</p>
			* @param role 角色
			* @param sessionInfo 用户sessionInfo
			* @return 返回受影响的记录数
	 */
	int  grant(Role role,SessionInfo sessionInfo);
	
	/**
	 * 
			* <p>queryByUser</p>
			* <p>根据用户id获取相应的角色信息</p>
			* @param sessionInfo 操作员信息
			* @return 用户角色list
	 */
	List<Role> queryByUser(SessionInfo sessionInfo);
	

}
