package com.example.main;

import com.example.search.searchCheckin;
import com.example.search.searchProject;
import com.example.search.searchRecord;
import com.example.start.Login;
import com.example.start.logOut;
import com.example.xm_gui.R;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Main_View extends TabActivity {
	private RadioGroup group;
	private TabHost tabHost;
	public static final String TAB_MAIN = "tabMain";
	public static final String TAB_OA = "tabOA";
	public static final String TAB_Project = "tab_project";
	public static final String TAB_Checkin = "tab_checkin";
	public static final String TAB_Record = "tab_record";
	public static Main_View instance = null;
	public static Button bt;

	private View layout;
	private boolean logout_display = false;
	private PopupWindow logoutWindow;
	private LayoutInflater inflater;
	
	Drawable dr_noraml;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_view);
		instance = this;

		// 初始化底部按钮
		group = setGroup();
		// 接收从Home传递参数，决定跳到哪个功能页面
		Bundle extra = getIntent().getExtras();
		int kind = 0;
		if (extra != null)
			kind = (Integer) extra.get("kind");
		Drawable dr = this.getResources().getDrawable(R.drawable.home_btn_bg_d);
		dr_noraml = this.getResources().getDrawable(R.drawable.home_btn_bg);
		
		switch (kind) {
		case 1:
			tabHost.setCurrentTabByTag(TAB_OA);
			bt = (Button) findViewById(R.id.radio_oa);
			bt.setBackgroundDrawable(dr);
		
			break;
		case 2:
			tabHost.setCurrentTabByTag(TAB_Project);
			bt = (Button) findViewById(R.id.radio_project);
			bt.setBackgroundDrawable(dr);
			
			break;
		case 3:
			tabHost.setCurrentTabByTag(TAB_Checkin);
			bt = (Button) findViewById(R.id.radio_checkin);
			bt.setBackgroundDrawable(dr);
			
			break;
		case 4:
			tabHost.setCurrentTabByTag(TAB_Record);
			bt = (Button) findViewById(R.id.radio_record);
			bt.setBackgroundDrawable(dr);
			
			break;
		default:
			break;
		}

	}

	// 添加下面的功能按钮,并监听切换
	private RadioGroup setGroup() {
		RadioGroup g = (RadioGroup) findViewById(R.id.main_radio);
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec(TAB_MAIN).setIndicator(TAB_MAIN)
				.setContent(new Intent(this, Home.class)));

		tabHost.addTab(tabHost.newTabSpec(TAB_OA).setIndicator(TAB_OA)
				.setContent(new Intent(this, OA_Select.class)));

		tabHost.addTab(tabHost.newTabSpec(TAB_Project)
				.setIndicator(TAB_Project)
				.setContent(new Intent(this, searchProject.class)));
		tabHost.addTab(tabHost.newTabSpec(TAB_Checkin)
				.setIndicator(TAB_Checkin)
				.setContent(new Intent(this, searchCheckin.class)));
		tabHost.addTab(tabHost.newTabSpec(TAB_Record).setIndicator(TAB_Record)
				.setContent(new Intent(this, searchRecord.class)));

		g.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
						bt.setBackgroundDrawable(dr_noraml);

				switch (checkedId) {
				case R.id.radio_oa:
					tabHost.setCurrentTabByTag(TAB_OA);
					break;
				case R.id.radio_project:
					tabHost.setCurrentTabByTag(TAB_Project);
					break;
				case R.id.radio_checkin:
					tabHost.setCurrentTabByTag(TAB_Checkin);
					break;
				case R.id.radio_record:
					tabHost.setCurrentTabByTag(TAB_Record);
					break;
				default:
					break;
				}
			}
		});

		return g;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (logout_display) { // 如果 Menu已经打开 ，先关闭Menu
				logoutWindow.dismiss();
				logout_display = false;
			} else {
				finish();
			}
		} else if (event.getKeyCode() == KeyEvent.KEYCODE_MENU
				&& event.getAction() == KeyEvent.ACTION_DOWN) { // 获取 Menu键
			if (!logout_display) {
				// 获取LayoutInflater实例
				inflater = (LayoutInflater) this
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()
				// 该方法返回的是一个View的对象，是布局中的根
				layout = inflater.inflate(R.layout.exit_logout, null);
				layout.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),
								"提示：点击窗口外部关闭窗口！", Toast.LENGTH_SHORT).show();
					}
				});
				logoutWindow = new PopupWindow(layout,
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); // 后两个参数是width和height
				logoutWindow.showAtLocation(this.findViewById(R.id.main_view),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

				layout.findViewById(R.id.main_exit);

				logout_display = true;
			} else {
				// 如果当前已经为显示状态，则隐藏起来
				logoutWindow.dismiss();
				logout_display = false;
			}

			return false;
		}
		 return super.onKeyDown(event.getKeyCode(), event);
	}

	public void exit_logouty(View v) {
		logoutWindow.dismiss();
		logout_display = false;
	//	Home.instance.finish(); // 直接这样关不了，因为Home是父，在栈底
		this.setResult(RESULT_OK);
		this.finish();
		/*
		 * Intent intent = new Intent();
		 * intent.setClass(Main_View.this,Login.class); startActivity(intent);
		 */
	}
	public void exit_logoutn(View v) {
		logoutWindow.dismiss();
		logout_display = false;
	}

}
