package com.humanity.test.mockControllers;

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
import org.mockito.Mock;
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
public class UserControllerTest {
	
	
	@Mock
	UserService userServiceMock;
	
	@InjectMocks
	UserController userController;
	
	User user = new User();
	
	@Before
	public void setUp() {
		
		user.setUsername("testUsername");
		user.setPassword("testPassword");

		
	}
	
	@Test
	public void loginTest() {
		
		userController.login("testUsername", "testPassword");
		verify(userServiceMock).login("testUsername", "testPassword");
		verifyNoMoreInteractions(userServiceMock);
		
	}
	
	@Test
	public void closeAccount() {
		
		userController.closeAccount(user);
		verify(userServiceMock).closeAccount(user);
		verifyNoMoreInteractions(userServiceMock);
		
	}

}
