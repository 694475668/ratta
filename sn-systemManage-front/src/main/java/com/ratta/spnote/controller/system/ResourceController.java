package com.ratta.spnote.controller.system;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.model.pagemodel.Resource;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.pagemodel.Tree;
import com.ratta.suponote.system.service.ResourceService;
import com.ratta.suponote.system.service.ResourceTypeService;

/**
 * @author page 资源管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/resourceController")
public class ResourceController extends BaseController {
	/**
	 * 资源服务
	 */
	@Autowired
	private ResourceService resourceService;
	/**
	 * 资源类型服务
	 */
	@Autowired
	private ResourceTypeService resourceTypeService;

	/**
	 * 获得资源树(资源类型为菜单类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session, HttpServletResponse response) throws IOException {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			response.getOutputStream()
					.print("<script language='javascript'>" + "window.location.href = getUrl();" + "</script>");
			return null;
		}
		return resourceService.tree(sessionInfo);
	}

	/**
	 * 获得资源树(包括所有资源类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			return null;
		}
		return resourceService.allTree(sessionInfo);

	}

	/**
	 * 跳转到资源管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/resource";
	}

	/**
	 * 跳转到资源添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("resourceTypeList", resourceTypeService.getResourceTypeList());
		Resource r = new Resource();
		r.setId(UUID.randomUUID().toString());
		request.setAttribute("resource", r);
		return "/admin/resourceAdd";
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@OperateLogger(content = "resource_add", operationType = OperateLogger.OperationType.C, table = "t_m_resource")
	public Json add(Resource resource, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			resourceService.add(resource, sessionInfo);
			j.setSuccess(true);
			j.setMsg(ResourceMessage.ADD_SUCCESS.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(ResourceMessage.ADD_ERROR.getMessage(e.getMessage()));
		}
		return j;
	}

	/**
	 * 跳转到资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		request.setAttribute("resourceTypeList", resourceTypeService.getResourceTypeList());
		Resource r = resourceService.get(id, sessionInfo);
		request.setAttribute("resource", r);
		return "/admin/resourceEdit";
	}

	/**
	 * 编辑资源
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	@OperateLogger(content = "resource_update", operationType = OperateLogger.OperationType.U, table = "t_m_resource")
	public Json edit(Resource resource, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			resourceService.edit(resource, sessionInfo);
			j.setSuccess(true);
			j.setMsg(ResourceMessage.EDIT_SUCCESS.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(ResourceMessage.EDIT_ERROR.getMessage(e.getMessage()));
		}
		return j;
	}

	/**
	 * 获得资源列表
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 * 
	 * 		updated by @Troy 2014-08-11 13:04:40 增加模糊查询
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid(String source_name, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		// return resourceService.treeGrid(source_name,sessionInfo);
		return resourceService.treeGrid(source_name, sessionInfo);
	}

	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "resource_delete", operationType = OperateLogger.OperationType.D, table = "t_m_resource")
	public Json delete(String id, HttpSession session) {
		Json j = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			resourceService.delete(id, sessionInfo);
			j.setMsg(ResourceMessage.DEL_SUCCESS.getMessage());
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(ResourceMessage.DEL_ERROR.getMessage(e.getMessage()));
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public Resource getBean(String id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourceService.get(id, sessionInfo);
	}

}
