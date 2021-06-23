package com.udc.muei.tfm.profiledataservice.controller.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
 * 
 * The Class SignupRequestDTO.
 * 
 * @author a.oteroc
 * 
 */
public class SignupRequestDTO {

	@NotBlank
	@Size(min = 3, max = 20)
	private String userName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	@NotBlank
	@Size(min = 1, max = 200)
	private String firstName;

	@NotBlank
	@Size(min = 1, max = 200)
	private String lastName;

	@NotBlank
	private Date birthdate;

	private String location;

	private String webSite;

	private String biography;

	private String pmiProfile;

	private boolean notificationEmails;

	private boolean marketingEmails;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
