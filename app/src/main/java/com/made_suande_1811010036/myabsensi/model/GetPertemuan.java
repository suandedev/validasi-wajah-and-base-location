package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPertemuan {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<Pertemuan> listDataPertemuan;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Pertemuan> getListDataPertemuan() {
		return listDataPertemuan;
	}

	public void setListDataPertemuan(List<Pertemuan> listDataPertemuan) {
		this.listDataPertemuan = listDataPertemuan;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
