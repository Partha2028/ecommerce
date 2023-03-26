package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Order;

public interface OrderService {

	public List<Order> getAllOrders();

	public Order getOrderDetails(Long id);

	public int placeOrder(Order orders);

}
