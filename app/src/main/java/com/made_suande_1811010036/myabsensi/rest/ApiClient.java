package com.made_suande_1811010036.myabsensi.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

	public static final String BASE_URL = "http://192.168.43.247:8080/my-absenv2/";
	private static Retrofit retrofit = null;
	public static Retrofit getClient() {
		if (retrofit==null){
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
