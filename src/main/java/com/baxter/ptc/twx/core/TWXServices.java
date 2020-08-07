package com.baxter.ptc.twx.core;

import org.json.simple.JSONObject;

import com.baxter.ptc.twx.TWXServicesInvoker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//list all services
public class TWXServices {
	
	public Response getThingProperty(String ThingName, String PropertyName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/"+ThingName+"/Properties/"+PropertyName)
				.headers(TWXConnectorPropeties.getHeaders()).body(jsonBody.toJSONString()).get();
		return responseT;
	}
	
	
	public Response getThingProperties(String ThingName) {
		Response response5 = TWXServicesInvoker.post(ThingName, "GetPropertyValues");
		return response5;
	}
	

	public Response setProperty(String ThingName, String proprtyName, Object propertyValue ) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put(proprtyName, propertyValue);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/"+ThingName+"/Properties/*?Accept=application%2Fjson&Content-Type=application%2Fjson")
				.headers(TWXConnectorPropeties.getHeaders()).body(jsonBody.toJSONString()).put();
		return responseT;
	}

	public Response setMultipleProperty(String ThingName, JSONObject body ) {
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/"+ThingName+"/Properties/*?Accept=application%2Fjson&Content-Type=application%2Fjson")
				.headers(TWXConnectorPropeties.getHeaders()).body(body.toJSONString()).put();
		return responseT;
	}
	
	public Response registerAmiaDevice(Integer serialNumber) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("serialNumber", serialNumber);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.DeviceRegistrationManager/Services/RegisterAmiaDevice")
				.headers(TWXConnectorPropeties.getHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}
	
	public Response registerClariaDevice(Integer serialNumber) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("serialNumber", serialNumber);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.DeviceRegistrationManager/Services/RegisterClariaDevice")
				.headers(TWXConnectorPropeties.getHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}
	
}
