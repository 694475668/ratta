package com.ratta.suponote.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.suponote.business.dao.EquipTypeDao;
import com.ratta.suponote.business.model.EquipType;
import com.ratta.suponote.business.result.EquipTypeRseult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.dao.InOutStockDao;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 设备型号业务层 2018-10-31
 */
@Service("equipTypeService")
@Transactional(rollbackFor = Exception.class)
public class EquipTypeBean implements EquipTypeService {

	private static Logger logger = LoggerFactory.getLogger(EquipTypeBean.class);
	@Autowired
	private EquipTypeDao equipTypeDao;
	@Autowired
	private InOutStockDao inOutStockDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询设备型号信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(equipTypeDao.query(params));
			dg.setTotal(equipTypeDao.count(params));
			logger.info("查询设备型号信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public EquipTypeRseult save(EquipType equipType, SessionInfo sessionInfo) {
		logger.info("添加设备型号信息:{}", equipType);
		if (equipType == null) {
			logger.error("设备型号信息为空");
			return EquipTypeRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return EquipTypeRseult.SESSIONINFO_NULL;
		}
		equipType.setOp_user(sessionInfo.getUsername());
		try {
			EquipType equipTypes = equipTypeDao.queryByType(equipType.getType().trim());
			if (equipTypes != null) {
				logger.error("该设备型号已经存在！");
				return EquipTypeRseult.TYPE_IS_EXISTS;
			}
			int result = equipTypeDao.save(equipType);
			logger.info("添加操作员信息成功,数据库影响行数:{}", result);
			return EquipTypeRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return EquipTypeRseult.REQUEST_FAIL;
		}
	}

	@Override
	public List<EquipType> queryNoPage() {
		try {
			List<EquipType> equiptype = equipTypeDao.query(new HashMap<String, Object>(8));
			return equiptype;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public EquipTypeRseult update(EquipType equipType, SessionInfo sessionInfo) {
		if (equipType == null) {
			logger.error("设备型号信息为空");
			return EquipTypeRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return EquipTypeRseult.SESSIONINFO_NULL;
		}
		equipType.setOp_user(sessionInfo.getUsername());
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("equipment_model", equipType.getId());
		try {
			EquipType oldEquipType = equipTypeDao.query(equipType.getId());
			if (!(equipType.getType().trim()).equals(oldEquipType.getType())) {
				EquipType equipTypes = equipTypeDao.queryByType(equipType.getType().trim());
				if (equipTypes != null) {
					logger.error("该设备型号已经存在！");
					return EquipTypeRseult.TYPE_IS_EXISTS;
				}
			}

			Long counts = inOutStockDao.queryByParams(params);
			if (counts > 0) {
				logger.error("该设备信号下已绑定设备！");
				return EquipTypeRseult.EQUIPTYPE_EQUIPMENT_BIND;
			}
			int result = equipTypeDao.update(equipType);
			logger.info("更新设备型号信息成功,数据库影响行数:{}", result);
			return EquipTypeRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return EquipTypeRseult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public EquipTypeRseult delete(Long id, SessionInfo sessionInfo) {
		logger.info("删除id={}的设备型号信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("设备型号信息id为空");
			return EquipTypeRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return EquipTypeRseult.SESSIONINFO_NULL;
		}
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("equipment_model", id);
		try {
			Long counts = inOutStockDao.queryByParams(params);
			if (counts > 0) {
				logger.error("该设备型号下已绑定设备！");
				return EquipTypeRseult.EQUIPTYPE_EQUIPMENT_BIND;
			}
			int result = equipTypeDao.delete(id);
			logger.info("删除id={}的设备型号信息成功,数据库影响行数:{}", id, result);
			return EquipTypeRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return EquipTypeRseult.REQUEST_FAIL;
		}
	}

	@Override
	public EquipType query(Long id) {
		logger.info("根据id:{}查询设备型号信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			EquipType equipType = equipTypeDao.query(id);
			logger.info("查询设备型号信息成功:{}", equipType);
			return equipType;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

}
