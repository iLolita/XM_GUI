package com.example.fileInfo;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.listadapter.infoAdapter;
import com.example.xm_gui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class FileContent extends Activity {
	 private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.filecontent);
		
		
		//在公文内容的右上角，显示下载页面的按钮
		Button download_file = (Button) findViewById(R.id.download_file);
		download_file.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url="http://61.166.249.130:1303/Upload/ceshi001.doc";
	        	String filename=url.substring(url.lastIndexOf("/")+1);
	         
	        	Log.v("下载线程", "下载开始");
	        	try{
	        		Intent intent = new Intent("com.example.download.DownLoadService");
	        		intent.putExtra("filename", filename);
	        		getApplicationContext().startService(intent);
	        	}catch(Exception e){
	        		Log.v("错误", e.getMessage()+" ");
	        	}

				}
			});
		// 填充内容

		ListView res_List = (ListView) findViewById(R.id.file_info_list);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Attribute", "发文文种：");
		map.put("AttributeValue", "公文");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "发文文号：");
		map.put("AttributeValue", "昆住保[2013]78号");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("Attribute", "发文文体：");
		map.put("AttributeValue", "昆明市住房保障局");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "公文密级：");
		map.put("AttributeValue", "一般");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "公文标题：");
		map.put("AttributeValue", "昆明市保障性住房信息化系统人居奖申报");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "主题词：");
		map.put("AttributeValue", "用信息化的高效管理实现住有所居的梦想");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "主送单位：");
		map.put("AttributeValue", "昆明市公有房屋管理中心");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "抄送单位：");
		map.put("AttributeValue", " ");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "局长批示：");
		map.put("AttributeValue", " ");
		remoteWindowItem.add(map);
	
				infoAdapter listItemAdapter = new infoAdapter(this,
						remoteWindowItem,// 数据源
						R.layout.listitem_information,// ListItem的XML实现
						new String[] { "Attribute", "AttributeValue"},					
						new int[] { R.id.Attribute, R.id.AttributeValue});
				res_List.setAdapter(listItemAdapter);
	}
}
