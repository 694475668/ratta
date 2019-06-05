package com.ratta.spnote.controller.feedback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ExcelXUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.ToolUtil;
import com.ratta.suponote.feedback.model.FeedbackRecord;
import com.ratta.suponote.feedback.service.FeedbackRecordService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;

/**
 * @author page 问题反馈记录管理控制层 2019-02-25
 */
@Controller
@RequestMapping("/feedbackRecordController")
public class FeedbackRecordController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FeedbackRecordService feedbackRecordService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String equipment_no, String contact, String valueOne, String valueTwo, 
			String valueThree) {
		return feedbackRecordService.query(ph, equipment_no,contact,valueOne,valueTwo,valueThree);
	}

	/**
	 * 跳转到反馈问题记录管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/feedback/feedbackRecord";
	}


	/**
	 * 导出
	 * 
	 * @param request
	 * @param typeId
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	@OperateLogger(content = "feedbackRecord_export", operationType = OperateLogger.OperationType.R)
	public Json export(HttpServletRequest request, String typeId) {
		Json j = new Json();
		try {
			String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "excel"
					+ File.separator;
			System.out.println(filePath);
			String name = "FeedbackRecordRecharge_" + ToolUtil.now() + ".xlsx";
			String reportPath = filePath + "export" + File.separator + name;
			Map<String, Object> params = new HashMap<String, Object>(32);
			params.put("typeId", typeId);
			List<FeedbackRecord> list = feedbackRecordService.queryNoPage(params);
			Map<String, String> datas = new HashMap<String, String>(16);
			datas.put("print_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ExcelXUtil.getInstance().exportObj2ExcelByTemplate(datas, filePath + "FeedbackRecordRecharge.xlsx", reportPath,
					list, FeedbackRecord.class, false);

			JSONObject jObj = new JSONObject();
			jObj.put("name", name);

			j.setSuccess(true);
			j.setMsg(FeedbackMessage.EXPORT_SUCCESS.getMessage());
			logger.info("export_json = '" + jObj.toJSONString() + "'");
			j.setObj(jObj.toJSONString());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(FeedbackMessage.EXPORT_ERROR.getMessage());

		}
		return j;
	}

	
	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public FeedbackRecord getBean(int id) {
		return feedbackRecordService.query(id);
	}
}
