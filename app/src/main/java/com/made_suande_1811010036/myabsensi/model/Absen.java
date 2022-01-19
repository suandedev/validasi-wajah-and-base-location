package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Absen {

	@SerializedName("id")
	private String id;
	@SerializedName("kelasId")
	private String kelasId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("pertemuanId")
	private String pertemuanId;
	@SerializedName("stateId")
	private String stateId;
	@SerializedName("keterangan")
	private String keterangan;
	@SerializedName("laatitude")
	private String latitude;
	@SerializedName("longtitude")
	private String longtitude;

	public Absen() {}

	public Absen(String id, String kelasId, String userId, String pertemuanId, String stateId, String keterangan, String latitude, String longtitude) {
		this.id = id;
		this.kelasId = kelasId;
		this.userId = userId;
		this.pertemuanId = pertemuanId;
		this.stateId = stateId;
		this.keterangan = keterangan;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPertemuanId() {
		return pertemuanId;
	}

	public void setPertemuanId(String pertemuanId) {
		this.pertemuanId = pertemuanId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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
