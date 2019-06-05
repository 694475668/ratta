package com.ratta.suponote.param.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.param.model.ErrorCode;

/**
 * @author page
 * 错误码管理持久层
 * 2018-10-31
 */
@Repository("errorCodeDao")
public class ErrorCodeDao extends SqlSessionDaoSupport{

	
	/**
	 * 
			* <p>query</p>
			* <p>查询错误码</p>
			* @param params 查询参数
			* p_begin 分页开始页(必填)
			* p_end 分页结束页(必填)
			* code 错误码(模糊查询)
			* desc 错误码描述(模糊查询)
			* @return 错误码列表
	 */
	public List<ErrorCode> query(Map<String, Object> params){
		return getSqlSession().selectList("errorcode.query", params);
	}
	/**
	 * 
			* <p>count</p>
			* <p>统计错误码信息</p>
			* @param params 查询参数
			* code 错误码(模糊查询)
			* desc 错误码描述(模糊查询)
			* @return 错误码统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("errorcode.count", params);
	}
	/**
	 * 
			* <p>save</p>
			* <p>添加错误码信息</p>
			* @param errorCode 错误码信息
			* @return 数据库影响行数
	 */
	public int save(ErrorCode errorCode){
		return getSqlSession().insert("errorcode.save", errorCode);
	}
	/**
	 * 
			* <p>query</p>
			* <p>根据code查询错误码信息</p>
			* @param code 错误码id
			* @return 错误码信息
	 */
	public ErrorCode query(String code){
		return getSqlSession().selectOne("errorcode.queryById", code);
	}
	/**
	 * 
			* <p>update</p>
			* <p>更新错误码信息</p>
			* @param errorCode 错误码信息
			* @return 数据库影响行数
	 */
	public int update(ErrorCode errorCode){
		return getSqlSession().update("errorcode.update", errorCode);
	}
	/**
	 * 
			* <p>delete</p>
			* <p>删除错误码信息</p>
			* @param code 错误码信息
			* @return 数据库影响行数
	 */
	public  int delete(String code){
		return getSqlSession().delete("errorcode.delete", code);
	}
}

	