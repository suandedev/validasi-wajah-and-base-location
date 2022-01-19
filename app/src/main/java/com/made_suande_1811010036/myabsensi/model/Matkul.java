package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Matkul {

	@SerializedName("id")
	private String id;
	@SerializedName("dosenId")
	private String dosenId;
	@SerializedName("mhsId")
	private String mhsId;
	@SerializedName("matkul")
	private String matkul;
	@SerializedName("hari")
	private String hari;
	@SerializedName("jam1")
	private String jam1;
	@SerializedName("jam2")
	private String jam2;
	@SerializedName("kelas")
	private String kelas;
	@SerializedName("sks")
	private String sks;
	@SerializedName("ruang1")
	private String ruang1;
	@SerializedName("ruang2")
	private String ruang2;
	@SerializedName("dosen")
	private String dosen;
	@SerializedName("name")
	private String name;
	@SerializedName("npm")
	private String npm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDosenId() {
		return dosenId;
	}

	public void setDosenId(String dosenId) {
		this.dosenId = dosenId;
	}

	public String getMhsId() {
		return mhsId;
	}

	public void setMhsId(String mhsId) {
		this.mhsId = mhsId;
	}

	public String getMatkul() {
		return matkul;
	}

	public void setMatkul(String matkul) {
		this.matkul = matkul;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}

	public String getJam1() {
		return jam1;
	}

	public void setJam1(String jam1) {
		this.jam1 = jam1;
	}

	public String getJam2() {
		return jam2;
	}

	public void setJam2(String jam2) {
		this.jam2 = jam2;
	}

	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}

	public String getSks() {
		return sks;
	}

	public void setSks(String sks) {
		this.sks = sks;
	}

	public String getRuang1() {
		return ruang1;
	}

	public void setRuang1(String ruang1) {
		this.ruang1 = ruang1;
	}

	public String getRuang2() {
		return ruang2;
	}

	public void setRuang2(String ruang2) {
		this.ruang2 = ruang2;
	}

	public String getDosen() {
		return dosen;
	}

	public void setDosen(String dosen) {
		this.dosen = dosen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNpm() {
		return npm;
	}

	public void setNpm(String npm) {
		this.npm = npm;
	}
}
