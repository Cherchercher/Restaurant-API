package com.zappos.restaurantsapplication.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.zappos.restaurantsapplication.model.Item;
import com.zappos.restaurantsapplication.controller.RestaurantController;
import com.zappos.restaurantsapplication.model.Menu;
import com.zappos.restaurantsapplication.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class, secure = false)
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestaurantService restaurantService;

	Item item1= new Item(1,"egg benedit", 1,1,15,"non-vegan");
	Item item2= new Item(2,"ham sandwich", 1,1,15,"non-vegan");
	Item item3= new Item(3,"olive bread", 1,1,15,"vegan");
	Item item4= new Item(4,"herbal tea", 1,1,15,"vegan");
	// brunch menus
	Menu Mockmenu  = new Menu(1,1, "brunch", Arrays.asList(item1,item3));
	
	
	String exampleMenuJson = "{\"type\":\"brunch\",\"items\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	@Test
	public void retrieveMenuForRestaurant() throws Exception {

		Mockito.when(
				restaurantService.retrieveMenu(Mockito.anyInt(),
						Mockito.anyInt())).thenReturn(Mockmenu);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/restaurants/restaurant1/menus/menu1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		
		String expected = "{id:1,name:eggbenedit,menuId:1,rId:1,price:15,vegan:non-vegan}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	
}
