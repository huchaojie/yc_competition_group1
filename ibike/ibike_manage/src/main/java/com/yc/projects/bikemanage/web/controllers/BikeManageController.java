package com.yc.projects.bikemanage.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.bikemanage.bean.Bike;
import com.yc.projects.bikemanage.service.BikeManageService;
import com.yc.projects.bikemanage.web.model.JsonModel;

@Controller
public class BikeManageController {
	@Autowired
	private BikeManageService bikeManageService;

	@RequestMapping("/back/addBike")
	@ResponseBody
	public JsonModel addBike(JsonModel jm, Bike bike) {
		try {
			bikeManageService.addBike(bike);
			jm.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/searchBike")
	@ResponseBody
	public JsonModel searchBike(JsonModel jm, Bike bike, Integer pageNum, Integer pageSize) {
		try {
			if (pageNum == null) {
				pageNum = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
			Map<String, Object> map = bikeManageService.searchBike(bike, pageNum, pageSize);
			jm.setCode(1);
			jm.setObj(map.get("list"));
			jm.setMsg(map.get("total") + "");
		} catch (Exception e) {
			jm.setCode(0);
			e.printStackTrace();
			jm.setMsg(e.getMessage());
		}

		return jm;

	}

	@RequestMapping("/back/updateBike")
	@ResponseBody
	public JsonModel updateBike(JsonModel jm, Bike bike) {
		try {
			boolean flag = false;
			flag = bikeManageService.updateBike(bike);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/deleteBike")
	@ResponseBody
	public JsonModel deleteBike(JsonModel jm, Bike bike) {
		try {
			boolean flag = false;
			flag = bikeManageService.deleteBike(bike);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}
}
