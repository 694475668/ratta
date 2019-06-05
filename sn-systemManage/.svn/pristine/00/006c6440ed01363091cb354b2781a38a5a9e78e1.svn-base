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
import com.ratta.suponote.firmware.dao.FirmwareEquipDao;
import com.ratta.suponote.firmware.dao.FirmwareFixPointDao;
import com.ratta.suponote.firmware.dao.FirmwareInfoDao;
import com.ratta.suponote.firmware.dao.FirmwareTaskDao;
import com.ratta.suponote.firmware.model.FirmwareInfo;
import com.ratta.suponote.firmware.model.FirmwareInfoLine;
import com.ratta.suponote.firmware.result.FirmwareInfoResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.dao.StockDao;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 固件版本业务层 2018-10-31
 */
@Service("firmwareInfoService")
@Transactional(rollbackFor = Exception.class)
public class FirmwareInfoBean implements FirmwareInfoService {
	private static final String FIRMWARE_STATUS = "0";
	private static Logger logger = LoggerFactory.getLogger(FirmwareInfoBean.class);
	@Autowired
	private FirmwareInfoDao firmwareInfoDao;
	@Autowired
	private StockDao stockDao;
	@Autowired
	private FirmwareTaskDao firmwareTaskDao;
	@Autowired
	private FirmwareEquipDao firmwareEquipDao;
	@Autowired
	private FirmwareFixPointDao firmwareFixPointDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询固件版本信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(firmwareInfoDao.query(params));
			dg.setTotal(firmwareInfoDao.count(params));
			logger.info("查询固件版本信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public DataGrid firmwareInfoLine(PageHelper ph, Map<String, Object> params) {
		logger.info("调用加载固件版本子类界面,查询参数:{},分页参数:{}", BaseUtil.map2String(params), ph);
		DataGrid dg = new DataGrid();
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		dg.setRows(firmwareInfoDao.firmwareInfoLine(params));
		dg.setTotal(firmwareInfoDao.countFirmwareInfoLine(params));
		logger.info("查询用户数据成功,总记录数:{}", dg.getTotal());
		return dg;
	}

