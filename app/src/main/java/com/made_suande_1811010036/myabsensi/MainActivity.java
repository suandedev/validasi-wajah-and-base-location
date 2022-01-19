package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.MatkulAdapter;
import com.made_suande_1811010036.myabsensi.model.Matkul;
import com.made_suande_1811010036.myabsensi.model.GetMatkul;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

	ListView lvMatkul;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMatkul = findViewById(R.id.lvMatkul);

		String TAG = "mydata";

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		getMatkul();
    }

	private void getMatkul() {
		Call<GetMatkul> absensiCall = mApiInterface.getMatkul(2);
		absensiCall.enqueue(new Callback<GetMatkul>() {
			@Override
			public void onResponse(Call<GetMatkul> call, Response<GetMatkul> response) {
				List<Matkul> matkulList = response.body().getListDataMatkul();

				Log.d("mydata", "onCreate: " + matkulList.get(1).getDosen());
				MatkulAdapter adapter = new MatkulAdapter(MainActivity.this, matkulList);
				lvMatkul.setAdapter(adapter);

				lvMatkul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), DetailMatkulActivity.class);
						intent.putExtra("matkul", matkulList.get(i).getMatkul());
						intent.putExtra("dosen", matkulList.get(i).getDosen());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetMatkul> call, Throwable t) {
				Log.d("mydata", "onCreate: " + t.getMessage());

			}
		});
	}
}
