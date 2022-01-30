package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.PostPutDelSetTime;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSetTimeActivity extends AppCompatActivity {

	String id, matkul, kelas, pertemuan, state , paramIn, paramOut, date;

	public String fdateIn, fdateOut;

	String TAG = "mydata";

	TextView dtmatkul, dtkelas, dtpertemuan, dtstate, dtparamIn, dtparamOut;
	Button btnDeleteSetTime;

	ApiInterface mApiInterface;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_set_time);

		dtmatkul = findViewById(R.id.dtmatkul);
		dtkelas = findViewById(R.id.dtkelas);
		dtpertemuan = findViewById(R.id.dtpertemuan);
		dtstate = findViewById(R.id.dtstate);
		dtparamIn = findViewById(R.id.dtparamIn);
		dtparamOut = findViewById(R.id.dtparamOut);
		btnDeleteSetTime = findViewById(R.id.btnDeleteSetTime);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		id  = getIntent().getStringExtra("id");
		matkul  = getIntent().getStringExtra("matkul");
		kelas  = getIntent().getStringExtra("kelas");
		pertemuan  = getIntent().getStringExtra("pertemuan");
		state  = getIntent().getStringExtra("state");
		paramIn  = getIntent().getStringExtra("paramIn");
		paramOut  = getIntent().getStringExtra("paramOut");

		convertTime();
		
		dtmatkul.setText(" matkul : " + matkul);
		dtkelas.setText(" kelas : " + kelas);
		dtpertemuan.setText(" pertemuan : " + pertemuan);
		dtstate.setText(" state : " + state);
		dtparamIn.setText(" waktu dimulai : " + fdateIn);
		dtparamOut.setText(" waktu berakhir : " + fdateOut);

		btnDeleteSetTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				deleteSetTime();
			}
		});
    }

	private void convertTime() {

		long unixSecondsIn = Long.parseLong(paramIn);
		long unixSecondsOut = Long.parseLong(paramOut);

		Date dateIn = new java.util.Date(unixSecondsIn*1000L);
		Date dateOut = new java.util.Date(unixSecondsOut*1000L);
// the format of your date
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// give a timezone reference for formatting (see comment at the bottom)
//		sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC+7"));
		fdateIn = sdf.format(dateIn);
		fdateOut = sdf.format(dateOut);
	}

	private void deleteSetTime() {

		Call<PostPutDelSetTime> call = mApiInterface.deleteTime(id);
		call.enqueue(new Callback<PostPutDelSetTime>() {
			@Override
			public void onResponse(Call<PostPutDelSetTime> call, Response<PostPutDelSetTime> response) {

			}

			@Override
			public void onFailure(Call<PostPutDelSetTime> call, Throwable t) {

			}
		});
		Toast.makeText(getApplicationContext(), "deleted successfully", Toast.LENGTH_SHORT).show();
		finish();
		ListSetTimeActivity.lta.getListSetTime();
	}
}
