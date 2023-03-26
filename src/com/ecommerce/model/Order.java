package com.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.LongPredicate;

public class Order implements Serializable {

	public Order(Long id, String productName, int quantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472597255640108523L;

	Long id;

	String productName;

	int quantity;

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String getProductName() {
		return productName;
	}

	private void setProductName(String productName) {
		this.productName = productName;
	}

	private int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", productName=" + productName + ", quantity=" + quantity + "]";
	}


}
