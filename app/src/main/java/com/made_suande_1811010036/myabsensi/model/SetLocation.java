package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class SetLocation {


	@SerializedName("id")
	private String id;
	@SerializedName("kelasId")
	private String kelasId;
	@SerializedName("dosen")
	private String dosen;
	@SerializedName("ruangan")
	private String ruangan;
	@SerializedName("jam")
	private String jam;
	@SerializedName("matkul")
	private String matkul;
	@SerializedName("kelas")
	private String kelas;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("longtitude")
	private String longtitude;

	public SetLocation() {

	}

	public SetLocation(String id, String kelasId, String dosen, String ruangan, String jam, String matkul, String kelas, String latitude, String longtitude) {
		this.id = id;
		this.kelasId = kelasId;
		this.dosen = dosen;
		this.ruangan = ruangan;
		this.jam = jam;
		this.matkul = matkul;
		this.kelas = kelas;
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

	public String getDosen() {
		return dosen;
	}

	public void setDosen(String dosen) {
		this.dosen = dosen;
	}

	public String getRuangan() {
		return ruangan;
	}

	public void setRuangan(String ruangan) {
		this.ruangan = ruangan;
	}

	public String getJam() {
		return jam;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

	public String getMatkul() {
		return matkul;
	}

	public void setMatkul(String matkul) {
		this.matkul = matkul;
	}

	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
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
