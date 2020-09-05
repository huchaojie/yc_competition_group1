package com.yc.projects.bikemanage.bean;

import java.io.Serializable;
import java.util.Arrays;

public class Bike implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bid;
	private Integer status;
	private String qrcode;
	private Double latitude;
	private Double longitude;

	private String id;
	private String openid;
	private String[] types;
	private Double[] loc = new Double[2];

	public static final int UNACTIVE = 0;
	public static final int LOCK = 1;
	public static final int USING = 2;
	public static final int INTROUBLE = 3;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double[] getLoc() {
		return loc;
	}

	public void setLoc(Double[] loc) {
		this.loc = loc;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Bike [bid=" + bid + ", status=" + status + ", qrcode=" + qrcode + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", id=" + id + ", openid=" + openid + ", types=" + Arrays.toString(types)
				+ ", loc=" + Arrays.toString(loc) + "]";
	}

}
