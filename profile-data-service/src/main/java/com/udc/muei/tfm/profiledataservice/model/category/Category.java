package com.udc.muei.tfm.profiledataservice.model.category;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
 * The Class Category.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "categories")
public class Category {

	@Id
	private String categoryId;

	private String categoryName;

	private String categoryDescription;

	private Date createDate;

	private Date updateDate;

	public Category() {

	}

	public Category(String categoryId, String categoryName, String categoryDescription, Date createdDate,
			Date updateDate) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.createDate = createdDate;
		this.updateDate = updateDate;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createdDate) {
		this.createDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}