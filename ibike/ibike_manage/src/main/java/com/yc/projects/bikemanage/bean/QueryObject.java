package com.yc.projects.bikemanage.bean;

import java.io.Serializable;

public class QueryObject implements Serializable {

	private static final long serialVersionUID = -2522290667294733455L;
	private String data;
	private Long count;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "QueryObject [data=" + data + ", count=" + count + "]";
	}

}
