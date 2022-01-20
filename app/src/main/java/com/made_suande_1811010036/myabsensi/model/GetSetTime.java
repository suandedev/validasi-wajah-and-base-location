package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSetTime {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<SetTime> listDataSetTime;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SetTime> getListDataSetTime() {
		return listDataSetTime;
	}

	public void setListDataSetTime(List<SetTime> listDataSetTime) {
		this.listDataSetTime = listDataSetTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
