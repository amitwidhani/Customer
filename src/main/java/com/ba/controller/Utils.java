package com.ba.controller;

import static java.lang.System.getenv;

public class Utils {
	private static final String ENDPOINT_KEY = "BOOKING_ENDPOINT";
	private static final String SERVICE_NAME = "BOOKING_SERVICE";
	private static final String SERVICE_NAME_HOST = SERVICE_NAME + "_HOST";
	private static final String SERVICE_NAME_PORT = SERVICE_NAME + "_PORT";
	
	public static String getCustomerEndpoint(String resourcePath) {
		String baseUrl = "";
		
		System.out.println("Host" + getenv(SERVICE_NAME_HOST));
		System.out.println("Port" + getenv(SERVICE_NAME_PORT));
		
		if (!isEmpty(getenv(SERVICE_NAME_HOST)) // check kubernetes service 
				&& !isEmpty(getenv(SERVICE_NAME_PORT)))
			baseUrl = "http://" + getenv(SERVICE_NAME_HOST) + ":" + System.getenv(SERVICE_NAME_PORT); 
		
		if (isEmpty(baseUrl)) { // check system properties
			baseUrl = System.getProperty(ENDPOINT_KEY);
		}
		
		if (isEmpty(baseUrl)) { // check environment variables
			baseUrl = System.getenv(ENDPOINT_KEY);
		}
		
		if (isEmpty(baseUrl)) { // default value
			baseUrl = "http://localhost:8080";
		} 
		System.out.println("Final Url " + baseUrl + resourcePath);
		return baseUrl + resourcePath;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
}
