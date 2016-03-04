package com.ct.ct_news_pagerone;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import org.apache.http.protocol.HTTP;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.ct.ct_news.News_WebView;
import com.ct.ct_news_base.TabDetailPager;
import com.ct.ct_news_config.Config;
import com.ct.ct_news_entity.TopNewsPhoto;
import com.ct.ct_news_entity.TopNewsPhotoGroup;
import com.ct.ct_news_entity.TopNewsText;
import com.ct.ct_news_imageload.ImageLoader;
import com.ct.ct_news_utils.CacheUtils;
import com.ct.ct_news_utils.JsonParser;
import com.ct.ct_news_utils.JsonResultSplit;
import com.ct.ct_news_utils.SharedPreferencesUtils;
import com.ct.ct_news_weight.RefreshListView;
import com.ct.ct_news_weight.RefreshListView.OnRefreshListener;
import com.example.ct_newss.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.viewpagerindicator.CirclePageIndicator;

public class NewsDetailPager extends TabDetailPager implements
		OnPageChangeListener {

	private CirclePageIndicator mindicator;
	private ViewPager mViewPager;

	private RefreshListView mRefreshListView;
	private View view;

	private int[] images = { R.drawable.p1, R.drawable.p2, R.drawable.p3, };

	
	private ArrayList<ImageView> arrayList;
	private View headerView;
	private List<TopNewsPhoto> mList_photo;
	private List<TopNewsText> mList_text;//这是listview里面所要显示的text
	private TextView tv_dec;
	private Handler handler;

	private MyAdapter myAdapter;
	
	private static final String  NEWS_TYPE="position";//新闻的类别
	private static final String  NEWS_TYPE_ROWS="rows";//新闻所要显示的条数
	private static final String NEWS_UPDATETIME="updatetime";//新闻的时间
	
	
	
	
	
	private boolean ismore = true;// 用来判断是否还要经过服务器

	public NewsDetailPager(Activity mActivity, List<TopNewsPhoto> mList_photo,
			List<TopNewsText> mList_text) {

		super(mActivity);
		this.mList_photo = mList_photo;
		this.mList_text = mList_text;
		myAdapter = new MyAdapter();

		mRefreshListView.setAdapter(myAdapter);

	}

	@Override
	public void initData() {
		initTopImageViews();

		mindicator = (CirclePageIndicator) headerView
				.findViewById(R.id.indicator);
		mViewPager = (ViewPager) headerView.findViewById(R.id.vp_top);
		mViewPager.setAdapter(new TopPager());
		mindicator.setViewPager(mViewPager);
		mindicator.setOnPageChangeListener(this);// 回调函数，用来监听的
		mindicator.onPageSelected(0);// 对他进行初始化

		initHandle();// 让viewpager动起来

	}

	private void initHandle() {

		handler = new Handler() {

			public void handleMessage(android.os.Message msg) {
				// 先获取当前的那一个地址
				int currentItem = mViewPager.getCurrentItem();

				if (currentItem < images.length - 1) {
					currentItem++;

				} else {
					currentItem = 0;

				}
				mViewPager.setCurrentItem(currentItem);

				handler.sendEmptyMessageDelayed(0, 3 * 1000);

			};

		};

		handler.sendEmptyMessageDelayed(0, 3 * 1000);

	}

	private void initTopImageViews() {
		arrayList = new ArrayList<ImageView>();

		//
		for (int i = 0; i < images.length; i++) {

			ImageView imageView = new ImageView(mActivity);
			imageView.setBackgroundResource(images[i]);
			arrayList.add(imageView);

		}

	}

	   class MyAdapter extends BaseAdapter implements OnScrollListener{
		private ImageLoader mImageLoader;
		
		private int startItem;
		private int endItem;
		public    String[] URLS;
		
		
		 public MyAdapter(){
			 
			 mImageLoader=new ImageLoader();
			 URLS=new String[mList_text.size()];
	    	for(int i=0;i<mList_text.size();i++){
			URLS[i]=mList_text.get(i).imageurl;
			
		}
			 
		 }
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList_text.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder=null;
		
			if (convertView == null) {
				convertView = View.inflate(mActivity,
						R.layout.activity_newsdetailtext, null);
				viewHolder=new ViewHolder();
				viewHolder.mImageView=(ImageView) convertView.findViewById(R.id.iv_news);
				viewHolder.tv_title = (TextView) convertView
						.findViewById(R.id.tv_title);
				viewHolder.tv_context = (TextView) convertView
						.findViewById(R.id.tv_context);
				viewHolder.tv_time = (TextView) convertView
						.findViewById(R.id.tv_time);
				convertView.setTag(viewHolder);

			}else{
				viewHolder=(ViewHolder) convertView.getTag();
				
			}
			String url=Config.SERVER_PHOTO+mList_text.get(position).imageurl;
			viewHolder.mImageView.setTag(url);

			mImageLoader.showImageByThread(viewHolder.mImageView, url);
		//	mImageLoader.showImageByAsyncTask(viewHolder.mImageView, url);
			viewHolder.tv_title.setText(mList_text.get(position).title);
			viewHolder.tv_context.setText(mList_text.get(position).context);
			viewHolder.tv_time.setText(com.ct.ct_news_utils.DateUtils.getDateToString(Long.parseLong(mList_text.get(position).updatetime)));

			// 寻找被按过的item
			String isreadItemAll = SharedPreferencesUtils.getString(mActivity,
					"isread", "");
			// 每一个getview显示的就是一个item

			String isreadItem = String.valueOf(mList_text.get(position).id);

			if (isreadItemAll.contains(isreadItem)) {
				viewHolder.tv_title.setTextColor(Color.RED);
				viewHolder.tv_context.setTextColor(Color.RED);

			}
			
			

			else {
				viewHolder.tv_title.setTextColor(Color.BLACK);
				viewHolder.tv_context.setTextColor(Color.BLACK);

			}

			return convertView;
		}
		
		
		class  ViewHolder{
			ImageView mImageView;
			TextView tv_title;
			TextView tv_context;
			TextView tv_time;
			
			
		}

//当状态转变的时候会调用
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			
		//停止状态
		if(scrollState==SCROLL_STATE_IDLE){
			
			
			
		}else{
			//取消所有异步加载
			
		}
		}

