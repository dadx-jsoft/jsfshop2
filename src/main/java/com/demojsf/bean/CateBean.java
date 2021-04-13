package com.demojsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.demojsf.pojo.Category;
import com.demojsf.pojo.Manufacturer;
import com.demojsf.service.CategoryService;
import com.demojsf.service.ManufacturerService;

//@ManagedBean
@Named
@SessionScoped
public class CateBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final static CategoryService cateService = new CategoryService();
	private final static ManufacturerService manuService = new ManufacturerService();
	
	public CateBean() {
	}

	public List<Category> getCategories() {
		return cateService.getCategories();
	}
	
	public List<Manufacturer> getManufacturers(){
		return manuService.getManufacturers();
	}
	
}
