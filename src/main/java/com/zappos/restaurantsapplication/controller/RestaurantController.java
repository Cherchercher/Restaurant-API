package com.zappos.restaurantsapplication.controller;

import java.util.List;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.zappos.restaurantsapplication.model.Item;
import com.zappos.restaurantsapplication.model.Menu;
import com.zappos.restaurantsapplication.model.Restaurant;
import com.zappos.restaurantsapplication.service.RestaurantService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;

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
		
	// ============================ Delete Methods  ======================
	@DeleteMapping("/items/{itemId}")
	public Object deleteItem(@PathVariable("itemId") Integer Id) {
		 List<Object> items = retrieveItems();
		 Item item  = RestaurantService.findItemById(Id);
		 ((RestaurantService) items).deleteItem(Id);
		 return item;
	}
	
	@DeleteMapping("/menus/{menuId}")
	public Object deleteMenu(@PathVariable("menuId") Integer Id) {
		 List<Object> menus = retrieveMenus();
		 Menu menu  = RestaurantService.findMenuById(Id);
		 ((RestaurantService) menus).deleteMenu(Id);
		 return menu;
	}
	
	@DeleteMapping("/restaurants/{restaurantId}")
	public Object deleteRestaurant(@PathVariable("restaurantId") Integer Id) {
		 List<Object> restaurants = retrieveRestaurants();
		 Restaurant restaurant  = RestaurantService.findRestaurantById(Id);
		 ((RestaurantService) restaurants).deleteRestaurant(Id);
		 return restaurant;
	}
	
	
	// ============================ Post Methods for Restaurants ======================
		@PostMapping("/restaurants")
		public ResponseEntity<Void> addRestaurant(@RequestBody Restaurant newRestaurant) {
			Restaurant restaurant = restaurantService.addRestaurant(newRestaurant);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(restaurant.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		
		@PostMapping("/restaurants/{restaurantId}/menus")
		public ResponseEntity<Void> addMenu(@PathVariable("restaurantId") Integer rId,@RequestBody Menu newMenu) {
			Menu menu = restaurantService.addMenu(rId, newMenu);

			if (menu == null)
				return ResponseEntity.noContent().build();

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newMenu.getId()).toUri();

			return ResponseEntity.created(location).build();
		}
		
		@PostMapping("/restaurants/{restaurantId}/menus/{menuId}/items")
		public ResponseEntity<Void> addItem(@PathVariable("restaurantId") Integer rId,
				@PathVariable("menuId") Integer menuId, @RequestBody Item newItem) {
			Item item = restaurantService.addItem(rId, menuId, newItem);

			if (item == null)
				return ResponseEntity.noContent().build();

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newItem.getId()).toUri();
			return ResponseEntity.created(location).build();
			
		}
		
		// ============================ Post Methods for Menus ======================
		@PostMapping("/menus")
		public ResponseEntity<Void> addMenu(@RequestBody Menu newMenu) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
			"/{id}").buildAndExpand(newMenu.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
				
				
				
		@PostMapping("/menus/{menuId}/items")
		public ResponseEntity<Void> addItem(@PathVariable("menuId") Integer menuId, @RequestBody Item newItem) {
			Item item = restaurantService.addItem( menuId, newItem);
			if (item == null)
					return ResponseEntity.noContent().build();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
							"/{id}").buildAndExpand(newItem.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		// ============================ Post Methods for Menus ======================
		@PostMapping("/items")
		public ResponseEntity<Void> addItem(@RequestBody Item newItem) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
			"/{id}").buildAndExpand(newItem.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
}
