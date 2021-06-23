package com.udc.muei.tfm.profiledataservice.model.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
 * The Class User.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "users")
public class User {

	@Id
	private String userId;

	private String userName;

	private String password;

	private String email;

	private String firstName;

	private String lastName;

	private Date birthdate;

	private String location;

	private String webSite;

	private String biography;

	private String pmiProfile;

	private boolean notificationEmails;

	private boolean marketingEmails;

	private boolean isAdmin;

	private long points;

	private Date createDate;

	private Date updateDate;

	public User() {

	}

	public User(String userId, String userName, String password, String email, String firstName, String lastName,
			Date birthdate, String location, String webSite, String biography, String pmiProfile,
			boolean notificationEmails, boolean marketingEmails, boolean isAdmin, long points, Date createDate,
			Date updateDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.location = location;
		this.webSite = webSite;
		this.biography = biography;
		this.pmiProfile = pmiProfile;
		this.notificationEmails = notificationEmails;
		this.marketingEmails = marketingEmails;
		this.isAdmin = isAdmin;
		this.points = points;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPmiProfile() {
		return pmiProfile;
	}

	public void setPmiProfile(String pmiProfile) {
		this.pmiProfile = pmiProfile;
	}

	public boolean isNotificationEmails() {
		return notificationEmails;
	}

	public void setNotificationEmails(boolean notificationEmails) {
		this.notificationEmails = notificationEmails;
	}

	public boolean isMarketingEmails() {
		return marketingEmails;
	}

	public void setMarketingEmails(boolean marketingEmails) {
		this.marketingEmails = marketingEmails;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}