package com.ct.ct_news_base;

import com.ct.ct_news_weight.RefreshListView;
import com.example.ct_newss.R;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

//Ϊ�˱���Ĺ淶��
public abstract class TabDetailPager {
	// ��֪���Ǹ������ʱ�����������д
	public Activity mActivity;
	
	public View footview;//������


	public TabDetailPager(Activity mActivity) {
		this.mActivity = mActivity;
		footview=initView();
	}


	public abstract View initView();
		
	

	

	public abstract void initData();

}
