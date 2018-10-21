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
import com.humanity.model.User;
import com.humanity.services.CartService;
import com.humanity.services.UserService;
import com.humanity.services.ComicService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class CartServiceTest {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Autowired
	ComicService comicService;
	
	@Autowired
	UserService userService;
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createCartUponLoginTest() {
		
		User user = humanConfig.user();
		user.setUsername("username2");
		user.setPassword("password2");
		
		cartService.createCartUponLogin(user);
		
		assertEquals("username2", cartService.retrieveShoppingCart(user).getCartUser());
		
		userService.closeAccount(user);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToCartTest() {
		
		Comic comic = comicService.findComicByChapter(0);
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		cartService.addComicToCart(comic, user);
		
		assertEquals(1, cartService.retrieveShoppingCart(user).getCartComics().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void retrieveShoppingCartTest() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		cartService.retrieveShoppingCart(user);
		
		//assertEquals("username", cartDAO.retrieveShoppingCart(user).getCartUser());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeAllItemsFromCartTest() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		List<Comic> comics = comicService.getAllComics();
		
		for (Comic comic: comics) {
			
			cartService.addComicToCart(comic, user);
			
		}
		
		Cart cart = cartService.retrieveShoppingCart(user);
		cartService.removeAllItemsFromCart(cart);
		
		assertEquals(0, cartService.retrieveShoppingCart(user).getCartComics().size());
		
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeComicFromCartTest() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		List<Comic> comics = comicService.getAllComics();
		
		for (Comic comic: comics) {
			
			cartService.addComicToCart(comic, user);
			
		}
		
		Cart cart = cartService.retrieveShoppingCart(user);
		Comic comic = comics.get(1);
		
		System.out.println(cart.getCartUser());
		cartService.removeComicFromCart(cart, comic);
		
		assertEquals(1, cartService.retrieveShoppingCart(user).getCartComics().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllCartsTest() {
		
		System.out.println("Cart size: " + cartService.getAllCarts().size());
		
	}
	

}
