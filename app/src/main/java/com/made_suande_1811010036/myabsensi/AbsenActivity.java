package com.made_suande_1811010036.myabsensi;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.made_suande_1811010036.myabsensi.ml.Model;
import com.made_suande_1811010036.myabsensi.model.GetMhs;
import com.made_suande_1811010036.myabsensi.model.GetSetTime;
import com.made_suande_1811010036.myabsensi.model.Mhs;
import com.made_suande_1811010036.myabsensi.model.PostPutDelAbsen;
import com.made_suande_1811010036.myabsensi.model.SetTime;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenActivity extends AppCompatActivity {

	TextView prediksi, presentase;
	ImageView imageView;
	Button btnGetImage, btnAbsen;
	int imageSize = 224;

	String predictName, userId, kelasId, pertemuanId, stateId, curentTime, npm;
	Double latitude, longtitute;

	private ApiInterface mApiInterface;
	String TAG = "mydata";

	FusedLocationProviderClient fusedLocationProviderClient;

    @SuppressLint("SetTextI18n")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

		prediksi = findViewById(R.id.prediksi);
		presentase = findViewById(R.id.presentase);
		imageView = findViewById(R.id.imageView);
		btnGetImage = findViewById(R.id.btnGetImage);
		btnAbsen = findViewById(R.id.btnAbsen);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

		btnGetImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
					Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, 1);
				}else {
					requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
				}

			}
		});

		btnAbsen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Call<GetMhs> call = mApiInterface.getMhs(predictName);

				call.enqueue(new Callback<GetMhs>() {
					@Override
					public void onResponse(Call<GetMhs> call, Response<GetMhs> response) {
						List<Mhs> mhsList = response.body().getListDataMhs();

						npm = mhsList.get(0).getNpm();
						
						Call<GetSetTime> call1 = mApiInterface.getSetTime(Integer.valueOf(kelasId),Integer.valueOf(pertemuanId),Integer.valueOf(stateId));
						call1.enqueue(new Callback<GetSetTime>() {
							@Override
							public void onResponse(Call<GetSetTime> call, Response<GetSetTime> response) {
								List<SetTime> setTimeList = response.body().getListDataSetTime();

								int paramIn = Integer.valueOf(setTimeList.get(0).getParamIn());
								int paramOut = Integer.valueOf(setTimeList.get(0).getParamOut());
								int time = Integer.valueOf(curentTime);

								Log.d(TAG, "onResponse: " + paramIn + " " + paramOut + " " + time);

								if (predictName == null) {
									Toast.makeText(getApplicationContext(), "foto tidak boleh kosong", Toast.LENGTH_SHORT).show();
								} else {
									if (time >=  paramIn && time <= paramOut) {
	//									doAbsen(Integer.valueOf(userId), Integer.valueOf(kelasId), Integer.valueOf(pertemuanId), Integer.valueOf(stateId), Integer.valueOf(curentTime), latitude, longtitute, predictName, Integer.valueOf(npm));
									} else {
										Toast.makeText(getApplicationContext(), "waktu absensi habis", Toast.LENGTH_SHORT).show();
										Log.d(TAG, "onResponse: false" );
									}

								}



//								Log.d(TAG, "onResponse: " + setTimeList.get(0).getParamIn());
							}

							@Override
							public void onFailure(Call<GetSetTime> call, Throwable t) {
								Log.d(TAG, "onFailure: " + t.getMessage());
							}
						});

//						doAbsen(Integer.valueOf(userId), Integer.valueOf(kelasId), Integer.valueOf(pertemuanId), Integer.valueOf(stateId), Integer.valueOf(curentTime), latitude, longtitute, predictName, Integer.valueOf(npm));
//					Log.d(TAG, "onResponse: " + npm);
					}

					@Override
					public void onFailure(Call<GetMhs> call, Throwable t) {
						Log.d(TAG, "onFailure: " + t.getMessage());
					}
				});

			}
		});

		//        get ekstra
		 userId = getIntent().getStringExtra("userId");
		 kelasId = getIntent().getStringExtra("kelasId");
		 pertemuanId = getIntent().getStringExtra("pertemuanId");
		 stateId = getIntent().getStringExtra("stateId");

		Long unixTime = System.currentTimeMillis() / 1000L;

		 curentTime = String.valueOf(unixTime);

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

							latitude = addresses.get(0).getLatitude();
							longtitute = addresses.get(0).getLongitude();
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
			ActivityCompat.requestPermissions(AbsenActivity.this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
		}

    }

	private void doAbsen(Integer userId, Integer kelasId, Integer pertemuanId, Integer stateId, Integer currentTime, Double latitute, Double longtitute, String predictName, Integer npm) {
		Call<PostPutDelAbsen> call = mApiInterface.postAbsen(
				kelasId, userId, pertemuanId, stateId, predictName, npm, "hadir", latitute, longtitute, currentTime
		);
		call.enqueue(new Callback<PostPutDelAbsen>() {
			@Override
			public void onResponse(Call<PostPutDelAbsen> call, Response<PostPutDelAbsen> response) {
				Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
				Log.d(TAG, "onResponse: " + response.body().getMessage());
			}

			@Override
			public void onFailure(Call<PostPutDelAbsen> call, Throwable t) {
				Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			Bitmap image = (Bitmap) data.getExtras().get("data");
			int dimension = Math.min(image.getWidth(), image.getHeight());
			image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
			imageView.setImageBitmap(image);

			image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
			clasifiyImage(image);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void clasifiyImage(Bitmap image) {
		try {
			Model model = Model.newInstance(getApplicationContext());

			TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
			byteBuffer.order(ByteOrder.nativeOrder());

			int [] intValues = new int[imageSize * imageSize];
			image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

			int pixel = 0;
			for(int i = 0; i < imageSize; i++){
				for(int j = 0; j < imageSize; j++){
					int val = intValues[pixel++]; // RGB
					byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
					byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
					byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
				}
			}

			inputFeature0.loadBuffer(byteBuffer);

			Model.Outputs outputs = model.process(inputFeature0);
			TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

			float[] confidences = outputFeature0.getFloatArray();
			// find the index of the class with the biggest confidence.
			int maxPos = 0;
			float maxConfidence = 0;
			for(int i = 0; i < confidences.length; i++){
				if(confidences[i] > maxConfidence){
					maxConfidence = confidences[i];
					maxPos = i;
				}
			}

			String[] classes = {"made suande", "indra", "tomas"};
			prediksi.setText(classes[maxPos]);
			predictName = classes[maxPos];

//			doAbsen(Integer.valueOf(userId), Integer.valueOf(kelasId), Integer.valueOf(pertemuanId), Integer.valueOf(stateId), Integer.valueOf(curentTime), latitude, longtitute, predictName);


			String s = "";
			for(int i = 0; i < classes.length; i++){
				s += String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100);
			}
			presentase.setText(s);

			model.close();

		} catch (IOException e) {
			Log.d(TAG, "clasifiyImage: " + e.getMessage());
		}
	}
}
