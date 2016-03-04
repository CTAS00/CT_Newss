package com.ct.ct_news_imageload;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.ct.ct_news_pagerone.NewsDetailPager;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class ImageLoader {
	private ImageView mImageView;
	private String murl;
	// 做个缓存来减少用户的流量使用问题

	private LruCache<String, Bitmap> mLruCache;
	//private NewsDetailPager mNewsDetailPager;
	

	public ImageLoader() {
		int maxMenory = (int) Runtime.getRuntime().maxMemory();
		int bitmapMenory = maxMenory / 4;
		mLruCache = new LruCache<String, Bitmap>(bitmapMenory) {

			protected int sizeOf(String key, Bitmap value) {
				// 每次存进去都会调用这个方式，看看存进去的大不大
				return value.getByteCount();

			};

		};
	
		

	}

	private void addBitmapToLruCache(String url, Bitmap bitmap) {

		if (getBitmapFromLruCache(url) == null) {
			mLruCache.put(url, bitmap);
		}

	}

	private Bitmap getBitmapFromLruCache(String url) {

		return mLruCache.get(url);
	}

	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			if (mImageView.getTag().equals(murl)) {
				mImageView.setImageBitmap((Bitmap) msg.obj);
			}

		};
	};

	public void showImageByThread(ImageView imageView, final String url) {

		mImageView = imageView;
		murl = url;
		if(getBitmapFromLruCache(url)==null){
		
		
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				Message message = Message.obtain();
				message.obj = getBitmapFromUrl(url);
				if(message.obj!=null){
					addBitmapToLruCache(url, (Bitmap)message.obj);
					
				}
				mHandler.sendMessage(message);
			}

		}.start();}
		
		else{
			
			imageView.setImageBitmap(getBitmapFromLruCache(url));
		}

	}

	public Bitmap getBitmapFromUrl(String url) {
		Bitmap mBitmap;
		InputStream is = null;
		try {
			URL murl = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) murl
					.openConnection();
			is = httpURLConnection.getInputStream();
			Thread.sleep(1000);
			mBitmap = BitmapFactory.decodeStream(is);
			httpURLConnection.disconnect();
			is.close();
		//	Thread.sleep(1000);
			return mBitmap;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void showImageByAsyncTask(ImageView imageView, String url) {
		if(getBitmapFromLruCache(url)==null){

		new MyAsyncTask(imageView,url).execute(url);
		}
		else{
			
			imageView.setImageBitmap(getBitmapFromLruCache(url));
		}

	}

	 private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
		private ImageView mImageView;
		private String murl;

		public MyAsyncTask(ImageView mImageView, String url) {
			this.mImageView = mImageView;
			this.murl=url;

		}

		@Override
		protected Bitmap doInBackground(String... params) {
//			Bitmap bitmap=getBitmapFromUrl(params[0]);
//			if(bitmap!=null){
//			addBitmapToLruCache(params[0],bitmap );}

			return getBitmapFromUrl(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
		//	if (mImageView.getTag().equals(murl)) {
			super.onPostExecute(result);
			mImageView.setImageBitmap(result);
		//	}

		}

	}
	 
	 
	 
//	 private void loadImageFromStartToEnd(int start,int end){
//		 
//		 for(int i=start;i<end;i++){
//			 String url=
//			 
//			 
//		 }
//		 
//		 
//		 
//	 }

}
