package com.ct.ct_news;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

import com.ct.ct_news_base.BaseActivity;
import com.ct.ct_news_base.BasePager;
import com.ct.ct_news_init.InitActivity;
import com.ct.ct_news_tabs.pagerfour;
import com.ct.ct_news_tabs.pagerone;
import com.ct.ct_news_tabs.pagerthree;
import com.ct.ct_news_tabs.pagertwo;
import com.example.ct_newss.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends BaseActivity implements OnCheckedChangeListener, OnPageChangeListener {

	private ViewPager mViewPager;
	private ArrayList<BasePager> basePagers;
	private RadioGroup mRadioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main2);
		super.onCreate(savedInstanceState);


	}
	
	public void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		
		mViewPager.setOnPageChangeListener(this);
		basePagers = new ArrayList<BasePager>();
		basePagers.add(new pagerone(this));
		basePagers.add(new pagertwo(this));
		basePagers.add(new pagerthree(this));
		basePagers.add(new pagerfour(this));

		mRadioGroup = (RadioGroup) findViewById(R.id.rg_group);
		mRadioGroup.setOnCheckedChangeListener(this);

	}

	public void initData() {
		mViewPager.setAdapter(new myviewpageradapter());
		basePagers.get(0).initData();
		

	}

	class myviewpageradapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return basePagers.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager basePager = basePagers.get(position);// 多态

			// 每次一初始化画面就要加载数据的，会偷跑用户的流量，可以这样来考虑，监听用户的网络状态，当时wifi的情况下就可以使用这种方法，反之，不要使用这种方法
			container.addView(basePager.toptitle);
			//basePager.initData();

			// container.addView(basePagers.get(position));

			return basePagers.get(position).toptitle;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {

		switch (arg1) {
		case R.id.btn_one:
			mViewPager.setCurrentItem(0, false);

			break;
		case R.id.btn_two:
			mViewPager.setCurrentItem(1, true);

			break;
		case R.id.btn_three:
			mViewPager.setCurrentItem(2, true);

			break;
		case R.id.btn_four:
			mViewPager.setCurrentItem(3, false);

			break;

		default:
			break;
		}

	}
//页面切换的监听事件
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		basePagers.get(position).initData();
		
		
	}

}
