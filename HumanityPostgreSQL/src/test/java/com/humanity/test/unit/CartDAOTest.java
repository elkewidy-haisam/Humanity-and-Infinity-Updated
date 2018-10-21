package com.humanity.test.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
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
import com.humanity.dao.PreviewDAO;
import com.humanity.dao.UserDAO;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
public class CartDAOTest {
	
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	ComicDAO comicDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HumanityConfig humanConfig;
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createCartUponLoginTest() {
		
		User user = humanConfig.user();
		user.setUsername("username2");
		user.setPassword("password2");
		
		cartDAO.createCartUponLogin(user);
		
		assertEquals("username2", cartDAO.retrieveShoppingCart(user).getCartUser());
		
		userDAO.closeAccount(user);
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToCartTest() {
		
		Comic comic = comicDAO.findComicByChapter(0);
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		cartDAO.addComicToCart(comic, user);
		
		assertEquals(1, cartDAO.retrieveShoppingCart(user).getCartComics().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void retrieveShoppingCartTest() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		cartDAO.retrieveShoppingCart(user);
		
		//assertEquals("username", cartDAO.retrieveShoppingCart(user).getCartUser());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeAllItemsFromCartTest() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		List<Comic> comics = comicDAO.getAllComics();
		
		for (Comic comic: comics) {
			
			cartDAO.addComicToCart(comic, user);
			
		}
		
		Cart cart = cartDAO.retrieveShoppingCart(user);
		cartDAO.removeAllItemsFromCart(cart);
		
		assertEquals(0, cartDAO.retrieveShoppingCart(user).getCartComics().size());
		
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeComicFromCartTest() {
		
		User user = humanConfig.user();
		user.setUsername("username");
		user.setPassword("password");
		
		List<Comic> comics = comicDAO.getAllComics();
		
		for (Comic comic: comics) {
			
			cartDAO.addComicToCart(comic, user);
			
		}
		
		Cart cart = cartDAO.retrieveShoppingCart(user);
		Comic comic = comics.get(1);
		
		System.out.println(cart.getCartUser());
		cartDAO.removeComicFromCart(cart, comic);
		
		assertEquals(1, cartDAO.retrieveShoppingCart(user).getCartComics().size());
		
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void getAllCartsTest() {
		
		System.out.println("Cart size: " + cartDAO.getAllCarts().size());
		
	}
	
}
