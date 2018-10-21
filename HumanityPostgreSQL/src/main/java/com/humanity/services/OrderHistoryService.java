package com.humanity.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.humanity.dao.CartDAO;
import com.humanity.dao.OrderHistoryDAO;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;

@Service
public class OrderHistoryService {
	
	private static final Logger log = Logger.getLogger(OrderHistoryService.class);
	private OrderHistoryDAO orderHistoryDAO;
	
	@Autowired
	public void setOrderHistoryDAO(OrderHistoryDAO orderHistoryDAO) {
		
		this.orderHistoryDAO = orderHistoryDAO;
		
	}
	
	public OrderHistory retrieveOrderHistoryByUser(User user) {
		
		return orderHistoryDAO.retrieveOrderHistoryByUser(user);
		
	}
	
	public void addCartToOrderHistory(OrderHistory orderHistory, User user) {
		
		orderHistoryDAO.addCartToOrderHistory(orderHistory, user);
		
	}

	public List<OrderHistory> getAllOrderHistories() {
		// TODO Auto-generated method stub
		return orderHistoryDAO.getAllOrderHistories();
	}
	
	
	public void createOrderHistoryUponLogin(User user) {
		// TODO Auto-generated method stub
		
		orderHistoryDAO.createOrderHistoryUponLogin(user);
		
	}

}
