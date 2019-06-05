package com.ratta.spnote.controller.stock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.ExcelXUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.ToolUtil;
import com.ratta.suponote.firmware.model.FirmwareInfo;
import com.ratta.suponote.firmware.service.FirmwareInfoService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.model.InOutStock;
import com.ratta.suponote.stock.result.InOutStockRseult;
import com.ratta.suponote.stock.service.InOutStockService;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 设备出入库管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/inOutStockController")
public class InOutStockController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InOutStockService inOutStockService;
	@Autowired
	private FirmwareInfoService firmwareInfoService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String batch_no, String equipment_model, String flag, String status,
			String dealers_name, String beginTime, String endTime, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("equipment_model", equipment_model);
		params.put("flag", flag);
		params.put("status", status);
		params.put("dealers_name", dealers_name);
		params.put("batch_no", batch_no);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return inOutStockService.query(ph, params);
	}

	@RequestMapping("/queryBatchNo")
	@ResponseBody
	public DataGrid queryBatchNo(PageHelper ph, String equipment_model, String batch_no, long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(16);
		if (id != 0) {
			FirmwareInfo firmwareInfo = firmwareInfoService.query(id);
			params.put("area", firmwareInfo.getArea());
			params.put("custom", firmwareInfo.getCustom());
			params.put("configuration", firmwareInfo.getConfiguration());
		}
		params.put("equipment_model", equipment_model);
		params.put("batch_no", batch_no);
		params.put("sessionInfo", sessionInfo);
		return inOutStockService.queryBatchNo(ph, params);
	}

	/**
	 * 跳转到设备出入库管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/stock/inOutStock";
	}

	/**
	 * 跳转到入库页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/inStock")
	public String inStock() {
		return "/admin/stock/inStock";
	}

	/**
	 * 入库
	 * 
	 * @param inOutStock
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/in", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "InStock", operationType = OperateLogger.OperationType.C, table = "t_m_inout")
	public Json inStock(InOutStock inOutStock, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			InOutStockRseult result = inOutStockService.in(inOutStock, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到出库页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/outStock")
	public String outStock(HttpServletRequest request) {
		return "/admin/stock/outStock";
	}

	/**
	 * 出库
	 * 
	 * @param inOutStock
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/out", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "OutStock", operationType = OperateLogger.OperationType.C, table = "t_m_inout")
	public Json outStock(InOutStock inOutStock, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		List<InOutStock> inOutStockList = new ArrayList<InOutStock>();
		inOutStockList.add(inOutStock);
		try {
			InOutStockRseult result = inOutStockService.out(inOutStockList, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 根据批次号获取设备用途
	 * 
	 * @return
	 */
	@RequestMapping("/queryEquipmentPurpose")
	@ResponseBody
	public List<InOutStock> queryEquipmentPurpose(String batch_no) {
		return inOutStockService.queryEquipmentPurpose(batch_no);
	}

	/**
	 * 撤销
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/inOutRevoke", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "deviceInOut_revoke", operationType = OperateLogger.OperationType.U, table = "t_m_inout")
	public Json inRevoke(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			InOutStockRseult result = inOutStockService.undoInOutStock(id, sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 模板下载
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/downloadDealersXls")
	@ResponseBody
	public void download(int type, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "excel"
				+ File.separator;
		String name = "";
		byte[] buffer = new byte[1024];
		try {
			if (type == 1) {
				name = "inOutStockImportModel.xlsx";
				response.setHeader("Content-disposition", "attachment;filename=inOutStockImportModel.xlsx");
			} else {
				name = "inOutStockBatchOutModel.xlsx";
				response.setHeader("Content-disposition", "attachment;filename=inOutStockBatchOutModel.xlsx");
			}
			String reportPath = filePath + name;
			FileInputStream fis = new FileInputStream(reportPath);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			OutputStream fos = response.getOutputStream();
			int length = fis.read(buffer);
			while (length > 0) {
				fos.write(buffer, 0, length);
				length = fis.read(buffer);
			}
			fis.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	@OperateLogger(content = "InOutStock_export", operationType = OperateLogger.OperationType.R)
	public Json export(HttpServletRequest request, String batch_no, String equipment_model, String flag, String status,
			String dealers_name, String beginTime, String endTime) {
		Json j = new Json();
		try {
			String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "excel"
					+ File.separator;
			System.out.println(filePath);
			String name = "inOutStock_" + ToolUtil.now() + ".xlsx";
			String reportPath = filePath + "export" + File.separator + name;
			Map<String, Object> params = new HashMap<String, Object>(32);
			params.put("equipment_model", equipment_model);
			params.put("flag", flag);
			params.put("status", status);
			params.put("dealers_name", dealers_name);
			params.put("batch_no", batch_no);
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			List<InOutStock> list = inOutStockService.querySum(params);
			if (list == null || list.isEmpty()) {
				j.setSuccess(false);
				j.setMsg(StockMessage.Stock_DATA_NO_EXPORT_ERROR.getMessage());
				return j;
			}
			Map<String, String> datas = new HashMap<String, String>(8);
			datas.put("print_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ExcelXUtil.getInstance().exportObj2ExcelByTemplate(datas, filePath + "inOutStock.xlsx", reportPath, list,
					InOutStock.class, false);

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
	 * 跳转到批入页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/importStock")
	public String importStock() {
		return "/admin/stock/importStock";
	}

	/**
	 * 批入
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Stock_Import", operationType = OperateLogger.OperationType.C, table = "t_m_inout")
	public Json upload(@RequestParam(value = "file") MultipartFile file, String equipment_model, String note,
			HttpSession session) throws IOException {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<InOutStock> inOutStocks = new ArrayList<InOutStock>();

		Json js = new Json();
		if (equipment_model == null) {
			logger.info("设备型号为空！");
			js.setMsg(StockMessage.EQUIPMENT_TYPE_NULL.getMessage());
		}
		String lastName = file.getOriginalFilename();
		if (!lastName.endsWith("xlsx")) {
			// 判断是否是Excel格式文件
			js.setMsg(StockMessage.FILE_FORMAT_EXCEL_ERROR.getMessage());
			return js;
		}
		try {
			// 创建Excel工作薄
			XSSFWorkbook excelWB = new XSSFWorkbook(file.getInputStream());
			// 得到第一个工作表
			XSSFSheet sheet = excelWB.getSheetAt(0);
			String sheetName = sheet.getSheetName();
			if (!"设备导入".equals(sheetName)) {
				js.setMsg(StockMessage.DEALERS_IMPORT_ERROR.getMessage());
				return js;
			}
			// 起始行
			XSSFRow row = null;
			// HSSFCell cell =null;
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数，这里只需遍历第一个工作表
			for (int i = 0; i < 1; i++) {
				sheet = excelWB.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数，从第三行开始解析
				for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
					InOutStock inOutStock = new InOutStock();
					row = sheet.getRow(j);
					// 批次号
					if (row.getCell(0) == null || row.getCell(0).toString() == "") {
						js.setMsg(StockMessage.BATCH_NO_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 设备号
					if (row.getCell(1) == null || row.getCell(1).toString() == "") {
						js.setMsg(StockMessage.EQUIOMENT_NO_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 固件版本号
					if (row.getCell(2) == null || row.getCell(2).toString() == "") {
						js.setMsg(StockMessage.FIRMWARE_VERSION_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					if (row.getCell(0) != null && row.getCell(0).getCellType() != XSSFCell.CELL_TYPE_BLANK) {
						inOutStock.setBatch_no(getValue(row.getCell(0)).trim());
						inOutStock.setEquipment_model(equipment_model);
						inOutStock.setFirmware_version(getValue(row.getCell(2)).trim());
						inOutStock.setNote(note);
						inOutStock.setBegin_sn(getValue(row.getCell(1)).trim());
						inOutStock.setEnd_sn(getValue(row.getCell(1)).trim());
						inOutStock.setEquipment_no(getValue(row.getCell(1)).trim());
						inOutStocks.add(inOutStock);
					} else {
						continue;
					}
				}
			}

			InOutStockRseult result = inOutStockService.save(inOutStocks, sessionInfo);
			js.setSuccess(result.getValue() == 0);
			js.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			js.setMsg(StockMessage.DEALERS_IMPORT_ERROR.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return js;
	}

	/**
	 * 跳转到批出页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exportStock")
	public String exportStock() {
		return "/admin/stock/exportStock";
	}

	/**
	 * 批出
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/batchOut", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Stock_batch_out", operationType = OperateLogger.OperationType.C, table = "t_m_inout")
	public Json batchOut(@RequestParam(value = "file") MultipartFile file, String equipment_purpose, String dealers_name,
			String note, HttpSession session) throws IOException {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<InOutStock> inOutStocks = new ArrayList<InOutStock>();
		Json js = new Json();
		String lastName = file.getOriginalFilename();
		if (!lastName.endsWith("xlsx")) {
			// 判断是否是Excel格式文件
			js.setMsg(StockMessage.FILE_FORMAT_EXCEL_ERROR.getMessage());
			return js;
		}
		try {
			// 创建Excel工作薄
			XSSFWorkbook excelWB = new XSSFWorkbook(file.getInputStream());
			// 得到第一个工作表
			XSSFSheet sheet = excelWB.getSheetAt(0);
			String sheetName = sheet.getSheetName();
			if (!"设备出库导入".equals(sheetName)) {
				js.setMsg(StockMessage.DEALERS_IMPORT_ERROR.getMessage());
				return js;
			}
			// 起始行
			XSSFRow row = null;
			// HSSFCell cell =null;
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数，这里只需遍历第一个工作表
			for (int i = 0; i < 1; i++) {
				sheet = excelWB.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数，从第三行开始解析
				for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
					InOutStock inOutStock = new InOutStock();
					row = sheet.getRow(j);
					// 设备号
					if (row.getCell(0) == null || row.getCell(0).toString() == "") {
						js.setMsg(StockMessage.EQUIOMENT_NO_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 固件版本号
					if (row.getCell(1) == null || row.getCell(1).toString() == "") {
						js.setMsg(StockMessage.FIRMWARE_VERSION_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					if (row.getCell(0) != null && row.getCell(0).getCellType() != XSSFCell.CELL_TYPE_BLANK) {
						inOutStock.setEquipment_no(getValue(row.getCell(0)).trim());
						inOutStock.setFirmware_version(getValue(row.getCell(1)).trim());
						inOutStock.setDealers_name(dealers_name);
						inOutStock.setEquipment_purpose(equipment_purpose);
						inOutStock.setNote(note);
						inOutStocks.add(inOutStock);
					} else {
						continue;
					}
				}
			}

			InOutStockRseult result = inOutStockService.out(inOutStocks, sessionInfo);
			js.setSuccess(result.getValue() == 0);
			js.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			js.setMsg(StockMessage.DEALERS_IMPORT_ERROR.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return js;
	}

	/**
	 * 判断excel中cell的类型
	 * 
	 * @param cell
	 * @return
	 */
	public String getValue(XSSFCell cell) {
		String k = "";
		if (cell == null) {
			return k;
		}

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			k = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			k = cell.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_ERROR:
			k = cell.getErrorCellValue() + "";
			break;
		case Cell.CELL_TYPE_FORMULA:
			k = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				k = new DataFormatter().formatRawCellContents(cell.getNumericCellValue(), 0, "yyyy-mm-dd");
			} else {
				DecimalFormat df = new DecimalFormat("0.00");
				k = df.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING:
			k = cell.getStringCellValue().trim();
			break;
		default:
			break;
		}
		return k;
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public InOutStock getBean(Long id) {
		return inOutStockService.query(id);
	}
}
