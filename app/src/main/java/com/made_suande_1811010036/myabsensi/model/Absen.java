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
	@SerializedName("mahasiswa")
	private String mahasiswa;
	@SerializedName("npm")
	private String npm;
	@SerializedName("keterangan")
	private String keterangan;
	@SerializedName("laatitude")
	private String latitude;
	@SerializedName("longtitude")
	private String longtitude;
	@SerializedName("createdAt")
	private String createdAt;

	public Absen() {}

	public Absen(String id, String kelasId, String userId, String pertemuanId, String stateId, String mahasiswa, String npm, String keterangan, String latitude, String longtitude, String createdAt) {
		this.id = id;
		this.kelasId = kelasId;
		this.userId = userId;
		this.pertemuanId = pertemuanId;
		this.stateId = stateId;
		this.mahasiswa = mahasiswa;
		this.npm = npm;
		this.keterangan = keterangan;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.createdAt = createdAt;
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

	public String getMahasiswa() {
		return mahasiswa;
	}

	public void setMahasiswa(String mahasiswa) {
		this.mahasiswa = mahasiswa;
	}

	public String getNpm() {
		return npm;
	}

	public void setNpm(String npm) {
		this.npm = npm;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
