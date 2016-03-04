package com.ct.ct_news_utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat sf = null;

	// ��ʱ���ת�����ַ���
	public static String getDateToString(long time) {
		Date d = new Date(time);

		return new SimpleDateFormat("yyyy��MM��dd�� HHʱMM��ss��").format(d);

	}

	// ��ȡϵͳʱ��
	public static String getCurrentDate() {
		Date d = new Date();

		return new SimpleDateFormat("yyyy��MM��dd��").format(d);

	}

	// ��ʱ��ת����ʱ���

	public static long getStringToDate(String time) {
		sf = new SimpleDateFormat("yyyy��MM��dd��");
		Date date = new Date();
		try {
			date = sf.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date.getTime();
	}

}