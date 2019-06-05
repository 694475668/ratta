package com.ratta.suponote.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.dao.system.ResourceDao;
import com.ratta.suponote.dao.system.RoleDao;
import com.ratta.suponote.model.pagemodel.Role;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;
import com.ratta.suponote.model.system.Tresource;
import com.ratta.suponote.model.system.Trole;

/**
 * @author page 角色管理业务类 2018-10-31
 */
@Service("roleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceBean implements RoleService {
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(RoleServiceBean.class);
	private static final String SPLIT_SIGN = ",";
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

	@Override
	@Transactional
	public int add(Role role, SessionInfo sessionInfo) {
		logger.info("调用添加角色:{}", role);
		int count = 0;
		Trole t = new Trole();
		BeanUtils.copyProperties(role, t);
		if (role.getPid() != null && !role.getPid().equalsIgnoreCase("")) {
			t.setTrole(roleDao.get(role.getPid(), sessionInfo.getLocale()));
		}
		t.setCreate_user(sessionInfo.getId());
		count += roleDao.save(t);
		logger.info("添加角色成功");
		return count;
	}

	@Override
	public Role get(String id, SessionInfo sessionInfo) {
		logger.info("调用根据角色id:{},获取角色", id);
		Role r = new Role();
		Trole t = roleDao.get(id, Locale.SIMPLIFIED_CHINESE);
		if (t != null) {
			BeanUtils.copyProperties(t, r);
			if (t.getTrole() != null) {
				r.setPid(t.getTrole().getId());
				r.setPname(t.getTrole().getName());
			}
			List<Tresource> s = t.getTresources();
			if (s != null && !s.isEmpty()) {
				boolean b = false;
				String ids = "";
				String names = "";
				for (Tresource tr : s) {
					if (b) {
						ids += ",";
						names += ",";
					} else {
						b = true;
					}
					ids += tr.getId();
					names += tr.getName();
				}
				r.setResourceIds(ids);
				r.setResourceNames(names);
			}
		} else {
			logger.warn("没有获取到角色,id:{}", id);
		}
		logger.info("根据id:{}获取角色:{}成功", id, r);
		return r;
	}

	@Override
	@Transactional
	public int edit(Role role, SessionInfo sessionInfo) {
		logger.info("调用编辑角色:{}", role);
		int count = 0;
		Trole t = roleDao.get(role.getId(), sessionInfo.getLocale());
		if (t != null) {
			BeanUtils.copyProperties(role, t);
			t.setModify_user(sessionInfo.getId());
			count += roleDao.update(t);
			logger.info("更新角色:{}成功", t);
		} else {
			logger.warn("没有找到角色:{}", role);
		}
		return count;
	}

	@Override
	public List<Role> treeGrid(SessionInfo sessionInfo, Role role) {
		logger.info("调用加载角色管理grid");
		List<Role> rl = new ArrayList<Role>();
		List<Trole> tl = null;
		tl = roleDao.find(null, null, role.getName(), sessionInfo.getLocale());

		if (tl != null && tl.size() > 0) {
			for (Trole t : tl) {
				Role r = new Role();
				BeanUtils.copyProperties(t, r);
				r.setIconCls("status_online");
				if (t.getTrole() != null) {
					r.setPid(t.getTrole().getId());
					r.setPname(t.getTrole().getName());
				}
				List<Tresource> s = t.getTresources();
				if (s != null && !s.isEmpty()) {
					boolean b = false;
					String ids = "";
					String names = "";
					for (Tresource tr : s) {
						if (b) {
							ids += ",";
							names += ",";
						} else {
							b = true;
						}
						ids += tr.getId();
						names += tr.getName();
					}
					r.setResourceIds(ids);
					r.setResourceNames(names);
				}
				rl.add(r);
			}
			logger.info("查询角色信息成功,总记录数:{}", rl.size());
		} else {
			logger.warn("角色列表为空");
		}
		return rl;
	}

	@Override
	@Transactional
	public int delete(String id, SessionInfo sessionInfo) throws Exception {
		logger.info("调用删除角色(id:{})信息", id);
		Trole t = roleDao.get(id, sessionInfo.getLocale());
		logger.info("删除角色(id:{})成功", id);
		if (hasUserBelowThisRole(id)) {
			logger.info("角色下尚有用户，不允许删除");
			throw new Exception(RoleServiceMessage.DEL_ERROR_HAS_USER.getMessage(sessionInfo.getLocale().getCountry()));
		}
		return roleDao.delete(t);
	}

	private boolean hasUserBelowThisRole(String roleId) {
		return roleDao.hasUser(roleId);
	}

	@Override
	public List<Tree> tree(SessionInfo sessionInfo) {
		logger.info("调用查询角色树结构");
		List<Trole> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		String userId = null;
		l = roleDao.find(userId, userId, null, sessionInfo.getLocale());
		if (l != null && l.size() > 0) {
			for (Trole t : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(t, tree);
				tree.setText(t.getName());
				tree.setIconCls("status_online");
				if (t.getTrole() != null) {
					tree.setPid(t.getTrole().getId());
				}
				lt.add(tree);
			}
			logger.info("获取角色树列表成功,总记录数:{}", lt.size());
		} else {
			logger.warn("角色树列表为空");
		}
		return lt;
	}

	@Override
	public List<Tree> allTree() {
		logger.info("获取所有角色树");
		return this.tree(null);
	}

	@Override
	@Transactional
	public int grant(Role role, SessionInfo sessionInfo) {
		logger.info("调用给角色:{}授权资源", role);
		Trole t = roleDao.get(role.getId(), sessionInfo.getLocale());
		List<Tresource> resources = null;
		if (role.getResourceIds() != null && !role.getResourceIds().equalsIgnoreCase("")) {
			String ids = "";
			boolean b = false;
			for (String id : role.getResourceIds().split(SPLIT_SIGN)) {
				if (b) {
					ids += ",";
				} else {
					b = true;
				}
				ids += "'" + id + "'";
			}
			// 1,查询出所有选择的资源对象
			resources = resourceDao.querySelectResource(ids, sessionInfo.getLocale());
			// 2,将资源对象添加到角色对象的tresources属性里面
			logger.info("角色:{}授权资源id:{}", t, ids);
			t.setTresources(new ArrayList<Tresource>(resources));
		} else {
			logger.warn("角色:{}授权资源为空", t);
			t.setTresources(null);
		}
		// 3,更新资源与角色的对应关系
		logger.info("角色{}授权资源成功", t);
		return roleDao.updateRoleResource(t);
	}

	@Override
	public List<Role> queryByUser(SessionInfo sessionInfo) {
		String userId = sessionInfo.getId();
		List<Trole> troles = roleDao.find(userId, userId, null, sessionInfo.getLocale());
		List<Role> roles = new ArrayList<Role>();
		for (Trole role : troles) {
			Role r = new Role();
			BeanUtils.copyProperties(role, r);

			roles.add(r);
		}
		return roles;
	}

}
