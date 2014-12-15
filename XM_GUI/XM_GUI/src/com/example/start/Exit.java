package com.example.start;

import com.example.main.Home;
import com.example.main.Main_View;
import com.example.xm_gui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Exit extends Activity {
	
	private LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exit_dialog);
		
		layout=(LinearLayout)findViewById(R.id.exit_layout);
		layout.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
						Toast.LENGTH_SHORT).show();	
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
	
	//这两个方法，对应按钮的xml定义时给定了
	public void exitbutton_n(View v) {  
    	this.finish();    	
      }  
	public void exitbutton_y(View v) {  
    	this.finish();
    	Home.instance.finish();//关闭Main 这个Activity
    	Main_View.instance.finish();
      }  
	
}
