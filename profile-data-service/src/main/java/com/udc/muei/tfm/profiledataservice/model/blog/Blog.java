package com.udc.muei.tfm.profiledataservice.model.blog;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class Blog.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "blogs")
public class Blog {

	@Id
	private String blogId;

	@DBRef
	private Category category;

	@DBRef
	private User user;

	private String title;

	private String header;

	private String body;

	private String footer;

	private Date createdDate;

	private Date updateDate;

	public Blog() {

	}

	public Blog(String blogId, Category category, User user, String title, String header, String body, String footer,
			Date createdDate, Date updateDate) {
		super();
		this.blogId = blogId;
		this.category = category;
		this.user = user;
		this.title = title;
		this.header = header;
		this.body = body;
		this.footer = footer;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}