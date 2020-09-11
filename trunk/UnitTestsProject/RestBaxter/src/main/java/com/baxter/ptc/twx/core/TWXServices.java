package com.baxter.ptc.twx.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.baxter.ptc.twx.TWXResultGetter;
import com.baxter.ptc.twx.TWXServicesInvoker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//list all services
public class TWXServices {



	public static Response setClariaProperty(String JSONString, String ClariaDeviceName) {
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
		System.out.println(responseT.asString());
		return responseT;
	}

	public static Response setClariaPropertyies(String JSONString, String ClariaDeviceName) {
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

	public static Response getClariaProperty(String propertyName, String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Properties/" + propertyName)
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		System.out.println(responseT.asString());
		return responseT;
	}

	public static Response getSettingsresponseFileDetails(String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/GetResponseFileDetails")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}

	public static Response getFirmwarePackageNameAndSize(String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/GetFirmwarePackage")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		return responseT;
	}

	public static Boolean checkClariaExist(String thingName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("type", "Thing");
		jsonBody.put("nameMask", thingName);
//		jsonBody.put("thingName", thingName);
		jsonBody.put("maxItems", 1);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Resources/EntityServices/Services/GetEntityList")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		Boolean result = TWXResultGetter.checkClariaExistJSON(responseT, thingName);
//		System.out.println(responseT.asString());
		System.out.println("check if serial number already exist : " + result);
		return result;
	}

	public static void invokeClariaRegisterWithSerialNumber(Integer serialNumber) {
		String thingName = "Claria.".concat(serialNumber.toString());
		TWXServices ts = new TWXServices();
		Boolean result = ts.checkClariaExist(thingName);
		if (!result) { // If not exist then create one thing
			ts.registerClariaDevice(serialNumber);
			System.out.println("created a Claria");
			Boolean CheckResultAfterCreated = ts.checkClariaExist(thingName);
		} else {
//			System.out.println("The serial numbre already exist");
		}
	}

	public static Response registerClariaDevice(Integer serialNumber) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("serialNumber", serialNumber);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.DeviceRegistrationManager/Services/RegisterClariaDevice")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println(responseT.getStatusCode());
		System.out.println(responseT.asString());
		return responseT;
	}

	public static Response getLastRestCall(String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Properties/LastRestCall")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		Long LastRestCall = TWXResultGetter.getClariaLastRestCall(responseT);
		System.out.println("LastRestCall = " + LastRestCall);
//		System.out.println("LastRestCall Response= "+responseT.asString());
		return responseT;
	}

	public static Response getReportingLastChange(String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Properties/reportingLastChange")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		Long LastRestCall = TWXResultGetter.getClariaReportingLastChange(responseT);
		System.out.println("ReportingLastChange = " + LastRestCall);
		return responseT;
	}

	public static Response uploadTreatmentFile(String fileName, String checksum, String treatmentFile,
			String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileName", fileName);
		jsonBody.put("checksum", checksum);
		jsonBody.put("treatmentFile", treatmentFile);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/SaveTreatmentFile")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println(responseT.asString());
		return responseT;
	}
	
	public static Response sendSettingsRequestFile(String fileName, String checksum, String settingsFile,
			String ClariaDeviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileName", fileName);
		jsonBody.put("checksum", checksum);
		jsonBody.put("settingsFile", settingsFile);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + ClariaDeviceName + "/Services/SavePatientSettingsFileRequest")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println(responseT.asString());
		return responseT;
	}
	
	
	
	//work!  wait for N second
	public static void pause(double seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
		}
	}

	public static void Pause_N_Minute(double min) {
//		System.out.println("Start time="+System.currentTimeMillis());
		pause(min*60);
//		System.out.println("End time  ="+System.currentTimeMillis());
	}
	
	
	

}
