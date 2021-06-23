package com.udc.muei.tfm.profiledataservice.controller.rate;

/*
 * 
 * The Class CommentRateDTO.
 * 
 * @author a.oteroc
 * 
 */
public class CommentRateDTO {

	String commentRateId;

	String userName;

	String commentId;

	int value;

	public CommentRateDTO() {

	}

	public CommentRateDTO(String commentRateId, String userName, String commentId, int value) {
		super();
		this.commentRateId = commentRateId;
		this.userName = userName;
		this.commentId = commentId;
		this.value = value;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentRateId() {
		return commentRateId;
	}

	public void setCommentRateId(String commentRateId) {
		this.commentRateId = commentRateId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
