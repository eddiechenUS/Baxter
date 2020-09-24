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

	static String DEVICENAME = "Claria.11223377";

	/**
	 * @org.junit.Test public void test1() { int a = 2; int b = 2; int c = a + b;
	 *                 Assert.assertEquals(4, c); }
	 */

	
	
	
	
	// testRegisterClaria
	@org.junit.Test
	public void testRegisterDevice() {// completed
		int serialnumber;
//		int serialnumber = (int) new Date().getTime();
		serialnumber = 11223344;
		Response response;
		// Create new thing
		TWXServices.registerClariaDevice(serialnumber);
		// check in twx is exist
		response = TWXServicesInvoker.getThing("Claria." + serialnumber);
		System.out.println(response.getStatusCode());
		// add assert
		Assert.assertEquals(200, response.getStatusCode());
		// Create new thing
		response = TWXServices.registerClariaDevice(serialnumber);
		// check in twx is exist
		Assert.assertEquals(200, response.getStatusCode());
	}

	@org.junit.Test
	public void testUpdateAndGetProperties() {// completed
		String singlePropertyName = "DeviceStatus";
		String FirstsinglePropertyValue = "SHUTTING_DOWN";
		String SecondsinglePropertyValue = "POWERED_UP";
		String device = "Claria.11223377";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// update property1
		TWXServices.setClariaProperty(testString, device);
		// get property value
		Response res = TWXServices.getClariaProperty(singlePropertyName, device);
//		System.out.println("first updated property is = "+singlePropertyName+" property value is "+TWXResultGetter.ShowProperty(res, singlePropertyName));
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res, singlePropertyName));

		String testString2 = "{\"" + singlePropertyName + "\":\"" + SecondsinglePropertyValue + "\"}";
		// update property1
		TWXServices.setClariaProperty(testString2, device);
		// get property value
		Response res2 = TWXServices.getClariaProperty(singlePropertyName, device);
