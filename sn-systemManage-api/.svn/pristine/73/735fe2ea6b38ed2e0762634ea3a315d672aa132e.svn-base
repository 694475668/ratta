package com.ratta.suponote.feedback.service;

import java.util.List;
import java.util.Map;
import com.ratta.suponote.feedback.model.FeedbackRecord;
import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;

/**
 * @author page
 * 反馈问题记录管理服务
 * 2019-02-22
 */
public interface FeedbackRecordService {
	
	/**
	 * 
	 * <p>query</p>
	 * <p>查询反馈问题记录信息</p>
	 * @param ph 分页帮助类
	 * @param equipment_no 设备号
	 * @param contact 联系方式
	 * @param valueOne 一级问题类型
	 * @param valueTwo 二级问题类型
	 * @param valueThree 三级问题类型
	 * @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph,String equipment_no,String contact,String valueOne,String valueTwo,String valueThree);
	/**
	 * 
	 * <p>queryNoPage</p>
	 * <p>不分页查询反馈问题记录信息</p>
	 * @param params 查询参数
	 * @return easyui datagrid数据模型
	 */
	List<FeedbackRecord> queryNoPage(Map<String, Object> params);
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询反馈问题记录信息</p>
	 * @param id id
	 * @return 反馈问题记录信息
	 */
	FeedbackRecord query(int id);

}
