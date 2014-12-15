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
	private EditText mUser; // 帐号编辑框
	private EditText mPassword; // 密码编辑框
	
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
	    	 * 从配置文件中得到保存的用户名与密码
	    	 * 密码根据用户名获得
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
				 * 即使设置为保存了密码，
				 * 若用户输入了密码，则直接将输入的信息作为输入的密码
				 * 而不是从配置文件中读取
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
	 * 用于响应“登录”按钮事件
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
						if("abc".equals(localUser) && MD5Authentication.getMD5("123").equals(localPasswd))   //判断 帐号和密码
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
						    //在LoadingActivity类中显示旋转进度条，并跳转到主页面
						    intent.setClass(Login.this,LoadingActivity.class);
						    startActivity(intent);

						}
						else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //判断 帐号和密码
						{
							new AlertDialog.Builder(Login.this)
							.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
							.setTitle("登录错误")
							.setMessage("  帐号或者密码不能为空，\n  请输入后再登录！")
							.setPositiveButton("确定", null)
							.create().show();
						 }
						else{
							new AlertDialog.Builder(Login.this)
							.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
							.setTitle("登录失败")
							.setMessage("  帐号或者密码不正确，\n  请检查后重新输入！")
							.setPositiveButton("确定", null)
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
