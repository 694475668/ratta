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
import com.ratta.suponote.app.dao.AppVersionDao;
import com.ratta.suponote.app.model.AppFixPoint;
import com.ratta.suponote.app.model.AppVersion;
import com.ratta.suponote.app.result.AppFixPointResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 
 * app修复点业务层 
 * 2019-03-07
 */
@Service("appFixPointService")
@Transactional(rollbackFor = Exception.class)
public class AppFixPointBean implements AppFixPointService {
	static final int FIXPOINT_LIMIT = 8000;
	private static Logger logger = LoggerFactory.getLogger(AppFixPointBean.class);
	@Autowired
	private AppFixPointDao appFixPointDao;
	@Autowired
	private AppVersionDao appVersionDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询app修复点信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(appFixPointDao.query(params));
			dg.setTotal(appFixPointDao.count(params));
			logger.info("查询app修复点信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public AppFixPointResult save(AppFixPoint appFixPoint, SessionInfo sessionInfo) {
		logger.info("添加app修复点:{}", appFixPoint);
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return AppFixPointResult.SESSIONINFO_NULL;
		}
		String fixPoint = appFixPoint.getFixPoint();
		if (fixPoint == null || fixPoint.isEmpty()) {
			logger.error("修复点为空");
			return AppFixPointResult.FIXPOINT_IS_NULL;
		}
		if (fixPoint.length() > FIXPOINT_LIMIT) {
			logger.error("修复点超过最大字符数！");
			return AppFixPointResult.FIXPOINT_LENGTH_LIMIT;
		}

		appFixPoint.setOpUser(sessionInfo.getUsername());
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("lan", appFixPoint.getLan());
		params.put("appName", appFixPoint.getAppName());
		params.put("appVersion", appFixPoint.getAppVersion());
		try {
			List<AppFixPoint> appFixPoints = appFixPointDao.query(params);
			if (appFixPoints != null && appFixPoints.size() > 0) {
				logger.error("该app修复点已经存在！");
				return AppFixPointResult.FIXPOINT_IS_EXISTS;
			}
			List<AppVersion> appVersionList = appVersionDao.query(params);
			appFixPoint.setAppId(appVersionList.get(0).getId());
			int result = appFixPointDao.save(appFixPoint);
			logger.info("添加app修复点信息成功,数据库影响行数:{}", result);
			return AppFixPointResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppFixPointResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public AppFixPointResult update(AppFixPoint appFixPoint, SessionInfo sessionInfo) {
		logger.info("修改app修复点:{}", appFixPoint);
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return AppFixPointResult.SESSIONINFO_NULL;
		}
		String fixPoint = appFixPoint.getFixPoint();
		if (fixPoint == null || fixPoint.isEmpty()) {
			logger.error("修复点为空");
			return AppFixPointResult.FIXPOINT_IS_NULL;
		}
		if (fixPoint.length() > FIXPOINT_LIMIT) {
			logger.error("修复点超过最大字符数！");
			return AppFixPointResult.FIXPOINT_LENGTH_LIMIT;
		}
		appFixPoint.setOpUser(sessionInfo.getUsername());
		try {
			int result = appFixPointDao.update(appFixPoint);
			logger.info("修改app修复点信息成功,数据库影响行数:{}", result);
			return AppFixPointResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return AppFixPointResult.REQUEST_FAIL;
		}
	}

	@Override
	public AppFixPoint query(Long id) {
		logger.info("根据id:{}查询app修复点信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			AppFixPoint appFixPoint = appFixPointDao.query(id);
			logger.info("查询app修复点信息成功:{}", appFixPoint);
			return appFixPoint;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}
}
