package com.made_suande_1811010036.myabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeDosenActivity extends AppCompatActivity {

	Button btnGetAbsenByDosen, btnLogout;

	String TAG = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosen);

        btnGetAbsenByDosen = findViewById(R.id.btnGetAbsenByDosen);
        btnLogout = findViewById(R.id.btnLogout);

//        get string eksra
		String userId = getIntent().getStringExtra("userId");


		btnGetAbsenByDosen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), KelasByDosenActivity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
    }
}
