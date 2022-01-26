package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.AbsenAdapterByAdmin;
import com.made_suande_1811010036.myabsensi.model.Absen;
import com.made_suande_1811010036.myabsensi.model.GetAbsen;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAbsenByAdminActivity extends AppCompatActivity {

	ListView lvKelasByAdmin;

	ApiInterface mApiInterface;

	String TAG = "mydata";

	public static  ListAbsenByAdminActivity la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_absen_by_admin);

		lvKelasByAdmin = findViewById(R.id.lvKelasByAdmin);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		la = this;

		refresh();
    }

	public void refresh() {

		Call<GetAbsen> call = mApiInterface.getAllAbsen();
		call.enqueue(new Callback<GetAbsen>() {
			@Override
			public void onResponse(Call<GetAbsen> call, Response<GetAbsen> response) {
				List<Absen> absenList = response.body().getListDataAbsen();

				Log.d(TAG, "onResponse: " + response.message());

				AbsenAdapterByAdmin adapter = new AbsenAdapterByAdmin(ListAbsenByAdminActivity.this, absenList);
				lvKelasByAdmin.setAdapter(adapter);

				lvKelasByAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), DetailAbsenByAdminActivity.class);
						intent.putExtra("id", absenList.get(i).getId());
						intent.putExtra("nama", absenList.get(i).getMahasiswa());
						intent.putExtra("npm", absenList.get(i).getNpm());
						intent.putExtra("keterangan", absenList.get(i).getKeterangan());
						intent.putExtra("lokasi", absenList.get(i).getLokasi());
						intent.putExtra("latitude", absenList.get(i).getLatitude());
						intent.putExtra("longtitude", absenList.get(i).getLongtitude());
						intent.putExtra("createdAt", absenList.get(i).getCreatedAt());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetAbsen> call, Throwable t) {
				Log.d(TAG, "onFailure: " +t.getMessage());
			}
		});
	}
}
