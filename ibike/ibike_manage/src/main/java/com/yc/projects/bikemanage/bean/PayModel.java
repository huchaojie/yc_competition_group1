package com.yc.projects.bikemanage.bean;

import java.io.Serializable;

public class PayModel implements Serializable {
	private static final long serialVersionUID = -5780746827830895641L;
	private Double start_longitude;
	private Double start_latitude;
	private Double end_longitude;
	private Double end_latitude;
	private String uuid; // 关于用户会话的id 相当于 sessionid
	private String openId;
	private String phoneNum;
	private Long startTime;
	private Long endTime;
	private Long totalTime;

	private Integer payMoney;
	private String logTime;
	private String bid;

	
	public Double getStart_latitude() {
		return start_latitude;
	}

	public void setStart_latitude(Double start_latitude) {
		this.start_latitude = start_latitude;
	}

	public Double getEnd_longitude() {
		return end_longitude;
	}

	public void setEnd_longitude(Double end_longitude) {
		this.end_longitude = end_longitude;
	}

	public Double getEnd_latitude() {
		return end_latitude;
	}

	public void setEnd_latitude(Double end_latitude) {
		this.end_latitude = end_latitude;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public Integer getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}


	public Double getStart_longitude() {
		return start_longitude;
	}

	public void setStart_longitude(Double start_longitude) {
		this.start_longitude = start_longitude;
	}

	@Override
	public String toString() {
		return "PayModel [start_longitude=" + start_longitude + ", start_latitude=" + start_latitude
				+ ", end_longitude=" + end_longitude + ", end_latitude=" + end_latitude + ", uuid=" + uuid + ", openId="
				+ openId + ", phoneNum=" + phoneNum + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", totalTime=" + totalTime + ", payMoney=" + payMoney + ", logTime=" + logTime + ", bid=" + bid + "]";
	}		
	
}
