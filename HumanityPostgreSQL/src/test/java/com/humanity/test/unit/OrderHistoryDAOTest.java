package com.humanity.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.dao.CartDAO;
import com.humanity.dao.ComicDAO;
import com.humanity.dao.OrderHistoryDAO;
import com.humanity.dao.UserDAO;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class OrderHistoryDAOTest {
	
	@Autowired
	OrderHistoryDAO orderHistoryDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	ComicDAO comicDAO;
	
	@Autowired
	HumanityConfig humanConfig;
	
	
	public void getAllOrderHistories() {
		
		System.out.println("Order histories size: " + orderHistoryDAO.getAllOrderHistories().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createOrderHistoryUponLogin() {
		
		User user = humanConfig.user();
		user.setUsername("username2");
		user.setPassword("password2");
		
		userDAO.register(user);
		
		assertEquals("username2", orderHistoryDAO.retrieveOrderHistoryByUser(user).getUsername());
		
		userDAO.closeAccount(user);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void retrieveOrderHistoryByUser() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		assertEquals("username", orderHistoryDAO.retrieveOrderHistoryByUser(user).getUsername());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addCartToOrderHistory() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		List<Comic> comics = comicDAO.getAllComics();
		
		for (Comic comic : comics) {
			
			cartDAO.addComicToCart(comic, user);
			
		}
		
		Cart cart = cartDAO.retrieveShoppingCart(user);
		
		user.setCart(cart);
		
		OrderHistory orderHistory = orderHistoryDAO.retrieveOrderHistoryByUser(user);
		
		orderHistoryDAO.addCartToOrderHistory(orderHistory, user);
		
		//assertEquals(3, orderHistoryDAO.retrieveOrderHistoryByUser(user).getOhComics().size());
		
	}
	
	
	

}
