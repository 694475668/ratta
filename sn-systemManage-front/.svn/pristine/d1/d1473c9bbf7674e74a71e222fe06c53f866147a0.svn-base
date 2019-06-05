package com.ratta.spnote.controller.firmware;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.schedule.JobTrigger;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.FirmwareUpdateUtil;
import com.ratta.spnote.util.CreatePasswordProtectedZipExampleUtil;
import com.ratta.spnote.util.DecryptionZipUtil;
import com.ratta.spnote.util.FileMD5Util;
import com.ratta.spnote.util.FileUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.RandomUtil;
import com.ratta.spnote.util.ReadTxtUtil;
import com.ratta.spnote.util.UFile;
import com.ratta.suponote.firmware.model.FirmwareInfo;
import com.ratta.suponote.firmware.model.FirmwareInfoLine;
import com.ratta.suponote.firmware.model.FirmwareTask;
import com.ratta.suponote.firmware.model.FirmwareZip;
import com.ratta.suponote.firmware.result.FirmwareInfoResult;
import com.ratta.suponote.firmware.result.FirmwareTaskResult;
import com.ratta.suponote.firmware.service.FirmwareEquipService;
import com.ratta.suponote.firmware.service.FirmwareInfoService;
import com.ratta.suponote.firmware.service.FirmwareTaskService;
import com.ratta.suponote.firmware.service.FirmwareZipService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.system.ScheduleTask;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponote.stock.service.StockService;
import com.ratta.suponotes.util.MD5FileUtil;
import com.ratta.suponotes.util.ResultEnumUtil;
import net.lingala.zip4j.core.ZipFile;

/**
 * @author page 固件管理控制层 2018-10-31
 */
