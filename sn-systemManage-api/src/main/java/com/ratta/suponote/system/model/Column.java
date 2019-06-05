package com.ratta.suponote.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author page
 * 表字段名
 * 2018-10-31
 */
public class Column implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7612035806180534521L;
	/**
	 *  主键
	 */
	private int id;
	/**
	 * 字段名
	 */
	private String name;
	/**
	 * 字段类型
	 */
	private String type;
	/**
	 * 字段长度
	 */
	private String length;
	/**
	 * 可为空(1可空,2不可空)
	 */
	private String is_null;
	/**
	 * 是否主键(1不是,2是)
	 */
	private String is_primary;
	/**
	 * 是否自增(1不是,2是)
	 */
	private String is_auto_increment;
	/**
	 * 字段描述
	 */
	private String description;
	/**
	 * 所属表(t_table.id)
	 */
	private String table_id;
	/**
	 * 修改人
	 */
	private String updatedby;
	/**
	 * 修改时间
	 */
	private Date updated;
	/**
	 * 默认值
	 */
	private String default_val;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getIs_null() {
		return is_null;
	}
	public void setIs_null(String is_null) {
		this.is_null = is_null;
	}
	public String getIs_primary() {
		return is_primary;
	}
	public void setIs_primary(String is_primary) {
		this.is_primary = is_primary;
	}
	public String getIs_auto_increment() {
		return is_auto_increment;
	}
	public void setIs_auto_increment(String is_auto_increment) {
		this.is_auto_increment = is_auto_increment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getDefault_val() {
		return default_val;
	}
	public void setDefault_val(String default_val) {
		this.default_val = default_val;
	}
	@Override
	public String toString() {
		return "Column [id=" + id + ", name=" + name + ", type=" + type
				+ ", length=" + length + ", is_null=" + is_null
				+ ", is_primary=" + is_primary + ", is_auto_increment="
				+ is_auto_increment + ", description=" + description
				+ ", table_id=" + table_id + ", updatedby=" + updatedby
				+ ", updated=" + updated + ", default_val=" + default_val + "]";
	}


}

	