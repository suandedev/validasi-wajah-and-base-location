package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class KelasByAdminActivity extends AppCompatActivity {

	ListView lvKelasByAdmin;
	Button btnNewKelas;

	ApiInterface mApiInterface;

	public  static KelasByAdminActivity ka;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas_by_admin);

		lvKelasByAdmin = findViewById(R.id.lvKelasByAdmin);
		btnNewKelas = findViewById(R.id.btnNewKelas);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		btnNewKelas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SelectUserActivity.class);
				startActivity(intent);
			}
		});

		ka = this;
		refresh();
    }

	public void refresh() {
    	lvKelasByAdmin.setAdapter(null);
		Call<GetKelas> call = mApiInterface.getKelas();
		call.enqueue(new Callback<GetKelas>() {
			@Override
			public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
				List<Kelas> kelasList = response.body().getListDataKelas();

				KelasAdapter adapter = new KelasAdapter(KelasByAdminActivity.this, kelasList);
				lvKelasByAdmin.setAdapter(adapter);
			}

			@Override
			public void onFailure(Call<GetKelas> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
