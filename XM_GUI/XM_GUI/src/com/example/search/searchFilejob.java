package com.example.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.xm_gui.R;

public class searchFilejob  extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_filejob);
        
        //¶¥²¿·µ»Ø°´Å¥
        Button bc=(Button)findViewById(R.id.search_filejob_back);
        bc.setOnClickListener(new OnClickListener() {   
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
	}
}
