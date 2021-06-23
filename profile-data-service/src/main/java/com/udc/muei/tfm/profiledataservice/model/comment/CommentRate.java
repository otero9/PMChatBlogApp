package com.udc.muei.tfm.profiledataservice.model.comment;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class RateComment.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "comment_rates")
public class CommentRate {

	@Id
	private String commentRateId;

	@DBRef
	private User user;

	@DBRef
	private Comment comment;

	private int value;

	private Date createdDate;

	private Date updateDate;

	public CommentRate() {

	}

	public CommentRate(String commentRateId, User user, Comment comment, int value, Date createdDate, Date updateDate) {
		super();
		this.commentRateId = commentRateId;
		this.user = user;
		this.comment = comment;
		this.value = value;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	public String getCommentRateId() {
		return commentRateId;
	}

	public void setCommentRateId(String commentRateId) {
		this.commentRateId = commentRateId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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
