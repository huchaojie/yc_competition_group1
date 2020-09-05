package com.yc.projects.yc74ibike.service;

import java.util.List;

import com.yc.projects.yc74ibike.bean.User;

public interface UserService {

	/**
	 * 实名认证
	 * 
	 * @param user
	 * @return
	 */
	public boolean identity(User user);

	/**
	 * 押金充值
	 * 
	 * @param user
	 * @return
	 */
	public boolean deposit(User user);

	/**
	 * 自动生成验证码并发送到指定手机
	 * 
	 * @param nationCode 国家编码
	 * @param phoneNum   手机号
	 * @throws Exception
	 */
	public void genVerifyCode(String nationCode, String phoneNum) throws Exception;

	/**
	 *
	 * @param phoneNum
	 * @param code
	 */
	public boolean verify(User user);

	/**
	 * 充值
	 * 
	 * @param user
	 * @return
	 */
	public boolean recharge(double balance, String phoneNum);

	/**
	 * 根据openid到 mongo中的 users集合中查是否有这个人.
	 * 
	 * @param openId
	 * @return
	 */
	public List<User> selectMember(String openId);

	/**
	 * 添加用户到mongo的users集合
	 * 
	 * @param u
	 */
	public void addMember(User u);

	/**
	 * 生成一个uuid,以它为键， sessionkey和openid为值，存到 redis中，且设置超时时间为 30天。
	 * 
	 * @param openId
	 * @param session_key
	 * @return
	 */
	public String redisSessionKey(String openId, String session_key);
}
