package com.made_suande_1811010036.myabsensi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.made_suande_1811010036.myabsensi.R;
import com.made_suande_1811010036.myabsensi.model.SetLocation;
import com.made_suande_1811010036.myabsensi.model.State;

import java.util.List;

public class SetLocationAdapter extends ArrayAdapter<SetLocation> {

	private Activity context;
	private List<SetLocation> setLocationList;

	public SetLocationAdapter(Activity context, List<SetLocation> setLocationList) {
		super(context, R.layout.singgle_set_location, setLocationList);
		this.context = context;
		this.setLocationList = setLocationList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_set_location, null, true);
		TextView tvKelas = (TextView) v.findViewById(R.id.kelasByDosen);
		TextView tvMatkul = (TextView) v.findViewById(R.id.matkulByDosen);
		TextView tvLatitude = (TextView) v.findViewById(R.id.latitudeByDosen);
		TextView tvLongtitude = (TextView) v.findViewById(R.id.longtitudeByDosen);

		SetLocation setLocation = setLocationList.get(position);

		tvKelas.setText("kelas : "+setLocation.getKelas());
		tvMatkul.setText("mata kuliah : "+setLocation.getMatkul());
		tvLatitude.setText("latitude : "+setLocation.getLatitude());
		tvLongtitude.setText("longtitude : "+setLocation.getLongtitude());

		return v;
	}
}
