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

public class infoAdapter extends BaseAdapter {
    private class buttonViewHolder {
    	TextView attributeName;
        TextView attributeValue;
    }
    
    private ArrayList<HashMap<String, Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] keyString;
    private int[] valueViewID;
    private buttonViewHolder holder;
    
    
    public infoAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
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
            convertView = mInflater.inflate(R.layout.listitem_information, null);
            holder = new buttonViewHolder();
            holder.attributeName = (TextView)convertView.findViewById(valueViewID[0]);
            holder.attributeValue = (TextView)convertView.findViewById(valueViewID[1]);
          
            convertView.setTag(holder);
        }
        
        HashMap<String, Object> appInfo = mAppList.get(position);
        if (appInfo != null) {
            String  value = (String) appInfo.get(keyString[1]);
            String aname = (String) appInfo.get(keyString[0]);
            holder.attributeValue.setText(value);
            holder.attributeName.setText(aname);
          }        
        return convertView;
    }

 //****************************与下载对应  ****************吴迪**********
      
   
}

