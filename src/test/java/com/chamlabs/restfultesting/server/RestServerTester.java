package com.chamlabs.restfultesting.server;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: cham6
 * @author cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/restfultesting.git
 */

import junit.framework.TestCase;
import com.chamlabs.restfulservices.server.RestServer;
import com.jayway.restassured.response.ResponseBody;
import static com.jayway.restassured.RestAssured.given;

import org.json.JSONObject;

/**
 * Test Class to test the Rest Server that exposes a Rest API 
 * @author ChaM
 *
 */
public class RestServerTester extends TestCase {

	private static int PORT = 9080; 
	public RestServerTester() {
		System.out.println("Starting the test RestServerTester");
	}
	
	/**
     * Junit test to validate the RESTServer endpoints
     * Steps:
     * 		1) Invoke the startService method of RestServer to start the services
     * 		2) Using Rest-Assured framework, send a GET request to servergetevents endpoint
     * 			Validate the Response code is 202
     * 		3) Using Rest-Assured framework, send a POST request to serverpostevents endpoint
     * 		4) Validate the response body for the particular JSON object details. 
     * Expected Behavior:
     * 		> The Rest server should respond with proper Status codes and response body for different 
     * 		  endpoints and request types.
     * Finally:
     * 		> Invoke the method to Stop the stub server started in RestServer.
     */
	public void testRestServer() {
		
		try {
			RestServer.startService(PORT);
			int status = given().when().get("/servergetevents").getStatusCode();
			assertTrue("The response code from REST Server is different than 202", status == 202);
			
			ResponseBody response = given().when().post("/serverpostevents").getBody();
			String responseBody = response.toString();
			
			JSONObject postRequestJSON = new JSONObject(responseBody);
			assertTrue("The access_token in json is incorrect",
					postRequestJSON.get("access_token").toString()
					.equalsIgnoreCase("abf8714d-73a3-42ab-9df8-d13fcb92a1d8"));
			assertTrue("The refresh_token in json is incorrect",
					postRequestJSON.get("refresh_token").toString()
					.equalsIgnoreCase("d5a5ab08-c200-421d-ad46-2e89c2f566f5"));
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Test Failed due to exception : " + e);
		}
		finally {
			//Stop the started server.
			RestServer.stopService();
		}
	}
}
