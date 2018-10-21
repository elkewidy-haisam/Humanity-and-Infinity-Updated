package com.humanity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.User;

@Repository
public class CartDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	HumanityConfig humanConfig;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createCartUponLogin(User user) {
		// TODO Auto-generated method stub
		
		Cart cart = humanConfig.cart();
		cart.setCartUser(user.getUsername());
		
		user.setCart(cart);
		
		sessionFactory.getCurrentSession().save(user);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addComicToCart(Comic comic, User user) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		Cart cart = retrieveShoppingCart(user);
		List<Comic> comics = new ArrayList<Comic>();
		comics.add(comic);
		
		cart.setCartComics(comics);
		user.setCart(cart);
		
		session.saveOrUpdate(user);
		
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Cart retrieveShoppingCart(User user) {
		// TODO Auto-generated method stub
		
		return (Cart) sessionFactory.getCurrentSession().get(Cart.class, user.getUsername());
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeAllItemsFromCart(Cart cart) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Comic> cartComics = cart.getCartComics();
		
		cartComics.clear();
		
		cart.setCartComics(cartComics);
		
		session.saveOrUpdate(cart);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void removeComicFromCart(Cart cart, Comic comic) {
		// TODO Auto-generated method stub
		
	
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Comic> cartComics = cart.getCartComics();
		
		cartComics.remove(comic);
		
		cart.setCartComics(cartComics);
		
		session.saveOrUpdate(cart);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Cart> getAllCarts() {
		
		return sessionFactory.getCurrentSession().createQuery("from Cart").list();
		
	}
	
	
	
}
