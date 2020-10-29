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

	public static Response setClariaProperty(String JSONString, String deviceName) {
		RestAssured.useRelaxedHTTPSValidation();

		JSONParser parser = new JSONParser();
		JSONObject jsonBody = null;
		try {
			jsonBody = (JSONObject) parser.parse(JSONString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Properties/*")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).put();
		System.out.println("status code ="+responseT.getStatusCode());
		System.out.println(responseT.asString());
		return responseT;
	}

	public static Response setClariaPropertyies(String JSONString, String deviceName) {
		JSONParser parser = new JSONParser();
		JSONObject jsonBody = null;
		try {
			jsonBody = (JSONObject) parser.parse(JSONString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Properties/*")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).put();
		return responseT;
	}

	public static Response getClariaProperty(String propertyName, String deviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Properties/" + propertyName)
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		System.out.println(responseT.asString());
		return responseT;
	}
	
	public static Boolean getBaxterFileBackupConfigurator_IsFileBackupEnabled(String propertyName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.FileBackupConfigurator/Properties/" + propertyName)
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		Boolean result = TWXResultGetter.getIsFileBackupEnabled(responseT);
		System.out.println(responseT.asString());
		return result;
	}
	public static Boolean getBaxterFileBackupConfigurator_ClariaTreatmentFileBackup(String propertyName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.FileBackupConfigurator/Properties/" + propertyName)
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		Boolean result = TWXResultGetter.getClariaTreatmentFileBackup(responseT);
		System.out.println(responseT.asString());
		return result;
	}

	public static Response getSettingsresponseFileDetails(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/GetResponseFileDetails")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		return responseT;
	}

	public static Response getFirmwarePackageNameAndSize(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/GetFirmwarePackage")
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
	
	
	
	public static Boolean getSettingsResponseFileDetails(String deviceName, String settingReqestFileName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/GetResponseFileDetails")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		Boolean IfMatch =  TWXResultGetter.getSettingsResponseFileDetailsMatchName(responseT, settingReqestFileName);
		System.out.println(responseT.asString());
		return IfMatch;
	}
	

	public static Response getLastRestCall(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Properties/LastRestCall")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		Long LastRestCall = TWXResultGetter.getClariaLastRestCall(responseT);
		System.out.println("LastRestCall = " + LastRestCall);
//		System.out.println("LastRestCall Response= "+responseT.asString());
		return responseT;
	}

	public static Response getReportingLastChange(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Properties/reportingLastChange")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
		Long LastRestCall = TWXResultGetter.getClariaReportingLastChange(responseT);
		System.out.println("ReportingLastChange = " + LastRestCall);
		return responseT;
	}

	public static Response uploadTreatmentFile(String fileName, String checksum, String treatmentFile,
			String deviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileName", fileName);
		jsonBody.put("checksum", checksum);
		jsonBody.put("treatmentFile", treatmentFile);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/SaveTreatmentFile")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println(responseT.asString());
		return responseT;
	}
	
	public static Response sendSettingsRequestFile(String fileName, String checksum, String settingsFile,
			String deviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileName", fileName);
		jsonBody.put("checksum", checksum);
		jsonBody.put("settingsFile", settingsFile);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/SavePatientSettingsFileRequest")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println(responseT.asString());
		return responseT;
	}
	
	
	// valid input 441/177/441177/TreatmentResults
	// valid input 441/177/441177/SettingsRequest
	// valid input 441/177/441177/SettingsResponse
	public static String DeleteFolder(String pathUnderBaxterClariaRepo) {//path: 441/177/441177/TreatmentResults
		RestAssured.useRelaxedHTTPSValidation();
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("path", pathUnderBaxterClariaRepo);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.ClariaFileRepository/Services/DeleteFolder")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		Integer statusCode = responseT.getStatusCode();
//		System.out.println("status code"+statusCode);
		if(!statusCode.equals(200)){
			System.out.println(statusCode);
			return "Folder No exist";
		}
		else{ 
			System.out.println(statusCode);
			return "delete ok";//delete successfully
		}
	}
	
	
	//after save/upload setting file
	//get setting file step 1
	//service:   - input : TreatmentResults return 441/177/441177/TreatmentResults or input : SettingsRequest return 441/177/441177/SettingsRequest
	// if need to check whether in the backup, then input  /441/188/441188/SettingsResponse/Backup/ in the following GetFileListingWithLinks service in Thing: Baxter.ClariaFileRepository
	public static String getFileRepositoryPath_TreatmentFile(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileType", "TreatmentResults");
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/GetFileRepositoryPath")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
//		System.out.println("response = "+responseT.asString());
		String path=TWXResultGetter.ShowResultValue(responseT);
//		System.out.println("path = "+path);
		return path;
	}
	
	//after save/upload setting file
	//get setting file step 1
	//service:   - input : TreatmentResults return 441/177/441177/TreatmentResults or input : SettingsRequest return 441/177/441177/SettingsRequest
	// if need to check whether in the backup, then input  /441/188/441188/SettingsResponse/Backup/ in the following GetFileListingWithLinks service in Thing: Baxter.ClariaFileRepository
	public static String getFileRepositoryPath_SettingResponse(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileType", "SettingsResponse");
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/GetFileRepositoryPath")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
//		System.out.println("response = "+responseT.asString());
		String path=TWXResultGetter.ShowResultValue(responseT);
		System.out.println("path = "+path);
		return path;
	}
	
	//after save/upload setting file
	//get setting file step 1
	//service:   - input : TreatmentResults return 441/177/441177/TreatmentResults or input : SettingsRequest return 441/177/441177/SettingsRequest
	// if need to check whether in the backup, then input  /441/188/441188/SettingsResponse/Backup/ in the following GetFileListingWithLinks service in Thing: Baxter.ClariaFileRepository
	public static String getFileRepositoryPath_SettingRequest(String deviceName) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("fileType", "SettingsRequest");
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/" + deviceName + "/Services/GetFileRepositoryPath")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
//		System.out.println("response = "+responseT.asString());
		String path=TWXResultGetter.ShowResultValue(responseT);
		System.out.println("path = "+path);
		return path;
	}
	
	
	//get setting file step 2
	public static String getDownLoadLinkFromFileListingWithLinks(String path) {
		String downloadLink = "";
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("path", "/"+path+"/");
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.ClariaFileRepository/Services/GetFileListingWithLinks")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
//		System.out.println("response = " + responseT.asString());
		downloadLink=TWXResultGetter.ShowResultValueByColName(responseT, "downloadLink");
//		String path=TWXResultGetter.StringResult(responseT);
		//if download link = null - > no data, no file exist
		if(downloadLink==null || (downloadLink!=null && downloadLink.length()==0)) {
			System.out.println("Link has no data");
			downloadLink = "NoData";
			return downloadLink;
//			System.out.println("downloadLink ="+downloadLink+"end");
		}else{
			return downloadLink; 
//			System.out.println("downloadLink ="+downloadLink+"end");
			}
	}
	
	
	//get setting file step 2
	public static String getDownLoadLinkFromFileListingWithLinks_In_BackupFile(String path) {
		String downloadLink = "";
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("path", "/"+path+"/Backup/");//      /441/188/441188/SettingsResponse/Backup/
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.ClariaFileRepository/Services/GetFileListingWithLinks")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		Integer statusCode = responseT.getStatusCode();
		System.out.println("status code here = "+ statusCode);
		if(statusCode.equals(500)) {
			return "not exist";
		}
		
//		System.out.println("response = " + responseT.asString());
		downloadLink=TWXResultGetter.ShowResultValueByColName(responseT, "downloadLink");
//		String path=TWXResultGetter.StringResult(responseT);
		//if download link = null - > no data, no file exist
		if(downloadLink==null || (downloadLink!=null && downloadLink.length()==0)) {
			return "NoData";
//			System.out.println("downloadLink ="+downloadLink+"end");
		}else{
			return downloadLink; 
//			System.out.println("downloadLink ="+downloadLink+"end");
			}
	}
	
	//get setting file step 3
	//shows settingRequest content and settingResponse file content and treatment file content
	public static Integer getFiles(String downloadLink) {
		if(downloadLink.equals("not exist")) {
			return 0;
		}else if(!downloadLink.equals("NoData")) {
			JSONObject jsonBody = new JSONObject();
			jsonBody.put("path", "/"+downloadLink+"/");
			Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
					.basePath(downloadLink.substring(10))
					.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).get();
			int statusCode=responseT.getStatusCode();
//			System.out.println("status code = " + statusCode);
			System.out.println("return content = " + responseT.asString());
			return statusCode;
		}else {
			return 0;//if status code = 0, then no data exist, or no link exist
		}
	}
	
	//if status code = 0, then no data exist, or no link exist
	public static int getSettingRequestFileCombination_SettingRequest(String deviceName) {
		int statusCode=getFiles(getDownLoadLinkFromFileListingWithLinks(getFileRepositoryPath_SettingRequest(deviceName)));
		System.out.println("status code for normal= " + statusCode);
		return statusCode;
	}
	
	//if status code = 0, then no data exist, or no link exist
	public static int getSettingRequestFileCombination_backup_SettingRequest(String deviceName) {
		int statusCode=getFiles(getDownLoadLinkFromFileListingWithLinks_In_BackupFile(getFileRepositoryPath_SettingRequest(deviceName)));
		System.out.println("status code for backup SettingRequest= " + statusCode);
		return statusCode;
	}
	//if status code = 0, then no data exist, or no link exist
	public static int getSettingResponseFileCombination_SettingResponse(String deviceName) {
		int statusCode=getFiles(getDownLoadLinkFromFileListingWithLinks(getFileRepositoryPath_SettingResponse(deviceName)));
		System.out.println("status code for normal SettingResponse= " + statusCode);
		return statusCode;
	}
	
	//if status code = 0, then no data exist, or no link exist
	public static int getSettingResponseFileCombination_backup_SettingResponse(String deviceName) {
		int statusCode=getFiles(getDownLoadLinkFromFileListingWithLinks_In_BackupFile(getFileRepositoryPath_SettingResponse(deviceName)));
		System.out.println("status code = " + statusCode);
		return statusCode;
	}
	
	//if status code = 0, then no data exist, or no link exist
	public static int getTreatmentFileFileCombination_TreatmentFile(String deviceName) {
		int statusCode=getFiles(getDownLoadLinkFromFileListingWithLinks(getFileRepositoryPath_TreatmentFile(deviceName)));
		System.out.println("status code = " + statusCode);
		return statusCode;
	}
	
	//if status code = 0, then no data exist, or no link exist
	public static int getTreatmentFileCombination_backup_TreatmentFile(String deviceName) {
		RestAssured.useRelaxedHTTPSValidation();
		int statusCode=getFiles(getDownLoadLinkFromFileListingWithLinks_In_BackupFile(getFileRepositoryPath_TreatmentFile(deviceName)));
		System.out.println("status code = " + statusCode);
		return statusCode;
	}
	
	
	public static Response getAuditLogByDeviceName(String deviceName) {
		String JSONString = "{\"values\":{\"dataShape\":{\"fieldDefinitions\":{\"logTimestamp\":{\"name\":\"logTimestamp\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"DATETIME\",\"ordinal\":5},\"name\":{\"name\":\"name\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":7},\"action\":{\"name\":\"action\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":2},\"id\":{\"name\":\"id\",\"aspects\":{\"isPrimaryKey\":true},\"description\":\"\",\"baseType\":\"GUID\",\"ordinal\":4},\"asset\":{\"name\":\"asset\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":3},\"category\":{\"name\":\"category\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":6}}},\"rows\":[{\"asset\":\""+deviceName+"\"}]},\"maxItems\":10}";
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.AuditLogDataTable/Services/QueryDataTableEntries")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(JSONString).post();
		System.out.println(responseT.asString());
		return responseT;
	}
	
	
	public static Response getDescendQuery(String deviceName) {
		RestAssured.useRelaxedHTTPSValidation();
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/TestThing1222/Services/descendQueryByAsset")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println("getDescendQuery"+responseT.asString());
		return responseT;
	}
	
	//
	public static Response getDescendQuery_BaxterAuditLogDataTable(String deviceName) {
		RestAssured.useRelaxedHTTPSValidation();
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/TestThing1222/Services/descendQueryDataTable")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println("getDescendQuery_BaxterAuditLogDataTable"+responseT.asString());
		return responseT;
	}
	
	
	public static String getAction(String deviceName) {
		String action=TWXResultGetter.getAction(getDescendQuery(deviceName));
		System.out.println("action = "+action);
		return action;
	}
	public static String getCategory(String deviceName) {
		String category=TWXResultGetter.getCategory(getDescendQuery(deviceName));
		System.out.println("category = "+category);
		return category;
	}
	public static String getName(String deviceName) {
		String name=TWXResultGetter.getName(getDescendQuery(deviceName));
		System.out.println("name = "+name);
		return name;
	}
	
	public static String getAsset(String deviceName) {
		String action=TWXResultGetter.getAsset(getDescendQuery(deviceName));
		System.out.println("asset = "+action);
		return action;
	}
	
	public static Long getTimeStamp(String deviceName) {
		Long timestamp=TWXResultGetter.getTimeStamp(getDescendQuery(deviceName));
		System.out.println("timestamp = "+timestamp);
		return timestamp;
	}
	
	
	//work!  wait for N second
	public static void pause(double seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
		}
	}
	
	
	//Execute service Claria.441177/Services/GetFirmwarePackageSize
	public static Response getFirmwarePackageSize(String deviceName) {
		RestAssured.useRelaxedHTTPSValidation();
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/"+deviceName+"/Services/GetFirmwarePackageSize")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println("GetFirmwarePackageSize infotable");
		System.out.println(responseT.asString());
		return responseT;
	}
	

	public static void Pause_N_Minute(double min) {
//		System.out.println("Start time="+System.currentTimeMillis());
		pause(min*60);
//		System.out.println("End time  ="+System.currentTimeMillis());
	}
	
	
	public static String returnClariaDeviceFilePath(String deviceName) {
//		System.out.println("Claria.441177".substring(0, 7)); //Claria
//		System.out.println("Claria.441177".substring(7));    //441177
//		System.out.println("Claria.441177".substring(7,10)); //441
//		System.out.println("Claria.441177".substring(10,13));//177
		String SerialNumber = deviceName.substring(7);    //441177
		String FirstName =deviceName.substring(7,10); //441
		String SecName = deviceName.substring(10,13);//177
		String path = FirstName+"/"+SecName+"/"+SerialNumber+"/";
		System.out.println(path);//  show: 441/177/441177/
		return path;
	}
	
	public static void ClearFolderOperation(String deviceName) {
		String path = TWXServices.returnClariaDeviceFilePath(deviceName);
		TWXServices.DeleteFolder(path + "TreatmentResults");
		TWXServices.DeleteFolder(path + "SettingsRequest");
		TWXServices.DeleteFolder(path + "SettingsResponse");
	}
	
	public static String returnTreatmentFilePath(String deviceName) {
		return returnClariaDeviceFilePath(deviceName)+"TreatmentResults";
	}
	
	public static String returnSettingsRequestFilePath(String deviceName) {
		return returnClariaDeviceFilePath(deviceName)+"SettingsRequest";
	}
	
	public static String returnSettingsResponseFilePath(String deviceName) {
		return returnClariaDeviceFilePath(deviceName)+"SettingsResponse";
	}
	
	
	//===============================Retry Test Case Services==========================================================
	
	//DataTableName = Baxter.UploadFailureDataTable, Baxter.UploadSuccessDataTable, Baxter.RetryDataTable
	//Column Name = timestamp, attempt, deviceName, repository, path
	//Use different Getter!
	public static Response getDataTableColumnDataByTimeDescend(String DataTableName) {//descend: Latest data on the top of the infoTable
		RestAssured.useRelaxedHTTPSValidation();
		String JSONString ="{\"values\":{\"dataShape\":{\"fieldDefinitions\":{\"path\":{\"name\":\"path\",\"aspects\":{\"isPrimaryKey\":true},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":2},\"fileName\":{\"name\":\"fileName\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":5},\"repository\":{\"name\":\"repository\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"THINGNAME\",\"ordinal\":1},\"attempt\":{\"name\":\"attempt\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"INTEGER\",\"ordinal\":3},\"deviceName\":{\"name\":\"deviceName\",\"aspects\":{\"isPrimaryKey\":false},\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":4}}},\"rows\":[]},\"maxItems\":50,\"tags\":[]\"}}";
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.UploadFailureDataTable/Services/QueryDataTableEntriesByTimeDescend")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(JSONString).post();
		System.out.println("DataTableName = "+DataTableName);
		System.out.println(responseT.asString());
		return responseT;
	}
	
	
	public static String DataTableByDeviceName(Response responseT) {//descend: Latest data on the top of the infoTable
		String result ="";
			String DeviceName=TWXResultGetter.getDataTableDeviceName(responseT);
			System.out.println("DeviceName = "+DeviceName);
			result = DeviceName;
			return result;
	}
	public static String DataTableByPath(Response responseT) {//descend: Latest data on the top of the infoTable
		String result ="";
		String Path=TWXResultGetter.getDataTablePath(responseT);
		System.out.println("Path = "+Path);
		result = Path;
		return result;
	}
	public static Long DataTableByAttempt(Response responseT) {//descend: Latest data on the top of the infoTable
		Long result  = 0L;
		Long Attempt=TWXResultGetter.getDataTableAttempt(responseT);
		System.out.println("Attempt = "+Attempt);
		result = Attempt;
		return result;
	}
	public static Long DataTableByTimeStamp(Response responseT) {//descend: Latest data on the top of the infoTable
		Long result  = 0L;
		Long timestamp=TWXResultGetter.getDataTableTimeStamp(responseT);
		System.out.println("timestamp = "+timestamp);
		result = timestamp;
		return result;
	}
	
	
	public static Response AddOrUpdateRetryDataTableEntries(String deviceName, String TreatmentFileName) {//descend: Latest data on the top of the infoTable
		RestAssured.useRelaxedHTTPSValidation();
		String path=returnTreatmentFilePath(deviceName)+"/"+TreatmentFileName;
		String JSONString ="{\"values\":{\"dataShape\":{\"fieldDefinitions\":{\"repository\":{\"name\":\"repository\",\"description\":\"\",\"baseType\":\"THINGNAME\",\"ordinal\":1,\"alias\":\"_6\",\"aspects\":{\"isPrimaryKey\":false}},\"path\":{\"name\":\"path\",\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":2,\"alias\":\"_7\",\"aspects\":{\"isPrimaryKey\":true}},\"attempt\":{\"name\":\"attempt\",\"description\":\"\",\"baseType\":\"INTEGER\",\"ordinal\":3,\"alias\":\"_8\",\"aspects\":{\"isPrimaryKey\":false}},\"deviceName\":{\"name\":\"deviceName\",\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":4,\"alias\":\"_9\",\"aspects\":{\"isPrimaryKey\":false}},\"fileName\":{\"name\":\"fileName\",\"description\":\"\",\"baseType\":\"STRING\",\"ordinal\":5,\"alias\":\"_10\",\"aspects\":{\"isPrimaryKey\":false}}}},\"rows\":[{\"repository\":\"Baxter.ClariaFileRepository\",\"path\":\""+path+"\",\"attempt\":\"0\",\"deviceName\":\""+deviceName+"\",\"fileName\":\""+TreatmentFileName+"\",\"_isSelected\":false}],\"name\":\"\",\"description\":\"\"}}";
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.RetryDataTable/Services/AddOrUpdateDataTableEntries")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(JSONString).post();
		System.out.println("AddOrUpdate status code ="+responseT.getStatusCode());
		System.out.println("details = "+responseT.asString());
		return responseT;
	}
	
	String error_treatment_file_path = "441/177/441177/TreatmentResults/T_2019-08-21-15-34-48_C_2_1501001701_00001.tar.gz.enc";
	String error_treatment_file_BLOBString ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
	
	public static Response ManualUploadTreatmentFileToRepo(String deviceName, String TreatmentFileName,String TreatmentFile_BLOB) {//descend: Latest data on the top of the infoTable
		RestAssured.useRelaxedHTTPSValidation();
		String path=returnTreatmentFilePath(deviceName)+"/"+TreatmentFileName;
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("BLOB", TreatmentFile_BLOB);
		jsonBody.put("path", path);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.ClariaFileRepository/Services/UploadService")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println("status code ="+responseT.getStatusCode());
		return responseT;
	}
	
	public static void DeleteRetryDataTableByKey_Data( String TreatmentFilePath) {//descend: Latest data on the top of the infoTable
		RestAssured.useRelaxedHTTPSValidation();
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("key", TreatmentFilePath);
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/Baxter.ClariaFileRepository/Services/DeleteDataTableEntryByKey")
				.headers(TWXConnectorPropeties.getClariaHeaders()).body(jsonBody.toJSONString()).post();
		System.out.println("status code ="+responseT.getStatusCode());
	}
	
	public static void ClearFileManualUploadAddOrUpdateTreatmentFileToRetryDataTable_Combination(String deviceName, String TreatmentFile_BLOB, String TreatmentFileName) {//descend: Latest data on the top of the infoTable
		RestAssured.useRelaxedHTTPSValidation();
		ClearFolderOperation(deviceName);
		String path=returnTreatmentFilePath(deviceName);
		DeleteRetryDataTableByKey_Data(path);
		ManualUploadTreatmentFileToRepo(deviceName,TreatmentFileName, TreatmentFile_BLOB);
		AddOrUpdateRetryDataTableEntries(deviceName,TreatmentFileName);
		System.out.println("Check Baxter.RetryDataTable now!");
	}
	
}
