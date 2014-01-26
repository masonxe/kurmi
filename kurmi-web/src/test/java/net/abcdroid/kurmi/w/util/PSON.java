package net.abcdroid.kurmi.w.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;

public class PSON {

	private Map<String, Object> psonData;
	private Gson gson;

	private static String JSON_HEADER = "{";
	private static String JSON_FOOTER = "}";
	private static String JSON_SEPERATOR = ",";

	public PSON() {
	}

	private void reuse() {
		psonData = null;
		gson = null;
	}

	public void put(String key, Object obj) {
		if (psonData == null)
			psonData = new HashMap<String, Object>();

		psonData.put(key, obj);
	}

	public void setMap(Map<String, Object> psonData) {
		this.psonData = psonData;
	}

	public void appendMap(Map<String, Object> psonData) {
		psonData.putAll(psonData);
	}

	public String toJSON() {
		StringBuilder returnJSON = new StringBuilder(JSON_HEADER);
		if (gson == null)
			gson = new Gson();

		Iterator<String> it = psonData.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object obj = psonData.get(key);

			returnJSON.append("\"").append(key).append("\":")
					.append(gson.toJson(obj));
			if (it.hasNext())
				returnJSON.append(JSON_SEPERATOR);
		}

		reuse();
		return returnJSON.append(JSON_FOOTER).toString();
	}

	public String toJSON(Object obj) {
		if (gson == null)
			gson = new Gson();

		return gson.toJson(obj);
	}

}