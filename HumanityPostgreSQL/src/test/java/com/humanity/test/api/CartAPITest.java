package com.humanity.test.api;

import org.apache.log4j.Logger;
import org.junit.Before;
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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;

import com.humanity.config.HumanityConfig;
import com.humanity.dao.CartDAO;
import com.humanity.dao.ComicDAO;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.User;

import io.restassured.http.ContentType;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class CartAPITest {
	
	private static final Logger log = Logger.getLogger(CartAPITest.class);
	
	private String baseUrl = "http://localhost:8080/HumanityPostgreSQL";
	private String createCart = "/createCart";
	private String addComicToCart = "/addComicToCart";
	private String retrieveShoppingCart = "/cart";
	private String removeAllItemsFromCart = "/remoeAllFromCart";
	private String removeComicFromCart = "removeFromCart";
	
	@Autowired
	HumanityConfig humanConfig;
	
	
	@Autowired
	static CartDAO cartDAO;
	
	@Autowired
	static ComicDAO comicDAO;
	
	User user;
	
	Cart cart;
	
	Comic comic;
	
	@Before
	public void components() {
		
		user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		comic = comicDAO.findComicByChapter(1);
		
		cart = cartDAO.retrieveShoppingCart(user);
		
	}
	
	@Autowired
	public void setCartDAO(CartDAO cartDAO) {
		
		CartAPITest.cartDAO = cartDAO;
		
	}
	
	@Autowired
	public void setComicDAO(ComicDAO comicDAO) {
		
		CartAPITest.comicDAO = comicDAO;
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createCartUponLoginTest() {
		
		log.info("TESTING createCartUponLogin");
		
		given().contentType(ContentType.JSON).body(user).when().post(baseUrl + createCart).then().assertThat().statusCode(201);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToCartTest() {
		
		log.info("TESTING addComicToCart");
		
		given().contentType(ContentType.JSON).body(comic).body(user).when().post(baseUrl + addComicToCart).then().assertThat().statusCode(201);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void retrieveShoppingCartTest() {
		
		log.info("TESTING retrieveShoppingCart");
		given().contentType(ContentType.JSON).body(user).when().get(baseUrl + retrieveShoppingCart).then().assertThat().statusCode(200);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeAllItemsFromCartTest() {
		
		log.info("TESTING removeAllItemsFromCart");
		given().contentType(ContentType.JSON).body(cart).when().delete(baseUrl + removeAllItemsFromCart).then().assertThat().statusCode(410);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeComicFromCartTest() {
		
		log.info("TESTING removeComicFromCartTest");
		given().contentType(ContentType.JSON).body(comic).body(cart).when().delete(baseUrl + removeComicFromCart).then().assertThat().statusCode(410);
		
	}
	
	
	
	
	
	
	
	

}
