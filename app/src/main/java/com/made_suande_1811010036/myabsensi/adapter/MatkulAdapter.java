package com.made_suande_1811010036.myabsensi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.made_suande_1811010036.myabsensi.R;
import com.made_suande_1811010036.myabsensi.model.Matkul;

import java.util.List;

public class MatkulAdapter extends ArrayAdapter<Matkul> {

	private Activity context;
	private List<Matkul> matkulList;

	public  MatkulAdapter (Activity context, List<Matkul> matkulList) {
		super(context, R.layout.singgle_matkul_all, matkulList);
		this.context = context;
		this.matkulList = matkulList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_matkul_all, null, true);
		TextView tvMatkul = (TextView) v.findViewById(R.id.matkul);
		TextView tvDosen = v.findViewById(R.id.dosen);

		Matkul matkul = matkulList.get(position);

		tvMatkul.setText(matkul.getMatkul());
		tvDosen.setText(matkul.getDosen());
		return v;
	}
}
