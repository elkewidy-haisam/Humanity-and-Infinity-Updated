package com.humanity.test.mockControllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.web.WebAppConfiguration;

import com.humanity.config.HumanityConfig;
import com.humanity.controllers.CartController;
import com.humanity.model.Cart;
import com.humanity.model.Comic;
import com.humanity.model.User;
import com.humanity.services.CartService;

import org.mockito.runners.*;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=HumanityConfig.class)
@WebAppConfiguration
public class CartControllerTest {
	
	@InjectMocks
	CartController cartController;
	
	@Mock
	CartService cartServiceMock;
	
	User user = new User();
	
	Cart cart = new Cart();
	
	Comic comic1, comic2, comic3, comic4, comic5;
	
	
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
		comics.add(comic1);
		comics.add(comic2);
		comics.add(comic3);
		comics.add(comic4);
		comics.add(comic5);
		
		cart.setCartComics(comics);
		
		user.setCart(cart);
		
	}
	
	@Test
	public void createCartUponLoginTest() {
		
		cartController.createCartUponLogin(user);
		verify(cartServiceMock).createCartUponLogin(user);
		verifyNoMoreInteractions(cartServiceMock);
		
		
	}
	
	@Test
	public void addComicToCartTest() {
		
		Comic comic6 = new Comic();
		comic6.setChapter(6);
		comic6.setTitle("Mock Title 6");
		comic6.setSynopsis("Mock Synopsis 6");
		comic6.setPrice(123.45);
		
		cartController.addComicToCart(comic6, user);
		
		verify(cartServiceMock).addComicToCart(comic6, user);
		verifyNoMoreInteractions(cartServiceMock);
		
		
	}
	
	@Test
	public void retrieveShoppingCartTest() {
		
		when(cartController.retrieveShoppingCart(user)).thenReturn(user.getCart());
		
		assertEquals(5, cartController.retrieveShoppingCart(user).getCartComics().size());
		
		verify(cartServiceMock, times(1)).retrieveShoppingCart(user);
		verifyNoMoreInteractions(cartServiceMock);
		
	}
	
	@Test
	public void removeAllItemsFromCart() {
		
		cartController.removeAllItemsFromCart(cart);
		
		assertEquals(0, cart.getCartComics().size());
		
		verify(cartServiceMock).removeAllItemsFromCart(cart);
		verifyNoMoreInteractions(cartServiceMock);
		
	}
	
	@Test
	public void removeComicFromCart() {
		
	    cartController.removeComicFromCart(comic1, cart);
		
		assertEquals(4, cart.getCartComics().size());
		assertEquals(false, cart.getCartComics().contains(comic1));
		
		verify(cartServiceMock).removeComicFromCart(cart, comic1);
		verifyNoMoreInteractions(cartServiceMock);
		
		
	}
	
	
	
	
	
	

}
