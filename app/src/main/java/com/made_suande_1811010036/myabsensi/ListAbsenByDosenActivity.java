package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.AbsenAdapterByMhs;
import com.made_suande_1811010036.myabsensi.model.Absen;
import com.made_suande_1811010036.myabsensi.model.GetAbsen;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAbsenByDosenActivity extends AppCompatActivity {

	ListView lvLihatAbsenByDosen;

	ApiInterface mAPiInterface;

	String TAG ="mydata";
	String kelasId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_absen_by_dosen);

        lvLihatAbsenByDosen = findViewById(R.id.lvLihatAbsenByDosen);

        mAPiInterface = ApiClient.getClient().create(ApiInterface.class);

        kelasId = getIntent().getStringExtra("kelasId");

        getAbsenByDosen();
    }

	private void getAbsenByDosen() {

		Call<GetAbsen> call = mAPiInterface.getAbsenByDosen(Integer.valueOf(kelasId));
		call.enqueue(new Callback<GetAbsen>() {
			@Override
			public void onResponse(Call<GetAbsen> call, Response<GetAbsen> response) {
				List<Absen> absenList = response.body().getListDataAbsen();

				Log.d(TAG, "onResponse: " + response.message());
				AbsenAdapterByMhs adapter = new AbsenAdapterByMhs(ListAbsenByDosenActivity.this, absenList);
				lvLihatAbsenByDosen.setAdapter(adapter);

				lvLihatAbsenByDosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent =  new Intent(getApplicationContext(), DetailAbsenByDosenActivity.class);
						intent.putExtra("id", absenList.get(i).getId());
						intent.putExtra("mahasiswa", absenList.get(i).getMahasiswa());
						intent.putExtra("npm", absenList.get(i).getNpm());
						intent.putExtra("keterangan", absenList.get(i).getKeterangan());
						intent.putExtra("latitude", absenList.get(i).getLatitude());
						intent.putExtra("longtitude", absenList.get(i).getLongtitude());
						intent.putExtra("lokasi", absenList.get(i).getLokasi());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetAbsen> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
