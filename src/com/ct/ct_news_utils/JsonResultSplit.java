package com.ct.ct_news_utils;

public class JsonResultSplit {

	public static String split(String result) {

		if (result == null) {

			return result;
		}

		String[] arrayStrings = result.split("<ns:return>");
		if (arrayStrings.length > 1) {
			result = arrayStrings[1];
		} else {

			return result;
		}

		return result.split("</ns:return>")[0];

	}

}
