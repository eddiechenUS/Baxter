package com.ptc.baxter.test.Claria;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.baxter.ptc.twx.TWXResultGetter;
import com.baxter.ptc.twx.TWXServicesInvoker;
import com.baxter.ptc.twx.core.TWXServices;

import io.restassured.response.Response;

public class ClariaTestCase1 {

	
	 static String DEVICENAME= "Claria.11223377";
	
	/**
	@org.junit.Test
	public void test1() {
		int a = 2;
		int b = 2;
		int c = a + b;
		Assert.assertEquals(4, c);
	}
	*/
	 
	 //testRegisterClaria
	@org.junit.Test
	public void testRegisterDevice() {//completed
		int serialnumber ;
//		int serialnumber = (int) new Date().getTime();
		serialnumber = 11223344;
		Response response;
		//Create new thing
		TWXServices.registerClariaDevice(serialnumber);
		//check in twx is exist
		response = TWXServicesInvoker.getThing("Claria." + serialnumber);
		System.out.println(response.getStatusCode());
		//add assert
		Assert.assertEquals(200, response.getStatusCode());
		//Create new thing
		response=TWXServices.registerClariaDevice(serialnumber);
		//check in twx is exist
		Assert.assertEquals(200, response.getStatusCode());
	}
	
	
	 @org.junit.Test
	public void testUpdateAndGetProperties() {//completed
		String singlePropertyName = "DeviceStatus";
		String FirstsinglePropertyValue = "SHUTTING_DOWN";
		String SecondsinglePropertyValue = "POWERED_UP";
		String device ="Claria.11223377";
		String testString = "{\""+singlePropertyName+"\":\""+FirstsinglePropertyValue+"\"}";
		//update property1
		TWXServices.setClariaProperty(testString, device);
		//get property value
		Response res=TWXServices.getClariaProperty(singlePropertyName, device);
//		System.out.println("first updated property is = "+singlePropertyName+" property value is "+TWXResultGetter.ShowProperty(res, singlePropertyName));
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res, singlePropertyName));
		
		String testString2 = "{\""+singlePropertyName+"\":\""+SecondsinglePropertyValue+"\"}";
		//update property1
		TWXServices.setClariaProperty(testString2, device);
		//get property value
		Response res2=TWXServices.getClariaProperty(singlePropertyName, device);
//		System.out.println("Second updated property is = "+singlePropertyName+" property value is "+TWXResultGetter.ShowProperty(res2, singlePropertyName));
		Assert.assertEquals(SecondsinglePropertyValue, TWXResultGetter.ShowProperty(res2, singlePropertyName));
	}
	
	
	 @org.junit.Test
	public void testUpdateRegisterAndGetProperty() {//incompleted = need to change to set multiple properties
		String singlePropertyName = "DeviceStatus";
		String FirstsinglePropertyValue = "POWERED_UPPP";
		Integer serialNumber= 11223377;
		String device ="Claria.".concat(serialNumber.toString());
		String testString = "{\""+singlePropertyName+"\":\""+FirstsinglePropertyValue+"\"}";
		// set property value
		TWXServices.setClariaProperty(testString, device);
		//register thing
		TWXServices.registerClariaDevice(serialNumber);
		//get property value
		Response res=TWXServices.getClariaProperty(singlePropertyName, device);
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res, singlePropertyName));
	}
	 
	 @org.junit.Test
	 public void checkIsReporting() {
		 
	 }
}
