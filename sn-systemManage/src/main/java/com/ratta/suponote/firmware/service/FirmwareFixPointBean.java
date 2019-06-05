package com.ratta.suponote.firmware.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ratta.suponote.firmware.dao.FirmwareFixPointDao;
import com.ratta.suponote.firmware.model.FirmwareFixPoint;
import com.ratta.suponote.firmware.result.FirmwareFixPointResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 固件修复点业务层 2018-10-31
 */
@Service("firmwareFixPointService")
@Transactional(rollbackFor = Exception.class)
public class FirmwareFixPointBean implements FirmwareFixPointService {
	static final int FIXPOINT_LIMIT = 8000;
	private static Logger logger = LoggerFactory.getLogger(FirmwareFixPointBean.class);
	@Autowired
	private FirmwareFixPointDao firmwareFixPointDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询固件修复点,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(firmwareFixPointDao.query(params));
			dg.setTotal(firmwareFixPointDao.count(params));
			logger.info("查询固件修复点成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public List<FirmwareFixPoint> queryByParam(Map<String, Object> params) {
		return firmwareFixPointDao.query(params);
	}

	@Override
	@Transactional
	public FirmwareFixPointResult save(FirmwareFixPoint firmwareFixPoint, SessionInfo sessionInfo) {
		logger.info("添加固件修复点:{}", firmwareFixPoint);
		if (firmwareFixPoint == null) {
			logger.error("固件修复点为空");
			return FirmwareFixPointResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareFixPointResult.SESSIONINFO_NULL;
		}
		String fixPoint = firmwareFixPoint.getFixPoint();
		if (fixPoint == null || fixPoint.isEmpty()) {
			logger.error("修复点为空");
			return FirmwareFixPointResult.FIXPOINT_IS_NULL;
		}
		if (fixPoint.length() > FIXPOINT_LIMIT) {
			logger.error("修复点超过最大字符数！");
			return FirmwareFixPointResult.FIXPOINT_LENGTH_LIMIT;
		}

		firmwareFixPoint.setOp_user(sessionInfo.getUsername());
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("lan", firmwareFixPoint.getLan());
		params.put("firmware_version", firmwareFixPoint.getFirmware_version());
		try {
			List<FirmwareFixPoint> fix = firmwareFixPointDao.query(params);
			if (fix != null && fix.size() > 0) {
				logger.error("该固件修复点已经存在！");
				return FirmwareFixPointResult.FIXPOINT_IS_EXISTS;
			}
			int result = firmwareFixPointDao.save(firmwareFixPoint);
			logger.info("添加固件修复点信息成功,数据库影响行数:{}", result);
			return FirmwareFixPointResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareFixPointResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareFixPointResult update(FirmwareFixPoint firmwareFixPoint, SessionInfo sessionInfo) {
		logger.info("修改固件修复点:{}", firmwareFixPoint);
		if (firmwareFixPoint == null) {
			logger.error("固件修复点为空");
			return FirmwareFixPointResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareFixPointResult.SESSIONINFO_NULL;
		}
		String fixPoint = firmwareFixPoint.getFixPoint();
		if (fixPoint == null || fixPoint.isEmpty()) {
			logger.error("修复点为空");
			return FirmwareFixPointResult.FIXPOINT_IS_NULL;
		}
		if (fixPoint.length() > FIXPOINT_LIMIT) {
			logger.error("修复点超过最大字符数！");
			return FirmwareFixPointResult.FIXPOINT_LENGTH_LIMIT;
		}
		firmwareFixPoint.setOp_user(sessionInfo.getUsername());
		try {
			int result = firmwareFixPointDao.update(firmwareFixPoint);
			logger.info("修改固件修复点信息成功,数据库影响行数:{}", result);
			return FirmwareFixPointResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareFixPointResult.REQUEST_FAIL;
		}
	}
}
