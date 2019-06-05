package com.ratta.suponote.stock.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.stock.dao.EquipmentReturnRecordDao;
import com.ratta.suponote.stock.dao.InOutStockDao;
import com.ratta.suponote.stock.dao.StockDao;
import com.ratta.suponote.stock.model.EquipmentReturnRecord;
import com.ratta.suponote.stock.model.InOutStock;
import com.ratta.suponote.stock.model.Stock;
import com.ratta.suponote.stock.result.InOutStockRseult;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 设备出入库记录业务层 2018-10-31
 */
@Service("inOutStockService")
@Transactional(rollbackFor = Exception.class)
public class InOutStockBean implements InOutStockService {

	private static Logger logger = LoggerFactory.getLogger(InOutStockBean.class);
	private static final String INOUT_FLAG = "1";
	@Autowired
	private InOutStockDao inOutStockDao;
	@Autowired
	private EquipTypeDao equipTypeDao;
	@Autowired
	private EquipmentReturnRecordDao equipmentReturnRecordDao;
	@Autowired
	private StockDao stockDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询出入库记录,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(inOutStockDao.query(params));
			dg.setTotal(inOutStockDao.count(params));
			logger.info("查询出入库记录成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public DataGrid queryBatchNo(PageHelper ph, Map<String, Object> params) {
		logger.info("查询批次号,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(inOutStockDao.queryBatchNo(params));
			dg.setTotal(inOutStockDao.countBatchNo(params));
			logger.info("查询批次号成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	public List<InOutStock> querySum(Map<String, Object> params) {
		logger.info("不分页查询出入库记录,查询参数:{}", BaseUtil.map2String(params));
		try {
			List<InOutStock> driverSums = inOutStockDao.querySumAll(params);
			for (InOutStock inOutStock : driverSums) {
				if ("1".equals(inOutStock.getFlag())) {
					inOutStock.setFlag("入库");
				}
				if ("2".equals(inOutStock.getFlag())) {
					inOutStock.setFlag("出库");
				}
				if ("1".equals(inOutStock.getStatus())) {
					inOutStock.setStatus("正常");
				}
				if ("2".equals(inOutStock.getStatus())) {
					inOutStock.setStatus("撤销");
				}
			}
			logger.info("不分页查询出入库记录数据成功,总记录数:{}", driverSums.size());
			return driverSums;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("不分页查询出入库记录数据异常:{}", e);
			return null;
		}
	}

	@Override
	public InOutStock query(Long id) {
		logger.info("根据id:{}查询出入库记录信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			InOutStock inOutStock = inOutStockDao.queryAllById(id);
			logger.info("查询出入库记录信息成功:{}", inOutStock);
			return inOutStock;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	public InOutStock queryByInoutId(Long inoutId, String flag) {
		logger.info("根据inout_id:{}查询出入库记录信息", inoutId);
		if (StringUtils.isEmpty(inoutId)) {
			logger.error("inoutId为空");
			return null;
		}
		try {
			InOutStock inOut = inOutStockDao.queryByInoutId(inoutId, flag);
			if (inOut.getCounts() != 0) {
				List<String> sns = generateSn(inOut);
				Collections.sort(sns);
				inOut.setEnd_sn(sns.get(sns.size() - 1));
			}
			logger.info("查询出入库记录信息成功:{}", inOut);
			return inOut;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public InOutStockRseult in(InOutStock inOutStock, SessionInfo sessionInfo) {
		logger.info("入库操作:{}", inOutStock);
		if (inOutStock == null) {
			logger.error("出入库记录为空");
			return InOutStockRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return InOutStockRseult.SESSIONINFO_NULL;
		}
		InOutStock inOut = inOutStockDao.queryByBatchNo(inOutStock.getBatch_no());
		if (inOut != null) {
			if (!inOutStock.getEquipment_model().equals(inOut.getEquipment_model())
					|| !inOutStock.getFirmware_version().trim().equals(inOut.getFirmware_version().trim())) {
				logger.error("批次号和设备型号或固件版本号不匹配！");
				return InOutStockRseult.NO_MATCH;
			}
		}
		List<String> sns = null;
		try {
			sns = generateSn(inOutStock);
		} catch (Exception e) {
			logger.error("生成序列号SN异常:", e);
			return InOutStockRseult.REQUEST_FAIL;
		}
		for (String sn : sns) {
			Stock stock = stockDao.queryByEquipNo(sn);
			if (stock != null) {
				logger.error("设备序列号:{}已经入库", sn);
				return InOutStockRseult.SN_EXISTS;
			}
		}
		inOutStock.setOp_user(sessionInfo.getUsername());
		inOutStock.setFlag("1");
		inOutStock.setStatus("1");
		inOutStock.setArea(inOutStock.getFirmware_version().split("\\.")[1].substring(1, 2));
		inOutStock.setCustom(inOutStock.getFirmware_version().split("\\.")[1].substring(2, 3));
		inOutStock.setConfiguration(inOutStock.getFirmware_version().split("\\.")[1].substring(3));
		Long id = inOutStockDao.generateId();
		inOutStock.setId(id);
		int result = inOutStockDao.save(inOutStock);
		logger.info("添加入库信息成功,数据库影响行数:{}", result);

		List<Stock> stocks = generateStock(inOutStock, sns);
		int resultStock = 0;
		for (Stock stock : stocks) {
			resultStock += stockDao.save(stock);
		}
		logger.info("设备入库操作成功,成功入库:{}", resultStock);
		return InOutStockRseult.SUCCESS;
	}

	@Override
	@Transactional
	public InOutStockRseult out(List<InOutStock> inOutStockList, SessionInfo sessionInfo) {
		logger.info("出库操作");
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return InOutStockRseult.SESSIONINFO_NULL;
		}
		if (inOutStockList == null || inOutStockList.size() <= 0) {
			logger.error("出入库记录为空");
			return InOutStockRseult.REQUEST_FAIL;
		}
		try {
			for (InOutStock inOutStock : inOutStockList) {
				Stock stock = stockDao.queryByEquipNo(inOutStock.getEquipment_no());
				if (stock == null) {
					logger.error("库存中没有sn:{}的设备", inOutStock.getEquipment_no());
					return InOutStockRseult.STOCK_NULL;
				}
				if (!"1".equals(stock.getFlag())) {
					logger.error("存在已经出库的设备sn:{},不可以出库操作", inOutStock.getEquipment_no());
					return InOutStockRseult.SN_HAS_OUTSOTCK_ERROR;
				}
				if (!"0".equals(stock.getHealthyState())) {
					logger.error("出库设备中存在维修中或已报废的设备！");
					return InOutStockRseult.EQUIPMENT_NOT_NORMAL;
				}
				
				Long id = inOutStockDao.generateId();
				InOutStock inout = inOutStockDao.queryByInoutId(stock.getInout_id(), "1");
				inout.setOp_user(sessionInfo.getUsername());
				inout.setId(id);
				inout.setFlag("2");
				inout.setStatus("1");
				inout.setCounts(1);
				inout.setEquipment_purpose(inOutStock.getEquipment_purpose());
				inout.setDealers_name(inOutStock.getDealers_name());
				inout.setBegin_sn(inOutStock.getEquipment_no());
				inout.setEnd_sn(inOutStock.getEquipment_no());
				inout.setFirmware_version(inOutStock.getFirmware_version());
				inout.setNote(inOutStock.getNote());
				int result = inOutStockDao.save(inout);
				logger.info("添加出库记录成功:{},数据库影响行数:{}", inout, result);
				
				stock.setFlag("2");
				stock.setInout_id(id);
				stock.setReleaseFirmwareVersion(inOutStock.getFirmware_version());
				stockDao.update(stock);
			}
			logger.info("出库成功");
			return InOutStockRseult.SUCCESS;
		} catch (Exception e) {
			logger.info("出库失败");
			return InOutStockRseult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public InOutStockRseult undoInOutStock(Long id, SessionInfo sessionInfo) {
		logger.info("撤销出入库信息,出入库记录id:{}", id);
		if (id == null) {
			logger.error("出入库记录id为空");
			return InOutStockRseult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("session超时");
			return InOutStockRseult.SESSIONINFO_NULL;
		}
		InOutStock inOut = inOutStockDao.queryAllById(id);
		if (inOut == null) {
			logger.error("没有id:{}的出入库记录", id);
			return InOutStockRseult.REQUEST_FAIL;
		}
		// 1判断是否存在已经出库的设备信息
		List<String> sns = null;
		try {
			sns = generateSn(inOut);
		} catch (Exception e) {
			logger.error("生成序列号SN异常:", e);
			return InOutStockRseult.REQUEST_FAIL;
		}
		for (String sn : sns) {
			List<EquipmentReturnRecord> equipmentReturnRecord = equipmentReturnRecordDao.queryByEquipmentNo(sn);
			if (equipmentReturnRecord != null && equipmentReturnRecord.size() > 0) {
				logger.error("出库单存在有退换货记录的设备，不可以撤销！");
				return InOutStockRseult.RETURN_DEVICE_NOT_REVOKE;
			}
		}

		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("inout_id", inOut.getId());
		List<Stock> stocks = stockDao.querySumAll(params);

		if (INOUT_FLAG.equals(inOut.getFlag())) {
			if (stocks.size() != inOut.getCounts()) {
				logger.error("入库单id:{}中存在已经出库的设备,不能撤销", id);
				return InOutStockRseult.INOUT_IS_OUTSTOCK_ERROR;
			}
			inOut.setStatus("2");
			inOut.setOp_user(sessionInfo.getUsername());
			int result = inOutStockDao.update(inOut);
			logger.info("入库撤销成功,数据库影响行数:{}", result);

			result = stockDao.delete(inOut.getId());
			logger.info("删除库存信息成功,数据库影响行数:{}", result);
			return InOutStockRseult.SUCCESS;
		} else {// 出库单撤销
			int result = 0;
			logger.info("更新出库单成功,数据库影响行数:{}", result);
			for (Stock stock : stocks) {
				if ("Y".equals(stock.getState())) {
					logger.error("出库单存在已经激活的设备，不可以撤销！");
					return InOutStockRseult.ACTIVATED_DEVICE_NOT_REVOKE;
				}
				stock.setFlag("1");
				InOutStock in = inOutStockDao.queryNewInOut(stock.getEquipment_no());
				logger.info("根据sn:{}查询相应的入库信息:{}", stock.getEquipment_no(), in);
				stock.setInout_id(in.getId());
				result += stockDao.update(stock);
			}
			inOut.setStatus("2");
			inOut.setOp_user(sessionInfo.getUsername());
			inOutStockDao.update(inOut);
			logger.info("库存撤销出库成功,数据库影响行数:{}", result);
			return InOutStockRseult.SUCCESS;
		}
	}

	@Override
	@Transactional
	public InOutStockRseult save(List<InOutStock> inOutStocks, SessionInfo sessionInfo) {
		Stock stock = null;
		Map<String, String> maps = new HashMap<String, String>(128);
		if (sessionInfo == null) {
			logger.error("session超时");
			return InOutStockRseult.SESSIONINFO_NULL;
		}
		if (inOutStocks == null || inOutStocks.size() <= 0) {
			logger.error("没有需要导入的入库记录！");
			return InOutStockRseult.NO_IMPORT_DATA;
		}
		try {
			boolean equipmentResult = isLegalEquipment(inOutStocks);
			if (equipmentResult) {
				logger.error("存在不符合设备型号的设备号！");
				return InOutStockRseult.IS_LEGAL_EQUIPMENT;
			}
			for (InOutStock inOutStock : inOutStocks) {
				// 在已经入过库的情况下，判断批次号和设备型号和版本号是否匹配
				InOutStock inOut = inOutStockDao.queryByBatchNo(inOutStock.getBatch_no());
				if (inOut != null) {
					if (!inOutStock.getEquipment_model().equals(inOut.getEquipment_model())
							|| !inOutStock.getFirmware_version().trim().equals(inOut.getFirmware_version().trim())) {
						logger.error("批次号和设备型号或固件版本号不匹配！");
						return InOutStockRseult.NO_MATCH;
					}
				}
				// 在未入库的情况下，判断批次号和版本号是否匹配
				if (maps.get(inOutStock.getBatch_no()) != null && maps.get(inOutStock.getBatch_no()) != "") {
					if (!maps.get(inOutStock.getBatch_no()).equals(inOutStock.getFirmware_version())) {
						logger.error("批次号和设备型号或固件版本号不匹配！");
						return InOutStockRseult.NO_MATCH;
					}
				} else {
					maps.put(inOutStock.getBatch_no(), inOutStock.getFirmware_version());
				}
				// 判断设备号是否存在
				Stock isStock = stockDao.queryByEquipNo(inOutStock.getEquipment_no());
				if (isStock != null) {
					logger.error("设备序列号:{}已经入库", inOutStock.getEquipment_no());
					return InOutStockRseult.SN_EXISTS;
				}
			}
			for (InOutStock inOutStock : inOutStocks) {
				Long id = inOutStockDao.generateId();
				inOutStock.setCounts(1);
				inOutStock.setId(id);
				inOutStock.setFlag("1");
				inOutStock.setStatus("1");
				inOutStock.setArea(inOutStock.getFirmware_version().split("\\.")[1].substring(1, 2));
				inOutStock.setCustom(inOutStock.getFirmware_version().split("\\.")[1].substring(2, 3));
				inOutStock.setConfiguration(inOutStock.getFirmware_version().split("\\.")[1].substring(3));
				inOutStock.setOp_user(sessionInfo.getUsername());
				inOutStockDao.save(inOutStock);
				stock = new Stock();
				stock.setFlag("1");
				stock.setState("N");
				stock.setUpdate_status("0");
				stock.setHealthyState("0");
				stock.setEquipment_no(inOutStock.getEquipment_no());
				stock.setBatch_no(inOutStock.getBatch_no());
				stock.setEquipment_model(inOutStock.getEquipment_model());
				stock.setInout_id(id);
				stock.setFirmware_version(inOutStock.getFirmware_version());
				stockDao.save(stock);
			}
			return InOutStockRseult.SUCCESS;
		} catch (Exception e) {
			logger.error("操作数据库异常{}", e);
			return InOutStockRseult.REQUEST_FAIL;
		}
	}

	/**
	 * 
	 * <p>
	 * isLegalEquipment
	 * </p>
	 * <p>
	 * 判断设备号是否是合法设备型号
	 * </p>
	 * 
	 * @param equipmentModel 设备型号
	 * @param inOutStocks    设备集合
	 * @return 结果
	 */
	public boolean isLegalEquipment(List<InOutStock> inOutStocks) {
		EquipType equipType = equipTypeDao.query(Long.parseLong(inOutStocks.get(0).getEquipment_model()));
		for (InOutStock inOutStock : inOutStocks) {
			String head = inOutStock.getBegin_sn().substring(0, equipType.getSn_front().length());
			int sumLength = inOutStock.getBegin_sn().length();
			// 判断总长度是否相符
			if (sumLength != equipType.getSn_length()) {
				return true;
			}
			// 判断SN头是否相同
			if (!head.equals(equipType.getSn_front())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * generateSn
	 * </p>
	 * <p>
	 * 根据出(入)库单计算出设备sn
	 * </p>
	 * 
	 * @param inOut 出(入)库信息
	 * @return 设备sn列表
	 */
	private List<String> generateSn(InOutStock inOut) {
		List<String> sns = new ArrayList<String>();
		EquipType equipType = equipTypeDao.query(Long.parseLong(inOut.getEquipment_model()));
		if (equipType != null) {
			int offset = equipType.getSn_offset();
			int length = equipType.getSn_offset_length();

			String sn = inOut.getBegin_sn();
			String begin = sn.substring(0, offset - 1);
			String num = sn.substring(offset - 1, offset - 1 + length);
			String end = sn.substring(offset - 1 + length);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				sb.append("0");
			}
			DecimalFormat df = new DecimalFormat(sb.toString());
			for (int i = 0; i < inOut.getCounts(); i++) {
				Long num2 = Long.parseLong(num) + i;
				sns.add(begin + df.format(num2) + end);
			}
		}
		return sns;
	}

	

	@Override
	public List<InOutStock> query(Map<String, Object> params) {
		return inOutStockDao.query(params);
	}

	@Override
	public List<InOutStock> queryEquipmentPurpose(String batchNo) {
		return inOutStockDao.queryEquipmentPurpose(batchNo);
	}
	/**
	 * 
	 * <p>
	 * generateStock
	 * </p>
	 * <p>
	 * 根据入库单,设备sn 生成库存信息(只有入库的时候用)
	 * </p>
	 * 
	 * @param inOut 入库单信息
	 * @param sns   设备sn列表
	 * @return 库存信息列表
	 */
	private List<Stock> generateStock(InOutStock inOut, List<String> sns) {
		List<Stock> stocks = new ArrayList<Stock>();
		for (String sn : sns) {
			Stock stock = new Stock();
			stock.setFlag("1");
			stock.setEquipment_model(inOut.getEquipment_model());
			stock.setEquipment_no(sn);
			stock.setHealthyState("0");
			stock.setInout_id(inOut.getId());
			stock.setBatch_no(inOut.getBatch_no());
			stock.setFirmware_version(inOut.getFirmware_version());
			stock.setState("N");
			stock.setTask_id(0);
			stock.setUpdate_status("0");
			stocks.add(stock);
		}
		return stocks;
	}
	
}