	@Override
	public FirmwareInfo query(Long id) {
		logger.info("根据id:{}查询固件版本信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			FirmwareInfo firmwareInfo = firmwareInfoDao.query(id);
			logger.info("查询固件版本信息成功:{}", firmwareInfo);
			return firmwareInfo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<FirmwareInfoLine> firmwareInfoLine(Map<String, Object> params) {
		logger.info("根据params:{}查询固件版本子类信息", params);
		if (StringUtils.isEmpty(params)) {
			logger.error("params为空");
			return null;
		}
		try {
			List<FirmwareInfoLine> firmwareInfo = firmwareInfoDao.firmwareInfoLine(params);
			logger.info("查询固件版本信息成功:{}", firmwareInfo);
			return firmwareInfo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public FirmwareInfoLine queryLine(Long id) {
		logger.info("根据id:{}查询固件版本子类信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			FirmwareInfoLine firmwareInfoLine = firmwareInfoDao.queryLine(id);
			logger.info("查询固件版本子类信息成功:{}", firmwareInfoLine);
			return firmwareInfoLine;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<FirmwareInfo> queryNoPage() {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("status", "2");
		params.put("audit_flag", "Y");
		try {
			List<FirmwareInfo> firmwareInfo = firmwareInfoDao.query(params);
			return firmwareInfo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public FirmwareInfoResult delete(Long id, SessionInfo sessionInfo) {
		logger.info("删除id={}的固件版本信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("固件版本信息id为空");
			return FirmwareInfoResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareInfoResult.SESSIONINFO_NULL;
		}
		FirmwareInfo firmwareInfo = firmwareInfoDao.query(id);
		if (!FIRMWARE_STATUS.equals(firmwareInfo.getStatus())) {
			logger.error("仅能删除未审核的固件版本！");
			return FirmwareInfoResult.ONLY_DELETE;
		}
		try {
			firmwareEquipDao.deleteFirmwareEquipType(firmwareInfo.getVersion());
			logger.info("删除version={}的固件设备绑定信息成功", firmwareInfo.getVersion());
			firmwareFixPointDao.delete(firmwareInfo.getVersion());
			logger.info("删除version={}的固件修复点信息成功", firmwareInfo.getVersion());
			firmwareInfoDao.delete(id);
			logger.info("删除id={}的固件版本信息成功", id);
			return FirmwareInfoResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareInfoResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareInfoResult deleteAuditTest(String equipmentNo, SessionInfo sessionInfo) {
		logger.info("删除equipment_no={}的固件审核测试任务信息", equipmentNo);
		try {
			// 从task表中，通过设备号和taskCode 删除记录
			firmwareTaskDao.deleteTask(equipmentNo, "03");
			// 更新任务task_id==0，update_status==0
			stockDao.updateTaskIdByEquiNo(equipmentNo, 0, "0");
			logger.info("删除equipment_no={}的固件审核测试任务信息成功", equipmentNo);
			return FirmwareInfoResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareInfoResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareInfoResult commitAudit(FirmwareInfo firmwareInfo, SessionInfo sessionInfo) {
		logger.info("审核固件信息:{}", firmwareInfo);
		if (firmwareInfo == null) {
			logger.error("审核固件信息firmwareInfo为空");
			return FirmwareInfoResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return FirmwareInfoResult.SESSIONINFO_NULL;
		}
		firmwareInfo.setAudit_user(sessionInfo.getUsername());
		try {
			firmwareInfoDao.commitAudit(firmwareInfo);
			return FirmwareInfoResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareInfoResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public FirmwareInfo addFirmwareInfo(FirmwareInfo firmware, FirmwareInfo firmwareInfo, SessionInfo sessionInfo) {
		if (firmwareInfo == null) {
			logger.error("大固件信息firmwareInfo为空");
			return null;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return null;
		}
		firmware.setAudit_flag(firmwareInfo.getAudit_flag());
		firmware.setAudit_info(firmwareInfo.getAudit_info());
		firmware.setImpactScope(firmwareInfo.getImpactScope());
		firmware.setModifyPoint(firmwareInfo.getModifyPoint());
		firmware.setOp_user(sessionInfo.getUsername());
		firmware.setPackagingTime(firmwareInfo.getPackagingTime());
		firmware.setUpdrageScope(firmwareInfo.getUpdrageScope());
		firmware.setVersion(firmwareInfo.getVersion());
		firmware.setVersionPurpose(firmwareInfo.getVersionPurpose());
		logger.info("大固件信息:{}", firmware);
		try {
			firmwareInfoDao.addFirmware(firmware);
			return firmware;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public FirmwareInfoResult addFirmwareInfoLin(FirmwareInfoLine firmwareInfoLine) {
		logger.info("小固件信息:{}", firmwareInfoLine);
		try {
			firmwareInfoDao.addFirmwareInfoLine(firmwareInfoLine);
			return FirmwareInfoResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareInfoResult.REQUEST_FAIL;
		}
	}

	@Override
	public List<FirmwareInfo> queryFirmwareInfoList(String version, String area, String custom, String configuration) {
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("version", version);
		params.put("area", area);
		params.put("custom", custom);
		params.put("configuration", configuration);
		try {
			List<FirmwareInfo> firmwareInfoList = firmwareInfoDao.queryFirmwareInfoList(params);
			return firmwareInfoList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return null;
		}
	}

	@Override
	public FirmwareInfoLine queryFirmwareInfoLine(String firmId, String type) {
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("firm_id", firmId);
		params.put("type", type);
		try {
			FirmwareInfoLine firmwareInfoList = firmwareInfoDao.queryfirmwareInfoLin(params);
			return firmwareInfoList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public int updateStatus(FirmwareInfo firmwareInfo) {
		return firmwareInfoDao.updateStatus(firmwareInfo);
	}

	@Override
	@Transactional
	public int deleteLine(Long id) {
		return firmwareInfoDao.deleteLine(id);
	}

	@Override
	public List<FirmwareInfoLine> firmwareInfoLineList(long id) {
		return firmwareInfoDao.firmwareInfoLineList(id);
	}
}
