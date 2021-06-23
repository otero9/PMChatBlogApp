package com.udc.muei.tfm.profiledataservice.controller.rate;

/*
 * 
 * The Class BlogRateDTO.
 * 
 * @author a.oteroc
 * 
 */
public class BlogRateDTO {

	String blogRateId;

	String userName;

	String blogId;

	int value;

	public BlogRateDTO() {

	}

	public BlogRateDTO(String blogRateId, String userName, String blogId, int value) {
		super();
		this.blogRateId = blogRateId;
		this.userName = userName;
		this.blogId = blogId;
		this.value = value;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getBlogRateId() {
		return blogRateId;
	}

	public void setBlogRateId(String blogRateId) {
		this.blogRateId = blogRateId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
