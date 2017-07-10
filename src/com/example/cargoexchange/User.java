package com.example.cargoexchange;

import com.example.floatView.FloatWindowsManagerActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User extends Activity {
    private Button mReturnButton;
	private Button mFloatWindowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        mReturnButton = (Button)findViewById(R.id.returnback);
        
        mFloatWindowBtn = (Button)findViewById(R.id.floatWindow);

    }

	public void back_to_login(View view) {
		Intent intent3 = new Intent(User.this, Login.class);
		startActivity(intent3);
		finish();

	}

	public void showFloatWindow(View view) {
		Intent intent4 = new Intent(User.this, FloatWindowsManagerActivity.class);
		startActivity(intent4);
		finish();
	}
}
