package com.ct.ct_news_base;

import java.security.PublicKey;

import com.ct.ct_news_weight.FlippingLoadingDialog;
import com.example.ct_newss.R;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public abstract class BasePager {
//写的是一个基类   里面有一个控件textview
	public Activity mActivity;

	public TextView tv_title;

	public View toptitle;
	
	public FrameLayout frameLayout;
	
	public FlippingLoadingDialog mFlippingLoadingDialog;

	public BasePager(Activity mActivity) {
		this.mActivity = mActivity;

		initViews();
		
		

	}

	private void initViews() {
		toptitle = View.inflate(mActivity, R.layout.activity_top_title, null);

		tv_title = (TextView) toptitle.findViewById(R.id.tv_title);
		frameLayout=(FrameLayout) toptitle.findViewById(R.id.frame);
		mFlippingLoadingDialog=new FlippingLoadingDialog(mActivity, "赵雨薇好好学习！！！");

	}
	
	
	public  abstract void initData();

	

}
