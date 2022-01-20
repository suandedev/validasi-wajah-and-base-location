package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMhs {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<Mhs> listDataMhs;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Mhs> getListDataMhs() {
		return listDataMhs;
	}

	public void setListDataMhs(List<Mhs> listDataMhs) {
		this.listDataMhs = listDataMhs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
