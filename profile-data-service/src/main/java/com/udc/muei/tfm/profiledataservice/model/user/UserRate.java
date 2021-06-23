package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
 * The Class UserRate.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "user_rates")
public class UserRate {

	@Id
	private String userRateId;

	@DBRef
	private User user;

	@DBRef
	private User valuedUser;

	private int value;

	private Date createdDate;

	private Date updateDate;

	public UserRate() {

	}

	public UserRate(String userRateId, User user, User valuedUser, int value, Date createdDate, Date updateDate) {
		super();
		this.userRateId = userRateId;
		this.user = user;
		this.valuedUser = valuedUser;
		this.value = value;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	public String getUserRateId() {
		return userRateId;
	}

	public void setUserRateId(String userRateId) {
		this.userRateId = userRateId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getValuedUser() {
		return valuedUser;
	}

	public void setValuedUser(User valuedUser) {
		this.valuedUser = valuedUser;
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
