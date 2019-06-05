package com.ratta.suponote.system.service;

import java.util.List;

import com.ratta.suponote.model.pagemodel.Resource;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;

/**
 * @author page
 * 资源管理服务
 * 2018-10-31
 */
public interface ResourceService {
	/**
	 * 
			* <p>tree</p>
			* <p>获取所有菜单类型的资源树列表</p>
			* @param sessionInfo 用户sessionInfo
			* @return 资源树列表
	 */
	List<Tree> tree(SessionInfo sessionInfo);
	/**
	 * 
			* <p>allTree</p>
			* <p>根据资源类型获取所有的资源树列表</p>
			* @param sessionInfo 用户sessionInfo
			* @param resourceType 资源类型ID(为空表示所有)
			* @return 资源树列表
	 */
	List<Tree> allTree(SessionInfo sessionInfo,String resourceType);
	/**
	 * 
			* <p>allTree</p>
			* <p>获取所有的资源树列表</p>
			* @param sessionInfo 用户sessionInfo
			* @return 资源树列表
	 */
	List<Tree> allTree(SessionInfo sessionInfo);
	/**
	 * 
			* <p>treeGrid</p>
			* <p>根据资源名获取资源列表</p>
			* @param source_name 资源名(模糊查询)
			* @param sessionInfo 用户sessionInfo
			* @return 资源列表
	 */
	List<Resource> treeGrid(String source_name,SessionInfo sessionInfo);
	/**
	 * 
			* <p>add</p>
			* <p>添加资源</p>
			* @param resource 要添加的资源
			* @param sessionInfo 用户sessionInfo
			* @return 返回受影响的记录数
	 */
	int add(Resource resource, SessionInfo sessionInfo);
	/**
	 * 
			* <p>delete</p>
			* <p>根据ID删除资源</p>
			* @param id 资源ID
			* @param sessionInfo 操作员信息
			* @return 返回受影响的记录数
			* @throws Exception 异常
	 */
	int delete(String id,SessionInfo sessionInfo) throws Exception;

	/**
	 * 
			* <p>edit</p>
			* <p>修改资源</p>
			* @param resource 要修改的资源
			* @param sessionInfo 用户sessionInfo
			* @return 返回受影响的记录数
	 */
	int edit(Resource resource, SessionInfo sessionInfo);
	/**
	 * 
			* <p>get</p>
			* <p>根据资源ID获取资源</p>
			* @param id 资源ID
			* @param sessionInfo 操作员信息
			* @return 资源对象
	 */
	Resource get(String id,SessionInfo sessionInfo);

	/**
	 * 
			* <p>getByName</p>
			* <p>根据资源Name获取资源</p>
			* @param resourceURL 资源名称
			* @param sessionInfo 操作员信息
			* @return 资源对象
	 */
	Resource getByURL(String resourceURL,SessionInfo sessionInfo) ;

}
