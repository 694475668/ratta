package com.ratta.suponote.stock.service;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.stock.dao.EquipmentReturnRecordDao;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 设备退换货业务层 2018-10-31
 */
@Service("equipmentReturnRecordService")
@Transactional(rollbackFor = Exception.class)
public class EquipmentReturnRecordBean implements EquipmentReturnRecordService {

	private static Logger logger = LoggerFactory.getLogger(EquipmentReturnRecordBean.class);
	@Autowired
	private EquipmentReturnRecordDao equipmentReturnRecordDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询设备退换货信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(equipmentReturnRecordDao.query(params));
			dg.setTotal(equipmentReturnRecordDao.count(params));
			logger.info("查询设备库存信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public EquipmentReturnRecord query(Long id) {
		return equipmentReturnRecordDao.queryById(id);
	}

}
