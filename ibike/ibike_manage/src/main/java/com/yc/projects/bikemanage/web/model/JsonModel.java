package com.yc.projects.bikemanage.web.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(value = Include.NON_NULL)
@ApiModel(value = "结果信息实体", description = "所有的REST调用得到的json结果")
public class JsonModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "操作响应码响应码，1为成功操作，其他为失败", required = true)
	private Integer code;
	@ApiModelProperty(value = "操作的响应信息，如果code为0则为异常信息")
	private String msg;
	@ApiModelProperty(value = "操作的结果，如果code为1则为结果值")
	private Object obj;
	@ApiModelProperty(value = "本操作执行完后，下一步重定向的地址")
	private String url;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", msg=" + msg + ", obj=" + obj + ", url=" + url + "]";
	}

}
