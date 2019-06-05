package com.ratta.suponote.snuser.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.usersn.model.LoginRecord;

/**
 * @author page
 * 登录记录管理持久层
 * 2018-10-31
 */
@Repository("loginRecordDao")
public class LoginRecordDao extends SqlSessionDaoSupport {
	/**
	 * find
	 * 根据参数查询云盘用户登录记录
	 * @return 
	 */
	public List<LoginRecord> find(Map<String, Object> params) {
		return getSqlSession().selectList("loginRecord.getAllLoginRecord",params);
	}

	/**
	 * count
	 * 根据参数查询云盘用户登录记录数
	 * @return 用户数
	 */
	public Long count(Map<String, Object> params) {
		return getSqlSession().selectOne("loginRecord.countAllLoginRecord", params);
	}
	/**
	 * 
			* <p>deleteByDays</p>
			* <p>清理30天之前的token数据</p>
			* @param Long daysBefore 几天前的数据
			* @return 数据库影响行数
	 */
	public int deleteByDays(Long daysBefore){
		return getSqlSession().delete("loginRecord.deleteByDays", daysBefore);
	}

}