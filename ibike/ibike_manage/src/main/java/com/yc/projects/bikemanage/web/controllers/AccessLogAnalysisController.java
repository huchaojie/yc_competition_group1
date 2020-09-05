package com.yc.projects.bikemanage.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.bikemanage.bean.AccessLog;
import com.yc.projects.bikemanage.bean.QueryObject;
import com.yc.projects.bikemanage.service.AccessLogAnalysisService;
import com.yc.projects.bikemanage.web.model.JsonModel;

@Controller
public class AccessLogAnalysisController {

	@Autowired
	private AccessLogAnalysisService accessLogAnalysisService;

	@RequestMapping("/back/findAllAccessLog")
	@ResponseBody
	public JsonModel findAllAccessLog(AccessLog accessLog, Integer pageNum, Integer pageSize, JsonModel jm) {
		try {
			if (pageNum == null) {
				pageNum = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
			Map<String, Object> map = accessLogAnalysisService.findAllAccessLog(pageNum, pageSize);
			jm.setCode(1);
			jm.setMsg(map.get("total") + "");
			jm.setObj(map.get("list"));
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/countAccessLog")
	@ResponseBody
	public JsonModel countAccessLog(AccessLog accessLog, JsonModel jm) {
		try {
			List<QueryObject> list = accessLogAnalysisService.findCount(accessLog);
			jm.setCode(1);
			jm.setObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/findPVUV")
	@ResponseBody
	public JsonModel findPVUV(JsonModel jm) {
		try {
			String[] findPVUV = accessLogAnalysisService.findPVUV();
			jm.setCode(1);
			jm.setObj(findPVUV);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return jm;
	}

}
