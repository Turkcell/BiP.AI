package com.turkcell.bipai.merchantsimulator.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class ConfigLoader {

	private static final Logger logger = Logger.getLogger(ConfigLoader.class.getName());
	private static final String SYSTEM_FOLDER = System.getProperty("CONF_PATH");
	private static final String PRODUCTS_FILE = "products.json";
	private static final String CONFIGURATION_FILE = "configuration.json";
	private static final String PAYMENT_LISTENER_RESPONSES = "paymentListenerResponses.json";
	public static final Map<String, String> CONF_MAP = new LinkedHashMap<>();
	public static final Map<String, String> PAY_LIST_RESP_MAP = new LinkedHashMap<>();
	public static final Map<String, List<String>> PRODUCTS_MAP = new LinkedHashMap<>();
	static {
		try {
			initConfMap();
			initProductsMap();
			initPayListResp();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static String getConf(String confName) {
		return CONF_MAP.get(confName);
	}

	public static void initConfMap() throws FileNotFoundException, IOException, ParseException {
		String filename = getFilepath(CONFIGURATION_FILE);
		JSONObject jsonObject = getJsonObjectFromFile(filename);

		CONF_MAP.putAll(new Gson().fromJson(jsonObject.toJSONString(), LinkedHashMap.class));
	}

	public static void initPayListResp() throws FileNotFoundException, IOException, ParseException {
		String filename = getFilepath(PAYMENT_LISTENER_RESPONSES);
		JSONObject jsonObject = getJsonObjectFromFile(filename);

		PAY_LIST_RESP_MAP.putAll(new Gson().fromJson(jsonObject.toJSONString(), LinkedHashMap.class));
	}

	
	public static void initProductsMap() throws FileNotFoundException, IOException, ParseException {
		String filename = getFilepath(PRODUCTS_FILE);
		JSONObject jsonObject = getJsonObjectFromFile(filename);

		PRODUCTS_MAP.putAll(new Gson().fromJson(jsonObject.toJSONString(), LinkedHashMap.class));
	}

	private static String getFilepath(String filename) {
		if (SYSTEM_FOLDER == null) {
			logger.severe("Config path boş bırakılamaz.");
		}
		String filepath = SYSTEM_FOLDER;
		if (!filepath.endsWith("/"))
			filepath = filepath + "/";
		return filepath + filename;
	}

	public static JSONObject getJsonObjectFromFile(String filename)
			throws FileNotFoundException, IOException, ParseException {

		InputStreamReader isr = null;

		try {

			FileInputStream fis = new FileInputStream(filename);
			isr = new InputStreamReader(fis, "UTF8");

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(isr);
			JSONObject jsonObject = (JSONObject) obj;

			return jsonObject;

		} finally {

		}
	}

}
