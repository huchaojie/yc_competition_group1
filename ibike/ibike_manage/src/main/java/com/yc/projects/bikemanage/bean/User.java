package com.yc.projects.bikemanage.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User implements Serializable {

	private static final long serialVersionUID = -4588550926501395260L;

	private Integer status;
	@Indexed(unique = true)
	private String phoneNum;
	private String name;
	private String idNum;
	private Double deposit;
	private Double balance;

	@Transient
	private String verifyCode;

	private String openId;
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [status=" + status + ", phoneNum=" + phoneNum + ", name=" + name + ", idNum=" + idNum
				+ ", deposit=" + deposit + ", balance=" + balance + ", verifyCode=" + verifyCode + ", openId=" + openId
				+ ", uuid=" + uuid + "]";
	}
}
