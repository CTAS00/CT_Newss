package com.ct.ct_news_config;
//�����ļ�
public class Config {
	// �������ip
	public static final String SERVER_IP = "192.168.32.126";
	
	//���ݿ�ĵ�ַ
	public static final String SERVER_PHOTO="http://"+SERVER_IP+":8080";
	// ������ʵ�ַ
    public static final String SERVER_ADDRESS="http://"+SERVER_IP+":8080/axis2/services/CT_News_Final/";
    
    
    
    
    
    
    
    
    //��ȡ������ʵĽӿ�
    public static final String SERVER_INTERVICE=SERVER_ADDRESS+"getTopNewsPhotoListGroup";
    
    //����ˢ����Ҫ���ʵĽӿ�
    public static final String LISTVIEW_SERVER_INTERVICE=SERVER_ADDRESS+"getNewestListviewText";
    
    //����ˢ����Ҫ���ʵĽӿ�
    public static final String LISTVIEW_SERVER_INTERVICE_UP=SERVER_ADDRESS+"getMoreerListviewText";
    
    
    //����ˢ�������position �������indicator
    public static final String  LISTVIEW_NEWS_TYPE="1";
    //����ˢ�»���ʾ������
    public static final String LISTVIEW_ROWS="10";
    
    //����ˢ�������position �������indicator
    public static final String  LISTVIEW_NEWS_TYPE_DOWN="1";
    //����ˢ�»���ʾ������
    public static final String LISTVIEW_ROWS_DOWN="10";
    //����ˢ���������ʱ��
   // public static final String LISTVIEW_LASTUPDATETIME="1";
    
}
