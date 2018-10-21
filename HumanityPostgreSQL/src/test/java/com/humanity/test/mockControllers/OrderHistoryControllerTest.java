package com.humanity.test.mockControllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.humanity.config.HumanityConfig;
import com.humanity.controllers.OrderHistoryController;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.OrderHistory;
import com.humanity.model.User;
import com.humanity.services.OrderHistoryService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
@WebAppConfiguration
public class OrderHistoryControllerTest {
	
	@InjectMocks
	OrderHistoryController orderHistoryController;
	
	@Mock
	OrderHistoryService orderHistoryServiceMock;
	
	User user = new User();
	
	Cart cart = new Cart();
	
	Comic comic1, comic2, comic3, comic4, comic5;
	
	List<Comic> comics = new ArrayList<Comic>();
	
	OrderHistory orderHistory = new OrderHistory();
	
	@Before
	public void setup() {
		
		user.setUsername("username");
		user.setPassword("password");
		
		comic1 = new Comic();
		comic1.setChapter(1);
		comic1.setPrice(123.45);
		comic1.setSynopsis("Mock Synopsis 1");
		comic1.setTitle("Mock Title 1");
		
		comic2 = new Comic();
		comic2.setChapter(2);
		comic2.setPrice(123.45);
		comic2.setSynopsis("Mock Synopsis 2");
		comic2.setTitle("Mock Title 2");
		
		comic3 = new Comic();
		comic3.setChapter(3);
		comic3.setPrice(123.45);
		comic3.setSynopsis("Mock Synopsis 3");
		comic3.setTitle("Mock Title 3");
		
		comic4 = new Comic();
		comic4.setChapter(4);
		comic4.setPrice(123.45);
		comic4.setSynopsis("Mock Synopsis 4");
		comic4.setTitle("Mock Title 4");
		
		comic5 = new Comic();
		comic5.setChapter(5);
		comic5.setPrice(123.45);
		comic5.setSynopsis("Mock Synopsis 5");
		comic5.setTitle("Mock Title 5");
		
		List<Comic> comics = new ArrayList<Comic>();
		List<Comic> ohComics = new ArrayList<Comic>();
		
		comics.add(comic1);
		comics.add(comic2);
		comics.add(comic3);
		comics.add(comic4);
		comics.add(comic5);
		
		cart.setCartComics(comics);
		
		user.setCart(cart);
		
		orderHistory.setOhComics(ohComics);
		user.setOrderHistory(orderHistory);
		
		
		
	}
	
	
	@Test
	public void retrieveOrderHistoryByUserTest() {
		
		when(orderHistoryController.retrieveOrderHistoryByUser(user)).thenReturn(user.getOrderHistory());
		
		orderHistoryController.retrieveOrderHistoryByUser(user);
		
		assertEquals(0, user.getOrderHistory()
				.getOhComics()
					.size());
		
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
		
		verify(orderHistoryServiceMock).retrieveOrderHistoryByUser(user);
		verifyNoMoreInteractions(orderHistoryServiceMock);
		
		
	}
	
	@Test
	public void addCartToOrderHistoryTest() {
		
		orderHistoryController.addCartToOrderHistory(orderHistory, user);
		verify(orderHistoryServiceMock).addCartToOrderHistory(orderHistory, user);
		verifyNoMoreInteractions(orderHistoryServiceMock);
		
		
	}
	
	@Test
	public void createOrderHistoryUponLoginTest() {
		
		orderHistoryController.createOrderHistoryUponLogin(user);
		verify(orderHistoryServiceMock).createOrderHistoryUponLogin(user);
		verifyNoMoreInteractions(orderHistoryServiceMock);
		
	}
	

}
