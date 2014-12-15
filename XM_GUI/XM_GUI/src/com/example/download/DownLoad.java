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
	 * @param source 文件再服务器端的地址
	 * @param dest 下载文件的目录
	 * @author vettal
	 */
	public static void getFileFromServer(String source,String dest,String filename,Handler handler,int id){	
	    //判断本地存放广告图片的目录是否存，不存在则新建文件夹		    
	   Message message=new Message();
	   try {
	    	
			File f = new File(dest);	    
		    if(!f.exists())
		    {
		       f.mkdir();
		    }
		    String localfilename=dest+filename;
		    File file = new File(localfilename);
		    //如果目标文件已经存在，则删除。产生覆盖旧文件的效果
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
			// 打开连接   						
	        URLConnection con = url.openConnection();
	        con.setConnectTimeout(3000);
	        //获得文件的长度
	        int contentLength = con.getContentLength();
	        //向主进程发送文件长度	       
			message.what=0;
			message.arg1=contentLength;
			message.arg2=id;
			message.obj=filename;
			handler.sendMessage(message);
	        android.util.Log.v("download","文件长度 :"+contentLength);
	        // 输入流   
	        InputStream is = con.getInputStream();  
	        // 1K的数据缓冲   
	        byte[] bs = new byte[1024];   
	        // 读取到的数据长度   
	        int len;   
	        // 输出的文件流   
	        OutputStream os = new FileOutputStream(localfilename);   
	        // 开始读取
	        int last_progress=0;
	        while ((len = is.read(bs)) != -1) { 
	            os.write(bs, 0, len);   
	            sum+=len;
	            progress=sum*100/contentLength;	
	            if(progress-last_progress>=1){
	            	Log.v("progress", ""+progress+"%");
	            	//向主进程发送下载进度
	            	message=new Message();
	            	message.what=1;
	            	message.arg1=progress;
	            	message.arg2=id;
	            	message.obj=filename;
	            	handler.sendMessage(message);
	            }
	            last_progress=progress;
	        }  
	        
	        // 完毕，关闭所有链接   
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
