package com.ratta.suponote.feedback.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.ratta.suponote.feedback.model.FeedbackRecord;

/**
 * 反馈问题记录持久层
 * @author page
 * 2019-02-22
 */
@Repository("feedbackRecordDao")
public class FeedbackRecordDao extends SqlSessionDaoSupport{

	/**
	 * 
	 * <p>query</p>
	 * <p>查询反馈问题记录</p>
	 * @param params 查询参数
	 * p_begin 分页开始页(必填)
	 * p_end 分页结束页(必填)
	 * @return 反馈问题记录列表
	 */
	public List<FeedbackRecord> query(Map<String, Object> params){
		return getSqlSession().selectList("feedbackRecord.query", params);
	}
	
	/**
	 * 
	 * <p>count</p>
	 * <p>统计反馈问题记录信息</p>
	 * @param params 查询参数
	 * @return 反馈问题记录统计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("feedbackRecord.count", params);
	}
	
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询反馈问题记录信息</p>
	 * @param id id
	 * @return 反馈问题记录信息
	 */
	public FeedbackRecord query(int id){
		return getSqlSession().selectOne("feedbackRecord.queryById", id);
	}
	
}
