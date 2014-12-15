package com.example.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class DownLoad {
	
	/**
	 * 
	 * @param source �ļ��ٷ������˵ĵ�ַ
	 * @param dest �����ļ���Ŀ¼
	 * @author vettal
	 */
	public static void getFileFromServer(String source,String dest,String filename,Handler handler,int id){	
	    //�жϱ��ش�Ź��ͼƬ��Ŀ¼�Ƿ�棬���������½��ļ���		    
	   Message message=new Message();
	   try {
	    	
			File f = new File(dest);	    
		    if(!f.exists())
		    {
		       f.mkdir();
		    }
		    String localfilename=dest+filename;
		    File file = new File(localfilename);
		    //���Ŀ���ļ��Ѿ����ڣ���ɾ�����������Ǿ��ļ���Ч��
		    if(file.exists())
		    {
		        file.delete();
		    }	
	    	file.createNewFile();
	    	
	    	String http=source+URLEncoder.encode(filename,"UTF-8");
	    	int progress=0;
	    	int sum=0;
	    	Log.v("progress", ""+progress+"%");
	    	URL url = new URL(http);
			// ������   						
	        URLConnection con = url.openConnection();
	        con.setConnectTimeout(3000);
	        //����ļ��ĳ���
	        int contentLength = con.getContentLength();
	        //�������̷����ļ�����	       
			message.what=0;
			message.arg1=contentLength;
			message.arg2=id;
			message.obj=filename;
			handler.sendMessage(message);
	        android.util.Log.v("download","�ļ����� :"+contentLength);
	        // ������   
	        InputStream is = con.getInputStream();  
	        // 1K�����ݻ���   
	        byte[] bs = new byte[1024];   
	        // ��ȡ�������ݳ���   
	        int len;   
	        // ������ļ���   
	        OutputStream os = new FileOutputStream(localfilename);   
	        // ��ʼ��ȡ
	        int last_progress=0;
	        while ((len = is.read(bs)) != -1) { 
	            os.write(bs, 0, len);   
	            sum+=len;
	            progress=sum*100/contentLength;	
	            if(progress-last_progress>=1){
	            	Log.v("progress", ""+progress+"%");
	            	//�������̷������ؽ���
	            	message=new Message();
	            	message.what=1;
	            	message.arg1=progress;
	            	message.arg2=id;
	            	message.obj=filename;
	            	handler.sendMessage(message);
	            }
	            last_progress=progress;
	        }  
	        
	        // ��ϣ��ر���������   
	        os.close();  
	        is.close();
	        progress=100;
	        Log.v("progress", ""+progress+"%");
	        message=new Message();
			message.what=2;
			message.arg1=progress;
			message.arg2=id;
			message.obj=filename;
			handler.sendMessage(message);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			android.util.Log.v("urlerror",e.getMessage());
			message=new Message();
			message.what=-1;
			message.arg2=id;
			message.obj=filename;
			handler.sendMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			android.util.Log.v("ioerror",e.getMessage());
			message=new Message();
			message.what=-1;
			message.arg2=id;
			message.obj=filename;
			handler.sendMessage(message);
		} catch(Exception e){
			message=new Message();
			message.what=-1;
			message.arg2=id;
			message.obj=filename;
			handler.sendMessage(message);
		}
	}	   
		 
	
}
