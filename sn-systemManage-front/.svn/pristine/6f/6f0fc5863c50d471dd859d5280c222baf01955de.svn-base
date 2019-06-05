package com.ratta.spnote.controller.system;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.Role;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;
import com.ratta.suponote.system.service.RoleService;

/**
 * @author page 角色控制器 2018-10-31
 */
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {
	/**
	 * 角色服务
	 */
	@Autowired
	private RoleService roleService;

	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/role";
	}

	/**
	 * 跳转到角色添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Role r = new Role();
		r.setId(UUID.randomUUID().toString());
		request.setAttribute("role", r);
		return "/admin/roleAdd";
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@OperateLogger(content = "role_add", operationType = OperateLogger.OperationType.C, table = "t_m_role")
	public Json add(Role role, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			roleService.add(role, sessionInfo);
			j.setSuccess(true);
			j.setMsg(RoleMessage.ADD_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(RoleMessage.ADD_ERROR.getMessage(e.getMessage()));
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 跳转到角色修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Role r = roleService.get(id, sessionInfo);
		request.setAttribute("role", r);
		return "/admin/roleEdit";
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	@OperateLogger(content = "role_update", operationType = OperateLogger.OperationType.U, table = "t_m_role")
	public Json edit(Role role, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			roleService.edit(role, sessionInfo);
			j.setSuccess(true);
			j.setMsg(RoleMessage.EDIT_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(RoleMessage.EDIT_ERROR.getMessage(e.getMessage()));
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 获得角色列表
	 * 
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid treeGrid(PageHelper ph, Role r, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Role> roles = roleService.treeGrid(sessionInfo, r);
		Role del = null;
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				if ("0".equals(role.getId())) {
					del = role;
				}
			}
		}
		roles.remove(del);

		DataGrid dg = new DataGrid();
		int begin = (ph.getPage() - 1) * ph.getRows();
		int end = ph.getPage() * ph.getRows();
		if (end > roles.size() - 1) {
			end = roles.size();
		}
		List<Role> roles2 = roles.subList(begin, end);
		dg.setRows(roles2);
		dg.setTotal(roles == null ? 0L : roles.size());
		return dg;
	}

	/**
	 * 角色树(只能看到自己拥有的角色)
	 * 
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Tree> trees = roleService.tree(sessionInfo);
		Tree del = null;
		for (Tree tree : trees) {
			if ("0".equals(tree.getId())) {
				del = tree;
			}
		}
		trees.remove(del);
		return trees;
	}

	/**
	 * 角色树
	 * 
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() {
		return roleService.allTree();
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "role_delete", operationType = OperateLogger.OperationType.D, table = "t_m_role")
	public Json delete(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			roleService.delete(id, sessionInfo);
			j.setMsg(RoleMessage.DEL_SUCCESS.getMessage());
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(RoleMessage.DEL_ERROR.getMessage((Object) e.getMessage()));
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 跳转到角色授权页面
	 * 
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(HttpServletRequest request, String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Role r = roleService.get(id, sessionInfo);
		request.setAttribute("role", r);
		return "/admin/roleGrant";
	}

	/**
	 * 授权
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	@OperateLogger(content = "role_grant", operationType = OperateLogger.OperationType.U, table = "t_m_role")
	public Json grant(Role role, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			roleService.grant(role, sessionInfo);
			j.setMsg(RoleMessage.GRANT_SUCCESS.getMessage());
			j.setSuccess(true);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(RoleMessage.GRANT_ERROR.getMessage(e.getMessage()));
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public Role getBean(String id) {
		return roleService.get(id, null);
	}

}
