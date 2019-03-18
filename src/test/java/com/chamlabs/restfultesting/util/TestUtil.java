package com.chamlabs.restfultesting.util;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.post;
import java.util.List;
import org.glassfish.grizzly.http.util.HttpStatus;
import com.xebialabs.restito.semantics.Call;
import com.xebialabs.restito.server.StubServer;

/**
 * This utility class contains several utility methods like : 
 * 		restartRestitoServerForGETRequests(..)
 * 		restartRestitoServerForPOSTRequests(..) 
 * 		waitAndGetCallList(..)
 * 
 * @author cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/restfultesting.git
 *
 */
public class TestUtil {
	
	/**
	 * Utility method to start restito stub server to accept GET requests
	 * @param server
	 * @param port
	 * @param status
	 */
	public static void restartRestitoServerForGETRequests (StubServer server, int port, HttpStatus status)
    {
        // Kill the restito server
        if (server != null) {
            server.stop();
        }
        // Initialize and configure a newer instance of the stub server
        server = new StubServer(port).run();
        whenHttp(server).match(get("/getevents")).then(status(status));
    }
	
	/**
	 * Utility method to start restito stub server to accept POST requests
	 * @param server
	 * @param port
	 * @param status
	 */
	public static void restartRestitoServerForPOSTRequests (StubServer server, int port, HttpStatus status)
    {
        // Kill the restito server
        if (server != null) {
            server.stop();
        }
        // Initialize and configure a newer instance of the stub server
        server = new StubServer(port).run();
        whenHttp(server).match(post("/postevents")).then(status(status));
    }
	
	/**
     * For a given restito stub server, loop for the given amount of seconds and
     * break and return the call list from server.
     * 
     * @param server
     * @param waitTimeInSeconds
     * @return
     * @throws InterruptedException
     */
	public static List<Call> waitAndGetCallList (StubServer server, int waitTimeInSeconds)
        throws InterruptedException
    {
        int timeoutCount = 0;
        List<Call> callList = server.getCalls();
        while (callList.isEmpty()) {
            Thread.sleep(1000);
            timeoutCount++;
            if (timeoutCount >= waitTimeInSeconds) {
                break;
            }
            callList = server.getCalls();
        }
        // Wait for 2 seconds to get all the calls into callList to Eliminate any falkyness.
        Thread.sleep(2000);
        return server.getCalls();
    }


}
