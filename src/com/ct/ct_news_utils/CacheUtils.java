package com.ct.ct_news_utils;

import android.content.Context;

public class CacheUtils {

	// 存取数据
	public static void setCache(Context context, String key, String value) {

		SharedPreferencesUtils.setString(context, key, value);

	}

	// 取数据

	public static String getCache(Context context, String key, String defaultvalue) {
	return 	SharedPreferencesUtils.getString(context, key, defaultvalue);

	}

}
