package com.ratta.suponote.dao.system;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.model.system.PwdHis;

/**
 * @author page
 * 密码历史操作持久层
 * 2018-10-31
 */
@Repository("pwdHisDao")
public class PwdHisDao extends SqlSessionDaoSupport{

	/**
	 * 
			* <p>save</p>
			* <p>添加密码历史操作</p>
			* @param pwdHis 密码历史操作
			* @return 数据库影响行数
	 */
	public  int save(PwdHis pwdHis){
		return getSqlSession().insert("pwdHis.save", pwdHis);
	}
	/**
	 * 
			* <p>query</p>
			* <p>查询最近N次</p>
			* @param params 查询参数
			* times 查询最近的改密次数
			* username 用户名
			* @return 最近N此的改密信息
	 */
	public List<PwdHis> query(Map<String, Object> params){
		return getSqlSession().selectList("pwdHis.query", params);
	}
}

	