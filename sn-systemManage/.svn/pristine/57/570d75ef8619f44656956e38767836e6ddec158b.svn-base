package com.ratta.suponote.snuser.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.snuser.dao.UserEquipmentDao;
import com.ratta.suponote.usersn.model.UserEquipment;
import com.ratta.suponote.usersn.service.UserEquipmentService;
import com.ratta.suponotes.util.BaseUtil;

/**
 * Title: SNUserServiceBean
 * Description: SN100用户业务层
 */
@Service("userEquipmentService")
public class UserEquipmentServiceBean implements UserEquipmentService {
	private Logger logger = LoggerFactory.getLogger(UserEquipmentServiceBean.class);

	@Autowired
	private UserEquipmentDao userEquipmentDao;


	public DataGrid dataGrid(PageHelper ph, Map<String, Object> params) {
		logger.info("调用加载用户设备管理界面,查询参数:{},分页参数:{}", BaseUtil.map2String(params), ph);
		DataGrid dg = new DataGrid();
		params.put("p_begin", (ph.getPage() - 1) * ph.getRows());
		params.put("p_end", ph.getRows());
		List<UserEquipment> userEquipmentList = userEquipmentDao.query(params);
		for (UserEquipment userEquipment : userEquipmentList) {
			String telephone =userEquipment.getTelephone(); 
			String email =userEquipment.getEmail(); 
			if(telephone!=null && !"".equals(telephone.trim())) {
				userEquipment.setTelephone(telephone.replace(telephone.substring(3, 7), "****"));
			}
            if(email!=null && !"".equals(email.trim())) {
            	userEquipment.setEmail(email.replace(email.substring(1, 3), "**"));
			}
		}
		dg.setRows(userEquipmentList);
		dg.setTotal(userEquipmentDao.count(params));
		logger.info("查询用户设备数据成功,总记录数:{}", dg.getTotal());
		return dg;
	}



	@Override
	public UserEquipment get(int id) {
		logger.info("调用根据id:{}获取用户设备信息", id);
		UserEquipment userEquipment = userEquipmentDao.get(id);
		return userEquipment;

	}

	
}
