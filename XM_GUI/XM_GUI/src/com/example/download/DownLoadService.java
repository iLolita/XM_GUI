package com.example.download;

import com.example.search.fileDownloaded;
import com.example.search.fileDownloaded_withtitle;
import com.example.search.searchFile;
import com.example.xm_gui.R;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class DownLoadService extends Service{
	// 声明通知管理器
	private NotificationManager mNotificationManager = null;
	private PendingIntent contentIntent = null;
	// 声明Notification对象
	private Notification notification = null;	
	Context context = null;
	
	private String source=null;
	private String dest=null;
	private String filename=null;
	int count=0;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override  
	public void onCreate() {   
		Log.v("service", "ServiceDemo onCreate"); 
		source="http://61.166.249.130:1303/Upload/";
		String status=Environment.getExternalStorageState(); 		
	    if(status.equals(Environment.MEDIA_MOUNTED)){
			dest=Environment.getExternalStorageDirectory()+"/download/";
		}else{
			dest=this.getFilesDir()+"/download/";			
		}
	    Log.v("source", source);
	    Log.v("dest", dest);	
	    init();
		super.onCreate();   
	}  
	/**
	 * 下载进度的处理	
	 */
	
	private Handler handler=new Handler(){
    	 public void handleMessage(Message msg){    		 
    			 switch (msg.what) {
    			 	case -1://下载失败
    			 		notification.setLatestEventInfo(context, 
    			 				(String)msg.obj, "下载失败", contentIntent);           
    			        //用mNotificationManager的notify方法通知用户生成标题栏消息通知 
    			        mNotificationManager.notify(msg.arg2, notification); 
    			 		break;	
    			 	case 0://下载即将开始
    			 		notification.setLatestEventInfo(context,
    			 				(String)msg.obj, "准备下载",contentIntent);           
    			        //用mNotificationManager的notify方法通知用户生成标题栏消息通知 
    			        mNotificationManager.notify(msg.arg2, notification);	
    			 		break;
    			 	case 1://下载进行中，获取下载的进度值
    			 		//progressBar.setProgress(msg.arg1);
    			 		//textView.setText(msg.arg1+"%");
    			 		notification.setLatestEventInfo(context,
    			 				(String)msg.obj, "已下载"+msg.arg1+"%",contentIntent);           
    			        //用mNotificationManager的notify方法通知用户生成标题栏消息通知 
    			        mNotificationManager.notify(msg.arg2, notification);
    			 		break;
    			 	case 2://下载完成
    			 		notification.setLatestEventInfo(context,
    			 				(String)msg.obj, "下载完成",contentIntent);           
    			        //用mNotificationManager的notify方法通知用户生成标题栏消息通知 
    			        mNotificationManager.notify(msg.arg2, notification);
    			        Intent intent = new Intent("com.example.download.DownLoadService");
    			        context.stopService(intent);
    			 		break;				
    			 	default:
    			 		break;
    			 }   		 
		 }    	 
     };
     
	@Override
	public void onStart(Intent intent, int startId){
		Bundle extras=intent.getExtras();
		filename=extras.getString("filename");
		
//		//测试下载文件查看功能的测试代码
//		ArrayList<FileUnit> list=DownFileList.getDownFileList(dest);
//		for(int i=0;i<list.size();++i){
//			FileUnit fn = list.get(i);
//			Log.v("测试",fn+"\n");
//		}
//		
		Log.v("filename", filename);
		count++;
		final int id=count;		
		Thread thread=new Thread(){
			@Override
			public void run(){
				DownLoad.getFileFromServer(source,dest,filename,handler,id);
			}
		};
		thread.start();		
	}
		
	
	private void init(){
		//消息通知栏 
        //定义NotificationManager 
        String ns = Context.NOTIFICATION_SERVICE; 
        mNotificationManager = (NotificationManager) getSystemService(ns);      
        notification = new Notification(); 
        notification.flags = Notification.FLAG_AUTO_CANCEL; 
     // 设置通知在状态栏显示的图标
  	    notification.icon = R.drawable.download;
        notification.tickerText="下载中";
        //定义下拉通知栏时要展现的内容信息 
        context = getApplicationContext();
        CharSequence contentTitle = filename; 
        CharSequence contentText = "准备下载"; 
        Intent notificationIntent = new Intent(getApplicationContext(),fileDownloaded_withtitle.class); 
        contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, 
        		notificationIntent, 0); 
	}
	
}
