package com.baxter.ptc.twx.core;

import java.sql.Blob;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.baxter.ptc.twx.TWXServicesInvoker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//list all services
public class TWXServices {

//	public Response getThingProperty(String ThingName, String PropertyName) {
//		JSONObject jsonBody = new JSONObject();
//		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
//				.basePath("Things/" + ThingName + "/Properties/" + PropertyName)
//				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
//		return responseT;
//	}
//
//	public Response getThingProperties(String ThingName) {
//		Response response5 = TWXServicesInvoker.post(ThingName, "GetPropertyValues");
//		return response5;
//	}
//
//	public Response setProperty(String ThingName, String propertyName, Object propertyValue) {
//		JSONObject jsonBody = new JSONObject();
//		jsonBody.put(propertyName, propertyValue);
//		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
//				.basePath("Things/" + ThingName
//						+ "/Properties/*?Accept=application%2Fjson&Content-Type=application%2Fjson")
//				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).put();
//		return responseT;
//	}
//
//	public Response setMultipleProperty(String ThingName, JSONObject body) {
//		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
//				.basePath("Things/" + ThingName
//						+ "/Properties/*?Accept=application%2Fjson&Content-Type=application%2Fjson")
//				.headers(TWXConnectorPropeties.getClariaHeaders()).body(body.toJSONString()).put();
//		return responseT;
//	}

	public Response setClariaProperty(String JSONString, String ClariaDeviceName) {
		JSONParser parser = new JSONParser();
		JSONObject jsonBody = null;
		try {
			jsonBody = (JSONObject) parser.parse(JSONString);
		} catch (ParseException e) {
			e.printStackTrace();
		}		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Properties/*")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).put();
		return responseT;
	}

//	public Response setClariaProperty2(String propertyName, String propertyValue, String ClariaDeviceName) {
//		JSONObject jsonBody = new JSONObject();
////		jsonBody.put(propertyName, propertyValue);
//		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
//				.basePath("Things/" + ClariaDeviceName + "/Properties/" + propertyName)
//				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).put();
//		return responseT;
//	}
	
	public Response setClariaPropertyies(String JSONString, String ClariaDeviceName) {
		JSONParser parser = new JSONParser();
		JSONObject jsonBody = null;
		try {
			jsonBody = (JSONObject) parser.parse(JSONString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Properties/*")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).put();
		return responseT;
	}
	
	public Response getClariaProperty(String propertyName, String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Properties/" + propertyName)
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		return responseT;
	}
	
	public Response sendSettingsrequestFile(String fileName, String checksum, Blob settingsFile, String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileName", fileName);
		jsonBody.put("checksum", checksum);
		jsonBody.put("settingsFile", settingsFile);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/SavePatientSettingsFileRequest")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}
	
	public Response getSettingsresponseFileDetails(String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/GetResponseFileDetails")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}
	
	public Response sendTreatmentResultFile(String fileName, String checksum, Blob treatmentFile, String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileName", fileName);
		jsonBody.put("checksum", checksum);
		jsonBody.put("treatmentFile", treatmentFile);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/SaveTreatmentFile")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}
	
	public Response getFirmwarePackageNameAndSize(String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/GetFirmwarePackage")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		return responseT;
	}
	
	public Response registerClariaDevice(Integer serialNumber) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("serialNumber", serialNumber);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.DeviceRegistrationManager/Services/RegisterClariaDevice")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}
	
//	public Response registerAmiaDevice(Integer serialNumber) {
//	JSONObject jsonBody = new JSONObject();
//	jsonBody.put("serialNumber", serialNumber);
//	Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
//			.basePath("Things/Baxter.DeviceRegistrationManager/Services/RegisterAmiaDevice")
//			.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
//	return responseT;
//}
}
