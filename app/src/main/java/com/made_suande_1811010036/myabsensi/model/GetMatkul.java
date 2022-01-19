package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMatkul {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<Matkul> listDataMatkul;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Matkul> getListDataMatkul() {
		return listDataMatkul;
	}

	public void setListDataAbsen(List<Matkul> listDataMatkul) {
		this.listDataMatkul = listDataMatkul;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
