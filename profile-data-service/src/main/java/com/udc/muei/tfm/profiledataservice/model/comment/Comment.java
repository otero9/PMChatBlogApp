package com.udc.muei.tfm.profiledataservice.model.comment;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.blog.Blog;
import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class Comment.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "comments")
public class Comment {

	@Id
	private String commentId;

	@DBRef
	private User user;
	
	@DBRef
	private Blog blog;
	
	private String value;
	
	private Date createdDate;
	
	private Date updateDate;
	

	public Comment() {

	}


	public Comment(String commentId, User user, Blog blog, String value, Date createdDate, Date updateDate) {
		super();
		this.commentId = commentId;
		this.user = user;
		this.blog = blog;
		this.value = value;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}


	public String getCommentId() {
		return commentId;
	}


	public void setCommentId(String commentId) {
		this.commentId = commentId;
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


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
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