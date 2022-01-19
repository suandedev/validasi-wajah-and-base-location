package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetState {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<State> listDataState;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<State> getListDataState() {
		return listDataState;
	}

	public void setListDataState(List<State> listDataState) {
		this.listDataState = listDataState;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
