package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailAbsenByDosenActivity extends AppCompatActivity {

	TextView nama, npm, keterangan, lokasi;
	Button btnCekLokasi;

	private String getMahasiswa, getNpm, getKeterangan, getLokasi, getLatitude, getLongtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen_by_dosen);

        nama = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        keterangan = findViewById(R.id.keterangan);
        lokasi = findViewById(R.id.lokasi);
        btnCekLokasi = findViewById(R.id.btnCekLokasi);

        getMahasiswa = getIntent().getStringExtra("mahasiswa");
        getNpm = getIntent().getStringExtra("npm");
        getKeterangan = getIntent().getStringExtra("keterangan");
        getLokasi = getIntent().getStringExtra("lokasi");
        getLatitude = getIntent().getStringExtra("latitude");
        getLongtitude = getIntent().getStringExtra("longtitude");

        nama.setText("nama : " + getMahasiswa);
        npm.setText("npm : " + getNpm);
        keterangan.setText("keterangan : " + getKeterangan);
        lokasi.setText("lokasi : " + getLokasi);

        btnCekLokasi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
				intent.putExtra("latitude", getLatitude);
				intent.putExtra("longtitude", getLongtitude);
				intent.putExtra("lokasi", getLokasi);
				startActivity(intent);
			}
		});

    }
}
