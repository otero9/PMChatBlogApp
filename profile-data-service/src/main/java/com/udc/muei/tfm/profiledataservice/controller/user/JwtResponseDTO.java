package com.udc.muei.tfm.profiledataservice.controller.user;

import java.util.Date;

/*
 * 
 * The Class JwtResponseDTO.
 * 
 * @author a.oteroc
 * 
 */
public class JwtResponseDTO {

	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private Date birthDate;
	private Date createdDate;
	private long points;

	public JwtResponseDTO(String accessToken, String id, String username, String email, String firstname,
			String lastname, Date birthDate, Date createdDate, long points) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.createdDate = createdDate;
		this.points = points;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

}
