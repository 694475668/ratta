package com.ratta.suponote.service.system.maint;

import java.util.Map;

import com.ratta.suponote.model.pagemodel.DataGrid;
import com.ratta.suponote.model.pagemodel.PageHelper;
import com.ratta.suponote.model.system.maint.Audit;

/**
 * @author page
 * 操作审计服务
 * 2018-10-31
 */
public interface AuditService {
	/**
	 * 
			* <p>query</p>
			* <p>查询操作审计信息</p>
			* @param ph 分页帮助类
			* @param params 操作参数
			* @return easyui datagrid数据模型
	 */
	DataGrid query(PageHelper ph ,Map<String, Object> params);
	/**
	 * 
			* <p>save</p>
			* <p>添加操作审计数据</p>
			* @param audit 操作审计数据
			* @return 1 添加成功 0 添加失败
	 */
	int save(Audit audit);
}

	