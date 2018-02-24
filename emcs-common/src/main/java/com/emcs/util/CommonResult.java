package com.emcs.util;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class CommonResult implements Serializable {
	private String status;
	private String msg;
	private Object data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object date) {
		this.data = data;
	}
	public String toString(){
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

}
