package com.humanity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.config.HumanityConfig;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;

@Repository
public class OrderHistoryDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private HumanityConfig humanConfig;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<OrderHistory> getAllOrderHistories() {
		
		return sessionFactory.getCurrentSession().createQuery("from OrderHistory").list();
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createOrderHistoryUponLogin(User user) {
		// TODO Auto-generated method stub
		
		OrderHistory orderHistory = humanConfig.orderHistory();
		orderHistory.setUsername(user.getUsername());
		
		user.setOrderHistory(orderHistory);
		
		sessionFactory.getCurrentSession().save(user);
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public OrderHistory retrieveOrderHistoryByUser(User user) {
		
		String hql = "from OrderHistory where username =:orderHistoryUsername";
		
		OrderHistory orderHistory = (OrderHistory) sessionFactory.getCurrentSession().createQuery(hql).setParameter("orderHistoryUsername", user.getUsername()).uniqueResult();
		
		return orderHistory;
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addCartToOrderHistory(OrderHistory orderHistory, User user) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Comic> orderHistoryComics = new ArrayList<Comic>();
		
		for (Comic comic: user.getCart().getCartComics()) {
			
			orderHistoryComics.add(comic);
			
		}
		
		user.getCart().getCartComics().clear();
		
		orderHistory.setOhComics(orderHistoryComics);
		user.setOrderHistory(orderHistory);
		
		session.saveOrUpdate(user);
		
	}

}
