package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.made_suande_1811010036.myabsensi.adapter.UsersAdapter;
import com.made_suande_1811010036.myabsensi.model.GetUsers;
import com.made_suande_1811010036.myabsensi.model.Users;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectUserActivity extends AppCompatActivity {

	ListView lvSelectUser;

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

		lvSelectUser = findViewById(R.id.lvSelectUser);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		Call<GetUsers> call = mApiInterface.getUsers();
		call.enqueue(new Callback<GetUsers>() {
			@Override
			public void onResponse(Call<GetUsers> call, Response<GetUsers> response) {
				List<Users> usersList = response.body().getListDataUsers();

				UsersAdapter adapter = new UsersAdapter(SelectUserActivity.this, usersList);
				lvSelectUser.setAdapter(adapter);

				lvSelectUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), InsertKelasActivity.class);
						intent.putExtra("id", usersList.get(i).getId());
						startActivity(intent);
						finish();
					}
				});
			}

			@Override
			public void onFailure(Call<GetUsers> call, Throwable t) {
				Log.d("mydata", "onFailure: " + t.getMessage());
			}
		});
    }
}
