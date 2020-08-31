package com.ptc.baxter.test.Claria;

import java.util.Date;

import org.junit.Assert;

import com.baxter.ptc.twx.TWXServicesInvoker;
import com.baxter.ptc.twx.core.TWXServices;

import io.restassured.response.Response;

public class ClariaTestCase1 {

	/**
	@org.junit.Test
	public void test1() {
		int a = 2;
		int b = 2;
		int c = a + b;
		Assert.assertEquals(4, c);
	}
	*/
	@org.junit.Test
	public void testRegisterDevice() {
		TWXServices twx = new TWXServices();
		int serialnumber = (int) new Date().getTime();
		//register device
		twx.registerClariaDevice(serialnumber);
		//check in twx is exist
		Response response = TWXServicesInvoker.getThing("Claria." + serialnumber);
		//add assert
		Assert.assertEquals(200, response.getStatusCode());
	}
	
	@org.junit.Test
	public void testUpdateAndGetProperty() {
	}
	
}
