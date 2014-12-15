package com.example.search;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadapter.localFileAdapter;
import com.example.xm_gui.R;

import com.example.download.*;

public class fileDownloaded extends Activity {
	 ArrayList<FileUnit> localfile=new ArrayList<FileUnit>();
	 String dest=null;
	 ListView res_List=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.filedownloaded);
        
       res_List = (ListView) findViewById(R.id.downloadedlist);
		
        creatList();

	}
	public void creatList() {
		// TODO Auto-generated method stub
		 
      // ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
		 
		//���ú�������ñ����ļ����е�·����Ĭ��·�����Ļ�ã�
		String status=Environment.getExternalStorageState(); 		
	    if(status.equals(Environment.MEDIA_MOUNTED)){
			dest=Environment.getExternalStorageDirectory()+"/download/";
		}else{
			dest=this.getFilesDir()+"/download/";			
		}
	    //��ʾ�ļ�����·��
	    TextView path=(TextView) findViewById(R.id.file_path);
	    path.setText(dest);
	    
		localfile=DownFileList.getDownFileList(dest);
        System.out.println(dest);
		 
		if(localfile!=null)
		{
			for (int i = 0; i < localfile.size(); i++) {
				FileUnit ftmp=localfile.get(i);
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(ftmp.getType().equals("doc"))
					map.put("ItemImage", R.drawable.icon_text);// ͼ����Դ��ID
				else
					map.put("ItemImage", R.drawable.icon_picture);
				map.put("ItemWinName",ftmp.getName() );
				map.put("ItemCloseWin", R.drawable.delete);
				remoteWindowItem.add(map);
			}

			// ������������Item�Ͷ�̬�����Ӧ��Ԫ��
			localFileAdapter listItemAdapter = new localFileAdapter(this,
					remoteWindowItem,// ����Դ
					R.layout.listitem,// ListItem��XMLʵ��
					// ��̬������ImageItem��Ӧ������
					new String[] { "ItemImage", "ItemWinName", "ItemCloseWin" },
					// ImageItem��XML�ļ������һ��ImageView,����TextView ID
					new int[] { R.id.ItemIcon, R.id.ItemWinName, R.id.ItemCloseWin }
					,dest);
			res_List.setAdapter(listItemAdapter);
			
			/**
			 *  ���ļ�
			 */
			res_List.setOnItemClickListener(new ListView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, android.view.View arg1,
						int arg2, long arg3) {
					Log.i("mytag","open file");
					
					FileUnit ftmp = localfile.get(arg2);
					String filePath = dest + ftmp.getName();
					Log.i("mytag", filePath);
					File file = new File(filePath);
								        
			        Intent intent = new Intent("android.intent.action.VIEW");   
			        intent.addCategory("android.intent.category.DEFAULT");   
			        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			        intent.setDataAndType(Uri.fromFile(file), "application/*");
			        startActivity(intent);	
			        
		
				}
			});
		}
		else
			Toast.makeText(fileDownloaded.this, "����û�����ع��ĵ�Ŷ~~",
			Toast.LENGTH_LONG).show();
		
	}

}
