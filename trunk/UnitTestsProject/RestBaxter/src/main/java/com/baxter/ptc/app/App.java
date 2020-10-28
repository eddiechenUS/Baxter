package com.baxter.ptc.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Blob;
import java.util.Date;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.crypto.Data;

import org.junit.Assert;

import com.baxter.ptc.twx.TWXResultGetter;
import com.baxter.ptc.twx.core.TWXServices;

import io.restassured.response.Response;

public class App {
	public static void main(String[] args) {
		// treatmentFile
		String treatmentFile ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
		String treatmentFile_wrong ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
		String checksum1 = "16A75D8D8C9A3D18A4358E892705F046";
		String FileName1 = "T_C_2_1501001701_00001.tar.gz.enc";
//		
//		
//		//settingFile request
		String settingFileRequest = "o4DypNNbDM/RPtZggS8hiyZ8qs0KdUH/ydGNFhvsiH4xJLQtKdLjskkHJ2j0pi/D3lR4+Hwql1+JLU1mNtIbSZIlyeDR8TmazRVScKd0i0/ur7E6biZUaPmDlw6bERCxRTibKzUi6Oe9NAzQWtB19VczlCykFTzvsUgSaZ7QeuMP987GbIZlvoXGKf9fMrVrFTQp+2PsFEsHvdLFaIAvuBpHrZq0BrAZmPwwWDpbEpwDDyhCKtfTASSYNFqFJgyrpiedA5LJH65Gz35kOnm1Kx41Fy+R/K6QZBF6k2N5dsiMdH0HxAW/DVtYcWTaFFAqkvPA575HXeUuwz6ro5a1hVz3wRJTPdQMg7D6HnaEd4vXZiKljHvkLTozBRjMZlqH+jni4cQBbK/V4cZvrUVuyjRjFn0TiEVEikVbdapLMYiHVVDUHi+079fMH1SXMHjopxvFFRi7LBi2RnBVgrFjAvjTHP6Wd8UBXQWXVKxcO+qr+OD+CiD98baN0DQXWMDUHwOUcVlDLysUFHUhttKktaUOP7atBc11pYzk1m+dnGGsi6I3OmrO3pVwSzZDm1yz6m7I0FW2XSWuQhNXT7NB/g==";
		String checksum2 = "766D234A2D811CC23EFCF584F40F037D";
		String FileName2 = "SQ_C_1_9999601000.tar.gz.enc";
		
		
		
		
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
//		Response r5 = st.registerClariaDevice(11111111);
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

//		st.checkClariaExist("test112");//test ok true
//		st.checkClariaExist("test112");//test ok false
//		st.checkClariaExist("Claria.6969699");//test ok
//		st.invokeClariaRegisterWithSerialNumber(6969633);
//		String testString = "{\"DeviceStatus\":\"DOWNVVVV\"}";
//		TWXServices.setClariaProperty(testString, "Claria.11223377");
//		String singlePropertyName = "DeviceStatus";
//		String FirstsinglePropertyValue = "SHUTTING_DOWN";
//		String SecondsinglePropertyValue = "POWERED_UPPPPP";
//		String device ="Claria.11223377";
//		
//		String testString = "{\""+singlePropertyName+"\":\""+FirstsinglePropertyValue+"\"}";
//		TWXServices.setClariaProperty(testString, device);
//		Response res=TWXServices.getClariaProperty(singlePropertyName, device);
//		System.out.println("first updated property is = "+singlePropertyName+" property value is "+TWXResultGetter.ShowProperty(res, singlePropertyName));
//	
//		String testString2 = "{\""+singlePropertyName+"\":\""+SecondsinglePropertyValue+"\"}";
//		TWXServices.setClariaProperty(testString2, "Claria.11223311");
//		Response res2=TWXServices.getClariaProperty(singlePropertyName, device);
//		System.out.println("Second updated property is = "+singlePropertyName+" property value is "+TWXResultGetter.ShowProperty(res2, singlePropertyName));

//		Integer serialNumber = 441188;
//		String deviceName = "Claria.".concat(serialNumber.toString());
//		TWXServices.registerClariaDevice(serialNumber);
//		Response res=TWXServicesInvoker.getThing("Claria.11223311");
//		System.out.println(res.asString());
//		TWXServices.registerClariaDevice(11223311);
//		TWXServices.getAction(deviceName);
//		TWXServices.getAsset(deviceName);
//		TWXServices.getTimeStamp(deviceName);
//		String word = TWXServices.DeleteFile("441/177/441177/TreatmentResults/Backup/T_C_2_1501001701_00001.tar.gz.enc");
//		System.out.println(word);
		//		String singlePropertyName = "DeviceStatus";
//		String FirstsinglePropertyValue = "POWERED_UPXXX";
//		Integer serialNumber = 441177;
//		String deviceName = "Claria.".concat(serialNumber.toString());
//		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
//		TWXServices.setClariaPropertyies(testString, deviceName);
		
		
//		String testString = "{\""+"isFileBackupEnabled"+"\":\"" + true + "\"}";
//		TWXServices.setClariaProperty(testString, "Baxter.FileBackupConfigurator");
//		TWXResultGetter.ShowBoolean(TWXServices.getClariaProperty("isFileBackupEnabled", "Baxter.FileBackupConfigurator"), "isFileBackupEnabled");
//		
//		
		
		
		
		
		
//		Integer serialNumber = 441177;
//		String deviceName = "Claria.".concat(serialNumber.toString());
//		
//		Response r1 =TWXServices.getDescendQuery(deviceName);
//	//	
//		Response r2 = TWXServices.getDescendQuery_BaxterAuditLogDataTable(deviceName);
//		
//		
//	 	//treatmentFile
//		String treatmentFile ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
//		String checksum1 = "16A75D8D8C9A3D18A4358E892705F046";
//		String FileName1 = "T_C_2_1501001701_00001.tar.gz.enc";
//		Response res=TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
//		Assert.assertEquals(200, res.getStatusCode());
//		
//		
		
		
//		Long serialnumber = (Long) new Date().getTime();
//		System.out.println(serialnumber);

//		
		
		
//		Response res=TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
//	P 	System.out.println("result  = "+TWXResultGetter.ShowBoolean(res, "result"));
//		
//		Response res=TWXServices.getClariaProperty("isReporting", deviceName);
//		System.out.println( TWXResultGetter.ShowBoolean(res, "isReporting"));
		
//		TWXServices.getFileRepositoryPath(deviceName);
//		TWXServices.getSettingResponseFileCombination(deviceName);
//		TWXServices.getAuditLogByDeviceName(deviceName);
//		TWXServices.getDescendQuery(deviceName);
////		TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
//		TWXServices.sendSettingsRequestFile(FileName2, checksum2, settingFileRequest, deviceName);
//		String result = TWXServices.getDownLoadLinkFromFileListingWithLinks("441/177/441177/TreatmentResults/Backup/");
//		if(result==null || (result!=null && result.length()==0)) {System.out.println("true");}else {System.out.println("false");};
//		TWXServices.getClariaProperty("SettingsRequestStatus", "Claria.441188");

		
//		Date d = new Date();
//		System.out.println("Date time is=" +d.getTime());
		
//		TWXServices.getLastRestCall(deviceName);
//		System.out.println("start");
//		TWXServices.Pause_N_Minute(0.15);//9sec
//		System.out.println("end");
//		TWXServices.getLastRestCall(deviceName);
//		TWXServices.getReportingLastChange(deviceName);
//		TWXServices.sendSettingsrequestFile(FileName, checksum, file, deviceName);
//		int serialnumber = (int) new Date().getTime();
//		String randomDate = "159"+serialnumber;
//		System.out.println(randomDate);
	}
	
	

}
