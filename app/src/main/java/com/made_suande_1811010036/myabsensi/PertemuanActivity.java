package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.PertemuanAdapter;
import com.made_suande_1811010036.myabsensi.model.GetPertemuan;
import com.made_suande_1811010036.myabsensi.model.Pertemuan;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PertemuanActivity extends AppCompatActivity {

	private ListView lvPertemuanByMhs;

	private ApiInterface mAPiInterface;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertemuan);

        lvPertemuanByMhs = findViewById(R.id.lvPertemuanByMhs);

        mAPiInterface = ApiClient.getClient().create(ApiInterface.class);

//        string ekstra
		String userId = getIntent().getStringExtra("userId");
		String kelasId = getIntent().getStringExtra("kelasId");

		Call<GetPertemuan> call = mAPiInterface.getPertemuan();
		call.enqueue(new Callback<GetPertemuan>() {
			@Override
			public void onResponse(Call<GetPertemuan> call, Response<GetPertemuan> response) {
				List<Pertemuan> pertemuanList = response.body().getListDataPertemuan();

				PertemuanAdapter adapter = new PertemuanAdapter(PertemuanActivity.this, pertemuanList);
				lvPertemuanByMhs.setAdapter(adapter);

				lvPertemuanByMhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), StateActivity.class);
						intent.putExtra("userId", userId);
						intent.putExtra("kelasId", kelasId);
						intent.putExtra("pertemuanId", pertemuanList.get(i).getId());
						startActivity(intent);
						Log.d(TAG, "onItemClick: " + getIntent().getStringExtra("kelasId"));
					}
				});
			}

			@Override
			public void onFailure(Call<GetPertemuan> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
    }
}
