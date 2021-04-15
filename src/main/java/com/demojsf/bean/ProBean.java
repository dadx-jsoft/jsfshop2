package com.demojsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.demojsf.pojo.Product;
import com.demojsf.service.ProductService;

@Named
@SessionScoped 
public class ProBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List < Product > productsList;
	
	Product product = new Product();
	Product newProduct = new Product();
	static ProductService productService = new ProductService();
	
	public List < Product > getProducts()  
    {  
		productsList = productService.getProducts(null);
        return productsList;  
    }  
	
	public void addProduct()  
    {  
		productService.addOrSaveProduct(newProduct);
        
        System.out.println("Product successfully saved.");  
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Product successfully saved.");  
//        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newProduct = new Product();  
    }  
	
	public void changeProduct(Product product)  
    {  
        this.product = product;  
    }
	public void updateProduct(Product product)  
    {  
//        String Name = user.getName();  
//        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);  
//        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        productService.addOrSaveProduct(product);
        System.out.println("Product Info successfully updated.");  
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
//        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        product = new Product();  
    }  
	public void deleteProduct(Product product)  
    {  
//        String Name = user.getName();  
        //FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",contactName);  
        // RequestContext.getCurrentInstance().showMessageInDialog(message3);  
        productService.deleteProduct(product);
        System.out.println("Product Info successfully deleted.");  
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
//        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
	
	public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited product name: ", ((Product) event.getObject()).getName());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Product editedProduct= (Product) event.getObject();  
        productService.addOrSaveProduct(editedProduct);
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        productsList.remove((Product) event.getObject());
    }

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}

	public static ProductService getProductService() {
		return productService;
	}

	public static void setProductService(ProductService productService) {
		ProBean.productService = productService;
	}  
	
}
