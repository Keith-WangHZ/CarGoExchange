package com.example.floatView;

import com.example.cargoexchange.R;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FloatWindowService extends Service{

	private LayoutParams mWindowParam;
	private WindowManager mWindowManager;
	private LinearLayout mFloatWindow;
	private Button mFLoatBtn;

	public FloatWindowService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		creatFloatWindow();
	}

	private void creatFloatWindow() {
		mWindowManager = (WindowManager)getApplication().getSystemService(getApplication().WINDOW_SERVICE);
		mWindowParam = new WindowManager.LayoutParams();
		mWindowParam.type = LayoutParams.TYPE_PHONE;
		mWindowParam.format = PixelFormat.RGB_888;
		mWindowParam.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		mWindowParam.gravity = Gravity.LEFT|Gravity.TOP;
		
		mWindowParam.x = 0;
		mWindowParam.y = 0;
		
		mWindowParam.width = LayoutParams.WRAP_CONTENT;
		mWindowParam.height = LayoutParams.WRAP_CONTENT;
		
		LayoutInflater layoutInflater = LayoutInflater.from(getApplication());
		mFloatWindow = (LinearLayout)layoutInflater.inflate(R.layout.float_window_layout, null);
		
		mWindowManager.addView(mFloatWindow, mWindowParam);
		
		mFLoatBtn = (Button)mFloatWindow.findViewById(R.id.floatBtn);
		
		mFloatWindow.measure(View.MeasureSpec.makeMeasureSpec(0,  
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec  
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		
		mFLoatBtn.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mWindowParam.x = (int)event.getRawX() - mFLoatBtn.getMeasuredWidth()/2;
				mWindowParam.y = (int)event.getRawY() - mFLoatBtn.getMeasuredHeight()/2 - (int)getStatusBarHeight(getApplicationContext());
				
				mWindowManager.updateViewLayout(mFloatWindow, mWindowParam);
				return false;
			}
		});
		
		mFLoatBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Hello, FloatWindow", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private double getStatusBarHeight(Context context){  
        double statusBarHeight = Math.ceil(25 * context.getResources().getDisplayMetrics().density);  
        return statusBarHeight;  
    } 
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mFloatWindow != null){
			mWindowManager.removeView(mFloatWindow);
		}
	}
	
	

}
