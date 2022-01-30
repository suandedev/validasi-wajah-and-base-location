package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.made_suande_1811010036.myabsensi.model.GetMhs;
import com.made_suande_1811010036.myabsensi.model.Mhs;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeMhsActivity extends AppCompatActivity {

	private Button btnAbsen, btnKelasByMhs, btnLogout;

	private ApiInterface mApiInterface;

	String TAG = "mydata";
	String userId;
	TextView npm, namaMhs, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mhs);

        btnAbsen = findViewById(R.id.btnAbsen);
        btnKelasByMhs = findViewById(R.id.btnKelasByMhs);
        btnLogout = findViewById(R.id.btnLogout);
		npm = findViewById(R.id.npm);
		namaMhs = findViewById(R.id.namaMhs);
		jurusan = findViewById(R.id.jurusan);

        userId = getIntent().getStringExtra("userId");

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        getMhs();

        btnAbsen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), KelasActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

        btnKelasByMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), ListAbsenActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
				Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
				startActivity(intent);
			}
		});

    }

	private void getMhs() {

		Call<GetMhs> call = mApiInterface.getMhsByUserId(userId);
		call.enqueue(new Callback<GetMhs>() {
			@Override
			public void onResponse(Call<GetMhs> call, Response<GetMhs> response) {
				List<Mhs> mhsList = response.body().getListDataMhs();

				npm.setText("NPM : " + mhsList.get(0).getNpm());
				jurusan.setText("Jurusan : " + mhsList.get(0).getJurusan());
				namaMhs.setText("Nama : " + mhsList.get(0).getName());
			}

			@Override
			public void onFailure(Call<GetMhs> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
