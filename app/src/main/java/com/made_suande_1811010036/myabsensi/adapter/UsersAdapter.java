package com.made_suande_1811010036.myabsensi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.made_suande_1811010036.myabsensi.R;
import com.made_suande_1811010036.myabsensi.model.Kelas;
import com.made_suande_1811010036.myabsensi.model.Users;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<Users> {

	private Activity context;
	private List<Users> usersList;

	public  UsersAdapter (Activity context, List<Users> usersList) {
		super(context, R.layout.singgle_users, usersList);
		this.context = context;
		this.usersList = usersList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_users, null, true);
		TextView tvEmail = (TextView) v.findViewById(R.id.sEmail);
		TextView tvPass = v.findViewById(R.id.sPassword);
		TextView tvRule = v.findViewById(R.id.sRule);

		Users users = usersList.get(position);

		tvEmail.setText(users.getEmail());
		tvPass.setText(users.getPassword());
		tvRule.setText(users.getRule());
		return v;
	}
}
