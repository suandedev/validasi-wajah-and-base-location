package com.made_suande_1811010036.myabsensi.rest;

import com.made_suande_1811010036.myabsensi.model.GetAbsen;
import com.made_suande_1811010036.myabsensi.model.GetKelas;
import com.made_suande_1811010036.myabsensi.model.GetMatkul;
import com.made_suande_1811010036.myabsensi.model.GetMhs;
import com.made_suande_1811010036.myabsensi.model.GetPertemuan;
import com.made_suande_1811010036.myabsensi.model.GetSetTime;
import com.made_suande_1811010036.myabsensi.model.GetState;
import com.made_suande_1811010036.myabsensi.model.GetUsers;
import com.made_suande_1811010036.myabsensi.model.Matkul;
import com.made_suande_1811010036.myabsensi.model.PostPutDelAbsen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

	@GET("matkul/{mhsId}")
	Call<GetMatkul> getMatkul(@Path("mhsId") Integer mhsId);

	@GET("pertemuan")
	Call<GetPertemuan> getPertemuan();

	@GET("absen")
	Call<GetAbsen> getAbsen(@Query("userId") Integer userId);

	@GET("auth")
	Call<GetUsers> getUsers();

	@GET("kelas")
	Call<GetKelas> getKelas();

	@GET("state")
	Call<GetState> getState();

	@GET("mhs")
	Call<GetMhs> getMhs(@Query("name") String name);

	@GET("settime")
	Call<GetSetTime> getSetTime(@Query("kelasId") int kelasId,
								@Query("pertemuanId") int peremuanId,
								@Query("stateId") int stateId);

	@FormUrlEncoded
	@POST("absen")
	Call<PostPutDelAbsen> postAbsen(@Field("kelasId") int kelasId,
									@Field("userId") int userId,
									@Field("pertemuanId") int pertemuanId,
									@Field("stateId") int stateId,
									@Field("mahasiswa") String mahasiswa,
									@Field("npm") int npm,
									@Field("keterangan") String keterangan,
									@Field("latitude") double latitute,
									@Field("longtitude") double longtitude,
									@Field("createdAt") int createdAt);
}
