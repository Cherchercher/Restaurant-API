package com.zappos.restaurantsapplication.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zappos.restaurantsapplication.model.Item;
import com.zappos.restaurantsapplication.model.Menu;
import com.zappos.restaurantsapplication.model.Restaurant;
import com.zappos.restaurantsapplication.service.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	// ============================ Get Methods for Restaurants ======================
	@GetMapping("/restaurants")
	public List<Object> retrieveRestaurants() {
		return restaurantService.retrieveAllRestaurants();
	}
	
	@GetMapping("/restaurants/{restaurantId}")
	public Object retrieveRestaurant(@PathVariable("restaurantId") Integer rId) {
		return restaurantService.retrieveRestaurant(rId);
	}
	
	
	@GetMapping("/restaurants/{restaurantId}/menus")
	public List<Object> retrieveMenusForRestaurant(@PathVariable("restaurantId") Integer rId) {
		return restaurantService.retrieveMenus(rId);
	}
	
	@GetMapping("/restaurants/{restaurantId}/menus/{menuId}")
	public Object retrieveMenuForRestaurant(@PathVariable("restaurantId") Integer rId,
			@PathVariable("menuId") Integer menuId) {
		return restaurantService.retrieveMenu(rId, menuId);
	}
	
	@GetMapping("/restaurants/{restaurantId}/menus/{menuId}/items")
	public List<Object> retrieveItems(@PathVariable("restaurantId") Integer rId, @PathVariable("menuId") Integer mId) {
		return restaurantService.retrieveItems(rId, mId);
	}
	
	@GetMapping("/restaurants/{restaurantId}/menus/{menuId}/items/{itemId}")
	public Object retrieveItem(@PathVariable("restaurantId") Integer rId,
			@PathVariable("menuId") Integer menuId, @PathVariable("itemId") Integer Id) {
		return restaurantService.retrieveItem(rId, menuId, Id);
	}
	
	
	// ============================ Get Methods for Menus ======================
	
	@GetMapping("/menus")
	public List<Object> retrieveMenus() {
		return restaurantService.retrieveMenus();
	}
	
	@GetMapping("/menus/{menuId}")
	public Object retrieveMenu(@PathVariable("menuId") Integer mId) {
		return restaurantService.retrieveMenu(mId);
	}
	
	
	@GetMapping("/menus/{menuId}/items")
	public List<Object> retrieveItems(@PathVariable("menuId") Integer menuId) {
		return restaurantService.retrieveItems(menuId);
	}
	
	@GetMapping("/menus/{menuId}/items/{itemId}")
	public Object retrieveItem(@PathVariable("menuId") Integer menuId,
			@PathVariable("itemId") Integer Id) {
		return restaurantService.retrieveItem(menuId, Id);
	}
	
	// ============================ Get Methods for Items ======================
	
	@GetMapping("/items")
	public List<Object> retrieveItems() {
		return restaurantService.retrieveItems();
	}
		
	@GetMapping("/items/{itemId}")
	public Object retrieveItem(@PathVariable("itemId") Integer Id) {
		return restaurantService.retrieveItem(Id);
	}
		
	
	// ============================ Delete Methods ======================
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") int id){
//        Restaurant restaurant = RestaurantService.findRestaurantById(id);
//
//        if (restaurant == null){
//              return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
//
//        restaurantService.deleteRestaurant(id);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
//	
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteMenu(@PathVariable("id") int id){
//        Menu menu = RestaurantService.findMenuById(id);
//
//        if (menu == null){
//              return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
//
//        restaurantService.deleteMenu(id);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
//	
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteItem(@PathVariable("id") int id){
//        Item item = RestaurantService.findItemById(id);
//
//        if (item == null){
//              return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
//
//        restaurantService.deleteItem(id);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }

}
