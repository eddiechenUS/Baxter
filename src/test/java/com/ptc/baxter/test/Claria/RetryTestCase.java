package com.ptc.baxter.test.Claria;

import java.util.Date;

import org.junit.Assert;

import com.baxter.ptc.twx.TWXResultGetter;
import com.baxter.ptc.twx.core.TWXServices;

import io.restassured.response.Response;

public class RetryTestCase {
	
	Integer serialNumber = 441177;
	String deviceName = "Claria.".concat(serialNumber.toString());
	
	String error_treatment_file_path = "441/177/441177/TreatmentResults/T_2019-08-21-15-34-48_C_2_1501001701_00001.tar.gz.enc";
	String error_treatment_file_path_in_Backup = "441/177/441177/TreatmentResults/Backup/T_2019-08-21-15-34-48_C_2_1501001701_00001.tar.gz.enc";
	String error_treatment_file_BLOBString ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
	String error_treatment_file_name = "T_2019-08-21-15-34-48_C_2_1501001701_00001.tar.gz.enc";
	
	
	
	
	//settingFile request
	String settingFileRequest_errorBLOB = "X+Sv66UrYo2eRz3pCPf5od6QmIp09Y0qOPouYQroyfvqDcCSR7OH20pnFKeaJwf/M5qOQrZQ9Z6Ez7gawIbBq2W7vFx8ZFCzF0+ee4HgseBQxunovfSgCicy9T9fwy9kAjmwHgjc9GJ6c5XZ76RXMmcEMyEBh/YF54e0d5fHanbOsSXjoVZLg2VlGAnHcAbrvuZG63XiDNI930pWr4zortPLw1Uw+ujppqUbSs3CO+EHzBgtHWwBWfuZvHzXMvh4Qu7/Qef1Da0UJ9gXtJjYs+6Of84WXOtWNkfQ9JF1C0CNII+DN7hkyWdsIpmLioib0tDuaTWDtJMLOI4yBfgAivQ4dYzyk5i7LX4cuBqnQyVJNuGAy5F6pgSKHdcZudhbrQam53Sh9OSuNiKhgy8mULXGr92zXVs4DEkq61ECF4PDWkTfMtHP20RbP/EWtFa0zprGdXTjbcMDsLyqK7mWlw==";
	String checksum2_error = "05B28882CCEF7C97E3B66520B835734D";
	String FileName2_error = "SQ_C_1_1501001701.tar.gz.enc";
	
	//treatmentFile
	String treatmentFileBLOB ="2TbnPevxsUFCY1K1/VMwYMKNJXpQcC/j/6jF0oqKTbqh8hAlITkwjVNHrkeS19K+f4tsAh9ifLp8R6yzZbB1u9OK3udC+JX1GiPeHFV4RhY+3NfRYktMSJHbWUmyRWRiy795PYBCkC7bk1z59sfBciBsjJ3uvAfdolwU7YCZ0HJA3KZrN1a9epWmzFndKId5wEABucHK93T/So5vkuuVM4QQMJ9KiT+A4zQ8kMdrn9ZjuOi2HX8JpHJpYR+R4p2UFRb+tbHdKK7bB+7MGr05l0+5E3cJeCOoQxA2ScabgLu1Qon0q9Pqg4AG/uINg88wFy1R3vZWcPDTZtudPdWOR6CpIvEWaY+DimXu8t5jlQSX7pCDlO67N3AQtAtuOe2XBfVQbKNWSu9sglnqyTSGl4Fn2otZjyjsAoMPJbNdS2zNKISepmRt3wbPUiSheh21ZQVyI+hVyGOkjtgAgrDH8daMt6SBEoyDTPeF2JqpTLJDTK/Oa0YuHn2G+TPooxY/2BiSrV8Z1DCnoDLDjNrZS0rCQEh8nDgCB9vWNSO4bUgDHlHzjsjPsgRgxehYdiQgRbNBEa7V0QrKHTaUr6BUBZIqgsxaryn/3ED0emrTqDo1kdThTH/V6oJHa6bjyagL8fNDIiPQbsVTnTLJklLSEiaMBwrDmIS6jDB+Icucp4aw7yI2KeaYVvZn8NGQpgyq/qNO7dm4GmadERTcDsg1IbWJAOB05OvdaGQLIQf/8PVuikFjlAvu9Wq8WF/hXEj8iipueONiF5ggsnipN7DUCeeHkKk4+2dqmqlocxaaHcKPM38H794Fi+Aucei5LOaoifN74Sw1iGgj4A2WnxIGf6ssaHVrVxGFRwFy+gLt6PtIZq7Cs6tUizRM7uTc61C+Ihi+JbRWvOM9wTAvJGoWVp8dJpjFeAI5t0aGPRGCGDU0r7kCjZmyllHj1Yhi68fjfgYAnxElxjQNpb+SYEQ0DCTUXQZmynbaZJMWbam9rXL/t/B+n26edhv2zvhIXhIzMMIMTuBlrupcp7PogcjNMnDbAx+wZWuWLUdvn6d+mY/euDHX4YKlErhJesHAj0EOsVzOAHbY/XKiU+SWH6Im1tGLLFsXX42sNi0XTW8YLJ3WUIMdqRQymZTtGnBafufqdp3gDtsslYYZshnWJ48PtwPtg8UfvjemVGOVPAFZRJO2BwdhXubJOEwbCORuslN3xdheEPug449AYjrbU1EtZstkPGih9A2UwBsodjMO7/RUVsUAsboFxJ6uhzc6j3wNhq7YT6y+asvtMFqAKzw8vY9tck5dI1rKVdQ8JlKXEWORGnvx6RC4+UfyPTlziT1NFxBMJMGq6upRxZhPYZS6cH5G3CNmO3GsS3nrMg0rb3imjUXpq+de/4uyhksyjHacP1nIzvbQ490+tZ2H3jZ/RyhDLa6n3l9/4SsBBZD6T44sy3P6ebsSEgPMXyR8+ZwRG8STRGRm8UzVwWl6MVGSfgy4BuA7l1QSOxBu4MHZtQdOqpMBZGcSqCMvkUA8FneVAWPbDlI17FOsUPWV8pIhYX/bJ+h8RiuB+VLobQpbJ2sB1QW6GswrfxFyC+wr8Dny1YHNx+awK/jF31Kd3ud/nQ==";
	String checksum1 = "16A75D8D8C9A3D18A4358E892705F046";
	String FileName1 = "T_C_2_1501001701_00001.tar.gz.enc";
	
