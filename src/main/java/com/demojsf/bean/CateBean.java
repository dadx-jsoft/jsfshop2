package com.demojsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.demojsf.pojo.Category;
import com.demojsf.service.CategoryService;

//@ManagedBean
@Named
@SessionScoped
public class CateBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private final static CategoryService cateService = new CategoryService();

	public CateBean() {
	}

	public List<Category> getCategories() {
		return cateService.getCategories();
	}

}
