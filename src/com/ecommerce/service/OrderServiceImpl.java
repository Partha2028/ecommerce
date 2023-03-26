package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Order;

public class OrderServiceImpl implements OrderService {

	OrderService service;

	OrderServiceDAO dao = new OrderServiceDAO();

	@Override
	public List<Order> getAllOrders() {
		return dao.getOrders();

	}

	@Override
	public Order getOrderDetails(Long id) {
		return dao.getOrder(id);
	}

	@Override
	public int placeOrder(Order order) {
		return dao.saveOrder(order);
	}

}
