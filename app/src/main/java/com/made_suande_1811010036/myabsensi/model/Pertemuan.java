package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Pertemuan {

	@SerializedName("id")
	private String id;

	@SerializedName("pertemuan")
	private String pertemuan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPertemuan() {
		return pertemuan;
	}

	public void setPertemuan(String pertemuan) {
		this.pertemuan = pertemuan;
	}
}
