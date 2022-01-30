package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Dosen {

	@SerializedName("id")
	private String id;
	@SerializedName("userId")
	private String userId;
	@SerializedName("dosen")
	private String dosen;
	@SerializedName("prodi")
	private String prodi;

	public Dosen() {

	}

	public Dosen(String id, String userId, String dosen, String prodi) {
		this.id = id;
		this.userId = userId;
		this.dosen = dosen;
		this.prodi = prodi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDosen() {
		return dosen;
	}

	public void setDosen(String dosen) {
		this.dosen = dosen;
	}

	public String getProdi() {
		return prodi;
	}

	public void setProdi(String prodi) {
		this.prodi = prodi;
	}
}
