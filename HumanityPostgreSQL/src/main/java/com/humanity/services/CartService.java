package com.humanity.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanity.dao.CartDAO;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.User;

@Service
public class CartService {
	
	private static final Logger log = Logger.getLogger(CartService.class);
	
	@Autowired
	CartDAO cartDAO;
	
	
	public void setCartDAO(CartDAO cartDAO) {
		
		this.cartDAO = cartDAO;
		
	}
	
	public List<Cart> getAllCarts() {
		
		List<Cart> cart = cartDAO.getAllCarts();
		return cart;
		
	}
	
	public void createCartUponLogin(User user) {
		
		cartDAO.createCartUponLogin(user);
		
	}
	
	public void addComicToCart(Comic comic, User user) {
		
		cartDAO.addComicToCart(comic, user);
		
	}
	
	public Cart retrieveShoppingCart(User user) {
		
		return cartDAO.retrieveShoppingCart(user);
		
	}
	
	public void removeAllItemsFromCart(Cart cart) {
		
		cartDAO.removeAllItemsFromCart(cart);
		
	}
	
	public void removeComicFromCart(Cart cart, Comic comic) {
		
		cartDAO.removeComicFromCart(cart, comic);
		
	}
	

}
