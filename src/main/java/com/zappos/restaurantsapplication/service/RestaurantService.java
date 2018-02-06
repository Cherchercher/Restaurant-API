package com.zappos.restaurantsapplication.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import com.zappos.restaurantsapplication.model.Item;
import com.zappos.restaurantsapplication.model.Menu;
import com.zappos.restaurantsapplication.model.Restaurant;

@Component
public class RestaurantService {
	private static List<Object> restaurants = new ArrayList<>();

	static {
		//Initialize Data
		//brunch items
		Item item1= new Item(1,"egg benedit", 1,1,15,"non-vegan");
		Item item2= new Item(2,"ham sandwich", 1,1,15,"non-vegan");
		Item item3= new Item(3,"olive bread", 1,1,15,"vegan");
		Item item4= new Item(4,"herbal tea", 1,1,15,"vegan");
		// brunch menus
		Menu menu1 = new Menu(1,1, "brunch", Arrays.asList(item1,item3));
		Menu menu2 = new Menu(2,2, "brunch", Arrays.asList(item2,item4));
		
		
		//dinner items
		Item item5 = new Item(5,"new-yor steak", 1,1,15,"non-vegan");
		Item item6 = new Item(6,"sizzling salmon", 1,1,15,"non-vegan");
		Item item7 = new Item(7,"red wine", 1,1,15,"vegan");
		Item item8 = new Item(8,"champaign", 1,1,15,"vegan");
		// dinner menus
		Menu menu3 = new Menu(1,1, "dinner", Arrays.asList(item5,item7));
		Menu menu4 = new Menu(2,2, "dinner", Arrays.asList(item6,item8));

		Restaurant restaurant1 = new Restaurant(1, "A_resto", 3.00 , "5 Arroya Drive, Irvine",
				Arrays.asList(1, 1, "kids-friendly",Arrays.asList(menu1,menu3))
		);
		
		Restaurant restaurant2 = new Restaurant(2, "B_resto", 4.00 , "3 Brista Drive, Brisbane",
				Arrays.asList(1, 2, "Romantic",Arrays.asList(menu2,menu4))
		);
		
		restaurants.add(restaurant1);
		restaurants.add(restaurant2);
	}

	public List<Object> retrieveAllRestaurants() {
		return restaurants;
	}

	public Restaurant retrieveRestaurant(Integer id) {
		for (Object restaurant : restaurants) {
			if (((Item) restaurant).getId().equals(id)) {
				return (Restaurant) restaurant;
			}
		}
		return null;
	}

	public List<Object> retrieveMenus(Integer id) {
		Restaurant restaurant = retrieveRestaurant(id);

		if (restaurant == null) {
			return null;
		}

		return restaurant.getMenus();
	}

	public Menu retrieveMenu(Integer rId, Integer Id) {
		Restaurant restaurant = retrieveRestaurant(rId);

		if (restaurant == null) {
			return null;
		}

		for (Object menu : restaurant.getMenus()) {
			if (((Menu) menu).getId().equals(Id)) {
				return (Menu) menu;
			}
		}
		return null;
	}
	
	public List<Object> retrieveItems(Integer rId, Integer mId) {
		Menu menu  = retrieveMenu(rId, mId);

		if (menu == null) {
			return null;
		}

		return menu.getItems();
	}
	
	public Item retrieveItem(Integer rId, Integer menuId, Integer Id) {
		Menu menu = retrieveMenu(rId, menuId);

		if (menu == null) {
			return null;
		}

		for (Object item :  menu.getItems()) {
			if (((Item) item).getId().equals(Id)) {
				return (Item) item;
			}
		}
		return null;
	}
	

	public Menu addMenu(Integer rId, Menu menu) {
		Restaurant restuarant = retrieveRestaurant(rId);

		if (restuarant == null) {
			return null;
		}

		((Restaurant) restaurants).getMenus().add(menu);
		return menu;
	}
}