package com.ratta.suponote.firmware.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.business.dao.EquipTypeDao;
import com.ratta.suponote.firmware.dao.FirmwareEquipDao;
import com.ratta.suponote.firmware.model.FirmwareEquipment;
import com.ratta.suponote.firmware.result.FirmwareEquipResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 固件版本设备绑定业务层 2018-10-31
 */
@Service("firmwareEquipService")
@Transactional(rollbackFor = Exception.class)
public class FirmwareEquipBean implements FirmwareEquipService {

	private static Logger logger = LoggerFactory.getLogger(FirmwareEquipBean.class);
	@Autowired
	private FirmwareEquipDao firmwareEquipDao;
	
	@Autowired
	private EquipTypeDao equipTypeDao;

	@Override
	public DataGrid firmwareEquip(PageHelper ph, Map<String, Object> params) {
		logger.info("查询固件设备绑定信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(firmwareEquipDao.query(params));
			dg.setTotal(firmwareEquipDao.count(params));
			logger.info("查询固件设备绑定信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public Long queryCount(String version) {
		Map<String, Object> param = new HashMap<String, Object>(8);
		param.put("version", version);
		Long i = firmwareEquipDao.count(param);
		return i;
	}

	@Override
	public List<FirmwareEquipment> queryEquipByVersion(Map<String, Object> params) {
		return firmwareEquipDao.query(params);
	}

	@Override
	@Transactional
	public FirmwareEquipResult addFirmwareEquipment(String version, List<String> equipmentId) {
		logger.info("固件版本:{}", version);
		if (equipmentId.size() == 0) {
			logger.error("设备型号为空");
			return FirmwareEquipResult.EQUIPMENTINFO_NULL;
		}
		if (version == null) {
			logger.error("固件版本为空");
			return FirmwareEquipResult.FIRMWAREINFO_NULL;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		List<FirmwareEquipment> firmwareEuipList = new ArrayList<FirmwareEquipment>();
		params.put("version", version);
		for (int i = 0; i < equipmentId.size(); i++) {
			params.put("type", equipTypeDao.query(Long.parseLong(equipmentId.get(i))).getType());
			if(firmwareEquipDao.query(params).size() > 0) {
				continue;
			}
			FirmwareEquipment firmwareEuip = new FirmwareEquipment();
			firmwareEuip.setEquipment_model(equipmentId.get(i));
			firmwareEuip.setFirmware_version(version);
			firmwareEuipList.add(firmwareEuip);
		}
		try {
			firmwareEquipDao.insertFirmwareEuip(firmwareEuipList);
			return FirmwareEquipResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return FirmwareEquipResult.REQUEST_FAIL;
		}
	}

}
