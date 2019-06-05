package com.ratta.suponote.feedback.model;

import java.util.Date;
import com.ratta.suponote.model.system.ExcelResources;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.NotCompare;

/**
 * @author page
 * 反馈问题记录类
 * 2019-02-21
 */
@Model(table="t_feedback_record")
public class FeedbackRecord implements java.io.Serializable {
	@NotCompare
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private int id;
	/**
	 * 问题类型ID
	 */
	@ExcelResources(order=1,title="类型编号")
	private String typeId;
	
	/**
	 * 一级问题类型
	 */
	@ExcelResources(order=2,title="一级问题类型")
	private String valueOne;
	/**
	 * 二级问题类型
	 */
	@ExcelResources(order=3,title="二级问题类型")
	private String valueTwo;
	/**
	 * 三级问题类型
	 */
	@ExcelResources(order=4,title="三级问题类型")
	private String valueThree;
	/**
	 * 问题描述
	 */
	@ExcelResources(order=5,title="问题描述")
	private String description;
	/**
	 * 设备号
	 */
	@ExcelResources(order=6,title="设备号")
	private String equipment_no;
	/**
	 * 联系方式
	 */
	@ExcelResources(order=7,title="联系方式")
	private String contact;
	/**
	 * 创建时间
	 */
	@ExcelResources(order=8,title="创建时间")
	private Date createTime;
	/**
	 * 用户ID
	 */
	private Long userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getValueOne() {
		return valueOne;
	}
	public void setValueOne(String valueOne) {
		this.valueOne = valueOne;
	}
	public String getValueTwo() {
		return valueTwo;
	}
	public void setValueTwo(String valueTwo) {
		this.valueTwo = valueTwo;
	}
	public String getValueThree() {
		return valueThree;
	}
	public void setValueThree(String valueThree) {
		this.valueThree = valueThree;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEquipment_no() {
		return equipment_no;
	}
	public void setEquipment_no(String equipment_no) {
		this.equipment_no = equipment_no;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "FeedbackRecord [id=" + id + ", typeId=" + typeId + ", valueOne=" + valueOne + ", valueTwo=" + valueTwo
				+ ", valueThree=" + valueThree + ", description=" + description + ", equipment_no=" + equipment_no
				+ ", contact=" + contact + ", createTime=" + createTime + ", userId=" + userId + "]";
	}
}
