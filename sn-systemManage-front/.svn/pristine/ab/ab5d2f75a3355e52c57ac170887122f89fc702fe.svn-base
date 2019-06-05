package com.ratta.spnote.controller.parameter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.param.model.Reference;
import com.ratta.suponote.param.result.ReferenceResult;
import com.ratta.suponote.param.service.ReferenceService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 系统参数明细控制层 2018-10-31
 */
@Controller
@RequestMapping("/systemParaDetailController")
public class SystemParaDetailController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReferenceService referenceService;

	@RequestMapping("/dataGridDetail")
	@ResponseBody
	public DataGrid dataGridDetail(PageHelper ph, String name, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("name", name);
		params.put("sessionInfo", sessionInfo);
		return referenceService.query(ph, params);
	}

	/**
	 * 跳转到数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/parameter/systemPara/systemParam";
	}

	/**
	 * 跳转到添加详情页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addSystemParaDetail")
	public String addSystemParaDetail(HttpServletRequest request) {
		return "/admin/parameter/systemPara/addSystemParaDetail";
	}

	/**
	 * 添加详情
	 * 
	 * @param reference
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addDetail", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "systemParaDetail_add", operationType = OperateLogger.OperationType.C, table = "t_m_reference")
	public Json addDetail(Reference reference, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			ReferenceResult result = referenceService.save(reference, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到修改详情页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editSystemParaDetail")
	public String editSystemParaDetail(Long id, HttpServletRequest request) {
		Reference reference = referenceService.queryById(id);
		request.setAttribute("reference", reference);
		return "/admin/parameter/systemPara/updateSystemParaDetail";
	}

	/**
	 * 修改详情
	 * 
	 * @param appid
	 * @return
	 */
	@RequestMapping("/editDetail")
	@ResponseBody
	@OperateLogger(content = "systemParaDetail_update", operationType = OperateLogger.OperationType.U, table = "t_m_reference")
	public Json editDetail(Reference reference, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			ReferenceResult result = referenceService.update(reference, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除参数明细
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "systemParaDetail_delete", operationType = OperateLogger.OperationType.D, table = "t_m_reference")
	public Json delete(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			ReferenceResult result = referenceService.delete(id, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public Reference getBean(Long id) {
		return referenceService.queryById(id);
	}

	public static void main(String[] args) {
		String a = "1.9";
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal("0.1");
		System.out.println(b1.add(b2).toString());
	}
}
