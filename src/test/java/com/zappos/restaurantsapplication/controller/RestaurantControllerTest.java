package com.zappos.restaurantsapplication.controller;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;

import org.json.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.zappos.restaurantsapplication.model.Item;
import com.zappos.restaurantsapplication.controller.RestaurantController;
import com.zappos.restaurantsapplication.filter.CORSFilter;
import com.zappos.restaurantsapplication.model.Menu;
import com.zappos.restaurantsapplication.model.Restaurant;
import com.zappos.restaurantsapplication.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class, secure = false)
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestaurantService restaurantService;
	
	@InjectMocks
    private RestaurantController restaurantController;

	Item item1= new Item(1,"egg benedit", 1,1,15,"non-vegan");
	Item item2= new Item(2,"ham sandwich", 1,1,15,"non-vegan");
	Item item3= new Item(3,"olive bread", 1,1,15,"vegan");
	Item item4= new Item(4,"herbal tea", 1,1,15,"vegan");
	//brunch menus
	Menu menu1 = new Menu(1,1, "brunch", Arrays.asList(item1,item3));
	Menu menu2 = new Menu(1,2, "brunch", Arrays.asList(item2,item4));
	//dinner items
	Item item5 = new Item(5,"new-yor steak", 1,1,15,"non-vegan");
	Item item6 = new Item(6,"sizzling salmon", 1,1,15,"non-vegan");
	Item item7 = new Item(7,"red wine", 1,1,15,"vegan");
	Item item8 = new Item(8,"champaign", 1,1,15,"vegan");
	//dinner menus
	Menu menu3 = new Menu(2,1, "dinner", Arrays.asList(item5,item7));
	Menu menu4 = new Menu(2,2, "dinner", Arrays.asList(item6,item8));

	Restaurant restaurant1 = new Restaurant(1, "A_resto", 3.00 , "5 Arroya Drive, Irvine",
		Arrays.asList(menu1,menu3)
	);
			
	Restaurant restaurant2 = new Restaurant(2, "B_resto", 4.00 , "3 Brista Drive, Brisbane",
			Arrays.asList(menu2,menu4)
	);
			
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(restaurantController)
                .addFilters(new CORSFilter())
                .build();
    }
	// ======== Tests for Restaurants
	// =========================================== Get All Restaurants ==========================================
	@Test
	public void TestRetrieveRestaurants() throws Exception {
	List<Object> restaurants = Arrays.asList(
		            restaurant1, restaurant2
		           );
		    when(restaurantService.retrieveAllRestaurants()).thenReturn(restaurants);
		    mockMvc.perform(get("/restaurants"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(2)))
		            .andExpect(jsonPath("$[0].id", is(1)))
		            .andExpect(jsonPath("$[0].name", is("A_resto")))
		            .andExpect(jsonPath("$[0].rating", is(3.00)))
		            .andExpect(jsonPath("$[0].address", is("5 Arroya Drive, Irvine")))
		            .andExpect(jsonPath("$[1].id", is(2)))
		            .andExpect(jsonPath("$[1].name", is("B_resto")))
		            .andExpect(jsonPath("$[1].rating", is(4.00)))
		            .andExpect(jsonPath("$[1].address", is("3 Brista Drive, Brisbane"))); 			
		    verify(restaurantService, times(1)).retrieveAllRestaurants();
		    verifyNoMoreInteractions(restaurantService);
	}
	
    // =========================================== Get Restaurant By ID =========================================

    @Test
    public void TestRetrieveRestaurantByID() throws Exception {
        when(restaurantService.retrieveRestaurant(1)).thenReturn(restaurant1);
        mockMvc.perform(get("/restaurants/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("A_resto")))
        			.andExpect(jsonPath("$.rating", is(3.00)))
        			.andExpect(jsonPath("$.address", is("5 Arroya Drive, Irvine")));

        verify(restaurantService, times(1)).retrieveRestaurant(1);
        verifyNoMoreInteractions(restaurantService);
    }

 // =========================================== Get All Menus of a restaurant ==========================================

 	@Test
 	public void TestRetrieveMenus() throws Exception {
 	List<Object> menus= Arrays.asList(menu1,menu3);
 		    when(restaurantService.retrieveMenus(1)).thenReturn(menus);
 		    mockMvc.perform(get("/restaurants/1/menus"))
 		            .andExpect(status().isOk())
 		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
 		            .andExpect(jsonPath("$", hasSize(2)))
 		            .andExpect(jsonPath("$[0].id", is(1)))
 		            .andExpect(jsonPath("$[0].rId", is(1)))
 		            .andExpect(jsonPath("$[0].type", is("brunch")))
 		            .andExpect(jsonPath("$[1].id", is(2)))
		            .andExpect(jsonPath("$[1].rId", is(1)))
		            .andExpect(jsonPath("$[1].type", is("dinner")));
 		    verify(restaurantService, times(1)).retrieveMenus(1);
 		    verifyNoMoreInteractions(restaurantService);
 	}
 	
 	
 	
 // =========================================== Get A Menu of a restaurant ==========================================
  	@Test
  	public void TestRetrieveMenu() throws Exception {
  		    when(restaurantService.retrieveMenu(1,1)).thenReturn(menu1);
  		    mockMvc.perform(get("/restaurants/1/menus/1"))
  		            .andExpect(status().isOk())
  		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
  		          .andExpect(jsonPath("$.id", is(1)))
                  .andExpect(jsonPath("$.rId", is(1)))
          			.andExpect(jsonPath("$.type", is("brunch")));
  		    verify(restaurantService, times(1)).retrieveMenu(1,1);
  		    verifyNoMoreInteractions(restaurantService);  
  	}
 	
 // =========================================== Get all items from a menu of a restaurant ==========================================
   	@Test
   	public void TestRetrieveItems() throws Exception {
   			List<Object> items = Arrays.asList(item1,item3);
   		    when(restaurantService.retrieveItems(1,1)).thenReturn(items);
   		    mockMvc.perform(get("/restaurants/1/menus/1/items"))
   		            .andExpect(status().isOk())
   		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
   		            .andExpect(jsonPath("$", hasSize(2)))
		            .andExpect(jsonPath("$[0].id", is(1)))
		            .andExpect(jsonPath("$[0].name", is("egg benedit")))
		            .andExpect(jsonPath("$[0].menuId", is(1)))
		            .andExpect(jsonPath("$[0].rId", is(1)))
		            .andExpect(jsonPath("$[0].price", is(15)))
		            .andExpect(jsonPath("$[0].vegan", is("non-vegan")))
		            .andExpect(jsonPath("$[1].id", is(3)))
		            .andExpect(jsonPath("$[1].name", is("olive bread")))
		            .andExpect(jsonPath("$[1].menuId", is(1)))
		            .andExpect(jsonPath("$[1].rId", is(1)))
		            .andExpect(jsonPath("$[1].price", is(15)))
		            .andExpect(jsonPath("$[1].vegan", is("vegan")));
   		    verify(restaurantService, times(1)).retrieveItems(1,1);
   		    verifyNoMoreInteractions(restaurantService);
   		    
   	}
   	
    // =========================================== Get an items from a menu of a restaurant ==========================================
   	@Test
   	public void TestRetrieveItem() throws Exception {
   		    when(restaurantService.retrieveItem(1,1,1)).thenReturn(item1);
   		    mockMvc.perform(get("/restaurants/1/menus/1/items/1"))
   		            .andExpect(status().isOk())
   		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$.id", is(1)))
		            .andExpect(jsonPath("$.name", is("egg benedit")))
		            .andExpect(jsonPath("$.menuId", is(1)))
		            .andExpect(jsonPath("$.rId", is(1)))
		            .andExpect(jsonPath("$.price", is(15)))
		            .andExpect(jsonPath("$.vegan", is("non-vegan")));
   		    verify(restaurantService, times(1)).retrieveItem(1,1,1);
   		    verifyNoMoreInteractions(restaurantService);
   		    
   	}
 	
   	// Tests for Menus

 // =========================================== Get All Menus  ==========================================

 	@Test
 	public void TestRetrieveAllMenus() throws Exception {
 	List<Object> menus= Arrays.asList(menu1,menu3);
 		    when(restaurantService.retrieveMenus()).thenReturn(menus);
 		    mockMvc.perform(get("/menus"))
 		            .andExpect(status().isOk())
 		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
 		            .andExpect(jsonPath("$", hasSize(2)))
 		            .andExpect(jsonPath("$[0].id", is(1)))
 		            .andExpect(jsonPath("$[0].rId", is(1)))
 		            .andExpect(jsonPath("$[0].type", is("brunch")))
 		            .andExpect(jsonPath("$[1].id", is(2)))
		            .andExpect(jsonPath("$[1].rId", is(1)))
		            .andExpect(jsonPath("$[1].type", is("dinner")));
 		    verify(restaurantService, times(1)).retrieveMenus();
 		    verifyNoMoreInteractions(restaurantService);
 	}
 	
 	
 	
 // =========================================== Get A Menu  ==========================================
  	@Test
  	public void TestRetrieveAMenu() throws Exception {
  		    when(restaurantService.retrieveMenu(1)).thenReturn(menu1);
  		    mockMvc.perform(get("/menus/1"))
  		            .andExpect(status().isOk())
  		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
  		          .andExpect(jsonPath("$.id", is(1)))
                  .andExpect(jsonPath("$.rId", is(1)))
          			.andExpect(jsonPath("$.type", is("brunch")));
  		    verify(restaurantService, times(1)).retrieveMenu(1);
  		    verifyNoMoreInteractions(restaurantService);  
  	}
 	
 // =========================================== Get all items==========================================
   	@Test
   	public void TestRetrieveAllItems() throws Exception {
   			List<Object> items = Arrays.asList(item1,item3);
   		    when(restaurantService.retrieveItems(1)).thenReturn(items);
   		    mockMvc.perform(get("/menus/1/items"))
   		            .andExpect(status().isOk())
   		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
   		            .andExpect(jsonPath("$", hasSize(2)))
		            .andExpect(jsonPath("$[0].id", is(1)))
		            .andExpect(jsonPath("$[0].name", is("egg benedit")))
		            .andExpect(jsonPath("$[0].menuId", is(1)))
		            .andExpect(jsonPath("$[0].rId", is(1)))
		            .andExpect(jsonPath("$[0].price", is(15)))
		            .andExpect(jsonPath("$[0].vegan", is("non-vegan")))
		            .andExpect(jsonPath("$[1].id", is(3)))
		            .andExpect(jsonPath("$[1].name", is("olive bread")))
		            .andExpect(jsonPath("$[1].menuId", is(1)))
		            .andExpect(jsonPath("$[1].rId", is(1)))
		            .andExpect(jsonPath("$[1].price", is(15)))
		            .andExpect(jsonPath("$[1].vegan", is("vegan")));
   		    verify(restaurantService, times(1)).retrieveItems(1);
   		    verifyNoMoreInteractions(restaurantService);
   		    
   	}
   	
    // =========================================== Get an item ==========================================
   	@Test
   	public void TestRetrieveAMenuItem() throws Exception {
   		    when(restaurantService.retrieveItem(1,1)).thenReturn(item1);
   		    mockMvc.perform(get("/menus/1/items/1"))
   		            .andExpect(status().isOk())
   		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$.id", is(1)))
		            .andExpect(jsonPath("$.name", is("egg benedit")))
		            .andExpect(jsonPath("$.menuId", is(1)))
		            .andExpect(jsonPath("$.rId", is(1)))
		            .andExpect(jsonPath("$.price", is(15)))
		            .andExpect(jsonPath("$.vegan", is("non-vegan")));
   		    verify(restaurantService, times(1)).retrieveItem(1,1);
   		    verifyNoMoreInteractions(restaurantService);
   		    
   	}
 	
 // =========================================== Get all items==========================================
   	@Test
   	public void TestRetrieveItemsThemselves() throws Exception {
   			List<Object> items = Arrays.asList(item1,item3);
   		    when(restaurantService.retrieveItems()).thenReturn(items);
   		    mockMvc.perform(get("/items"))
   		            .andExpect(status().isOk())
   		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
   		            .andExpect(jsonPath("$", hasSize(2)))
		            .andExpect(jsonPath("$[0].id", is(1)))
		            .andExpect(jsonPath("$[0].name", is("egg benedit")))
		            .andExpect(jsonPath("$[0].menuId", is(1)))
		            .andExpect(jsonPath("$[0].rId", is(1)))
		            .andExpect(jsonPath("$[0].price", is(15)))
		            .andExpect(jsonPath("$[0].vegan", is("non-vegan")))
		            .andExpect(jsonPath("$[1].id", is(3)))
		            .andExpect(jsonPath("$[1].name", is("olive bread")))
		            .andExpect(jsonPath("$[1].menuId", is(1)))
		            .andExpect(jsonPath("$[1].rId", is(1)))
		            .andExpect(jsonPath("$[1].price", is(15)))
		            .andExpect(jsonPath("$[1].vegan", is("vegan")));
   		    verify(restaurantService, times(1)).retrieveItems();
   		    verifyNoMoreInteractions(restaurantService);
   		    
   	}
   	
    // =========================================== Get an item ==========================================
   	@Test
   	public void TestRetrieveItemItself() throws Exception {
   		    when(restaurantService.retrieveItem(1)).thenReturn(item1);
   		    mockMvc.perform(get("/items/1"))
   		            .andExpect(status().isOk())
   		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$.id", is(1)))
		            .andExpect(jsonPath("$.name", is("egg benedit")))
		            .andExpect(jsonPath("$.menuId", is(1)))
		            .andExpect(jsonPath("$.rId", is(1)))
		            .andExpect(jsonPath("$.price", is(15)))
		            .andExpect(jsonPath("$.vegan", is("non-vegan")));
   		    verify(restaurantService, times(1)).retrieveItem(1);
   		    verifyNoMoreInteractions(restaurantService);
   		    
   	}
   	
   	// ================Deletion tests====================
    
	@Test
   	public void TestDeleteItem() throws Exception {
   	 	RestaurantService mock = org.mockito.Mockito.mock(RestaurantService.class);
   	 	mock.deleteItem(1);
   	 	verify(mock, times(1)).deleteItem(1);;
   	}
	
	@Test
   	public void TestDeleteRestaurant() throws Exception {
   	 	RestaurantService mock = org.mockito.Mockito.mock(RestaurantService.class);
   	 	mock.deleteRestaurant(1);
   	 	verify(mock, times(1)).deleteRestaurant(1);;
   	}
	
	@Test
   	public void TestDeleteMenu() throws Exception {
   	 	RestaurantService mock = org.mockito.Mockito.mock(RestaurantService.class);
   	 	mock.deleteMenu(1);
   	 	verify(mock, times(1)).deleteMenu(1);;
   	}
   	
   	
 
}
