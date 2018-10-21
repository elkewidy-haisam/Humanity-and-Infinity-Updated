package com.humanity.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
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
import com.humanity.model.Comic;
import com.humanity.services.ComicService;
import com.humanity.test.unit.ComicDAOTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class ComicServiceTest {

private static final Logger log = Logger.getLogger(ComicServiceTest.class);
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	HumanityConfig humanConfig;
	
		
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllComicsTest() {
		
		assertEquals(5, comicService.getAllComics().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToStoreTest() {
		
		int numberComicsBefore = comicService.getAllComics().size();
		
		Comic comic = humanConfig.comic();
		comic.setTitle("Test Title");
		comic.setSynopsis("Test synopsis");
		comic.setPrice(123.45);
		comicService.addComicToStore(comic);
		
		int numberComicsAfter = comicService.getAllComics().size();
		
		assertEquals(numberComicsAfter, numberComicsBefore + 1);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateComicCredentialsTest(){
		
		Comic comic = humanConfig.comic();
		comic.setTitle("Test Title");
		comic.setSynopsis("Test synopsis");
		comic.setPrice(123.45);
		
		comic.setTitle("Updating the title here.");
		comicService.updateComicCredentials(comic);
		
		assertEquals("Updating the title here.", comicService.findComicByTitle("Updating the title here.").getTitle());
		
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void findComicByChapterTest(){
		
		int chapter = 0;
		
		assertEquals("Updating the title here.", comicService.findComicByChapter(chapter).getTitle());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void findComicByTitleTest(){
		
		String title = "Updating the title here.";
		
		assertNotNull(comicService.findComicByTitle(title));
		assertEquals("Updating the title here.", comicService.findComicByTitle(title).getTitle());
		
	}
	
	
}
