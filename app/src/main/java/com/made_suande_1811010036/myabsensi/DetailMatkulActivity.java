package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.made_suande_1811010036.myabsensi.adapter.PertemuanAdapter;
import com.made_suande_1811010036.myabsensi.model.GetPertemuan;
import com.made_suande_1811010036.myabsensi.model.Pertemuan;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMatkulActivity extends AppCompatActivity {

	TextView matkul, dosen;
	ListView lvPertemuan;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_matkul);

        dosen = findViewById(R.id.dosen);
        matkul = findViewById(R.id.matkul);
        lvPertemuan = findViewById(R.id.lvPertemuan);

        String getDosen = getIntent().getStringExtra("dosen");
        String getMatkul = getIntent().getStringExtra("matkul");

        dosen.setText(getDosen);
        matkul.setText(getMatkul);

		String TAG = "mydata";

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		Call<GetPertemuan> pertemuanCall = mApiInterface.getPertemuan();

		pertemuanCall.enqueue(new Callback<GetPertemuan>() {
			@Override
			public void onResponse(Call<GetPertemuan> call, Response<GetPertemuan> response) {
				List<Pertemuan> pertemuanList = response.body().getListDataPertemuan();

				Log.d(TAG, "onCreate: " + pertemuanList.get(8).getPertemuan());

				PertemuanAdapter adapter = new PertemuanAdapter(DetailMatkulActivity.this, pertemuanList);
				lvPertemuan.setAdapter(adapter);

				lvPertemuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), AbsenActivity.class);

						intent.putExtra("pertemuan", pertemuanList.get(i).getPertemuan());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetPertemuan> call, Throwable t) {
				Log.d(TAG, "onCreate: " + t.getMessage());

			}
		});
    }
}
