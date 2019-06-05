package com.ratta.suponote.dao.system.maint;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.model.system.maint.Audit;

/**
 * @author page
 * 操作审计管理持久层
 * 2018-10-31
 */
@Repository("auditDao")
public class AuditDao extends SqlSessionDaoSupport {
	/**
	 * 
			* <p>save</p>
			* <p>添加操作审计信息</p>
			* @param audit 操作审计信息
			* @return 数据库影响行数
	 */
	public int save(Audit audit){
		return getSqlSession().insert("audit.save", audit);
	}
	/**
	 * 
			* <p>query</p>
			* <p>查询操作审计信息</p>
			* @param params 查询参数
			* p_begin 分页开始页(必填)
			* p_end 分页结束页(必填)
			* op_type 操作类型（1:查询2:添加3:修改4:删除）
			* @return 操作审计信息列表
	 */
	public List<Audit> query(Map<String, Object> params){
		return getSqlSession().selectList("audit.query", params);
	}
	/**
	 * 
			* <p>count</p>
			* <p>统计操作审计信息</p>
			* @param params 查询参数
			* op_type 操作类型（1:查询2:添加3:修改4:删除）
			* @return 操作审计数
	 */
	public Long count(Map<String, Object> params){
		return getSqlSession().selectOne("audit.count", params);
	}
	/**
	 * 
			* <p>queryByOpUser</p>
			* <p>根据操作员id查询操作记录</p>
			* @param id 操作员id
			* @return 操作审计集合
	 */
	public List<Audit> queryByOpUser(Long id) {
		return getSqlSession().selectList("audit.queryByOpUser", id);
	}
	
	/**
	 * 
			* <p>deleteByDays</p>
			* <p>删除记录</p>
			* @param Long daysBefore 几天前的数据
			* @return 数据库影响行数
	 */
	public int deleteByDays(Long daysBefore){
		return getSqlSession().delete("audit.deleteByDays", daysBefore);
	}
}

	