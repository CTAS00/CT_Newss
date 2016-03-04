package com.ct.ct_news_tabs;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.ct.ct_news_base.BasePager;

public class pagertwo extends BasePager {

	public pagertwo(Activity mActivity) {
		super(mActivity);
	}
	
	public void initData(){
		
		tv_title.setText("我是画面二");
		
		
		TextView textView=new TextView(mActivity);
		
		textView.setText("赵雨薇好好学习！！！");
		textView.setTextColor(Color.RED);
		textView.setGravity(Gravity.CENTER);
		frameLayout.addView(textView);
		
		
	}


}
