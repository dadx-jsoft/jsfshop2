package com.demojsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.demojsf.pojo.Order;
import com.demojsf.pojo.OrderItem;
import com.demojsf.pojo.Product;
import com.demojsf.service.OrderItemService;
import com.demojsf.service.ProductService;

@Named
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProductService productService;

	private List<OrderItem> orderItems;

	private OrderItemService orderItemService;

	public CartBean() {
	}

	@PostConstruct
	public void init() {
		orderItems = new ArrayList<>();
		productService = new ProductService();
		orderItemService = new OrderItemService();
	}

	public void addItemToCart(int productId) {

		if (productId > 0) {
			if (getOrderItemByProductId(productId) == null) { // chưa có sp trong giỏ
				Product product = productService.getProductById(productId);
				OrderItem newItem = new OrderItem();
				newItem.setProduct(product);
				newItem.setQuantity(1);
				orderItems.add(newItem);
			} else { // đã tồn tại sp trong giỏ
				OrderItem oldItem = getOrderItemByProductId(productId);
				oldItem.setQuantity(oldItem.getQuantity() + 1);
			}
		}

	}

	public boolean updateCart(Integer proId, Order o) {
		if (proId > 0) {
			// không tồn tại product
			Product p = productService.getProductById(proId);
			if(p == null) { // lấy lại ds item trong db cho bào giỏ hàng
				orderItems = orderItemService.getOrderItemsByOrderId(o);
				return false;
			}
			
			orderItems = orderItemService.getOrderItemsByOrderId(o);
			if (getOrderItemByProductId(proId) == null) { // chưa có sp trong giỏ
				Product product = productService.getProductById(proId);
				OrderItem newItem = new OrderItem();
				newItem.setProduct(product);
				newItem.setQuantity(1);
				orderItems.add(newItem);
			} else { // đã tồn tại sp trong giỏ
				OrderItem oldItem = getOrderItemByProductId(proId);
				oldItem.setQuantity(oldItem.getQuantity() + 1);
			}
		}
		return true;
		
	}

	public void deleteItemInCart(Product p) {
		// c1
		Iterator<OrderItem> iterator = orderItems.iterator();
		while (iterator.hasNext()) {
			OrderItem orderItem = iterator.next();
			if (orderItem.getProduct() == p) {
				// languages.remove(language); // Don't use ArrayList.remove()
				iterator.remove();
				orderItemService.deleteOrderItem(orderItem); // xóa trong db
			}
		}
		// c2
//		orderItems.removeIf(orderItem -> orderItem.getProduct() == p);
	}

	public int getSoLuongSanPham() {
		return orderItems.size();
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	private OrderItem getOrderItemByProductId(int id) {
		for (OrderItem orderItem : orderItems) {
			if (orderItem.getProduct().getId() == id)
				return orderItem;
		}
		return null;
	}

}
