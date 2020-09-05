package com.yc.projects.bikemanage.service;

import java.util.Map;

import com.yc.projects.bikemanage.bean.User;



public interface UserManageService {

	public void addUser(User user);

	public Map<String,Object> searchUser(User user, int pageNum, int pageSize);

	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);

}
