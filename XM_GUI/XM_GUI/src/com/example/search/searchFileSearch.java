package com.example.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.download.FileUnit;
import com.example.fileInfo.FileContent;
import com.example.listadapter.lvButtonAdapter;
import com.example.main.Home;
import com.example.main.Main_View;
import com.example.xm_gui.R;

public class searchFileSearch extends ListActivity {

	private RelativeLayout layout_name;
	private RelativeLayout layout_time;
	private RelativeLayout layout_files;
	
	private TextView startTime;
	private TextView endTime;
	private int yearOfCalendar;
	private int monthOfYear;
	private int dayOfMonth;
	private static int INDEX_OF_DIALOG=0;
	 ArrayList<FileUnit> localfile=new ArrayList<FileUnit>();
	 String dest=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_files_search);


		layout_name = (RelativeLayout) findViewById(R.id.file_search_by_name);
		layout_time = (RelativeLayout) findViewById(R.id.file_search_by_time);
		layout_files = (RelativeLayout) findViewById(R.id.file_list);
		layout_name.setVisibility(View.VISIBLE);//0�ɼ���4���ɼ�(ռλ)��8gone
		layout_time.setVisibility(View.GONE);
		layout_files.setVisibility(View.GONE);

// ---------------���������ؼ�
		startTime = (TextView) findViewById(R.id.start_time);
		endTime = (TextView) findViewById(R.id.end_time);
	
		Calendar calendar = Calendar.getInstance();
		yearOfCalendar = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH)+1;
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		startTime.setText(yearOfCalendar + "-" + monthOfYear + "-" + dayOfMonth);
		endTime.setText(yearOfCalendar + "-" + monthOfYear + "-" + dayOfMonth);
		
		startTime.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
			
				// 4.��ť�����¼��������ťʱ����showDialog()������
				// �÷������Զ�����onCreateDialog()������������ʾһ��DatePickerDialog��
				INDEX_OF_DIALOG=0;
				showDialog(INDEX_OF_DIALOG);
			}			
		});
		endTime.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {INDEX_OF_DIALOG=1;
			showDialog(INDEX_OF_DIALOG);
		}
		});
		
	
// ----------------����list��ʾ��ѯ���----------------------Ԭ�ܲ���--------

		ListView res_List = (ListView) findViewById(android.R.id.list);
		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
		 
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.icon_text);// ͼ����Դ��ID
			map.put("ItemWinName", "�����б�����ס����Ϣ��ϵͳ�˾ӽ��걨");
			map.put("ItemCloseWin", R.drawable.download);
			remoteWindowItem.add(map);
		 
			map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.icon_text);// ͼ����Դ��ID
			map.put("ItemWinName", "�����й���ס�� ����1 ");
			map.put("ItemCloseWin", R.drawable.download);
			remoteWindowItem.add(map);
			
			map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.icon_text);// ͼ����Դ��ID
			map.put("ItemWinName", "�����й���ס�����빫��2 ");
			map.put("ItemCloseWin", R.drawable.download);
			remoteWindowItem.add(map);
			
		// ������������Item�Ͷ�̬�����Ӧ��Ԫ��
		lvButtonAdapter listItemAdapter = new lvButtonAdapter(this,
				remoteWindowItem,// ����Դ
				R.layout.listitem,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "ItemImage", "ItemWinName", "ItemCloseWin" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.ItemIcon, R.id.ItemWinName, R.id.ItemCloseWin });
		res_List.setAdapter(listItemAdapter);
 
//
		/**
		 *  �򿪹���
		 */
		res_List.setOnItemClickListener(new ListView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, android.view.View arg1,
					int arg2, long arg3) {
				Log.i("mytag","open file");
			/*	
				FileUnit ftmp = localfile.get(arg2);
				String filePath = dest + ftmp.getName();
				Log.i("mytag", filePath);
				File file = new File(filePath);*/
				//ͨ��ĳ�ַ�ʽ����ļ������ݣ�����ʾ
				
		        Intent intent = new Intent(searchFileSearch.this,FileContent.class);
		         startActivity(intent);	
 
			}
		});
		
		
		
//���������ѯ��ť�ļ����¼�, ������������������ʾ��ѯ�������������������������������������������������������������������������
		Button search_by_name_bt = (Button) findViewById(R.id.search_file_byname_bt);
		Button search_by_time_bt = (Button) findViewById(R.id.search_file_bytime_bt);
		search_by_time_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					layout_files.setVisibility(View.VISIBLE);
				}
			});
		search_by_name_bt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
				layout_files.setVisibility(View.VISIBLE);
			}
		});
		
	
		
		
	}

	
	//����ѡ����趨��Ӧ�ı��е�ֵ
	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int month, int day) {// �������ڱ��趨����еĴ�������Ƚϼ򵥣�ֻ�����һ���ַ�����
			if(INDEX_OF_DIALOG==0)	
				startTime.setText(year + "-" + (month+1) + "-" + day);
			else if(INDEX_OF_DIALOG==1)	
				endTime.setText(year + "-" + (month+1) + "-" + day);
		
		}
	};

	//����ѡ�����ڵĶԻ���
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			return new DatePickerDialog(this, onDateSetListener, yearOfCalendar,
					monthOfYear-1, dayOfMonth);
		case 1:
			return new DatePickerDialog(this, onDateSetListener, yearOfCalendar,
					monthOfYear-1, dayOfMonth);
		}
		return null;
	}

	
//ѡ���ѯ����
	public void show_search_way_dialog(View v){
		new AlertDialog.Builder(searchFileSearch.this)
		.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
		.setTitle("��ѡ��")
		.setSingleChoiceItems(new String[]{"��������", "ʱ�䷶Χ"},0,
				new DialogInterface.OnClickListener() {	
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (which == 0) {
							layout_name.setVisibility(View.VISIBLE);
							layout_time.setVisibility(View.GONE);
							layout_files.setVisibility(View.GONE);
						} else {
							layout_name.setVisibility(View.GONE);
							layout_time.setVisibility(View.VISIBLE);
							layout_files.setVisibility(View.GONE);
						}
					}
				}				
				)
		.setNegativeButton("ȡ��", null)
		.show();
	}
}
