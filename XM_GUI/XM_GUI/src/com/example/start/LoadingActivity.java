package com.example.start;

import com.example.main.Home;
import com.example.xm_gui.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class LoadingActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.loading);
			
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			 Intent intent = new Intent();
             intent.setClass(LoadingActivity.this, Home.class);
             startActivity(intent);
			LoadingActivity.this.finish();
			Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
		}
	}, 200);
   }
}