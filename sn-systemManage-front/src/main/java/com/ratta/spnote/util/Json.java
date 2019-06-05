package com.ratta.spnote.util;

/**
 * @author page JSON模型 用户后台向前台返回的JSON对象 2018-10-31
 */
public class Json implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;

	private String object;
	private String errorinfo;
	private String verinfo;
	private String iconinfo;

	public String getVerinfo() {
		return verinfo;
	}

	public void setVerinfo(String verinfo) {
		this.verinfo = verinfo;
	}

	public String getIconinfo() {
		return iconinfo;
	}

	public void setIconinfo(String iconinfo) {
		this.iconinfo = iconinfo;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
