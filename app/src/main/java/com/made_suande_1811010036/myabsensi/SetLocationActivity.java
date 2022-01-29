package com.made_suande_1811010036.myabsensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.made_suande_1811010036.myabsensi.model.PostPutDelSetLocation;
import com.made_suande_1811010036.myabsensi.model.PostPutDelUsers;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetLocationActivity extends AppCompatActivity {

	Button btngetLocation, btnSaveLocation;
	TextView latitude, longtitude;

	String TAG = "mydata";
	String kelasId;
	Double getLatitude, getLongtitude;

	ApiInterface mApiInterface;

	FusedLocationProviderClient fusedLocationProviderClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_location);

		btngetLocation = findViewById(R.id.btnGetLocation);
		btnSaveLocation = findViewById(R.id.btnSaveLocation);
		latitude = findViewById(R.id.latitude);
		longtitude = findViewById(R.id.longtitude);

		kelasId = getIntent().getStringExtra("kelasId");

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

		btngetLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getLocation();
				latitude.setText("latitude is:" + String.valueOf(getLatitude));
				longtitude.setText("longtitude is:" + String.valueOf(getLongtitude));
			}
		});

		btnSaveLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				saveLocation();
			}
		});

	}

	private void saveLocation() {

		Call<PostPutDelSetLocation> call = mApiInterface.postSetLocation(Integer.parseInt(kelasId), getLatitude, getLongtitude);
		call.enqueue(new Callback<PostPutDelSetLocation>() {
			@Override
			public void onResponse(Call<PostPutDelSetLocation> call, Response<PostPutDelSetLocation> response) {
				Log.d(TAG, "onResponse: " + response.message());
				Toast.makeText(getApplicationContext(), "insert succesfully", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Call<PostPutDelSetLocation> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}

	private void getLocation() {

		//check permission
		if (ActivityCompat.checkSelfPermission(SetLocationActivity.this,
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
							Geocoder geocoder = new Geocoder(SetLocationActivity.this,
									Locale.getDefault());

							// initialize adddress
							List<Address> addresses = geocoder.getFromLocation(
									location.getLatitude(), location.getLongitude(), 1
							);

							getLatitude = addresses.get(0).getLatitude();
							getLongtitude = addresses.get(0).getLongitude();
//							doAbsen(Integer.valueOf(userId), Integer.valueOf(kelasId), Integer.valueOf(pertemuanId), Integer.valueOf(stateId), Integer.valueOf(curentTime), latitude, longtitute);

							Log.d(TAG, "onComplete: " + addresses.get(0).getLatitude() + " long" + addresses.get(0).getLongitude());

						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			});
		} else {
			//when permision denied
			ActivityCompat.requestPermissions(SetLocationActivity.this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
		}
	}
}
