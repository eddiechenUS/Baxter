package com.ptc.baxter.test.Claria;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.baxter.ptc.twx.TWXResultGetter;
import com.baxter.ptc.twx.TWXServicesInvoker;
import com.baxter.ptc.twx.core.TWXServices;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class ClariaTestCase {

//	static String DEVICENAME = "Claria.11223377";

	/**
	 * @org.junit.Test public void test1() { int a = 2; int b = 2; int c = a + b;
	 *                 Assert.assertEquals(4, c); }
	 */

	// settingFile request
	String settingFileRequest_error = "X+Sv66UrYo2eRz3pCPf5od6QmIp09Y0qOPouYQroyfvqDcCSR7OH20pnFKeaJwf/M5qOQrZQ9Z6Ez7gawIbBq2W7vFx8ZFCzF0+ee4HgseBQxunovfSgCicy9T9fwy9kAjmwHgjc9GJ6c5XZ76RXMmcEMyEBh/YF54e0d5fHanbOsSXjoVZLg2VlGAnHcAbrvuZG63XiDNI930pWr4zortPLw1Uw+ujppqUbSs3CO+EHzBgtHWwBWfuZvHzXMvh4Qu7/Qef1Da0UJ9gXtJjYs+6Of84WXOtWNkfQ9JF1C0CNII+DN7hkyWdsIpmLioib0tDuaTWDtJMLOI4yBfgAivQ4dYzyk5i7LX4cuBqnQyVJNuGAy5F6pgSKHdcZudhbrQam53Sh9OSuNiKhgy8mULXGr92zXVs4DEkq61ECF4PDWkTfMtHP20RbP/EWtFa0zprGdXTjbcMDsLyqK7mWlw==";
	String checksum2_error = "05B28882CCEF7C97E3B66520B835734D";
	String FileName2_error = "SQ_C_1_1501001701.tar.gz.enc";

	// treatmentFile
	String treatmentFile = "2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
	String checksum1 = "16A75D8D8C9A3D18A4358E892705F046";
	String FileName1 = "T_C_2_1501001701_00001.tar.gz.enc";

	// settingFile request
	String settingFileRequest = "o4DypNNbDM/RPtZggS8hiyZ8qs0KdUH/ydGNFhvsiH4xJLQtKdLjskkHJ2j0pi/D3lR4+Hwql1+JLU1mNtIbSZIlyeDR8TmazRVScKd0i0/ur7E6biZUaPmDlw6bERCxRTibKzUi6Oe9NAzQWtB19VczlCykFTzvsUgSaZ7QeuMP987GbIZlvoXGKf9fMrVrFTQp+2PsFEsHvdLFaIAvuBpHrZq0BrAZmPwwWDpbEpwDDyhCKtfTASSYNFqFJgyrpiedA5LJH65Gz35kOnm1Kx41Fy+R/K6QZBF6k2N5dsiMdH0HxAW/DVtYcWTaFFAqkvPA575HXeUuwz6ro5a1hVz3wRJTPdQMg7D6HnaEd4vXZiKljHvkLTozBRjMZlqH+jni4cQBbK/V4cZvrUVuyjRjFn0TiEVEikVbdapLMYiHVVDUHi+079fMH1SXMHjopxvFFRi7LBi2RnBVgrFjAvjTHP6Wd8UBXQWXVKxcO+qr+OD+CiD98baN0DQXWMDUHwOUcVlDLysUFHUhttKktaUOP7atBc11pYzk1m+dnGGsi6I3OmrO3pVwSzZDm1yz6m7I0FW2XSWuQhNXT7NB/g==";
	String checksum2 = "766D234A2D811CC23EFCF584F40F037D";
	String FileName2 = "SQ_C_1_9999601000.tar.gz.enc";

	// settingFile Response
	String settingFileResponse = "o4DypNNbDM/RPtZggS8hiyZ8qs0KdUH/ydGNFhvsiH4xJLQtKdLjskkHJ2j0pi/D3lR4+Hwql1+JLU1mNtIbSZIlyeDR8TmazRVScKd0i0/ur7E6biZUaPmDlw6bERCxRTibKzUi6Oe9NAzQWtB19VczlCykFTzvsUgSaZ7QeuMP987GbIZlvoXGKf9fMrVrFTQp+2PsFEsHvdLFaIAvuBpHrZq0BrAZmPwwWDpbEpwDDyhCKtfTASSYNFqFJgyrpiedA5LJH65Gz35kOnm1Kx41Fy+R/K6QZBF6k2N5dsiMdH0HxAW/DVtYcWTaFFAqkvPA575HXeUuwz6ro5a1hVz3wRJTPdQMg7D6HnaEd4vXZiKljHvkLTozBRjMZlqH+jni4cQBbK/V4cZvrUVuyjRjFn0TiEVEikVbdapLMYiHVVDUHi+079fMH1SXMHjopxvFFRi7LBi2RnBVgrFjAvjTHP6Wd8UBXQWXVKxcO+qr+OD+CiD98baN0DQXWMDUHwOUcVlDLysUFHUhttKktaUOP7atBc11pYzk1m+dnGGsi6I3OmrO3pVwSzZDm1yz6m7I0FW2XSWuQhNXT7NB/g==";
	String FileName3 = "SR_C_1_9999601000.tar.gz.enc";

	// task 165 :testRegisterClaria
	@org.junit.Test
	public void testRegisterDevice() {// completed
		RestAssured.useRelaxedHTTPSValidation();
		Long initialTime = (Long) new Date().getTime();
		System.out.println("initialTime" + initialTime);
		Integer serialNumber;
//		int serialnumber = (int) new Date().getTime();
		serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		Response response;
		// Create new thing
		TWXServices.registerClariaDevice(serialNumber);
		// check in twx is exist
		response = TWXServicesInvoker.getThing("Claria." + serialNumber);
		System.out.println(response.getStatusCode());
		// add assert
		Assert.assertEquals(200, response.getStatusCode());
		// Create new thing
		response = TWXServices.registerClariaDevice(serialNumber);
		// check in twx is exist
		Assert.assertEquals(200, response.getStatusCode());
		Long currentTime = TWXServices.getTimeStamp(deviceName);
//		System.out.println("current time = "+currentTime);
//		System.out.println("initial time = "+initialTime);
		// check auditLog if added
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Cannot register device, device already exists", TWXServices.getAction(deviceName));
	}

	// task 168
	@org.junit.Test
	public void testUpdateProperty_RegisterDevice_getProperty() {
		Long initialTime = (Long) new Date().getTime();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		String singlePropertyName = "DeviceStatus";
		// set property value
		String FirstsinglePropertyValue = "POWERED_UP";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		TWXServices.setClariaProperty(testString, deviceName);
		// register same device again
		TWXServices.registerClariaDevice(serialNumber);
		Long currentTime = TWXServices.getTimeStamp(deviceName);
//		System.out.println("current time = "+currentTime);
//		System.out.println("initial time = "+initialTime);
		// check auditLog if added
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Cannot register device, device already exists", TWXServices.getAction(deviceName));
		Response res2 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		// check property value
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res2, singlePropertyName));
	}

	// task 176
	@org.junit.Test
	public void Update_Correct_Treatment_File_with_Backup() {
		Long initialTime = (Long) new Date().getTime();
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		String singlePropertyName = "isFileBackupEnabled";
		// set property value
		Boolean FirstsinglePropertyValue = true;
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// step 1 : Set isFileBackupEnabled, ClariaTreatmentFileBackup Backup to true
		TWXServices.setClariaProperty(testString, "Baxter.FileBackupConfigurator");
		Assert.assertEquals(FirstsinglePropertyValue,
				TWXServices.getBaxterFileBackupConfigurator_IsFileBackupEnabled(singlePropertyName));

		String singlePropertyName2 = "ClariaTreatmentFileBackup";
		Boolean FirstsinglePropertyValue2 = true;
		String testString2 = "{\"" + singlePropertyName2 + "\":\"" + FirstsinglePropertyValue2 + "\"}";
		TWXServices.setClariaProperty(testString2, "Baxter.FileBackupConfigurator");

		// step 2 : Update Correct Treatment File
		Response res = TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res, "result"));
		// step 3 : File is moving to backup repository
		Long currentTime = TWXServices.getTimeStamp(deviceName);
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Backup", TWXServices.getCategory(deviceName));
		Assert.assertEquals("FileMoved", TWXServices.getName(deviceName));
		Assert.assertEquals(200, TWXServices.getTreatmentFileCombination_backup_TreatmentFile(deviceName));// valid
																											// path:
																											// Thing:
																											// Baxter.ClariaFileRepository/GetFileListingWithLinks,
																											// input =
																											// /441/188/441188/SettingsResponse/Backup/
	}

	// task 200 : Request and Download Settings File without backup //finished
	@org.junit.Test
	public void Request_and_Download_Settings_File_without_backup() {
		Long initialTime = (Long) new Date().getTime();
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		// Set backup to true : 1. set isFileBackupEnabled to false
		String deviceName2 = "Baxter.FileBackupConfigurator";
		String singlePropertyName2 = "isFileBackupEnabled";
		Boolean FirstsinglePropertyValue2 = false;
		String testString2 = "{\"" + singlePropertyName2 + "\":\"" + FirstsinglePropertyValue2 + "\"}";
		TWXServices.setClariaProperty(testString2, deviceName2);
		// Set backup to true : 1. set ClariaSettingsFileBackup to false
		String singlePropertyName3 = "ClariaSettingsFileBackup";
		Boolean FirstsinglePropertyValue3 = false;
		String testString3 = "{\"" + singlePropertyName3 + "\":\"" + FirstsinglePropertyValue3 + "\"}";
		TWXServices.setClariaProperty(testString3, deviceName2);

		// verify isFileBackupEnabled is false
		Response resE = TWXServices.getClariaProperty(singlePropertyName2, deviceName2);
		Assert.assertEquals(FirstsinglePropertyValue2, TWXResultGetter.ShowBoolean(resE, singlePropertyName2));
		// verify ClariaSettingsFileBackup is false
		Response resZ = TWXServices.getClariaProperty(singlePropertyName3, deviceName2);
		Assert.assertEquals(FirstsinglePropertyValue3, TWXResultGetter.ShowBoolean(resZ, singlePropertyName3));

		// step 1 : Set Claria device SettingsRequestStatus to IDLE
		String singlePropertyName = "SettingsRequestStatus";
		String FirstsinglePropertyValue = "IDLE";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";

		TWXServices.setClariaProperty(testString, deviceName);
		Response resL = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(resL, singlePropertyName));
		// step 2 : Send request file
		Response res2 = TWXServices.sendSettingsRequestFile(FileName2, checksum2, settingFileRequest, deviceName);
		// check if upload successfully
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res2, "result"));
		// step 3 : Check auditlog, whether setting file move to backup, CAUTION :
		// sometime other people will do task so that auditlog will be disrupt
		Long currentTime = TWXServices.getTimeStamp(deviceName);
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Backup", TWXServices.getCategory(deviceName));
		Assert.assertEquals("FileDeleted", TWXServices.getName(deviceName));
		// step 4 : check if file not exist in main repo and no backup file created
		// object : setting "request" file
		// not in the main storage
		Assert.assertEquals(0, TWXServices.getSettingRequestFileCombination_SettingRequest(deviceName));// if no data,
		// Baxter.ClariaFileRepository/GetFileListingWithLinks/ input
		// 441/177/441177/SettingsRequest/Backup
		// Baxter.ClariaFileRepository/GetFileListingWithLinks/ input
		// 441/177/441177/SettingsRequest
		// step 5 : check setting request status value is REQUEST_UPLOADED
		Response res3 = TWXServices.getClariaProperty("SettingsRequestStatus", deviceName);
		while (!TWXResultGetter.ShowProperty(res3, singlePropertyName).equals("SETTINGS_AVAILABLE")) {
			res2 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		}
		// step 6: check GetResponseFileDetails if it match the name in TWX
		// name = SettingsResponse - SR_C_1_9999601000.tar.gz.enc, SettingsRequest -
		// SQ_C_1_9999601000.tar.gz.enc
		Assert.assertEquals(true, TWXServices.getSettingsResponseFileDetails(deviceName, FileName2));
		// step 7 : send download request?
		Assert.assertEquals(0, TWXServices.getSettingRequestFileCombination_SettingRequest(deviceName));
		// step 8 : Change Property "SettingsRequestStatus" To "SETTINGS_DOWNLOADED"
		String FirstsinglePropertyValue5 = "SETTINGS_DOWNLOADED";
		String testString5 = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue5 + "\"}";
		TWXServices.setClariaProperty(testString5, deviceName);
		Response res5 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		Assert.assertEquals(FirstsinglePropertyValue5, TWXResultGetter.ShowProperty(res5, singlePropertyName));
		// step 9 : check File was not in repository
		//
		Assert.assertEquals(0, TWXServices.getSettingRequestFileCombination_SettingRequest(deviceName));
		Assert.assertEquals(0, TWXServices.getSettingResponseFileCombination_SettingResponse(deviceName));
	}

	// task 199
	@org.junit.Test
	public void Update_Old_Treatment_File() {
		Long initialTime = (Long) new Date().getTime();
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		// step 1 : upload old setting request file, file have correct but old file, so
		// the erorr will be shown in audit log
		Response res2 = TWXServices.sendSettingsRequestFile(FileName2_error, checksum2_error, settingFileRequest_error,
				deviceName);
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res2, "result"));
		// step 2 : still not delete in the main repo, 200 means exist the
		// getFileWithLink
		Assert.assertEquals(200, TWXServices.getSettingRequestFileCombination_SettingRequest(deviceName));// if no data,
		// step 3 : Check auditlog for old file reault
		Long currentTime = TWXServices.getTimeStamp(deviceName);
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Error", TWXServices.getCategory(deviceName));
		Assert.assertEquals("ServiceError", TWXServices.getName(deviceName));
	}

	// task 198 : Update Correct Treatment File without Backup
	@org.junit.Test
	public void Update_Correct_Treatment_File_without_Backup() {
		Long initialTime = (Long) new Date().getTime();
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		String singlePropertyName = "isFileBackupEnabled";
		// set property value
		Boolean FirstsinglePropertyValue = false;
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// step 1 : Set Backup to true
		TWXServices.setClariaProperty(testString, "Baxter.FileBackupConfigurator");
		Assert.assertEquals(FirstsinglePropertyValue,
				TWXServices.getBaxterFileBackupConfigurator_IsFileBackupEnabled(singlePropertyName));

		String singlePropertyName2 = "ClariaTreatmentFileBackup";
		Boolean FirstsinglePropertyValue2 = false;
		String testString2 = "{\"" + singlePropertyName2 + "\":\"" + FirstsinglePropertyValue2 + "\"}";
		TWXServices.setClariaProperty(testString2, "Baxter.FileBackupConfigurator");
		// step 2 : Update Correct Treatment File
		Response res = TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res, "result"));
		// step 3 : File is moving to backup repository
		// not in the main storage
		Assert.assertEquals(0, TWXServices.getTreatmentFileFileCombination_TreatmentFile(deviceName));// if no data, the
		// not in the backup file
		Assert.assertEquals(0, TWXServices.getTreatmentFileCombination_backup_TreatmentFile(deviceName));// valid path:
		// step 4 : Entry is adding to auditlog
		Long currentTime = TWXServices.getTimeStamp(deviceName);
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Backup", TWXServices.getCategory(deviceName));
		Assert.assertEquals("FileDeleted", TWXServices.getName(deviceName));
	}

	// task 177 finished
	@org.junit.Test
	public void Request_and_Download_Settings_File_with_backup() {
		Long initialTime = (Long) new Date().getTime();
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		String singlePropertyName5 = "isFileBackupEnabled";
		// set property value
		Boolean FirstsinglePropertyValue5 = true;
		String testString5 = "{\"" + singlePropertyName5 + "\":\"" + FirstsinglePropertyValue5 + "\"}";
		// step 1 : Set isFileBackupEnabled, ClariaTreatmentFileBackup Backup to true
		TWXServices.setClariaProperty(testString5, "Baxter.FileBackupConfigurator");
		Assert.assertEquals(FirstsinglePropertyValue5,
				TWXServices.getBaxterFileBackupConfigurator_IsFileBackupEnabled(singlePropertyName5));

		String singlePropertyName7 = "ClariaTreatmentFileBackup";
		Boolean FirstsinglePropertyValue7 = true;
		String testString7 = "{\"" + singlePropertyName7 + "\":\"" + FirstsinglePropertyValue7 + "\"}";
		TWXServices.setClariaProperty(testString7, "Baxter.FileBackupConfigurator");
		Assert.assertEquals(FirstsinglePropertyValue7,
				TWXServices.getBaxterFileBackupConfigurator_ClariaTreatmentFileBackup(singlePropertyName7));
		// set backup finished
		// set Claria device property
		String singlePropertyName = "SettingsRequestStatus";
		// set property value
		String FirstsinglePropertyValue = "IDLE";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// step 1 : Set Claria device SettingsRequestStatus to IDLE
		TWXServices.setClariaProperty(testString, deviceName);
		Response res = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res, singlePropertyName));
		// step 2 : Send request file
		Response res2 = TWXServices.sendSettingsRequestFile(FileName2, checksum2, settingFileRequest, deviceName);
		// check if upload successfully
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res2, "result"));
		// step 3 : Check auditlog, whether setting file move to backup, CAUTION :
		// sometime other people will do task so that auditlog will be disrupt
		Long currentTime = TWXServices.getTimeStamp(deviceName);
		Assert.assertTrue(currentTime > initialTime);
		Assert.assertEquals(deviceName, TWXServices.getAsset(deviceName));
		Assert.assertEquals("Backup", TWXServices.getCategory(deviceName));
		Assert.assertEquals("FileMoved", TWXServices.getName(deviceName));
		// step 4 : check if file not exist in main repo and appears in backup file
		// object : setting "request" file
		// not in the main storage
		Assert.assertEquals(0, TWXServices.getSettingRequestFileCombination_SettingRequest(deviceName));// if no data,
		// not in the backup file
		Assert.assertEquals(200, TWXServices.getSettingRequestFileCombination_backup_SettingRequest(deviceName));// valid
		// Baxter.ClariaFileRepository/GetFileListingWithLinks/ input
		// 441/177/441177/SettingsRequest/Backup
		// Baxter.ClariaFileRepository/GetFileListingWithLinks/ input
		// 441/177/441177/SettingsRequest
		// step 5 : check setting request status value is REQUEST_UPLOADED
		Response res3 = TWXServices.getClariaProperty("SettingsRequestStatus", deviceName);
		while (!TWXResultGetter.ShowProperty(res3, singlePropertyName).equals("SETTINGS_AVAILABLE")) {
			res2 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		}
		// step 6: check GetResponseFileDetails if it match the name in TWX
		// name = SettingsResponse - SR_C_1_9999601000.tar.gz.enc, SettingsRequest -
		// SQ_C_1_9999601000.tar.gz.enc
		Assert.assertEquals(true, TWXServices.getSettingsResponseFileDetails(deviceName, FileName2));
		// step 7 : send download request?
		Assert.assertEquals(200, TWXServices.getSettingResponseFileCombination_SettingResponse(deviceName));
		// step 8 : Change Property "SettingsRequestStatus" To "SETTINGS_DOWNLOADED"
		String FirstsinglePropertyValue2 = "SETTINGS_DOWNLOADED";
		String testString2 = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue2 + "\"}";
		TWXServices.setClariaProperty(testString2, deviceName);
		Response res4 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		Assert.assertEquals(FirstsinglePropertyValue2, TWXResultGetter.ShowProperty(res4, singlePropertyName));
		// step 9 : check File was moving to backup repository
		//
		Assert.assertEquals(0, TWXServices.getSettingResponseFileCombination_SettingResponse(deviceName));
		Assert.assertEquals(200, TWXServices.getSettingResponseFileCombination_backup_SettingResponse(deviceName));
	}

	@org.junit.Test
	public void testUpdateAndGetProperties() {// completed
		RestAssured.useRelaxedHTTPSValidation();
		String singlePropertyName = "DeviceStatus";
		String FirstsinglePropertyValue = "SHUTTING_DOWN";
		String SecondsinglePropertyValue = "POWERED_UP";
		String device = "Claria.441177";
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
		RestAssured.useRelaxedHTTPSValidation();
		String singlePropertyName = "DeviceStatus";
		String FirstsinglePropertyValue = "POWERED_UPPPPP";
		Integer serialNumber = 441177;
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

	@org.junit.Test
	public void testRequestAndDownloadSettingsFile() {
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		String singlePropertyName = "SettingsRequestStatus";
		String FirstsinglePropertyValue = "IDLE";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// set property SettingsRequestStatus to "IDLE"
		TWXServices.setClariaProperty(testString, deviceName);
		Response res = TWXServices.getClariaProperty("SettingsRequestStatus", deviceName);
		Assert.assertEquals("IDLE", TWXResultGetter.ShowProperty(res, singlePropertyName));
		// send setting file request
		TWXServices.sendSettingsRequestFile(FileName2, checksum2, settingFileRequest, deviceName);
		// check property SettingsRequestStatus whether change to "REQUEST_UPLOADED" ,
		// but it would be too fast to change from "REQUEST_UPLOADED" to
		// "SETTINGS_AVAILABLE", therefore I check for "SETTINGS_AVAILABLE".
		Response res2 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		while (!TWXResultGetter.ShowProperty(res2, singlePropertyName).equals("SETTINGS_AVAILABLE")) {
			res2 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		}
		// check whether download file process is fine, status code = 200
		Assert.assertEquals(200, TWXServices.getSettingResponseFileCombination_SettingResponse(deviceName));
		String testString2 = "{\"" + singlePropertyName + "\":\"" + "SETTINGS_DOWNLOADED" + "\"}";
		TWXServices.setClariaProperty(testString2, deviceName);
		Response res3 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		// check if property SettingsRequestStatus change to value "SETTINGS_DOWNLOADED"
		Assert.assertEquals("SETTINGS_DOWNLOADED", TWXResultGetter.ShowProperty(res3, singlePropertyName));
	}

	@org.junit.Test
	public void testUploadTreamentFile() {
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:
		Response res = TWXServices.uploadTreatmentFile(FileName1, checksum1, treatmentFile, deviceName);
		Assert.assertEquals(200, res.getStatusCode());
	}

	@org.junit.Test
	public void testIsReporting() {
		RestAssured.useRelaxedHTTPSValidation();
		Integer serialNumber = 441177;
		String singlePropertyName = "LastRestCall";
		Date d = new Date();
		long FirstsinglePropertyValue = d.getTime();
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		String deviceName = "Claria.".concat(serialNumber.toString());
		TWXServices.setClariaProperty(testString, deviceName);
		Response res = TWXServices.getClariaProperty("isReporting", deviceName);
		// check isReporting property if it's true
		Assert.assertEquals(true, TWXResultGetter.ShowBoolean(res, "isReporting"));
		// wait for 5 mins
		TWXServices.Pause_N_Minute(5);
		// check isReporting property if it's false
		Response res2 = TWXServices.getClariaProperty("isReporting", deviceName);
		Assert.assertEquals(false, TWXResultGetter.ShowBoolean(res2, "isReporting"));
	}
	
/*	
	
	// task 178   , not finished yet
	@org.junit.Test
	public void Update_Device() {
		RestAssured.useRelaxedHTTPSValidation();
		Long initialTime = (Long) new Date().getTime();

		// step 1 : Create Deploy

		Integer serialNumber = 441177;
		String deviceName = "Claria.".concat(serialNumber.toString());
		String singlePropertyName = "FirmwareUpdateStatus";
		String FirstsinglePropertyValue = "UPDATE_AVAILABLE";
		String testString = "{\"" + singlePropertyName + "\":\"" + FirstsinglePropertyValue + "\"}";
		// step 2 : Change Property "FirmwareUpdateStatus" To "UPDATE_AVAILABLE" on TWX
		// side
		TWXServices.setClariaProperty(testString, deviceName);
		// step 3 : Verified changed property
		Response res = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		Assert.assertEquals(FirstsinglePropertyValue, TWXResultGetter.ShowProperty(res, singlePropertyName));
		// step 4 : Execute GetFirmwarePackageSize
		Response res2 = TWXServices.getFirmwarePackageSize(deviceName);
		// verified the retrieve infoTable
		String FirmwareName = "FirmwareNameXXXX";
		Assert.assertEquals(FirmwareName, TWXResultGetter.InfoTableStringGetter(res2, "name"));
		Assert.assertTrue(initialTime < TWXResultGetter.InfoTableLongGetter(res2, "lastModifiedDate"));
		// maybe varifed fileType

		// step 5 :
		// I assumed the property value "DOWNLOADING" will be too soon to recognize,
		// therefore skip verified this value, because if it not change to value
		// "DOWNLOADED", it won't go to the above value, "SUCCESS"
//		String SecondsinglePropertyValue = "DOWNLOADED";
//		String testString2 = "{\"" + singlePropertyName + "\":\"" + SecondsinglePropertyValue + "\"}";
//		Response res3 = TWXServices.setClariaProperty(testString2, deviceName);
//		Assert.assertEquals(SecondsinglePropertyValue, TWXResultGetter.ShowProperty(res3, singlePropertyName));

		// step 6 : how to verified send download request?
		// step 7 : Change Property "FirmwareUpdateStatus" To "DOWNLOADED"
		Response res4 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		while (!TWXResultGetter.ShowProperty(res4, singlePropertyName).equals("DOWNLOADED")
				|| !TWXResultGetter.ShowProperty(res4, singlePropertyName).equals("DOWNLOADING")) {
			String FirmwareUpdateStatus = TWXResultGetter.ShowProperty(res4, singlePropertyName);
			System.out.println("FirmwareUpdateStatus now is = " + FirmwareUpdateStatus);
			res4 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		}
		// step 8 : Change Property "FirmwareUpdateStatus" To "SUCCESS"
		String singlePropertyValue3 = "SUCCESS";
		String testString2 = "{\"" + singlePropertyName + "\":\"" + singlePropertyValue3 + "\"}";
		TWXServices.setClariaProperty(testString2, deviceName);
		// Verified changed property
		Response res5 = TWXServices.getClariaProperty(singlePropertyName, deviceName);
		Assert.assertEquals(singlePropertyValue3, TWXResultGetter.ShowProperty(res5, singlePropertyName));
		// step 9 : Increase Control-Software-Version and Safety-Software-Version
		// how? the properties are String
	}


	*/

}
