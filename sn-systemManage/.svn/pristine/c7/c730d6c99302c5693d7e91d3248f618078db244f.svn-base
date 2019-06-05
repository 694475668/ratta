package com.ratta.suponote.param.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.suponote.param.dao.DictionaryDao;
import com.ratta.suponote.param.model.Dictionary;
import com.ratta.suponote.param.result.DictionaryResult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page 数据字典服务 2018-10-31
 */
@Service("dictionaryService")
@Transactional(rollbackFor = Exception.class)
public class DictionaryBean implements DictionaryService {
	private static Logger logger = LoggerFactory.getLogger(DictionaryBean.class);
	@Autowired
	private DictionaryDao dictionaryDao;

	@Override
	public DataGrid query(PageHelper ph, Map<String, Object> params) {
		logger.info("查询数据字典信息,查询参数:{}", BaseUtil.map2String(params));
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
			dg.setRows(dictionaryDao.query(params));
			dg.setTotal(dictionaryDao.count(params));
			logger.info("查询数据字典信息成功,总记录数:{}", dg.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
		}
		return dg;
	}

	@Override
	@Transactional
	public DictionaryResult save(Dictionary dictionary, SessionInfo sessionInfo) {
		logger.info("添加数据字典信息:{}", dictionary);
		if (dictionary == null) {
			logger.error("数据字典信息为空");
			return DictionaryResult.DICTIONARY_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return DictionaryResult.SESSIONINFO_NULL;
		}
		dictionary.setOp_user(sessionInfo.getUsername());

		Map<String, Object> params = new HashMap<String, Object>(8);
		params.put("name", dictionary.getName());
		params.put("value", dictionary.getValue());

		Dictionary diction = dictionaryDao.queryByPamram(params);

		if (diction != null) {
			logger.error("同一个业务码下不允许存在相同编码！");
			return DictionaryResult.SERIAL_IS_SAME;
		}

		try {
			int result = dictionaryDao.save(dictionary);
			logger.info("添加操作员信息成功,数据库影响行数:{}", result);
			return DictionaryResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return DictionaryResult.DATABASE_ERROR;
		}
	}

	@Override
	public Dictionary query(Long id) {
		logger.info("根据id:{}查询数据字典信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return null;
		}
		try {
			Dictionary dictionary = dictionaryDao.query(id);
			logger.info("查询数据字典信息成功:{}", dictionary);
			return dictionary;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作员异常:{}", e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public DictionaryResult update(Dictionary dictionary, SessionInfo sessionInfo) {
		if (dictionary == null) {
			logger.error("数据字典信息为空");
			return DictionaryResult.DICTIONARY_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return DictionaryResult.SESSIONINFO_NULL;
		}
		dictionary.setOp_user(sessionInfo.getUsername());
		try {
			int result = dictionaryDao.update(dictionary);
			logger.info("更新数据字典信息成功,数据库影响行数:{}", result);
			return DictionaryResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return DictionaryResult.DATABASE_ERROR;
		}
	}

	@Override
	@Transactional
	public DictionaryResult delete(Long id, SessionInfo sessionInfo) {
		logger.info("删除id={}的数据字典信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("数据字典信息id为空");
			return DictionaryResult.DICTIONARY_ID_NULL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return DictionaryResult.SESSIONINFO_NULL;
		}
		try {
			int result = dictionaryDao.delete(id);
			logger.info("删除id={}的数据字典信息成功,数据库影响行数:{}", id, result);
			return DictionaryResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return DictionaryResult.DATABASE_ERROR;
		}
	}

	@Override
	public List<Dictionary> queryDictionary(String name) {
		logger.info("查询name={}的数据字典信息", name);
		if (StringUtils.isEmpty(name)) {
			logger.error("数据字典信息name为空");
			return null;
		}
		try {
			List<Dictionary> dictionarys = dictionaryDao.queryNoPage(name);
			logger.info("查询数据字典详细信息成功,总记录数:{}", dictionarys.size());
			return dictionarys;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询数据字典信息失败:{}", e);
			return null;
		}
	}

}
