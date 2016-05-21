package com.example.fragmentactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentactivity.MainActivity.OnButtonClickedListener;

public class Fragment1 extends Fragment implements OnClickListener {
	
	private TextView textView;
	private Button   button;
	private Handler  handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MainActivity activity = (MainActivity) getActivity();
		handler = activity.handler; // 得到Activity的Handler
		
		// 加监听
		activity.setButtonClickedListener(new OnButtonClickedListener() {
			@Override
			public void onclicked(String s) {
				// 显示Activity传来的内容
				textView.setText(s);
				button.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		textView 	= (TextView) getActivity().findViewById(R.id.textView1);
		button 		= (Button)   getActivity().findViewById(R.id.button2);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button2:
			// 发送指令到Activity
			handler.sendEmptyMessage(100);
			break;

		default:
			break;
		}
	}

}
