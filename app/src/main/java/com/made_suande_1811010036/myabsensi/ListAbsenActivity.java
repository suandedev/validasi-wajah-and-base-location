package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.AbsenAdapterByMhs;
import com.made_suande_1811010036.myabsensi.model.Absen;
import com.made_suande_1811010036.myabsensi.model.GetAbsen;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAbsenActivity extends AppCompatActivity {

	ListView lvAbsen;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_absen);

		lvAbsen  = findViewById(R.id.lvAbsen);
		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		Call<GetAbsen> absenCall = mApiInterface.getAbsen(1);
		absenCall.enqueue(new Callback<GetAbsen>() {
			@Override
			public void onResponse(Call<GetAbsen> call, Response<GetAbsen> response) {
				List<Absen> absenList = response.body().getListDataAbsen();

				AbsenAdapterByMhs adapter = new AbsenAdapterByMhs(ListAbsenActivity.this, absenList);
				lvAbsen.setAdapter(adapter);
			}

			@Override
			public void onFailure(Call<GetAbsen> call, Throwable t) {
				Log.d("mydata", "onCreate: " + t.getMessage());

			}
		});
    }
}
