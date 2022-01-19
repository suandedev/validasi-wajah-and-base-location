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

public class KelasActivity extends AppCompatActivity {

	private ListView lvKelasByMhs;

	private ApiInterface mApiInterface;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        lvKelasByMhs = findViewById(R.id.lvKelasbyMhs);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

//        string ekstra
		String userId = getIntent().getStringExtra("userId");

		Call<GetKelas> call = mApiInterface.getKelas();
		call.enqueue(new Callback<GetKelas>() {
			@Override
			public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {

				List<Kelas> kelasList = response.body().getListDataKelas();

				KelasAdapter adapter = new KelasAdapter(KelasActivity.this, kelasList);
				lvKelasByMhs.setAdapter(adapter);

				lvKelasByMhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), PertemuanActivity.class);
						intent.putExtra("kelasId", kelasList.get(i).getId());
						intent.putExtra("userId", userId);
						startActivity(intent);
					}
				});

				Log.d(TAG, "onResponse: " + response.body().getListDataKelas());
			}

			@Override
			public void onFailure(Call<GetKelas> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});

    }
}
