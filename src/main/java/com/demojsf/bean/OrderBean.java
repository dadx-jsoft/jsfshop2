package com.demojsf.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.demojsf.pojo.Order;
import com.demojsf.pojo.OrderItem;
import com.demojsf.pojo.Product;
import com.demojsf.service.OrderItemService;
import com.demojsf.service.OrderService;

@Named
@RequestScoped
public class OrderBean {

	private Order order = new Order();
	private List<OrderItem> orderItemsList;

	static OrderService orderService = new OrderService();
	static OrderItemService orderItemService = new OrderItemService();

	public void order() {
		orderService.addOrSaveOrder(order);
		System.out.println("order successfully saved.");
		for (OrderItem orderItem : orderItemsList) {
		}
		order = new Order();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItem> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItem> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}

}
