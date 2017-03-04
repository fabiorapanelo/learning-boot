package com.fabiorapanelo;

public class Product {

	public Product(Long id, String name) {
		this.name = name;
		this.id = id;
	}

	private String name;
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}