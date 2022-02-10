package com.made_suande_1811010036.myabsensi.rest;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

	public static final String BASE_URL = "http://1811010036.skripsi.darmajaya.ac.id/";
//	public static final String BASE_URL = "http://192.168.43.247:8080/my-absenv3/";
//	public static final String BASE_URL = "http://192.168.43.247:8080/my-absenv2/";
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
