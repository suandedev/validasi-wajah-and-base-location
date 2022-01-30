package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class SetTime {

	@SerializedName("id")
	private String id;
	@SerializedName("kelasId")
	private String kelasId;
	@SerializedName("pertemuanId")
	private String pertemuanId;
	@SerializedName("stateId")
	private String stateId;
	@SerializedName("pertemuan")
	private String pertemuan;
	@SerializedName("matkul")
	private String matkul;
	@SerializedName("kelas")
	private String kelas;
	@SerializedName("state")
	private String state;
	@SerializedName("paramIn")
	private String paramIn;
	@SerializedName("paramOut")
	private String paramOut;

	public SetTime() {

	}

	public SetTime(String id, String kelasId, String pertemuanId, String stateId, String pertemuan, String matkul, String kelas, String state, String paramIn, String paramOut) {
		this.id = id;
		this.kelasId = kelasId;
		this.pertemuanId = pertemuanId;
		this.stateId = stateId;
		this.pertemuan = pertemuan;
		this.matkul = matkul;
		this.kelas = kelas;
		this.state = state;
		this.paramIn = paramIn;
		this.paramOut = paramOut;
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

	public String getPertemuan() {
		return pertemuan;
	}

	public void setPertemuan(String pertemuan) {
		this.pertemuan = pertemuan;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParamIn() {
		return paramIn;
	}

	public void setParamIn(String paramIn) {
		this.paramIn = paramIn;
	}

	public String getParamOut() {
		return paramOut;
	}

	public void setParamOut(String paramOut) {
		this.paramOut = paramOut;
	}
}
