package com.demojsf.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.demojsf.pojo.Order;
import com.demojsf.pojo.OrderItem;
import com.demojsf.service.OrderItemService;
import com.demojsf.service.OrderService;
import com.demojsf.service.ProductService;

@Named
@SessionScoped
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Order order = new Order();
	private boolean showButton = true;
	private boolean hideInput = true;

	static OrderService orderService = new OrderService();
	static ProductService productService = new ProductService();
	OrderItemService orderItemService = new OrderItemService();
	
	@Inject
	private CartBean cartBean;

	public void performOrder() {
		
		if(cartBean.getOrderItems().size() == 0) { // giỏ hàng trống
			return;
		}
		for (OrderItem orderItem : cartBean.getOrderItems()) {
			order.addOrderItem(orderItem);
		}

		orderService.addOrSaveOrder(order);
		System.out.println("order " + order.getId() + " successfully saved.");

		this.showButton = false;
		this.setHideInput(false);
	}

	public void updateOrder(Integer proId) {
		if(null != proId) {
			if(cartBean.updateCart(proId, this.order) == false) {
				System.out.println("order " + order.getId() + " can not updated.");
				return;
			}
		}
		if (order.getId() > 0) {
			order.setOrderItems(new ArrayList<OrderItem>());
			for (OrderItem orderItem : cartBean.getOrderItems()) {
				order.addOrderItem(orderItem);
			}

			orderService.addOrSaveOrder(order);
			System.out.println("order " + order.getId() + " successfully updated.");
		}
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isShowButton() {
		return showButton;
	}

	public void setShowButton(boolean showButton) {
		this.showButton = showButton;
	}

	public boolean isHideInput() {
		return hideInput;
	}

	public void setHideInput(boolean hideInput) {
		this.hideInput = hideInput;
	}


}
