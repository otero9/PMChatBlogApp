package com.udc.muei.tfm.profiledataservice.model.blog;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class BlogRate.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "blog_rates")
public class BlogRate {

	@Id
	private String blogRateId;

	@DBRef
	private User user;

	@DBRef
	private Blog blog;

	private int value;

	private Date createdDate;

	private Date updateDate;

	public BlogRate() {

	}

	public BlogRate(String blogRateId, User user, Blog blog, int value, Date createdDate,
			Date updateDate) {
		super();
		this.blogRateId = blogRateId;
		this.user = user;
		this.blog = blog;
		this.value = value;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}


	public String getBlogRateId() {
		return blogRateId;
	}


	public void setBlogRateId(String blogRateId) {
		this.blogRateId = blogRateId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Blog getBlog() {
		return blog;
	}


	public void setBlog(Blog blog) {
		this.blog = blog;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
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