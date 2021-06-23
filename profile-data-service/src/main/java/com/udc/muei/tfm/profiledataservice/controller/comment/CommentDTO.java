package com.udc.muei.tfm.profiledataservice.controller.comment;

import java.util.Date;

/*
 * 
 * The Class CommentDTO.
 * 
 * @author a.oteroc
 * 
 */
public class CommentDTO {

	private String commentId;
	private String userName;
	private String commentValue;
	private int points;
	private Date createdDate;

	public CommentDTO() {

	}

	public CommentDTO(String commentId, String userName, String commentValue, int points, Date createdDate) {
		super();
		this.commentId = commentId;
		this.userName = userName;
		this.commentValue = commentValue;
		this.points = points;
		this.createdDate = createdDate;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentValue() {
		return commentValue;
	}

	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
