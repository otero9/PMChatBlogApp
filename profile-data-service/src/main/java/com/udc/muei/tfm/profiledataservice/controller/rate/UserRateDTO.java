package com.udc.muei.tfm.profiledataservice.controller.rate;

/*
 * 
 * The Class UserRateDTO.
 * 
 * @author a.oteroc
 * 
 */
public class UserRateDTO {

	String valorationId;

	String userName;

	String userId;

	String userRatedId;

	int value;

	public UserRateDTO() {

	}

	public UserRateDTO(String valorationId, String userName, String userId, String userRatedId, int value) {
		super();
		this.valorationId = valorationId;
		this.userName = userName;
		this.userId = userId;
		this.userRatedId = userRatedId;
		this.value = value;
	}

	public String getValorationId() {
		return valorationId;
	}

	public void setValorationId(String valorationId) {
		this.valorationId = valorationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRatedId() {
		return userRatedId;
	}

	public void setUserRatedId(String userRatedId) {
		this.userRatedId = userRatedId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
