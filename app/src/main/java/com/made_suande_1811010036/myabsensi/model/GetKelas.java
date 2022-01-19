package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKelas {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<Kelas> listDataKelas;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Kelas> getListDataKelas() {
		return listDataKelas;
	}

	public void setListDataKelas(List<Kelas> listDataKelas) {
		this.listDataKelas = listDataKelas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
