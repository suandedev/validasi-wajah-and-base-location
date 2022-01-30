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
import com.made_suande_1811010036.myabsensi.model.GetSetLocation;
import com.made_suande_1811010036.myabsensi.model.PostPutDelSetLocation;
import com.made_suande_1811010036.myabsensi.model.SetLocation;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateLocationActivity extends AppCompatActivity {

	Button btnGetLocation, btnSaveLocation;
	TextView latitude, longtitude;

	ApiInterface mApiInterface;

	FusedLocationProviderClient fusedLocationProviderClient;

	String getId, getKelasId;
	Double getLatitude, getLongtitude;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);

		btnGetLocation = findViewById(R.id.btnGetLocation);
		btnSaveLocation = findViewById(R.id.btnSaveLocation);
		latitude = findViewById(R.id.latitude);
		longtitude = findViewById(R.id.longtitude);

		getId = getIntent().getStringExtra("id");
		getKelasId = getIntent().getStringExtra("kelasId");

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

		getLocation();

		btnSaveLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (getLatitude == null) {
					Toast.makeText(getApplicationContext(), "tekan get base location", Toast.LENGTH_SHORT).show();
				}
				updateLocation();
			}
		});


		btnGetLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getLastLocation();
				latitude.setText("latitude is:" + String.valueOf(getLatitude));
				longtitude.setText("longtitude is:" + String.valueOf(getLongtitude));
			}
		});
    }

	private void getLastLocation() {

		//check permission
		if (ActivityCompat.checkSelfPermission(UpdateLocationActivity.this,
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
							Geocoder geocoder = new Geocoder(UpdateLocationActivity.this,
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
			ActivityCompat.requestPermissions(UpdateLocationActivity.this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
		}
	}

	private void updateLocation() {
		if (getLatitude == null && getLongtitude == null) {
			Toast.makeText(getApplicationContext(), "lokasi kosong", Toast.LENGTH_SHORT).show();
		} else {
			Call<SetLocation> call = mApiInterface.updateLocation(Integer.parseInt(getId), Integer.parseInt(getKelasId), getLatitude, getLongtitude);
			call.enqueue(new Callback<SetLocation>() {
				@Override
				public void onResponse(Call<SetLocation> call, Response<SetLocation> response) {
					if (response.isSuccessful()) {
						Toast.makeText(getApplicationContext(), "update successfull", Toast.LENGTH_SHORT).show();
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "faild update", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<SetLocation> call, Throwable t) {
					Log.d("mydata", "onFailure: " + t.getMessage());
				}
			});
		}
	}

	private void getBaseLocation() {
		//check permission
		if (ActivityCompat.checkSelfPermission(UpdateLocationActivity.this,
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
							Geocoder geocoder = new Geocoder(UpdateLocationActivity.this,
									Locale.getDefault());

							// initialize adddress
							List<Address> addresses = geocoder.getFromLocation(
									location.getLatitude(), location.getLongitude(), 1
							);

							getLatitude = addresses.get(0).getLatitude();
							getLongtitude = addresses.get(0).getLongitude();
//							doAbsen(Integer.valueOf(userId), Integer.valueOf(kelasId), Integer.valueOf(pertemuanId), Integer.valueOf(stateId), Integer.valueOf(curentTime), latitude, longtitute);

							Log.d("mydata", "onComplete: " + addresses.get(0).getLatitude() + " long" + addresses.get(0).getLongitude());

						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			});
		} else {
			//when permision denied
			ActivityCompat.requestPermissions(UpdateLocationActivity.this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
		}

		Log.d(TAG, "getBaseLocation: " + getLatitude);
	}

	private void getLocation() {
		Call<GetSetLocation> call = mApiInterface.getSetLocationById(Integer.parseInt(getId));
		call.enqueue(new Callback<GetSetLocation>() {
			@Override
			public void onResponse(Call<GetSetLocation> call, Response<GetSetLocation> response) {
				List<SetLocation> setLocationList = response.body().getListDataSetLocation();

				latitude.setText("latitude : " + setLocationList.get(0).getLatitude());
				longtitude.setText("latitude : " + setLocationList.get(0).getLongtitude());
			}

			@Override
			public void onFailure(Call<GetSetLocation> call, Throwable t) {
				Log.d("mydata", "onFailure: " + t.getMessage());
			}
		});
	}
}
