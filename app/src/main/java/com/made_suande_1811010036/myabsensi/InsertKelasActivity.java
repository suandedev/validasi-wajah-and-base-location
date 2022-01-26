package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.PostPutDelKelas;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertKelasActivity extends AppCompatActivity {

	EditText kelas, matkul, jam, ruangan, dosen;
	Button btnSaveKelas;
	String userId;

	ApiInterface mApiInterface;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_kelas);

        kelas = findViewById(R.id.eKelas);
        matkul = findViewById(R.id.eMatlul);
        jam = findViewById(R.id.eJam);
        ruangan = findViewById(R.id.eRuangan);
        dosen = findViewById(R.id.eDosen);
        btnSaveKelas = findViewById(R.id.btnSaveKelas);

        userId = getIntent().getStringExtra("id");

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		btnSaveKelas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (kelas.getText().toString().equals("")
						&& matkul.getText().toString().equals("")
						&& jam.getText().toString().equals("")
						&& ruangan.getText().toString().equals("")
						&& dosen.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "semua data tidak kosong!", Toast.LENGTH_SHORT).show();
				} else {
					Call<PostPutDelKelas> call = mApiInterface.postKelas(
							userId,
							kelas.getText().toString(),
							matkul.getText().toString(),
							jam.getText().toString(),
							ruangan.getText().toString(),
							dosen.getText().toString()
					);
					call.enqueue(new Callback<PostPutDelKelas>() {
						@Override
						public void onResponse(Call<PostPutDelKelas> call, Response<PostPutDelKelas> response) {
							Toast.makeText(getApplicationContext(), "success insert new kelas", Toast.LENGTH_SHORT).show();
							KelasByAdminActivity.ka.refresh();
							finish();
							Log.d(TAG, "onResponse: " + response.message());
						}

						@Override
						public void onFailure(Call<PostPutDelKelas> call, Throwable t) {
							Log.d(TAG, "onFailure: " + t.getMessage());
						}
					});
				}
			}
		});
    }
}
