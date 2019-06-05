package com.ratta.suponote.usersn.model;

import java.util.Date;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 云盘用户设备绑定关系类
 * 2018-10-31
 */
@Model(table="t_user_equipment")
public class UserEquipment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@PK
	private Long id;
	/**设备号*/
	private String equipment_number;
	/**设备名*/
	private String name;
	/**锁定状态*/
	private String status;
	/**在线状态*/
	private String online_status;
	/**手机号*/
	private String telephone;
	/**邮箱*/
	private String email;
	/**绑定时间*/
	private Date create_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEquipment_number() {
		return equipment_number;
	}
	public void setEquipment_number(String equipment_number) {
		this.equipment_number = equipment_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOnline_status() {
		return online_status;
	}
	public void setOnline_status(String online_status) {
		this.online_status = online_status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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
		return "UserEquipment [id=" + id + ", equipment_number=" + equipment_number + ", name=" + name + ", status="
				+ status + ", online_status=" + online_status + ", telephone=" + telephone + ", email=" + email
				+ ", create_time=" + create_time  + "]";
	}


}
