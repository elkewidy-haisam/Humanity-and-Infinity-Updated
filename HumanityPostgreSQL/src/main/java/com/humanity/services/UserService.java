package com.humanity.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.dao.UserDAO;
import com.humanity.model.User;

@Service
public class UserService {
	
	public static final Logger log = Logger.getLogger(UserService.class);
	private UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		
		this.userDAO = userDAO;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
		userDAO.login(username, password);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void closeAccount(User user) {
		// TODO Auto-generated method stub
		
		userDAO.closeAccount(user);
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void register(User user) {
		
		userDAO.register(user);
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<User> getAllUsers() {
		
		return userDAO.getAllUsers();
		
	}
	
	

}
