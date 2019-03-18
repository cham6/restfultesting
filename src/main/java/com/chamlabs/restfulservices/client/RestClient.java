package com.chamlabs.restfulservices.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 * This class contains several methods to send different types of HTTP requests like: 
 * 		sendRequest(..)
 * 		sendRequestWithCustomHeaders(..) 
 * 		sendPOSTRequestWithJSONBody(..)
 * 
 * @author cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/restfultesting.git
 *
 */
public class RestClient {
		
	public RestClient() {
		System.out.println("Creating RestClient constructor");
	}

	/**
	 * Method to Send GET request to http://localhost:<<port>>/getevents
	 * @param port
	 * @return true if GET request is successfully sent. False, otherwise.
	 */
	public static boolean sendGETRequest(int port) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet("http://localhost:" + port + "/getevents");
			//HttpResponse response = client.execute(request);
			client.execute(getRequest);
			System.out.println("HTTP request is sent successfully."
					+ "Returning True");
			return true;
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Some exception has occurred during the HTTP Client creation."
				+ "Returning false");
		return false;
	}
	
	/**
	 * Method to Send GET request with headers to http://localhost:<<port>>/getevents
	 * @param port
	 * @return true if GET request is successfully sent. False, otherwise.
	 */
	public static boolean sendGETRequestWithCustomHeaders(int port) {
		
		try {
			Map<String, String> headerValueMap = new HashMap<String, String>();
			headerValueMap.put("Authorization","Bearer 1234567890qwertyuiop");
			headerValueMap.put("Accept", "text/html");
			headerValueMap.put("Cache-Control", "no-cache");
			headerValueMap.put("Connection", "keep-alive");
			headerValueMap.put("Content-Type", "application/json");
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet("http://localhost:" + port + "/getevents");
			
			for (Map.Entry<String,String> entry : headerValueMap.entrySet())  {
				getRequest.addHeader(entry.getKey(), entry.getValue());
			}
			//HttpResponse response = client.execute(request);
			client.execute(getRequest);
			System.out.println("HTTP request is sent successfully."
					+ "Returning True");
			return true;
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Some exception has occurred during the HTTP Client creation."
				+ "Returning false");
		return false;
	}
	
	/**
	 * Method to Send POST request with body to http://localhost:<<port>>/postevents
	 * @param port
	 * @return true if POST request is successfully sent. False, otherwise.
	 */
	public static boolean sendPOSTRequestWithJSONBody(int port) {
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject();
            jsonObject.put("timeUpdated", 1535703838478L);
            jsonObject.put("access_token", "abf8714d-73a3-42ab-9df8-d13fcb92a1d8");
            jsonObject.put("refresh_token", "d5a5ab08-c200-421d-ad46-2e89c2f566f5");
            jsonObject.put("token_type", "bearer");
            jsonObject.put("scope", "");
            jsonObject.put("expires_in", 1024);
            
            String json = jsonObject.toString();
            StringEntity entity = new StringEntity(json);
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://localhost:" + port + "/postevents");
			postRequest.setEntity(entity);
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Content-type", "application/json");

			//HttpResponse response = client.execute(request);
			client.execute(postRequest);
			System.out.println("HTTP request is sent successfully."
					+ "Returning True");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Some exception has occurred during the HTTP Request creation."
				+ "Returning false");
		return false;
	}

}
