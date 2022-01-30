package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.SetTimeAdapter;
import com.made_suande_1811010036.myabsensi.model.GetSetTime;
import com.made_suande_1811010036.myabsensi.model.SetTime;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSetTimeActivity extends AppCompatActivity {

	ListView lvSetTime;

	ApiInterface mApiInterface;

	public static ListSetTimeActivity lta;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_set_time);

        lvSetTime = findViewById(R.id.lvSetTime);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        lta = this;

        getListSetTime();
    }

	public void getListSetTime() {
		Call<GetSetTime> call = mApiInterface.getSetTime();
		call.enqueue(new Callback<GetSetTime>() {
			@Override
			public void onResponse(Call<GetSetTime> call, Response<GetSetTime> response) {

				List<SetTime> setTimeList = response.body().getListDataSetTime();

				SetTimeAdapter adapter = new SetTimeAdapter(ListSetTimeActivity.this, setTimeList);
				lvSetTime.setAdapter(adapter);

				lvSetTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), DetailSetTimeActivity.class);
						intent.putExtra("id", setTimeList.get(i).getId());
						intent.putExtra("matkul", setTimeList.get(i).getMatkul());
						intent.putExtra("kelas", setTimeList.get(i).getKelas());
						intent.putExtra("pertemuan", setTimeList.get(i).getPertemuan());
						intent.putExtra("state", setTimeList.get(i).getState());
						intent.putExtra("paramIn", setTimeList.get(i).getParamIn());
						intent.putExtra("paramOut", setTimeList.get(i).getParamOut());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetSetTime> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
