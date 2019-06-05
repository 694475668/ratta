package com.ratta.suponote.snuser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;
import com.ratta.suponote.snuser.dao.SNUserDao;
import com.ratta.suponote.usersn.model.SNUser;
import com.ratta.suponote.usersn.result.SnUserResult;
import com.ratta.suponote.usersn.service.SNUserService;
import com.ratta.suponotes.util.BaseUtil;

/**
 * Title: SNUserServiceBean
 * Description: SN100用户业务层
 */
@Service("snuserService")
public class SNUserServiceBean implements SNUserService {
	private Logger logger = LoggerFactory.getLogger(SNUserServiceBean.class);

	@Autowired
	private SNUserDao userDao;

	public DataGrid dataGrid(PageHelper ph, Map<String, Object> params) {
		logger.info("调用加载用户管理界面,查询参数:{},分页参数:{}", BaseUtil.map2String(params), ph);
		DataGrid dg = new DataGrid();
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		List<SNUser> snuserList = userDao.find(params);
		for (SNUser snUser : snuserList) {
			String telephone =snUser.getTelephone(); 
			String email =snUser.getEmail(); 
			if(telephone!=null && !"".equals(telephone.trim())) {
				snUser.setTelephone(telephone.replace(telephone.substring(3, 7), "****"));
			}
            if(email!=null && !"".equals(email.trim())) {
            	snUser.setEmail(email.replace(email.substring(1, 3), "**"));
			}
		}
		dg.setRows(snuserList);
		dg.setTotal(userDao.count(params));
		logger.info("查询用户数据成功,总记录数:{}", dg.getTotal());
		return dg;
	}


	@Override
	@Transactional
	public SnUserResult updateIsNormal(String userid, String isNormal) {
		logger.info("冻结/解冻用户,userid:{}", userid);
		int result = 0;
		SNUser t = userDao.get(userid);
		if (isNormal.equals(t.getIsNormal())) {
			return SnUserResult.STATUS_SAME;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		try {
			result = userDao.updateIsNormal(userid, isNormal);

			if (result == 1) {
				logger.info("冻结/解冻用户成功,数据库影响行数:{}", result);
				return SnUserResult.SUCCESS;
			} else {
				logger.info("冻结/解冻用户失败,数据库影响行数:{}", result);
				return SnUserResult.REQUEST_FAIL;
			}
		} catch (Exception e) {
			logger.error("数据库操作异常:{}", e.getMessage());
			return SnUserResult.REQUEST_FAIL;
		}
	}

	@Override
	public SNUser get(String userid) {
		logger.info("调用根据userid:{}获取用户", userid);
		SNUser t = userDao.get(userid);
		logger.info("根据userid:{}成功获取用户信息:{}", userid, t);
		return t;

	}

	@Override
	@Transactional
	public SnUserResult updateAccount(SNUser snuser, SessionInfo sessionInfo) {
		logger.info("更新云盘用户信息:{}", snuser);

		if (snuser == null) {
			logger.error("云盘用户信息为空");
			return SnUserResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return SnUserResult.SESSIONINFO_NULL;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			if(snuser.getTelephone() == "" || snuser.getTelephone() == null) {
				params.put("email", snuser.getEmail());
				if (!snuser.getOldEmail().equals(snuser.getEmail())) {
					Long countEmail = userDao.countSnUserByTelphoneOrEmail(params);
					if (countEmail > 0) {
						return SnUserResult.EMAIL_EXITS;
					}
				}else {
					return SnUserResult.EMAIL_SAME;
				}
			}else {
				params.put("telephone", snuser.getTelephone());
				if (!snuser.getOld_telephone().equals(snuser.getTelephone())) {
					Long countTelphone = userDao.countSnUserByTelphoneOrEmail(params);
					if (countTelphone > 0) {
						return SnUserResult.TELPHONE_EXITS;
					}
				}else {
					return SnUserResult.TELPHONE_SAME;
				}
			}
			userDao.updateAccount(snuser);
			return SnUserResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return SnUserResult.REQUEST_FAIL;
		}
	}

	@Override
	@Transactional
	public SnUserResult unBund(Long id, SessionInfo sessionInfo) {
		logger.info("删除id={}的用户设备绑定信息", id);
		if (StringUtils.isEmpty(id)) {
			logger.error("id为空");
			return SnUserResult.REQUEST_FAIL;
		}
		if (sessionInfo == null) {
			logger.error("操作员信息为空");
			return SnUserResult.SESSIONINFO_NULL;
		}
		try {
			userDao.unBund(id);
			return SnUserResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据库操作异常:{}", e.getMessage());
			return SnUserResult.REQUEST_FAIL;
		}
	}

	@Override
	public String getUsernameByEquipmentNo(String equipment_number) {
		return userDao.getUsernameByEquipmentNo(equipment_number);
	}
}