	//settingFile request
	String settingFileRequestBLOB = "o4DypNNbDM/RPtZggS8hiyZ8qs0KdUH/ydGNFhvsiH4xJLQtKdLjskkHJ2j0pi/D3lR4+Hwql1+JLU1mNtIbSZIlyeDR8TmazRVScKd0i0/ur7E6biZUaPmDlw6bERCxRTibKzUi6Oe9NAzQWtB19VczlCykFTzvsUgSaZ7QeuMP987GbIZlvoXGKf9fMrVrFTQp+2PsFEsHvdLFaIAvuBpHrZq0BrAZmPwwWDpbEpwDDyhCKtfTASSYNFqFJgyrpiedA5LJH65Gz35kOnm1Kx41Fy+R/K6QZBF6k2N5dsiMdH0HxAW/DVtYcWTaFFAqkvPA575HXeUuwz6ro5a1hVz3wRJTPdQMg7D6HnaEd4vXZiKljHvkLTozBRjMZlqH+jni4cQBbK/V4cZvrUVuyjRjFn0TiEVEikVbdapLMYiHVVDUHi+079fMH1SXMHjopxvFFRi7LBi2RnBVgrFjAvjTHP6Wd8UBXQWXVKxcO+qr+OD+CiD98baN0DQXWMDUHwOUcVlDLysUFHUhttKktaUOP7atBc11pYzk1m+dnGGsi6I3OmrO3pVwSzZDm1yz6m7I0FW2XSWuQhNXT7NB/g==";
	String checksum2 = "766D234A2D811CC23EFCF584F40F037D";
	String FileName2 = "SQ_C_1_9999601000.tar.gz.enc";
	
