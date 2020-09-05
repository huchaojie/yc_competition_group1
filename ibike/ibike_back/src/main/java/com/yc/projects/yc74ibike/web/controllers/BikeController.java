package com.yc.projects.yc74ibike.web.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.yc74ibike.bean.Bike;
import com.yc.projects.yc74ibike.service.BikeService;
import com.yc.projects.yc74ibike.web.model.JsonModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Api(value = "小辰出行单车信息操作接口", tags = { "单车信息/控制层" })
public class BikeController {
	private Logger logger = LogManager.getLogger();
	@Autowired
	private BikeService bikeService;

	/**
	 * 扫码开锁
	 * 
	 * @param jsonModel
	 * @param bike      必须的参数：经纬度
	 * @return
	 */

	@RequestMapping(value = "/open", method = { RequestMethod.POST })
	@ApiOperation(value = "用户端开锁", notes = "给指定的共享单车开锁，参数以json格式传过来接收")
	/*
	 * @ApiImplicitParams({ @ApiImplicitParam(name = "bid", value = "共享单车编号",
	 * required = true),
	 * 
	 * @ApiImplicitParam(name = "latitude", value = "纬度", required = true),
	 * 
	 * @ApiImplicitParam(name = "longitude", value = "经度", required = true), })
	 */
	public @ResponseBody JsonModel open(@ApiIgnore JsonModel jsonModel, @RequestBody Bike bike) {
		logger.info("请求参数：" + bike);
		try {
			bikeService.open(bike);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setMsg(e.getMessage());
			jsonModel.setCode(0);
		}

		return jsonModel;
	}

	@RequestMapping(value = "/findNearAll", method = { RequestMethod.POST })
	@ApiOperation(value = "查找最近的单车", notes = "查找最近的10部单车")
	public @ResponseBody JsonModel findNearAll(@ApiIgnore JsonModel jm, @RequestBody Bike bike) {
		//System.out.println(bike);
		List<Bike> list = bikeService.findNearAll(bike);
		jm.setCode(1);
		jm.setObj(list);
		return jm;
	}
	
	@PostMapping("/repair")
	public @ResponseBody JsonModel repair(   JsonModel jm, Bike bike) {
		try {
			this.bikeService.reportMantinant(  bike );
			jm.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(  e.getMessage() );
		}
		return jm;
	}

}
