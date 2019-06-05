package com.ratta.suponote.feedback.service;

import java.util.List;
import java.util.Map;

import com.ratta.suponote.feedback.model.FeedbackProblemType;
import com.ratta.suponote.feedback.result.FeedbackProblemTypeRseult;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.pagemodel.SessionInfo;

/**
 * @author page
 * 反馈问题类型管理服务
 * 2019-02-22
 */
public interface FeedbackProblemTypeService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询反馈问题类型信息</p>
	 * @param ph 分页帮助类
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,Map<String, Object> params);
	/**
	 * 
	 * <p>save</p>
	 * <p>添加反馈问题类型信息</p>
	 * @param feedbackProblemType 反馈问题类型信息
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	FeedbackProblemTypeRseult save(FeedbackProblemType feedbackProblemType,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>update</p>
	 * <p>修改反馈问题类型信息</p>
	 * @param feedbackProblemType 反馈问题类型信息
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	FeedbackProblemTypeRseult update(FeedbackProblemType feedbackProblemType,SessionInfo sessionInfo);
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除反馈问题类型信息</p>
	 * @param typeId 反馈问题类型ID
	 * @param sessionInfo 操作员信息模型
	 * @return 返回枚举
	 */
	FeedbackProblemTypeRseult delete(String typeId, SessionInfo sessionInfo);

	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询反馈问题类型信息</p>
	 * @param typeId typeId
	 * @return 反馈问题类型信息
	 */
	FeedbackProblemType query(String typeId);
	/**
	 * 
	 * <p>queryValueOne</p>
	 * <p>查询第一级问题类型</p>
	 * @return 反馈问题类型列表
	 */
	List<FeedbackProblemType> queryValueOne();
	/**
	 * 
	 * <p>queryValueTwo</p>
	 * <p>查询第二级问题类型</p>
	 * @param typeId typeId
	 * @return 反馈问题类型列表
	 */
	List<FeedbackProblemType> queryValueTwo(String typeId);
	/**
	 * 
	 * <p>queryValueThree</p>
	 * <p>查询第三级问题类型</p>
	 * @param typeId typeId
	 * @return 反馈问题类型列表
	 */
	List<FeedbackProblemType> queryValueThree(String typeId);
}
