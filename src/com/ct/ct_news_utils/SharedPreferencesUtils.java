package com.ct.ct_news_utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	// 整个文件的名称
	public static final String SHARE_NAME = "ct_first";
	// key键值的名称
	public static final String SHARE_ISFIRST = "IsFirst";
	
	public static boolean getBoolean(Context context,String key,boolean defaultvalue){
      	SharedPreferences sharedPreferences=context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
		
		return sharedPreferences.getBoolean(SHARE_ISFIRST, defaultvalue);
		
		
		
	}
	
	public static void putBoolean(Context context,String key,boolean value){
      	SharedPreferences sharedPreferences=context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
		
    	sharedPreferences.edit().putBoolean(SHARE_ISFIRST, value).commit();
	
	}
	public static String getString(Context context,String key,String defaultvalue){
      	SharedPreferences sharedPreferences=context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
		
		return sharedPreferences.getString(key, defaultvalue);
		
		
		
	}
	
	public static void setString(Context context,String key,String value){
      	SharedPreferences sharedPreferences=context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
		
    	sharedPreferences.edit().putString(key, value).commit();
	
	}
	

}
