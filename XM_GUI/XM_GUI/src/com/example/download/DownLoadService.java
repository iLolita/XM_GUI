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
	// ����֪ͨ������
	private NotificationManager mNotificationManager = null;
	private PendingIntent contentIntent = null;
	// ����Notification����
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
	 * ���ؽ��ȵĴ���	
	 */
	
	private Handler handler=new Handler(){
    	 public void handleMessage(Message msg){    		 
    			 switch (msg.what) {
    			 	case -1://����ʧ��
    			 		notification.setLatestEventInfo(context, 
    			 				(String)msg.obj, "����ʧ��", contentIntent);           
    			        //��mNotificationManager��notify����֪ͨ�û����ɱ�������Ϣ֪ͨ 
    			        mNotificationManager.notify(msg.arg2, notification); 
    			 		break;	
    			 	case 0://���ؼ�����ʼ
    			 		notification.setLatestEventInfo(context,
    			 				(String)msg.obj, "׼������",contentIntent);           
    			        //��mNotificationManager��notify����֪ͨ�û����ɱ�������Ϣ֪ͨ 
    			        mNotificationManager.notify(msg.arg2, notification);	
    			 		break;
    			 	case 1://���ؽ����У���ȡ���صĽ���ֵ
    			 		//progressBar.setProgress(msg.arg1);
    			 		//textView.setText(msg.arg1+"%");
    			 		notification.setLatestEventInfo(context,
    			 				(String)msg.obj, "������"+msg.arg1+"%",contentIntent);           
    			        //��mNotificationManager��notify����֪ͨ�û����ɱ�������Ϣ֪ͨ 
    			        mNotificationManager.notify(msg.arg2, notification);
    			 		break;
    			 	case 2://�������
    			 		notification.setLatestEventInfo(context,
    			 				(String)msg.obj, "�������",contentIntent);           
    			        //��mNotificationManager��notify����֪ͨ�û����ɱ�������Ϣ֪ͨ 
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
		
//		//���������ļ��鿴���ܵĲ��Դ���
//		ArrayList<FileUnit> list=DownFileList.getDownFileList(dest);
//		for(int i=0;i<list.size();++i){
//			FileUnit fn = list.get(i);
//			Log.v("����",fn+"\n");
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
		//��Ϣ֪ͨ�� 
        //����NotificationManager 
        String ns = Context.NOTIFICATION_SERVICE; 
        mNotificationManager = (NotificationManager) getSystemService(ns);      
        notification = new Notification(); 
        notification.flags = Notification.FLAG_AUTO_CANCEL; 
     // ����֪ͨ��״̬����ʾ��ͼ��
  	    notification.icon = R.drawable.download;
        notification.tickerText="������";
        //��������֪ͨ��ʱҪչ�ֵ�������Ϣ 
        context = getApplicationContext();
        CharSequence contentTitle = filename; 
        CharSequence contentText = "׼������"; 
        Intent notificationIntent = new Intent(getApplicationContext(),fileDownloaded_withtitle.class); 
        contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, 
        		notificationIntent, 0); 
	}
	
}
