package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Mhs {

	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("npm")
	private String npm;
	@SerializedName("jurusan")
	private String jurusan;

	public Mhs() {

	}

	public Mhs(String id, String name, String npm, String jurusan) {
		this.id = id;
		this.name = name;
		this.npm = npm;
		this.jurusan = jurusan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getJurusan() {
		return jurusan;
	}

	public void setJurusan(String jurusan) {
		this.jurusan = jurusan;
	}
}
