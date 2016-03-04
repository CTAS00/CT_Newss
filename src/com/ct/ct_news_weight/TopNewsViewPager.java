package com.ct.ct_news_weight;

import android.R.integer;
import android.app.Notification.Action;
import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TopNewsViewPager extends ViewPager {
	private int startX;
	private int startY;

	public TopNewsViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	// 还有一个就是优化的问题，
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);

		switch (ev.getAction()) {

		case MotionEvent.ACTION_DOWN:

			startX = (int) ev.getRawX();
			startY = (int) ev.getRawY();

			break;

		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getRawX();
			int endY = (int) ev.getRawY();

			if (Math.abs(endX - startX) > Math.abs(endY - startY)) {// 只能是判断是否是左右滑动

				if (endX < startX) {// 右滑

					if (getCurrentItem() == getAdapter().getCount() - 1) {

						getParent().requestDisallowInterceptTouchEvent(false);// 请求父亲去拦截

					}

				} else {
					if (getCurrentItem() == 0) {
						getParent().requestDisallowInterceptTouchEvent(false);// 请求父亲去拦截

					}

				}

			}else{
				getParent().requestDisallowInterceptTouchEvent(false);// 请求父亲去拦截
				
				
			}

			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

}
