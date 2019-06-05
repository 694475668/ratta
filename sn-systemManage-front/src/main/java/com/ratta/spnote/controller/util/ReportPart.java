package com.ratta.spnote.controller.util;

/**
 * @author page 报表部分 2018-10-31
 */
public class ReportPart implements java.io.Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private String farmework;

	private Integer lines;

	public ReportPart() {
		super();
	}

	public ReportPart(String farmework, Integer lines) {
		super();
		this.farmework = farmework;
		this.lines = lines;
	}

	public String getFarmework() {
		return farmework;
	}

	public void setFarmework(String farmework) {
		this.farmework = farmework;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

}
