package com.ct.ct_news_weight;

import com.example.ct_newss.R;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;







public class FlippingLoadingDialog extends Dialog{
	
	private String information;
	private ImageView mImageView;
	private TextView mTextView;

	public FlippingLoadingDialog(Context context,String info) {
		super(context,R.style.loading_dialog);
		this.information=info;
		init();
		
		
	}

	private void init() {
             setContentView(R.layout.flippingloadingdialog);
             mImageView=(ImageView) findViewById(R.id.loadingdialog_iv_icon);
             mTextView=(TextView) findViewById(R.id.loadingdialog_tv_text);
             mTextView.setText(information);
         
             
             mImageView.setAnimation(this.setrotateanimation());
             
             
	}
	
	private RotateAnimation setrotateanimation(){
		
	    RotateAnimation rotateAnimation=new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2*1000);
        rotateAnimation.setFillAfter(true);//让其维持变后的样子
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        return rotateAnimation;
		
		
	}
	
	
	@Override
	public void show() {
		
	
			super.show();
			 mImageView.setAnimation(this.setrotateanimation());
			
			
		
			
		
	}
	
	

	

	

	
	

	



}
