package com.ratta.suponote.system.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.User;
import com.ratta.suponote.model.system.Tuser;

/**
 * @author page
 * 用户服务类
 * 2018-10-31
 */
public interface UserService {
	/**
	 * 
			* <p>login</p>
			* <p>用户登录</p>
			* @param user 要登陆的用户
			* @return 登陆的用户
	 */
	User login(User user);
	
	
	/**
	 * 
			* <p>errorLogin</p>
			* <p>输入错误密码，更新数据库错误密码标志</p>
			* @param user 要查看的用户
			* @return 影响数据库的行数
	 */
	int errorLogin(User user) ;
	
	/**
	 * 
			* <p>isLocked</p>
			* <p>查看指定用户是否被锁定</p>
			* @param user 要查看的用户
			* @return true 已锁定 false 未锁定
	 */
	boolean isLocked(User user) ;
	/**
	 * 
			* <p>dataGrid</p>
			* <p>加载用户主页面datagrid</p>
			* @param params 参数
			* @param ph 分页用的模型
			* @return 页面easyui  datagrid模型
	 */
	DataGrid dataGrid(PageHelper ph,Map<String, Object> params);
	/**
	 * 
			* <p>add</p>
			* <p>添加用户</p>
			* @param user 要添加的用户
			* @param sessionInfo 用户sessionInfo
			* @throws Exception 异常
	 */
	void add(User user,SessionInfo sessionInfo) throws Exception;
	/**
	 * 
	 * getUserByName
	 * 根据用户名查询用户(不区分大小写)
	 * @param username 用户名
	 * @return 用户实体
	 */
	Tuser getUserByName(String username);
	/**
	 * 
			* <p>get</p>
			* <p>根据ID获取用户</p>
			* @param id 用户ID
			* @return 用户
	 */
	User get(Long id);
	/**
	 * 
			* <p>edit</p>
			* <p>修改用户信息</p>
			* @param user 要修改的用户
			* @param sessionInfo 用户sessionInfo
			* @throws Exception 异常
	 */
	void edit(User user,SessionInfo sessionInfo) throws Exception;
	/**
	 * 
			* <p>delete</p>
			* <p>根据ID删除用户</p>
			* @param id 用户ID
	        * @return 返回受影响的行数
	 */
	int delete(Long id);
	/**
	 * 
			* <p>grant</p>
			* <p>授权用户</p>
			* @param ids 用户id列表(给多个用户授权)
			* @param user 授权的用户(角色在user为roleIds)
			* @param sessionInfo 操作员信息
	        * @return 受影响的行数
	 */
	int grant(String ids, User user,SessionInfo sessionInfo);
	/**
	 * 
			* <p>resourceList</p>
			* <p>获取用户能访问的资源列表</p>
			* @param id 用户id
			* @param sessionInfo 操作员信息
			* @return 资源列表集合
	 */
	List<String> resourceList(Long id,SessionInfo sessionInfo);
	/**
	 * 
			* <p>editPwd</p>
			* <p>修改用户密码</p>
			* @param user 用户
	 */	
	void editPwd(User user);
	/**
	 * 
			* <p>editCurrentUserPwd</p>
			* <p>用户自己修改密码吗</p>
			* @param sessionInfo 用户sessionInfo
			* @param oldPwd 旧密码
			* @param pwd 新密码
			* @return 0 改密成功,1旧密码错误 2 与最近N次密码相同
	 */
	int editCurrentUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd);
	/**
	 * 
			* <p>freshErrorLogin</p>
			* <p>清除用户密码输错次数</p>
			* @param user 用户信息 
			* @return 1清除成功 0 清除失败
	 */
	int freshErrorLogin(User user);
	
	/**
	 * 
			* <p>start</p>
			* <p>启用</p>
			* @param id 用户id
			* @param sessionInfo 操作员信息模型
			* @return 1 启用成功 0 启用失败
	 */
	int start(Long id,SessionInfo sessionInfo);
	/**
	 * 
			* <p>stop</p>
			* <p>停用</p>
			* @param id 用户id
			* @param sessionInfo 操作员信息模型 
			* @return 1 停用成功 0 停用失败
	 */
	int stop(Long id,SessionInfo sessionInfo);
	/**
	 * 
			* <p>unlock</p>
			* <p>解锁</p>
			* @param id 用户id
			* @param sessionInfo 操作员信息模型
			* @return 1 解锁成功 0 解锁失败
	 */
	int unlock(Long id,SessionInfo sessionInfo);
	/**
	 * 查询用户列表
	 * @param ids id数组
	 * @return 用户集合
	 */
	List<User> getByIds(String[] ids) ;
	
	/**
	 * 查询剩余密码登录次数
	 * @param id id
	 * @return 错误次数
	 */
	long selectErrorCounts(Long id);
}

