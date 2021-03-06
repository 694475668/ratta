package com.ratta.suponote.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ratta.suponote.dao.system.ResourceDao;
import com.ratta.suponote.dao.system.ResourceTypeDao;
import com.ratta.suponote.dao.system.UserDao;
import com.ratta.suponote.model.pagemodel.Resource;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;
import com.ratta.suponote.model.system.Tresource;
import com.ratta.suponote.model.system.Trole;
import com.ratta.suponote.model.system.Tuser;

/**
 * @author page 资源管理类 2018-10-31
 */
@Service("resourceService")
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceBean implements ResourceService {
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(ResourceServiceBean.class);
	/**
	 * 资源管理持久层dao
	 */
	@Autowired
	private ResourceDao resourceDao;
	/**
	 * 资源类型持久层dao
	 */
	@Autowired
	private ResourceTypeDao resourceTypeDao;
	/**
	 * 用户管理持久层dao
	 */
	@Autowired
	private UserDao userDao;

	@Override
	public List<Tree> tree(SessionInfo sessionInfo) {
		logger.info("调用查询所有菜单类型的资源列表,resourceTypeId:{}", 0);
		return allTree(sessionInfo, "0");
	}

	@Override
	public List<Tree> allTree(SessionInfo sessionInfo, String resourceType) {
		logger.info("调用查询资源列表,resourceType:{}", resourceType);
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>(16);
		if (sessionInfo != null) {
			logger.info("设置只查询当前用户的资源,userId:{}", sessionInfo.getId());
			params.put("locale", sessionInfo.getLocale());
			params.put("userId", sessionInfo.getId());
		}
		if (StringUtils.isNotEmpty(resourceType)) {
			params.put("resourceTypeId", resourceType);
		}
		l = resourceDao.find(params);
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				if (r.getTresource() != null) {
					tree.setPid(r.getTresource().getId());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>(512);
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
			logger.info("查询资源列表成功:总记录数:{}", lt.size());
		} else {
			logger.warn("查询资源列表为空");
		}
		return lt;
	}

	@Override
	public List<Tree> allTree(SessionInfo sessionInfo) {
		return allTree(sessionInfo, null);
	}

	@Override
	public List<Resource> treeGrid(String sourceName, SessionInfo sessionInfo) {
		logger.info("查询资源管理grid,sourceName:{}", sourceName);
		List<Tresource> l = null;
		List<Resource> lr = new ArrayList<Resource>();

		Map<String, Object> params = new HashMap<String, Object>(32);
		if (sessionInfo != null) {
			logger.info("设置只查询当前用户的资源,userId:{}", sessionInfo.getId());
			params.put("locale", sessionInfo.getLocale());
			params.put("userId", sessionInfo.getId());
		}

		if (!StringUtils.isEmpty(sourceName)) {
			logger.info("设置参数:source_name:{}", sourceName);
			params.put("source_name", "%%" + sourceName + "%%");
		}

		l = resourceDao.find(params);

		if (l != null && l.size() > 0) {
			for (Tresource t : l) {
				Resource r = new Resource();
				BeanUtils.copyProperties(t, r);
				if (t.getTresource() != null) {
					r.setPid(t.getTresource().getId());
					r.setPname(t.getTresource().getName());
				}
				r.setTypeId(t.getTresourcetype().getId());
				r.setTypeName(t.getTresourcetype().getName());
				if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
					r.setIconCls(t.getIcon());
				}
				lr.add(r);
			}
			logger.info("查询资源列表成功,总记录数:{}", lr.size());
		} else {
			logger.info("查询资源列表为空");
		}
		return lr;
	}

	@Override
	@Transactional
	public int add(Resource resource, SessionInfo sessionInfo) {
		logger.info("调用添加资源:{}", resource);
		int count = 0;
		Tresource t = new Tresource();
		BeanUtils.copyProperties(resource, t);
		if (resource.getPid() != null && !resource.getPid().equalsIgnoreCase("")) {
			t.setTresource(resourceDao.get(resource.getPid(), sessionInfo.getLocale()));
		}
		if (resource.getTypeId() != null && !resource.getTypeId().equalsIgnoreCase("")) {
			t.setTresourcetype(resourceTypeDao.getById(resource.getTypeId()));
		}
		if (resource.getIconCls() != null && !resource.getIconCls().equalsIgnoreCase("")) {
			t.setIcon(resource.getIconCls());
		}
		t.setCreate_user(sessionInfo.getUsername());
		count += resourceDao.save(t);
		logger.info("保存资源成功");
		// 由于当前用户所属的角色，没有访问新添加的资源权限，所以在新添加资源的时候，将当前资源授权给当前用户的所有角色，以便添加资源后在资源列表中能够找到
		Tuser user = userDao.get(Long.parseLong(sessionInfo.getId()));
		Set<Trole> roles = user.getTroles();
		for (Trole r : roles) {
			r.getTresources().add(t);
		}
		count += resourceDao.saveRoleResource(t, roles);
		logger.info("添加资源到当前用户id:{}所有的角色成功");
		return count;
	}

	@Override
	@Transactional
	public int delete(String id, SessionInfo sessionInfo) throws Exception {
		logger.info("调用删除资源,id:{}", id);
		Tresource t = resourceDao.get(id, sessionInfo.getLocale());

		if (resourceDao.hasRoleOwnResources(id)) {
			throw new Exception("该资源已被分配角色，不能删除");
		}

		logger.info("删除资源id:{}成功", id);
		return del(t);
	}

	private int del(Tresource t) {
		int count = 0;
		if (t.getTresources() != null && t.getTresources().size() > 0) {
			System.out.println(t.getTresources().size());
			for (Tresource r : t.getTresources()) {
				count += del(r);
			}
		}
		count += resourceDao.delete(t);
		return count;
	}

	@Override
	@Transactional
	public int edit(Resource resource, SessionInfo sessionInfo) {
		logger.info("调用编辑角色:{}", resource);
		Tresource t = resourceDao.get(resource.getId(), sessionInfo.getLocale());
		if (t != null) {
			BeanUtils.copyProperties(resource, t);
			if (resource.getTypeId() != null && !resource.getTypeId().equalsIgnoreCase("")) {
				t.setTresourcetype(resourceTypeDao.getById(resource.getTypeId()));
			}
			if (resource.getIconCls() != null && !resource.getIconCls().equalsIgnoreCase("")) {
				t.setIcon(resource.getIconCls());
			}
			if (resource.getPid() != null && !resource.getPid().equalsIgnoreCase("")) {
				Tresource pt = resourceDao.get(resource.getPid(), sessionInfo.getLocale());
				System.out.println(pt.toString());
				t.setTresource(pt);
			} else {
				t.setTresource(null);
			}
			t.setUpdate_user(sessionInfo.getUsername());
			int result = resourceDao.update(t);
			logger.info("编辑角色:{}成功", t);
			return result;
		} else {
			logger.warn("未查询到资源:{}", resource);
			return 0;
		}
	}

	@Override
	public Resource get(String id, SessionInfo sessionInfo) {
		logger.info("调用根据id:{}查询资源", id);
		Tresource t = resourceDao.get(id, sessionInfo.getLocale());
		Resource r = null;
		if (t != null) {
			r = new Resource();
			BeanUtils.copyProperties(t, r);
			if (t.getTresource() != null) {
				r.setPid(t.getTresource().getId());
				r.setPname(t.getTresource().getName());
			}
			r.setTypeId(t.getTresourcetype().getId());
			r.setTypeName(t.getTresourcetype().getName());
			if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
				r.setIconCls(t.getIcon());
			}
			logger.info("获取资源{}成功", t);
		} else {
			logger.info("未查询到id:{}的资源");
		}
		return r;
	}

	@Override
	public Resource getByURL(String resourceURL, SessionInfo sessionInfo) {
		logger.info("调用根据name:{}查询资源", resourceURL);

		Tresource t = resourceDao.getByURL(resourceURL, Locale.SIMPLIFIED_CHINESE);
		Resource r = null;
		if (t != null) {
			r = new Resource();
			BeanUtils.copyProperties(t, r);
			if (t.getTresource() != null) {
				r.setPid(t.getTresource().getId());
				r.setPname(t.getTresource().getName());
			}
			r.setTypeId(t.getTresourcetype().getId());
			r.setTypeName(t.getTresourcetype().getName());
			if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
				r.setIconCls(t.getIcon());
			}
			logger.info("获取资源{}成功", t);
		} else {
			r = new Resource();
			logger.info("未查询到name:{}的资源");
		}
		return r;
	}
}
