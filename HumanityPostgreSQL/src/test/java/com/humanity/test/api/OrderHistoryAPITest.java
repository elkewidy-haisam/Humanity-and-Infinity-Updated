package com.humanity.test.api;

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

import static io.restassured.RestAssured.given;

import com.humanity.config.HumanityConfig;
import com.humanity.dao.OrderHistoryDAO;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;

import io.restassured.http.ContentType;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class OrderHistoryAPITest {
	
	private static final Logger log = Logger.getLogger(OrderHistoryAPITest.class);
	
	private String baseUrl = "http://localhost:8080/HumanityPostgreSQL";
	private String retrieveOrderHistoryByUser = "/orderhistory";
	private String addCartToOrderHistory = "/orderhistory/purchase";
	
	@Autowired
	private OrderHistoryDAO orderHistoryDAO;
	
	
	public void setOrderHistoryDAO(OrderHistoryDAO orderHistoryDAO) {
		
		this.orderHistoryDAO = orderHistoryDAO;
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void retrieveOrderHistoryByUserTest() {
		
		log.info("TESTING retrieveOrderHistoryByUser");
		
		User user = new User();
		given().contentType(ContentType.JSON).body(user).when().get(baseUrl + retrieveOrderHistoryByUser).then().assertThat().statusCode(302);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addCartToOrderHistoryTest() {
		
		log.info("TESTING addCartToOrderHistory");
		
		User user = new User();
		OrderHistory orderHistory = new OrderHistory();
		given().contentType(ContentType.JSON).body(user).body(orderHistory).when().post(baseUrl + addCartToOrderHistory).then().assertThat().statusCode(201);
	}
	

}
