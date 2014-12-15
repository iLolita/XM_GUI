package com.example.start;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Authentication {
	
	public static String getMD5(String passwd) throws NoSuchAlgorithmException{    
        MessageDigest md5 = MessageDigest.getInstance("MD5");    
        md5.update(passwd.getBytes());    
        byte[] m = md5.digest();//º”√‹     
        return getString(m);    
}    
    private static String getString(byte[] b){    
        StringBuffer sb = new StringBuffer();    
         for(int i = 0; i < b.length; i ++){    
          sb.append(b[i]);    
         }    
         return sb.toString(); 
    }
	
}