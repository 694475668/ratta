package com.ratta.spnote.controller.parameter;

import java.util.HashMap;
import java.util.List;
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
import com.ratta.suponote.param.model.Dictionary;
import com.ratta.suponote.param.result.DictionaryResult;
import com.ratta.suponote.param.service.DictionaryService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 数据字典管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/dictionaryController")
public class DictionaryController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String name, String value_cn, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("name", name);
		params.put("value_cn", value_cn);
		params.put("sessionInfo", sessionInfo);
		return dictionaryService.query(ph, params);
	}

	/**
	 * 根据key值获取数据字典列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryDictionary")
	@ResponseBody
	public List<Dictionary> queryDictionary(String name) {
		return dictionaryService.queryDictionary(name);
	}

	/**
	 * 跳转到数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/parameter/dictionary/dictionary";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addDictionary")
	public String addDictionary() {
		return "/admin/parameter/dictionary/addDictionary";
	}

	/**
	 * 添加
	 * 
	 * @param errorCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Dictionary_add", operationType = OperateLogger.OperationType.C, table = "t_dictionary")
	public Json add(Dictionary errorCode, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			DictionaryResult result = dictionaryService.save(errorCode, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editDictionary")
	public String editDictionary(HttpServletRequest request, Long id) {
		Dictionary dictionary = dictionaryService.query(id);
		request.setAttribute("dictionary", dictionary);
		return "/admin/parameter/dictionary/updateDictionary";
	}

	/**
	 * 修改
	 * 
	 * @param errorCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Dictionary_update", operationType = OperateLogger.OperationType.U, table = "t_dictionary")
	public Json edit(Dictionary dictionary, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			DictionaryResult result = dictionaryService.update(dictionary, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "Dictionary_delete", operationType = OperateLogger.OperationType.D, table = "t_dictionary")
	public Json delete(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			DictionaryResult result = dictionaryService.delete(id, sessionInfo);
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
	public Dictionary getBean(Long id) {
		return dictionaryService.query(id);
	}

}
