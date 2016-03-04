

package com.ct.ct_news_tabs;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ct.ct_news_base.BasePager;
import com.ct.ct_news_base.TabDetailPager;
import com.ct.ct_news_config.Config;
import com.ct.ct_news_entity.TopNewsPhoto;
import com.ct.ct_news_entity.TopNewsPhotoGroup;
import com.ct.ct_news_entity.TopNewsText;
import com.ct.ct_news_pagerone.NewsDetailPager;
import com.ct.ct_news_utils.CacheUtils;
import com.ct.ct_news_utils.JsonParser;
import com.ct.ct_news_utils.JsonResultSplit;
import com.ct.ct_news_weight.FlippingLoadingDialog;
import com.example.ct_newss.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;


import com.viewpagerindicator.TabPageIndicator;

public class pagerone extends BasePager implements OnPageChangeListener {
	
	
	
	
	 private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };
	
	
	
	
	private ArrayList<String> arrayList;//这是标题
	private ArrayList<TabDetailPager> arraylistpager;//这是标题下面的界面


	  private List<TopNewsPhotoGroup> list;//这是解析出来的东西
	//  private List<TopNewsText> list_text;

	  private View view;




	private TabPageIndicator indicator;




	private ViewPager pager;
	

	public pagerone(Activity mActivity) {
		super(mActivity);
	}
	
	public void initData(){
		
	String cache=CacheUtils.getCache(mActivity, Config.SERVER_INTERVICE, null);
	
	if(cache!=null){
		list = new JsonParser<TopNewsPhotoGroup>()
				.parserJsonList(JsonResultSplit.split(cache),
						TopNewsPhotoGroup.class);
		initDataToView();

	}
		
		
		
		
		getwebphoto();//获取json   还有的注意就是里面的是异步线程，所以说有可能不是按照一步步往下来实行的//这是一个异步线程
	
		
		
	}
	private void getwebphoto() {
		HttpUtils http = new HttpUtils();
	//	RequestParams params=new RequestParams(HTTP.UTF_8);
		//params.addQueryStringParameter("position", "1");
		
		http.send(HttpMethod.GET,
				Config.SERVER_INTERVICE,
		    new RequestCallBack<String>(){
		      

			

				@Override
		        public void onLoading(long total, long current, boolean isUploading) {
		           // testTextView.setText(current + "/" + total);
		        }

		        @Override
		        public void onSuccess(ResponseInfo<String> responseInfo) {
		          //  textView.setText(responseInfo.result);
		        	String resultstring=(String)responseInfo.result;
		        	
		        //	JsonResultSplit.split(resultstring);
		        	  	
	       
		        	list = new JsonParser<TopNewsPhotoGroup>()
							.parserJsonList(JsonResultSplit.split(resultstring),
									TopNewsPhotoGroup.class);
		        	LogUtils.d(resultstring);
		        	
		        	//将数据存到缓存中去
		        	CacheUtils.setCache(mActivity, Config.SERVER_INTERVICE, resultstring);
		        	
		        
		        	initDataToView();
		        	mFlippingLoadingDialog.dismiss();
		        	
		        }

		     
				@Override
		        public void onStart() {
					mFlippingLoadingDialog.show();
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	mFlippingLoadingDialog.dismiss();
		        }

				
		});		
	}

	private void initTitleData() {
		arrayList=new ArrayList<String>();
		arrayList.add("新闻");
		arrayList.add("视频");
		arrayList.add("轻松一刻");
		arrayList.add("骡子");
		
		arraylistpager=new ArrayList<TabDetailPager>();
		for(int i=0;i<CONTENT.length;i++){
			
			arraylistpager.add(new NewsDetailPager(mActivity,list.get(i).photo,list.get(i).text));
			
		}
		
			
	}
	


	
	
	 class TabViewPager extends PagerAdapter {
	     


	        @Override
	        public CharSequence getPageTitle(int position) {
	            return CONTENT[position % CONTENT.length].toUpperCase();
	        }

	        @Override
	        public int getCount() {
	          return CONTENT.length;
	        }

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			
				@Override
			public Object instantiateItem(ViewGroup container, int position) {
				
				View view=arraylistpager.get(position).footview;
				container.addView(view);                                      
				arraylistpager.get(position).initData();
				
				
				return view;
			}
			
				@Override
				public void destroyItem(ViewGroup container, int position, Object object) {
					container.removeView((View)object);
				}
			
			
	    }





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
	
	}
	
	
	   private void initDataToView() {
			// TODO Auto-generated method stub
       	initTitleData();   //里面有对象
       	
   		
   		
   		tv_title.setText("我是画面一");

   		view = View.inflate(mActivity, R.layout.activity_tab, null);
   		indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
   		pager = (ViewPager) view.findViewById(R.id.pager);
   		pager.setOnPageChangeListener(this);
   		
   		pager.setAdapter(new TabViewPager());
   			
   		
   		indicator.setViewPager(pager);
   		
   		frameLayout.removeAllViews();//防止会产生重叠
   		frameLayout.addView(view);
		}



}
