package com.made_suande_1811010036.myabsensi;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.made_suande_1811010036.myabsensi.model.PostPutDelAbsen;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenActivity extends AppCompatActivity {

	private ApiInterface mApiInterface;
	String TAG = "mydata";

	FusedLocationProviderClient fusedLocationProviderClient;

    @SuppressLint("SetTextI18n")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

		//        get ekstra
		String userId = getIntent().getStringExtra("userId");
		String kelasId = getIntent().getStringExtra("kelasId");
		String pertemuanId = getIntent().getStringExtra("pertemuanId");
		String stateId = getIntent().getStringExtra("stateId");

		Long unixTime = System.currentTimeMillis() / 1000L;

		String curentTime = String.valueOf(unixTime);

//		Log.d(TAG, "onCreate: " +curentTime);

		//check permission
		if (ActivityCompat.checkSelfPermission(AbsenActivity.this,
				Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			//when permission granted
			if (ActivityCompat.checkSelfPermission(this,
					Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
					&& ActivityCompat.checkSelfPermission(this,
					Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				return;
			}
			fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
				@Override
				public void onComplete(@NonNull Task<Location> task) {

					//inittialize location
					Location location = task.getResult();
					if (location != null) {
						try {
							// initialize geocoder
							Geocoder geocoder = new Geocoder(AbsenActivity.this,
									Locale.getDefault());

							// initialize adddress
							List<Address> addresses = geocoder.getFromLocation(
									location.getLatitude(),location.getLongitude(), 1
							);

							doAbsen(Integer.valueOf(userId), Integer.valueOf(kelasId), Integer.valueOf(pertemuanId), Integer.valueOf(stateId), Integer.valueOf(curentTime), addresses.get(0).getLatitude(), addresses.get(0).getLongitude());

							Log.d(TAG, "onComplete: " + addresses.get(0).getLatitude() + " long" + addresses.get(0).getLongitude());

						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			});
		} else {
			//when permision denied
			ActivityCompat.requestPermissions(AbsenActivity.this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
		}

    }

	private void doAbsen(Integer userId, Integer kelasId, Integer pertemuanId, Integer stateId, Integer currentTime, Double latitute, Double longtitute) {
		Call<PostPutDelAbsen> call = mApiInterface.postAbsen(
				userId, kelasId, pertemuanId, stateId, "made", 1811010036, "hadir", latitute, longtitute, currentTime
		);
		call.enqueue(new Callback<PostPutDelAbsen>() {
			@Override
			public void onResponse(Call<PostPutDelAbsen> call, Response<PostPutDelAbsen> response) {
				Log.d(TAG, "onResponse: " + response.body().getMessage());
			}

			@Override
			public void onFailure(Call<PostPutDelAbsen> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
