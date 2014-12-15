package com.example.search;

import junit.framework.TestFailure;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

import com.example.start.Login;
import com.example.xm_gui.R;

public class searchProject extends Activity {
	private Button searchButton;
	private RelativeLayout relativeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_project);

		// �������ذ�ť
		Button bc = (Button) findViewById(R.id.search_project_back);
		searchButton = (Button) findViewById(R.id.proj_search_bt);
		relativeLayout = (RelativeLayout) findViewById(R.id.relative1);

		bc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		EditText project_name = (EditText) findViewById(R.id.proj_search_text);
		Log.v("mytag",project_name.getText().toString());
 
        
        
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				relativeLayout.setVisibility(View.VISIBLE);

				String projectName, projectStatus, projectFund, departmentString;
				String planString, realString, regionString, propertyString;
				String lianString, gongString, jingshiString, pengString;
				String planAreaString, approvedString, realAreaString, introductionString;

				projectName = "���԰";
				projectStatus = "������Ŀ";
				projectFund = "4200��Ԫ";
				departmentString = "���ⷿ";
				planString = "1200��";
				realString = "1200��";
				regionString = "��ɽ��";
				propertyString = "�滮�õ�";
				lianString = "200��";
				gongString = "1000��";
				jingshiString = "0��";
				pengString = "0��";

				planAreaString = "6300.36ƽ����";
				approvedString = "6300.36ƽ����";
				realAreaString = "6300.36ƽ����";
				introductionString = "��Ŀλ�������йٶ�������Ƭ����������ɫ���ز������ٿ������ǣ����õ����Լ32.3Ķ���ܽ������Լ8.2��ƽ���ף����й�������ס��Լ3.06��ƽ���ף�606�ף�����ҵ������������ʩԼ3.09��ƽ���ף����½������Լ 2.05��ƽ���� �� ��Ͷ��Լ3.76��Ԫ��";

				TextView name = (TextView) findViewById(R.id.name_text);
				TextView status = (TextView) findViewById(R.id.status_text);
				TextView fund = (TextView) findViewById(R.id.fund_text);
				TextView department = (TextView) findViewById(R.id.department_text);
				TextView planTextView = (TextView) findViewById(R.id.plan_text);
				TextView realTEXTextView = (TextView) findViewById(R.id.real_text);
				TextView regionTextView = (TextView) findViewById(R.id.where_text);
				TextView propertyTextView = (TextView) findViewById(R.id.property_text);

				TextView lianTextView = (TextView) findViewById(R.id.lian_text);
				TextView gongTextView = (TextView) findViewById(R.id.gong_text);
				TextView jingshiTextView = (TextView) findViewById(R.id.jingshi_text);
				TextView pengTextView = (TextView) findViewById(R.id.peng_text);

				TextView planAreaTextView = (TextView) findViewById(R.id.plan_area);
				TextView approvedAreaTextView = (TextView) findViewById(R.id.approved_area);
				TextView realAreaTextView = (TextView) findViewById(R.id.real_area);
				TextView introductionTextView = (TextView) findViewById(R.id.project_introduction);

				name.setText(projectName);
				status.setText(projectStatus);
				fund.setText(projectFund);
				department.setText(departmentString);
				planTextView.setText(planString);
				realTEXTextView.setText(realString);
				regionTextView.setText(regionString);
				propertyTextView.setText(propertyString);

				lianTextView.setText(lianString);
				gongTextView.setText(gongString);
				jingshiTextView.setText(jingshiString);
				pengTextView.setText(pengString);

				planAreaTextView.setText(planAreaString);
				approvedAreaTextView.setText(approvedString);
				realAreaTextView.setText(realAreaString);
				introductionTextView.setText(introductionString);
			}
		});

	}
	
	
}
