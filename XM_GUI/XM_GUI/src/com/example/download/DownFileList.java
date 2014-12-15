package com.example.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownFileList {
		
	/**
	 * �õ�����Ŀ¼�����ص��ļ���Ϣ
	 * ��Ϣ�����ļ������ļ�����
	 * �ļ����ͷ�Ϊ���֣�һ�����ĵ����ͣ�typeֵΪdoc����һ����ͼƬ���ͣ�typeֵΪimage
	 * @param dir ��������ļ���Ŀ¼
	 * @return ArrayList<FileUnit>
	 * @author vettal
	 */
	public static ArrayList<FileUnit> getDownFileList(String dir){
		ArrayList<FileUnit> list = new ArrayList<FileUnit>();
		File dir_file = new File(dir);
		if(!dir_file.exists() || !dir_file.isDirectory()){
			return null;
		}
		File[] files=dir_file.listFiles();
		for(int i=0;i<files.length;++i){
			if(files[i].isDirectory())
				continue;
			FileUnit fn = new FileUnit();
			String name=files[i].getName();
			fn.setName(name);
			String suffix=null;
			String type=null;
			int pos=name.lastIndexOf(".");
			if(pos!=-1){
				suffix=name.substring(pos+1);
			}
			if(null==suffix || suffix.equals("doc")|| suffix.equals("docx")
					|| suffix.equals("xls")|| suffix.equals("xlsx")
					|| suffix.equals("ppt")|| suffix.equals("pptx")){
				type="doc";
			}else{
				type="image";
			}
			fn.setType(type);
			list.add(fn);
		}
		return list;
	}
	
}
