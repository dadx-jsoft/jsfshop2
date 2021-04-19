package com.demojsf.bean;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "bean")
//@SessionScoped
@ViewScoped
public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String page;

	@PostConstruct
	public void init() {
//		page = "product-list"; // Default include.
		page = "order";
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}