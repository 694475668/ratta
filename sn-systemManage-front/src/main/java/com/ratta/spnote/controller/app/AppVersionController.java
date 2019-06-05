package com.ratta.spnote.controller.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.ratta.spnote.controller.BaseController;
import com.ratta.spnote.util.ApkUtil;
import com.ratta.spnote.util.ConfigUtil;
import com.ratta.spnote.util.Json;
import com.ratta.spnote.util.MD5Util;
import com.ratta.spnote.util.OperateLogger;
import com.ratta.spnote.util.UFile;
import com.ratta.suponote.app.model.AppVersion;
import com.ratta.suponote.app.result.AppVersionResult;
import com.ratta.suponote.app.service.AppVersionService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.ResultEnumUtil;

/**
 * @author page app版本管理控制层 2019-02-07
 */
@Controller
@RequestMapping("/appVersionController")
public class AppVersionController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	final String app_BucketName = ConfigUtil.get("App_BucketName");
	@Autowired
	private AppVersionService appVersionService;
	private final String cpath = "/file/app/version";

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, String appName, String appVersion, String appEnvironment,
			String updateFlag,String auditingFlag,String deployFlag,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("appName", appName);
		params.put("appVersion", appVersion);
		params.put("updateFlag", updateFlag);
		params.put("appEnvironment", appEnvironment);
		params.put("auditingFlag", auditingFlag);
		params.put("deployFlag", deployFlag);
		params.put("sessionInfo", sessionInfo);
		return appVersionService.query(ph, params);
	}

	/**
	 * 跳转到app版本管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/app/appVersion/appVersion";
	}

	/**
	 * 查询app名称列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryAllAppVersion")
	@ResponseBody
	public List<String> queryAllAppVersion() {
		return appVersionService.queryAllAppVersion();
	}

	/**
	 * 查询app版本列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryVersionByAppName")
	@ResponseBody
	public List<String> queryVersionByAppName(String appName) {
		return appVersionService.queryVersionByAppName(appName);
	}

	/**
	 * 跳转到版本上传页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addAppUpload")
	public String addAppUpload() {
		return "/admin/app/appVersion/addAppUpload";
	}

	/**
	 * 版本上传
	 * @param appVersion
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "app_version_add", operationType = OperateLogger.OperationType.C, table = "t_app_version")
	public Json add(AppVersion appVersion, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		AppVersionResult result = null;
		try {
			String ver_url = sessionInfo.getUrl();
			String versionNo = ApkUtil.getVersionCode(ver_url);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appName", appVersion.getAppName());
			params.put("versionNo", versionNo);
			List<AppVersion> versionList = appVersionService.query(params);
			if (versionList != null && versionList.size() > 0) {
				j.setMsg(AppUploadMessage.APP_VERSION_EXIST.getMessage());
				j.setSuccess(false);
				return j;
			}
			
			String key =appVersion.getAppName()+"_"+appVersion.getAppVersion()+"_"+System.currentTimeMillis();
			boolean ufileResult = UFile.putFile(app_BucketName, key, ver_url);
			if(ufileResult) {
				appVersion.setMd5(MD5Util.getMD5(ver_url));
				appVersion.setUrl(key);
				appVersion.setVersionNo(versionNo);
				
				String fileName = appVersion.getFileName();
				String appEnvironment=(fileName.substring(0, fileName.length()-5));
				appVersion.setAppEnvironment(appEnvironment.substring(appEnvironment.lastIndexOf("_")+1));
				
				appVersion.setOpUser(sessionInfo.getUsername());
				result = appVersionService.save(appVersion);
				if(result.getValue() == 0) {
					super.removeFile(ver_url);
					sessionInfo.setUrl(null);
				}
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
	 * 删除
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@OperateLogger(content = "app_version_delete", operationType = OperateLogger.OperationType.D, table = "t_app_version")
	public Json delete(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		AppVersion appVersion = appVersionService.query(id);
		try {
			AppVersionResult result = appVersionService.delete(id, sessionInfo);
			if (result.getValue() == 0) {
				UFile.deleteFile(app_BucketName, appVersion.getUrl());
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
	 * 撤销
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/undo")
	@ResponseBody
	@OperateLogger(content = "app_version_deploy_undo", operationType = OperateLogger.OperationType.U, table = "t_app_version")
	public Json undo(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			AppVersionResult result = appVersionService.updateDeployFlagById(id, "4");
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}

	/**
	 * 发布
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/deploy")
	@ResponseBody
	@OperateLogger(content = "app_version_deploy", operationType = OperateLogger.OperationType.U, table = "t_app_version")
	public Json deploy(Long id, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			AppVersionResult result = appVersionService.deploy(id);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}
	
	
	/**
	 * 跳转到审核页面
	 * @return
	 */
	@RequestMapping("/toAuditing")
	public String toAuditing(HttpServletRequest request, Long id) {
		request.setAttribute("id", id);
		return "/admin/app/appVersion/audit";
	}
	
	/**
	 * 审核
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/auditing", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "app_audit", operationType = OperateLogger.OperationType.U, table = "t_app_version")
	public Json auditing(AppVersion appVersion, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			AppVersionResult result = appVersionService.updateAuditingFlag(appVersion);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}
	
	
	/**
	 * 跳转到灰度页面
	 * @return
	 */
	@RequestMapping("/toGray")
	public String toGray(HttpServletRequest request, Long id) {
		request.setAttribute("id", id);
		return "/admin/app/appVersion/gray";
	}
	
	/**
	 * 灰度
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gray", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogger(content = "app_gray", operationType = OperateLogger.OperationType.U, table = "t_app_gray_deploy")
	public Json gray(long id,String userid,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		try {
			AppVersionResult result = appVersionService.appGrayDeploy(id,userid);
			j.setSuccess(result.getValue() == 0);
			j.setMsg(ResultEnumUtil.getDesc(result, sessionInfo != null ? sessionInfo.getLocale() : null));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 删除文件
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public void deleteFile(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String url=sessionInfo.getUrl();
		if(url!=null){
			super.removeFile(url);
			sessionInfo.setUrl(null);
		}
	}
	
	/**
	 * 上传应用版本信息
	 * 
	 * @param file
	 * @param appid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Json upload(@RequestParam MultipartFile file, HttpServletRequest request, HttpSession session) {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String fileUrl = sessionInfo.getUrl();
		if (fileUrl != null) {
			super.removeFile(fileUrl);
		}
		String fileName = file.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath(cpath);
		boolean result = super.write(path, fileName, file);
		logger.info("写文件结果{}", result);
		
        //解析app获取部分信息
		String filePath = path + File.separator + fileName;
		logger.info("获取文件的路径{}", filePath);
		json.setMsg(result ? AppUploadMessage.APP_VER_UPLOAD_SUCCESS.getMessage()
				: AppUploadMessage.APP_VER_UPLOAD_FAIL.getMessage());
		json.setSuccess(result);
		json.setObj(ApkUtil.getPackage(filePath));
		json.setVerinfo(ApkUtil.getVersion(filePath));
		json.setErrorinfo(ApkUtil.getApkLabel(filePath, ""));
		json.setIconinfo(fileName);
		
		sessionInfo.setUrl(filePath);
		return json;
	}

	/**
	 * 下载
	 * @param request
	 * @param response
	 * @param url 下载地址
	 * @param fileName 文件名称
	 * @throws IOException
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void download(String fileName, HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException {
		byte[] buffer = new byte[1024];
		try {
			// 读取文件
			InputStream fis = UFile.downloadFile(app_BucketName, url);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
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
	 * 根据主键查询 bean，记录日志时候调用，方法名约定，不能随意更改
	 * 
	 * @param id
	 * @return
	 */
	public AppVersion getBean(Long id) {
		return appVersionService.query(id);
	}
}
