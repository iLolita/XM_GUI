package com.example.start;

import java.security.NoSuchAlgorithmException;

import com.example.main.Home;
import com.example.main.Main_View;
import com.example.xm_gui.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;


public class Login extends Activity{

	private SharedPreferences settingPreferences;
	private Editor mEditor;
	private CheckBox mCheckBox;
	private String localUser;
	private String localPasswd;
	private boolean local = false;
	private boolean flag = false;
	
	public static Login instance = null;
	private EditText mUser; // �ʺű༭��
	private EditText mPassword; // ����༭��
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
        mCheckBox = (CheckBox)findViewById(R.id.savePasswordCB);
        mUser = (EditText)findViewById(R.id.username_edit);
	    mPassword = (EditText)findViewById(R.id.password_edit);

	    settingPreferences = getSharedPreferences("settings", MODE_PRIVATE);
	    mEditor = settingPreferences.edit();
	    
	    if(settingPreferences.getBoolean("HAS_SAVED_PASSWD", false)) {
	    	/**
	    	 * �������ļ��еõ�������û���������
	    	 * ��������û������
	    	 */
	    	localUser = settingPreferences.getString("LOCAL_USER", "");
    		localPasswd = settingPreferences.getString(localUser, "");
    		mUser.setText(localUser);
    		mPassword.setText("123456");
    		local = true;
    		flag = true;
    	}
	    
	    if(settingPreferences.getBoolean("SAVING_PASSWD", false)){
	    	mCheckBox.setChecked(true);
	    }
	    
	    mPassword.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub	
				/**
				 * ��ʹ����Ϊ���������룬
				 * ���û����������룬��ֱ�ӽ��������Ϣ��Ϊ���������
				 * �����Ǵ������ļ��ж�ȡ
				 */
				if(flag == true) {
					mPassword.setText(null);
					flag = false;
				}
				local = false;
				mEditor.putBoolean("HAS_SAVED_PASSWD", false);
				mEditor.commit();
				Log.i("mytag","Passwd");
				return false;
			}
	    	
	    });	    
	    
	    mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() { 
	    	@Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            	if(mCheckBox.isChecked()) {
            		mEditor.putBoolean("SAVING_PASSWD", true);
            	} else {
            		mEditor.putBoolean("SAVING_PASSWD", false);
            		mEditor.putBoolean("HAS_SAVED_PASSWD", false);
            	}
            	mEditor.commit();
    		    Log.i("mytag", "onClick");
            }
	    });
        
        instance = this;
        login();
	}
	


	/**
	 * ������Ӧ����¼����ť�¼�
	 */
	private void login() {
		 	
		Button log_to_main=(Button)findViewById(R.id.signin_button);
		log_to_main.setOnClickListener(new OnClickListener() {   
	            @Override
	            public void onClick(View v) {
	        	    
	        	    localUser = mUser.getText().toString();
	        	    if(local == false) {
						try {
							Log.i("mytag", mPassword.getText().toString());
							localPasswd = MD5Authentication.getMD5(mPassword.getText().toString());
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	    }
	        	    
	            	try {
						if("abc".equals(localUser) && MD5Authentication.getMD5("123").equals(localPasswd))   //�ж� �ʺź�����
						{
							if(settingPreferences.getBoolean("SAVING_PASSWD", false)) {
								mEditor.putBoolean("HAS_SAVED_PASSWD", true);
								mEditor.putString("LOCAL_USER", "abc");
								try {
									mEditor.putString(localUser, MD5Authentication.getMD5("123"));
								} catch (NoSuchAlgorithmException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								mEditor.commit();
							}
						    Intent intent = new Intent();
						    //��LoadingActivity������ʾ��ת������������ת����ҳ��
						    intent.setClass(Login.this,LoadingActivity.class);
						    startActivity(intent);

						}
						else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //�ж� �ʺź�����
						{
							new AlertDialog.Builder(Login.this)
							.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
							.setTitle("��¼����")
							.setMessage("  �ʺŻ������벻��Ϊ�գ�\n  ��������ٵ�¼��")
							.setPositiveButton("ȷ��", null)
							.create().show();
						 }
						else{
							new AlertDialog.Builder(Login.this)
							.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
							.setTitle("��¼ʧ��")
							.setMessage("  �ʺŻ������벻��ȷ��\n  ������������룡")
							.setPositiveButton("ȷ��", null)
							.create().show();
							mPassword.setText(null);
							mEditor.putBoolean("HAS_SAVED_PASSWD", false);
							mEditor.commit();
						}
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	         	
//	                Intent intent = new Intent();
//	                intent.setClass(Login.this, Home.class);
//	                startActivityForResult(intent, 0);
	            }
	        });
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
