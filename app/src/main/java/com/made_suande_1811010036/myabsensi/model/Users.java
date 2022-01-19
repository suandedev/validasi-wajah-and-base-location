package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class Users {

	@SerializedName("id")
	private String id;
	@SerializedName("email")
	private String email;
	@SerializedName("password")
	private String password;
	@SerializedName("rule")
	private String rule;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}
