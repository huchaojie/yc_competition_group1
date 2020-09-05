package com.yc.projects.yc74ibike.service;

import com.yc.projects.yc74ibike.bean.PayModel;

public interface LogService {

	/**
	 * 保存操作日志
	 * 
	 * @param log
	 */
	public void save(String log);

	/**
	 * @param log
	 */
	public void savePayLog(String log);

	
	public void saveRepairLog(String log);
	
	public void saveRideLog(PayModel payModel);
}
