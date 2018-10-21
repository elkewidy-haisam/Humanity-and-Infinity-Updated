package com.humanity.test.mockControllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.web.WebAppConfiguration;

import org.mockito.runners.*;

import com.humanity.config.HumanityConfig;
import com.humanity.controllers.ComicController;
import com.humanity.model.Comic;
import com.humanity.services.ComicService;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
@WebAppConfiguration
public class ComicControllerTest {
	
	
	
	@InjectMocks
	ComicController comicController;
	
	@Mock
	ComicService comicServiceMock;
	
	Comic comic1, comic2, comic3, comic4, comic5;
	
	ArrayList<Comic> comics = new ArrayList<Comic>();
	
	@Before
	public void setup() {
		
		comic1 = new Comic();
		comic1.setChapter(1);
		comic1.setPrice(123.45);
		comic1.setSynopsis("Mock Synopsis 1");
		comic1.setTitle("Mock Title 1");
		
		comic2 = new Comic();
		comic2.setChapter(2);
		comic2.setPrice(123.45);
		comic2.setSynopsis("Mock Synopsis 2");
		comic2.setTitle("Mock Title 2");
		
		comic3 = new Comic();
		comic3.setChapter(3);
		comic3.setPrice(123.45);
		comic3.setSynopsis("Mock Synopsis 3");
		comic3.setTitle("Mock Title 3");
		
		comic4 = new Comic();
		comic4.setChapter(4);
		comic4.setPrice(123.45);
		comic4.setSynopsis("Mock Synopsis 4");
		comic4.setTitle("Mock Title 4");
		
		comic5 = new Comic();
		comic5.setChapter(5);
		comic5.setPrice(123.45);
		comic5.setSynopsis("Mock Synopsis 5");
		comic5.setTitle("Mock Title 5");
		
		comics.add(comic1);
		comics.add(comic2);
		comics.add(comic3);
		comics.add(comic4);
		comics.add(comic5);
		
		
		
	}
	
	@Test
	public void getAllComicsTest() throws Exception {
		

		 when(comicServiceMock.getAllComics()).thenReturn(Arrays.asList(comic1, comic2, comic3, comic4, comic5));
		 
		
		 
		 List<Comic> comics = comicController.getAllComics();
		 
		 assertEquals(5, comics.size());
		 
		 for (int i = 0; i < comics.size(); i++) {
			 
			 assertEquals(i + 1, comics.get(i).getChapter());
			 assertEquals(123.45, comics.get(i).getPrice(), 0);
			 assertEquals("Mock Title " + (i + 1), comics.get(i).getTitle());
			 assertEquals("Mock Synopsis " + (i + 1), comics.get(i).getSynopsis());
			 
		 }
		 
		 verify(comicServiceMock, times(1)).getAllComics();
		 verifyNoMoreInteractions(comicServiceMock);
		
	}
	
	@Test
	public void findComicByChapterTest() {
		
		
		when(comicController.findComicByChapter(1)).thenReturn(comic1);
		
		assertEquals(1, comicController.findComicByChapter(1).getChapter());
		assertEquals(123.45, comicController.findComicByChapter(1).getPrice(), 0);
		assertEquals("Mock Title 1", comicController.findComicByChapter(1).getTitle());
		assertEquals("Mock Synopsis 1", comicController.findComicByChapter(1).getSynopsis());
		
		verify(comicServiceMock, times(4)).findComicByChapter(1);
		verifyNoMoreInteractions(comicServiceMock);
		
		
	}
	
	@Test
	public void findComicByTitleTest() {
		
		when(comicServiceMock.findComicByTitle("Mock Title 2")).thenReturn(comic2);
		
		assertEquals(2, comicController.findComicByTitle("Mock Title 2").getChapter());
		assertEquals(123.45, comicController.findComicByTitle("Mock Title 2").getPrice(), 0);
		assertEquals("Mock Title 2", comicController.findComicByTitle("Mock Title 2").getTitle());
		assertEquals("Mock Synopsis 2", comicController.findComicByTitle("Mock Title 2").getSynopsis());
		
		verify(comicServiceMock, times(4)).findComicByTitle("Mock Title 2");
		verifyNoMoreInteractions(comicServiceMock);
		
	}
	
	@Test
	public void addComicToStoreTest() {
		
		Comic comic6 = new Comic();
		comic6.setChapter(6);
		comic6.setTitle("Mock Title 6");
		comic6.setSynopsis("Mock Synopsis 6");
		comic6.setPrice(123.45);
		
		comicServiceMock.addComicToStore(comic6);
		
		verify(comicServiceMock).addComicToStore(comic6);
		verifyNoMoreInteractions(comicServiceMock);
		
		
		
		
	}
	
	@Test
	public void updateComicCredentialsTest() {
		
		comic1.setTitle("Test Update Title 1");
		comicServiceMock.updateComicCredentials(comic1);
		
		comic1.setTitle("Test Title 1");
		comicServiceMock.updateComicCredentials(comic1);
		
		verify(comicServiceMock, times(2)).updateComicCredentials(comic1);
		verifyNoMoreInteractions(comicServiceMock);
		
		
	}
	
	

}
