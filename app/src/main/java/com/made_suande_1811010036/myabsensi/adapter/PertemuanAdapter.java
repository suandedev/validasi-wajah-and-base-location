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
import com.made_suande_1811010036.myabsensi.model.Pertemuan;

import java.util.List;

public class PertemuanAdapter extends ArrayAdapter<Pertemuan> {

	private Activity context;
	private List<Pertemuan> pertemuanList;

	public  PertemuanAdapter (Activity context, List<Pertemuan> pertemuanList) {
		super(context, R.layout.singgle_pertemuan, pertemuanList);
		this.context = context;
		this.pertemuanList = pertemuanList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_pertemuan, null, true);
		TextView tvPertemuan = (TextView) v.findViewById(R.id.pertemuan);

		Pertemuan pertemuan = pertemuanList.get(position);

		tvPertemuan.setText(pertemuan.getPertemuan());
		return v;
	}
}
