package com.yc.projects.bikemanage.bean;

import java.io.Serializable;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1790893862881186081L;
	private String id;
	private String sex;
	private String type;
	private String name;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", sex=" + sex + ", type=" + type + ", name=" + name + ", password=" + password
				+ "]";
	}
}
