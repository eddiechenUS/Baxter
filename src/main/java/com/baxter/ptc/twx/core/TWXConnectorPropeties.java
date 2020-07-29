package com.baxter.ptc.twx.core;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class TWXConnectorPropeties {
	
	public static Headers getHeaders(String appKey) {
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		list.add(new Header("appKey", appKey));
		list.add(new Header("Accept", "application/json"));
		list.add(new Header("x-thingworx-session", "true"));
		list.add(new Header("authType", "AUTH_THINGWORX_APPKEY"));
		return new Headers(list);
	}
	
	public static Headers getHeaders() {
		return getHeaders(TWXCredentials.APP_KEY);
	}
	
	public static String getPathForInvokeThingService(String thingName, String serviceName) {
		return "Things/"+thingName+"/Services/"+serviceName;
	}
	
	public static String getBaseUrl() {
		return TWXCredentials.BASE_URL;
	}
	
}
