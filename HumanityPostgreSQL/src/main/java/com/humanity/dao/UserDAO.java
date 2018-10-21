package com.humanity.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.model.Cart;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;

@Repository
public class UserDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private HumanityConfig humanConfig;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
		String hql = "from User WHERE username =:user AND password =:pass";
		User user = (User) sessionFactory.getCurrentSession().createQuery(hql).setParameter("user", username).setParameter("pass", password).uniqueResult();
		
		if (user.getUsername() == username && user.getPassword() == password) {
			
			System.out.println("Login successful.");
			
		} else {
			
			System.out.println("Login unsuccessful.");
			
		}
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void closeAccount(User user) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().delete(user);
		
		System.out.println("User deleted.");
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void register(User user) {
		
		Cart cart = humanConfig.cart();
		cart.setCartUser(user.getUsername());
		
		OrderHistory orderHistory = humanConfig.orderHistory();
		orderHistory.setUsername(user.getUsername());
		
		user.setCart(cart);
		user.setOrderHistory(orderHistory);
		
		sessionFactory.getCurrentSession().save(user);
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<User> getAllUsers() {
		
		return sessionFactory.getCurrentSession().createQuery("from User").list();
		
	}
	

}
