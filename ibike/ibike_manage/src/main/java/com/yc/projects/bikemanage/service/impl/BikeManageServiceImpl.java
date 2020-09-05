package com.yc.projects.bikemanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.yc.projects.bikemanage.bean.Bike;
import com.yc.projects.bikemanage.service.BikeManageService;

@Service
public class BikeManageServiceImpl implements BikeManageService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addBike(Bike bike) {
		Double[] loc = { bike.getLatitude(), bike.getLongitude() };
		bike.setLoc(loc);
		mongoTemplate.insert(bike, "bike");
	}

	@Override
	public Map<String,Object> searchBike(Bike bike, int pageNum, int pageSize) {
		Query q = new Query();
		if (bike.getBid() != null && !"".equals(bike.getBid())) {
			q.addCriteria(Criteria.where("id").is(bike.getBid()));
		}
		if (bike.getStatus() != null && bike.getStatus() >= 0 && bike.getStatus() <= 3) {
			q.addCriteria(Criteria.where("status").is(bike.getStatus()));
		}
		q.skip((pageNum - 1) * pageSize).limit(pageSize);
		List<Bike> list = mongoTemplate.find(q, Bike.class,"bike");
		long total = mongoTemplate.count(q, Bike.class, "bike");
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	@Override
	public boolean updateBike(Bike bike) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(bike.getBid()));
		Update u = new Update();
		if (bike.getStatus() >= 0 && bike.getStatus() <= 3) {
			u.set("status", bike.getStatus());
		}
		if (bike.getLatitude() != null && bike.getLongitude() != null) {
			System.out.println(bike.getLatitude());
			u.set("latitude", bike.getLatitude());
			u.set("longitude", bike.getLongitude());
			Double[] loc = { bike.getLatitude(), bike.getLongitude() };
			u.set("loc", loc);
		}
		UpdateResult updateResult = mongoTemplate.updateFirst(q, u, Bike.class, "bike");
		if (updateResult.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBike(Bike bike) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(bike.getBid()));
		DeleteResult deleteResult = mongoTemplate.remove(q, Bike.class, "bike");
		if (deleteResult.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}
}
