package com.yc.projects.bikemanage.service;

import java.util.List;
import java.util.Map;

import com.yc.projects.bikemanage.bean.AccessLog;
import com.yc.projects.bikemanage.bean.QueryObject;

public interface AccessLogAnalysisService {

	public Map<String, Object> findAllAccessLog(int pageNum, int pageSize);

	public List<QueryObject> findCount(AccessLog accessLog);

	public String[] findPVUV();

}
