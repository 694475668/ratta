package com.ratta.suponote.feedback.model;

import java.util.Date;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.NotCompare;

/**
 * @author page
 * 反馈问题类型类
 * 2019-02-21
 */
@Model(table="t_feedback_type")
public class FeedbackProblemType implements java.io.Serializable {
	@NotCompare
	private static final long serialVersionUID = 1L;
	/**
	 * 问题类型ID
	 */
	private String typeId;
	/**
	 * 问题日文描述
	 */
	private String valueJa;
	/**
	 * 问题中文描述
	 */
	private String valueCn;
	/**
	 * 问题英文描述
	 */
	private String valueEn;
	/**
	 * 操作者
	 */
	private String opUser;
	/**
	 * 操作时间
	 */
	private Date opTime;
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getValueEn() {
		return valueEn;
	}
	public void setValueEn(String valueEn) {
		this.valueEn = valueEn;
	}
	public String getValueCn() {
		return valueCn;
	}
	public void setValueCn(String valueCn) {
		this.valueCn = valueCn;
	}
	public String getOpUser() {
		return opUser;
	}
	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getValueJa() {
		return valueJa;
	}
	public void setValueJa(String valueJa) {
		this.valueJa = valueJa;
	}
	@Override
	public String toString() {
		return "FeedbackProblemType [typeId=" + typeId + ", valueJa=" + valueJa + ", valueEn=" + valueEn + ", valueCn="
				+ valueCn + ", opUser=" + opUser + ", opTime=" + opTime + "]";
	}

}
