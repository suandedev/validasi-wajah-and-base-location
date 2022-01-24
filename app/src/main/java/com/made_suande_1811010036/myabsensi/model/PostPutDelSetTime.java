package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelSetTime {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	Absen mAbsen;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Absen getmAbsen() {
		return mAbsen;
	}

	public void setmAbsen(Absen mAbsen) {
		this.mAbsen = mAbsen;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
