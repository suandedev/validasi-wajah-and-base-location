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
import com.made_suande_1811010036.myabsensi.model.Matkul;

import java.util.List;

public class KelasAdapter extends ArrayAdapter<Kelas> {

	private Activity context;
	private List<Kelas> kelasList;

	public  KelasAdapter (Activity context, List<Kelas> kelasList) {
		super(context, R.layout.singgle_kelas_by_mhs, kelasList);
		this.context = context;
		this.kelasList = kelasList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_kelas_by_mhs, null, true);
		TextView tvDosen = (TextView) v.findViewById(R.id.dosen);
		TextView tvKelas = v.findViewById(R.id.kelas);
		TextView tvMatkul = v.findViewById(R.id.matkul);

		Kelas kelas = kelasList.get(position);

		tvDosen.setText("Dosen : "+kelas.getDosen());
		tvKelas.setText("Kelas : "+kelas.getKelas());
		tvMatkul.setText("Matkul : "+kelas.getMatkul());
		return v;
	}
}
