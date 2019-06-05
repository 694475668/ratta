package com.ratta.spnote.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.IpUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.OperateLogger.OperationType;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.Role;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;
import com.ratta.suponote.model.pagemodel.User;
import com.ratta.suponote.system.service.ResourceService;
import com.ratta.suponote.system.service.RoleService;
import com.ratta.suponote.system.service.UserService;
import com.ratta.suponotes.util.MD5Util;

/**
 * @author page 用户管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 用户管理service
	 */
	@Autowired
	private UserService userService;

	/**
	 * 角色管理service
	 */
	@Autowired
	private RoleService roleService;
	/**
	 * 资源管理service
	 */
	@Autowired
	private ResourceService resourceService;

	/**
	 * 
	 * <p>
	 * login
	 * </p>
	 * <p>
	 * 用户登录
	 * </p>
	 * 
	 * @param user    用户
	 * @param session HttpSession
	 * @param request HttpServletRequest
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	@OperateLogger(content = "user_login", operationType = OperateLogger.OperationType.LOGIN_IN)
	public Json login(User user, HttpSession session, HttpServletRequest request) {
		Json j = new Json();
		User u = userService.login(user);
		if (u != null) {
			if (this.isStoped(u.getId())) {
				j.setSuccess(false);
				j.setMsg(UserMessage.STATUS_STOP.getMessage());
				return j;
			}
			if (userService.isLocked(u)) {
				j.setSuccess(false);
				j.setMsg(UserMessage.STATUS_LOCK.getMessage());
				return j;
			}
			boolean changePwd = false;
			if ("0".equals(u.getModify_pwd())) {
				changePwd = true;
			}
			if (u.getPwd().equals(MD5Util.md5(user.getPwd()))) {
				BeanUtils.copyProperties(u, user);
				logger.info("用户登录成功,用户:{}", u);
				SessionInfo sessionInfo = new SessionInfo();
				sessionInfo.setId(u.getId().toString());
				sessionInfo.setName(u.getName());
				sessionInfo.setUsername(u.getUsername());
				sessionInfo.setLocale(Locale.SIMPLIFIED_CHINESE);

				List<Role> r = roleService.queryByUser(sessionInfo);
				StringBuffer sb = new StringBuffer();
				for (Role role : r) {
					sb.append(role.getId()).append(",");
				}
				if (sb.length() > 0) {
					sb.delete(sb.length() - 1, sb.length());
				}
				sessionInfo.setRoleId(sb.toString());
				sessionInfo.setIp(IpUtil.getIpAddr());
				sessionInfo.setResourceList(userService.resourceList(u.getId(), sessionInfo));
				session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
				session.setAttribute("changePwd", changePwd);
				j.setObj("{ \"id\" : \"" + sessionInfo.getId() + "\",\"name\" : \"" + sessionInfo.getName()
						+ "\",\"ip\":\"" + sessionInfo.getIp() + "\",  \"changePwd\" : \"" + changePwd + "\" }");
				userService.freshErrorLogin(u);
				j.setSuccess(true);
				j.setMsg(UserMessage.LOGIN_SUCCESS.getMessage());
				return j;
			} else {
				logger.warn(UserMessage.NAME_PWD_ERROR.getMessage());
				user.setId(u.getId());
				userService.errorLogin(user);
				long counts = userService.selectErrorCounts(u.getId());
				if (counts == 0) {
					userService.isLocked(u);
					j.setSuccess(false);
					j.setMsg(UserMessage.STATUS_LOCK.getMessage());
					return j;
				} else {
					j.setSuccess(false);
					j.setMsg(UserMessage.NAME_PWD_ERROR.getMessage(counts));
					return j;
				}
			}

		} else {
			j.setMsg(UserMessage.NAME_ERROR.getMessage());
		}
		return j;
	}

	private boolean isStoped(Long id) {
		User user = userService.get(id);
		return "2".equals(user.getStatus());
	}

	/**
	 * logout 用户退出
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	@OperateLogger(content = "user_logout", operationType = OperateLogger.OperationType.LOGIN_OU)
	public Json logout(HttpSession session) {
		Json j = new Json();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		j.setMsg(UserMessage.LOGOUT_SUCCESS.getMessage());
		return j;
	}

	/**
	 * manager 跳转到用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/user";
	}

	/**
	 * dataGrid 获取用户数据表格
	 * 
	 * @param user 查询用户
	 * @param ph   分页帮助类
	 * @return easyui grid数据模型
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(String username, String roleIds, String status, String modifydatetimeStart,
			String modifydatetimeEnd, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("username", username);
		params.put("roleIds", roleIds);
		params.put("status", status);
		params.put("modifydatetimeStart", modifydatetimeStart);
		params.put("modifydatetimeEnd", modifydatetimeEnd);
		params.put("sessionInfo", sessionInfo);
		return userService.dataGrid(ph, params);
	}

	/**
	 * addPage 跳转到添加用户页面
	 * 
	 * @param request httpServletRequest
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		User u = new User();
		request.setAttribute("user", u);
		return "/admin/userAdd";
	}

	/**
	 * add 添加用户
	 * 
	 * @param user    添加的用户对象
	 * @param session httpSession
	 * @return Json
	 */
	@RequestMapping("/add")
	@ResponseBody
	@OperateLogger(content = "user_add", operationType = OperateLogger.OperationType.C, table = "t_m_user")
	public Json add(User user, HttpSession session) {
		Json j = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());

			if (StringUtils.isEmpty(user.getRoleIds())) {
				throw new Exception(UserMessage.SELECT_RIGHT_ROLE.getMessage());
			}
			if (userService.getUserByName(user.getUsername()) != null) {
				logger.info("用户:{}已经存在", user);
				throw new Exception(UserMessage.USERNAME_EXIST.getMessage());
			}
			userService.add(user, sessionInfo);
			j.setSuccess(true);
			j.setMsg(UserMessage.ADD_SUCCESS.getMessage());
			j.setObj(user);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * editPage 跳转到修改用户页面
	 * 
	 * @param request HttPServletRequest
	 * @param id      用户id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		User u = userService.get(Long.parseLong(id));
		request.setAttribute("user", u);
		return "/admin/userEdit";
	}

	/**
	 * edit 修改用户
	 * 
	 * @param user    用户对象
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	@OperateLogger(content = "user_update", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json edit(User user, HttpSession session) {
		Json j = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			user.setModify_pwd("0");
			userService.edit(user, sessionInfo);
			j.setSuccess(true);
			j.setMsg(UserMessage.EDIT_SUCCESS.getMessage());
			j.setObj(user);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * delete 删除用户
	 * 
	 * @param id      用户id
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "user_delete", operationType = OperateLogger.OperationType.D, table = "t_m_user")
	public Json delete(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		if (id != null && !id.equalsIgnoreCase(sessionInfo.getId())) {
			int result = userService.delete(Long.parseLong(id));
			if (result > 0) {
				j.setMsg(UserMessage.DEL_SUCCESS.getMessage());
				j.setSuccess(true);
			} else {
				j.setMsg(UserMessage.DEL_EXISTS_AUDIT_ERROR.getMessage());
				j.setSuccess(false);
			}
		} else {
			j.setMsg(UserMessage.DEL_ME_ERROR.getMessage());
			j.setSuccess(false);
		}

		return j;
	}

	/**
	 * batchDelete 批量删除用户
	 * 
	 * @param ids     用户id字符串,用“,”隔开
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public Json batchDelete(String ids, HttpSession session) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					this.delete(id, session);
				}
			}
		}
		j.setMsg(UserMessage.BATCH_DEL_SUCCESS.getMessage());
		j.setSuccess(true);
		return j;
	}

	/**
	 * grantPage 跳转到用户授权页面
	 * 
	 * @param ids     用户id字符串,用“,”隔开
	 * @param names   用户名称字符串,用“,”隔开
	 * @param request HttpServletRequest
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(String ids, String usernames, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("ids", ids);
		StringBuffer sb = new StringBuffer();
		for (String id : ids.split(",")) {
			if (!StringUtils.isEmpty(id)) {
				User user = userService.get(Long.parseLong(id));
				if (ids.split(",").length == 1) {
					request.setAttribute("user", user);
				}
				if (user != null) {
					sb.append(user.getUsername()).append(",");
				}
			}
		}
		if (sb.length() > 0) {
			sb = sb.deleteCharAt(sb.length() - 1);
		}
		request.setAttribute("usernames", sb.toString());

		return "/admin/userGrant";
	}

	/**
	 * grant 授权用户
	 * 
	 * @param ids  被授权的用户id字符串,用“,”隔开
	 * @param user 授权的用户
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	// 用户管理[授权]
	@OperateLogger(content = "user_grant", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json grant(String ids, User user, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		userService.grant(ids, user, sessionInfo);
		j.setSuccess(true);
		j.setMsg(UserMessage.GRANT_SUCCESS.getMessage());
		return j;
	}

	/**
	 * editPwdPage 跳转到修改密码页面
	 * 
	 * @param id      要修改密码的用户id
	 * @param request HTTPServletRequest
	 * @return
	 */
	@RequestMapping("/editPwdPage")
	public String editPwdPage(String id, HttpServletRequest request) {
		User u = userService.get(Long.parseLong(id));
		request.setAttribute("user", u);
		return "/admin/userEditPwd";
	}

	/**
	 * editPwd 修改用户密码
	 * 
	 * @param user 用户
	 * @return
	 */
	@RequestMapping("/editPwd")
	@ResponseBody
	@OperateLogger(content = "user_modify_pwd", operationType = OperationType.U, table = "t_m_user")
	public Json editPwd(Long id, String pwd) {
		Json j = new Json();
		User user = userService.get(id);
		user.setPwd(pwd);
		userService.editPwd(user);
		j.setSuccess(true);
		j.setMsg(UserMessage.EDIT_SUCCESS.getMessage());
		return j;
	}

	/**
	 * editCurrentUserPwdPage 跳转到修改自己的密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/editCurrentUserPwdPage")
	public String editCurrentUserPwdPage(HttpServletRequest request, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			request.setAttribute("msg", UserMessage.NO_SESSION.getMessage());
			return "/error/noSession";
		}
		request.setAttribute("sessionInfo", sessionInfo);
		return "/user/userEditPwd";
	}

	/**
	 * editCurrentUserPwd 修改自己的密码
	 * 
	 * @param session HttpSession
	 * @param oldPwd  原始密码
	 * @param pwd     新密码
	 * @return
	 */
	@RequestMapping("/editCurrentUserPwd")
	@ResponseBody
	@OperateLogger(content = "user_cpanel_modify_pwd", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json editCurrentUserPwd(Long id, HttpSession session, String oldPwd, String pwd) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			j.setMsg(UserMessage.NO_SESSION.getMessage());
			return j;
		}
		int result = userService.editCurrentUserPwd(sessionInfo, oldPwd, pwd);
		if (result == 0) {
			session.setAttribute("changePwd", false);
			j.setSuccess(true);
			j.setMsg(UserMessage.EDIT_CURRENT_PWD_SUCCESS.getMessage());
			return j;
		} else {
			j.setSuccess(false);
			j.setMsg(result == 1 ? UserMessage.OLD_PWD_ERROR.getMessage() : UserMessage.OLD_NEW_PWD_LIKE.getMessage());
		}
		return j;
	}

	/**
	 * currentUserRolePage 跳转到显示用户角色页面
	 * 
	 * @param request HttpServleteRequest
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping("/currentUserRolePage")
	public String currentUserRolePage(HttpServletRequest request, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			request.setAttribute("msg", UserMessage.NO_SESSION.getMessage());
			return "/error/noSession";
		}
		List<Role> l = roleService.queryByUser(sessionInfo);
		List<Tree> lt = new ArrayList<Tree>();
		if (l != null && l.size() > 0) {
			for (Role r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				tree.setText(r.getName());
				tree.setIconCls("status_online");
				tree.setPid(r.getPid());
				lt.add(tree);
			}
		}
		request.setAttribute("userRoles", JSON.toJSONString(lt));
		return "/user/userRole";
	}

	/**
	 * currentUserResourcePage 跳转到显示用户资源页面
	 * 
	 * @param request HttpServleteRequest
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping("/currentUserResourcePage")
	public String currentUserResourcePage(HttpServletRequest request, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			request.setAttribute("msg", UserMessage.NO_SESSION.getMessage());
			return "/error/noSession";
		}
		request.setAttribute("userResources", JSON.toJSONString(resourceService.allTree(sessionInfo)));
		return "/user/userResource";
	}

	/**
	 * 解锁
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/unlock")
	@ResponseBody
	@OperateLogger(content = "user_unlock", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json unlock(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			userService.unlock(id, sessionInfo);
			j.setSuccess(true);
			j.setMsg(UserMessage.UNLOCK_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 启用
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/open")
	@ResponseBody
	@OperateLogger(content = "user_open", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json open(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		if (id != null && !id.equalsIgnoreCase(sessionInfo.getId())) {
			userService.start(Long.parseLong(id), sessionInfo);
			j.setSuccess(true);
			j.setMsg(UserMessage.START_SUCCESS.getMessage());
		} else {
			j.setMsg(UserMessage.START_ME_ERROR.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 批量启用
	 * 
	 * @param ids
	 * @param session
	 * @return
	 */
	@RequestMapping("/batchOpen")
	@ResponseBody
	@OperateLogger(content = "user_batch_open", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json batchOpen(String ids, HttpSession session) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					this.open(id, session);
				}
			}
		}
		j.setMsg(UserMessage.BATCH_START_SUCCESS.getMessage());
		j.setSuccess(true);
		return j;
	}

	/**
	 * 停用
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/close")
	@ResponseBody
	@OperateLogger(content = "user_stop", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json close(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		if (id != null && !id.equalsIgnoreCase(sessionInfo.getId())) {
			userService.stop(Long.parseLong(id), sessionInfo);
			j.setSuccess(true);
			j.setMsg(UserMessage.STOP_SUCCESS.getMessage());
		} else {
			j.setMsg(UserMessage.STOP_ME_ERROR.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 批量停用
	 * 
	 * @param ids
	 * @param session
	 * @return
	 */
	@RequestMapping("/batchClose")
	@ResponseBody
	@OperateLogger(content = "user_batch_stop", operationType = OperateLogger.OperationType.U, table = "t_m_user")
	public Json batchClose(String ids, HttpSession session) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					this.close(id, session);
				}
			}
		}
		j.setMsg(UserMessage.BATCH_STOP_SUCCESS.getMessage());
		j.setSuccess(true);
		return j;
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public User getBean(Long id) {
		return userService.get(id);
	}

	/**
	 * 批量操作 在选择一条数据的情况
	 * 
	 * @param id
	 * @return
	 */
	public User getBean(String id) {
		return userService.get(Long.parseLong(id));
	}

	public User[] getBean(String[] ids) {
		User[] users = new User[ids.length];
		for (int i = 0; i < users.length; i++) {
			users[i] = userService.get(Long.parseLong(ids[i]));
		}
		return users;
	}
}
