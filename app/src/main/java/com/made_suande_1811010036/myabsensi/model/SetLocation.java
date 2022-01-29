package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class SetLocation {


	@SerializedName("id")
	private String id;
	@SerializedName("kelasId")
	private String kelasId;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("longtitude")
	private String longtitude;

	public SetLocation() {

	}

	public SetLocation(String id, String kelasId, String latitude, String longtitude) {
		this.id = id;
		this.kelasId = kelasId;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKelasId() {
		return kelasId;
	}

	public void setKelasId(String kelasId) {
		this.kelasId = kelasId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
}
