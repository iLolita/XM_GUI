package com.example.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.search.fileDownloaded;
import com.example.search.fileDownloaded_withtitle;
import com.example.search.searchFile;
import com.example.search.searchFileflow;
import com.example.search.searchFilejob;
import com.example.search.searchFiletag;
import com.example.xm_gui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OA_Select extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_oa);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setNumColumns(2);

		gridview.setAdapter(add_items());

		// 监听器，用于点击后，页面跳转
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	// 在主界面上，添加各个按钮
	private SimpleAdapter add_items() {
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 1; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (i == 1) {
				map.put("ItemImage", R.drawable.g21);
				map.put("ItemText", getResources()
						.getString(R.string.gridview1));
			}
			if (i == 2) {
				map.put("ItemImage", R.drawable.g22);
				map.put("ItemText", getResources()
						.getString(R.string.gridview2));
			}
			
			if (i == 3) {
				map.put("ItemImage", R.drawable.g24);
				map.put("ItemText", getResources()
						.getString(R.string.gridview4));
			}
			if (i == 4) {
				map.put("ItemImage", R.drawable.g25);
				map.put("ItemText", getResources()
						.getString(R.string.gridview5));
			}
			lstImageItem.add(map);
		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.grid_item, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });

		return saImageItems;
	}

	// 监听类
	class ItemClickListener implements OnItemClickListener {
		@SuppressWarnings("unchecked")
		public void onItemClick(AdapterView<?> arg0,// The AdapterView where the
													// click happened
				View arg1,// The view within the AdapterView that was clicked
				int arg2,// The position of the view in the adapter
				long arg3// The row id of the item that was clicked
		) {

			HashMap<String, Object> item = (HashMap<String, Object>) arg0
					.getItemAtPosition(arg2);
			Intent intent = new Intent();

			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview1))) {
				intent.setClass(OA_Select.this, searchFile.class);

			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview2))) {
				intent.setClass(OA_Select.this, searchFileflow.class);
			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview3))) {
				intent.setClass(OA_Select.this, searchFilejob.class);

			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview4))) {
				intent.setClass(OA_Select.this, searchFiletag.class);

			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview5))) {
				// Toast.makeText(OA_Select.this, R.string.gridview5,
				// Toast.LENGTH_SHORT).show();
				intent.setClass(OA_Select.this, fileDownloaded_withtitle.class);
			}
			startActivity(intent);

		}
	}

}
