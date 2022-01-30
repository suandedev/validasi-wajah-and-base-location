package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.made_suande_1811010036.myabsensi.model.Dosen;
import com.made_suande_1811010036.myabsensi.model.GetDosen;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDosenActivity extends AppCompatActivity {

	Button btnGetAbsenByDosen, btnLogout, btnSetLocation;
	TextView dosen, prodi;

	String TAG = "mydata";

	ApiInterface mApiInterface;
	String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosen);

        btnGetAbsenByDosen = findViewById(R.id.btnGetAbsenByDosen);
		btnSetLocation = findViewById(R.id.btnSetLocation);
        btnLogout = findViewById(R.id.btnLogout);
        dosen = findViewById(R.id.dosen);
        prodi = findViewById(R.id.prodi);

        userId = getIntent().getStringExtra("userId");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

//        get string eksra
		String userId = getIntent().getStringExtra("userId");


		btnGetAbsenByDosen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), KelasByDosenActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		btnSetLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), GetSetLocationActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
				startActivity(intent);
				finish();
			}
		});

		getDosen();
    }

	private void getDosen() {

		Call<GetDosen> call = mApiInterface.getDosenByUserId(userId);

		call.enqueue(new Callback<GetDosen>() {
			@Override
			public void onResponse(Call<GetDosen> call, Response<GetDosen> response) {
				List<Dosen> dosenList = response.body().getListDataDosen();

				dosen.setText("Nama : " + dosenList.get(0).getDosen());
				prodi.setText("Progam Studi : " + dosenList.get(0).getProdi());
			}

			@Override
			public void onFailure(Call<GetDosen> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
