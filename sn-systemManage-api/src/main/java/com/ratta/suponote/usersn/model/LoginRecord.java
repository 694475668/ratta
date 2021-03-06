package com.ratta.suponote.usersn.model;

import java.util.Date;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;
/**
 * @author page
 * 云盘用户登录记录类
 * 2018-10-31
 */
@Model(table="t_login_record")
public class LoginRecord implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@PK
	private Long id;
	/**用户名*/  
	private String username;
	/**手机号*/  
	private String telephone;
	/**邮箱*/  
	private String email;
	/**登录方式 */
	private String login_method;
	/**ip*/
	private String ip;
	/**设备*/
	private String equipment;
	/**浏览器*/
	private String browser;
	/**登陆起始时间*/
    private Date modifydatetimeStart;
    /**登陆截止时间*/
    private Date modifydatetimeEnd;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogin_method() {
		return login_method;
	}
	public void setLogin_method(String login_method) {
		this.login_method = login_method;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public Date getModifydatetimeStart() {
		return modifydatetimeStart;
	}
	public void setModifydatetimeStart(Date modifydatetimeStart) {
		this.modifydatetimeStart = modifydatetimeStart;
	}
	public Date getModifydatetimeEnd() {
		return modifydatetimeEnd;
	}
	public void setModifydatetimeEnd(Date modifydatetimeEnd) {
		this.modifydatetimeEnd = modifydatetimeEnd;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "LoginRecord [id=" + id + ", username=" + username + ", telephone=" + telephone + ", email=" + email
				+ ", login_method=" + login_method + ", ip=" + ip + ", equipment=" + equipment + ", browser=" + browser
				+ ", modifydatetimeStart=" + modifydatetimeStart + ", modifydatetimeEnd=" + modifydatetimeEnd + "]";
	}
}
