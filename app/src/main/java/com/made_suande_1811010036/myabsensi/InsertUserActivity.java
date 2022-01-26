package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.made_suande_1811010036.myabsensi.model.PostPutDelUsers;
import com.made_suande_1811010036.myabsensi.rest.ApiClient;
import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertUserActivity extends AppCompatActivity {

	Button btnSaveUser;
	EditText email, password, rule;

	ApiInterface mApiInterface;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

		btnSaveUser = findViewById(R.id.btnSaveUser);
		email = findViewById(R.id.email);
		password = findViewById(R.id.password);
		rule = findViewById(R.id.rule);

		mApiInterface = ApiClient.getClient().create(ApiInterface.class);

		btnSaveUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Call<PostPutDelUsers> call = mApiInterface.insertUser(email.getText().toString(), password.getText().toString(), rule.getText().toString());
				call.enqueue(new Callback<PostPutDelUsers>() {
					@Override
					public void onResponse(Call<PostPutDelUsers> call, Response<PostPutDelUsers> response) {
						Log.d(TAG, "onResponse: oke" );
					}

					@Override
					public void onFailure(Call<PostPutDelUsers> call, Throwable t) {
						Log.d(TAG, "onFailure: " + t.getMessage());
					}
				});
				UserByAdminActivity.ma.refresh();
				finish();
				Toast.makeText(getApplicationContext(), "succes create new user", Toast.LENGTH_SHORT).show();
			}
		});
    }
}
