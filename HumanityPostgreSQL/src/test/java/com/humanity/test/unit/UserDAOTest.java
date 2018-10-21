package com.humanity.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.dao.UserDAO;
import com.humanity.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class UserDAOTest {
	
	private static final Logger log = Logger.getLogger(UserDAOTest.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private HumanityConfig humanConfig;
	
	
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void register() {
		
		int currentUsersBefore = userDAO.getAllUsers().size();
		
		User user = humanConfig.user();
		user.setPassword("password");
		user.setUsername("elkewidyhaisam");
		
		userDAO.register(user);
		
		int currentUsersAfter = userDAO.getAllUsers().size();
		
		assertEquals(currentUsersBefore + 1, currentUsersAfter);
		
		
	}
	
	@Test
	public void login() {
		
		userDAO.login("elkewidyhaisam", "password");
		
	}
	
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void closeAccount() {
		
		int currentUsersBefore = userDAO.getAllUsers().size();
		
		User user = humanConfig.user();
		user.setPassword("password");
		user.setUsername("elkewidyhaisam");
		
		userDAO.closeAccount(user);
		
		int currentUsersAfter = userDAO.getAllUsers().size();
		
		assertEquals(currentUsersBefore - 1, currentUsersAfter);
		
	}


}
