package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.StateAdapter;
import com.made_suande_1811010036.myabsensi.model.GetState;
import com.made_suande_1811010036.myabsensi.model.State;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateActivity extends AppCompatActivity {

	private ListView lvState;

	private ApiInterface mApiInterface;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        lvState = findViewById(R.id.lvState);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

//        get ekstra
		String userId = getIntent().getStringExtra("userId");
		String kelasId = getIntent().getStringExtra("kelasId");
		String pertemuanId = getIntent().getStringExtra("pertemuanId");

		Call<GetState> call = mApiInterface.getState();
		call.enqueue(new Callback<GetState>() {
			@Override
			public void onResponse(Call<GetState> call, Response<GetState> response) {
				List<State> state = response.body().getListDataState();

				StateAdapter adapter = new StateAdapter(StateActivity.this, state);
				lvState.setAdapter(adapter);

				lvState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), AbsenActivity.class);
						intent.putExtra("userId", userId);
						intent.putExtra("kelasId", kelasId);
						intent.putExtra("pertemuanId", pertemuanId);
						intent.putExtra("stateId", state.get(i).getId());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetState> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
    }
}
