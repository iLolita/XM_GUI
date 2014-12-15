package com.example.search;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.example.xm_gui.R;

public class searchFile  extends TabActivity {

	private RadioGroup group;
	private TabHost tabHost;
	public static final String TAB_SEARCH = "tabSearch";
	public static final String TAB_DOWNLOADED = "tabDownloaded";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_files);

		// ----------------顶部返回按钮
		Button bc = (Button) findViewById(R.id.searchfile_back);
		bc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		group = setTopGroup();		
		
	}
	// 添加顶部的功能按钮,并监听切换
		private RadioGroup setTopGroup() {
			RadioGroup g = (RadioGroup) findViewById(R.id.top_radio);
			tabHost = getTabHost();
			tabHost.addTab(tabHost.newTabSpec(TAB_SEARCH).setIndicator(TAB_SEARCH)
					.setContent(new Intent(this, searchFileSearch.class)));
			
			tabHost.addTab(tabHost.newTabSpec(TAB_DOWNLOADED).setIndicator(TAB_DOWNLOADED)
					.setContent(new Intent(this, fileDownloaded.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
			 ((RadioButton) g.getChildAt(0)).toggle();
			g.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch (checkedId) {
					case R.id.radio_search:
						tabHost.setCurrentTabByTag(TAB_SEARCH);
						break;
					case R.id.radio_downloaded:

						tabHost.setCurrentTabByTag(TAB_DOWNLOADED);
						break;
					default:
						break;
					}
				}
			});

			return g;
		}

	
}
