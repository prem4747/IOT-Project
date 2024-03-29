package com.mongo.crud.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class MethodUtils {

	private MethodUtils() {
	}

	public static String prepareErrorJSON(HttpStatus status, Exception ex) {
		JSONObject errorJSON = new JSONObject();
		try {
			errorJSON.put("status", status.value());
			errorJSON.put("error", status.getReasonPhrase());
			errorJSON.put("message", ex.getMessage().split(":")[0]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return errorJSON.toString();
	}
}
