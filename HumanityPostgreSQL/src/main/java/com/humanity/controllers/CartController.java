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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humanity.dao.CartDAO;
import com.humanity.dao.ComicDAO;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.User;
import com.humanity.services.CartService;

@RestController
@RequestMapping("/humanity")
public class CartController {
	
	private static final Logger log = Logger.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;
	
	public void setCartService(CartService cartService) {
		
		this.cartService = cartService;
		System.out.println("CartService: " + this.cartService);
		
	}
	
	@RequestMapping(value="/cart/createCart", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createCartUponLogin(@Valid @RequestBody User user) {
		
		cartService.createCartUponLogin(user);
		
	}
	
	@RequestMapping(value="/cart/addComicToCart", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToCart(@Valid @RequestBody Comic comic, User user) {
		
		cartService.addComicToCart(comic, user);
		
	}
	
	@RequestMapping(value="/cart/getShoppingCart", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Cart retrieveShoppingCart(@Valid @RequestBody User user) {
		
		return cartService.retrieveShoppingCart(user);
		
	}
	
	@RequestMapping(value="/cart/removeAllFromCart", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeAllItemsFromCart(@Valid @RequestBody Cart cart) {
		
		cartService.removeAllItemsFromCart(cart);
		
	}
	
	@RequestMapping(value="/cart/removeFromCart", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeComicFromCart(@Valid @RequestBody Comic comic, Cart cart) {
		
		cartService.removeComicFromCart(cart, comic);
		
	}
	

}
