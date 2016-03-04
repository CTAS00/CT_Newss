package com.ct.ct_news_utils;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class JsonParser<T> {

	public T parserJsonBean(String jsonString, Class<T> bean) {
		try {
			return JSONObject.parseArray(jsonString, bean).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public List<T> parserJsonList(String jsonString, Class<T> bean) {
		try {
			return JSONObject.parseArray(jsonString, bean);
			// JSONObject.parseArray(arg0)
		} catch (Exception e) {
			return null;
		}
	}

}
