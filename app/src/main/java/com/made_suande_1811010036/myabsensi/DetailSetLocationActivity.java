package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.PostPutDelSetLocation;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSetLocationActivity extends AppCompatActivity {

	TextView dkelas, dlatitude, dlongtitude, ddosen, druangan, djam, dmatkul;
	Button btnUpdate, btnDelete;

	String getId, getkelasId, getLatitude, getlongtitude, getDosen, getRuangan, getJam, getMatkul, getKelas;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_set_location);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        dkelas = findViewById(R.id.dkelas);
        dlatitude = findViewById(R.id.dlatitude);
		dlongtitude = findViewById(R.id.dlongtitude);
		ddosen = findViewById(R.id.ddosen);
		druangan = findViewById(R.id.druangan);
		djam = findViewById(R.id.djam);
		dmatkul = findViewById(R.id.dmatkul);
		btnDelete = findViewById(R.id.btnDeleteLocation);
		btnUpdate = findViewById(R.id.btnUpdateLocation);

		getId = getIntent().getStringExtra("id");
		getkelasId = getIntent().getStringExtra("kelasId");
		getlongtitude = getIntent().getStringExtra("longtitude");
		getLatitude = getIntent().getStringExtra("latitude");
		getDosen = getIntent().getStringExtra("dosen");
		getRuangan = getIntent().getStringExtra("ruangan");
		getJam = getIntent().getStringExtra("jam");
		getMatkul = getIntent().getStringExtra("matkul");
		getKelas = getIntent().getStringExtra("kelas");

		dlatitude.setText("latitude id : " + getLatitude);
		dlongtitude.setText("longtitude id : " + getlongtitude);
		dkelas.setText("kelas id : " + getKelas);
		ddosen.setText("dosen : " + getDosen);
		druangan.setText("ruangan : " + getRuangan);
		djam.setText("jam : " + getJam);
		dmatkul.setText("matkul : " + getMatkul);

		btnUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), UpdateLocationActivity.class);
				intent.putExtra("id", getId);
				intent.putExtra("kelasId", getkelasId);
				startActivity(intent);
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Call<PostPutDelSetLocation> call = mApiInterface.deleteLocation(getId);
				call.enqueue(new Callback<PostPutDelSetLocation>() {
					@Override
					public void onResponse(Call<PostPutDelSetLocation> call, Response<PostPutDelSetLocation> response) {

					}

					@Override
					public void onFailure(Call<PostPutDelSetLocation> call, Throwable t) {
						Log.d("mydata", "onFailure: " + t.getMessage());
					}
				});
				Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
				finish();
				GetSetLocationActivity.sla.getSetLocation();
			}
		});
    }
}
