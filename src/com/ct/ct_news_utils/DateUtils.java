package com.ct.ct_news_utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat sf = null;

	// 将时间戳转化成字符串
	public static String getDateToString(long time) {
		Date d = new Date(time);

		return new SimpleDateFormat("yyyy年MM月dd日 HH时MM分ss秒").format(d);

	}

	// 获取系统时间
	public static String getCurrentDate() {
		Date d = new Date();

		return new SimpleDateFormat("yyyy年MM月dd日").format(d);

	}

	// 将时间转化成时间戳

	public static long getStringToDate(String time) {
		sf = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date();
		try {
			date = sf.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date.getTime();
	}

}