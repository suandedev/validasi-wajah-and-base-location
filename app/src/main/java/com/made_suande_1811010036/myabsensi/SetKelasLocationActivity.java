package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.KelasAdapter;
import com.made_suande_1811010036.myabsensi.model.GetKelas;
import com.made_suande_1811010036.myabsensi.model.Kelas;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetKelasLocationActivity extends AppCompatActivity {

	ListView lvSetKelasLocation;

	String TAG = "mydata";
	String userId;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_kelas_location);

		lvSetKelasLocation = findViewById(R.id.lvSetKelasLocation);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		userId = getIntent().getStringExtra("userId");

		getKelas();
    }

	private void getKelas() {
		Call<GetKelas> call = mApiInterface.getKelasById(userId);
		call.enqueue(new Callback<GetKelas>() {
			@Override
			public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
				List<Kelas> kelasList = response.body().getListDataKelas();

				KelasAdapter adapter = new KelasAdapter(SetKelasLocationActivity.this, kelasList);
				lvSetKelasLocation.setAdapter(adapter);

				lvSetKelasLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), SetLocationActivity.class);
						intent.putExtra("kelasId", kelasList.get(i).getId());
						startActivity(intent);
						finish();
					}
				});
			}

			@Override
			public void onFailure(Call<GetKelas> call, Throwable t) {
				Log.d(TAG, "onFailure: " +t.getMessage());
			}
		});
	}
}
