package com.ratta.suponote.system.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ratta.suponote.system.model.Column;
import com.ratta.suponote.system.model.Table;

/**
 * @author page
 * 数据字典表持久层
 * 2018-10-31
 */
@Repository("dataDictDao")
public class DataDictDao extends SqlSessionDaoSupport{
	/**
	 * 
			* <p>queryTable</p>
			* <p>查询表信息</p>
			* @param param 参数
			* p_begin 分页开始页(必填)
			* p_end 每页行数(必填)
			* name 表名(模糊查询)
			* type 类型(1表,2视图,3函数,4触发器)
			* description 描述(模糊查询)
			* @return 表信息
	 */
	public List<Table> queryTable(Map<String, Object> param){
		return getSqlSession().selectList("system_DataDict.queryTable", param);
	}
	/**
	 * 
			* <p>countTable</p>
			* <p>统计表信息</p>
			* @param param 参数
			* name 表名(模糊查询)
			* type 类型(1表,2视图,3函数,4触发器)
			* description 描述(模糊查询)
			* @return 记录数
	 */
	public Long countTable(Map<String, Object> param){
		return  getSqlSession().selectOne("system_DataDict.countTable", param);
	}
	/**
	 * 
			* <p>addTable</p>
			* <p>添加数据字典-表</p>
			* @param table 表
			* @return 数据库影响行数
	 */
	public int addTable(Table table){
		return getSqlSession().insert("system_DataDict.addTable", table);
	}
	/**
	 * 
			* <p>queryTable</p>
			* <p>根据id查询表名</p>
			* @param id 表名id
			* @return 表名实体
	 */
	public Table queryTable(Integer id){
		return getSqlSession().selectOne("system_DataDict.queryTableEntity",id);
	}
	/**
	 * 
			* <p>updateTable</p>
			* <p>更新表名</p>
			* @param table 表名信息
			* @return 数据库影响行数
	 */
	public int updateTable(Table table){
		return getSqlSession().update("system_DataDict.updateTable", table);
	}
	/**
	 * 
			* <p>deleteTable</p>
			* <p>删除表名信息</p>
			* @param id 表名id
			* @return 数据库影响行数
	 */
	public int deleteTable(Integer id){
		return getSqlSession().delete("system_DataDict.deleteTable", id);
	}
	/**
	 * 
			* <p>deleteColumnByTable</p>
			* <p>根据tableId 删除字段信息</p>
			* @param tableId 表id
			* @return 数据库影响行数
	 */
	public int deleteColumnByTable(Integer tableId){
		return getSqlSession().delete("system_DataDict.deleteColumnByTable", tableId);
	}
	/**
	 * 
			* <p>queryColumn</p>
			* <p>查询字段信息</p>
			* @param param 参数
			* p_begin 分页开始页(必填)
			* p_end 每页行数(必填)
			* name 表名(模糊查询)
			* type 类型(TODO 需要重新定义)
			* description 描述(模糊查询)
			* table_id 所属表id
			* @return 表信息
	 */
	public List<Column> queryColumn(Map<String, Object> param){
		return getSqlSession().selectList("system_DataDict.queryColumn", param);
	}
	/**
	 * 
			* <p>countColumn</p>
			* <p>统计字段信息</p>
			* @param param 参数
			* name 表名(模糊查询)
			* type 类型(TODO 需要重新定义)
			* description 描述(模糊查询)
			* table_id 所属表id
			* @return 记录数
	 */
	public Long countColumn(Map<String, Object> param){
		return getSqlSession().selectOne("system_DataDict.countColumn", param);
	}
	/**
	 * 
			* <p>addColumn</p>
			* <p>添加字段信息</p>
			* @param column 字段信息
			* @return 数据库影响行数
	 */
	public int addColumn(Column column){
		return getSqlSession().insert("system_DataDict.addColumn", column);
	}
	
	/**
	 * 
			* <p>queryColumn</p>
			* <p>根据id查询字段实体</p>
			* @param id 字段表id
			* @return 字段
	 */
	public Column queryColumn(Integer id){
		return getSqlSession().selectOne("system_DataDict.queryColumnEntity", id);
	}
	
	/**
	 * 
			* <p>updateColumn</p>
			* <p>更新字段</p>
			* @param column 字段实体
			* @return 数据库影响行数
	 */
	public int updateColumn(Column column){
		return getSqlSession().update("system_DataDict.updateColumn", column);
	}
	/**
	 * 
			* <p>deleteColumn</p>
			* <p>根据id删除字段信息</p>
			* @param id 字段id
			* @return 数据库影响行数
	 */
	public int deleteColumn(Integer id){
		return getSqlSession().delete("system_DataDict.deleteColumn", id);
	}
}

	