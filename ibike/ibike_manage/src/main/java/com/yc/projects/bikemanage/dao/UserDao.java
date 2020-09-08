package com.yc.projects.bikemanage.dao;

import java.util.List;

import com.yc.projects.bikemanage.bean.User;

public interface UserDao {

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);

	/**
	 * 查询用户
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user, int pageNum, int pageSize);

	public long findTotal(User user, int pageNum, int pageSize);
}
