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

		// �����ļ��� �������ܱ�֤������������ͬʱʵ��
		AnimationSet animationSet = new AnimationSet(false);
		// ��ת����

		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(2 * 1000);
		rotateAnimation.setFillAfter(true);// ����ά���ڽ���ʱ���״̬

		// ll_init.startAnimation(rotateAnimation);
		// ���Ŷ���
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2 * 1000);
		scaleAnimation.setFillAfter(true);// ����ά���ڽ���ʱ���״̬

		//

		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(scaleAnimation);

		ll_init.startAnimation(animationSet);
		//���Ҷ���������ʱ����Ҫ���������������棬��զ��֪�������Ƿ�����أ���ʱ������ü����¼�����
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
				//����������������
				//���û��ǵ�һ�ν����ʱ�����ʾҪ����������棬���ڶ��ε�ʱ��Ͳ���Ҫ������������������
				//�����д��붼д������Ļ����ڴ�������ۣ���չ�Զ�����
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
