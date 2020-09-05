package com.yc.projects.bikemanage.service;

import java.util.Map;

import com.yc.projects.bikemanage.bean.Bike;


public interface BikeManageService {
	public void addBike(Bike bike);

	public Map<String,Object> searchBike(Bike bike, int pageNum, int pageSize);

	public boolean updateBike(Bike bike);
	
	public boolean deleteBike(Bike bike);
}
