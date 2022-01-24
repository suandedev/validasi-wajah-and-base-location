package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.made_suande_1811010036.myabsensi.rest.ApiInterface;

public class HomeMhsActivity extends AppCompatActivity {

	private Button btnAbsen, btnKelasByMhs, btnLogout;

	private ApiInterface mApiInterface;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mhs);

        btnAbsen = findViewById(R.id.btnAbsen);
        btnKelasByMhs = findViewById(R.id.btnKelasByMhs);
        btnLogout = findViewById(R.id.btnLogout);

//        get string ekstra
		String userId = getIntent().getStringExtra("userId");

        btnAbsen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), KelasActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

        btnKelasByMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), ListAbsenActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
				Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
				startActivity(intent);
			}
		});

    }
}
