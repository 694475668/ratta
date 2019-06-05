package com.ratta.spnote.controller.stock;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.alibaba.fastjson.JSONObject;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.EquipmentUnlinkUtil;
import com.ratta.spnote.util.ExcelXUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.ToolUtil;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponote.stock.result.StockRseult;
import com.ratta.suponote.stock.service.StockService;
import com.ratta.suponote.usersn.service.SNUserService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 设备库存管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/stockController")
public class StockController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StockService stockService;

	@Autowired
	private SNUserService snUserService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String batch_no, String flag, String equipment_model, String equipment_no,
			String update_status, String deployVersion, String firmware_version, String healthyState, String beginTime,
			String endTime, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("equipment_model", equipment_model);
		params.put("equipment_no", equipment_no);
		params.put("update_status", update_status);
		params.put("firmware_version", firmware_version);
		params.put("batch_no", batch_no);
		params.put("deployVersion", deployVersion);
		params.put("flag", flag);
		params.put("healthyState", healthyState);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return stockService.query(ph, params);
	}

	@RequestMapping("/dataGridNotRelease")
	@ResponseBody
	public DataGrid dataGridNotRelease(PageHelper ph, String equipment_model,String batch_no, String firmware_version,String equipment_number,
			String equipment_purpose, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("batch_no", batch_no);
		params.put("equipment_model", equipment_model);
		params.put("firmware_version", firmware_version);
		params.put("equipment_number", equipment_number);
		params.put("equipment_purpose", equipment_purpose);
		params.put("sessionInfo", sessionInfo);
		return stockService.queryStockNotRelease(ph, params);
	}

	/**
	 * 跳转到设备库存管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/stock/stock";
	}

	/**
	 * 导出
	 * 
	 * @param request
	 * @param orderid
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	@OperateLogger(content = "equipmentStock_export", operationType = OperateLogger.OperationType.R)
	public Json export(HttpServletRequest request, String equipment_model, String equipment_no, String firmware_version,
			String batch_no, String update_status, String beginTime, String endTime) {
		Json j = new Json();
		try {
			String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "excel"
					+ File.separator;
			System.out.println(filePath);
			String name = "stock_" + ToolUtil.now() + ".xlsx";
			String reportPath = filePath + "export" + File.separator + name;
			Map<String, Object> params = new HashMap<String, Object>(32);
			params.put("equipment_model", equipment_model);
			params.put("equipment_no", equipment_no);
			params.put("batch_no", batch_no);
			params.put("firmware_version", firmware_version);
			params.put("update_status", update_status);
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			List<Stock> list = stockService.querySum(params);
			if (list == null || list.isEmpty()) {
				j.setSuccess(false);
				j.setMsg(StockMessage.Stock_DATA_NO_EXPORT_ERROR.getMessage());
				return j;
			}
			Map<String, String> datas = new HashMap<String, String>(8);
			datas.put("print_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ExcelXUtil.getInstance().exportObj2ExcelByTemplate(datas, filePath + "stock.xlsx", reportPath, list,
					Stock.class, false);

			JSONObject jObj = new JSONObject();
			jObj.put("name", name);

			j.setSuccess(true);
			j.setMsg(StockMessage.Stock_DATA_EXPORT_SUCCESS.getMessage());

			logger.info("export_json = '" + jObj.toJSONString() + "'");
			j.setObj(jObj.toJSONString());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(StockMessage.Stock_DATA_EXPORT_ERROR.getMessage());

		}
		return j;
	}

	/**
	 * 跳转到退货页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/returnStock")
	public String toReturnStock(HttpServletRequest request, String equipment_no) {
		request.setAttribute("equipment_no", equipment_no);
		return "/admin/stock/returnStock";
	}

	/**
	 * 退货
	 * 
	 * @param equipmentReturnRecord
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/return", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Equipment_return", operationType = OperateLogger.OperationType.C, table = "t_m_return_record")
	public Json returnStock(EquipmentReturnRecord equipmentReturnRecord, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		boolean resultBoolean = false;
		try {
			StockRseult result = stockService.returnStock(equipmentReturnRecord, sessionInfo);
			if (result.getValue() == 0) {
				resultBoolean = true;
				String account = snUserService.getUsernameByEquipmentNo(equipmentReturnRecord.getEquipment_no());
				if (account != null && !"".equals(account)) {
					int pushResult = EquipmentUnlinkUtil.push(equipmentReturnRecord.getEquipment_no(), account);
					if (pushResult != 1) {
						resultBoolean = false;
					}
				}
			}
			j.setSuccess(resultBoolean);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到换货页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exchangeStock")
	public String toExchangeStock(HttpServletRequest request, String equipment_no) {
		request.setAttribute("equipment_no", equipment_no);
		return "/admin/stock/exchangeStock";
	}

	/**
	 * 换货
	 * 
	 * @param equipmentReturnRecord
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Equipment_exchange", operationType = OperateLogger.OperationType.C, table = "t_m_return_record")
	public Json exchangeStock(EquipmentReturnRecord equipmentReturnRecord, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		boolean resultBoolean = false;
		try {
			StockRseult result = stockService.exchangeStock(equipmentReturnRecord, sessionInfo);
			if (result.getValue() == 0) {
				resultBoolean = true;
				String account = snUserService.getUsernameByEquipmentNo(equipmentReturnRecord.getEquipment_no());
				if (account != null && !"".equals(account)) {
					int pushResult = EquipmentUnlinkUtil.push(equipmentReturnRecord.getEquipment_no(), account);
					if (pushResult != 1) {
						resultBoolean = false;
					}
				}
			}
			j.setSuccess(resultBoolean);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到修改健康状态页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toHealthyState")
	public String toHealthyState(HttpServletRequest request, String equipment_no) {
		request.setAttribute("equipment_no", equipment_no);
		return "/admin/stock/healthyState";
	}

	/**
	 * 健康状态
	 * 
	 * @param equipmentReturnRecord
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/healthyState", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Equipment_healthyState", operationType = OperateLogger.OperationType.U, table = "t_m_return_record")
	public Json healthyState(EquipmentReturnRecord equipmentReturnRecord, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			StockRseult result = stockService.healthyState(equipmentReturnRecord, sessionInfo);
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
	public Stock getBean(Long id) {
		return stockService.query(id);
	}
}
