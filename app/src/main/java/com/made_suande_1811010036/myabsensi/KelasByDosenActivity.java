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

public class KelasByDosenActivity extends AppCompatActivity {

	ListView lvKelasByDosen;

	ApiInterface mApiInterface;

	String TAG = "mydata";
	String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas_by_dosen);

		lvKelasByDosen = findViewById(R.id.lvKelasByDosen);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		 userId = getIntent().getStringExtra("userId");

		getKelasByDosen();
    }

	private void getKelasByDosen() {
		Call<GetKelas> call = mApiInterface.getKelasByDosen(Integer.valueOf(userId));
		call.enqueue(new Callback<GetKelas>() {
			@Override
			public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
				List<Kelas> kelasList = response.body().getListDataKelas();

				Log.d(TAG, "onResponse: " +response.message());
				KelasAdapter adapter = new KelasAdapter(KelasByDosenActivity.this, kelasList);
				lvKelasByDosen.setAdapter(adapter);

				lvKelasByDosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), ListAbsenByDosenActivity.class);
						intent.putExtra("kelasId", kelasList.get(i).getId());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetKelas> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
