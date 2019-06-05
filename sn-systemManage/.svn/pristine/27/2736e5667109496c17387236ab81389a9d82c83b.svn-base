package com.ratta.suponote.snuser.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.usersn.model.SNUser;

/**
 * 
 * Title: UserDao
 * Description: SN100用户数据交互层
 */
@Repository("snuserDao")
public class SNUserDao extends SqlSessionDaoSupport {
	/**
	 * find
	 * 查询所有记录
	 * @return 记录列表
	 */
	public List<SNUser> find(Map<String, Object> params) {
		List<SNUser> users = getSqlSession().selectList("snuser.getAllSNUser",params);
		return users;
	}

	/**
	 * count
	 * 统计所有记录
	 * @return 统计结果
	 */
	public Long count(Map<String, Object> params) {
		return getSqlSession().selectOne("snuser.countAllSNUser", params);
	}


	/**
	 * update
	 * 冻结用户
	 * @param userid 用户id
	 * @param isNormal 是否正常标识
	 * @return 更新结果
	 */
	public int updateIsNormal(String userid,String isNormal) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userid", userid);
		params.put("isNormal", isNormal);
		return getSqlSession().update("snuser.updateIsNormal", params);
	}

	/**
	 * get
	 * 根据用户ID查询用户信息
	 * @param id 用户ID
	 * @return 用户信息
	 */
	public SNUser get(String userid) {
		SNUser user = getSqlSession().selectOne("snuser.getTUserById", userid);
		return user;
	}
	/**
	 * getUsernameByEquipmentNo
	 * 通过设备号查询用户信息
	 * @param equipment_number 设备号
	 * @return 结果
	 */
	public String getUsernameByEquipmentNo(String equipment_number) {
		return getSqlSession().selectOne("snuser.getUsernameByEquipmentNo", equipment_number);
	}
	/**
	 * 
			* <p>updateAccount</p>
			* 更改用户手机号
			* @param snuser
			* @param sessionInfo sessionInfo
			* @return 
	 */
	public int updateAccount(SNUser snuser){
		return getSqlSession().update("snuser.updateAccount", snuser);
	}
	/**
	 * 
			* <p>unBund</p>
			* 解绑
			* @param id 
			* @param sessionInfo sessionInfo
			* @return 
	 */
	public  int unBund(Long id){
		return getSqlSession().delete("snuser.unBund", id);
	}
	/**
	 * countSnUserByTelphoneOrEmail
	 * 根据手机号或邮箱判断用户是否存在
	 * @return 统计结果
	 */
	public Long countSnUserByTelphoneOrEmail(Map<String, Object> params) {
		return getSqlSession().selectOne("snuser.countSnUserByTelphoneOrEmail", params);
	}
}