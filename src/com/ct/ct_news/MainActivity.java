package com.ct.ct_news;

import com.ct.ct_news_init.InitActivity;
import com.example.ct_newss.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    private TabHost mTabHost;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        initTabs();
    }



	private void initViews() {
		
		mTabHost = getTabHost();
		
	}
	private void initTabs() {
		LayoutInflater inflater=LayoutInflater.from(this);
		View view1=inflater.inflate(R.layout.activity_tabhost_bottom, null);
		//我要往选项卡里面加东西的话就要用内部类 TabSpec   
		TabHost.TabSpec tabSpec1=mTabHost.newTabSpec("1").setIndicator(view1);
		//把选项卡画面加到TabHost中去
		tabSpec1.setContent(new Intent(MainActivity.this,InitActivity.class));
		mTabHost.addTab(tabSpec1);
		
		
		
		View view2=inflater.inflate(R.layout.activity_tabhost_bottom, null);
		TextView textView2=(TextView) view2.findViewById(R.id.tv_bottom);
		textView2.setText("按钮二");
		//我要往选项卡里面加东西的话就要用内部类 TabSpec   
		TabHost.TabSpec tabSpec2=mTabHost.newTabSpec("2").setIndicator(view2);
		//把选项卡画面加到TabHost中去
		tabSpec2.setContent(new Intent(MainActivity.this,InitActivity.class));
		mTabHost.addTab(tabSpec2);
		
		
		
		View view3=inflater.inflate(R.layout.activity_tabhost_bottom, null);
		TextView textView3=(TextView) view3.findViewById(R.id.tv_bottom);
		textView3.setText("按钮三");
		//我要往选项卡里面加东西的话就要用内部类 TabSpec   
		TabHost.TabSpec tabSpec3=mTabHost.newTabSpec("3").setIndicator(view3);
		//把选项卡画面加到TabHost中去
		tabSpec3.setContent(new Intent(MainActivity.this,InitActivity.class));
		mTabHost.addTab(tabSpec3);
		
		
		View view4=inflater.inflate(R.layout.activity_tabhost_bottom, null);
		TextView textView4=(TextView) view4.findViewById(R.id.tv_bottom);
		textView4.setText("按钮四");
		//我要往选项卡里面加东西的话就要用内部类 TabSpec   
		TabHost.TabSpec tabSpec4=mTabHost.newTabSpec("4").setIndicator(view4);
		//把选项卡画面加到TabHost中去
		tabSpec4.setContent(new Intent(MainActivity.this,InitActivity.class));
		mTabHost.addTab(tabSpec4);
	}



    
}
