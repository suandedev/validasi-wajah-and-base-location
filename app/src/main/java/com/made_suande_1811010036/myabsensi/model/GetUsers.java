package com.made_suande_1811010036.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUsers {

	@SerializedName("status")
	String status;
	@SerializedName("result")
	List<Users> listDataUsers;
	@SerializedName("message")
	String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Users> getListDataUsers() {
		return listDataUsers;
	}

	public void setListDataUsers(List<Users> listDataUsers) {
		this.listDataUsers = listDataUsers;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
