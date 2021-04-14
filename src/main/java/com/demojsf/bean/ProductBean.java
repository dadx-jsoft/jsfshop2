package com.demojsf.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.demojsf.pojo.Category;
import com.demojsf.pojo.Manufacturer;
import com.demojsf.pojo.Product;
import com.demojsf.service.ProductService;

//@ManagedBean
@Named
@SessionScoped
//@RequestScoped
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static ProductService productSevice = new ProductService();

	private int productId;
	private String name;
	private String description;
	private BigDecimal price;
	private Category category;
	private Set<Manufacturer> manufacturers;
	private Part imgFile;

	@Inject
	private Bean bean;

	public static ProductService getProductsevice() {
		return productSevice;
	}

//	public ProductBean() {
//		if (!FacesContext.getCurrentInstance().isPostback()) {
//			String productId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
//					.get("product_id");
//			if (productId != null && !productId.isEmpty()) {
//				Product p = productSevice.getProductById(Integer.parseInt(productId));
//				this.productId = p.getId();
//				this.name = p.getName();
//				this.description = p.getDescription();
//				this.price = p.getPrice();
//				this.category = p.getCategory();
//				this.manufacturers = p.getManufacturers();
//			}
//		}
//
//	}

	public void initEdit(Product p) {
//		if (!FacesContext.getCurrentInstance().isPostback()) {
//			String productId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
//					.get("product_id");

		this.productId = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.category = p.getCategory();
		this.manufacturers = p.getManufacturers();
		bean.setPage("save-update-product");

//		}

	}

	public void initAdd() {
//		if (!FacesContext.getCurrentInstance().isPostback()) {
//			String productId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
//					.get("product_id");

//		this.productId = p.getId();
//		this.name = p.getName();
//		this.description = p.getDescription();
//		this.price = p.getPrice();
//		this.category = p.getCategory();
//		this.manufacturers = p.getManufacturers();
		bean.setPage("add-product");

//		}

	}

	public void addProduct() {

		Product p = new Product();
		p.setName(this.name);
		p.setDescription(this.description);
		p.setPrice(this.price);
		p.setCategory(this.category);
		p.setManufacturers(this.manufacturers);

		try {
			if (this.imgFile != null) {
				this.uploadFile();
				p.setImage("upload/" + this.imgFile.getSubmittedFileName());
			}

			if (productSevice.addOrSaveProduct(p)) {
				bean.setPage("product-list");
//				return "product-list?faces-redirect=true";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		bean.setPage("save-update-product");
//		return "products";
	}

	public void editProduct() {
		Product p = productSevice.getProductById(this.productId);
		p.setName(this.name);
		p.setDescription(this.description);
		p.setPrice(this.price);
		p.setCategory(this.category);
		p.setManufacturers(this.manufacturers);

		try {
			if (this.imgFile != null) {
				this.uploadFile();
				p.setImage("upload/" + this.imgFile.getSubmittedFileName());
			}

			if (productSevice.addOrSaveProduct(p)) {
				bean.setPage("product-list");
//				return "product-list?faces-redirect=true";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		bean.setPage("save-update-product");
//		return "products";
	}

	public String deleteProduct(Product p) throws Exception {
		if (productSevice.deleteProduct(p))
			return "successful";
		throw new Exception("Something wrong !");
	}

	public List<Product> getProducts() {
		return productSevice.getProducts(null);
	}

	private void uploadFile() throws IOException {
//		String path = FacesContext.getCurrentInstance().getExternalContext()
//									.getRealPath("/resources/images/upload")
//									+"/"+this.imgFile.getSubmittedFileName();
		String path = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadPath") + "/"
				+ this.imgFile.getSubmittedFileName();
		try (InputStream in = this.imgFile.getInputStream(); FileOutputStream out = new FileOutputStream(path)) {
			byte[] b = new byte[1024];
			int byteRead;

			while ((byteRead = in.read(b)) != -1) {
				out.write(b, 0, byteRead);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(Set<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public Part getImgFile() {
		return imgFile;
	}

	public void setImgFile(Part imgFile) {
		this.imgFile = imgFile;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
