package com.ct.ct_news;

import com.ct.ct_news_base.BaseActivity;
import com.ct.ct_news_base.BaseApplication;
import com.example.ct_newss.R;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class News_WebView extends BaseActivity implements OnClickListener {
	private WebView mWebView;
	private ImageView iv_back;
	private ImageView iv_setting;
	private ProgressBar mProgressBar;
	private int SingleChoiceItems;// 选择的单个

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_webview);
		super.onCreate(savedInstanceState);
	

	//	initView();
		// mWebView
	}

	public void initView() {
		mWebView = (WebView) findViewById(R.id.wv_news);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);

	
		iv_setting = (ImageView) findViewById(R.id.iv_setting);
		iv_setting.setOnClickListener(this);
		mProgressBar=(ProgressBar) findViewById(R.id.webview_progress);

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new WebViewClient() {

			// 画面加载
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				mProgressBar.setVisibility(View.VISIBLE);
			}

			// 加载完成
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				mProgressBar.setVisibility(View.GONE);
			}

			// 链接跳转的时候默认调用
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}

		});
		mWebView.loadUrl(getIntent().getExtras().getString("url"));
		// 用来获取网站的信息
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				// TODO Auto-generated method stub
				super.onReceivedTitle(view, title);
			}

		});

	}

	private void ShowTextSize() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String[] items = new String[] { "大", "中", "小" };
		builder.setSingleChoiceItems(items, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						SingleChoiceItems = arg1;

					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				WebSettings settings = mWebView.getSettings();

				switch (SingleChoiceItems) {
				case 0:
					settings.setTextSize(TextSize.LARGEST);

					break;
				case 1:
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 2:
					settings.setTextSize(TextSize.SMALLEST);
					break;

				default:
					break;
				}

			}
		});

		builder.show();
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.iv_back:
			boolean flag=false;
			
			//若主界面存在的话就直接回主界面
			for(Activity activity:mBaseApplication.mList){
				if(activity.getClass().getName().toString().contains("MainActivity2")){
					flag=true;
					//finish();
					break;
					
				}
//				else{
//					finish();
//				startActivity(new Intent(this,MainActivity2.class));
//					
//					
//				}
			}
				
				if(flag==false){
					
					startActivity(new Intent(this,MainActivity2.class));	
					
					
				}
				finish();
				
				
		
			
		
			
			break;
case R.id.iv_setting:
	ShowTextSize();
			break;


		default:
			break;
		}
		
	}

}
