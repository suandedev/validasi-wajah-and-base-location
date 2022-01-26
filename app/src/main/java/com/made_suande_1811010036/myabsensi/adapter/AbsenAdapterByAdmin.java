package com.made_suande_1811010036.myabsensi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.made_suande_1811010036.myabsensi.R;
import com.made_suande_1811010036.myabsensi.model.Absen;

import java.util.List;

public class AbsenAdapterByAdmin extends ArrayAdapter<Absen> {

	private Activity context;
	private List<Absen> absenList;

	public AbsenAdapterByAdmin(Activity context, List<Absen> absenList) {
		super(context, R.layout.single_absen_by_admin, absenList);
		this.context = context;
		this.absenList = absenList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.single_absen_by_admin, null, true);
		TextView tvNpm = (TextView) v.findViewById(R.id.npmByAdmin);
		TextView tvName = v.findViewById(R.id.namaMhsByAdmin);
		TextView tvKeterangan = v.findViewById(R.id.keteranganByAdmin);

		Absen absen = absenList.get(position);

		tvNpm.setText(absen.getNpm());
		tvName.setText(absen.getMahasiswa());
		tvKeterangan.setText(absen.getKeterangan());

		return v;
	}
}
