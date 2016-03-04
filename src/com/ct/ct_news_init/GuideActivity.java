package com.ct.ct_news_init;

import java.util.ArrayList;

import com.example.ct_newss.R;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GuideActivity extends Activity implements OnPageChangeListener {

	private int[] imageId = new int[] { R.drawable.ic_launcher,
			R.drawable.haha, R.drawable.ic_launcher };
	private ArrayList<ImageView> imagelist;

	private ViewPager mViewPager;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_init_guide);
		initView();
		initData();

	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.vp_lead);
		mButton = (Button) findViewById(R.id.btn_start);

		imagelist = new ArrayList<ImageView>();

	}

	private void initData() {

		for (int i = 0; i < imageId.length; i++) {
			// 一般添加的是对象，我要咋样把id变成对象呢
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imageId[i]);
			imagelist.add(imageView);

		}

		mViewPager.setAdapter(new GuideView());
		mViewPager.setOnPageChangeListener(this);

	}

	class GuideView extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageId.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imagelist.get(position));

			return imagelist.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		
		
		
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
