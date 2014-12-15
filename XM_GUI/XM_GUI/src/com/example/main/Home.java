package com.example.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.start.Exit;
import com.example.start.Login;
import com.example.start.logOut;
import com.example.xm_gui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Home extends Activity {
	public static Home instance = null;
    private View layout;	
	private boolean logout_display = false;
	private PopupWindow logoutWindow;
	private LayoutInflater inflater;
	
	private String tag_news="����";
	private String tag_yuqing="������Ϣ";
	private String tag_OA="OA�칫";
	private String tag_task="�������";
	private String tag_project="ҵ���ѯͳ��";
	private String tag_checkin="���ݲɼ��뱨��";
	private String tag_record="��������";
	private String tag_calendar="����";
	private String tag_message="�ڲ���Ϣ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		instance = this;
		setContentView(R.layout.grid_home);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setNumColumns(3);
		gridview.setAdapter(add_items());

		// �����������ڵ����ҳ����ת
		gridview.setOnItemClickListener(new ItemClickListener());
		
		
		Button bottom_logout= (Button) findViewById(R.id.home_logout_bt);
		bottom_logout.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// ���ð�menu������˳���¼���˳�����
				if(!logout_display){
					//��ȡLayoutInflaterʵ��
					inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
					//�÷������ص���һ��View�Ķ����ǲ����еĸ�
					layout = inflater.inflate(R.layout.exit_logout, null);
					layout.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "��ʾ����������ⲿ�رմ��ڣ�", 
									Toast.LENGTH_SHORT).show();	
						}
					});

					logoutWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //������������width��height
					logoutWindow.showAtLocation(findViewById(R.id.home), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //����layout��PopupWindow����ʾ��λ��
				 
					layout.findViewById(R.id.main_exit);
		
					logout_display = true;				
				}else{
					//�����ǰ�Ѿ�Ϊ��ʾ״̬������������
					logoutWindow.dismiss();
					logout_display = false;
					}
				
				//exit_logouty(v);
			}
		});
	}

	// oncreatʱ���������ϣ���Ӹ�����ť
	private SimpleAdapter add_items() {
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 1; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (i == 1) {
				map.put("ItemImage", R.drawable.g11);
				map.put("ItemText",tag_news);
			}
			if (i == 2) {
				map.put("ItemImage", R.drawable.g12);
				map.put("ItemText",tag_yuqing);
			}
			if (i == 3) {
				map.put("ItemImage", R.drawable.g13);
				map.put("ItemText", tag_OA);
			}
			if (i == 4) {
				map.put("ItemImage", R.drawable.g14);
				map.put("ItemText",tag_task);
			}
			if (i == 5) {
				map.put("ItemImage", R.drawable.g15);
				map.put("ItemText",tag_project);
			}
			if (i == 6) {
				map.put("ItemImage", R.drawable.g16);
				map.put("ItemText",tag_checkin);
			}
			if (i == 7) {
				map.put("ItemImage", R.drawable.g17);
				map.put("ItemText",tag_record);
			}
			if (i == 8) {
				map.put("ItemImage", R.drawable.g18);
				map.put("ItemText", tag_calendar);
			}
			if (i == 9) {
				map.put("ItemImage", R.drawable.g19);
				map.put("ItemText", tag_message);
			}
			lstImageItem.add(map);
		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.grid_item, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });

		return saImageItems;
	}

	// �����ࡣͨ�������ҳ����4����ť�����ݲ�����Main_View�У�����ת����Ӧҳ��
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
				intent.setClass(Home.this, Main_View.class);
				// ����ҳ����ѡ��OA
				if (item.get("ItemText").equals(tag_OA)) {
					intent.putExtra("kind", 1);
					startActivityForResult(intent, 0);
				}
				// ѡ�񹤳̲�ѯ
				else if (item.get("ItemText").equals(tag_project)) {
					intent.putExtra("kind", 3);
					startActivityForResult(intent, 0);
				}
				else if (item.get("ItemText").equals(tag_checkin)) {
					intent.putExtra("kind", 2);
					startActivityForResult(intent, 0);
				}
				else if (item.get("ItemText").equals(tag_record)) {
					intent.putExtra("kind", 4);
					startActivityForResult(intent, 0);
				}
				else
					 Toast.makeText(Home.this, "��δʵ��",Toast.LENGTH_SHORT).show();
				
			}
		}

//��Ϊ��Main_View �йز���Home,������result������
@Override
protected void onActivityResult(int requestCode,int resultCode,Intent data){
	if(requestCode==0 && resultCode==RESULT_OK)
		finish();
}
	
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(logout_display){         //��� Menu�Ѿ��� ���ȹر�Menu
        		logoutWindow.dismiss();
        		logout_display = false;
        		}
        	else {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "�ٰ�һ�η��ؼ��˳�����", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
	
				} else {
					finish();
					Login.instance.finish();
				}
				return true;
			}
		}
		else if(keyCode == KeyEvent.KEYCODE_MENU){   //��ȡ Menu��			
			if(!logout_display){
				//��ȡLayoutInflaterʵ��
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				//�÷������ص���һ��View�Ķ����ǲ����еĸ�
				layout = inflater.inflate(R.layout.exit_logout, null);
				layout.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "��ʾ����������ⲿ�رմ��ڣ�", 
								Toast.LENGTH_SHORT).show();	
					}
				});

				logoutWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //������������width��height
				//menuWindow.showAsDropDown(layout); //���õ���Ч��
				//menuWindow.showAsDropDown(null, 0, layout.getHeight());
				logoutWindow.showAtLocation(this.findViewById(R.id.home), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //����layout��PopupWindow����ʾ��λ��
				 
				layout.findViewById(R.id.main_exit);
	
				logout_display = true;				
			}else{
				//�����ǰ�Ѿ�Ϊ��ʾ״̬������������
				logoutWindow.dismiss();
				logout_display = false;
				}
			
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	
	public void exit_logouty(View v) {
		this.finish();
		logout_display = false;
		Home.instance.finish();
	}
	public void exit_logoutn(View v) {
		logoutWindow.dismiss();
		logout_display = false;
	}
	
	

}
