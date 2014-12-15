package com.example.search;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listadapter.lvButtonAdapter;
import com.example.xm_gui.R;

public class searchRecord extends ListActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_record);
        
        //¶¥²¿·µ»Ø°´Å¥
        Button bc=(Button)findViewById(R.id.search_record_back);
        bc.setOnClickListener(new OnClickListener() {   
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
	}
    
}

