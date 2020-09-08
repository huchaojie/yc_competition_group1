package com.yc.projects.yc74ibike.dao;

import java.util.List;

import com.yc.projects.yc74ibike.bean.User;

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
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user);

}
