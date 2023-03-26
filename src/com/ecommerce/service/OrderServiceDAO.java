package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.LongPredicate;

import com.ecommerce.model.Order;

public class OrderServiceDAO {

	static List<Order> orders = new ArrayList<>();
	static {
		orders.add(new Order(1L, "Samsung TV", 87));
		orders.add(new Order(2L, "Onida TV", 87));
		orders.add(new Order(3L, "LG TV", 87));
		orders.add(new Order(4L, "Sony TV", 87));
		orders.add(new Order(5L, "MI TV", 87));
		orders.add(new Order(6L, "Huvai TV", 87));
		orders.add(new Order(7L, "Smart TV", 87));
		orders.add(new Order(8L, "HD Smart TV", 87));
	}

	public static Order getOrder(Long checkId) {
		LongPredicate match = id -> id == checkId;
		Optional<Order> result = orders.stream().filter(order -> match.test(order.getId())).findFirst();
		if (result.isPresent())
			return result.get();
		else
			return null;
	}

	public int saveOrder(Order order) {
		orders.add(order);
		return orders.size();
	}

	public List<Order> getOrders() {
		return orders;
	}
}
