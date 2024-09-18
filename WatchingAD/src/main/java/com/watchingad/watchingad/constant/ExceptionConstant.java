package com.watchingad.watchingad.constant;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ExceptionConstant implements BaseConstant {

	;
	
	public static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "필수 파라메터가 없습니다.";
	public static final String MAIL_EXCEPTION_MESSAGE = "메일 전송 중 에러가 발생했습니다.";
	public static final String USER_ALREADY_EXCEPTION_MESSAGE = "이미 가입된 이메일입니다.";
	public static final String NOT_FOUND_USER_EXCEPTION_MESSAGE = "아이디나 비밀번호를 확인해주세요.";
	public static final String NOT_FOUND_TOKEN_EXCEPTION_MESSAGE = "토큰을 찾을 수 없습니다.";
	public static final String TOKEN_IS_EXPIRE = "토큰이 만료되었습니다.";
	
	private static final Map<Object, String> itemToValue = new HashMap<Object, String>();
	private int key;
	private String value;

	static {
		for (ExceptionConstant value : values()) {
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

	ExceptionConstant( int key, String value ) {
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
