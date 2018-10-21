package com.humanity.test.mvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import  static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.humanity.config.HumanityConfig;
import com.humanity.controllers.UserController;
import com.humanity.model.User;
import com.humanity.services.UserService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class UserControllerMvcTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Autowired
	private UserService userServiceMock;
	
	@InjectMocks
	private UserController controller;
	
	@Before
	public void setUp() {

	    this.mockMvc = standaloneSetup(controller).build();

	}
	
	private String baseUrl = "http://localhost:8080/HumanityPostgreSQL";
	
	
	@Test
	public void loginTest() throws Exception {
		
		String username = "username";
		String password = "password";
		
		User user = humanConfig.user();
		user.setUsername(username);
		user.setPassword(password);
		
		userServiceMock.register(user);
		
		mockMvc.perform(get("/login")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.param("username", username)
				.param("password", password)
				).andExpect(status().isFound());
					
		verify(userServiceMock, times(1)).login(username, password);
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void closeAccountTest() throws Exception {
		
		String username = "username2";
		String password = "password2";
		
		User user = humanConfig.user();
		user.setUsername(username);
		user.setPassword(password);
		
		userServiceMock.register(user);
		
		mockMvc.perform(delete("/farewell")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(status().isGone());
		
		verify(userServiceMock, times(1)).closeAccount(user);
		verifyNoMoreInteractions(userServiceMock);
		
	}
	

}
