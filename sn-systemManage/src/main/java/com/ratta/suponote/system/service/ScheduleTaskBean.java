package com.ratta.suponote.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.dao.system.ScheduleTaskDao;
import com.ratta.suponote.dao.system.maint.AuditDao;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.model.system.ScheduleLog;
import com.ratta.suponote.model.system.ScheduleTask;
import com.ratta.suponote.param.dao.ReferenceDao;
import com.ratta.suponote.param.model.Reference;
import com.ratta.suponote.snuser.dao.LoginRecordDao;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 调度任务服务 2018-10-31
 */
@Service("scheduleTaskService")
@Transactional(rollbackFor = Exception.class)
public class ScheduleTaskBean implements ScheduleTaskService {
	/**
	 * 日志记录
	 */
	private static Logger logger = LoggerFactory.getLogger(ScheduleTaskBean.class);
	private static final String NAME = "name";

	/**
	 * 调度任务dao
	 */
	@Autowired
	private ScheduleTaskDao scheduleTaskDao;

	@Autowired
	private AuditDao auditDao;

	@Autowired
	private ReferenceDao referenceDao;

	@Autowired
	private LoginRecordDao loginRecordDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询调度任务信息,查询参数:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}
		if (params.get(NAME) != null) {
			params.put(NAME, BaseUtil.orclFuzzyQueryParam(params.get(NAME)));
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		dg.setRows(scheduleTaskDao.query(params));
		dg.setTotal(scheduleTaskDao.count(params));
		logger.info("查询调度任务成功,总记录数:{}", dg.getTotal());
		return dg;
	}

	@Override
	public List<ScheduleTask> queryAll() {
		logger.info("查询所有的调度任务信息");
		List<ScheduleTask> list = scheduleTaskDao.queryAll();
		logger.info("查询所有的调度任务成功,总记录数:{}", list.size());
		return list;
	}

	@Override
	@Transactional
	public int save(ScheduleTask scheduleTask, SessionInfo sessionInfo) {
		logger.info("保存调度任务信息:{}", scheduleTask);
		int result = 0;
		if (scheduleTask != null) {
			scheduleTask.setCreatedby(sessionInfo.getUsername());
			result = scheduleTaskDao.save(scheduleTask);
			logger.info("添加调度任务信息成功,数据库影响行数:{}", result);
		} else {
			logger.error("没有要添加的调度任务信息");
		}
		return result > 0 ? 1 : 0;
	}

	@Override
	@Transactional
	public int update(ScheduleTask scheduleTask, SessionInfo sessionInfo) {
		logger.info("更新调度任务信息:{}", scheduleTask);
		int result = 0;
		if (scheduleTask != null) {
			scheduleTask.setUpdatedby(sessionInfo.getId());
			result = scheduleTaskDao.update(scheduleTask);
			logger.info("更新调度任务信息成功,数据库影响行数:{}", result);
		} else {
			logger.error("没有要更新的调度任务信息");
		}
		return result > 0 ? 1 : 0;
	}

	@Override
	public ScheduleTask queryById(Long id) {
		logger.info("根据id:{}查询调度任务信息", id);
		ScheduleTask scheduleTask = scheduleTaskDao.queryById(id);
		logger.info("查询调度任务信息成功:{}", scheduleTask);
		return scheduleTask;
	}

	@Override
	public DataGrid queryLog(PageHelper ph, Map<String, Object> params) {
		logger.info("查询调度任务日志,查询条件:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());

		dg.setRows(scheduleTaskDao.queryLog(params));
		dg.setTotal(scheduleTaskDao.countLog(params));
		logger.info("查询调度任务日志成功,总记录数:{}", dg.getTotal());
		return dg;
	}

	@Override
	@Transactional
	public int addLog(ScheduleLog scheduleLog) {
		logger.info("添加调度任务日志:{}", scheduleLog);
		int result = 0;
		if (scheduleLog != null) {
			result = scheduleTaskDao.saveLog(scheduleLog);
			logger.info("添加调度任务日志成功,数据库影响行数:{}", result);
			return 1;
		} else {
			logger.error("调度任务日志为空");
			return 0;
		}
	}

	@Override
	public List<ScheduleTask> queryByIds(String ids) {
		return null;
	}

	@Override
	@Transactional
	public int dataclean() {
		logger.info("[数据清理] 开始...");
		int result = 0;
		long daysBefore;
		// 清除n天之前的数据, 注意是 “1” 不是 “01”
		List<Reference> ref = referenceDao.queryByParamCode("01", "CLEAN_DAY");
		if (ref == null || ref.size() < 0) {
			logger.info("数据清理，数据库参数查询有误！");
			return 0;
		}
		daysBefore = Integer.parseInt(ref.get(0).getValue());
		logger.info("[数据清理]-清除{}天之前的数据", daysBefore);

		try {

			result = auditDao.deleteByDays(daysBefore);
			logger.info("[数据清理]-audit-删除{}条数据", result);

			result = scheduleTaskDao.deleteByDays(daysBefore);
			logger.info("[数据清理]-schedule task log-删除{}条数据", result);

		} catch (Exception e) {
			logger.error("数据清理异常:{}", e.getMessage());
			e.printStackTrace();
			return 0;
		}
		logger.info("数据库清理结束.");
		return 1;
	}

	@Override
	@Transactional
	public int tokenclean() {
		logger.info("[SN100用户登录记录清理] 开始...");
		int result = 0;
		long daysBefore;
		// 清除n天之前的数据, 注意是 “1” 不是 “01”
		List<Reference> ref = referenceDao.queryByParamCode("01", "LOGIN_RECORD_CLEAN");
		if (ref == null || ref.size() < 0) {
			logger.info("SN100用户登录记录清理，数据库参数查询有误！");
			return 0;
		}
		daysBefore = Integer.parseInt(ref.get(0).getValue());
		logger.info("[SN100用户登录记录清理]-清除{}天之前的数据", daysBefore);

		try {

			result = loginRecordDao.deleteByDays(daysBefore);
			logger.info("[SN100用户登录记录清理]-audit-删除{}条数据", result);

		} catch (Exception e) {
			logger.error("SN100用户登录记录清理异常:{}", e.getMessage());
			e.printStackTrace();
			return 0;
		}
		logger.info("SN100用户登录记录清理结束.");
		return 1;
	}

}
