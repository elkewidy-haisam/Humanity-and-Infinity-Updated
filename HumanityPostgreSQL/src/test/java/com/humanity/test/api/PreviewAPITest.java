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
import com.humanity.dao.PreviewDAO;
import com.humanity.model.Preview;

import io.restassured.http.ContentType;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class PreviewAPITest {
	
	private static final Logger log = Logger.getLogger(CartAPITest.class);
	
	private String baseUrl = "http://localhost:8080/HumanityPostgreSQL";
	private String getAllPreviews = "/previews";
	private String getSpecificPreview = "/previews/{chapter}";
	private String addPreview = "/previews/add";
	private String updatePreview = "/previews/update";
	
	@Autowired
	private PreviewDAO previewDAO;
	
	public void setPreviewDAO(PreviewDAO previewDAO) {
		
		this.previewDAO = previewDAO;
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllPreviewsTest() {
		
		log.info("TESTING getAllPreviews");
		given().contentType(ContentType.JSON).when().get(baseUrl + getAllPreviews).then().assertThat().statusCode(302);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getSpecificPreviewTest() {
		
		Preview preview = new Preview();
		
		int chapter = preview.getPreviewChapter();
		
		log.info("TESTING getSpecificPreview");
		given().contentType(ContentType.JSON).body(preview).pathParam("chapter", chapter).when().get(baseUrl + getSpecificPreview).then().assertThat().statusCode(302);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addPreviewTest() {
		
		log.info("TESTING addPreview");
		
		Preview preview = new Preview();
		
		given().contentType(ContentType.JSON).body(preview).when().post(baseUrl + addPreview).then().assertThat().statusCode(201);
	
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updatePreviewTest() {
		
		log.info("TESTING updatePreview");
		Preview preview = new Preview();
		given().contentType(ContentType.JSON).body(preview).when().put(baseUrl + updatePreview).then().assertThat().statusCode(200);
	}
	

}
