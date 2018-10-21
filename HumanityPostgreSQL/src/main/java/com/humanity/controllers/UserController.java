package com.humanity.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.humanity.dao.UserDAO;
import com.humanity.model.User;
import com.humanity.services.UserService;

@RestController
@RequestMapping("/humanity")
public class UserController {
	
	public static final Logger log = Logger.getLogger(UserService.class);
	private UserService userService;
	
	
	
	@Autowired
	public void setUserSerivce(UserService userService) {
		
		this.userService = userService;
		
	}
	
	@RequestMapping(value="/login/{username}/{password}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void login(@PathVariable String username, @PathVariable String password) {
		// TODO Auto-generated method stub
		
		userService.login(username, password);
		
	}

	@RequestMapping(value="/farewell", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void closeAccount(@Valid @RequestBody User user) {
		// TODO Auto-generated method stub
		
		userService.closeAccount(user);
		
	}
	
}
