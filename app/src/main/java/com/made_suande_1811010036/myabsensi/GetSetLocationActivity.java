package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.SetLocationAdapter;
import com.made_suande_1811010036.myabsensi.model.GetSetLocation;
import com.made_suande_1811010036.myabsensi.model.SetLocation;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSetLocationActivity extends AppCompatActivity {

	ListView lvSetLokasi;

	Button btnInsertSetLocation;

	public  static GetSetLocationActivity sla;

	ApiInterface mApiInterface;

	String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_set_location);

		lvSetLokasi = findViewById(R.id.lvSetLokasi);
		btnInsertSetLocation = findViewById(R.id.btnInsertSetLocation);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		userId = getIntent().getStringExtra("userId");

		getSetLocation();

		sla = this;

		btnInsertSetLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SetKelasLocationActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
    }

	public void getSetLocation() {

		Call<GetSetLocation> call = mApiInterface.getSetLocationAll();
		call.enqueue(new Callback<GetSetLocation>() {
			@Override
			public void onResponse(Call<GetSetLocation> call, Response<GetSetLocation> response) {
				List<SetLocation> setLocationList = response.body().getListDataSetLocation();

				SetLocationAdapter adapter = new SetLocationAdapter(GetSetLocationActivity.this, setLocationList);
				lvSetLokasi.setAdapter(adapter);

				lvSetLokasi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), DetailSetLocationActivity.class);
						intent.putExtra("id", setLocationList.get(i).getId());
						intent.putExtra("kelasId", setLocationList.get(i).getKelasId());
						intent.putExtra("latitude", setLocationList.get(i).getLatitude());
						intent.putExtra("longtitude", setLocationList.get(i).getLongtitude());
						intent.putExtra("dosen", setLocationList.get(i).getDosen());
						intent.putExtra("ruangan", setLocationList.get(i).getRuangan());
						intent.putExtra("jam", setLocationList.get(i).getJam());
						intent.putExtra("matkul", setLocationList.get(i).getMatkul());
						intent.putExtra("kelas", setLocationList.get(i).getKelas());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetSetLocation> call, Throwable t) {
				Log.d("mydata", "onFailure: " + t.getMessage());
			}
		});
	}
}
