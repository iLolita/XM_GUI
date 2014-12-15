package com.example.listadapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.download.DownLoad;
import com.example.download.DownLoadService;
import com.example.search.searchFileSearch;
import com.example.xm_gui.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class localFileAdapter extends BaseAdapter {
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
	private String local_dir;

	public localFileAdapter(Context c,
			ArrayList<HashMap<String, Object>> appList, int resource,
			String[] from, int[] to,String dest) {
		mAppList = appList;
		mContext = c;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
		local_dir=dest;
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

	public void removeItem(int position) {
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
			holder.appIcon = (ImageView) convertView
					.findViewById(valueViewID[0]);
			holder.appName = (TextView) convertView
					.findViewById(valueViewID[1]);
			holder.buttonClose = (ImageButton) convertView
					.findViewById(valueViewID[2]);
			convertView.setTag(holder);
		}

		HashMap<String, Object> appInfo = mAppList.get(position);
		if (appInfo != null) {
			String aname = (String) appInfo.get(keyString[1]);
			int mid = (Integer) appInfo.get(keyString[0]);
			int bid = (Integer) appInfo.get(keyString[2]);
			holder.appName.setText(aname);
			holder.appIcon.setImageDrawable(holder.appIcon.getResources()
					.getDrawable(mid));
			holder.buttonClose.setImageDrawable(holder.buttonClose
					.getResources().getDrawable(bid));
			holder.buttonClose
					.setOnClickListener(new lvButtonListener(position));
		}
		return convertView;
	}

	class lvButtonListener implements OnClickListener {
		private int position;

		lvButtonListener(int pos) {
			position = pos;
		}

		// ****************************打开本地文件 ****************袁杰**********8
		@Override
		public void onClick(View v) {
			final int vid = v.getId();
			new AlertDialog.Builder(mContext)
					.setTitle("确定删除？")
					.setMessage("您确定删除该条信息吗？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									//文件名，需要重新计算
										
									@SuppressWarnings("unchecked")
									Map<String, String> m=(Map<String, String>) getItem(position);
									String filename=(String) m.get("ItemWinName");
									filename=local_dir+filename;
									Log.i("myTag", filename);
									
									//删除文件的操作
									File file=new File(filename);
									
										file.delete();
									
									if (vid == holder.buttonClose.getId())
										removeItem(position);
									
								}
							})
					.setNegativeButton("取消",null)
					.show();

		}
	}

}
