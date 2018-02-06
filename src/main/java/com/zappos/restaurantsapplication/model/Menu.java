package com.zappos.restaurantsapplication.model;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class Menu {
	private Integer id;
	private Integer rId;
	private String type;
	private List<Object> items;


	public Menu() {

	}


	public Menu(Integer id, Integer rId, String type, List<Object> items) {
		super();
		this.id = id;
		this.rId = rId;
		this.type = type;
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getrId() {
		return rId;
	}

	public String getType() {
		return type;
	}

	public List<Object> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return String.format(
				"Menu [id=%d, riD=%d, type=%s, items=%s]", id, rId, type, items);
	}

}