package com.ratta.suponote.business.service;

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
import com.ratta.suponote.business.dao.DealersDao;
import com.ratta.suponote.business.model.Dealers;
import com.ratta.suponote.business.result.DealersRseult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.param.dao.DictionaryDao;
import com.ratta.suponote.stock.dao.InOutStockDao;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 经销商业务层 2018-10-31
 */
@Service("dealersService")
@Transactional(rollbackFor = Exception.class)
public class DealersBean implements DealersService {

	private static Logger logger = LoggerFactory.getLogger(DealersBean.class);
	@Autowired
	private DealersDao dealersDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private InOutStockDao inOutStockDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询经销商信息,查询参数:{}", BaseUtil.map2String(params));
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
			Map<String,Object> queryParam = new HashMap<String,Object>();
			List<Dealers> dealersList = dealersDao.query(params);
			for (Dealers dealers : dealersList) {
				queryParam.put("name", "DEALERS_TYPE");
				queryParam.put("value", dealers.getDealersType());
				dealers.setDealersType(dictionaryDao.queryByPamram(queryParam).getValue_cn());
			}
			dg.setRows(dealersList);
			dg.setTotal(dealersDao.count(params));
			logger.info("查询经销商信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public DealersRseult save(Dealers dealers, SessionInfo sessionInfo) {
		logger.info("添加经销商信息:{}", dealers);
		if (dealers == null) {
			logger.error("数据经销商为空");
			return DealersRseult.DICTIONARY_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return DealersRseult.SESSIONINFO_NULL;
		}
		try {
			Dealers infos = dealersDao.queryByDealersName(dealers.getDealersName().trim());
			if (infos != null) {
				logger.error("该经销商已经存在！");
				return DealersRseult.DEALERS_IS_EXISTS;
			}
			int result = dealersDao.save(dealers);
			logger.info("添加操作员信息成功,数据库影响行数:{}", result);
			return DealersRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return DealersRseult.DATABASE_ERROR;
		}
	}

	@Override
	@Transactional
	public DealersRseult update(Dealers dealers, SessionInfo sessionInfo) {
		if (dealers == null) {
			logger.error("经销商信息为空");
			return DealersRseult.DICTIONARY_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return DealersRseult.SESSIONINFO_NULL;
		}
		try {
			Dealers oldDealer = dealersDao.query(dealers.getId());
			if (!(dealers.getDealersName().trim()).equals(oldDealer.getDealersName())) {
				Dealers infos = dealersDao.queryByDealersName(dealers.getDealersName().trim());
				if (infos != null) {
					logger.error("该经销商已经存在！");
					return DealersRseult.DEALERS_IS_EXISTS;
				}
			}
			int result = dealersDao.update(dealers);
			logger.info("更新经销商信息成功,数据库影响行数:{}", result);
			return DealersRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return DealersRseult.DATABASE_ERROR;
		}
	}

	@Override
	@Transactional
	public DealersRseult delete(Long id, SessionInfo sessionInfo) {
		logger.info("删除id={}的经销商信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("数据经销商id为空");
			return DealersRseult.DICTIONARY_ID_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return DealersRseult.SESSIONINFO_NULL;
		}
		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("dealers_name", id);
		try {
			Long counts = inOutStockDao.queryByParams(params);
			if (counts > 0) {
				logger.error("该经销商下有绑定设备！");
				return DealersRseult.DEALERS_EQUIPMENT_EXISTS;
			}
			int result = dealersDao.delete(id);
			logger.info("删除id={}的经销商信息成功,数据库影响行数:{}", id, result);
			return DealersRseult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return DealersRseult.DATABASE_ERROR;
		}
	}

	@Override
	public Dealers query(Long id) {
		logger.info("根据id:{}查询经销商信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			Dealers dealers = dealersDao.query(id);
			logger.info("查询经销商信息成功:{}", dealers);
			return dealers;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	/**
	 * 导入经销商信息
	 */
	@Override
	@Transactional
	public String save(List<Dealers> dealers, SessionInfo sessionInfo) {
		logger.info("开始导入经销商信息");
		String msg = null;
		if (dealers == null || dealers.size() <= 0) {
			msg = "没有要导入的经销商信息";
			logger.error(msg);
			return msg;
		}
		if (sessionInfo == null) {
			msg = "操作员信息为空";
			logger.error(msg);
			return msg;
		}
		int add = 0;
		int update = 0;
		List<Dealers> adds = new ArrayList<Dealers>();
		List<Dealers> updates = new ArrayList<Dealers>();

		for (Dealers info : dealers) {
			Dealers infos = dealersDao.queryByDealersName(info.getDealersName());
			if (infos != null) {
				info.setId(infos.getId());
				updates.add(info);
			} else {
				adds.add(info);
			}
		}
		for (Dealers info : adds) {
			add += dealersDao.save(info);
		}
		logger.info("新增经销商数:{}", add);
		for (Dealers info : updates) {
			update += dealersDao.update(info);
		}
		logger.info("更新经销商信息数:{}", update);
		return "导入经销商信息成功,新增:" + add + "条,更新:" + update + "条";
	}

	/**
	 * 导出数据
	 */
	@Override
	public List<Dealers> querySum(Map<String, Object> params) {
		logger.info("不分页查询经销商数据,查询参数:{}", BaseUtil.map2String(params));
		try {
			List<Dealers> driverSums = dealersDao.querySumAll(params);
			logger.info("不分页查询经销商数据成功,总记录数:{}", driverSums.size());
			return driverSums;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("不分页查询经销商数据异常:{}", e);
			return null;
		}
	}

}
