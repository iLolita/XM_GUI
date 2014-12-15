package com.example.search;

import java.util.ArrayList;
import java.util.HashMap;
 
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.listadapter.infoAdapter;
import com.example.listadapter.lvButtonAdapter;
import com.example.main.Home;
import com.example.start.Login;
import com.example.xm_gui.R;

public class searchCheckin  extends  Activity {

	private RelativeLayout layout_user;
	private RelativeLayout layout_house;
	private RelativeLayout layout_radiogroup;
	private RadioGroup group;
 
	public static final String TAB_PERSON = "tabPerson";
	public static final String TAB_HOUSE = "tabHouse";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_checkin);
		// �������ذ�ť
		Button bc = (Button) findViewById(R.id.checkin_top_back);
		bc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		group = setTopGroup();	


		layout_user = (RelativeLayout) findViewById(R.id.user_info);
		layout_house = (RelativeLayout) findViewById(R.id.house_info);	
		layout_radiogroup = (RelativeLayout) findViewById(R.id.checkin_radio_layout);	
		layout_user.setVisibility(View.GONE);
		layout_house.setVisibility(View.GONE);
		layout_radiogroup.setVisibility(View.GONE);
 
 
		// �������

		ListView res_List = (ListView) findViewById(R.id.user_info_list);
		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Attribute", "������");
		map.put("AttributeValue", "������");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "���أ�");
		map.put("AttributeValue", "������");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("Attribute", "�ֵ��죺");
		map.put("AttributeValue", "���ƽֵ�");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "���֤�ţ�");
		map.put("AttributeValue", "530111197810180016");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "ס�����Ϻţ�");
		map.put("AttributeValue", "YNKMBZ10302202312");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "�ֻ��ţ�");
		map.put("AttributeValue", "1898714538");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "��ͥ������");
		map.put("AttributeValue", "3");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "�������ͣ�");
		map.put("AttributeValue", "��������ס��");
		remoteWindowItem.add(map);
		
		ListView res_List2 = (ListView) findViewById(R.id.house_info_list);
		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> HouseInfo = new ArrayList<HashMap<String, Object>>();
		map = new HashMap<String, Object>();
		map.put("Attribute", "���ݱ�ţ�");
		map.put("AttributeValue", "1-0301");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "������Ŀ��");
		map.put("AttributeValue", "���԰");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "����¥�̣�");
		map.put("AttributeValue", "���԰");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "¥�����ƣ�");
		map.put("AttributeValue", "1��");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "��Ԫ���ƣ�");
		map.put("AttributeValue", "1��Ԫ");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "����״̬��");
		map.put("AttributeValue", "�ѳ���");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "���ͣ�");
		map.put("AttributeValue", "����һ��(D1)");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "���������");
		map.put("AttributeValue", "57.28ƽ����");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "���������");
		map.put("AttributeValue", "41.07ƽ����");
		HouseInfo.add(map);map = new HashMap<String, Object>();
		map.put("Attribute", "��̯�����");
		map.put("AttributeValue", "16.21ƽ����");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "�������䣺");
		map.put("AttributeValue", "��������");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "���");
		map.put("AttributeValue", "17.00Ԫ/ƽ����");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "��ע��");
		map.put("AttributeValue", " ");
		HouseInfo.add(map);
	
		//����list�ֱ�������������������ʾ����Ҫ�������İ�
		infoAdapter listItemAdapter = new infoAdapter(this,
				remoteWindowItem,// ����Դ
				R.layout.listitem_information,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "Attribute", "AttributeValue"},
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.Attribute, R.id.AttributeValue});
		res_List.setAdapter(listItemAdapter);
		
		infoAdapter listItemAdapter2 = new infoAdapter(this,
				HouseInfo,// ����Դ
				R.layout.listitem_information,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "Attribute", "AttributeValue"},
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.Attribute, R.id.AttributeValue});
		res_List2.setAdapter(listItemAdapter2);
		
 
//�����ѯ��ť����ʾ���
		Button search_checkin_bt = (Button) findViewById(R.id.checkin_search_bt);
		search_checkin_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 layout_radiogroup.setVisibility(View.VISIBLE);
				 layout_user.setVisibility(View.VISIBLE);
				 layout_house.setVisibility(View.INVISIBLE);
			}
		});
		
		
	}
	// ��Ӷ����Ĺ��ܰ�ť,�������л�
			private RadioGroup setTopGroup() {
				RadioGroup g = (RadioGroup) findViewById(R.id.check_radio);	 
		 
				 ((RadioButton) g.getChildAt(0)).toggle();
				g.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.radio_person:
							layout_user.setVisibility(View.VISIBLE);
							layout_house.setVisibility(View.INVISIBLE);
							break;
						case R.id.radio_house:
							layout_user.setVisibility(View.INVISIBLE);
							layout_house.setVisibility(View.VISIBLE);
							break;
						default:
							break;
						}
					}
				});

				return g;
			}

}
