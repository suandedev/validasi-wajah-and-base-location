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
import com.made_suande_1811010036.myabsensi.model.SetTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SetTimeAdapter extends ArrayAdapter<SetTime> {

	private Activity context;
	private List<SetTime> setTimeList;

	public SetTimeAdapter(Activity context, List<SetTime> setTimeList) {
		super(context, R.layout.singgle_set_time, setTimeList);
		this.context = context;
		this.setTimeList = setTimeList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_set_time, null, true);
		TextView tvMatkul = (TextView) v.findViewById(R.id.smatkul);
		TextView tvKelas = (TextView) v.findViewById(R.id.skelas);
		TextView tvPertemuan = (TextView) v.findViewById(R.id.spertemuan);
		TextView tvState = (TextView) v.findViewById(R.id.sstate);
		TextView tvParamIn = (TextView) v.findViewById(R.id.sparamIn);

		SetTime setTime = setTimeList.get(position);

		long unixSecondsIn = Long.parseLong(setTime.getParamIn());

		Date dateIn = new java.util.Date(unixSecondsIn*1000L);
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fdateIn = sdf.format(dateIn);

		tvMatkul.setText("mata kuliah : "+setTime.getMatkul());
		tvKelas.setText("kelas : "+setTime.getKelas());
		tvPertemuan.setText("pertemuan : "+setTime.getPertemuan());
		tvState.setText("state : "+setTime.getState());
		tvParamIn.setText("param in : "+fdateIn);

		return v;
	}
}