//滑动的时候会一直调用
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			startItem=firstVisibleItem;
			endItem=firstVisibleItem+visibleItemCount;
			
		}

	}

	class TopPager extends PagerAdapter {

		private BitmapUtils bitmapUtils;
		private ImageView imageView;

		public TopPager() {
			bitmapUtils = new BitmapUtils(mActivity);
			bitmapUtils.configDefaultLoadingImage(R.drawable.p1);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			imageView = new ImageView(mActivity);
			imageView.setScaleType(ScaleType.FIT_XY);// 填充画面

			bitmapUtils.display(imageView, Config.SERVER_PHOTO
					+ mList_photo.get(position).getUrl());
			container.addView(imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	@Override
	public View initView() {
		view = View.inflate(mActivity, R.layout.activity_newsdetailpager, null);

		headerView = View.inflate(mActivity, R.layout.activity_toppager, null);

		tv_dec = (TextView) headerView.findViewById(R.id.tv_dec);
		mRefreshListView = (RefreshListView) view
				.findViewById(R.id.refreshlistview);

		mRefreshListView.addHeaderView(headerView);
		// mRefreshListView.setOnItemClickListener(listener)
		mRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(mActivity, "我的位置是" + position, 1).show();

				// 存储已读的id
				setHasReadIds(position);

				// 。。
				// 设置成已读
				setHasRead(view);

				// 新闻的明细画面

				Intent intent = new Intent(mActivity, News_WebView.class);
				intent.putExtra("url", mList_text.get(position).url);
				mActivity.startActivity(intent);

			}
		});

		mRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				// 获取网络上的数据，下拉刷新
				getnewestlist();

			}

			private void getnewestlist() {
				HttpUtils http = new HttpUtils();
				RequestParams params = new RequestParams(HTTP.UTF_8);
				params.addQueryStringParameter(NEWS_TYPE,
						Config.LISTVIEW_NEWS_TYPE);
				params.addQueryStringParameter(NEWS_TYPE_ROWS, Config.LISTVIEW_ROWS);

				http.send(HttpMethod.GET, Config.LISTVIEW_SERVER_INTERVICE,
						params, new RequestCallBack<String>() {

							// private List<TopNewsText> list;

							@Override
							public void onLoading(long total, long current,
									boolean isUploading) {

							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								// textView.setText(responseInfo.result);
								String resultstring = (String) responseInfo.result;

							

								mList_text = new JsonParser<TopNewsText>()
										.parserJsonList(JsonResultSplit
												.split(resultstring),
												TopNewsText.class);

								// for(TopNewsText bean:mList){
								//
								// mList_text.add(0, bean);
								//
								// }
								LogUtils.d(resultstring);

								mRefreshListView.onRefreshComplete(true);
								myAdapter.notifyDataSetChanged();

								

							}

							@Override
							public void onStart() {
							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								mRefreshListView.onRefreshComplete(true);
								Toast.makeText(mActivity, "数据加载超级异常，请稍后再试", 0)
										.show();
							}

						});
			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub

				if (ismore) {

					getmorelist();

				} else {
					mRefreshListView.onRefreshComplete(true);

					Toast.makeText(mActivity, "数据库已经加载完全啦！", 0).show();

				}

			}

			private void getmorelist() {
				HttpUtils http = new HttpUtils();
				RequestParams params = new RequestParams(HTTP.UTF_8);
				params.addQueryStringParameter(NEWS_TYPE,
						Config.LISTVIEW_NEWS_TYPE_DOWN);
			

				params.addQueryStringParameter(NEWS_UPDATETIME,"2016-02-01 22:42:19");
				//mList_text.get(mList_text.size() - 1).updatetime
				params.addQueryStringParameter(NEWS_TYPE_ROWS,
						
						Config.LISTVIEW_ROWS_DOWN);

				http.send(HttpMethod.GET, Config.LISTVIEW_SERVER_INTERVICE_UP,
						params, new RequestCallBack<String>() {

							// private List<TopNewsText> list;

							@Override
							public void onLoading(long total, long current,
									boolean isUploading) {

							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								// textView.setText(responseInfo.result);
								String resultstring = (String) responseInfo.result;

								// JsonResultSplit.split(resultstring);

								List<TopNewsText> mList = new JsonParser<TopNewsText>()
										.parserJsonList(JsonResultSplit
												.split(resultstring),
												TopNewsText.class);
								if (mList.size() < Integer
										.parseInt(Config.LISTVIEW_ROWS_DOWN)) {
									ismore = false;

								}

								for (TopNewsText bean : mList) {

									mList_text.add(bean);

								}
								LogUtils.d(resultstring);

								mRefreshListView.onRefreshComplete(true);
								myAdapter.notifyDataSetChanged();

							}

							@Override
							public void onStart() {
							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								
								mRefreshListView.onRefreshComplete(true);
								Toast.makeText(mActivity, "数据加载异常，请稍后再试", 0)
										.show();
							}

						});
			}
		});

		return view;
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
		tv_dec.setText(mList_photo.get(position).getDec());
		;
	}

	// 存储已读的id
	private void setHasReadIds(int position) {
		String isreadposition = String.valueOf(mList_text.get(position).id);

		// .arg0.
		String isreadpositiongroup = SharedPreferencesUtils.getString(
				mActivity, "isread", "");
		if (isreadpositiongroup.contains(isreadposition) == false) {

			// ,1,2,3,4

			isreadpositiongroup += "," + isreadposition;
			SharedPreferencesUtils.setString(mActivity, "isread",
					isreadpositiongroup);

		}

	}

	// 设置成已读
	private void setHasRead(View view) {
		TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
		TextView tv_context = (TextView) view.findViewById(R.id.tv_context);
		tv_title.setTextColor(Color.RED);
		tv_context.setTextColor(Color.RED);

	}
	
	
	
	

}
