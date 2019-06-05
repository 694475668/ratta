package com.ratta.suponote.param.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ratta.suponote.param.dao.ReferenceDao;
import com.ratta.suponote.param.model.Reference;
import com.ratta.suponote.param.result.ReferenceResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 系统参数详细信息服务 2018-10-31
 */
@Service("referenceService")
@Transactional(rollbackFor = Exception.class)
public class ReferenceBean implements ReferenceService {
	/**
	 * 日志记录
	 */
	private static Logger logger = LoggerFactory.getLogger(ReferenceBean.class);
	/**
	 * 系统参数详细信息dao
	 */
	@Autowired
	private ReferenceDao referenceDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询系统参数详细信息,查询参数:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		dg.setRows(referenceDao.query(params));
		dg.setTotal(referenceDao.count(params));
		logger.info("查询系统参数详细信息成功,总记录数:{}", dg.getTotal());
		return dg;
	}

	@Override
	@Transactional
	public ReferenceResult save(Reference reference, SessionInfo sessionInfo) {
		logger.info("保存系统参数详细信息:{}", reference);
		if (reference == null) {
			logger.error("系统参数详细信息为空");
			return ReferenceResult.REFERENCE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return ReferenceResult.SESSIONINFO_NULL;
		}
		reference.setOp_user(sessionInfo.getUsername());
		try {
			List<Reference> ref = referenceDao.queryByParamCode(reference.getSerial(), reference.getName());
			if (ref != null && ref.size() > 0) {
				logger.error("参数已存在！");
				return ReferenceResult.REFERENCE_EXISTS;
			}
			int result = referenceDao.save(reference);
			logger.info("保存系统参数详细信息,数据库影响行数:{}", result);
			return ReferenceResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return ReferenceResult.DATABASE_ERROR;
		}
	}

	@Override
	public Reference queryById(Long id) {
		logger.info("根据id查询系统参数详细信息,id:{}", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("查询id为空");
			return null;
		}
		Reference reference = referenceDao.queryById(id);
		logger.info("查询系统参数详细信息成功,{}", reference);
		return reference;
	}

	@Override
	@Transactional
	public ReferenceResult update(Reference reference, SessionInfo sessionInfo) {
		logger.info("更新系统参数详细信息:{}", reference);
		if (reference == null) {
			logger.info("系统参数详细信息为空");
			return ReferenceResult.REFERENCE_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return ReferenceResult.SESSIONINFO_NULL;
		}
		reference.setOp_user(sessionInfo.getUsername());
		try {
			int result = referenceDao.update(reference);
			logger.info("更新系统参数详细信息成功,数据库影响行数:{}", result);
			return ReferenceResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return ReferenceResult.DATABASE_ERROR;
		}
	}

	@Override
	@Transactional
	public ReferenceResult delete(Long id, SessionInfo sessionInfo) {
		logger.info("根据id:{}删除参数明细", id);
		if (id == null) {
			logger.error("参数id为空");
			return ReferenceResult.REFERENCE_ID_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return ReferenceResult.SESSIONINFO_NULL;
		}
		try {
			int result = referenceDao.delete(id);
			logger.info("根据id删除参数明细成功,数据库影响行数:{}", result);
			return ReferenceResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据id删除参数明细异常:{}", e);
			return ReferenceResult.DATABASE_ERROR;
		}
	}

	@Override
	public List<Reference> queryParam(String serial, String name) {
		List<Reference> reference = referenceDao.queryByParamCode(serial, name);
		return reference;
	}
}
