package com.yc.projects.bikemanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yc.projects.bikemanage.bean.AccessLog;
import com.yc.projects.bikemanage.bean.QueryObject;
import com.yc.projects.bikemanage.dao.AccessLogAnalysisDao;
import com.yc.projects.bikemanage.service.AccessLogAnalysisService;

@Service
public class AccessLogAnalysisServiceImpl implements AccessLogAnalysisService {

	@Autowired
	private AccessLogAnalysisDao accessLogAnalysisDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public Map<String, Object> findAllAccessLog(int pageNum, int pageSize) {
		List<AccessLog> list = accessLogAnalysisDao.findAccessLog(pageNum, pageSize);
		System.out.println(list);
		List<QueryObject> count = accessLogAnalysisDao.findCount(null);
		long total = count.get(0).getCount();
		System.out.println(total);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	@Override
	public List<QueryObject> findCount(AccessLog accessLog) {
		return accessLogAnalysisDao.findCount(accessLog);
	}

	@Override
	public String[] findPVUV() {
		String pv = stringRedisTemplate.opsForValue().get("accesslog_analysis_total_pv");
		String uv = stringRedisTemplate.opsForValue().get("accesslog_analysis_total_uv");
		String[] s = { pv, uv };
		return s;
	}

}
