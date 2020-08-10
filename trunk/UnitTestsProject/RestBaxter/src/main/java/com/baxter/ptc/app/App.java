package com.baxter.ptc.app;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.baxter.ptc.twx.TWXResultGetter;
import com.baxter.ptc.twx.core.TWXConnectorPropeties;
import com.baxter.ptc.twx.core.TWXServices;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class App {
	public static void main(String [] args) {
//		System.out.println("Hello Baxter");
//		
//		//easy (not good) example 
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("Num1", 1.0);
//		requestParams.put("Num2", 2.0);
//		Header h1 = new Header("Content-Type", "application/json");
//		Header h2 = new Header("appKey", "c8285bf0-e95d-416c-87de-934820f123a6");
//		Header h3 = new Header("Accept", "application/json");
//		Header h4 = new Header("x-thingworx-session", "true");
//		Header h5 = new Header("authType", "AUTH_THINGWORX_APPKEY");
//		List<Header> list = new ArrayList<Header>();
//		list.add(h1);
//		list.add(h2);
//		list.add(h3);
//		list.add(h4);
//		list.add(h5);
//		Headers header = new Headers(list);
//		System.out.println("TWX 1 TIME");
//		Response response1 = RestAssured.given().baseUri("https://baxter-dev4.cloud.thingworx.com/Thingworx/").basePath("Things/TestRest/Services/AddTwoNumbers")
//				.headers(header).body(requestParams.toJSONString()).post();
//		System.out.println(response1.getBody().asString());
//		int statusCode1 = response1.getStatusCode();
//		System.out.println(statusCode1);
//		
//		
//	
//		//good example
//		JSONObject jsonBody = new JSONObject();
//		jsonBody.put("Num1", 3.0);
//		jsonBody.put("Num2", 4.0);
//		System.out.println("TWX 2 TIME");
//		Response response2 = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
//				.basePath(TWXConnectorPropeties.getPathForInvokeThingService("TestRest", "AddTwoNumbers"))
//				.headers(TWXConnectorPropeties.getHeaders()).body(jsonBody.toJSONString()).post();
//		System.out.println(response2.getBody().asString());
//		int statusCode2 = response2.getStatusCode();
//		System.out.println(statusCode2);
//		
//		
//		System.out.println("getBaseUrl() ="+TWXConnectorPropeties.getBaseUrl());
//		System.out.println(".getPathForInvokeThingService ="+TWXConnectorPropeties.getPathForInvokeThingService("TestRest", "AddTwoNumbers"));
//		System.out.println(".getHeaders() ="+TWXConnectorPropeties.getHeaders());
//		
//
//		
//	
//		//best example
//		JSONObject body = new JSONObject();
//		body.put("Num1", 5.0);
//		body.put("Num2", 6.0);
//		System.out.println("TWX 3 TIME");
//		Response response3 = TWXServicesInvoker.post("TestRest", "AddTwoNumbers", body);
//		System.out.println(response3.getBody().asString());
//		int statusCode3 = response3.getStatusCode();
//		System.out.println(statusCode3);
//		
//		System.out.println("TWX 4 TIME");
//		Response response4 = TWXServicesInvoker.post("TestRest", "GetText");
//		System.out.println(response4.getBody().asString());
//		int statusCode4 = response4.getStatusCode();
//		System.out.println(statusCode4);
//		
//		
//		System.out.println("TWX 5 TIME");
//		Response response5 = TWXServicesInvoker.post("Amia@AmiaTesting6", "GetPropertyValues");
//		System.out.println(response5.getBody().asString());
//		int statusCode5 = response5.getStatusCode();
//		System.out.println(statusCode5);
//		
		TWXServices st = new TWXServices();
		
//		Response r1 = st.getThingProperties("Amia@AmiaTesting6");
//		System.out.println(r1.getBody().asString());
//		System.out.println(r1.getStatusCode());
//			
//		Response r2 = st.getThingProperty("Amia@AmiaTesting6","SettingsResponsePath");
//		System.out.println(r2.getBody().asString());
//		System.out.println(r2.getStatusCode());
//		
//		Response r3 = st.setProperty("Amia@AmiaTesting6", "language", "lllllllkk");
//		System.out.println(r3.getBody().asString());
//		System.out.println(r3.getStatusCode());
//		
//		
//		JSONObject jsonObject = new JSONObject();
//		//"{\"language\":\"uuuu\",\"shareKey\":\"ooo\"}"
//		JSONParser parser = new JSONParser();
//		try {
//			jsonObject = (JSONObject) parser.parse("{\"language\":\"ukkkuuu\",\"sharedKey\":\"ookkko\"}");
//			Response r4 = st.setMultipleProperty("Amia@AmiaTesting6", jsonObject);
//			System.out.println(r4.getBody().asString());
//			System.out.println(r4.getStatusCode());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		
//		Response r5 = st.registerAmiaDevice(556679);
//		System.out.println(r5.getBody().asString());
//		System.out.println(r5.getStatusCode());
//		
//		Response r6 = st.registerAmiaDevice(775569);
//		System.out.println(r6.getBody().asString());
//		System.out.println(r6.getStatusCode());
//		TWXResultGetter.distributor(TWXResultGetter.categorize(TWXResultGetter.showResult()));
		
//		Response r6 = st.testResponse2();
//		System.out.println(r6.getBody().asString());
//		System.out.println(r6.getStatusCode());
		
		
	}
}
