package com.ratta.suponote.param.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.suponote.param.dao.ErrorCodeDao;
import com.ratta.suponote.param.model.ErrorCode;
import com.ratta.suponote.param.result.ErrorCodeResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 错误码服务 2018-10-31
 */
@Service("errorCodeService")
@Transactional(rollbackFor = Exception.class)
public class ErrorCodeBean implements ErrorCodeService {
	private static Logger logger = LoggerFactory.getLogger(ErrorCodeBean.class);
	@Autowired
	private ErrorCodeDao errorCodeDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询错误码信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(errorCodeDao.query(params));
			dg.setTotal(errorCodeDao.count(params));
			logger.info("查询错误码信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public ErrorCodeResult save(ErrorCode errorCode, SessionInfo sessionInfo) {
		logger.info("添加错误码信息:{}", errorCode);
		if (errorCode == null) {
			logger.error("错误码信息为空");
			return ErrorCodeResult.ERRORCODE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return ErrorCodeResult.SESSIONINFO_NULL;
		}
		ErrorCode oldErrorCode = errorCodeDao.query(errorCode.getCode());
		if (oldErrorCode != null) {
			logger.error("code:{} 错误码已经存在", errorCode.getCode());
			return ErrorCodeResult.ERRORCODE_CODE_EXISTS;
		}
		errorCode.setOp_user(sessionInfo.getUsername());
		try {
			int result = errorCodeDao.save(errorCode);
			logger.info("添加操作员信息成功,数据库影响行数:{}", result);
			return ErrorCodeResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return ErrorCodeResult.DATABASE_ERROR;
		}
	}

	@Override
	public ErrorCode query(String code) {
		logger.info("根据code:{}查询错误码信息", code);
		if (StringUtils.isEmpty(code)) {
			logger.error("错误码code为空");
			return null;
		}
		try {
			ErrorCode errorCode = errorCodeDao.query(code);
			logger.info("查询错误码信息成功:{}", errorCode);
			return errorCode;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public ErrorCodeResult update(ErrorCode errorCode, SessionInfo sessionInfo) {
		logger.info("更新错误码信息:{}", errorCode);
		if (errorCode == null) {
			logger.error("错误码信息为空");
			return ErrorCodeResult.ERRORCODE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return ErrorCodeResult.SESSIONINFO_NULL;
		}
		errorCode.setOp_user(sessionInfo.getUsername());
		try {
			int result = errorCodeDao.update(errorCode);
			logger.info("更新错误码信息成功,数据库影响行数:{}", result);
			return ErrorCodeResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return ErrorCodeResult.DATABASE_ERROR;
		}
	}

	@Override
	@Transactional
	public ErrorCodeResult delete(String code, SessionInfo sessionInfo) {
		logger.info("删除code={}的错误码信息", code);
		if (StringUtils.isEmpty(code)) {
			logger.error("错误码信息code为空");
			return ErrorCodeResult.ERRORCODE_CODE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return ErrorCodeResult.SESSIONINFO_NULL;
		}
		try {
			int result = errorCodeDao.delete(code);
			logger.info("删除code={}的错误码信息成功,数据库影响行数:{}", code, result);
			return ErrorCodeResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return ErrorCodeResult.DATABASE_ERROR;
		}
	}

}
