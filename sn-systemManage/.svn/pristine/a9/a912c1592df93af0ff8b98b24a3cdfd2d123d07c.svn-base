package com.ratta.suponote.equipment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ratta.suponote.equipment.dao.EquipmentLogDao;
import com.ratta.suponote.equipment.model.EquipmentLog;
import com.ratta.suponote.equipment.result.EquipmentLogResult;
import com.ratta.suponote.equipment.service.EquipmentLogService;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 设备日志业务层 2018-10-31
 */
@Service("equipmentLogService")
@Transactional(rollbackFor = Exception.class)
public class EquipmentLogBean implements EquipmentLogService {

	private static Logger logger = LoggerFactory.getLogger(EquipmentLogBean.class);
	@Autowired
	private EquipmentLogDao equipmentLogDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询设备日志信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(equipmentLogDao.query(params));
			dg.setTotal(equipmentLogDao.count(params));
			logger.info("查询设备日志信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public EquipmentLog query(Long id) {
		return equipmentLogDao.queryById(id);
	}

	@Override
	@Transactional
	public EquipmentLogResult delete(Long id) {
		logger.info("删除id={}的设备日志信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("设备日志信息id为空");
			return EquipmentLogResult.REQUEST_FAIL;
		}
		try {
			equipmentLogDao.delete(id);
			logger.info("删除id={}的设备日志信息成功", id);
			return EquipmentLogResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return EquipmentLogResult.REQUEST_FAIL;
		}
	}

	@Override
	public List<EquipmentLog> selectByDays(Long daysBefore) {
		return equipmentLogDao.selectByDays(daysBefore);
	}

	@Override
	@Transactional
	public int updateByParam(Long id, String flag, String isDownload, String remark) {
		return equipmentLogDao.updateByParam(id, flag, isDownload, remark);
	}

}
