package com.ratta.suponote.feedback.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.feedback.model.FeedbackProblemType;

/**
 * 反馈问题类型持久层
 * @author page
 * 2019-02-22
 */
@Repository("feedbackProblemTypeDao")
public class FeedbackProblemTypeDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询反馈问题类型</p>
	 * @param params 查询参数
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 反馈问题类型列表
	 */
	public List<FeedbackProblemType> query(Map<String, Object> params){
		return getSqlSession().selectList("feedbackProblemType.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计反馈问题类型信息</p>
	 * @param params 查询参数
	 * @return 反馈问题类型统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("feedbackProblemType.count", params);
	}
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加信息</p>
	 * @param  反馈问题类型信息
	 * @return 数据库影响行数
	 */
	public int save(FeedbackProblemType feedbackProblemType){
		return getSqlSession().insert("feedbackProblemType.insert", feedbackProblemType);
	}
	
	/**
	 * 
	 * <p>update</p>
	 * <p>修改信息</p>
	 * @param  反馈问题类型信息
	 * @return 数据库影响行数
	 */
	public int update(FeedbackProblemType feedbackProblemType){
		return getSqlSession().update("feedbackProblemType.update", feedbackProblemType);
	}
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除反馈问题类型信息</p>
	 * @param typeId id
	 * @return 数据库影响行数
	 */
	public int delete(String typeId){
		return getSqlSession().update("feedbackProblemType.delete", typeId);
	}
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询反馈问题类型信息</p>
	 * @param typeId
	 * @return 反馈问题类型信息
	 */
	public FeedbackProblemType query(String typeId){
		return getSqlSession().selectOne("feedbackProblemType.queryById", typeId);
	}
	
	/**
	 * 
	 * <p>queryListById</p>
	 * <p>根据id查询反馈问题类型信息</p>
	 * @param typeId
	 * @return 反馈问题类型信息
	 */
	public List<FeedbackProblemType> queryListById(String typeId){
		return getSqlSession().selectList("feedbackProblemType.queryListById", typeId);
	}
	
	/**
	 * 
	 * <p>queryValueOne</p>
	 * <p>查询第一级问题类型</p>
	 * @return 反馈问题类型列表
	 */
	public List<FeedbackProblemType> queryValueOne(){
		return getSqlSession().selectList("feedbackProblemType.queryValueOne");
	}
	/**
	 * 
	 * <p>queryValueTwo</p>
	 * <p>查询第级问题类型</p>
	 * @return 反馈问题类型列表
	 */
	public List<FeedbackProblemType> queryValueTwo(String typeId){
		return getSqlSession().selectList("feedbackProblemType.queryValueTwo",typeId);
	}
	/**
	 * 
	 * <p>queryValueThree</p>
	 * <p>查询第三级问题类型</p>
	 * @return 反馈问题类型列表
	 */
	public List<FeedbackProblemType> queryValueThree(String typeId){
		return getSqlSession().selectList("feedbackProblemType.queryValueThree",typeId);
	}
}