@Controller
@RequestMapping("/firmwareController")
public class FirmwareController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FirmwareInfoService firmwareInfoService;

	@Autowired
	private FirmwareTaskService firmwareTaskService;
	@Autowired
	private FirmwareEquipService firmwareEquipService;

	@Autowired
	private FirmwareZipService firmwareZipService;
	@Autowired
	private StockService stockService;
	@Autowired
	private JobTrigger jobTrigger;
	private String Path = "/";

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String version, String status, String audit_flag, String area,
			String custom, String configuration, String versionType, String beginTime, String endTime,
			HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("version", version);
		params.put("area", area);
		params.put("custom", custom);
		params.put("configuration", configuration);
		params.put("versionType", versionType);
		params.put("status", status);
		params.put("audit_flag", audit_flag);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return firmwareInfoService.query(ph, params);
	}

	@RequestMapping("/dataGridAudit")
	@ResponseBody
	public DataGrid dataGridAudit(PageHelper ph, String version, String status, String beginTime, String endTime,
			HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("version", version);
		params.put("status", status);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("sessionInfo", sessionInfo);
		return firmwareInfoService.query(ph, params);
	}

	@RequestMapping("/dataGridAuditTest")
	@ResponseBody
	public DataGrid dataGridAuditTest(PageHelper ph, String firmware_version, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("firmware_version", firmware_version);
		params.put("sessionInfo", sessionInfo);
		return firmwareTaskService.queryAuditTest(ph, params);
	}

	/**
	 * 跳转到固件版本管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/firmware/firmware";
	}

	/**
	 * 跳转到固件版本管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/managerAudit")
	public String managerAudit() {
		return "/admin/firmware/firmwareAudit";
	}

	/**
	 * 跳转到调度时间设置页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/quartz")
	public String quartz(HttpServletRequest request) {
		return "/admin/firmware/quartz";
	}

	/**
	 * dataGrid 获取用户数据表格
	 * 
	 * @param id 查询固件版本子类
	 * @param ph 分页帮助类
	 * @return easyui grid数据模型
	 */
	@RequestMapping("/firmwareInfoLine")
	@ResponseBody
	public DataGrid firmwareInfoLine(Long id, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(32);
		params.put("firm_id", id);
		params.put("merge_flag", "N");
		params.put("sessionInfo", sessionInfo);
		return firmwareInfoService.firmwareInfoLine(ph, params);
	}

	/**
	 * 审核测试
	 * 
	 * @param FirmwareAuditTest
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/firmwareAuditTest", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "firmwareTask_auditTest", operationType = OperateLogger.OperationType.U, table = "t_m_firmware_task")
	public Json firmwareAuditTest(FirmwareTask firmwareTask, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		firmwareTask.setIsFlash("N");
		int result = 0;
		try {
			Long id = firmwareTaskService.generateId();
			FirmwareInfo firmwareInfo = firmwareInfoService.query(firmwareTask.getId());
			firmwareTask.setCustom(firmwareInfo.getCustom());
			firmwareTask.setConfiguration(firmwareInfo.getConfiguration());
			firmwareTask.setArea(firmwareInfo.getArea());
			String[] equipment = firmwareTask.getEquuipmentNo().split(",");
			List<String> equipmentNo = new ArrayList<String>();
			for (String string : equipment) {
				equipmentNo.add(string);
			}
			firmwareTask.setId(id);
			if ("0".equals(firmwareTask.getPorformType())) {
				result = FirmwareUpdateUtil.push(equipmentNo, "03");
				if (result == 1) {
					firmwareTask.setFinishFlag("1");
					firmwareTask.setPerformTime("立即执行");
					FirmwareTaskResult firmwareTaskResult = firmwareTaskService.firmwareAuditTest(firmwareTask,
							sessionInfo);
					j.setSuccess(firmwareTaskResult.getValue() == 0);
					j.setMsg(ResultEnumUtil.getDesc(firmwareTaskResult,
							sessionInfo != null ? sessionInfo.getLocale() : null));
				} else {
					j.setMsg(FirmwareMessage.FIRMWARE_UPDATE_ERROR.getMessage());
					j.setSuccess(false);
				}
			} else {
				ScheduleTask job = new ScheduleTask();
				job.setName(id + "");
				job.setCron(firmwareTask.getPerformTime());
				job.setStatus("0");
				job.setBzcode("firmware_update");
				jobTrigger.add(job);
				firmwareTask.setFinishFlag("0");
				FirmwareTaskResult firmwareTaskResult = firmwareTaskService.firmwareAuditTest(firmwareTask,
						sessionInfo);
				j.setSuccess(firmwareTaskResult.getValue() == 0);
				j.setMsg(ResultEnumUtil.getDesc(firmwareTaskResult,
						sessionInfo != null ? sessionInfo.getLocale() : null));
			}

		} catch (Exception e) {
			j.setMsg(FirmwareMessage.FIRMWARE_UPDATE_ERROR.getMessage(e.getMessage()));
			logger.error(e.getMessage());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 跳转到审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/firmwareAudit")
	public String firmwareAudit(HttpServletRequest request, Long id) {
		request.setAttribute("id", id);
		return "/admin/firmware/audit";
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/commitAudit", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "Firmware_Audit", operationType = OperateLogger.OperationType.U, table = "t_fireware_info")
	public Json commitAudit(FirmwareInfo firmwareInfo, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			FirmwareInfoResult firmwareInfoResult = firmwareInfoService.commitAudit(firmwareInfo, sessionInfo);
			j.setSuccess(firmwareInfoResult.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(firmwareInfoResult, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 查看版本信息详情
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/detail")
	@OperateLogger(content = "firmwareInfo_detail", operationType = OperateLogger.OperationType.R, table = "t_fireware_info")
	public String detail(HttpServletRequest request, Long id) {
		FirmwareInfo firmwareInfo = firmwareInfoService.query(id);
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("firm_id", firmwareInfo.getId());
		List<FirmwareInfoLine> firmwareInfoLine = firmwareInfoService.firmwareInfoLine(params);
		request.setAttribute("firmwareInfoLine", firmwareInfoLine);
		request.setAttribute("firmwareInfo", firmwareInfo);
		return "/admin/firmware/detail";
	}

	/**
	 * 固件审核（查看版本信息详情）
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/detailAudit")
	@OperateLogger(content = "firmwareInfoAudit_detail", operationType = OperateLogger.OperationType.R, table = "t_fireware_info")
	public String detailAudit(HttpServletRequest request, Long id) {
		FirmwareInfo firmwareInfo = firmwareInfoService.query(id);
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("firm_id", firmwareInfo.getId());
		List<FirmwareInfoLine> firmwareInfoLine = firmwareInfoService.firmwareInfoLine(params);
		request.setAttribute("firmwareInfoLine", firmwareInfoLine);
		request.setAttribute("firmwareInfo", firmwareInfo);
		return "/admin/firmware/detail";
	}

	/**
	 * 固件审核（查看版本子类信息详情）
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/detailLine")
	@OperateLogger(content = "firmwareInfoLineAudit_detail", operationType = OperateLogger.OperationType.R, table = "t_fireware_info_line")
	public String detailLine(HttpServletRequest request, Long id) {
		FirmwareInfoLine firmwareInfoLine = firmwareInfoService.queryLine(id);
		request.setAttribute("firmwareInfoLine", firmwareInfoLine);
		return "/admin/firmware/detailLine";
	}

	/**
	 * 查看版本子类信息详情
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/detailLineAudit")
	@OperateLogger(content = "firmwareInfoLine_detail", operationType = OperateLogger.OperationType.R, table = "t_fireware_info_line")
	public String detailLineAudit(HttpServletRequest request, Long id) {
		FirmwareInfoLine firmwareInfoLine = firmwareInfoService.queryLine(id);
		request.setAttribute("firmwareInfoLine", firmwareInfoLine);
		return "/admin/firmware/detailLine";
	}

	/**
	 * 不分页查询已审核通过的固件版本记录
	 */
	@RequestMapping("/queryNoPage")
	@ResponseBody
	public List<FirmwareInfo> queryNoPage() {
		return firmwareInfoService.queryNoPage();
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
	@OperateLogger(content = "FirmwareInfo_delete", operationType = OperateLogger.OperationType.D, table = "t_fireware_info")
	public Json delete(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		FirmwareInfo firmwareInfo = firmwareInfoService.query(id);
		final String firmware_BucketName = ConfigUtil.get("Firmware_BucketName");
		try {
			FirmwareInfoResult result = firmwareInfoService.delete(id, sessionInfo);
			if (result.getValue() == 0) {
				deletefirmwareZip(firmwareInfo.getVersion(), firmware_BucketName);
				deletefirmwareLine(id, firmware_BucketName);
			}
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 审核测试删除
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteAuditTest")
	@ResponseBody
	@OperateLogger(content = "FirmwareAuditTest_delete", operationType = OperateLogger.OperationType.D, table = "t_m_stock")
	public Json deleteAuditTest(Long id, String version, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			// 判断还有几个设备在执行这个任务，如果仅剩一个，则删除这个任务
			Stock stock = stockService.query(id);
			int count = stockService.countByTaskID(stock.getTask_id());
			if (count == 1) {
				// 判断该固件是否还有审核测试记录，如没有，则将状态改为未审核
				int versions = firmwareTaskService.countByVersion(version);
				if (versions == 1) {
					FirmwareInfo firmwareInfo = new FirmwareInfo();
					firmwareInfo.setStatus("0");
					firmwareInfo.setVersion(version);
					// 更新固件状态为未审核
					firmwareInfoService.updateStatus(firmwareInfo);
				}
				// 判断如果是定时任务，则从jobTrigger中删除这个调度任务
				FirmwareTask firmwareTask = firmwareTaskService.query(new Long((long) stock.getTask_id()));
				if (!"立即执行".equals(firmwareTask.getPerformTime())) {
					ScheduleTask job = new ScheduleTask();
					job.setName(firmwareTask.getId() + "");
					jobTrigger.delete(job);
				}
				// 删除固件任务
				firmwareTaskService.deleteTaskById(new Long((long) stock.getTask_id()));
			}
			// 删除终端任务表记录和更新设备更新状态为初始状态
			FirmwareInfoResult result = firmwareInfoService.deleteAuditTest(stock.getEquipment_no(), sessionInfo);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转固件上传页面
	 * 
	 * @return
	 */
	@RequestMapping("/addfirmware")
	public String addfirmware() {
		return "/admin/firmware/firewareUpload";
	}

	/**
	 * firmwareUpload<br>
	 * 上传固件包<br>
	 * 
	 * @param file       固件包
	 * @param password   解压密码
	 * @param firewareId 设备型号集合
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/firmwareUpload")
	@ResponseBody
	public Json firmwareUpload(String password, String file_Path, String checkbo, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		// 需要保存的4个小固件
		List<FirmwareInfoLine> firmwareInfoLineList = new ArrayList<FirmwareInfoLine>();
		String secret = RandomUtil.getRandomStringByLength(9);
		// 大固件保存的路径 path
		String path = file_Path.substring(0, file_Path.lastIndexOf(Path));
		// 大固件解压缩的路径 path
		final String unpackPath = path + File.separator + "unpackPath";
		boolean result = true;
		Json json = new Json();
		String firmwareJson = "";
		String name = file_Path.substring(file_Path.lastIndexOf(Path) + 1, file_Path.length());
		logger.info("固件已经保存，准备解压!", "");
		String zippath = path + File.separator + name;
		System.out.println(zippath);
		// 解压缩固件包
		int a = CreatePasswordProtectedZipExampleUtil.UnpackZip(file_Path, password, unpackPath);
		if (a <= 0) {
			json.setSuccess(false);
			json.setMsg(FirmwareMessage.FIRMWARE_PASSWORD_ERROR.getMessage());
			return json;
		}
		// 获取指定文件路径里面的文件
		File[] files = FileUtil.listAllFiles(unpackPath);
		List<String> types = new ArrayList<String>();
		types.add("APP");
		types.add("SYSTEM");
		types.add("APP_LIB");
		types.add("SYSTEM_LIB");
		/* Iterator<String> sListIterator = types.iterator(); */
		// 获取json文件
		for (int i = 0; i < files.length; i++) {
			String filename = files[i].getName();
			String suffix = filename.substring(filename.lastIndexOf(".") + 1);
			if (suffix.toLowerCase().equals("json") || suffix.toLowerCase().equals("txt")) {
				// 读取json文件内容
				firmwareJson = ReadTxtUtil.readTxtFile(unpackPath + File.separator + filename);
			}
		}
		// 将json文件转成对象
		FirmwareInfo firmwareInfo = JSONObject.parseObject(firmwareJson, FirmwareInfo.class);
		List<FirmwareInfoLine> packageList = firmwareInfo.getPackageList();
		if (result == true) {
			String area = firmwareInfo.getVersion().split("\\.")[1].substring(1, 2);
			String custom = firmwareInfo.getVersion().split("\\.")[1].substring(2, 3);
			String equipment_config = firmwareInfo.getVersion().split("\\.")[1].substring(3);

			for (int i = 0; i < packageList.size(); i++) {
				// 将解压缩后的小固件上传到文件服务器
				String uFilePath = unpackPath + File.separator + packageList.get(i).getFile_name();
				String newName = "Original" + System.currentTimeMillis() + packageList.get(i).getFile_name();
				UFile.putFile(ConfigUtil.get("Firmware_BucketName"), newName, uFilePath);

				// 组装数据（4个小固件包中的原始包）
				packageList.get(i).setFileUrl(newName);
				packageList.get(i).setMerge_flag("N");
				firmwareInfoLineList.add(packageList.get(i));
			}
			String ver = firmwareInfo.getVersion();
			String[] versions = ver.split("\\.");
			String likeversion = versions[0] + "." + "_" + versions[1].substring(1) + ".%" + versions[3].substring(3);
			logger.info("需要查询的大固件版本号类型为：{}", likeversion);
			// 查询跟大固件版本号相似的大固件数据集合
			List<FirmwareInfo> firmwareInfoList = firmwareInfoService.queryFirmwareInfoList(likeversion, area, custom,
					equipment_config);
			String id = null;
			String verfirmwareInfo = null;
			String[] version = firmwareInfo.getVersion().split("\\.");
			logger.info("需要上传的固件版本号的编译时间：{}", version[2]);
			for (int i = 0; i < firmwareInfoList.size(); i++) {
				// 当客户端传过来的版本号与查询的版本还不一样时进入；（此处做判断是为了避免跟自身比较）
				if (!firmwareInfo.getVersion().equals(firmwareInfoList.get(i).getVersion())) {
					if (id == null) {
						// id==null说明循环是第一次，获取查询到的固件id和版本号
						id = String.valueOf(firmwareInfoList.get(i).getId());
						verfirmwareInfo = firmwareInfoList.get(i).getVersion();
					} else {
						// 将本次循环得到的版本号以"."分割
						String[] verfirmwareInfoLists = firmwareInfoList.get(i).getVersion().split("\\.");
						// 将本上次得到的版本号以"."分割
						String[] verfirmwareIns = verfirmwareInfo.split("\\.");
						// 判断本次循环得到的版本号是否小于上传固件的版本号
						logger.info("本次循环得到的固件版本号的编译时间：{}", verfirmwareInfoLists[2]);
						if (Integer.parseInt(verfirmwareInfoLists[2]) <= Integer.parseInt(version[2])) {
							// 判断版本编译时间模板状态是否大于上次
							if (Integer.parseInt(verfirmwareInfoLists[2]) >= Integer.parseInt(verfirmwareIns[2])) {
								// 判断序号是否大于上次的，如果大于则说明这次的数据比较新
								if (Integer.parseInt(verfirmwareInfoLists[3].substring(0, 3)) >= Integer
										.parseInt(verfirmwareIns[3].substring(0, 3))) {
									id = String.valueOf(firmwareInfoList.get(i).getId());
									verfirmwareInfo = firmwareInfoList.get(i).getVersion();
								}
							}
						}
					}
				}
			}
			// 根据大固件id查询需要合入的小固件的最新版本
			if (id != null) {
				logger.info("得到的大固件ID为：{}", id);
				for (int i = 0; i < types.size(); i++) {
					FirmwareInfoLine firmwareInfoLine = firmwareInfoService.queryFirmwareInfoLine(id, types.get(i));
					boolean flag = true;
					for (int k = 0; k < packageList.size(); k++) {
						if (packageList.get(k).getType().toLowerCase()
								.equals(firmwareInfoLine.getType().toLowerCase())) {
							flag = false;
							break;
						}
					}
					if (flag) {
						// 添加需要合入的小固件
						String newName = "Original" + System.currentTimeMillis() + firmwareInfoLine.getType() + ".zip";
						firmwareInfoLine.setMerge_flag("Y");
						firmwareInfoLine.setFile_name(firmwareInfoLine.getType() + ".zip");
						// 下载该小固件包
						try {
							InputStream InputStream = UFile.downloadFile(ConfigUtil.get("Firmware_BucketName"),
									firmwareInfoLine.getFileUrl());
							firmwareInfoLine.setFileUrl(newName);
							packageList.add(firmwareInfoLine);
							if (InputStream == null) {
								System.gc();
								super.deleteDirectory(path);
								logger.error("需要组合的小固件包不存在");
								json.setMsg(FirmwareMessage.FIRMWARE__UPLOAD_ERROR.getMessage());
								json.setSuccess(result);
								return json;
							}
							String destination = unpackPath + File.separator + firmwareInfoLine.getType() + ".zip";
							FileUtil.getFile(destination, InputStream);
							// 上传该小固件包
							UFile.putFile(ConfigUtil.get("Firmware_BucketName"), newName, destination);

							// 组装数据(4个小固件包中的增量包)
							firmwareInfoLineList.add(firmwareInfoLine);
						} catch (Exception e) {
							e.printStackTrace();
							e.getMessage();
						}
					}
				}
			}
			// 1、保存大固件数据
			FirmwareInfo firmware = new FirmwareInfo();
			firmware.setArea(area);
			firmware.setCustom(custom);
			firmware.setConfiguration(equipment_config);
			// 添加大固件信息
			FirmwareInfo firmwareinfo = firmwareInfoService.addFirmwareInfo(firmware, firmwareInfo, sessionInfo);
			// 添加大固件跟设备型号对应关系信息
			List<String> equipmentids = new ArrayList<String>();
			String[] equipmentIds = checkbo.split(",");
			for (int i = 0; i < equipmentIds.length; i++) {
				equipmentids.add(equipmentIds[i]);
			}
			firmwareEquipService.addFirmwareEquipment(firmwareinfo.getVersion(), equipmentids);
			// 2、保存4个小固件数据
			for (int i = 0; i < firmwareInfoLineList.size(); i++) {
				firmwareInfoLineList.get(i).setFirm_id(firmwareinfo.getId().toString());
				firmwareInfoService.addFirmwareInfoLin(firmwareInfoLineList.get(i));
			}
			// 获取指定文件路径里面的文件
			File[] filelist = FileUtil.listAllFiles(unpackPath);
			// 组合15中可能会需要的大固件包
			result = callable(filelist, unpackPath, firmwareInfo, secret, sessionInfo.getUsername(), ver, packageList);
		}
		System.gc();
		super.deleteDirectory(path);
		json.setMsg(result ? FirmwareMessage.FIRMWARE_UPLOAD_SUCCESS.getMessage() : FirmwareMessage.FIRMWARE__UPLOAD_ERROR.getMessage());
		json.setSuccess(result);
		return json;
	}

	/**
	 * callable<br>
	 * 开启带返回值的线程<br>
	 * 
	 * @param filelist 固件解压后目录里面的文件
	 * @param path     固件解压缩的目录
	 * @param combPath 新大固件压缩包存放的路径
	 * @param password 压缩密码
	 * @param userNmae 操作员
	 * @param version  版本号
	 * @return
	 */
	@SuppressWarnings("finally")
	public boolean callable(File[] filelist, final String path, final FirmwareInfo firmware, final String password,
			final String userNmae, final String version, final List<FirmwareInfoLine> packageList) {
		final String filePath = ConfigUtil.get("Firmware_BucketName");
		List<FirmwareInfoLine> firmwareInfoLines = firmware.getPackageList();
		String name1 = null;
		String name2 = null;
		String name3 = null;
		String name4 = null;
		for (int i = 0; i < filelist.length; i++) {
			String filename = filelist[i].getName();
			String suffix = filename.substring(filename.lastIndexOf(".") + 1);
			if (suffix.toLowerCase().equals("zip")) {
				for (int j = 0; j < firmwareInfoLines.size(); j++) {
					if (filename.equals(firmwareInfoLines.get(j).getFile_name())) {
						if ("app".equals(firmwareInfoLines.get(j).getType().toLowerCase())) {
							name1 = filename;
						}
						if ("system".equals(firmwareInfoLines.get(j).getType().toLowerCase())) {
							name2 = filename;
						}
						if ("app_lib".equals(firmwareInfoLines.get(j).getType().toLowerCase())) {
							name3 = filename;
						}
						if ("system_lib".equals(firmwareInfoLines.get(j).getType().toLowerCase())) {
							name4 = filename;
						}
					}
				}
			}
		}
		final String filename1 = name1;
		final String filename2 = name2;
		final String filename3 = name3;
		final String filename4 = name4;
		final String fileMD5App = FileMD5Util.getFileMD5(new File(path + File.separator + filename1));
		final String fileMD5System = FileMD5Util.getFileMD5(new File(path + File.separator + filename2));
		final String fileMD5AppLib = FileMD5Util.getFileMD5(new File(path + File.separator + filename3));
		final String fileMD5SystemLib = FileMD5Util.getFileMD5(new File(path + File.separator + filename4));
		final String Y = "Y";
		final String N = "N";
		int i = 0;
		boolean result = false;

		ExecutorService exec = Executors.newFixedThreadPool(15);
		// 1、只需要A文件(APP)
		Callable<Integer> app = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "app";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, N, N, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 2、只需要S文件(system)
		Callable<Integer> system = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "system";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename2));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, Y, N, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 3、只需要LB文件(lib_app)
		Callable<Integer> lib_app = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "lib_app";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename3));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, N, Y, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 4、只需要LS文件(lib_system)
		Callable<Integer> lib_system = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "lib_system";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, N, N, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 5、需要A和B文件(App和system)
		Callable<Integer> a_s = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_s";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename2));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, Y, N, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 6、需要A和C文件(App和lib_App)
		Callable<Integer> a_la = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_la";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename3));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, N, Y, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 7、需要A和D文件(App和lib_system)
		Callable<Integer> a_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, N, N, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 8、需要B和C文件(system和lib_app)
		Callable<Integer> s_la = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "s_la";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename2));
					files.add(new File(path + File.separator + filename3));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, Y, Y, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 9、需要B和D文件(system和lib_system)
		Callable<Integer> s_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "s_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename2));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, Y, N, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 10、需要C和D文件(lib_app和lib_system)
		Callable<Integer> la_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "la_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename3));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, N, Y, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 11、需要A和B和C文件(app和system和lib_app)
		Callable<Integer> a_s_la = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_s_la";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename2));
					files.add(new File(path + File.separator + filename3));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, Y, Y, N,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 12、需要A和B和D文件(app和system和lib_system)
		Callable<Integer> a_s_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_s_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename2));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, Y, N, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 13、需要A和C和D文件(app和lib_app和lib_system)
		Callable<Integer> a_la_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_la_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename3));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, N, Y, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 14、需要B和C和D文件(system和lib_app和lib_system)
		Callable<Integer> s_la_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "s_la_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename2));
					files.add(new File(path + File.separator + filename3));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, N, Y, Y, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		// 15、全部都需要(app和system和lib_app和lib_system)
		Callable<Integer> a_s_la_ls = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String depath = "a_s_la_ls";
				String name = "fireware_" + depath + ".zip";
				String jsonName = "config_" + depath + ".json";
				String jsonPath = path + File.separator + jsonName;
				// 创建json文件
				FirmwareInfo firmwareInfo = new FirmwareInfo();
				firmwareInfo.setVersion(firmwareInfo.getVersion());
				List<FirmwareInfoLine> list = new ArrayList<FirmwareInfoLine>();
				for (int j = 0; j < packageList.size(); j++) {
					if ("app".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5App);
						list.add(packageList.get(j));
					}
					if ("system".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5System);
						list.add(packageList.get(j));
					}
					if ("app_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5AppLib);
						list.add(packageList.get(j));
					}
					if ("system_lib".equals(packageList.get(j).getType().toLowerCase())) {
						packageList.get(j).setMd5(fileMD5SystemLib);
						list.add(packageList.get(j));
					}
				}
				firmwareInfo.setVersion(firmware.getVersion());
				firmwareInfo.setPackageList(list);
				String json = JSON.toJSONString(firmwareInfo, SerializerFeature.WriteMapNullValue);
				logger.info("json数据是：" + json);
				createJsonFile(jsonPath, json);
				try {
					ArrayList<File> files = new ArrayList<File>();
					files.add(new File(path + File.separator + filename1));
					files.add(new File(path + File.separator + filename2));
					files.add(new File(path + File.separator + filename3));
					files.add(new File(path + File.separator + filename4));
					files.add(new File(jsonPath));
					FirmwareZip firmwareZip = uploadFirmwareZip(files, name, path, password, version, Y, Y, Y, Y,
							userNmae, depath);
					firmwareZipService.addFirmwareZip(firmwareZip);
					return 1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("固件组合失败失败,错误原因:{}", e.getMessage());
					// 数据重置，删除数据库垃圾数据和文件服务器垃圾数据
					deletefirmwareZip(version, filePath);
					return 0;
				}
			}
		};
		try {
			Integer fireware1 = exec.submit(app).get();
			Integer fireware2 = exec.submit(system).get();
			Integer fireware3 = exec.submit(lib_app).get();
			Integer fireware4 = exec.submit(lib_system).get();
			Integer fireware5 = exec.submit(a_s).get();
			Integer fireware6 = exec.submit(a_la).get();
			Integer fireware7 = exec.submit(a_ls).get();
			Integer fireware8 = exec.submit(s_la).get();
			Integer fireware9 = exec.submit(s_ls).get();
			Integer fireware10 = exec.submit(la_ls).get();
			Integer fireware11 = exec.submit(a_s_la).get();
			Integer fireware12 = exec.submit(a_s_ls).get();
			Integer fireware13 = exec.submit(a_la_ls).get();
			Integer fireware14 = exec.submit(s_la_ls).get();
			Integer fireware15 = exec.submit(a_s_la_ls).get();
			i = fireware1 == 1 && fireware2 == 1 && fireware3 == 1 && fireware4 == 1 && fireware5 == 1 && fireware6 == 1
					&& fireware7 == 1 && fireware8 == 1 && fireware9 == 1 && fireware10 == 1 && fireware11 == 1
					&& fireware12 == 1 && fireware13 == 1 && fireware14 == 1 && fireware15 == 1 ? 1 : 0;
			logger.info("固件组合:{}", i > 0 ? "成功" : "失败");
			result = i > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("固件组合失败:{}", e.getMessage());
			result = false;
		} finally {
			exec.shutdown();
			return result;
		}
	}

	/**
	 * uploadFirmwareZip<br>
	 * 组合大固件并加密压缩并上传到服务器<br>
	 * 
	 * @param files          等待压缩的小固件文件集合
	 * @param name           压缩后的大固件名称
	 * @param combPath       压缩包存放的路径
	 * @param password       压缩密码
	 * @param version        大固件版本号
	 * @param appflag        是否包含APP固件
	 * @param systemflag     是否包含SYSTEM固件
	 * @param lib_appflag    是否包含LIB_APP固件
	 * @param lib_syatemflag 是否包含LIB_SYSTEM固件
	 * @param userNmae       操作员
	 * @return
	 */
	public FirmwareZip uploadFirmwareZip(ArrayList<File> files, String name, String combPath, String password,
			String version, String appflag, String systemflag, String lib_appflag, String lib_systemflag,
			String userNmae, String depath) {
		File toFile = new File(combPath + File.separator + depath);
		if (!toFile.exists()) {
			toFile.mkdirs(); // 创建目录
		}
		for (int i = 0; i < files.size(); i++) {
			try {
				DecryptionZipUtil.copyFile(files.get(i),
						new File(combPath + File.separator + depath + File.separator + files.get(i).getName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			DecryptionZipUtil.zipFilesAndEncrypt(combPath + File.separator + depath, combPath + File.separator + name,
					password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(combPath + File.separator + name);
			// 加密压缩固件
			CreatePasswordProtectedZipExampleUtil.CompressionFiles(zipFile, files, password);
			// 上传固件
			// 将FIle文件转换成MultipartFile
			String newName = System.currentTimeMillis() + name;
			try {
				String uFilePath = combPath + File.separator + name;
				UFile.putFile(ConfigUtil.get("Firmware_BucketName"), newName, uFilePath);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("组合固件上传服务器失败:{}", e.getMessage());
			}
			String fileMd5 = FileMD5Util.getFileMD5(new File(combPath + File.separator + name));
			FirmwareZip firmwareZip = new FirmwareZip();
			firmwareZip.setVersion(version);
			firmwareZip.setAppFlag(appflag);
			firmwareZip.setSystemFlag(systemflag);
			firmwareZip.setLibAppFlag(lib_appflag);
			firmwareZip.setLibSystemFlag(lib_systemflag);
			firmwareZip.setUrl(newName);
			firmwareZip.setOpUser(userNmae);
			firmwareZip.setMd5(fileMd5);
			firmwareZip.setSecret(password);
			return firmwareZip;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * deletefirmwareZip<br>
	 * 删除数据库垃圾数据和文件服务器垃圾数据<br>
	 * 
	 * @param version  固件版本号
	 * @param filePath 文件服务器的路径
	 */
	public void deletefirmwareZip(String version, String filePath) {
		// 根据当前版本号查询固件数据
		List<FirmwareZip> firmwareZipList = firmwareZipService.firmwareZipList(version);
		// 根据当前版本号删除固件数据
		firmwareZipService.delete(version);
		// 删除文件服务器上的固件
		for (int i = 0; i < firmwareZipList.size(); i++) {
			UFile.deleteFile(filePath, firmwareZipList.get(i).getUrl());
		}
	}

	/**
	 * deletefirmwareLine<br>
	 * 删除数据库垃圾数据和文件服务器垃圾数据<br>
	 * 
	 * @param id       id
	 * @param filePath 文件服务器的路径
	 */
	public void deletefirmwareLine(long id, String filePath) {
		// 根据当前版本号查询固件数据
		List<FirmwareInfoLine> firmwareLineList = firmwareInfoService.firmwareInfoLineList(id);
		// 根据当前版本号删除固件数据
		firmwareInfoService.deleteLine(id);
		// 删除文件服务器上的固件
		for (int i = 0; i < firmwareLineList.size(); i++) {
			UFile.deleteFile(filePath, firmwareLineList.get(i).getFileUrl());
		}
	}

	/**
	 * 保存大固件
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadFirmware", method = RequestMethod.POST)
	@ResponseBody
	public Json uploadFirmware(@RequestParam(value = "file") MultipartFile file, String password,
			HttpServletRequest request, HttpServletResponse response) {
		String cpath = request.getSession().getServletContext().getRealPath(File.separator) + "file" + File.separator
				+ "firmware" + File.separator + "zipFile" + File.separator + System.currentTimeMillis();
		Json json = new Json();
		String old_name = file.getOriginalFilename();
		if (!old_name.toLowerCase().endsWith("zip")) {
			logger.info("格式:{}", old_name);
			// 判断是否是zip文件
			json.setSuccess(false);
			json.setMsg(FirmwareMessage.FIRMWARE__FORMAT_ERROR.getMessage());
			return json;
		}
		// 判断该大固件是否已经存在
		String version = old_name.substring(0, old_name.lastIndexOf("."));
		System.out.println(version);
		Long in = firmwareEquipService.queryCount(version);
		if (in > 0) {
			json.setSuccess(false);
			json.setMsg("该固件版本已经存在！");
			return json;
		}
		boolean result = super.write(cpath, old_name, file);
		// 大固件保存的路径 path
		String path = cpath.substring(0, cpath.lastIndexOf(Path));
		// 大固件解压缩的路径 path
		final String unpackPath = path + File.separator + System.currentTimeMillis() + "unpackPath";
		String firmwareJson = "";
		logger.info("固件已经保存，准备解压!", "");
		// 解压缩固件包
		int a = CreatePasswordProtectedZipExampleUtil.UnpackZip(cpath + File.separator + old_name, password,
				unpackPath);
		if (a <= 0) {
			json.setSuccess(false);
			json.setMsg(FirmwareMessage.FIRMWARE_PASSWORD_ERROR.getMessage());
			return json;
		}
		// 获取指定文件路径里面的文件
		File[] files = FileUtil.listAllFiles(unpackPath);
		List<String> types = new ArrayList<String>();
		types.add("APP");
		types.add("SYSTEM");
		types.add("APP_LIB");
		types.add("SYSTEM_LIB");
		Iterator<String> sListIterator = types.iterator();
		if (files.length >= 2) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if (fileName.toLowerCase().equals("app.zip") || fileName.toLowerCase().equals("app_lib.zip")
						|| fileName.toLowerCase().equals("system.zip")
						|| fileName.toLowerCase().equals("system_lib.zip")
						|| fileName.toLowerCase().equals("config.json")) {
					result = true;
				} else {
					result = false;
					System.gc();
					super.deleteDirectory(cpath);
					super.deleteDirectory(unpackPath);
					json.setSuccess(result);
					json.setMsg(FirmwareMessage.FIRMWARE__NAME_ERROR.getMessage());
					return json;
				}
			}
		} else {
			result = false;
			System.gc();
			super.deleteDirectory(cpath);
			super.deleteDirectory(unpackPath);
			json.setSuccess(result);
			json.setMsg(FirmwareMessage.FIRMWARE__UPLOAD_ERROR.getMessage());
			return json;
		}
		// 获取json文件
		for (int i = 0; i < files.length; i++) {
			String filename = files[i].getName();
			String suffix = filename.substring(filename.lastIndexOf(".") + 1);
			if (suffix.toLowerCase().equals("json") || suffix.toLowerCase().equals("txt")) {
				// 读取json文件内容
				firmwareJson = ReadTxtUtil.readTxtFile(unpackPath + File.separator + filename);
			}
		}
		// 将json文件转成对象
		try {
			FirmwareInfo firmwareInfo = JSONObject.parseObject(firmwareJson, FirmwareInfo.class);
			List<FirmwareInfoLine> packageList = firmwareInfo.getPackageList();
			// 判断是否需要合入文件;判断文件MD5是否正确;
			// 此时暂时不在循环过程中上传小固件，避免后面循环中出现文件效验失败后，服务器产生垃圾文件，确保所有文件都合格时，在循环上传。
			while (sListIterator.hasNext()) {
				String type = sListIterator.next().toLowerCase();
				for (int i = 0; i < packageList.size(); i++) {
					String gettype = packageList.get(i).getType().toLowerCase();
					if (type.toLowerCase().equals(gettype)) {
						String fileMd5 = MD5FileUtil.getFileMD5(new File(unpackPath + File.separator + packageList.get(i).getFile_name()));
						if (!fileMd5.equals(packageList.get(i).getMd5())) {
							logger.error("文件‘" + packageList.get(i).getFile_name() + "’MD5效验未通过");
							result = false;
							break;
						}
						sListIterator.remove();
					}
				}
			}
			String ver = firmwareInfo.getVersion();
			String[] versions = ver.split("\\.");
			String area = versions[1].substring(1, 2);
			String custom = versions[1].substring(2, 3);
			String equipment_config = versions[1].substring(3);

			String likeversion = versions[0] + "." + "_" + versions[1].substring(1) + ".%" + versions[3].substring(3);
			// 查询跟大固件版本号相似的大固件数据集合
			List<FirmwareInfo> firmwareInfoList = firmwareInfoService.queryFirmwareInfoList(likeversion, area, custom,
					equipment_config);
			String id = null;
			String verfirmwareInfo = null;
			for (int i = 0; i < firmwareInfoList.size(); i++) {
				// 当客户端传过来的版本号与查询的版本还不一样时进入；（此处做判断是为了避免跟自身比较）
				if (!firmwareInfo.getVersion().equals(firmwareInfoList.get(i).getVersion())) {
					// 将本次循环得到的版本号以"."分割
					String[] verfirmwareInfoLists = firmwareInfoList.get(i).getVersion().split("\\.");
					// 判断是否有比本次版本更早的模板编译时间
					if (Long.parseLong(verfirmwareInfoLists[2]) <= Long.parseLong(versions[2])) {
						// 判断是否有比本次版本序列号更低的版本号
						if (Integer.parseInt(verfirmwareInfoLists[3].substring(0, 3)) < Integer
								.parseInt(versions[3].substring(0, 3))) {
							if (id == null) {
								// id==null说明循环是第一次，获取查询到的固件id和版本号
								id = String.valueOf(firmwareInfoList.get(i).getId());
								verfirmwareInfo = firmwareInfoList.get(i).getVersion();
							} else {
								// 将本上次得到的版本号以"."分割
								String[] verfirmwareIns = verfirmwareInfo.split("\\.");
								// 判断版本编译时间模板状态是否大于上次
								if (Integer.parseInt(verfirmwareInfoLists[2]) > Integer.parseInt(verfirmwareIns[2])) {
									// 判断序号是否大于上次的，如果大于则说明这次的数据比较新
									if (Integer.parseInt(verfirmwareInfoLists[3].substring(0, 3)) > Integer
											.parseInt(verfirmwareIns[3].substring(0, 3))) {
										id = String.valueOf(firmwareInfoList.get(i).getId());
										verfirmwareInfo = firmwareInfoList.get(i).getVersion();
									}
								}
							}
						}
					}
				}
			}
			if (id == null) {
				if (files.length < 5) {
					result = false;
					System.gc();
					super.deleteDirectory(cpath);
					super.deleteDirectory(unpackPath);
					json.setSuccess(result);
					json.setMsg(FirmwareMessage.FIRMWARE__ERROR.getMessage());
					return json;
				}
			}
		} catch (Exception e2) {
			e2.getMessage();
			result = false;
			System.gc();
			super.deleteDirectory(cpath);
			super.deleteDirectory(unpackPath);
			json.setSuccess(result);
			json.setMsg(FirmwareMessage.FIRMWARE__UPLOAD_ERROR.getMessage());
			return json;
		}
		System.gc();
		super.deleteDirectory(unpackPath);
		json.setSuccess(result);
		json.setMsg(result ? FirmwareMessage.FIRMWARE_UPLOAD_SUCCESS.getMessage()
				: FirmwareMessage.FIRMWARE__UPLOAD_ERROR.getMessage());
		json.setErrorinfo(cpath + File.separator + old_name);
		return json;
	}

	/**
	 * createJsonFile<br>
	 * 创建json文件<br>
	 * 
	 * @param path 文件路径
	 * @param json 文件内容
	 */
	public void createJsonFile(String path, String json) {
		byte[] sourceByte = json.getBytes();
		if (null != sourceByte) {
			try {
				File file = new File(path);
				if (!file.exists()) {
					File dir = new File(file.getParent());
					dir.mkdirs();
					file.createNewFile();
				}
				FileOutputStream outStream = new FileOutputStream(file);
				outStream.write(sourceByte);
				outStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public FirmwareInfo getBean(Long id) {
		return firmwareInfoService.query(id);
	}
}
