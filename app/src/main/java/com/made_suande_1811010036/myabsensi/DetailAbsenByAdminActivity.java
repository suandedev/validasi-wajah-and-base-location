package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

public class DetailAbsenByAdminActivity extends AppCompatActivity {

	TextView nama, npm, keterangan, lokasi, latitude, longtitude, createdAt;
	String getId, getNama, getNpm, getKEterangan, getLokasi, getLatitude, getLongtitude, getCreatedAt;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen_by_admin);

        nama = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        keterangan = findViewById(R.id.keterangan);
        lokasi = findViewById(R.id.lokasi);
        latitude = findViewById(R.id.latitude);
        longtitude = findViewById(R.id.longtitude);
        createdAt = findViewById(R.id.createdAt);

        getId = getIntent().getStringExtra("id");
        getNama = getIntent().getStringExtra("nama");
        getNpm = getIntent().getStringExtra("npm");
        getKEterangan = getIntent().getStringExtra("keterangan");
        getLokasi = getIntent().getStringExtra("lokasi");
        getLatitude = getIntent().getStringExtra("latitude");
        getLongtitude = getIntent().getStringExtra("longtitude");
        getCreatedAt = getIntent().getStringExtra("createdAt");

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        nama.setText("nama : "+ getNama);
        npm.setText("nama : "+ getNpm);
        keterangan.setText("nama : "+ getKEterangan);
        lokasi.setText("nama : "+ getLokasi);
        latitude.setText("nama : "+ getLatitude);
        longtitude.setText("nama : "+ getLongtitude);
        createdAt.setText("nama : "+ getCreatedAt);
    }
}
