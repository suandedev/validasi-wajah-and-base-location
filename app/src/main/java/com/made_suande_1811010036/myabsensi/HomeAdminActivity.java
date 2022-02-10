package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeAdminActivity extends AppCompatActivity {

	Button btnKelasByAdmin, btnTime, btnUserByAdmin, btnGetAbsenByAdmin, btnLogout;

	String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        btnKelasByAdmin = findViewById(R.id.btnKelasByAdmin);
		btnTime = findViewById(R.id.btnTime);
		btnUserByAdmin = findViewById(R.id.btnUserByAdmin);
		btnGetAbsenByAdmin = findViewById(R.id.btnGetAbsenByAdmin);
		btnLogout = findViewById(R.id.btnLogout);

		userId = getIntent().getStringExtra("userId");

        btnKelasByAdmin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), KelasByAdminActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

        btnTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), ListSetTimeActivity.class);
//				Intent intent = new Intent(getApplicationContext(), KelasActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		btnUserByAdmin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), UserByAdminActivity.class);
				startActivity(intent);
			}
		});

		btnGetAbsenByAdmin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), ListAbsenByAdminActivity.class);
				startActivity(intent);
			}
		});

		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
}
