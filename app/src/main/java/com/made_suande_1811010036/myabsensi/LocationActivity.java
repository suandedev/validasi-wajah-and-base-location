package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

	private GoogleMap map;

	String getLalitude, getLongtitude, getLokasi;
	Double latitude, longtitude;
	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

		SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		supportMapFragment.getMapAsync(this);

		getLalitude = getIntent().getStringExtra("latitude");
		getLongtitude = getIntent().getStringExtra("longtitude");
		getLokasi = getIntent().getStringExtra("lokasi");

		latitude = Double.parseDouble(getLalitude);
		longtitude = Double.parseDouble(getLongtitude);
//
//		Log.d(TAG, "onCreate: " + latitude + " " +longtitude);

    }

	@Override
	public void onMapReady(GoogleMap googleMap) {
		map = googleMap;
		LatLng darmajaya = new LatLng(latitude, longtitude);
		map.addMarker(new MarkerOptions()
				.position(darmajaya)
				.title(getLokasi)
		);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(darmajaya, 20));
	}
}
