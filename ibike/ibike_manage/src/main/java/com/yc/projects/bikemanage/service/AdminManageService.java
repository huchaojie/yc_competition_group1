package com.yc.projects.bikemanage.service;

import java.util.Map;

import com.yc.projects.bikemanage.bean.Admin;

public interface AdminManageService {
	public void addAdmin(Admin admin);

	public boolean updateAdmin(Admin admin);

	public boolean deleteAdmin(Admin admin);

	Map<String, Object> searchAdmin(Admin admin, int pageNum, int pageSize);

	public boolean checkLogin(Admin admin);
	
	public boolean login(Admin admin);
	
	public boolean logout(Admin admin);
}
