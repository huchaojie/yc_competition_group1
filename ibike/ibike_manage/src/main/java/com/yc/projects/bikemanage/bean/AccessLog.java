package com.yc.projects.bikemanage.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class AccessLog implements Serializable {

	private static final long serialVersionUID = 2327545233754057280L;
	private String remoteIp;
	private String loginRemoteName;
	private String authrizedName;
	private String responseCode;
	private Integer contentBytes;
	private Integer handleTime;
	private Long timestamps;
	private String requestmethod;
	private String requesturl;
	private String requestprotocol;
	private String refer;
	private String browsername;

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getLoginRemoteName() {
		return loginRemoteName;
	}

	public void setLoginRemoteName(String loginRemoteName) {
		this.loginRemoteName = loginRemoteName;
	}

	public String getAuthrizedName() {
		return authrizedName;
	}

	public void setAuthrizedName(String authrizedName) {
		this.authrizedName = authrizedName;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Integer getContentBytes() {
		return contentBytes;
	}

	public void setContentBytes(Integer contentBytes) {
		this.contentBytes = contentBytes;
	}

	public Integer getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Integer handleTime) {
		this.handleTime = handleTime;
	}

	public Long getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(Long timestamps) {
		this.timestamps = timestamps;
	}

	public String getRequestmethod() {
		return requestmethod;
	}

	public void setRequestmethod(String requestmethod) {
		this.requestmethod = requestmethod;
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}

	public String getRequestprotocol() {
		return requestprotocol;
	}

	public void setRequestprotocol(String requestprotocol) {
		this.requestprotocol = requestprotocol;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getBrowsername() {
		return browsername;
	}

	public void setBrowsername(String browsername) {
		this.browsername = browsername;
	}

	@Override
	public String toString() {
		return "AccessLog [remoteIp=" + remoteIp + ", loginRemoteName=" + loginRemoteName + ", authrizedName="
				+ authrizedName + ", responseCode=" + responseCode + ", contentBytes=" + contentBytes + ", handleTime="
				+ handleTime + ", timestamps=" + timestamps + ", requestmethod=" + requestmethod + ", requesturl="
				+ requesturl + ", requestprotocol=" + requestprotocol + ", refer=" + refer + ", browsername="
				+ browsername + "]";
	}

}
