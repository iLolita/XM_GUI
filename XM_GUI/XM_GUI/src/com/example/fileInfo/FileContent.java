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
		
		
		//�ڹ������ݵ����Ͻǣ���ʾ����ҳ��İ�ť
		Button download_file = (Button) findViewById(R.id.download_file);
		download_file.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url="http://61.166.249.130:1303/Upload/ceshi001.doc";
	        	String filename=url.substring(url.lastIndexOf("/")+1);
	         
	        	Log.v("�����߳�", "���ؿ�ʼ");
	        	try{
	        		Intent intent = new Intent("com.example.download.DownLoadService");
	        		intent.putExtra("filename", filename);
	        		getApplicationContext().startService(intent);
	        	}catch(Exception e){
	        		Log.v("����", e.getMessage()+" ");
	        	}

				}
			});
		// �������

		ListView res_List = (ListView) findViewById(R.id.file_info_list);
		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Attribute", "�������֣�");
		map.put("AttributeValue", "����");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "�����ĺţ�");
		map.put("AttributeValue", "��ס��[2013]78��");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("Attribute", "�������壺");
		map.put("AttributeValue", "������ס�����Ͼ�");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "�����ܼ���");
		map.put("AttributeValue", "һ��");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "���ı��⣺");
		map.put("AttributeValue", "�����б�����ס����Ϣ��ϵͳ�˾ӽ��걨");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "����ʣ�");
		map.put("AttributeValue", "����Ϣ���ĸ�Ч����ʵ��ס�����ӵ�����");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "���͵�λ��");
		map.put("AttributeValue", "�����й��з��ݹ�������");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "���͵�λ��");
		map.put("AttributeValue", " ");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "�ֳ���ʾ��");
		map.put("AttributeValue", " ");
		remoteWindowItem.add(map);
	
				infoAdapter listItemAdapter = new infoAdapter(this,
						remoteWindowItem,// ����Դ
						R.layout.listitem_information,// ListItem��XMLʵ��
						new String[] { "Attribute", "AttributeValue"},					
						new int[] { R.id.Attribute, R.id.AttributeValue});
				res_List.setAdapter(listItemAdapter);
	}
}