//		System.out.println("Second updated property is = "+singlePropertyName+" property value is "+TWXResultGetter.ShowProperty(res2, singlePropertyName));
		Assert.assertEquals(SecondsinglePropertyValue, TWXResultGetter.ShowProperty(res2, singlePropertyName));
	}

	@org.junit.Test
	public void testUpdateRegisterAndGetProperty() {// incompleted = need to change to set multiple properties
		String singlePropertyName = "DeviceStatus";
		String FirstsinglePropertyValue = "POWERED_UPPP";
		Integer serialNumber = 11223377;
		String device = "Claria.".concat(serialNumber.toString());
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// set property value
		TWXServices.setClariaProperty(testString, device);
		// register thing
		TWXServices.registerClariaDevice(serialNumber);
		// get property value
		Response res = TWXServices.getClariaProperty(singlePropertyName, device);
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res, singlePropertyName));
	}

	
	
	 	//treatmentFile
		String treatmentFile ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
		String checksum1 = "16A75D8D8C9A3D18A4358E892705F046";
		String FileName1 = "T_C_2_1501001701_00001.tar.gz.enc";
		
		
		//settingFile request
		String settingFileRequest = "o4DypNNbDM/RPtZggS8hiyZ8qs0KdUH/ydGNFhvsiH4xJLQtKdLjskkHJ2j0pi/D3lR4+Hwql1+JLU1mNtIbSZIlyeDR8TmazRVScKd0i0/ur7E6biZUaPmDlw6bERCxRTibKzUi6Oe9NAzQWtB19VczlCykFTzvsUgSaZ7QeuMP987GbIZlvoXGKf9fMrVrFTQp+2PsFEsHvdLFaIAvuBpHrZq0BrAZmPwwWDpbEpwDDyhCKtfTASSYNFqFJgyrpiedA5LJH65Gz35kOnm1Kx41Fy+R/K6QZBF6k2N5dsiMdH0HxAW/DVtYcWTaFFAqkvPA575HXeUuwz6ro5a1hVz3wRJTPdQMg7D6HnaEd4vXZiKljHvkLTozBRjMZlqH+jni4cQBbK/V4cZvrUVuyjRjFn0TiEVEikVbdapLMYiHVVDUHi+079fMH1SXMHjopxvFFRi7LBi2RnBVgrFjAvjTHP6Wd8UBXQWXVKxcO+qr+OD+CiD98baN0DQXWMDUHwOUcVlDLysUFHUhttKktaUOP7atBc11pYzk1m+dnGGsi6I3OmrO3pVwSzZDm1yz6m7I0FW2XSWuQhNXT7NB/g==";
		String checksum2 = "766D234A2D811CC23EFCF584F40F037D";
		String FileName2 = "SQ_C_1_9999601000.tar.gz.enc";
	 

	
	@org.junit.Test
	public void testRequestAndDownloadSettingsFile() {
		String singlePropertyName = "SettingsRequestStatus";
		String FirstsinglePropertyValue = "IDLE";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		Integer serialNumber = 441188;
		String deviceName = "Claria.".concat(serialNumber.toString());
		//set property SettingsRequestStatus  to "IDLE"
		TWXServices.setClariaProperty(testString, deviceName);
		Response res=TWXServices.getClariaProperty("SettingsRequestStatus", deviceName);
		Assert.assertEquals("IDLE", TWXResultGetter.ShowProperty(res, singlePropertyName));
		//send setting file request 
		TWXServices.sendSettingsRequestFile(FileName2, checksum2, settingFileRequest, deviceName);
		//check property SettingsRequestStatus  whether change to "REQUEST_UPLOADED"  , but it would be too fast to change from "REQUEST_UPLOADED" to "SETTINGS_AVAILABLE", therefore I check for "SETTINGS_AVAILABLE".
		Response res2=TWXServices.getClariaProperty(singlePropertyName, deviceName);
		while(!TWXResultGetter.ShowProperty(res2, singlePropertyName).equals("SETTINGS_AVAILABLE")) {
			res2=TWXServices.getClariaProperty(singlePropertyName, deviceName);
		}
		//check whether download file process is fine, status code = 200 
		Assert.assertEquals(200, TWXServices.getSettingResponseFileCombination(deviceName));
		String testString2 = "{\"" + singlePropertyName + "\":\""+"SETTINGS_DOWNLOADED"+"\"}";
		TWXServices.setClariaProperty(testString2, deviceName);
		Response res3=TWXServices.getClariaProperty(singlePropertyName, deviceName);
		//check if property SettingsRequestStatus change to value "SETTINGS_DOWNLOADED"
		Assert.assertEquals("SETTINGS_DOWNLOADED", TWXResultGetter.ShowProperty(res3, singlePropertyName));
	}
	

	
	@org.junit.Test
	public void testUploadTreamentFile() {
		Integer serialNumber = 441188;
		String deviceName = "Claria.".concat(serialNumber.toString());
		Response res=TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
		Assert.assertEquals(200, res.getStatusCode());
	}
	

	@org.junit.Test
	public void testIsReporting() {
		Integer serialNumber = 441188;
		String singlePropertyName = "LastRestCall";
		Date d = new Date();
		long FirstsinglePropertyValue = d.getTime();
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		String deviceName = "Claria.".concat(serialNumber.toString());
		TWXServices.setClariaProperty(testString, deviceName);
		Response res=TWXServices.getClariaProperty("isReporting", deviceName);
		//check isReporting property if it's true
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res, "isReporting"));
		//wait for 5 mins
		TWXServices.Pause_N_Minute(5);
		//check isReporting property if it's false
		Response res2=TWXServices.getClariaProperty("isReporting", deviceName);
		Assert.assertEquals(false, TWXResultGetter.ShowBoolean(res2, "isReporting"));
	}
	
//	Assert.assertEquals("", TWXResultGetter.ShowBoolean(res2, "isReporting"));
}
