package com.chamlabs.restfultesting.client;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: cham6
 * @author cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/restfultesting.git
 */

import junit.framework.TestCase;

import com.chamlabs.restfulservices.client.RestClient;
import com.chamlabs.restfultesting.util.TestUtil;
import com.xebialabs.restito.semantics.Call;
import com.xebialabs.restito.server.StubServer;
import static org.glassfish.grizzly.http.util.HttpStatus.ACCEPTED_202;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

/**
 * This class contains several junit tests to validate the RestClient operations like: 
 * 		sendRequest(..)
 * 		sendRequestWithCustomHeaders(..) 
 * 		sendPOSTRequestWithJSONBody(..)
 * 
 * @author cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/restfultesting.git
 *
 */
public class RestClientTester extends TestCase {
	
    private static final Integer PORT = 9098;
    private static final Integer PORT2 = 9099;
    private static final Integer PORT3 = 9097;
	
    public RestClientTester() {
    	System.out.println("Starting the test RestClientTester");
    }
    /**
     * Junit test to validate the GET request from RestClient
     * Steps:
     * 		1) Create a stub server using Restito framework and configure it to listen on given port
     * 		2) Invoke the sendGETRequest(..) method of RestClient
     * 		3) Restito captures the matching GET requests sent, if any.
     * 		4) Validate if Restito has captured any GET requests on given endpoint
     * Expected Behavior:
     * 		> Restito should have captured GET request and it should have captured only one GET request.
     * Finally:
     * 		> Stop the stub server started using restito.
     */
	public void testGETRequestFromClient() {
		
		StubServer server = null;
		try {
		//This will start the stub server on 'PORT' and responds with HTTP 202 'ACCEPTED_202'
		TestUtil.restartRestitoServerForGETRequests(server, PORT, ACCEPTED_202);
		
		RestClient.sendGETRequest(PORT);
		
		List<Call> callList = TestUtil.waitAndGetCallList(server, 30);
		assertTrue("GET request is not received from the RestClient. Test failed.", 
				(callList != null) && (callList.size() == 1));
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Test Failed due to exception : " + e);
		}
		finally {
			if(server != null) {
				server.stop();
            }
		}	
	}
	
	/**
     * Junit test to validate the GET request with headers from RestClient
     * Steps:
     * 		1) Create a stub server using Restito framework and configure it to listen on given port
     * 		2) Invoke the sendGETRequestWithCustomHeaders(..) method of RestClient
     * 		3) Restito captures the matching GET requests sent, if any.
     * 		4) Validate if Restito has captured any GET requests on given endpoint
     * Expected Behavior:
     * 		> Restito should have captured GET request and it should have captured only one GET request.
     * 		> Get the headers of the captured GET request 
     * 		  and make sure the headers match to the ones configured.
     * Finally:
     * 		> Stop the stub server started using restito.
     */
	public void testGETRequestWithHeadersFromClient() {
		StubServer server = null;
		
		try {
		//This will start the stub server on 'PORT' and responds with HTTP 202 'ACCEPTED_202'
			TestUtil.restartRestitoServerForGETRequests(server, PORT2, ACCEPTED_202);
		
		RestClient.sendGETRequestWithCustomHeaders(PORT2);
		
		List<Call> callList = TestUtil.waitAndGetCallList(server, 30);
		assertTrue("GET request is not received from the RestClient. Test failed.", 
				(callList != null) && (callList.size() == 1));
		
		//Validate the headers of the GET request from REST Client
		Map<String, List<String>> headersFromRequest = callList.get(0).getHeaders();
		assertTrue("GET request contains header Accept and its value ",
				headersFromRequest.get("Accept").contains("text/html"));
		assertTrue("GET request contains header Authorization and its value ",
				headersFromRequest.get("Authorization").contains("Bearer 1234567890qwertyuiop"));
		assertTrue("GET request contains header Cache-Control and its value ",
				headersFromRequest.get("Cache-Control").contains("no-cache"));
		assertTrue("GET request contains header Connection and its value ",
				headersFromRequest.get("Connection").contains("keep-alive"));
		assertTrue("GET request contains header Content-Type and its value ",
				headersFromRequest.get("Content-Type").contains("application/json"));
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Test Failed due to exception : " + e);
		}
		finally {
			if(server != null) {
				server.stop();
            }
		}
	}
	
/**
     * Junit test to validate the POST request with body and headers from RestClient
     * Steps:
     * 		1) Create a stub server using Restito framework and configure it to listen on given port
     * 		2) Invoke the sendPOSTRequestWithJSONBody(..) method of RestClient
     * 		3) Restito captures the matching POST requests sent, if any.
     * 		4) Validate if Restito has captured any POST requests on given endpoint
     * Expected Behavior:
     * 		> Restito should have captured POST request and it should have captured only one POST request.
     * 		> Get the body of the captured POST request and validate the JSON values
     * Finally:
     * 		> Stop the stub server started using restito.
	 */
	public void testPOSTRequestWithJSONBody() {
		StubServer server = null;
		
		try {
		//This will start the stub server on 'PORT' and responds with HTTP 202 'ACCEPTED_202'
			TestUtil.restartRestitoServerForPOSTRequests(server, PORT3, ACCEPTED_202);
		
		RestClient.sendPOSTRequestWithJSONBody(PORT3);
		
		List<Call> callList = TestUtil.waitAndGetCallList(server, 30);
		assertTrue("POST request is not received from the RestClient. Test failed.", 
				(callList != null) && (callList.size() == 1));
		
		//Validate the headers of the GET request from REST Client
		
		String requestBody = callList.get(0).getPostBody();
		JSONObject postRequestJSON = new JSONObject(requestBody);
		assertTrue("The timeUpdated in json is incorrect",
				postRequestJSON.get("timeUpdated").toString().equalsIgnoreCase("1535703838478"));
		assertTrue("The access_token in json is incorrect",
				postRequestJSON.get("access_token").toString().
				equalsIgnoreCase("abf8714d-73a3-42ab-9df8-d13fcb92a1d8"));
		assertTrue("The refresh_token in json is incorrect",
				postRequestJSON.get("refresh_token").toString().
				equalsIgnoreCase("d5a5ab08-c200-421d-ad46-2e89c2f566f5"));
		assertTrue("The token_type in json is incorrect",
				postRequestJSON.get("token_type").toString().equalsIgnoreCase("bearer"));
		assertTrue("The expires_in in json is incorrect",
				postRequestJSON.get("expires_in").toString().equalsIgnoreCase("1024"));
		assertTrue("The scope in json is incorrect",
				postRequestJSON.get("scope").toString().equalsIgnoreCase(""));
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Test Failed due to exception : " + e);
		}
		finally {
			if(server != null) {
				server.stop();
            }
		}
	}
}
