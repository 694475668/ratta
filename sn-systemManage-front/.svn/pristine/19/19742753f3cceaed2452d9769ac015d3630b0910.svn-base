package com.ratta.spnote.controller.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author page 和报表界面展示有关的 entity 2018-10-31
 */
public class ReportBuilder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pageMaxLines;
	private List<String> htmls;
	private String htmlContinued;
	private String repontName;

	private Map<String, ReportVariable> vars; // 所有参数
	private ReportPart startPage; // 页面开始部分
	private ReportPart title; // 页面标题
	private ReportPart pageHead; // 页头(列头)
	private ReportPart body; // 内容
	private ReportPart pageSummary; // 页汇总(列汇总)
	private ReportPart tail; // 页尾
	private ReportPart endPage; // 页面结束部分

	public ReportBuilder() {
		super();
		vars = new HashMap<String, ReportVariable>();
	}

	public ReportBuilder(String repontName) {
		super();
		this.repontName = repontName;
		vars = new HashMap<String, ReportVariable>();
	}

	public Integer getPageMaxLines() {
		return pageMaxLines;
	}

	public void setPageMaxLines(Integer pageMaxLines) {
		this.pageMaxLines = pageMaxLines;
	}

	public List<String> getHtmls() {
		return htmls;
	}

	public void setHtmls(List<String> htmls) {
		this.htmls = htmls;
	}

	public String getHtmlContinued() {
		return htmlContinued;
	}

	public void setHtmlContinued(String htmlContinued) {
		this.htmlContinued = htmlContinued;
	}

	public String getRepontName() {
		return repontName;
	}

	public void setRepontName(String repontName) {
		this.repontName = repontName;
	}

	public ReportVariable getVars(String key) {
		return vars.get(key);
	}

	public void addVars(ReportVariable reportVars) {
		this.vars.put(reportVars.getVar_name(), reportVars);
	}

	public void addVars(String key, ReportVariable reportVars) {
		this.vars.put(key, reportVars);
	}

	public ReportPart getStartPage() {
		return startPage;
	}

	public void setStartPage(ReportPart startPage) {
		this.startPage = startPage;
	}

	public ReportPart getTitle() {
		return title;
	}

	public void setTitle(ReportPart title) {
		this.title = title;
	}

	public ReportPart getPageHead() {
		return pageHead;
	}

	public void setPageHead(ReportPart pageHead) {
		this.pageHead = pageHead;
	}

	public ReportPart getBody() {
		return body;
	}

	public void setBody(ReportPart body) {
		this.body = body;
	}

	public ReportPart getPageSummary() {
		return pageSummary;
	}

	public void setPageSummary(ReportPart pageSummary) {
		this.pageSummary = pageSummary;
	}

	public ReportPart getTail() {
		return tail;
	}

	public void setTail(ReportPart tail) {
		this.tail = tail;
	}

	public ReportPart getEndPage() {
		return endPage;
	}

	public void setEndPage(ReportPart endPage) {
		this.endPage = endPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setVars(Map<String, ReportVariable> vars) {
		this.vars = vars;
	}

}
