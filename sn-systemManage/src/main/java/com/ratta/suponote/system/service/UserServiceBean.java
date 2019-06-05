package com.ratta.suponote.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.suponote.dao.system.PwdHisDao;
import com.ratta.suponote.dao.system.ResourceDao;
import com.ratta.suponote.dao.system.RoleDao;
import com.ratta.suponote.dao.system.UserDao;
import com.ratta.suponote.dao.system.maint.AuditDao;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.User;
import com.ratta.suponote.model.system.PwdHis;
import com.ratta.suponote.model.system.Tresource;
import com.ratta.suponote.model.system.Trole;
import com.ratta.suponote.model.system.Tuser;
import com.ratta.suponote.model.system.maint.Audit;
import com.ratta.suponote.param.dao.ReferenceDao;
import com.ratta.suponotes.util.BaseUtil;
import com.ratta.suponotes.util.MD5Util;

/**
 * @author page 用户管理业务类 2018-10-31
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceBean implements UserService {
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(UserServiceBean.class);
	private static final String SPLIT_SIGN = ",";
	private static final String ROLE_IDS = "roleIds";
	private static final String USER_NAME = "username";
	/**
	 * 用户管理持久层dao
	 */
	@Autowired
	private UserDao userDao;
	/**
	 * 用户改密历史持久层dao
	 */
	@Autowired
	private PwdHisDao pwdHisDao;
	/**
	 * 参数表
	 */
	@Autowired
	private ReferenceDao referenceDao;

	/**
	 * 角色管理持久层dao
	 */
	@Autowired
	private RoleDao roleDao;

	/**
	 * 资源管理持久层dao
	 */
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private AuditDao auditDao;

	@Override
	public User login(User user) {
		logger.info("调用用户登录,用户:{}", user);
		Tuser t = userDao.getUserByName1(user.getUsername());
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			logger.info("用户登录成功,用户:{}", t);
			return user;
		}
		logger.warn("用户登录失败,没有该用户信息.");
		return null;
	}

	@Override
	public int errorLogin(User user) {
		logger.info("调用更新用户:{}密码输错次数", user);
		int result = userDao.errorLogin(user.getId());
		logger.info("更新用户[id:{}]密码输错次数成功,数据库影响行数:{}", result);
		return result;
	}

	@Override
	public boolean isLocked(User user) {
		logger.info("调用检查用户:{}是否被锁定", user);
		long count = userDao.getLocked(user.getId());
		if (count > 0) {
			int result = userDao.lockUser(user.getId());
			logger.info("锁定用户[id:{}]成功,数据库影响行数:{}", user.getId(), result);
		} else {
			logger.info("用户[id:{}]没有被锁定");
		}
		return count > 0;
	}

	@Override
	@Transactional
	public int freshErrorLogin(User user) {
		logger.info("调用清除用户:{}密码输错次数", user);
		int result = userDao.freshErrorLogin(user.getId());
		logger.info("清除用户[id:{}]密码成功,数据库影响行数:{}", user.getId(), result);
		return result;
	}

	@Override
	public DataGrid dataGrid(PageHelper ph, Map<String, Object> params) {
		logger.info("调用加载用户管理界面,查询参数:{},分页参数:{}", BaseUtil.map2String(params), ph);
		DataGrid dg = new DataGrid();
		List<User> ul = new ArrayList<User>();
		if (params == null) {
			params = new HashMap<String, Object>(64);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		if (params.get(USER_NAME) != null) {
			params.put("username", BaseUtil.orclFuzzyQueryParam(params.get(USER_NAME)));
		}
		if (params.get(ROLE_IDS) != null) {
			params.put("roleIds", params.get(ROLE_IDS));
		}
		List<Tuser> l = userDao.find(params);
		if (l != null && l.size() > 0) {
			for (Tuser t : l) {
				User u = new User();
				BeanUtils.copyProperties(t, u);
				Set<Trole> roles = t.getTroles();
				if (roles != null && !roles.isEmpty()) {
					String roleIds = "";
					String roleNames = "";
					boolean b = false;
					for (Trole tr : roles) {
						if (b) {
							roleIds += ",";
							roleNames += ",";
						} else {
							b = true;
						}
						roleIds += tr.getId();
						roleNames += tr.getName();
					}
					u.setRoleIds(roleIds);
					u.setRoleNames(roleNames);
				}
				ul.add(u);
			}
		}
		dg.setRows(ul);
		dg.setTotal(userDao.count(params));
		logger.info("查询用户数据成功,总记录数:{}", dg.getTotal());
		return dg;
	}

	@Override
	@Transactional
	synchronized public void add(User user, SessionInfo sessionInfo) throws Exception {
		logger.info("调用添加用户:{}", user);

		Tuser u = new Tuser();
		if (user.getPwd() == null || "".equals(user.getPwd())) {
			logger.info("没有输入密码,使用默认密码:{}", "123456");
			user.setPwd(MD5Util.md5("123456"));
		}
		BeanUtils.copyProperties(user, u);

		Long id = userDao.getMaxSEQ();

//			u.setPwd(MD5Util.md5(user.getPwd()));
		u.setId(id);
		u.setIsactive("Y");
		u.setStatus("1");
		u.setCounts(0L);
		u.setCreatedby(sessionInfo.getUsername());
		userDao.save(u);

		grant(Long.toString(id), user, sessionInfo);
		logger.info("添加用户:{}成功", u);
	}

	@Override
	public User get(Long id) {
		logger.info("调用根据id:{}获取用户", id);
		Tuser t = userDao.get(id);
		User u = new User();
		BeanUtils.copyProperties(t, u);
		if (t.getTroles() != null && !t.getTroles().isEmpty()) {
			String roleIds = "";
			String roleNames = "";
			boolean b = false;
			for (Trole role : t.getTroles()) {
				if (b) {
					roleIds += ",";
					roleNames += ",";
				} else {
					b = true;
				}
				roleIds += role.getId();
				roleNames += role.getName();
			}
			u.setRoleIds(roleIds);
			u.setRoleNames(roleNames);
		}
		logger.info("根据id:{}成功获取用户信息:{}", id, u);
		return u;

	}

	@Override
	@Transactional
	synchronized public void edit(User user, SessionInfo sessionInfo) throws Exception {
		logger.info("调用修改用户:{}信息", user);
		String newPwd = user.getPwd();
		Tuser u = userDao.get(user.getId());
		if (u != null) {
			String oldPwd = u.getPwd();
			BeanUtils.copyProperties(user, u);
			u.setUpdatedby(sessionInfo.getUsername());
			if (!StringUtils.isEmpty(oldPwd) && !newPwd.equals(oldPwd)) {
				u.setModify_pwd("0");
			}
			userDao.update(u);
			logger.info("修改用户:{}信息成功", u);
		} else {
			logger.error("用户:{}不存在", user);
			throw new Exception("用户不存在");
		}
	}

	@Override
	@Transactional
	public int delete(Long id) {
		List<Audit> audits = auditDao.queryByOpUser(id);
		logger.info("获取用户:{}下面的操作记录", audits);
		if (audits == null || audits.isEmpty()) {
			logger.info("用户id:" + id);
			logger.info("准备删除:{}", id);
			userDao.deleteUserRoles(id);
			return userDao.delete(id);
		}
		return 0;
	}

	@Override
	@Transactional
	public int grant(String ids, User user, SessionInfo sessionInfo) {
		logger.info("调用给用户授权角色,被授权用户id:{},授权的用户:{}", ids, user);
		if (ids != null && ids.length() > 0) {
			List<Trole> roles = new ArrayList<Trole>();
			if (user.getRoleIds() != null) {
				for (String roleId : user.getRoleIds().split(SPLIT_SIGN)) {
					roles.add(roleDao.get(roleId, sessionInfo.getLocale()));
				}
			}
			List<Tuser> users = new ArrayList<Tuser>();
			for (String id : ids.split(SPLIT_SIGN)) {
				if (id != null && !id.equalsIgnoreCase("")) {
					Tuser t = userDao.get(Long.parseLong(id));
					t.setUpdatedby(sessionInfo.getUsername());
					users.add(t);
					t.setTroles(new HashSet<Trole>(roles));
				}
			}
			logger.info("给用户授权角色成功");
			return userDao.updateUsersRoles(users);
		}
		logger.warn("没有要授权角色的用户,ids:{}", ids);
		return 0;
	}

	@Override
	public List<String> resourceList(Long id, SessionInfo sessionInfo) {
		logger.info("调用获取用户(id:{})能访问的资源列表", id);
		List<String> resourceList = new ArrayList<String>();
		Tuser t = userDao.get(id);

		if (t != null) {
			Set<Trole> roles = t.getTroles();
			if (roles != null && !roles.isEmpty()) {
				for (Trole role : roles) {
					System.out.println(sessionInfo.getLocale());
					List<Tresource> lists = resourceDao.getResourceByRole(role.getId(), sessionInfo.getLocale());
					Set<Tresource> resources = new HashSet<Tresource>();
					for (Tresource tresource : lists) {
						resources.add(tresource);
					}
					if (resources != null && !resources.isEmpty()) {
						for (Tresource resource : resources) {
							if (resource != null && resource.getUrl() != null) {
								resourceList.add(resource.getUrl());
							}
						}
					}
				}
			}
			logger.info("获取用户(id:{})能访问的资源列表:{}", id, resourceList);
		} else {
			logger.warn("没有查询到用户");
		}
		return resourceList;
	}

	@Override
	@Transactional
	public void editPwd(User user) {
		if (user != null && !StringUtils.isEmpty(user.getPwd())) {
			Tuser u = userDao.get(user.getId());

			if (!u.getPwd().equals(MD5Util.md5(user.getPwd()))) {
				u.setCounts(0L);
				u.setStatus("1");
				u.setModify_pwd("0");
			}
			u.setPwd(MD5Util.md5(user.getPwd()));
			userDao.update(u);
		}
	}

	@Override
	@Transactional
	public int editCurrentUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd) {
		logger.info("调用修改当前用户密码");
		Tuser u = userDao.get(Long.parseLong(sessionInfo.getId()));
		Map<String, Object> params = new HashMap<String, Object>(16);
		String val = referenceDao.queryByParamCode("01", "SAME_PASS_COUNTS").get(0).getValue();
		params.put("username", sessionInfo.getUsername());
		// 04参数名表示查询最近多少次改密
		params.put("times", Integer.parseInt(val));
		List<PwdHis> pwdHis = pwdHisDao.query(params);
		for (PwdHis pwdHis2 : pwdHis) {
			if (pwdHis2.getPwd().equals(MD5Util.md5(pwd))) {
				return 2;
			}
		}
		if (u.getPwd().equalsIgnoreCase(MD5Util.md5(oldPwd))) {
			u.setPwd(MD5Util.md5(pwd));
			u.setModify_pwd("1");
			userDao.update(u);
			logger.info("修改密码成功");
			PwdHis his = new PwdHis();
			his.setPwd(MD5Util.md5(pwd));
			his.setUsername(sessionInfo.getUsername());
			pwdHisDao.save(his);
			return 0;
		}
		logger.info("修改密码失败");
		return 1;
	}

	@Override
	@Transactional
	public int start(Long id, SessionInfo sessionInfo) {
		logger.info("启用用户,id:{}", id);
		Tuser tuser = userDao.get(id);
		int result = 0;
		if (tuser != null) {
			tuser.setStatus("1");
			tuser.setCounts(0L);
			tuser.setUpdatedby(sessionInfo.getUsername());
			result = userDao.update(tuser);
			logger.info("启用用户成功,数据库影响行数:{}", result);
		} else {
			logger.error("没有id:{}的用户信息", id);
		}
		return result > 0 ? 1 : 0;
	}

	@Override
	@Transactional
	public int stop(Long id, SessionInfo sessionInfo) {
		logger.info("停用用户,id:{}", id);
		Tuser tuser = userDao.get(id);
		int result = 0;
		if (tuser != null) {
			tuser.setStatus("2");
			tuser.setUpdatedby(sessionInfo.getUsername());
			result = userDao.update(tuser);
			logger.info("停用用户信息成功,数据库影响行数:{}", result);
		} else {
			logger.error("没有id:{}的用户信息", id);
		}
		return result > 0 ? 1 : 0;
	}

	@Override
	@Transactional
	public int unlock(Long id, SessionInfo sessionInfo) {
		logger.info("解锁用户,id:{}", id);
		Tuser tuser = userDao.get(id);
		int result = 0;
		if (tuser != null) {
			tuser.setCounts(0L);
			tuser.setStatus("1");
			tuser.setUpdatedby(sessionInfo.getUsername());
			result = userDao.update(tuser);
			logger.info("解锁用户信息成功,数据库影响行数:{}", result);
		} else {
			logger.error("没有id:{}的用户信息", id);
		}
		return result > 0 ? 1 : 0;
	}

	@Override
	public List<User> getByIds(String[] ids) {
		return userDao.getByIds(ids);
	}

	/**
	 * 查询剩余密码登录次数
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public long selectErrorCounts(Long id) {
		long result = userDao.selectErrorCounts(id);
		logger.info("查询剩余密码登录次数", result);
		return result;
	}

	@Override
	public Tuser getUserByName(String username) {
		return userDao.getUserByName(username);
	}
}
