package com.ba.domain.booking;

import java.util.List;

public class Booking {
	
	private String id;
	
	private String description;
	
	private List<Product> products;
	
	private double totalAmount;

	public Booking(String id, String description, List<Product> products, double totalAmount) {
		super();
		this.id = id;
		this.description = description;
		this.products = products;
		this.totalAmount = totalAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
