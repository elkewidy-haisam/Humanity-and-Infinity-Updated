package com.humanity.framework;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class RestAssuredConfiguration {
	
	
	@BeforeClass
	public void configure() {
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/HumanityPostgreSQL";
		
		
		
		
	}

}
