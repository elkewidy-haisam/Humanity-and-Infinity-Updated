package com.humanity.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
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

import com.humanity.config.HumanityConfig;
import com.humanity.dao.ComicDAO;
import com.humanity.model.Comic;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class ComicDAOTest {
	
	private static final Logger log = Logger.getLogger(ComicDAOTest.class);
	
	@Autowired
	ComicDAO comicDAO;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	HumanityConfig humanConfig;
	
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllComicsTest() {
		
		assertEquals(5, comicDAO.getAllComics().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToStoreTest() {
		
		int numberComicsBefore = comicDAO.getAllComics().size();
		
		Comic comic = humanConfig.comic();
		comic.setTitle("Test Title");
		comic.setSynopsis("Test synopsis");
		comic.setPrice(123.45);
		comicDAO.addComicToStore(comic);
		
		int numberComicsAfter = comicDAO.getAllComics().size();
		
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
		comicDAO.updateComicCredentials(comic);
		
		assertEquals("Updating the title here.", comicDAO.findComicByTitle("Updating the title here.").getTitle());
		
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void findComicByChapterTest(){
		
		int chapter = 0;
		
		assertEquals("Updating the title here.", comicDAO.findComicByChapter(chapter).getTitle());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void findComicByTitleTest(){
		
		String title = "Updating the title here.";
		
		assertNotNull(comicDAO.findComicByTitle(title));
		assertEquals("Updating the title here.", comicDAO.findComicByTitle(title).getTitle());
		
	}
	

}
