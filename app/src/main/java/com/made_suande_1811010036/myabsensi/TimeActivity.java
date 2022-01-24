package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.PostPutDelSetTime;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeActivity extends AppCompatActivity {

	Button btnSaveTime;

	TimePicker timePickerIn, timePickerOut;
	DatePicker datePikerIn, datePikerOut;

	String TAG = "mydata";
	String kelasId, pertemuanId, stateId;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);


		btnSaveTime = findViewById(R.id.btnSaveTime);

		timePickerIn = findViewById(R.id.timePikerIn);
		datePikerIn = findViewById(R.id.datePikerIn);

		timePickerOut = findViewById(R.id.timePikerOut);
		datePikerOut = findViewById(R.id.datePikerOut);

		timePickerIn.setIs24HourView(true);
		timePickerOut.setIs24HourView(true);

		kelasId = getIntent().getStringExtra("kelasId");
		pertemuanId = getIntent().getStringExtra("pertemuanId");
		stateId = getIntent().getStringExtra("stateId");

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		btnSaveTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Call<PostPutDelSetTime> call = mApiInterface.postSetTime(
						Integer.valueOf(kelasId),
						Integer.valueOf(pertemuanId),
						Integer.valueOf(stateId),
						datePikerIn.getDayOfMonth(),
						datePikerIn.getMonth()+1,
						datePikerIn.getYear(),
						timePickerIn.getHour(),
						timePickerIn.getMinute(),
						datePikerOut.getDayOfMonth(),
						datePikerOut.getMonth()+1,
						datePikerOut.getYear(),
						timePickerOut.getHour(),
						timePickerOut.getMinute()
				);
				call.enqueue(new Callback<PostPutDelSetTime>() {
					@Override
					public void onResponse(Call<PostPutDelSetTime> call, Response<PostPutDelSetTime> response) {
						Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(Call<PostPutDelSetTime> call, Throwable t) {
						Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});
				Log.d(TAG, "onClick: time in" + timePickerIn.getHour() + " " +timePickerIn.getMinute());
				Log.d(TAG, "onClick: date in" + datePikerIn.getDayOfMonth() + " " + datePikerIn.getMonth() + 1 + " " + datePikerIn.getYear());
				Log.d(TAG, "onClick: time out" + timePickerOut.getHour() + " " + timePickerOut.getMinute());
				Log.d(TAG, "onClick: date out" + datePikerOut.getDayOfMonth() + " " + datePikerOut.getMonth() + 1 + " " + datePikerOut.getYear());
			}
		});
    }
}

