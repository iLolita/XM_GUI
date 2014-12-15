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

		// 顶部返回按钮
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

				projectName = "泽惠园";
				projectStatus = "竣工项目";
				projectFund = "4200万元";
				departmentString = "公租房";
				planString = "1200套";
				realString = "1200套";
				regionString = "西山区";
				propertyString = "规划用地";
				lianString = "200套";
				gongString = "1000套";
				jingshiString = "0套";
				pengString = "0套";

				planAreaString = "6300.36平方米";
				approvedString = "6300.36平方米";
				realAreaString = "6300.36平方米";
				introductionString = "项目位于昆明市官渡区五腊片区，东临绿色房地产，西临俊福花城，净用地面积约32.3亩。总建筑面积约8.2万平方米，其中公共租赁住房约3.06万平方米（606套），商业及生活配套设施约3.09万平方米，地下建筑面积约 2.05万平方米 ， 总投资约3.76亿元。";

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
