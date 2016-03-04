package com.ct.ct_news_base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class BaseApplication extends Application {
	public List<Activity> mList;
//	private static BaseApplication instance;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mList=new ArrayList<Activity>();
	//	instance=this;

	}
	
	
	public void addActivity(Activity activity){
		mList.add(activity);
		
		
		
		
	}
	
	public void removeActivity(Activity activity){
		mList.remove(activity);
		
		
		
		
	}

}
