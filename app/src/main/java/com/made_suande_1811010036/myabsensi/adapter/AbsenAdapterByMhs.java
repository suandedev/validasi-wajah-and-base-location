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
import com.made_suande_1811010036.myabsensi.model.Matkul;

import java.util.List;

public class AbsenAdapterByMhs extends ArrayAdapter<Absen> {

	private Activity context;
	private List<Absen> absenList;

	public AbsenAdapterByMhs(Activity context, List<Absen> absenList) {
		super(context, R.layout.single_absen_by_mhs, absenList);
		this.context = context;
		this.absenList = absenList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.single_absen_by_mhs, null, true);
		TextView tvNpm = (TextView) v.findViewById(R.id.npm);
		TextView tvName = v.findViewById(R.id.nama);
		TextView tvKeterangan = v.findViewById(R.id.keterangan);

		Absen absen = absenList.get(position);

		tvNpm.setText("NPM : "+absen.getNpm());
		tvName.setText("Nama : "+absen.getMahasiswa());
		tvKeterangan.setText("Keterangan : "+absen.getKeterangan());

		return v;
	}
}
