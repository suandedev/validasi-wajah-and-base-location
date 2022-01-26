package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.GetMatkul;
import com.made_suande_1811010036.myabsensi.model.GetUsers;
import com.made_suande_1811010036.myabsensi.model.Users;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

	private Button btnLogin;
	private EditText getEmail, getPassword;

	private ApiInterface mApiInterface;

	String TAG = "mydata";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);

		btnLogin = findViewById(R.id.login);
		getEmail = findViewById(R.id.email);
		getPassword = findViewById(R.id.password);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Call<GetUsers> call = mApiInterface.getUsers();
				call.enqueue(new Callback<GetUsers>() {
					@Override
					public void onResponse(Call<GetUsers> call, Response<GetUsers> response) {
						List<Users> usersList = response.body().getListDataUsers();

						for (Users user : usersList) {
//							String email = getEmail.getText().toString();
//							String password = getPassword.getText().toString();
							String email = "admin@gmail.com";
							String password = "123";
							if (email.equals(user.getEmail())) {
								if (password.equals(user.getPassword())) {
									if (user.getRule().equals("1")) {
										Intent intent = new Intent(getApplicationContext(), HomeAdminActivity.class);
										intent.putExtra("userId", user.getId());
										startActivity(intent);
										finish();
										Log.d("mydata", "onResponse: " + user.getId());
									}
									if (user.getRule().equals("2")) {
										Intent intent = new Intent(getApplicationContext(), HomeDosenActivity.class);
										intent.putExtra("userId", user.getId());
										startActivity(intent);
										finish();
										Log.d("mydata", "onResponse: " + user.getRule());
									}
									if (user.getRule().equals("3")) {
										Intent intent = new Intent(getApplicationContext(), HomeMhsActivity.class);
										intent.putExtra("userId", user.getId());
										startActivity(intent);
										finish();
										Log.d("mydata", "onResponse: " + user.getRule());
									}
								}
							} else {
								Toast.makeText(getApplicationContext(), "email or password incorrect", Toast.LENGTH_SHORT).show();
							}

						}

//						Log.d("mydata", "onResponse: " + usersList);
					}

					@Override
					public void onFailure(Call<GetUsers> call, Throwable t) {
						Log.d("mydata", "onFailure: " + t.getMessage());
					}
				});
			}
		});
	}
}
