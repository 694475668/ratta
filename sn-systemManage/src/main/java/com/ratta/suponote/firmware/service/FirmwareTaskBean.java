package com.ratta.suponote.firmware.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.suponote.firmware.dao.FirmwareInfoDao;
import com.ratta.suponote.firmware.dao.FirmwareTaskDao;
import com.ratta.suponote.firmware.model.FirmwareInfo;
import com.ratta.suponote.firmware.model.FirmwareTask;
import com.ratta.suponote.firmware.result.FirmwareTaskResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.dao.StockDao;
import com.ratta.suponote.stock.model.InOutStock;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 固件版本发布任务业务层 2018-10-31
 */
@Service("firmwareTaskService")
@Transactional(rollbackFor = Exception.class)
public class FirmwareTaskBean implements FirmwareTaskService {
	private static final String TASK_FINISH_FLAG = "1";
	private static final String TASK_IS_HISTORY = "0";
	private static Logger logger = LoggerFactory.getLogger(FirmwareTaskBean.class);
	@Autowired
	private FirmwareTaskDao firmwareTaskDao;
	@Autowired
	private StockDao stockDao;
	@Autowired
	private FirmwareInfoDao firmwareInfoDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询固件版本任务信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(firmwareTaskDao.query(params));
			dg.setTotal(firmwareTaskDao.count(params));
			logger.info("查询固件版本任务信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public DataGrid queryAuditTest(PageHelper ph, Map<String, Object> params) {
		logger.info("查询固件审核测试任务信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(firmwareTaskDao.queryAuditTest(params));
			dg.setTotal(firmwareTaskDao.countAuditTest(params));
			logger.info("查询固件审核测试任务信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public FirmwareTaskResult delete(Long id, List<InOutStock> inouts, SessionInfo sessionInfo) {
		logger.info("删除id={}的固件版本任务信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("固件版本任务信息id为空");
			return FirmwareTaskResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareTaskResult.SESSIONINFO_NULL;
		}
		FirmwareTask firmwareTask = firmwareTaskDao.query(id);

		if (TASK_FINISH_FLAG.equals(firmwareTask.getFinishFlag())) {
			logger.error("仅能删除未到执行时间的的固件发布任务！");
			return FirmwareTaskResult.ONLY_DELETE;
		}
		int result = 0;
		try {
			if (TASK_IS_HISTORY.equals(firmwareTask.getIsHistory())) {
				for (InOutStock inOutStock : inouts) {
					stockDao.updateTaskId(inOutStock.getId(), 0);
					stockDao.updateStatusByInoutId(inOutStock.getId(), "0");
				}
			}
			result = firmwareTaskDao.delete(id);
			logger.info("删除id={}的固件版本任务信息成功,数据库影响行数:{}", id, result);
			return FirmwareTaskResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareTaskResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareTaskResult revoke(FirmwareTask firmwareTask, SessionInfo sessionInfo) {
		if (firmwareTask == null) {
			logger.error("固件版本任务信息为空");
			return FirmwareTaskResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareTaskResult.SESSIONINFO_NULL;
		}

		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("status", "1");
		params.put("revokeUser", sessionInfo.getUsername());
		params.put("id", firmwareTask.getId());
		int result = 0;
		try {
			List<String> stocks = stockDao.queryEquipmentNoByTaskId(firmwareTask.getId());
			for (String string : stocks) {
				firmwareTaskDao.deleteTask(string, "03");
			}
			result = firmwareTaskDao.update(params);
			logger.info("撤销固件版本任务信息成功,数据库影响行数:{}", result);
			return FirmwareTaskResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareTaskResult.REQUEST_FAIL;
		}
	}

	@Override
	public FirmwareTask query(Long id) {
		return firmwareTaskDao.query(id);
	}

	@Override
	@Transactional
	public FirmwareTaskResult deploy(FirmwareTask firmwareTask, List<InOutStock> inouts, SessionInfo sessionInfo) {
		logger.info("发布固件版本", firmwareTask);
		if (StringUtils.isEmpty(firmwareTask)) {
			logger.error("固件版本信息id为空");
			return FirmwareTaskResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareTaskResult.SESSIONINFO_NULL;
		}
		firmwareTask.setDeployUser(sessionInfo.getUsername());
		firmwareTask.setDeployVersion(firmwareTask.getFirmware_version());
		firmwareTask.setBz_code("firmware_update");
		try {
			// 获取创建的任务ID
			Long id = firmwareTaskDao.generateId();
			firmwareTask.setId(id);
			// 更新设备任务ID和设备固件更新状态
			if (firmwareTask.getEquipmentno() == null || firmwareTask.getEquipmentno().isEmpty()) {
				for (InOutStock inOutStock : inouts) {
					stockDao.updateTaskId(inOutStock.getId(), firmwareTask.getId());
					stockDao.updateStatusByInoutId(inOutStock.getId(), "1");
				}
			} else {
				stockDao.updateTaskIdByEquiNo(firmwareTask.getEquipmentno(), firmwareTask.getId().intValue(), "1");
			}
			firmwareTaskDao.insertFirmwareTask(firmwareTask);
			logger.info("发布固件版本信息成功");
			return FirmwareTaskResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareTaskResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareTaskResult firmwareAuditTest(FirmwareTask firmwareTask, SessionInfo sessionInfo) {
		logger.info("审核测试固件版本", firmwareTask);
		if (StringUtils.isEmpty(firmwareTask)) {
			logger.error("固件版本信息id为空");
			return FirmwareTaskResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareTaskResult.SESSIONINFO_NULL;
		}
		FirmwareInfo firmwareInfo = new FirmwareInfo();
		firmwareInfo.setVersion(firmwareTask.getFirmware_version());
		firmwareInfo.setStatus("1");
		firmwareTask.setDeployUser(sessionInfo.getUsername());
		String[] equipment = firmwareTask.getEquuipmentNo().split(",");
		try {
			firmwareTask.setBz_code("firmware_update");
			firmwareTask.setType("1");
			firmwareTask.setDeployVersion(firmwareTask.getFirmware_version());
			firmwareTaskDao.insertFirmwareTask(firmwareTask);
			for (String equipmentNo : equipment) {
				Stock stock = stockDao.queryByEquipNo(equipmentNo);
				stockDao.updateTaskIdByEquiNo(equipmentNo, firmwareTask.getId().intValue(), "1");
				List<String> equipmentNos = stockDao.queryEquipmentNoByTaskId(stock.getTask_id());
				if (equipmentNos.size() == 0) {
					firmwareTaskDao.delete(new Long((long) stock.getTask_id()));
				}
			}
			firmwareInfoDao.updateStatus(firmwareInfo);
			logger.info("审核测试固件版本信息成功");
			return FirmwareTaskResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareTaskResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public long updateFinshFlag(long id) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("finish_flag", "1");
		params.put("id", id);
		return firmwareTaskDao.update(params);
	}

	@Override
	public Long generateId() {
		return firmwareTaskDao.generateId();
	}

	@Override
	public void deleteTaskById(long id) {
		firmwareTaskDao.delete(id);
	}

	@Override
	public int countByVersion(String version) {
		return firmwareTaskDao.countByVersion(version);
	}

	@Override
	@Transactional
	public long updateIsHistory(long id) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("is_history", "1");
		params.put("id", id);
		return firmwareTaskDao.update(params);
	}
}
