package com.made_suande_1811010036.myabsensi.rest;

import com.made_suande_1811010036.myabsensi.model.GetAbsen;
import com.made_suande_1811010036.myabsensi.model.GetDosen;
import com.made_suande_1811010036.myabsensi.model.GetKelas;
import com.made_suande_1811010036.myabsensi.model.GetMatkul;
import com.made_suande_1811010036.myabsensi.model.GetMhs;
import com.made_suande_1811010036.myabsensi.model.GetPertemuan;
import com.made_suande_1811010036.myabsensi.model.GetSetLocation;
import com.made_suande_1811010036.myabsensi.model.GetSetTime;
import com.made_suande_1811010036.myabsensi.model.GetState;
import com.made_suande_1811010036.myabsensi.model.GetUsers;
import com.made_suande_1811010036.myabsensi.model.Matkul;
import com.made_suande_1811010036.myabsensi.model.PostPutDelAbsen;
import com.made_suande_1811010036.myabsensi.model.PostPutDelKelas;
import com.made_suande_1811010036.myabsensi.model.PostPutDelSetLocation;
import com.made_suande_1811010036.myabsensi.model.PostPutDelSetTime;
import com.made_suande_1811010036.myabsensi.model.PostPutDelUsers;
import com.made_suande_1811010036.myabsensi.model.SetLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

	@GET("matkul/{mhsId}")
	Call<GetMatkul> getMatkul(@Path("mhsId") Integer mhsId);

	@GET("pertemuan")
	Call<GetPertemuan> getPertemuan();

	@GET("absen")
	Call<GetAbsen> getAbsen(@Query("userId") Integer userId);

	@GET("absen")
	Call<GetAbsen> getAbsenByDosen(@Query("kelasId") Integer kelasId);

	@GET("auth")
	Call<GetUsers> getUsers();

	@GET("kelas")
	Call<GetKelas> getKelas();

	@GET("kelas/{userId}")
	Call<GetKelas> getKelasById(@Path("userId") String userId);

	@GET("state")
	Call<GetState> getState();

	@GET("absen")
	Call<GetAbsen> getAllAbsen();

	@GET("mhs")
	Call<GetMhs> getMhs(@Query("name") String name);

	@GET("kelas/{userId}")
	Call<GetKelas> getKelasByDosen(@Path("userId") int userId);

	@GET("settime")
	Call<GetSetTime> getSetTime(@Query("kelasId") int kelasId,
								@Query("pertemuanId") int peremuanId,
								@Query("stateId") int stateId);

	@GET("users")
	Call<GetUsers> getUserByAdmin();

	@GET("setlocation/{kelasId}")
	Call<GetSetLocation> getSetLocation(@Path("kelasId") int kelasId);

	@GET("setlocationbyid/{id}")
	Call<GetSetLocation> getSetLocationById(@Path("id") int id);

	@GET("setlocation")
	Call<GetSetLocation> getSetLocationAll();

	@GET("dosen/{userId}")
	Call<GetDosen> getDosenByUserId(@Path("userId") String userId);

	@GET("mhsbyuserid/{userId}")
	Call<GetMhs> getMhsByUserId(@Path("userId") String userId);

	@FormUrlEncoded
	@POST("absen")
	Call<PostPutDelAbsen> postAbsen(@Field("kelasId") int kelasId,
									@Field("userId") int userId,
									@Field("pertemuanId") int pertemuanId,
									@Field("stateId") int stateId,
									@Field("mahasiswa") String mahasiswa,
									@Field("npm") int npm,
									@Field("keterangan") String keterangan,
									@Field("lokasi") String lokasi,
									@Field("latitude") double latitute,
									@Field("longtitude") double longtitude,
									@Field("createdAt") int createdAt);

	@FormUrlEncoded
	@POST("settime")
	Call<PostPutDelSetTime> postSetTime(@Field("kelasId") int kelasId,
										@Field("pertemuanId") int pertemuanId,
										@Field("stateId") int stateId,
										@Field("dayIn") int dayIn,
										@Field("monthIn") int monthIn,
										@Field("yearIn") int yearIn,
										@Field("hourIn") int hourIn,
										@Field("minuteIn") int minuteIn,
										@Field("dayOut") int dayOut,
										@Field("monthOut") int monthOut,
										@Field("yearOut") int yearOut,
										@Field("hourOut") int hourOut,
										@Field("minuteOut") int minuteOut
	);

	@FormUrlEncoded
	@POST("users")
	Call<PostPutDelUsers> insertUser(@Field("email") String email,
									 @Field("password") String password,
									 @Field("rule") String rule);

	@FormUrlEncoded
	@PUT("users")
	Call<GetUsers> resetPasswordUser(@Field("email") String email,
									 @Field("password") String password,
									 @Field("rule") Integer rule);

	@FormUrlEncoded
	@PUT("setlocation")
	Call<SetLocation> updateLocation(@Field("id") Integer id,
									 @Field("kelasId") Integer kelasId,
									 @Field("latitude") Double latitude,
									 @Field("longtitude") Double longtitude);

	@FormUrlEncoded
	@POST("kelas")
	Call<PostPutDelKelas> postKelas(@Field("userId") String id,
									@Field("kelas") String kelas,
									@Field("matkul") String matkul,
									@Field("jam") String jam,
									@Field("ruangan") String ruangan,
									@Field("dosen") String dosen);

	@FormUrlEncoded
	@POST("setlocation")
	Call<PostPutDelSetLocation> postSetLocation(@Field("kelasId") Integer kelasId,
												@Field("latitude") Double latitude,
												@Field("longtitude") Double longtitude);

	@FormUrlEncoded
	@HTTP(method = "DELETE", path = "users", hasBody = true)
	Call<PostPutDelUsers> deleteUser(@Field("id") String id);

	@FormUrlEncoded
	@HTTP(method = "DELETE", path = "setlocation", hasBody = true)
	Call<PostPutDelSetLocation> deleteLocation(@Field("id") String id);
}
