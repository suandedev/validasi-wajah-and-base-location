package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSetLocation {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<SetLocation> listDataSetLocation;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SetLocation> getListDataSetLocation() {
		return listDataSetLocation;
	}

	public void setListDataSetLocation(List<SetLocation> listDataSetLocation) {
		this.listDataSetLocation = listDataSetLocation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
