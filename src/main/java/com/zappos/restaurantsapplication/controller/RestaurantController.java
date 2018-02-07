package com.zappos.restaurantsapplication.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zappos.restaurantsapplication.model.Menu;
import com.zappos.restaurantsapplication.service.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

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
	
	
	@PostMapping("/restaurants/{restaurantId}/menus/")
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable Integer rId, @RequestBody Menu newMenu) {

		Menu menu = restaurantService.addMenu(rId, newMenu);
		
		if (menu == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(menu.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
