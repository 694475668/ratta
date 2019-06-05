package com.ratta.suponote.snuser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.param.dao.DictionaryDao;
import com.ratta.suponote.param.model.Dictionary;
import com.ratta.suponote.snuser.dao.LoginRecordDao;
import com.ratta.suponote.usersn.model.LoginRecord;
import com.ratta.suponote.usersn.service.LoginRecordService;
import com.ratta.suponotes.util.BaseUtil;

/**
 * @author page SN用户登录记录类 2018-10-31
 */
@Service("loginRecordService")
@Transactional(rollbackFor = Exception.class)
public class LoginRecordServiceBean implements LoginRecordService {
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(LoginRecordServiceBean.class);
	/**
	 * 持久层dao
	 */
	@Autowired
	private LoginRecordDao loginRecordDao;
	@Autowired
	private DictionaryDao dictionaryDao;

	@Override
	public DataGrid dataGrid(PageHelper ph, Map<String, Object> params) {
		logger.info("调用加载用户登录记录界面,查询参数:{},分页参数:{}", BaseUtil.map2String(params), ph);
		DataGrid dg = new DataGrid();
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		List<LoginRecord> loginRecordList = loginRecordDao.find(params);
		Map<String, Object> dictionaryParam = new HashMap<String, Object>();
		for (LoginRecord loginRecord : loginRecordList) {
			String telephone =loginRecord.getTelephone(); 
			String email =loginRecord.getEmail(); 
			if ("1".equals(loginRecord.getLogin_method())) {
				loginRecord.setUsername(telephone.replace(telephone.substring(3, 7), "****"));
			} 
			if ("2".equals(loginRecord.getLogin_method())) {
				loginRecord.setUsername(email.replace(email.substring(1, 3), "**"));
			}
			dictionaryParam.put("name", "LOGIN_METHOD");
			dictionaryParam.put("value", loginRecord.getLogin_method());
			Dictionary Dictionary = dictionaryDao.queryByPamram(dictionaryParam);
			loginRecord.setLogin_method(Dictionary.getValue_cn());
		}
		dg.setRows(loginRecordList);
		dg.setTotal(loginRecordDao.count(params));
		logger.info("查询用户登录记录成功,总记录数:{}", dg.getTotal());
		return dg;
	}
}
