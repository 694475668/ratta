package com.ratta.suponote.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.suponote.app.dao.AppFixPointDao;
import com.ratta.suponote.app.dao.AppGrayDeployDao;
import com.ratta.suponote.app.dao.AppVersionDao;
import com.ratta.suponote.app.model.AppGrayDeploy;
import com.ratta.suponote.app.model.AppVersion;
import com.ratta.suponote.app.result.AppVersionResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 
 * app版本业务层 
 * 2019-03-07
 */
@Service("appVersionService")
@Transactional(rollbackFor = Exception.class)
public class AppVersionBean implements AppVersionService {
	static final int FIXPOINT_LIMIT = 8000;
	private static Logger logger = LoggerFactory.getLogger(AppVersionBean.class);
	@Autowired
	private AppVersionDao appVersionDao;
	@Autowired
	private AppFixPointDao appFixPointDao;
	@Autowired
	private AppGrayDeployDao appGrayDeployDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询app版本信息,查询参数:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (ph == null) {
			logger.error("分页数据为空");
			return dg;
		}
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		try {
			dg.setRows(appVersionDao.query(params));
			dg.setTotal(appVersionDao.count(params));
			logger.info("查询app版本信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public AppVersion query(Long id) {
		logger.info("根据id:{}查询app版本信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
		    AppVersion appVersion = appVersionDao.query(id);
			logger.info("查询app版本信息成功:{}", appVersion);
			return appVersion;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> queryAllAppVersion() {
		return appVersionDao.queryAllAppVersion();
	}

	@Override
	public List<String> queryVersionByAppName(String appName) {
		return appVersionDao.queryVersionByAppName(appName);
	}

	@Override
	@Transactional
	public AppVersionResult delete(Long id, SessionInfo sessionInfo) {
		logger.info("删除id={}的app版本信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("app版本信息id为空");
			return AppVersionResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return AppVersionResult.SESSIONINFO_NULL;
		}
		try {
			appFixPointDao.deleteFixPointByAppId(id);
			logger.info("删除id={}的app修复点信息成功", id);
			appVersionDao.delete(id);
			logger.info("删除id={}的app版本信息成功", id);
			return AppVersionResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppVersionResult.REQUEST_FAIL;
		}
	}

	@Override
	public List<AppVersion> query(Map<String, Object> params) {
		return appVersionDao.query(params);
	}

	@Override
	public AppVersionResult save(AppVersion appVersion) {
		logger.info("新增app版本信息");
		if (appVersion == null) {
			logger.error("app版本信息为空");
			return AppVersionResult.REQUEST_FAIL;
		}
		try {
			appVersionDao.save(appVersion);
			logger.info("新增app版本信息成功");
			return AppVersionResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppVersionResult.REQUEST_FAIL;
		}
	}

	@Override
	public AppVersionResult updateDeployFlagById(Long id, String deployFlag) {
		logger.info("修改app版本发布状态");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("deployFlag", deployFlag);
		try {
			appVersionDao.updateDeployFlagById(params);
			logger.info("修改app版本发布状态成功");
			return AppVersionResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppVersionResult.REQUEST_FAIL;
		}
	}
	
	@Override
	@Transactional
	public AppVersionResult deploy(Long id) {
		logger.info("app版本发布");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("deployFlag", "3");
		try {
			appVersionDao.updateDeployFlagById(params);
			appGrayDeployDao.updateByAppId(id);
			logger.info("app版本发布成功");
			return AppVersionResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppVersionResult.REQUEST_FAIL;
		}
	}

	@Override
	public AppVersionResult updateAuditingFlag(AppVersion appVersion) {
		logger.info("修改app版本审核状态");
		try {
			appVersionDao.updateAuditingFlag(appVersion);
			logger.info("修改app版本审核状态成功");
			return AppVersionResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppVersionResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public AppVersionResult appGrayDeploy(long id, String userid) {
		logger.info("app灰度发布开始：");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("deployFlag", "2");
		try {
			String [] userids = userid.split(",");
			for (String string : userids) {
				appGrayDeployDao.deleteByParam(id,string);
				AppGrayDeploy appGrayDeploy = new AppGrayDeploy();
				appGrayDeploy.setAppId(id);
				appGrayDeploy.setUserId(Long.parseLong(string));
				appGrayDeployDao.save(appGrayDeploy);
			}
			appVersionDao.updateDeployFlagById(params);
			logger.info("app灰度发布成功");
			return AppVersionResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppVersionResult.REQUEST_FAIL;
		}
	}
}
