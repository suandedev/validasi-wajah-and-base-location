package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAbsen {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<Absen> listDataAbsen;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Absen> getListDataAbsen() {
		return listDataAbsen;
	}

	public void setListDataAbsen(List<Absen> listDataAbsen) {
		this.listDataAbsen = listDataAbsen;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
