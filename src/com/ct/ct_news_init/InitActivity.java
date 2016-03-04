package com.ct.ct_news_init;

import com.ct.ct_news_utils.SharedPreferencesUtils;
import com.example.ct_newss.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class InitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_init);

		startAnimation();
	}

	private void startAnimation() {
		LinearLayout ll_init = (LinearLayout) findViewById(R.id.ll_init);

		// 动画的集合 ，就是能保证几个动画可以同时实现
		AnimationSet animationSet = new AnimationSet(false);
		// 旋转动画

		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(2 * 1000);
		rotateAnimation.setFillAfter(true);// 让其维持在结束时候的状态

		// ll_init.startAnimation(rotateAnimation);
		// 缩放动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2 * 1000);
		scaleAnimation.setFillAfter(true);// 让其维持在结束时候的状态

		//

		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(scaleAnimation);

		ll_init.startAnimation(animationSet);
		//当我动画结束的时候我要进入新手引导界面，那咋样知道动画是否结束呢？这时候可以用监听事件来做
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				//跳入新手引导界面
				//当用户是第一次进入的时候会提示要进入这个界面，当第二次的时候就不会要求进入新手引导界面的
				//将所有代码都写在里面的话对于代码的美观，扩展性都不好
				startActivityToNew();
			
				
				
				
				
			}

		
		});

	}
	
	private void startActivityToNew() {
		
		
		if(SharedPreferencesUtils.getBoolean(this, SharedPreferencesUtils.SHARE_ISFIRST, true)==true){
			
			Intent intent=new Intent(InitActivity.this,GuideActivity.class);
			startActivity(intent);
			SharedPreferencesUtils.putBoolean(this, SharedPreferencesUtils.SHARE_ISFIRST, false);
		
		}
		
	}

}
