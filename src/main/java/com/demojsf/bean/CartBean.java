package com.demojsf.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.inject.Named;

import com.demojsf.pojo.Product;
import com.demojsf.service.ProductService;

//@ManagedBean
@Named
@SessionScoped
public class CartBean implements Serializable, ValueChangeListener {

	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductService();

	private Product newProduct = new Product();

	
	public CartBean() {
	}
	
	@PostConstruct
	public void init() {
		Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.get("cart") == null) {
			sessionMap.put("cart", new HashMap<>());
		}
	}
	
	public void performCartChanged(int productId) {
		System.out.println("==================" + productId);
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		if (productId > 0) {
			Product product = productService.getProductById(productId);
			if (cart.get(productId) == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("productId", productId);
				data.put("productName", product.getName());
				data.put("productPrice", product.getPrice());
				data.put("count", 1);

				cart.put(productId, data);
			} else {
				Map<String, Object> data = (Map<String, Object>) cart.get(productId);
				data.put("count", Integer.parseInt(data.get("count").toString()) + 1);
			}
		}
		productId=0;
	}

	public List<Map<String, Object>> getCarts() {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");

		List<Map<String, Object>> kq = new ArrayList<>();
		for (Object o : cart.values()) {
			Map<String, Object> d = (Map<String, Object>) o;
			kq.add(d);
		}

		return kq;
	}

	public void addItemToCart(int productId, String productName, BigDecimal price) {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		if (cart.get(productId) == null) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("productId", productId);
			data.put("productName", productName);
			data.put("productPrice", price);
			data.put("count", 1);

			cart.put(productId, data);
		} else {
			Map<String, Object> data = (Map<String, Object>) cart.get(productId);
			data.put("count", Integer.parseInt(data.get("count").toString()) + 1);
		}
	}

	public void addItemToCart(int productId) {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		if (productId > 0) {
			Product product = productService.getProductById(productId);
			if (cart.get(productId) == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("productId", productId);
				data.put("productName", product.getName());
				data.put("productPrice", product.getPrice());
				data.put("count", 1);

				cart.put(productId, data);
			} else {
				Map<String, Object> data = (Map<String, Object>) cart.get(productId);
				data.put("count", Integer.parseInt(data.get("count").toString()) + 1);
			}
		}
	}

	public void deleteItemInCart(int productId) {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		if (cart.get(productId) != null) {
			cart.remove(productId);
		}
	}

	public int getSoLuongSanPham() {
		Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cart");
		int soLuongSanPham = 0;
		for (Object o : cart.values()) {
			Map<String, Object> d = (Map<String, Object>) o;
			soLuongSanPham += Integer.parseInt(d.get("count").toString());
		}
		return soLuongSanPham;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}

	@Override
	public void processValueChange(ValueChangeEvent e) throws AbortProcessingException {
		if (null != e.getNewValue()) {
			int productId = Integer.parseInt(e.getNewValue().toString());
			System.out.println("==================" + productId);
			Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get("cart");
			if (productId > 0) {
				Product product = productService.getProductById(productId);
				if (cart.get(productId) == null) {
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("productId", productId);
					data.put("productName", product.getName());
					data.put("productPrice", product.getPrice());
					data.put("count", 1);

					cart.put(productId, data);
				} else {
					Map<String, Object> data = (Map<String, Object>) cart.get(productId);
					data.put("count", Integer.parseInt(data.get("count").toString()) + 1);
				}
			}
		}
	}


}
