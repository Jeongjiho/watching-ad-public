package com.watchingad.watchingad.constant;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CommonConstant implements BaseConstant {

	;
	
	public static final String COMMON_YN_Y = "Y";
	public static final String COMMON_YN_N = "N";
	public static final String AUTHORIZATION_HEADER = "X-AUTH-TOKEN";
	public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
	public static final String REFRESH_HASH = "REFRESH_HASH";
	
	private static final Map<Object, String> itemToValue = new HashMap<Object, String>();
	private int key;
	private String value;

	static {
		for (CommonConstant value : values()) {
			itemToValue.put(value.getKey(), value.getValue());
		}
	}

	public static String getValue( String key ) {
		if ( itemToValue.containsKey(key) ) {
			return itemToValue.get(key);
		}
		return "";
	}

	public static String getKey( String value ) {

		String key = "";

		Iterator<Map.Entry<Object, String>> iterator = itemToValue.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Object, String> entry = iterator.next();
			if ( entry.getValue().equals(value) ) {
				key = (String) entry.getKey();
				break;
			}
		}

		return key;

	}

	CommonConstant( int key, String value ) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Object getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setKey( Object key ) {
		this.key = (int) key;
	}

	@Override
	public void setValue( String value ) {
		this.value = value;
	}

}
