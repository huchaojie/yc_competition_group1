package com.yc.projects.bikemanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.yc.projects.bikemanage.bean.Admin;
import com.yc.projects.bikemanage.service.AdminManageService;

@Service
public class AdminManageServiceImpl implements AdminManageService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void addAdmin(Admin admin) {
		mongoTemplate.insert(admin, "admin");
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(admin.getId()));
		Update u = new Update();
		if (admin.getName() != null & !"".equals(admin.getName())) {
			u.set("name", admin.getName());
		}
		if (admin.getPassword() != null & !"".equals(admin.getPassword())) {
			u.set("password", admin.getPassword());
		}
		if (admin.getSex() != null && !"".equals(admin.getSex())) {
			u.set("sex", admin.getSex());
		}
		if (admin.getType() != null && !"".equals(admin.getType())) {
			u.set("type", admin.getType());
		}
		UpdateResult updateResult = mongoTemplate.updateFirst(q, u, Admin.class, "admin");
		if (updateResult.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> searchAdmin(Admin admin, int pageNum, int pageSize) {
		Query q = new Query();
		if (admin.getId() != null && !"".equals(admin.getId())) {
			q.addCriteria(Criteria.where("id").is(admin.getId()));
		}
		if (admin.getPassword() != null && !"".equals(admin.getPassword())) {
			q.addCriteria(Criteria.where("password").is(admin.getPassword()));
		}
		if (admin.getSex() != null && !"".equals(admin.getSex())) {
			q.addCriteria(Criteria.where("sex").is(admin.getSex()));
		}
		if (admin.getType() != null && !"".equals(admin.getType())) {
			q.addCriteria(Criteria.where("type").is(admin.getType()));
		}
		if (admin.getName() != null && !"".equals(admin.getName())) {
			Pattern p = Pattern.compile(".*" + admin.getName() + ".*");
			q.addCriteria(Criteria.where("name").regex(p));
		}
		q.skip((pageNum - 1) * pageSize).limit(20);
		long total = mongoTemplate.count(q, Admin.class, "admin");
		List<Admin> list = mongoTemplate.find(q, Admin.class, "admin");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		return map;

	}

	@Override
	public boolean deleteAdmin(Admin admin) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(admin.getId()));
		DeleteResult deleteResult = mongoTemplate.remove(q, Admin.class, "admin");
		if (deleteResult.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkLogin(Admin admin) {
		String name = admin.getName();
		String status = stringRedisTemplate.opsForValue().get(name);
		if (status == null || !status.equals("true")) {
			return false;
		}
		return true;
	}

	@Override
	public boolean login(Admin admin) {
		Query q = new Query();
		q.addCriteria(Criteria.where("name").is(admin.getName()));
		q.addCriteria(Criteria.where("password").is(admin.getPassword()));
		long count = mongoTemplate.count(q, Admin.class, "admin");
		if (count > 0) {
			stringRedisTemplate.opsForValue().set(admin.getName(), "true", 1, TimeUnit.DAYS);
			return true;
		}
		return false;
	}

	@Override
	public boolean logout(Admin admin) {
		String name = admin.getName();
		if (name != null && !"".equals(name)) {
			stringRedisTemplate.opsForValue().set(name, "", 1, TimeUnit.SECONDS);
			;
		}
		return true;
	}

}
