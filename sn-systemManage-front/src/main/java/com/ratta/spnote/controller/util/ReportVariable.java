package com.ratta.spnote.controller.util;

/**
 * @author page 报表参数 entity 2018-10-31
 */
public class ReportVariable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String var_name;

	/**
	 * 属于哪一个子类
	 */
	private String type;

	public ReportVariable() {
		super();
	}

	public ReportVariable(String var_name) {
		super();
		this.var_name = var_name;
	}

	public String getVar_name() {
		return var_name;
	}

	public void setVar_name(String var_name) {
		this.var_name = var_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
