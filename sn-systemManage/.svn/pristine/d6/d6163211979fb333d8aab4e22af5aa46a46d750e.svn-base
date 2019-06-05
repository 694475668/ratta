package com.ratta.suponote.service.system.maint;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.dao.system.maint.AuditDao;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.system.maint.Audit;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 操作审计服务 2018-10-31
 */
@Service("auditService")
@Transactional(rollbackFor = Exception.class)
public class AuditBean implements AuditService {
	/**
	 * 日志记录
	 */
	private static Logger logger = LoggerFactory.getLogger(AuditBean.class);
	/**
	 * 操作审计dao
	 */
	@Autowired
	private AuditDao auditDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询操作审计数据,查询条件:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		dg.setRows(auditDao.query(params));
		dg.setTotal(auditDao.count(params));
		logger.info("查询操作审计数据成功,总记录数:{}", dg.getTotal());
		return dg;
	}

	@Override
	@Transactional
	public int save(Audit audit) {
		logger.info("添加操作审计数据:{}", audit);
		int result = 0;
		if (audit != null) {
			result = auditDao.save(audit);
			logger.info("添加操作审计数据成功,数据库影响行数:{}", result);
		} else {
			logger.error("操作审计数据为空");
		}
		return result > 0 ? 1 : 0;
	}

}
