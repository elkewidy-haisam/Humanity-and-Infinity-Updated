package com.humanity.test.integration;

import static org.junit.Assert.assertEquals;

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
import com.humanity.model.User;
import com.humanity.services.UserService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class UserServiceTest {
	
	private static final Logger log = Logger.getLogger(UserServiceTest.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void register() {
		
		int currentUsersBefore = userService.getAllUsers().size();
		
		User user = humanConfig.user();
		user.setPassword("password");
		user.setUsername("elkewidyhaisam");
		
		userService.register(user);
		
		int currentUsersAfter = userService.getAllUsers().size();
		
		assertEquals(currentUsersBefore + 1, currentUsersAfter);
		
		
	}
	
	@Test
	public void login() {
		
		User user = humanConfig.user();
		user.setPassword("password");
		user.setUsername("elkewidyhaisam");
		
		userService.register(user);
		userService.login("elkewidyhaisam", "password");
		
		userService.closeAccount(user);
		
	}
	
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void closeAccount() {
		
		int currentUsersBefore = userService.getAllUsers().size();
		
		User user = humanConfig.user();
		user.setPassword("password");
		user.setUsername("elkewidyhaisam");
		
		userService.closeAccount(user);
		
		int currentUsersAfter = userService.getAllUsers().size();
		
		assertEquals(currentUsersBefore, currentUsersAfter);
		
	}

}
