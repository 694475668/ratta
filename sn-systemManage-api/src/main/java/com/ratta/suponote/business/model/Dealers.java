package com.ratta.suponote.business.model;

import java.io.Serializable;
import com.ratta.suponote.model.system.ExcelResources;
import com.ratta.suponotes.util.Model;
import com.ratta.suponotes.util.PK;

/**
 * @author page
 * 经销商类
 * 2018-10-31
 */
public class Dealers implements Serializable{

	/** 序列标志 */
	private static final long serialVersionUID = 1L;
	/** PK */
	@PK
	private Long id;
	
	/** 经销商名称 */
	@ExcelResources(order=1,title="经销商名称")
    private String dealersName;
	/** 经销商类别 */
	@ExcelResources(order=2,title="经销商类别")
    private String dealersType;
    
    /** 联系人 */
	@ExcelResources(order=3,title="联系人")
    private String contact;
    
    /** 手机号 */
	@ExcelResources(order=4,title="联系电话")
    private String phone;
    
    /** 仓库地址 */
	@ExcelResources(order=5,title="仓库地址")
    private String address;
    
    /** 申请时间 */
	@ExcelResources(order=6,title="申请时间")
    private String applicationTime;
    
    /** 业务员 */
	@ExcelResources(order=7,title="业务员")
    private String salesman;
    
    /** 备注 */
	@ExcelResources(order=8,title="备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealersName() {
        return dealersName;
    }

    public void setDealersName(String dealersName) {
        this.dealersName = dealersName == null ? null : dealersName.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

	public String getDealersType() {
		return dealersType;
	}

	public void setDealersType(String dealersType) {
		this.dealersType = dealersType;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Dealers [id=" + id + ", dealersName=" + dealersName + ", dealersType=" + dealersType + ", contact="
				+ contact + ", phone=" + phone + ", address=" + address + ", applicationTime=" + applicationTime
				+ ", salesman=" + salesman + ", remark=" + remark + "]";
	}
}
