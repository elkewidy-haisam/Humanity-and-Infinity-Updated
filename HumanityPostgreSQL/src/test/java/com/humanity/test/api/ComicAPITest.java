package com.humanity.test.api;

import static org.hamcrest.CoreMatchers.is;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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
import com.humanity.dao.ComicDAO;
import com.humanity.model.Comic;

import io.restassured.http.ContentType;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class ComicAPITest {

	private static final Logger log = Logger.getLogger(ComicAPITest.class);
	
	private String baseUrl = "http://localhost:8080/Humanity";
	private String getComics = "/comics/getAllComics";
	private String addComicToStore = "/addComicToStore";
	private String updateComic = "/updateComic";
	private String findComicByChapter = "/findComicByChapter/{chapter}";
	private String findComicByTitle = "/findComicByTitle/{title}";
	
	
	private ComicDAO comicDAO;
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Autowired
	public void setComicDAO(ComicDAO comicDAO) {
		
		this.comicDAO = comicDAO;
		System.out.println("ComicDAO is there? " + this.comicDAO);
		System.out.println("HumanityConfig: " + humanConfig);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllComicsTest() {
		
		log.info("TESTING getAllComics");
		given().contentType(ContentType.JSON)
			.when().get(baseUrl + getComics)
				.then()
					.assertThat()
					.body("size()", is(5));
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Ignore
	public void addComicToStoreTest() {
		
		log.info("TESTING addComicToStore");
		
		Comic comic = humanConfig.comic();
		comic.setChapter(3);
		comic.setPrice(123.32);
		comic.setTitle("Rest Assured 2: Machine Version 2");
		comic.setSynopsis("Testing via REST Assured 2");
		
		given().contentType(ContentType.JSON).body(comic).when().post(baseUrl + addComicToStore).then().assertThat().statusCode(201);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Ignore
	public void updateComicCredentialsTest() {
		
		log.info("TESTING updateComicCredentials");
		
		Comic comic = humanConfig.comic();
		comic.setChapter(1);
		comic.setPrice(123.32);
		comic.setTitle("Rest Assured: Machine Version");
		comic.setSynopsis("Testing via REST Assured");
		given().contentType(ContentType.JSON).body(comic).when().put(baseUrl + updateComic).then().assertThat().statusCode(200);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Ignore
	public void findComicByChapterTest() {
		
		log.info("TESTING findComicByChapter");
		
		int chapter = 1;
		
		given().contentType(ContentType.JSON).pathParam("chapter", chapter).when().put(baseUrl + findComicByChapter).then().assertThat().statusCode(302);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Ignore
	public void findComicByTitleTest() {
		
		log.info("TESTING findComicByTitle");
		
		String title = "Updating the title here.";
		
		given().contentType(ContentType.JSON).pathParam("title", title).when().get(baseUrl + findComicByTitle).then().assertThat().statusCode(302);
		
	}
	
	
	
}
