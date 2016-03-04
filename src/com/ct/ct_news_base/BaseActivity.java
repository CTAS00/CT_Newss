package com.ct.ct_news_base;

import cn.jpush.android.api.JPushInterface;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity{
	public BaseApplication mBaseApplication;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Toast.makeText(this, "haha", 0).show();
		JPushInterface.init(this);
		mBaseApplication=(BaseApplication) getApplication();
		mBaseApplication.addActivity(this);
		
		
		initView();
		initData();
	}

@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	mBaseApplication.removeActivity(this);
}

	public void initView() {
		
	}
	public void initData() {
		
	}

}
