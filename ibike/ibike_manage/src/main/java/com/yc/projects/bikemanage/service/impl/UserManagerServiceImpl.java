package com.yc.projects.bikemanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.yc.projects.bikemanage.bean.User;
import com.yc.projects.bikemanage.service.UserManageService;

@Service
public class UserManagerServiceImpl implements UserManageService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addUser(User user) {
		mongoTemplate.insert(user, "user");
	}

	@Override
	public Map<String, Object> searchUser(User user, int pageNum, int pageSize) {
		Query q = new Query();
		if (user.getOpenId() != null && !"".equals(user.getOpenId())) {
			q.addCriteria(Criteria.where("openId").is(user.getOpenId()));
		}
		if (user.getPhoneNum() != null && !"".equals(user.getPhoneNum())) {
			q.addCriteria(Criteria.where("phoneNum").is(user.getPhoneNum()));
		}
		if (user.getStatus() != null) {
			q.addCriteria(Criteria.where("status").is(user.getStatus()));
		}
		if (user.getName() != null && !"".equals(user.getName())) {
			Pattern p = Pattern.compile(".*" + user.getName() + ".*");
			q.addCriteria(Criteria.where("name").regex(p));
		}
		if (user.getIdNum() != null && !"".equals(user.getIdNum())) {
			Pattern p = Pattern.compile(".*" + user.getIdNum() + ".*");
			q.addCriteria(Criteria.where("idNum").regex(p));
		}
		q.skip((pageNum - 1) * pageSize).limit(20);
		long total = mongoTemplate.count(q, User.class, "user");
		List<User> list = mongoTemplate.find(q, User.class, "user");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	@Override
	public boolean updateUser(User user) {
		Query q = new Query();
		q.addCriteria(Criteria.where("openId").is(user.getOpenId()));
		Update u = new Update();
		if (user.getPhoneNum() != null && user.getPhoneNum() != "") {
			u.set("phoneNum", user.getPhoneNum());
		}
		if (user.getStatus() != null) {
			u.set("status", user.getStatus());
		}
		if (user.getName() != null && user.getName() != "") {
			u.set("name", user.getName());
		}
		if (user.getIdNum() != null && user.getIdNum() != "") {
			u.set("idNum", user.getIdNum());
		}
		if (user.getDeposit() != null) {
			u.set("deposit", user.getDeposit());
		}
		if (user.getBalance() != null) {
			u.set("balance", user.getBalance());
		}
		UpdateResult updateFirst = mongoTemplate.updateFirst(q, u, User.class, "user");
		if (updateFirst.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		Query q = new Query();
		q.addCriteria(Criteria.where("openId").is(user.getOpenId()));
		DeleteResult deleteResult = mongoTemplate.remove(q, User.class, "user");
		if (deleteResult.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}

}
