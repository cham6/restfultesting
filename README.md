# restfultesting
Project to help QA engineers understand the REST API services' testing. REST stands for "REpresentational State Transfer" and few basic definitions are as follows:

Time for some basics first.. Grab some snacks and start reading. 

# What is REST Client: 
A method or an API to invoke a REST API that is exposed for communication by any system or service provider. For example: If an API is exposed to get the real time traffic information on a route from Google, the software that invokes the Google traffic API is called the Rest client.

# What is REST Server: 
A method or an API that is exposed for communication by any system or service provider. For example: If Google expose an API to get the real time traffic information on a given route. Here, the Google server needs to be up and running to listen to any requests to the exposed API.

Lets establish a complete E2E scenario from the above examples.

# Scenario: 
Lets consider a mobile taxi booking application like Uber. Uber needs the real time information on the traffic situation in and around the routes that the vehicles travel. 
# a. Rest Client: 
Uber web or mobile application. This app sends a request the Rest API exposed by the Google maps to get the real time data. For ex: A HTTP GET request 
# b. Rest Server: 
Google responds with the required details to Uber app's request. 

Apparently, both the client and server are equally important in REST communication. Hence, I have implemented the automation testing of both REST Client and server. 

>  Contains source and test modules for validating RestClients and RestServers. As QA engineers, the Source code is not of much interest to you. However, look at it for reference.

Frameworks used: 

  >> Basic Java constructs.
  
  >> Junit (for unit testing)
  
  >> Restito (com.xebialabs.restito) 
  
  >> apache httpclient (org.apache.httpcomponents)
  
  >> RestAssured (com.jayway.restassured)
