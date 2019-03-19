# restfultesting
Project to help QA engineers understand the REST API services' testing. REST stands for "REpresentational State Transfer".

Time for some basics first.. Grab some popcorn and start reading. 

# What is REST Client: 
A method or a tool to invoke a REST service API that is exposed for communication by any system or service provider. For example: If an API is exposed to get the real time traffic information about a route from Google, the software/tool that invokes the Google traffic API is called the Rest client.

# What is REST Server: 
A method or an API that is exposed for communication by any system or service provider. For example: Google expose an API to get the real time traffic information on a given route. Here, the Google server needs to be up and running to listen to any requests to the exposed API, from different clients.

Lets establish a complete E2E scenario from the above examples.

# Scenario: 
Lets consider a taxi booking application like Uber. Uber needs the real time information on the traffic situation in and around the routes that their vehicles travel. 
# a. Rest Client: 
Here the client is Uber web or mobile application. This app sends a request the Rest API exposed by the Google maps to get the real time data. For ex: A HTTP GET request 
# b. Rest Server: 
Google's Server with endpoint exposed. In this example, Google is the Service provider. Google responds with the required details to Uber app's request. 

Apparently, both the client and server are equally important in REST communication. Hence, I have implemented examples for automation testing of both REST Client and Server. 

This project contains source and test modules for validating RestClients and RestServers. As QA engineers, the Source code is not of much interest to us. However, look at it for reference.

# Frameworks used: 

  >> Basic Java constructs
  
  >> Junit (Can be replaced by the mighty and simple TestNG)
  
  >> Restito (com.xebialabs.restito) 
  
  >> Apache httpclient (org.apache.httpcomponents)
  
  >> RestAssured (com.jayway.restassured)


