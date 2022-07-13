package com.ecom.payload;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {

	private int categoryId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String categoryDesc;
	
	public CategoryDto(int categoryId, String title, String categoryDesc) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.categoryDesc = categoryDesc;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	
}
