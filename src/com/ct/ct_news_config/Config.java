package com.ct.ct_news_config;
//配置文件
public class Config {
	// 网络访问ip
	public static final String SERVER_IP = "192.168.32.126";
	
	//数据库的地址
	public static final String SERVER_PHOTO="http://"+SERVER_IP+":8080";
	// 网络访问地址
    public static final String SERVER_ADDRESS="http://"+SERVER_IP+":8080/axis2/services/CT_News_Final/";
    
    
    
    
    
    
    
    
    //获取网络访问的接口
    public static final String SERVER_INTERVICE=SERVER_ADDRESS+"getTopNewsPhotoListGroup";
    
    //下拉刷新所要访问的接口
    public static final String LISTVIEW_SERVER_INTERVICE=SERVER_ADDRESS+"getNewestListviewText";
    
    //上拉刷新所要访问的接口
    public static final String LISTVIEW_SERVER_INTERVICE_UP=SERVER_ADDRESS+"getMoreerListviewText";
    
    
    //下拉刷新里面的position 代表的是indicator
    public static final String  LISTVIEW_NEWS_TYPE="1";
    //下拉刷新会显示的条数
    public static final String LISTVIEW_ROWS="10";
    
    //上拉刷新里面的position 代表的是indicator
    public static final String  LISTVIEW_NEWS_TYPE_DOWN="1";
    //上拉刷新会显示的条数
    public static final String LISTVIEW_ROWS_DOWN="10";
    //上拉刷新最上面的时间
   // public static final String LISTVIEW_LASTUPDATETIME="1";
    
}
