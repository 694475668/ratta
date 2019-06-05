package com.ratta.spnote.controller.business;

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
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.ratta.suponote.business.model.Dealers;
import com.ratta.suponote.business.result.DealersRseult;
import com.ratta.suponote.business.service.DealersService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page 经销商管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/dealersController")
public class DealersController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DealersService dealersService;

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String dealersName, String contact, String phone, String dealersType,
			String beginTime, String endTime, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("dealersName", dealersName);
		params.put("contact", contact);
		params.put("phone", phone);
		params.put("dealersType", dealersType);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return dealersService.query(ph, params);
	}

	/**
	 * 跳转到经销商管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/business/dealers/dealers";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addDealers")
	public String addDealers() {
		return "/admin/business/dealers/addDealers";
	}

	/**
	 * 添加
	 * 
	 * @param dealers
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	/** 经销商管理[添加] */
	@OperateLogger(content = "Dealers_add", operationType = OperateLogger.OperationType.C, table = "t_m_dealers")
	public Json add(Dealers dealers, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			DealersRseult result = dealersService.save(dealers, sessionInfo);
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
	@RequestMapping(value = "/editDealers")
	public String editDealers(HttpServletRequest request, Long id) {
		Dealers dealers = dealersService.query(id);
		request.setAttribute("dealers", dealers);
		return "/admin/business/dealers/updateDealers";
	}

	/**
	 * 修改
	 * 
	 * @param dealers
	 * @param session
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	/** 经销商管理[修改] */
	@OperateLogger(content = "Dealers_update", operationType = OperateLogger.OperationType.U, table = "t_m_dealers")
	public Json edit(Dealers dealers, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			DealersRseult result = dealersService.update(dealers, sessionInfo);
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
	/** 经销商管理[删除] */
	@OperateLogger(content = "Dealers_delete", operationType = OperateLogger.OperationType.D, table = "t_m_dealers")
	public Json delete(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			DealersRseult result = dealersService.delete(id, sessionInfo);
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
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "excel"
				+ File.separator;
		String name = "DealersTemplate.xlsx";
		String reportPath = filePath + name;
		System.out.println("reportPath：" + reportPath);
		byte[] buffer = new byte[1024];
		try {
			// 读取文件
			FileInputStream fis = new FileInputStream(reportPath);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=DealersTemplate.xlsx");
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
	 * 导入
	 * 
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Dealers_upload", operationType = OperateLogger.OperationType.C, table = "t_m_dealers")
	public Json upload(@RequestParam(value = "file") MultipartFile file, HttpSession session) throws IOException {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json js = new Json();
		String lastName = file.getOriginalFilename();
		if (!lastName.endsWith("xls") && !lastName.endsWith("xlsx")) {
			// 判断是否是Excel格式文件
			js.setMsg(DealersMessage.DEALERS_FILE_FORMAT_EXCEL_ERROR.getMessage());
			return js;
		}
		try {
			// 创建Excel工作薄
			Workbook excelWB = null;
			if (lastName.endsWith("xls")) {
				excelWB = new HSSFWorkbook(file.getInputStream());
			} else {
				excelWB = new XSSFWorkbook(file.getInputStream());
			}
			// 得到第一个工作表
			Sheet sheet = excelWB.getSheetAt(0);
			// 起始行
			Row row = null;
			// HSSFCell cell =null;
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数，这里只需遍历第一个工作表
			for (int i = 0; i < 1; i++) {
				sheet = excelWB.getSheetAt(i);
				System.out.println(sheet.getPhysicalNumberOfRows());
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数，从第三行开始解析
				for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
					row = sheet.getRow(j);
					// 判断是否有经销商名称
					if (row.getCell(0) == null || row.getCell(0).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_DEALERSNAME_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					//判断是否有经销商类别
					if (row.getCell(1) == null || row.getCell(1).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_DEALERSTYPE_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 判断是否有联系人
					if (row.getCell(2) == null || row.getCell(2).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_CONTACT_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 判断是否有手机号
					if (row.getCell(3) == null || row.getCell(3).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_PHONE_NULL_ERROR.getMessage(j + 1));
						return js;
					} else {
						if (!Pattern.matches("^\\d{6,15}$", row.getCell(3).toString())) {
							js.setMsg(DealersMessage.DEALERS_PHONE_FORMAT_ERROR.getMessage(j + 1));
							return js;
						}
					}
					// 判断是否有地址
					if (row.getCell(4) == null || row.getCell(4).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_ADDRESS_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 判断是否有申请时间
					if (row.getCell(5) == null || row.getCell(5).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_APPLICATIONTIME_NULL_ERROR.getMessage(j + 1));
						return js;
					}
					// 判断是否有业务员
					if (row.getCell(6) == null || row.getCell(6).toString() == "") {
						js.setMsg(DealersMessage.DEALERS_SALESMAN_NULL_ERROR.getMessage(j + 1));
						return js;
					}
				}
			}

			List<Dealers> info = saveExcel(file, sessionInfo.getUsername());
			String result = dealersService.save(info, sessionInfo);
			js.setSuccess(true);
			js.setMsg(result);
		} catch (Exception e) {
			js.setMsg(DealersMessage.DEALERS_IMPORT_ERROR.getMessage());

			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return js;
	}

	/**
	 * 解析excel
	 * 
	 * @param fis
	 * @return
	 * @throws Exception
	 */
	public List<Dealers> saveExcel(MultipartFile file, String userName) throws Exception {
		List<Dealers> infos = new ArrayList<Dealers>();
		String lastName = file.getOriginalFilename();
		Dealers dealers = null;
		// 创建Excel工作薄
		Workbook excelWB = null;
		if ("xls".equals(lastName.endsWith("xls"))) {
			excelWB = new HSSFWorkbook(file.getInputStream());
		} else {
			excelWB = new XSSFWorkbook(file.getInputStream());
		}
		// 得到第一个工作表
		Sheet sheet = excelWB.getSheetAt(0);
		// 起始行
		Row row = null;
		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数，这里只需遍历第一个工作表
		for (int i = 0; i < 1; i++) {
			sheet = excelWB.getSheetAt(i);
			// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数，从第三行开始解析
			for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
				row = sheet.getRow(j);
				if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
					dealers = new Dealers();
					// 经销商名称
					dealers.setDealersName(getValue(row.getCell(0)).trim());
					// 经销商类别
					dealers.setDealersType(getValue(row.getCell(1)).trim());
					// 联系人
					dealers.setContact(getValue(row.getCell(2)).trim());
					// 手机号
					dealers.setPhone(getValue(row.getCell(3)).trim());
					if (dealers.getPhone() != null && dealers.getPhone().length() > 11) {
						dealers.setPhone(dealers.getPhone().substring(0, 11));
					}
					// 地址
					dealers.setAddress(getValue(row.getCell(4)).trim());
					// 申请时间
					dealers.setApplicationTime(getValue(row.getCell(5)).trim());
					// 业务员
					dealers.setSalesman(getValue(row.getCell(6)).trim());
					// 备注
					dealers.setRemark(getValue(row.getCell(7)).trim());
					infos.add(dealers);
				} else {
					continue;
				}
			}
		}
		return infos;
	}

	/**
	 * 判断excel中cell的类型
	 * 
	 * @param cell
	 * @return
	 */
	public String getValue(Cell cell) {
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
	 * 导出
	 * 
	 * @param request
	 * @param orderid
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	@OperateLogger(content = "Dealers_export", operationType = OperateLogger.OperationType.R)
	public Json export(HttpServletRequest request, String dealersName, String contact, String phone, String dealersType,
			String beginTime, String endTime) {
		Json j = new Json();
		try {
			String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "excel"
					+ File.separator;
			System.out.println(filePath);
			String name = "DealersRecharge_" + ToolUtil.now() + ".xlsx";
			String reportPath = filePath + "export" + File.separator + name;
			Map<String, Object> params = new HashMap<String, Object>(32);
			params.put("dealersName", dealersName);
			params.put("contact", contact);
			params.put("phone", phone);
			params.put("dealersType", dealersType);
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			List<Dealers> list = dealersService.querySum(params);
			if (list == null || list.isEmpty()) {
				j.setSuccess(false);
				j.setMsg(DealersMessage.DEALERS_INOUT_NO_DATA_TO_EXPORT_ERROR.getMessage());
				return j;
			}
			Map<String, String> datas = new HashMap<String, String>(16);
			datas.put("print_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ExcelXUtil.getInstance().exportObj2ExcelByTemplate(datas, filePath + "DealersRecharge.xlsx", reportPath,
					list, Dealers.class, false);

			JSONObject jObj = new JSONObject();
			jObj.put("name", name);

			j.setSuccess(true);
			j.setMsg(DealersMessage.DEALERS_INOUT_EXPORT_SUCCESS.getMessage());

			logger.info("export_json = '" + jObj.toJSONString() + "'");
			j.setObj(jObj.toJSONString());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(DealersMessage.DEALERS_INOUT_EXPORT_ERROR.getMessage());

		}
		return j;
	}

	/**
	 * 不分页查询经销商记录
	 */
	@RequestMapping("/queryNoPage")
	@ResponseBody
	public List<Dealers> queryNoPage() {
		return dealersService.querySum(new HashMap<String, Object>(8));
	}
	
	/**
	 * 通过id查询经销商信息
	 * @param id id
	 */
	@RequestMapping("/queryDearlerById")
	@ResponseBody
	public Dealers queryDearlerById(Long id) {
		return dealersService.query(id);
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public Dealers getBean(Long id) {
		return dealersService.query(id);
	}
}
