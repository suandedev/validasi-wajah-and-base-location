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

public class UserByAdminActivity extends AppCompatActivity {

	ListView lvUserByAdmin;

	ApiInterface mApiInterface;

	String TAG = "mydata";

	public static UserByAdminActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_by_admin);

		lvUserByAdmin = findViewById(R.id.lvUserByAdmin);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		ma = this;
		refresh();
    }

	public void refresh() {
		lvUserByAdmin.setAdapter(null);
		Call<GetUsers> call = mApiInterface.getUserByAdmin();
		call.enqueue(new Callback<GetUsers>() {
			@Override
			public void onResponse(Call<GetUsers> call, Response<GetUsers> response) {

				List<Users> usersList = response.body().getListDataUsers();

//				Log.d(TAG, "onResponse: " + usersList.get(0).getId());

				UsersAdapter adapter = new UsersAdapter(UserByAdminActivity.this, usersList);
				lvUserByAdmin.setAdapter(adapter);

				lvUserByAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						Intent intent = new Intent(getApplicationContext(), DetailUserByAdmin.class);
						intent.putExtra("id", usersList.get(i).getId());
						intent.putExtra("email", usersList.get(i).getEmail());
						intent.putExtra("rule", usersList.get(i).getRule());
						startActivity(intent);
					}
				});
			}

			@Override
			public void onFailure(Call<GetUsers> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getMessage());
			}
		});
	}
}
