package com.ratta.suponote.snuser.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.usersn.model.UserEquipment;

/**
 * 
 * Title: userEquipmentDao
 * Description: SN100用户设备交互层
 */
@Repository("userEquipmentDao")
public class UserEquipmentDao extends SqlSessionDaoSupport {
	/**
	 * query
	 * 查询所有记录
	 * @return 记录列表
	 */
	public List<UserEquipment> query(Map<String, Object> params) {
		List<UserEquipment> userEquipments = getSqlSession().selectList("userEquipment.getAllUserEquipment",params);
		return userEquipments;
	}

	/**
	 * count
	 * 统计所有记录
	 * @return 统计结果
	 */
	public Long count(Map<String, Object> params) {
		return getSqlSession().selectOne("userEquipment.countAllUserEquipment", params);
	}

	/**
	 * get
	 * 根据ID查询用户设备信息
	 * @param id ID
	 * @return 用户设备信息
	 */
	public UserEquipment get(int id) {
		UserEquipment userEquipment = getSqlSession().selectOne("userEquipment.getUserEquipmentById", id);
		return userEquipment;
	}
	/**
	 * getUserEquipmentByUserId
	 * 根据ID查询用户设备信息
	 * @param userId 用户ID
	 * @return 用户设备信息集合
	 */
	public List<UserEquipment> getUserEquipmentByUserId(Long userId) {
		List<UserEquipment> userEquipment = getSqlSession().selectList("userEquipment.getUserEquipmentByUserId", userId);
		return userEquipment;
	}

}