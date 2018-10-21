package com.humanity.test.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;
import com.humanity.services.CartService;
import com.humanity.services.ComicService;
import com.humanity.services.OrderHistoryService;
import com.humanity.services.UserService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class OrderHistoryServiceTest {
	
	@Autowired
	OrderHistoryService orderHistoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	HumanityConfig humanConfig;
	
	
	public void getAllOrderHistories() {
		
		System.out.println("Order histories size: " + orderHistoryService.getAllOrderHistories().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createOrderHistoryUponLogin() {
		
		User user = humanConfig.user();
		user.setUsername("username2");
		user.setPassword("password2");
		
		userService.register(user);
		
		assertEquals("username2", orderHistoryService.retrieveOrderHistoryByUser(user).getUsername());
		
		userService.closeAccount(user);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void retrieveOrderHistoryByUser() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		assertEquals("username", orderHistoryService.retrieveOrderHistoryByUser(user).getUsername());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addCartToOrderHistory() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		List<Comic> comics = comicService.getAllComics();
		
		for (Comic comic : comics) {
			
			cartService.addComicToCart(comic, user);
			
		}
		
		Cart cart = cartService.retrieveShoppingCart(user);
		
		user.setCart(cart);
		
		OrderHistory orderHistory = orderHistoryService.retrieveOrderHistoryByUser(user);
		
		orderHistoryService.addCartToOrderHistory(orderHistory, user);
		
		//assertEquals(3, orderHistoryDAO.retrieveOrderHistoryByUser(user).getOhComics().size());
		
	}

}
