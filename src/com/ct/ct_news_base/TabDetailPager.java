package com.ct.ct_news_base;

import com.ct.ct_news_weight.RefreshListView;
import com.example.ct_newss.R;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

//为了编码的规范性
public abstract class TabDetailPager {
	// 当知道那个界面的时候可以这样来写
	public Activity mActivity;
	
	public View footview;//跟布局


	public TabDetailPager(Activity mActivity) {
		this.mActivity = mActivity;
		footview=initView();
	}


	public abstract View initView();
		
	

	

	public abstract void initData();

}
