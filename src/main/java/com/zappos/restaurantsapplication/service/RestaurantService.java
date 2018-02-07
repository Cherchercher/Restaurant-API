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
	private static List<Object> items = new ArrayList<>();
	private static List<Object> menus = new ArrayList<>();

	static {
		//Initialize Data
		//brunch items
		Item item1= new Item(1,"egg benedit", 1,1,15,"non-vegan");
		Item item2= new Item(2,"ham sandwich", 1,1,15,"non-vegan");
		Item item3= new Item(3,"olive bread", 1,1,15,"vegan");
		Item item4= new Item(4,"herbal tea", 1,1,15,"vegan");
		// brunch menus
		Menu menu1 = new Menu(1,1, "brunch", Arrays.asList(item1,item3));
		Menu menu2 = new Menu(1,2, "brunch", Arrays.asList(item2,item4));
		//dinner items
		Item item5 = new Item(5,"new-yor steak", 1,1,15,"non-vegan");
		Item item6 = new Item(6,"sizzling salmon", 1,1,15,"non-vegan");
		Item item7 = new Item(7,"red wine", 1,1,15,"vegan");
		Item item8 = new Item(8,"champaign", 1,1,15,"vegan");
		// dinner menus
		Menu menu3 = new Menu(2,1, "dinner", Arrays.asList(item5,item7));
		Menu menu4 = new Menu(2,2, "dinner", Arrays.asList(item6,item8));
		Restaurant restaurant1 = new Restaurant(1, "A_resto", 3.00 , "5 Arroya Drive, Irvine",
				Arrays.asList(menu1,menu3)
		);
		Restaurant restaurant2 = new Restaurant(2, "B_resto", 4.00 , "3 Brista Drive, Brisbane",
				Arrays.asList(menu2,menu4)
		);
		restaurants.add(restaurant1);
		restaurants.add(restaurant2);
		items.add(item1); items.add(item2); items.add(item3); items.add(item4); items.add(item5); items.add(item6); items.add(item7);items.add(item8);
		menus.add(menu1); menus.add(menu2); menus.add(menu3); menus.add(menu4);
	}

	//================Get Methods for Restaurants===========================
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
	
	
	//================Get Methods for Menus===========================
	public List<Object> retrieveMenus() {
		return menus;
	}

	public Menu retrieveMenu(Integer Id) {
		List<Object> menus = retrieveMenus();

		for (Object menu : menus) {
			if (((Menu) menu).getId().equals(Id)) {
				return (Menu) menu;
			}
		}
		return null;
	}
	
	public List<Object> retrieveItems(Integer mId) {
		Menu menu  = retrieveMenu(mId);

		if (menu == null) {
			return null;
		}

		return menu.getItems();
	}
	
	public Item retrieveItem(Integer menuId, Integer Id) {
		List<Object> items = retrieveItems(menuId);

		if (items == null) {
			return null;
		}

		for (Object item :  items) {
			if (((Item) item).getId().equals(Id)) {
				return (Item) item;
			}
		}
		return null;
	}
	
	//================Get Methods for Items===========================
		public List<Object> retrieveItems() {
			return items;
		}

		public Item retrieveItem(Integer Id) {
			List<Object> items = retrieveItems();

			for (Object item : items) {
				if (((Item) item).getId().equals(Id)) {
					return (Item) item;
				}
			}
			return null;
		}
		

	 public static Restaurant findRestaurantById(int id) {
	        for (Object restaurant : restaurants){
	            if (((Restaurant) restaurant).getId() == id){
	                return (Restaurant) restaurant;
	            }
	        }
	        return null;
	  }
	 
	 public static Menu findMenuById(int id) {
	        for (Object menu : menus){
	            if (((Menu) menu).getId() == id){
	                return (Menu) menu;
	            }
	        }
	        return null;
	  }
	 
	 public static Item findItemById(int id) {
	        for (Object item : items){
	            if (((Item) item).getId() == id){
	                return (Item) item;
	            }
	        }
	        return null;
	  }
	 
	 
	 public void addRestaurant(Restaurant restaurant) {
	     restaurants.add(restaurant);
	 }
	 
	 public void addMenu(Menu menu) {
	     menus.add(menu);
	 }
	 
	 public void addItem(Menu item) {
	     menus.add(item);
	 }
	   
	 public void deleteRestaurant(int id) {
	     Restaurant restaurant = findRestaurantById(id);
	     restaurants.remove(restaurant);
	 }
	 
	 public void deleteItem(int id) {
	     Item item = findItemById(id);
	     items.remove(item);
	   
	 }
	 
	 public void deleteMenu(int id) {
	     Menu menu = findMenuById(id);
	     menus.remove(menu);
	 }
	 
	 public Menu addMenu(Integer rId, Menu menu) {
			Restaurant restaurant = retrieveRestaurant(rId);
			if (restaurant == null) {
				return null;
			} 
			((Restaurant) restaurant).getMenus().add(menu);
			return menu;
	 }
	 
	 public Item addItem(Integer rId, Integer menuId, Item item) {
			Menu menu = retrieveMenu(rId,menuId);
			if (menu == null) {
				return null;
			}
			((Menu) menu).getItems().add(item);
			return item;
	 }
	 
	 
	 
	 

}