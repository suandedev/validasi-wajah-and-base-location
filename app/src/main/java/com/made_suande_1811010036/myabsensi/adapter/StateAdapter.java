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
import com.made_suande_1811010036.myabsensi.model.State;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

	private Activity context;
	private List<State> stateList;

	public StateAdapter(Activity context, List<State> stateList) {
		super(context, R.layout.singgle_state, stateList);
		this.context = context;
		this.stateList = stateList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View v = inflater.inflate(R.layout.singgle_state, null, true);
		TextView tvState = (TextView) v.findViewById(R.id.state1);

		State state = stateList.get(position);

		tvState.setText(state.getState());

		return v;
	}
}
