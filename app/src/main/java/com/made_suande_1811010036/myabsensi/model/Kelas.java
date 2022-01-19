package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Kelas {

	@SerializedName("id")
	private String id;
	@SerializedName("userId")
	private String userId;
	@SerializedName("kelas")
	private String kelas;
	@SerializedName("matkul")
	private String matkul;
	@SerializedName("jam")
	private String jam;
	@SerializedName("ruangan")
	private String ruangan;
	@SerializedName("dosen")
	private String dosen;

	public Kelas() {

	}

	public Kelas(String id,String userId, String kelas, String matkul, String jam, String ruangan, String dosen) {
		this.id = id;
		this.userId = userId;
		this.kelas = kelas;
		this.matkul = matkul;
		this.jam = jam;
		this.ruangan = ruangan;
		this.dosen = dosen;
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

	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}

	public String getMatkul() {
		return matkul;
	}

	public void setMatkul(String matkul) {
		this.matkul = matkul;
	}

	public String getJam() {
		return jam;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

	public String getRuangan() {
		return ruangan;
	}

	public void setRuangan(String ruangan) {
		this.ruangan = ruangan;
	}

	public String getDosen() {
		return dosen;
	}

	public void setDosen(String dosen) {
		this.dosen = dosen;
	}
}
