package com.chamlabs.restfulservices.server;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: cham6
 * @author cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/restfultesting.git
 */

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.post;
import static org.glassfish.grizzly.http.util.HttpStatus.ACCEPTED_202;
import static com.xebialabs.restito.semantics.Action.contentType;
import static com.xebialabs.restito.semantics.Action.stringContent;
import org.json.JSONObject;
import com.xebialabs.restito.server.StubServer;

public class RestServer {
	
	public static StubServer server = null;
	public RestServer() {
		System.out.println("Rest Server's constructor is called");
	}
	/**
	 * Method  to start the Rest Services using Restito. 
	 * Starts and listen at 2 endpoints, namely, servergetevents and serverpostevents
	 * @param port
	 * @return true if the services started successfully. False, otherwise.
	 */
	public static boolean startService(int port) {
		try {
				if (server != null) {
	            server.stop();
				}
			
				JSONObject jsonObject = null;
				jsonObject = new JSONObject();
		        jsonObject.put("access_token", "abf8714d-73a3-42ab-9df8-d13fcb92a1d8");
		        jsonObject.put("refresh_token", "d5a5ab08-c200-421d-ad46-2e89c2f566f5");
		        jsonObject.put("token_type", "bearer");
		        // Initialize and configure a newer instance of the stub server
		        server = new StubServer(port).run();
		        whenHttp(server).match(get("/servergetevents")).then(status(ACCEPTED_202));
		        whenHttp(server).match(post("/serverpostevents")).then(status(ACCEPTED_202),
		        		stringContent(jsonObject.toString()),contentType("application/json"));
		        return true;
			}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Some exception has occurred during the HTTP server creation."
				+ "Returning false");
		return false;
	}
	
	/**
	 * Method  to stop the Rest Services running. 
	 * @return true if the service stopped successfully. False, otherwise.
	 */
	public static boolean stopService() {
		try {
				if (server != null) {
	            server.stop();
				}
		        return true;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Some exception has occurred during the HTTP server stop."
				+ "Returning false");
		return false;
	}

}
