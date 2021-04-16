package com.demojsf.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.demojsf.pojo.Order;
import com.demojsf.pojo.OrderItem;
import com.demojsf.pojo.Product;
import com.demojsf.service.OrderService;
import com.demojsf.service.ProductService;

@Named
@SessionScoped
public class OrderBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Order order = new Order();

	static OrderService orderService = new OrderService();
	static ProductService productService = new ProductService();

	public void order() {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		for (Entry<Integer, Object> entry : cart.entrySet()) {
			int productId = entry.getKey();
			Product product = productService.getProductById(productId);
			OrderItem orderItem = new OrderItem();
			Map<String, Object> data = (Map<String, Object>) cart.get(productId);
			orderItem.setQuantity(Integer.parseInt(data.get("count").toString()));
			orderItem.setProduct(product);
			order.addOrderItem(orderItem);
		}

		orderService.addOrSaveOrder(order);
		System.out.println("order successfully saved.");
		
		Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//		sessionMap.clear();
		sessionMap.put("cart", new HashMap<>());
		
		order = new Order();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


}
