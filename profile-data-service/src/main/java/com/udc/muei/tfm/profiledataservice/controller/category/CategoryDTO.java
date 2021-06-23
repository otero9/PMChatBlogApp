package com.udc.muei.tfm.profiledataservice.controller.category;

/*
 * 
 * The Class CategoryDTO.
 * 
 * @author a.oteroc
 * 
 */
public class CategoryDTO {

	private String categoryId;
	private String categoryName;
	private String categoryDescription;

	public CategoryDTO() {

	}

	public CategoryDTO(String categoryId, String categoryName, String categoryDescription) {
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}