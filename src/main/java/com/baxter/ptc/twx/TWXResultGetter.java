package com.baxter.ptc.twx;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.baxter.ptc.app.App;
import com.baxter.ptc.twx.core.TWXConnectorPropeties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TWXResultGetter {

	static Response response;

	public static String StringResult(Response rs) {
		String result = "";
		ArrayList al = (ArrayList) j1.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (String) i.get("result");
		}
		return result;
	}

	public static String ShowProperty(Response rs, String propertyName) {
		JSONObject jOJ = null;
		String result = "";
		JSONParser parser = new JSONParser();
		try {
			jOJ = (JSONObject) parser.parse(rs.getBody().asString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList al = (ArrayList) jOJ.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (String) i.get(propertyName);
		}
		return result;

	}

	public static String ShowResultValue(Response rs) {
		JSONObject jOJ = null;
		String result = "";
		JSONParser parser = new JSONParser();
		try {
			jOJ = (JSONObject) parser.parse(rs.getBody().asString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList al = (ArrayList) jOJ.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (String) i.get("result");
		}
		return result;
	}

	public static String ShowResultValueByColName(Response rs, String ColName) {
		JSONObject jOJ = null;
		String result = "";
		JSONParser parser = new JSONParser();
		try {
			jOJ = (JSONObject) parser.parse(rs.getBody().asString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList al = (ArrayList) jOJ.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (String) i.get(ColName);
		}
		return result;
	}

	public static Boolean ShowBoolean(Response rs, String propertyName) {
		JSONObject jOJ = null;
		Boolean result = false;
		JSONParser parser = new JSONParser();
		try {
			jOJ = (JSONObject) parser.parse(rs.getBody().asString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList al = (ArrayList) jOJ.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (Boolean) i.get(propertyName);
		}
		System.out.println("ShowBoolean = "+result);
		return result;

	}

	public static Boolean BooleanResult(Response rs) {
		Boolean result = false;
		ArrayList al = (ArrayList) j1.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (Boolean) i.get("result");
		}
		return result;
	}

	public static Double NumberResult(Response rs) {
		Double result = 0.0;
		ArrayList al = (ArrayList) j1.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (Double) i.get("result");
		}
		return result;
	}

	public static Integer IntegerResult(Response rs) {
		Integer result = 0;
		ArrayList al = (ArrayList) j1.get("rows");
		for (Object item : al) {
			JSONObject i = (JSONObject) item;
			result = (int) (long) i.get("result");// if exceed range of integer, then there will be exception
		}
		return result;
	}

	public static JSONObject JSONResult(Response rs) {
		JSONObject result = null;
		JSONParser parser = new JSONParser();
		try {
			result = (JSONObject) parser.parse(rs.getBody().asString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static JSONObject InfotableResult(Response rs) {
		JSONObject result = null;
		result = (JSONObject) o1.get("fieldDefinitions");
		return result;
	}

	public static void StoreResponse(Response rs) {
		response = rs;
	}

	public static Response showResult() {
		JSONObject jsonBody = new JSONObject();
		Response responseT = RestAssured.given().baseUri(TWXConnectorPropeties.getBaseUrl())
				.basePath("Things/test111/Services/tryJSONoutput").headers(TWXConnectorPropeties.getHeaders())
				.body(jsonBody.toJSONString()).post();
		TWXResultGetter.StoreResponse(responseT);
		return responseT;
	}

	public static void distributor(String baseType) {
		if (baseType.equals("STRING")) {
			System.out.println(TWXResultGetter.StringResult(response));
		}
		;
		if (baseType.equals("NUMBER")) {
			System.out.println(TWXResultGetter.NumberResult(response));
		}
		;
		if (baseType.equals("INTEGER")) {
			System.out.println(TWXResultGetter.IntegerResult(response));
		}
		;
		if (baseType.equals("BOOLEAN")) {
			System.out.println(TWXResultGetter.BooleanResult(response));
		}
		;
		if (baseType.equals("JSON")) {
			System.out.println(TWXResultGetter.JSONResult(response).toJSONString());
		}
		;
		if (baseType.equals("INFOTABLE")) {
			System.out.println(TWXResultGetter.InfotableResult(response));
		}
		;
	}

	static JSONObject j1 = null;
	static JSONObject rows = null;
	static JSONObject o1 = null;
	static JSONObject l1 = null;
	static JSONObject a1 = null;
	static JSONObject b1 = null;
	static String baseType = "";

	public static String categorize(Response rs) {
		try {
			JSONParser parser = new JSONParser();
			j1 = (JSONObject) parser.parse(rs.getBody().asString());
			rows = (JSONObject) parser.parse(rs.getBody().asString());
			if (!j1.containsKey("dataShape"))
				return "JSON";
			o1 = (JSONObject) j1.get("dataShape");
			l1 = (JSONObject) o1.get("fieldDefinitions");
			if (!l1.containsKey("result"))
				return "INFOTABLE";
			a1 = (JSONObject) l1.get("result");
			baseType = (String) a1.get("baseType");
			if (baseType.equals("INTEGER"))
				return "INTEGER";
			if (baseType.equals("NUMBER"))
				return "NUMBER";
			if (baseType.equals("STRING"))
				return "STRING";
			if (baseType.equals("BOOLEAN"))
				return "BOOLEAN";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	static JSONObject t1 = null;
	static JSONObject t12 = null;

	public static boolean checkClariaExistJSON(Response res, String name) {
		try {
			String result = "";
			JSONParser parser = new JSONParser();
			t1 = (JSONObject) parser.parse(res.getBody().asString());
			ArrayList t12 = (ArrayList) t1.get("rows");
			for (Object item : t12) {
				JSONObject i = (JSONObject) item;
				if (name.equals((String) i.get("name")))
					return true;
			}
		} catch (ParseException e) {
		}
		return false;
	}

	public static Long getClariaLastRestCall(Response res) {
		Long result = (long) 0.00;
		try {

			JSONParser parser = new JSONParser();
			t1 = (JSONObject) parser.parse(res.getBody().asString());
			ArrayList t12 = (ArrayList) t1.get("rows");
			for (Object item : t12) {
				JSONObject i = (JSONObject) item;
				result = (Long) i.get("LastRestCall");
				return result;
			}
		} catch (ParseException e) {
		}
		return result;
	}

	public static Long getClariaReportingLastChange(Response res) {
		Long result = (long) 0.00;
		try {

			JSONParser parser = new JSONParser();
			t1 = (JSONObject) parser.parse(res.getBody().asString());
			ArrayList t12 = (ArrayList) t1.get("rows");
			for (Object item : t12) {
				JSONObject i = (JSONObject) item;
				result = (Long) i.get("reportingLastChange");
				return result;
			}
		} catch (ParseException e) {
		}
		return result;
	}

	public static Long getAuditTimeStamp(Response res) {
		return null;

	}

	public static String getAction(Response res) {
		JSONObject Q1 = null;
		String result = "";
		Integer count = 1;
		try {

			JSONParser parser = new JSONParser();
			Q1 = (JSONObject) parser.parse(res.getBody().asString());
			ArrayList t12 = (ArrayList) Q1.get("rows");
			while (count != 0) {
				for (Object item : t12) {
					JSONObject i = (JSONObject) item;
					result = (String) i.get("action");
					return result;
				}
				count--;
			}
		} catch (ParseException e) {
		}
		return result;
	}

	public static String getAsset(Response res) {
		JSONObject Q1 = null;
		String result = "";
		Integer count = 1;
		try {

			JSONParser parser = new JSONParser();
			Q1 = (JSONObject) parser.parse(res.getBody().asString());
			ArrayList t12 = (ArrayList) Q1.get("rows");
			while (count != 0) {
				for (Object item : t12) {
					JSONObject i = (JSONObject) item;
					result = (String) i.get("asset");
					return result;
				}
				count--;
			}
		} catch (ParseException e) {
		}
		return result;
	}
	
	public static Long getTimeStamp(Response res) {
		JSONObject Q1 = null;
		Long result = 0L;
		Integer count = 1;
		try {

			JSONParser parser = new JSONParser();
			Q1 = (JSONObject) parser.parse(res.getBody().asString());
			ArrayList t12 = (ArrayList) Q1.get("rows");
			while (count != 0) {
				for (Object item : t12) {
					JSONObject i = (JSONObject) item;
					result = (Long) i.get("timestamp");
					return result;
				}
				count--;
			}
		} catch (ParseException e) {
		}
		return result;
	}
	
	

}
