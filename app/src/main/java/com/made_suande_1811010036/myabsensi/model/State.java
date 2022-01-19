package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class State {

	@SerializedName("id")
	private String id;
	@SerializedName("state")
	private String state;

	public State() {

	}

	public State(String id, String state) {
		this.id = id;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
