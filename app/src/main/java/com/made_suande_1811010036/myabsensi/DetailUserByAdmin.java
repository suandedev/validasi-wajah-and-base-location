package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.GetAbsen;
import com.made_suande_1811010036.myabsensi.model.GetUsers;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserByAdmin extends AppCompatActivity {

	Button resetPassByAdmin, btnDeleteUser;
	TextView dEmail, dRule;

	String id, email, rule;

	String TAG = "mydata";

	ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_by_admin);

		resetPassByAdmin = findViewById(R.id.resetPassByAdmin);
		btnDeleteUser = findViewById(R.id.btnDeleteUser);
		dEmail = findViewById(R.id.dEmail);
		dRule = findViewById(R.id.dRule);

		id = getIntent().getStringExtra("id");
		email = getIntent().getStringExtra("email");
		rule = getIntent().getStringExtra("rule");

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		int setRule = Integer.parseInt(rule);


		dEmail.setText("email : " + email);
		dRule.setText("email : " + rule);

		resetPassByAdmin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Call<GetUsers> call = mApiInterface.resetPasswordUser(email, email, setRule);

				call.enqueue(new Callback<GetUsers>() {
					@Override
					public void onResponse(Call<GetUsers> call, Response<GetUsers> response) {
						UserByAdminActivity.ma.refresh();
						finish();
						Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(Call<GetUsers> call, Throwable t) {
//						Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});
			}
		});

		btnDeleteUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Call<GetUsers> call = mApiInterface.deleteUser(id);
				call.enqueue(new Callback<GetUsers>() {
					@Override
					public void onResponse(Call<GetUsers> call, Response<GetUsers> response) {
						Log.d(TAG, "onResponse: oke");
					}

					@Override
					public void onFailure(Call<GetUsers> call, Throwable t) {

					}
				});
				UserByAdminActivity.ma.refresh();
				finish();
				Toast.makeText(getApplicationContext(), "oke deleted", Toast.LENGTH_SHORT).show();
			}
		});
    }
}
