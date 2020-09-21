package com.bapay.publicserver.Response;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.core.env.Environment;

public class ResponseDataMap extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;

	private Map<String, Object> headerMap = new HashMap<String, Object>();
	private Map<String, Object> headerCommonInfoMap = new HashMap<String, Object>();
	private Map<String, Object> bodyMap = new HashMap<String, Object>();
	private Map<String, Object> tailMap = new HashMap<String, Object>();
	private Map<String, Object> tailStatusMap = new HashMap<String, Object>();

	/********** 생성자 ****************/
	public ResponseDataMap(Environment environment) {

		//header.commonInfo 값 셋팅
		headerCommonInfoMap.put("locale",Locale.KOREA);

		//tail 값 셋팅
		tailMap.put("checksum", "N");	//hashType 으로 해시값 생성
		tailMap.put("endmark", environment != null ? environment.getProperty("server.response.endmark") : "");	//response endmark

		//JSON 구조 대로 map 셋팅
		put("tail",tailMap);
		put("body",bodyMap);
		put("header",headerMap);
	}
	
	public ResponseDataMap(Environment environment, Locale locale) {
		this(environment);
		headerCommonInfoMap.put("locale",locale);
	}

	public void setLocale(Locale locale) {
		headerCommonInfoMap.put("locale",locale);
	}

	public Locale getLocale() {
		return (Locale)headerCommonInfoMap.get("locale");
	}

	public void putHeader(String key, Object value) {
		headerMap.put(key,value);
	}
	public Object getHeader(String key) {
		return headerMap.get(key);
	}
	
	public Map<String, Object> getBody() {
		return bodyMap;
	}

	public void putData(String key, Object value) {
		bodyMap.put(key,value);
	}
	public Object getData(String key) {
		return bodyMap.get(key);
	}

	public void putHeaderCommonInfoMap(String key, Object value) {
		headerCommonInfoMap.put(key,value);
	}
	public Object getHeaderCommonInfoMap(String key) {
		return headerCommonInfoMap.get(key);
	}

	public void putTailStatus(String key, Object value) {
		tailStatusMap.put(key,value);
	}
	public Object getTailStatus(String key) {
		return tailStatusMap.get(key);
	}

	public void putTail(String key, Object value) {
		tailMap.put(key,value);
	}
	public Object getTail(String key) {
		return tailMap.get(key);
	}
}