	//settingFile Response
	String settingFileResponseBLOB = "o4DypNNbDM/RPtZggS8hiyZ8qs0KdUH/ydGNFhvsiH4xJLQtKdLjskkHJ2j0pi/D3lR4+Hwql1+JLU1mNtIbSZIlyeDR8TmazRVScKd0i0/ur7E6biZUaPmDlw6bERCxRTibKzUi6Oe9NAzQWtB19VczlCykFTzvsUgSaZ7QeuMP987GbIZlvoXGKf9fMrVrFTQp+2PsFEsHvdLFaIAvuBpHrZq0BrAZmPwwWDpbEpwDDyhCKtfTASSYNFqFJgyrpiedA5LJH65Gz35kOnm1Kx41Fy+R/K6QZBF6k2N5dsiMdH0HxAW/DVtYcWTaFFAqkvPA575HXeUuwz6ro5a1hVz3wRJTPdQMg7D6HnaEd4vXZiKljHvkLTozBRjMZlqH+jni4cQBbK/V4cZvrUVuyjRjFn0TiEVEikVbdapLMYiHVVDUHi+079fMH1SXMHjopxvFFRi7LBi2RnBVgrFjAvjTHP6Wd8UBXQWXVKxcO+qr+OD+CiD98baN0DQXWMDUHwOUcVlDLysUFHUhttKktaUOP7atBc11pYzk1m+dnGGsi6I3OmrO3pVwSzZDm1yz6m7I0FW2XSWuQhNXT7NB/g==";
	String FileName3 = "SR_C_1_9999601000.tar.gz.enc";
	
	
	//Column Name = timestamp, attempt, deviceName, repository, path
	
	//Task 222
	@org.junit.Test
	public void Fail_Retry_File_below_limit() {
		TWXServices.ClearFileManualUploadAddOrUpdateTreatmentFileToRetryDataTable_Combination(deviceName,treatmentFileBLOB, FileName1);
		//Check fieldName
		String fieldName1 = "path";
		String fieldName2 = "atttempt";
		String fieldName3 = "path";
		String dataTableName = "Baxter.RetryDataTable";
		Response res = TWXServices.getDataTableColumnDataByTimeDescend(dataTableName);
//		TWXServices.getDataTableColumnDataByTimeDescend_seperater(res,fieldName1);
		Assert.assertTrue(TWXServices.DataTableByAttempt(res)<=3);
//		TWXServices.getDataTableColumnDataByTimeDescend_seperater(res,fieldName3);
	}
	
	
	//Task 224
	@org.junit.Test
	public void Success_Retry_File_With_Backup() {
		// BEFORE EXECUTE: Make sure there is no content in Treatment file in the
		// Baxter.ClariaFileRepository
		// If already have treatment file in Backup or main, delete it at the beginning
		TWXServices.ClearFolderOperation(deviceName);
		// Start task:	
		//Set Backup to true
		String singlePropertyName5 = "isFileBackupEnabled";
		//set property value
		Boolean FirstsinglePropertyValue5 = true;
		String testString5 = "{\"" + singlePropertyName5 + "\":\"" + FirstsinglePropertyValue5 + "\"}";
		//step 1 : Set isFileBackupEnabled, ClariaTreatmentFileBackup Backup to true
		TWXServices.setClariaProperty(testString5, "Baxter.FileBackupConfigurator");
		Assert.assertEquals(FirstsinglePropertyValue5,TWXServices.getBaxterFileBackupConfigurator_IsFileBackupEnabled(singlePropertyName5));
		
		String singlePropertyName7 = "ClariaTreatmentFileBackup";
		Boolean FirstsinglePropertyValue7 = true;
		String testString7 = "{\"" + singlePropertyName7 + "\":\"" + FirstsinglePropertyValue7 + "\"}";
		TWXServices.setClariaProperty(testString7, "Baxter.FileBackupConfigurator");
		Assert.assertEquals(FirstsinglePropertyValue7,TWXServices.getBaxterFileBackupConfigurator_ClariaTreatmentFileBackup(singlePropertyName7));
		
		//Start task
		TWXServices.ClearFileManualUploadAddOrUpdateTreatmentFileToRetryDataTable_Combination(deviceName,treatmentFileBLOB, FileName1);
		
		//Check fieldName
		String fieldName1 = "path";
		String fieldName2 = "atttempt";
		String fieldName3 = "path";
		String dataTableName = "Baxter.RetryDataTable";
		Response res = TWXServices.getDataTableColumnDataByTimeDescend(dataTableName);
//		TWXServices.getDataTableColumnDataByTimeDescend_seperater(res,fieldName1);
		Assert.assertTrue(TWXServices.DataTableByAttempt(res)<=3);
//		TWXServices.getDataTableColumnDataByTimeDescend_seperater(res,fieldName3);

		//Backup verify
		//not in the main storage
		Assert.assertEquals(0,TWXServices.getTreatmentFileFileCombination_TreatmentFile(deviceName));// if no data, the status code will be 0, it is verified.
		//not in the backup file
		Assert.assertEquals(200,TWXServices.getTreatmentFileCombination_backup_TreatmentFile(deviceName));//  valid path: Thing: Baxter.ClariaFileRepository/GetFileListingWithLinks, input =  /441/188/441188/SettingsResponse/Backup/

	}
}
