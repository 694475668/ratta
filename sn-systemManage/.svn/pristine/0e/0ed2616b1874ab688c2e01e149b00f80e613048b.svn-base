package com.ratta.suponote.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ratta.suponote.firmware.dao.FirmwareTaskDao;
import com.ratta.suponote.firmware.model.FirmwareTask;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.dao.EquipmentReturnRecordDao;
import com.ratta.suponote.stock.dao.InOutStockDao;
import com.ratta.suponote.stock.dao.StockDao;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;
import com.ratta.suponote.stock.model.InOutStock;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponote.stock.result.StockRseult;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 设备库存业务层 2018-10-31
 */
@Service("stockService")
@Transactional(rollbackFor = Exception.class)
public class StockBean implements StockService {

	private static Logger logger = LoggerFactory.getLogger(StockBean.class);
	private static final String DEPLOY_VERSION = "deployVersion";
	private static final String HEALTHY_STATUS = "0";
	private static final String ACTIVE_STATE = "Y";
	private static final String RETURN_FLAG = "1";

	@Autowired
	private StockDao stockDao;
	@Autowired
	private InOutStockDao inoutStockDao;
	@Autowired
	private EquipmentReturnRecordDao equipmentReturnRecordDao;
	@Autowired
	private FirmwareTaskDao firmwareTaskDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询设备库存信息,查询参数:{}", BaseUtil.map2String(params));
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
			List<Stock> stock = new ArrayList<Stock>();
			Long counts = (long) 0;
			if (!"".equals(params.get(DEPLOY_VERSION)) && params.get(DEPLOY_VERSION) != null) {
				List<Long> list = firmwareTaskDao.selectByVersion((String) params.get("deployVersion"));
				if (list != null && list.size() > 0) {
					params.put("list", list);
					stock = stockDao.query(params);
					counts = stockDao.count(params);
				}
			} else {
				stock = stockDao.query(params);
				counts = stockDao.count(params);
			}
			dg.setRows(stock);
			dg.setTotal(counts);
			logger.info("查询设备库存信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	/**
	 * 导出数据
	 */
	@Override
	public List<Stock> querySum(Map<String, Object> params) {
		logger.info("不分页查询设备库存,查询参数:{}", BaseUtil.map2String(params));
		try {
			List<Stock> driverSums = stockDao.querySumAll(params);
			logger.info("不分页查询设备库存数据成功,总记录数:{}", driverSums.size());
			return driverSums;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("不分页查询设备库存数据异常:{}", e);
			return null;
		}
	}

	@Override
	public Stock query(Long id) {
		logger.info("根据id:{}查询设备库存信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			Stock stock = stockDao.query(id);
			logger.info("查询设备库存信息成功:{}", stock);
			return stock;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public int isLagal(String equipmentNo) {
		logger.info("根据equipmentNo:{}查询设备库存信息", equipmentNo);
		if (StringUtils.isEmpty(equipmentNo)) {
			logger.error("equipmentNo为空");
			return 0;
		}
		try {
			int stock = stockDao.isLagal(equipmentNo);
			logger.info("查询设备库存信息成功:{}", stock);
			return stock;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return 0;
	}

	@Override
	public List<Stock> queryEquipmentByInOutId(long inoutId) {
		return stockDao.queryEquipmentByInOutId(inoutId);
	}

	@Override
	public DataGrid queryStockNotRelease(PageHelper ph, Map<String, Object> params) {
		logger.info("查询未发布固件的设备库存信息,查询参数:{}", BaseUtil.map2String(params));
		DataGrid dg = new DataGrid();
		if (ph == null) {
			logger.error("分页数据为空");
			return dg;
		}
		if (params == null) {
			params = new HashMap<String, Object>(32);
		}

		List<FirmwareTask> equipmentNos = firmwareTaskDao.queryAuditTest(params);
		if (equipmentNos.size() > 0) {
			params.put("list", equipmentNos);
		}
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		try {
			dg.setRows(stockDao.queryStockNotRelease(params));
			dg.setTotal(stockDao.countStockNotRelease(params));
			logger.info("查询设备库存信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public StockRseult returnStock(EquipmentReturnRecord equipmentReturnRecord, SessionInfo sessionInfo) {
		logger.info("退货信息:{}", equipmentReturnRecord);
		if (equipmentReturnRecord == null) {
			logger.error("退换货记录为空");
			return StockRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return StockRseult.SESSIONINFO_NULL;
		}

		try {
			// 生成入库记录
			Long returnId = inoutStockDao.generateId();
			Stock returnStock = stockDao.queryByEquipNo(equipmentReturnRecord.getEquipment_no());
			if ("1".equals(returnStock.getFlag())) {
				logger.error("仅出库设备可以退换货！");
				return StockRseult.RETURN_LIMIT;
			}
			// 如有固件更新任务，则删除
			if (returnStock.getTask_id() != 0) {
				firmwareTaskDao.deleteTask(equipmentReturnRecord.getEquipment_no(), "03");
				List<String> string = stockDao.queryEquipmentNoByTaskId(new Long((long) returnStock.getTask_id()));
				if (string.size() == 1) {
					firmwareTaskDao.delete(new Long((long) returnStock.getTask_id()));
				}
			}

			InOutStock returnInStock = inoutStockDao.queryAllById(returnStock.getInout_id());
			returnInStock.setFlag("1");
			returnInStock.setDealers_name("0");
			returnInStock.setEquipment_purpose("");
			int returnInSaveResult = inoutStockSave(returnInStock, equipmentReturnRecord.getEquipment_no(),
					sessionInfo.getUsername(), returnId);

			if (returnInSaveResult > 0) {
				// 修改退货设备一些状态
				returnStock.setFlag("1");
				returnStock.setHealthyState(equipmentReturnRecord.getHealthyState());
				returnStock.setState("N");
				returnStock.setTask_id(0);
				returnStock.setUpdate_status("0");
				returnStock.setInout_id(returnId);
				returnStock.setRemark(equipmentReturnRecord.getRemark());
				int updateByEquiNoResult = stockDao.updateByEquiNo(returnStock);
				if (updateByEquiNoResult > 0) {
					// 生成退货记录
					equipmentReturnRecord.setOp_user(sessionInfo.getUsername());
					equipmentReturnRecord.setType("1");
					equipmentReturnRecordDao.save(equipmentReturnRecord);
				}
			}
			logger.info("退货成功:{}", equipmentReturnRecord);
			return StockRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return StockRseult.REQUEST_FAIL;
	}

	@Override
	@Transactional
	public StockRseult exchangeStock(EquipmentReturnRecord equipmentReturnRecord, SessionInfo sessionInfo) {
		logger.info("换货信息:{}", equipmentReturnRecord);
		if (equipmentReturnRecord.getExchangeEquipmentNo().equals(equipmentReturnRecord.getEquipment_no())) {
			logger.error("换货设备号和被换货设备号不能一致！");
			return StockRseult.EXCHANGE_IS_CONSISTENT;
		}
		Stock returnStock = stockDao.queryByEquipNo(equipmentReturnRecord.getEquipment_no());
		if ("1".equals(returnStock.getFlag())) {
			logger.error("仅出库设备可以退换货！");
			return StockRseult.RETURN_LIMIT;
		}
		// 判断换货设备合法性
		Stock exchangeStock = stockDao.queryByEquipNo(equipmentReturnRecord.getExchangeEquipmentNo());
		if (exchangeStock == null) {
			logger.error("换货设备不存在");
			return StockRseult.EXCHANGE_STOCK_NULL;
		} else {
			if (!HEALTHY_STATUS.equals(exchangeStock.getHealthyState())) {
				logger.error("换货设备正处于维修中或已报废！");
				return StockRseult.EXCHANGE_STOCK_ISNOT_NORMAL;
			}
			if (ACTIVE_STATE.equals(exchangeStock.getState())) {
				logger.error("换货设备已处于使用中，请仔细核对！");
				return StockRseult.EXCHANGE_STOCK_IS_USE;
			}
		}
		try {
			// 生成退货设备的入库记录
			Long returnId = inoutStockDao.generateId();
			// 如有固件更新任务，则删除
			if (returnStock.getTask_id() != 0) {
				firmwareTaskDao.deleteTask(equipmentReturnRecord.getEquipment_no(), "03");
				List<String> string = stockDao.queryEquipmentNoByTaskId(new Long((long) returnStock.getTask_id()));
				if (string.size() == 1) {
					firmwareTaskDao.delete(new Long((long) returnStock.getTask_id()));
				}
			}
			InOutStock returnInStock = inoutStockDao.queryAllById(returnStock.getInout_id());
			InOutStock exchangeOutStock = inoutStockDao.queryAllById(exchangeStock.getInout_id());
			if (!returnInStock.getEquipment_model().equals(exchangeOutStock.getEquipment_model())) {
				logger.error("设备型号不一致！");
				return StockRseult.EQUIPMENT_MODEL_INCONSISTENT;
			}
			// 换货设备的出库记录的设备用途、经销商使用旧设备的
			exchangeOutStock.setDealers_name(returnInStock.getDealers_name());
			exchangeOutStock.setEquipment_purpose(returnInStock.getEquipment_purpose());
			returnInStock.setFlag("1");
			returnInStock.setDealers_name("0");
			returnInStock.setEquipment_purpose("");
			int returnInSaveResult = inoutStockSave(returnInStock, equipmentReturnRecord.getEquipment_no(),
					sessionInfo.getUsername(), returnId);
			if (returnInSaveResult > 0) {
				// 修改退货设备一些状态
				returnStock.setFlag("1");
				returnStock.setInout_id(returnId);
				returnStock.setTask_id(0);
				returnStock.setUpdate_status("0");
				returnStock.setHealthyState(equipmentReturnRecord.getHealthyState());
				returnStock.setState("N");
				returnStock.setRemark(equipmentReturnRecord.getRemark());
				int updateByEquiNoResult = stockDao.updateByEquiNo(returnStock);
				if (updateByEquiNoResult > 0) {
					if (RETURN_FLAG.equals(exchangeStock.getFlag())) {
						// 生成换货出库记录
						Long exchangeId = inoutStockDao.generateId();
						exchangeOutStock.setFlag("2");
						int exchangeOutSaveResult = inoutStockSave(exchangeOutStock,
								equipmentReturnRecord.getExchangeEquipmentNo(), sessionInfo.getUsername(), exchangeId);
						if (exchangeOutSaveResult > 0) {
							// 修改换货设备未出库状态
							exchangeStock.setFlag("2");
							exchangeStock.setInout_id(exchangeId);
							stockDao.update(exchangeStock);
						}
					}
					// 生成换货记录
					equipmentReturnRecord.setOp_user(sessionInfo.getUsername());
					equipmentReturnRecord.setType("2");
					equipmentReturnRecordDao.save(equipmentReturnRecord);
				}
			}
			logger.info("换货成功:{}", equipmentReturnRecord);
			return StockRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return StockRseult.REQUEST_FAIL;
	}

	@Override
	@Transactional
	public StockRseult healthyState(EquipmentReturnRecord equipmentReturnRecord, SessionInfo sessionInfo) {
		logger.info("退货信息:{}", equipmentReturnRecord);
		if (equipmentReturnRecord == null) {
			logger.error("退换货记录为空");
			return StockRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return StockRseult.SESSIONINFO_NULL;
		}
		try {
			stockDao.updateHealthyState(equipmentReturnRecord.getEquipment_no(),
					equipmentReturnRecord.getHealthyState());
			logger.info("修改健康状态成功:{}", equipmentReturnRecord);
			return StockRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return StockRseult.REQUEST_FAIL;
	}

	@Override
	public List<String> queryEquipmentNoByTaskId(long taskId) {
		return stockDao.queryEquipmentNoByTaskId(taskId);
	}

	@Override
	public int countByTaskID(int taskId) {
		List<String> list = stockDao.queryEquipmentNoByTaskId(taskId);
		return list.size();
	}

	@Override
	public Stock queryByEquipNo(String equipmentNo) {
		return stockDao.queryByEquipNo(equipmentNo);
	}

	@Override
	public List<Long> queryTaskIdByInOutId(long inoutId) {
		return stockDao.queryTaskIdByInOutId(inoutId);
	}

	public int inoutStockSave(InOutStock inoutStock, String equipmentNo, String username, Long id1) {
		inoutStock.setId(id1);
		inoutStock.setCounts(1);
		inoutStock.setBegin_sn(equipmentNo);
		inoutStock.setEnd_sn(equipmentNo);
		inoutStock.setOp_user(username);
		return inoutStockDao.save(inoutStock);
	}
}
