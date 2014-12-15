package com.example.listadapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.download.DownLoad;
import com.example.download.DownLoadService;
import com.example.xm_gui.R;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class lvButtonAdapter extends BaseAdapter {
    private class buttonViewHolder {
        ImageView appIcon;
        TextView appName;
        ImageButton buttonClose;
    }
    
    private ArrayList<HashMap<String, Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] keyString;
    private int[] valueViewID;
    private buttonViewHolder holder;
    
    
    public lvButtonAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
            String[] from, int[] to) {
        mAppList = appList;
        mContext = c;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keyString = new String[from.length];
        valueViewID = new int[to.length];
        System.arraycopy(from, 0, keyString, 0, from.length);
        System.arraycopy(to, 0, valueViewID, 0, to.length);
    }
    
    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int position){
        mAppList.remove(position);
        this.notifyDataSetChanged();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (buttonViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.listitem, null);
            holder = new buttonViewHolder();
            holder.appIcon = (ImageView)convertView.findViewById(valueViewID[0]);
            holder.appName = (TextView)convertView.findViewById(valueViewID[1]);
            holder.buttonClose = (ImageButton)convertView.findViewById(valueViewID[2]);
            convertView.setTag(holder);
        }
        
        HashMap<String, Object> appInfo = mAppList.get(position);
        if (appInfo != null) {
            String aname = (String) appInfo.get(keyString[1]);
            int mid = (Integer)appInfo.get(keyString[0]);
            int bid = (Integer)appInfo.get(keyString[2]);
            holder.appName.setText(aname);
            holder.appIcon.setImageDrawable(holder.appIcon.getResources().getDrawable(mid));
            holder.buttonClose.setImageDrawable(holder.buttonClose.getResources().getDrawable(bid));
            holder.buttonClose.setOnClickListener(new lvButtonListener(position));
        }        
        return convertView;
    }

    class lvButtonListener implements OnClickListener {
        private int position;

        lvButtonListener(int pos) {
            position = pos;
        }
 //****************************与下载对应  ****************吴迪**********
        @Override
        public void onClick(View v) {
        	String url=null;
        	if(position==1)
        		url="http://61.166.249.130:1303/Upload/ceshi001.doc";
        	else
        		url="http://61.166.249.130:1303/Upload/123.docx";
        	String filename=url.substring(url.lastIndexOf("/")+1);
        	//String filename="123.docx";	
        	Log.v("下载线程", "下载开始");
        	try{
        		Intent intent = new Intent("com.example.download.DownLoadService");
        		intent.putExtra("filename", filename);
        		mContext.startService(intent);
        	}catch(Exception e){
        		Log.v("错误", e.getMessage());
        	}

        }
    }
    
}

