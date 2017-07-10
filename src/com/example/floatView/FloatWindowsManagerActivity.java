package com.example.floatView;

import com.example.cargoexchange.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FloatWindowsManagerActivity extends Activity {

	private Button mShowBtn;
	private Button mHideBtn;

	public FloatWindowsManagerActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.float_window_manager);

		mShowBtn = (Button) findViewById(R.id.show);
		mHideBtn = (Button) findViewById(R.id.hide);

		if (null != mShowBtn || null != mHideBtn) {
			mShowBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(FloatWindowsManagerActivity.this, FloatWindowService.class);
					startService(intent);
					finish();
				}
			});
			mHideBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(FloatWindowsManagerActivity.this, FloatWindowService.class);
					stopService(intent);
				}
			});
		}

		/*View.OnClickListener buttonClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FloatWindowsManagerActivity.this, FloatWindowService.class);
				switch (v.getId()) {
				case R.id.show:
					startService(intent);

					break;
				case R.id.hide:
					stopService(intent);
					break;
				}
			}
		};*/
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
