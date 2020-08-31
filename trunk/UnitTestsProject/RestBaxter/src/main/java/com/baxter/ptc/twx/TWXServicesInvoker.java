package com.baxter.ptc.twx;

import org.json.simple.JSONObject;

import com.baxter.ptc.twx.core.TWXConnectorPropeties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TWXServicesInvoker {

	public static Response post(String baseUrl, String basePath, Headers headers, String body) {
		return RestAssured.given().baseUri(baseUrl).basePath(basePath).headers(headers).body(body).post();
	}
	
	public static Response post(String baseUrl, String basePath, Headers headers, JSONObject body) {
		return post(baseUrl, basePath, headers, body.toJSONString());
	}
	
	public static Response post(String thingName, String serviceName, String appKey, JSONObject body) {
		return post(TWXConnectorPropeties.getBaseUrl(), TWXConnectorPropeties.getPathForInvokeThingService(thingName, serviceName), 
				TWXConnectorPropeties.getHeaders(appKey), body);
	}
	
	public static Response post(String thingName, String serviceName, JSONObject body) {
		return post(TWXConnectorPropeties.getBaseUrl(), TWXConnectorPropeties.getPathForInvokeThingService(thingName, serviceName), 
				TWXConnectorPropeties.getHeaders(), body);
	}
	
	public static Response post(String thingName, String serviceName) {
		return post(thingName, serviceName, new JSONObject());
	}
	
	public static Response getThing(String thingName) {
		return RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl()).
				basePath(TWXConnectorPropeties.getPathForGetThing(thingName)).headers(TWXConnectorPropeties.getHeaders()).get();
	}
	
}
