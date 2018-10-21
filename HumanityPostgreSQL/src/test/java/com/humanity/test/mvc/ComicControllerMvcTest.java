package com.humanity.test.mvc;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import net.minidev.json.JSONArray;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.humanity.config.HumanityConfig;
import com.humanity.controllers.ComicController;
import com.humanity.services.ComicService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class ComicControllerMvcTest {
	
	
	MockMvc mockMvc;
	
	ComicService comicServiceMock;
	
	ComicController comicController;
	
	@Autowired
	private WebApplicationContext context;
	
	
	@Before
	public void setup() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		comicController = new ComicController();
		comicServiceMock = org.mockito.Mockito.mock(ComicService.class);
		
	}
	
	@Test
	public void getAllComicsMvcTest() throws Exception {
		
	mockMvc.perform(get("/comics/getAllComics"))
		.andExpect(jsonPath("$[0].chapter", is(1)))
        .andExpect(jsonPath("$[0].price", is(123.45)))
        .andExpect(jsonPath("$[0].title", is("Test Title 1")))
        .andExpect(jsonPath("$[0].synopsis", is("Test Synopsis 1")))
	    	 .andExpect(jsonPath("$[1].chapter", is(2)))
	         .andExpect(jsonPath("$[1].price", is(123.45)))
	         .andExpect(jsonPath("$[1].title", is("Test Title 2")))
	         .andExpect(jsonPath("$[1].synopsis", is("Test Synopsis 2")))
		    	 .andExpect(jsonPath("$[2].chapter", is(3)))
		         .andExpect(jsonPath("$[2].price", is(123.45)))
		         .andExpect(jsonPath("$[2].title", is("Test Title 3")))
		         .andExpect(jsonPath("$[2].synopsis", is("Test Synopsis 3")))
			    	 .andExpect(jsonPath("$[3].chapter", is(4)))
			         .andExpect(jsonPath("$[3].price", is(123.45)))
			         .andExpect(jsonPath("$[3].title", is("Test Title 4")))
			         .andExpect(jsonPath("$[3].synopsis", is("Test Synopsis 4")))
				    	 .andExpect(jsonPath("$[4].chapter", is(5)))
				         .andExpect(jsonPath("$[4].price", is(123.45)))
				         .andExpect(jsonPath("$[4].title", is("Test Title 5")))
				         .andExpect(jsonPath("$[4].synopsis", is("Test Synopsis 5")))
					         .andExpect(status().isOk())
					         .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					         .andExpect(( jsonPath("$", hasSize(5))));
	 
	 System.out.println(jsonPath("$").toString());
	 
	 	/* String jsonString; // find a way to parse JSON path to String to confirm you are actually seeing the JSON.
	    DocumentContext docCtx = JsonPath.parse(jsonString);
	    JsonPath jsonPath = JsonPath.compile("$.list[?(@.name == \"foo1\")]");
	    JSONArray val1=docCtx.read(jsonPath);
	    System.out.println(val1); */
		
		
	}
	
	

}
