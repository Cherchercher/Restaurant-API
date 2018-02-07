package com.zappos.restaurantsapplication.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
public class Restaurant {
	@NotBlank
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	 @NotBlank
	 private String name;
	 private Double rating;
	 @NotBlank
	 private String address;
	 private List<Object> menus;
	 
	 public Restaurant() {

	 }

	 public Restaurant(int id, String name, double rating, String address, List<Object> list) {
			super();
			this.id = id;
			this.name = name;
			this.rating = rating;
			this.address = address;
			this.menus = list;
		}
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Double getRating() {
	        return rating;
	    }

	    public void setRating(Double rating) {
	        this.rating = rating;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }
	    
	    
	    public List<Object> getMenus() {
			return menus;
		}
	   
	    
	    @Override
		public String toString() {
			return String.format(
					"Restaurant [id=%d, name=%s, rating=%d, address=%s, menus=%s]", 
					id, name, rating, address, menus);
		}
		
}