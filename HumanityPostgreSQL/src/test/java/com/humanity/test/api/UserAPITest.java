package com.humanity.test.api;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.dao.UserDAO;
import com.humanity.model.User;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class UserAPITest {
	
private static final Logger log = Logger.getLogger(OrderHistoryAPITest.class);
	
	private String baseUrl = "http://localhost:8080/HumanityPostgreSQL";
	private String login = "/login/{username}/{password}";
	private String closeAccount = "/farewell";
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Autowired
	UserDAO userDAO;
	
	RequestSpecification request = new RequestSpecBuilder().build();
	ResponseSpecification response = new ResponseSpecBuilder().build();
	
	
	public void setOrderHistoryDAO(UserDAO userDAO) {
		
		this.userDAO = userDAO;
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void loginTest() {
		
		log.info("TESTING login");
		String username = "username3";
		String password = "password3";
		
		User user = humanConfig.user();
		user.setUsername(username);
		user.setPassword(password);
		
		userDAO.register(user);
		
		given().spec(request).contentType(ContentType.JSON).when().get(baseUrl + login, username, password).then().assertThat().statusCode(302).log().all();
		
		userDAO.closeAccount(user);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void closeAccount() {
		
		log.info("TESTING closeAccount");
		
		User user = new User();
		user.setUsername("username3");
		user.setPassword("password3");
		
		userDAO.register(user);
		
		given().spec(request).contentType(ContentType.JSON).body(user).when().delete(baseUrl + closeAccount).then().assertThat().statusCode(410).log().all();
		
		userDAO.closeAccount(user);
		
	}


}
