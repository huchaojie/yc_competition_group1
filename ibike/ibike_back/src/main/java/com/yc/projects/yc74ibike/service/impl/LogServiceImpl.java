package com.yc.projects.yc74ibike.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.yc.projects.yc74ibike.bean.PayModel;
import com.yc.projects.yc74ibike.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(String log) {
		mongoTemplate.save(log, "logs");

	}

	@Override
	public void savePayLog(String log) {
		System.out.println(log);
		mongoTemplate.save(log, "payLogs");

	}

	@Override
	public void saveRepairLog(String log) {
		System.out.println(log);
		mongoTemplate.save(log, "repairLogs");
	}

	@Override
	public void saveRideLog(PayModel payModel) {
		mongoTemplate.insert(payModel, "rideLogs");		
	}

}
