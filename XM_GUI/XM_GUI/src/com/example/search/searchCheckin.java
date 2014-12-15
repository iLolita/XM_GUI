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
		// 顶部返回按钮
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
 
 
		// 填充内容

		ListView res_List = (ListView) findViewById(R.id.user_info_list);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Attribute", "姓名：");
		map.put("AttributeValue", "伍兴力");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "区县：");
		map.put("AttributeValue", "盘龙区");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("Attribute", "街道办：");
		map.put("AttributeValue", "青云街道");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "身份证号：");
		map.put("AttributeValue", "530111197810180016");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "住房保障号：");
		map.put("AttributeValue", "YNKMBZ10302202312");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "手机号：");
		map.put("AttributeValue", "1898714538");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "家庭人数：");
		map.put("AttributeValue", "3");
		remoteWindowItem.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "保障类型：");
		map.put("AttributeValue", "公共租赁住房");
		remoteWindowItem.add(map);
		
		ListView res_List2 = (ListView) findViewById(R.id.house_info_list);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> HouseInfo = new ArrayList<HashMap<String, Object>>();
		map = new HashMap<String, Object>();
		map.put("Attribute", "房屋编号：");
		map.put("AttributeValue", "1-0301");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "所属项目：");
		map.put("AttributeValue", "泽惠园");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "所属楼盘：");
		map.put("AttributeValue", "泽惠园");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "楼栋名称：");
		map.put("AttributeValue", "1栋");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "单元名称：");
		map.put("AttributeValue", "1单元");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "房屋状态：");
		map.put("AttributeValue", "已出租");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "户型：");
		map.put("AttributeValue", "二室一厅(D1)");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "建筑面积：");
		map.put("AttributeValue", "57.28平方米");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "套内面积：");
		map.put("AttributeValue", "41.07平方米");
		HouseInfo.add(map);map = new HashMap<String, Object>();
		map.put("Attribute", "分摊面积：");
		map.put("AttributeValue", "16.21平方米");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "房屋坐落：");
		map.put("AttributeValue", "坐北朝南");
		HouseInfo.add(map);
		map = new HashMap<String, Object>();
		map.put("Attribute", "租金：");
		map.put("AttributeValue", "17.00元/平方米");
		HouseInfo.add(map);
		
		map = new HashMap<String, Object>();
		map.put("Attribute", "备注：");
		map.put("AttributeValue", " ");
		HouseInfo.add(map);
	
		//两个list分别有适配器，仅用于显示不需要适配器的吧
		infoAdapter listItemAdapter = new infoAdapter(this,
				remoteWindowItem,// 数据源
				R.layout.listitem_information,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "Attribute", "AttributeValue"},
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.Attribute, R.id.AttributeValue});
		res_List.setAdapter(listItemAdapter);
		
		infoAdapter listItemAdapter2 = new infoAdapter(this,
				HouseInfo,// 数据源
				R.layout.listitem_information,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "Attribute", "AttributeValue"},
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.Attribute, R.id.AttributeValue});
		res_List2.setAdapter(listItemAdapter2);
		
 
//点击查询按钮，显示结果
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
	// 添加顶部的功能按钮,并监听切换
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
