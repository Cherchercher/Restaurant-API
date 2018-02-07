package com.zappos.restaurantsapplication.model;


import java.util.List;
import org.hibernate.validator.constraints.NotBlank;




public class Item {
    private Integer id;
	private Integer menuId;
	private Integer rId;
	private String name;
	private Integer price;
	private String vegan;

	
	public Item() {

	}

	public Item(Integer id, String name, Integer menuId, Integer rId, Integer price, String vegan) {
		super();
		this.id = id;
		this.name = name;
		this.menuId = menuId;
		this.rId = rId;
		this.price = price;
		this.vegan = vegan;
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

	public Integer getmenuId() {
		return menuId;
	}

	public void setmenurId(Integer menuId) {
		this.menuId = menuId;
	}
	
	public Integer getrId() {
		return rId;
	}
	
	public void setrId(Integer rId) {
		this.rId = rId;
	}


	public Integer getprice() {
		return price;
	}
	
	public void setprice(Integer price) {
		this.price = price;
	}

	public String getVegan() {
		return vegan;
	}
	
	public void setvegan(String vegan) {
		this.vegan = vegan;
	}



	@Override
	public String toString() {
		return String.format(
				"Item [id=%d, name=%s, menuId=%d, rId=%d, price=%d, vegan=%s]", id, name, menuId, rId, price,vegan);
	}

}