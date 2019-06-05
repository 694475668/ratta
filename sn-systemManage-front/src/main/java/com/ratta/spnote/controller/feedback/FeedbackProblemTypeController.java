package com.ratta.spnote.controller.feedback;

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
import com.ratta.suponote.feedback.model.FeedbackProblemType;
import com.ratta.suponote.feedback.result.FeedbackProblemTypeRseult;
import com.ratta.suponote.feedback.service.FeedbackProblemTypeService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 问题反馈类型管理控制层 2019-02-25
 */
@Controller
@RequestMapping("/feedbackProblemTypeController")
public class FeedbackProblemTypeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FeedbackProblemTypeService feedbackProblemTypeService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String typeId, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("typeId", typeId);
		params.put("sessionInfo", sessionInfo);
		return feedbackProblemTypeService.query(ph, params);
	}

	/**
	 * 跳转到反馈问题类型管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/feedback/feedbackProblemType";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addFeedbackProblemType")
	public String addFeedbackProblemType() {
		return "/admin/feedback/addFeedbackProblemType";
	}

	/**
	 * 添加
	 * 
	 * @param feedbackProblemType
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	/** 反馈问题类型管理[添加] */
	@OperateLogger(content = "feedbackProblemType_add", operationType = OperateLogger.OperationType.C, table = "t_feedback_type")
	public Json add(FeedbackProblemType feedbackProblemType, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FeedbackProblemTypeRseult result = feedbackProblemTypeService.save(feedbackProblemType, sessionInfo);
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
	 * @return
	 */
	@RequestMapping(value = "/updateFeedbackProblemType")
	public String updateFeedbackProblemType(HttpServletRequest request,String typeId) {
		request.setAttribute("feedbackProblemType", feedbackProblemTypeService.query(typeId));
		return "/admin/feedback/updateFeedbackProblemType";
	}

	/**
	 * 修改
	 * 
	 * @param feedbackProblemType
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	/** 反馈问题类型管理[修改] */
	@OperateLogger(content = "feedbackProblemType_update", operationType = OperateLogger.OperationType.U, table = "t_feedback_type")
	public Json update(FeedbackProblemType feedbackProblemType, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FeedbackProblemTypeRseult result = feedbackProblemTypeService.update(feedbackProblemType, sessionInfo);
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
	 * @param typeId
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	/** 反馈问题类型管理[删除] */
	@OperateLogger(content = "feedbackProblemType_delete", operationType = OperateLogger.OperationType.D, table = "t_feedback_type")
	public Json delete(String typeId, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FeedbackProblemTypeRseult result = feedbackProblemTypeService.delete(typeId, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 查询第一级问题类型
	 * @return
	 */
	@RequestMapping("/queryValueOne")
	@ResponseBody
	public List<FeedbackProblemType> queryValueOne() {
		return feedbackProblemTypeService.queryValueOne();
	}

	/**
	 * 查询第二级问题类型
	 * @return
	 */
	@RequestMapping("/queryValueTwo")
	@ResponseBody
	public List<FeedbackProblemType> queryValueTwo(String typeId) {
		return feedbackProblemTypeService.queryValueTwo(typeId);
	}
	
	/**
	 * 查询第三级问题类型
	 * @return
	 */
	@RequestMapping("/queryValueThree")
	@ResponseBody
	public List<FeedbackProblemType> queryValueThree(String typeId) {
		return feedbackProblemTypeService.queryValueThree(typeId);
	}
	
	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public FeedbackProblemType getBean(String typeId) {
		return feedbackProblemTypeService.query(typeId);
	}
}
