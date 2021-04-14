package com.demojsf.bean;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "bean")
@ViewScoped
public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String page;

	@PostConstruct
	public void init() {
		page = "content"; // Default include.
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}