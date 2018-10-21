package com.humanity.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humanity.model.Cart;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;
import com.humanity.services.OrderHistoryService;

@RestController
@RequestMapping("/humanity")
public class OrderHistoryController {
	
	private static final Logger log = Logger.getLogger(CartController.class);
	private OrderHistoryService orderHistoryService;
	
	@Autowired
	public void setCartService(OrderHistoryService orderHistoryService) {
		
		this.orderHistoryService = orderHistoryService;
		
	}
	
	@RequestMapping(value="/orders/orderhistory", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public OrderHistory retrieveOrderHistoryByUser(@Valid @RequestBody User user) {
		
		return orderHistoryService.retrieveOrderHistoryByUser(user);
		
	}
	
	@RequestMapping(value="/orders/purchase", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addCartToOrderHistory(@Valid @RequestBody OrderHistory orderHistory, User user) {
		
		orderHistoryService.addCartToOrderHistory(orderHistory, user);
		
	}
	
	@RequestMapping(value="/orders/createOrderHistory", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createOrderHistoryUponLogin(@Valid @RequestBody User user) {
		
		orderHistoryService.createOrderHistoryUponLogin(user);
		
	}

}
